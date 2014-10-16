package org.vkedco.compvision.dog;

import java.util.ArrayList;
import java.util.HashMap;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.vkedco.opencv.haar.HaarWaveletWin;
import org.vkedco.opencv.haar.HaarWaveletWinStats;
import org.vkedco.opencv.haar.TwoDMatHaar;
import org.vkedco.opencv.haar.TwoDMatHaar.EDGE_TYPE;


public class DOG {

	private static int rectSize = DOGConstants.RECT_SIZE;
	private final static double theta_thresh = DOGConstants.THETA_THRESH;
	private final static double magn_thresh = DOGConstants.MAGN_THRESH;
	// private final static double freq_thresh=Constants.FREQ_THRESH;
	private final static double sim_theta_thresh = DOGConstants.SIM_THETA_THRESH;
	// private static Scalar COLOR_WHITE=new Scalar(255, 255, 255);
	// private static Scalar COLOR_BLACK=new Scalar(0, 0, 0);
	static final Scalar COLOR_YELLOW = new Scalar(255, 255, 0);

	public static boolean saveImage = false;

	public DOG() {
	}

	// VK: 26sept14: this method returns the DOG for a submat whose top left
	// corner is (row, col) and whose
	// side is equal to size.
	final static double IMPOSSIBLE_DOG = 1000;

	public static double getDOGForSquareSubMat(Mat mat, int row, int col,
			int size, String img_path) {
		Rect mask = new Rect(col, row, size, size);
		Mat subImage = new Mat(mat, mask);

		RGOT rgot = Gradients.getMostFrequentRGOT(Gradients
				.computeRGOTTableForMat(subImage, theta_thresh, magn_thresh));
		if (rgot != null) {
			return rgot.dtheta;
		} else {
			return IMPOSSIBLE_DOG;
		}
	}

	public static void getDOG(Mat mat, int row, int col, int size,
			String img_path) {
		Mat wholeImg = mat;
		Mat tempImg = wholeImg.clone();
		Mat subImage = new Mat();
		Gradients gradientsDetector = new Gradients();
		HashMap<Point, RGOT> GGOT = new HashMap<Point, RGOT>();

		ArrayList<RGOTNeighborhood> neighborhoodList = new ArrayList<RGOTNeighborhood>();

		if (row + size < tempImg.rows() && col + size < tempImg.cols()) {
			// mask of rectSize
			Rect mask = new Rect(col, row, size, size);
			subImage = new Mat(tempImg, mask);

			RGOT rGot = gradientsDetector.computeRGOT(subImage, theta_thresh,
					magn_thresh);
			GGOT.put(new Point(col, row), rGot);
			if (rGot != null) {
				rGot.setColumn(col);
				rGot.setRow(row);
				{
					RGOTNeighborhood neighbourhood = findNeighborhoodForRGOT(
							rGot, neighborhoodList);
					if (neighbourhood != null) {
						neighbourhood.membersList.add(rGot);
					} else {
						RGOTNeighborhood newNeighborhood = new RGOTNeighborhood(
								rGot.dtheta, neighborhoodList.size() + 1);
						newNeighborhood.membersList.add(rGot);
						neighborhoodList.add(newNeighborhood);
					}
				}
			}
		}

		StringBuffer saved_img_path = new StringBuffer(img_path);
		saved_img_path.append("_" + row + "_" + col + "_" + size + "_"
				+ "DOG.JPG");
		drawDOGs(tempImg, neighborhoodList, GGOT, size,
				saved_img_path.toString());
	}

	private static void drawDOGs(Mat img,
			ArrayList<RGOTNeighborhood> neighborhoodList,
			HashMap<Point, RGOT> gGOT, int rect_size, String img_path) {

		if (neighborhoodList.size() > 0) {
			RGOTNeighborhood barcodeNeighborhood = neighborhoodList.get(0);

			for (RGOTNeighborhood bin : neighborhoodList) {
				if (barcodeNeighborhood.membersList.size() < bin.membersList
						.size()) {
					barcodeNeighborhood = bin;
				}
			}
			// double barcodeNeighborhoodTheta =
			// barcodeNeighborhood.membersList.get(0).dtheta;
			Mat DOGmat = img.clone();
			// Draw the GGOTS
			for (Point loc : gGOT.keySet()) {
				RGOT rgot = gGOT.get(loc);
				if (rgot != null) {
					Core.rectangle(DOGmat, new org.opencv.core.Point(
							rgot.column, rgot.row), new org.opencv.core.Point(
							rgot.column + rect_size, rgot.row + rect_size),
							new Scalar(0, 255, 0), 1);
					Core.putText(DOGmat, "" + rgot.dtheta,
							new org.opencv.core.Point(rgot.column,
									rgot.row + 20),
							Core.FONT_HERSHEY_COMPLEX_SMALL, 0.5, COLOR_YELLOW);
					Core.putText(DOGmat, "" + rgot.freq,
							new org.opencv.core.Point(rgot.column, rgot.row
									+ (rect_size / 2)),
							Core.FONT_HERSHEY_COMPLEX_SMALL, 0.5, COLOR_YELLOW);
					Highgui.imwrite(img_path, DOGmat);
				}
			}
		}
	}

	private static RGOTNeighborhood findNeighborhoodForRGOT(RGOT rGot,
			ArrayList<RGOTNeighborhood> neighborhoodList) {
		boolean sim = false;

		for (RGOTNeighborhood neighborhood : neighborhoodList) {
			sim = thetaSim(neighborhood.bin_Theta, rGot.dtheta,
					sim_theta_thresh);
			if (sim) {
				if (hasNeighborMask(neighborhood, rGot))
					return neighborhood;
			}
		}
		return null;
	}

	private static boolean hasNeighborMask(RGOTNeighborhood neighborhood,
			RGOT rGot) {

		boolean result = false;

		for (RGOT rgotMember : neighborhood.membersList) {
			// Horizontal
			if (rGot.getRow() == rgotMember.getRow())
				if (Math.abs(rGot.getColumn() - rgotMember.getColumn()) == rectSize) {
					result = true;
					break;
				}
			// /Vertical
			if (rGot.getColumn() == rgotMember.getColumn())
				if (Math.abs(rGot.getRow() - rgotMember.getRow()) == rectSize) {
					result = true;
					break;
				}
			// Diagonal
			if (Math.abs(rGot.getColumn() - rgotMember.getColumn()) == rectSize)
				if (Math.abs(rGot.getRow() - rgotMember.getRow()) == rectSize) {
					result = true;
					break;
				}
		}
		return result;

	}

	private static boolean thetaSim(double bin_Theta, double dtheta,
			double thetaThresh) {
		if (Math.abs(dtheta - bin_Theta) < thetaThresh)
			return true;
		else if (Math.abs((dtheta + 180) - bin_Theta) < thetaThresh)
			return true;
		else if (Math.abs((dtheta - 180) - bin_Theta) < thetaThresh)
			return true;
		else
			return false;
	}
}
