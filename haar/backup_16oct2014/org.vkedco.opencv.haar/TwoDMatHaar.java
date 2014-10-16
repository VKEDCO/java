package org.vkedco.opencv.haar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.vkedco.wavelets.haar.TwoDHaar;
import org.vkedco.wavelets.tests.TwoDMatHaarTests;

// bugs to vladimir dot kulyukin at gmail dot com

public class TwoDMatHaar {

	public static final void displayMat(Mat mat) {
		for (int r = 0; r < mat.rows(); r++) {
			System.out.print("Row " + r + ": ");
			for (int c = 0; c < mat.cols(); c++) {
				System.out.print(mat.get(r, c)[0] + " ");
			}
			System.out.println();
		}
	}
	
	static double log(int x, int base) {
		return (Math.log(x) / Math.log(base));
	}

	static final boolean isPowOf2(int n) {
		if ( n < 1 ) {
			return false;
		}
		else {
			final double pOf2 = TwoDMatHaar.log(n, 2);
			return Math.abs(pOf2 - (int)pOf2) == 0;
		}
	}
	
	static final int largestPowOf2SmallerThanX(int n) {
		int rslt = n-1;
		while ( !TwoDMatHaar.isPowOf2(rslt) ) { rslt--; }
		return rslt;
	}
	
	public static final double[][] getMax2DPowOf2SquarePixArrayFromGrayscaleMat(Mat mat) {
		//System.out.println("get2DPixelArrayFromGrayscaleMat");
		int num_rows = mat.rows();
		int num_cols = mat.cols();
		
		if ( num_rows == 0 || num_cols == 0 ) {
			//System.out.println("empty mat");
			return null;
		}
		
		if ( !TwoDMatHaar.isPowOf2(num_rows) ) {
			//System.out.println("check get2DPixelArrayFromMat()");
			num_rows = TwoDMatHaar.largestPowOf2SmallerThanX(num_rows);
			//System.out.println("num_rows = " + num_rows);
		}
		
		if ( !TwoDMatHaar.isPowOf2(num_cols) ) {
			num_cols = TwoDMatHaar.largestPowOf2SmallerThanX(num_cols);
			//System.out.println("num_cols = " + num_cols);
		}
		
		
		final int dim = Math.min(num_rows, num_cols);		
		//System.out.println("\ndim = " + dim);
		double[][] pix_vals = new double[dim][dim];

		for (int r = 0; r < dim; r++) {
			for (int c = 0; c < dim; c++) {
				pix_vals[r][c] = mat.get(r, c)[0];
			}
		}
		//System.out.println("ImgArr's num_rows = " + pix_vals.length);
		//System.out.println("ImgArr's num_cols = " + pix_vals[0].length);
		return pix_vals;
	}
	
	public static final double[][] get2DSquarePixArrayFromGrayscalMatAt(Mat mat, int r, int c, int size) {
		final double[][] pixvals = new double[size][size];
		for(int row = r; row < r + size; row++) {
			for(int col = c; col < c + size; col++) {
				pixvals[row-r][col-c] = mat.get(row, col)[0];
			}
		}
		return pixvals;
	}
	
	static final Mat getGrayscaleMatFrom2DPixelArray(Mat mat, double[][] pixvals) {
		// steps is the num_steps_forward
		Mat newMat = new Mat(mat.rows(), mat.cols(), Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		for (int i = 0; i < pixvals.length; i++) {
			for (int j = 0; j < pixvals.length; j++) {
				newMat.put(i, j, pixvals[i][j]);
			}
		}
		return newMat;
	}
	
	// Mat is of size 2^n x 2^n.
	public static ArrayList<double[][]> orderedFastHaarWaveletTransformForNumIters(
			Mat mat, int n, int num_iters) {
		double[][] pixels = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(mat);
		ArrayList<double[][]> rslt = TwoDHaar
				.orderedFastHaarWaveletTransformForNumIters(pixels, n,
						num_iters);
		pixels = null;
		return rslt;
	}
	
	/***************************************************************
	 * 
	 * Given three thresholds: Theta_HAA, Theta_VAA, Theta_DAA
	 * There are the following stats computed for each window N x N,
	 * where N = 2^n: HAA, VAA, DAA.
	 * - If HAA >= Theta_HAA and VAA < Theta_VAA and DAA < Theta_DAA,
	 *   then it a vertical edge
	 * - If VAA >= Theta_VAA and HAA < Theta_HAA and DAA < Theta_DAA,
	 *   then it is a horizontal edge
	 * - If (HAA >= Theta_HAA and VAA >= Theta_VAA) or DAA >= Theta_DAA {
	 *     then it is a diagonal edge;
	 *     if ( DRA < 0 ) then edge is from NE to SW
	 *     if ( DRA > 0 ) then edge is from NW to SE  
	 * }
	 * 
	 * For now, we assume we iterate until the window is 2 x 2.
	 * 
	 ***************************************************************
	 */
	
	public static enum EDGE_TYPE { HOR, VER, DIG, NONE };
	
	 // N = 2^n
    public static EDGE_TYPE detectEdgeType(double[][] pixels, int n, double theta_HAA, double theta_VAA, double theta_DAA) {
    	ArrayList<double[][]> transform = TwoDHaar.orderedFastHaarWaveletTransformForNumIters(pixels, n, n-1);
    	double[][] horizontals = TwoDHaar.getHorizontalsFromOrderedList(transform);
    	double[][] verticals = TwoDHaar.getVerticalsFromOrderedList(transform);
    	double[][] diagonals = TwoDHaar.getDiagonalsFromOrderedList(transform);
    	double HAA = TwoDHaar.computeAbsAvrg(horizontals, 2);
    	double VAA = TwoDHaar.computeAbsAvrg(verticals, 2);
    	double DAA = TwoDHaar.computeAbsAvrg(diagonals, 2);
    	
    	horizontals = null;
    	verticals = null;
    	diagonals = null;
    	
    	if ( HAA >= theta_HAA && VAA < theta_VAA && DAA < theta_DAA ) {
    		return EDGE_TYPE.HOR;
    	}
    	else if ( VAA >= theta_VAA && HAA < theta_HAA && DAA < theta_DAA ) {
    		return EDGE_TYPE.VER;
    	}
    	else if ( ( HAA >= theta_HAA && VAA >= theta_VAA ) || DAA >= theta_DAA ) {
    		return EDGE_TYPE.DIG;
    	}
    	else {
    		return EDGE_TYPE.NONE;
    	}
    }
    
    public static HaarWaveletWinStats computeWaveletWinStats(double[][] pixels, int log2_window_size, 
    		double haa_thresh, double vaa_theta, double daa_thresh) {
    	//System.out.println("detectEdgeStats()");
    	//System.out.println("log2_window_size = " + log2_window_size);
    	//System.out.println("theta_HAA = " + theta_HAA);
    	//System.out.println("theta_VAA = " + theta_VAA);
    	//System.out.println("theta_DAA = " + theta_DAA);
    	ArrayList<double[][]> transform = TwoDHaar.orderedFastHaarWaveletTransformForNumIters(pixels, 
    			log2_window_size, log2_window_size-1);
    	
    	double[][] horizontals 	= TwoDHaar.getHorizontalsFromOrderedList(transform);
    	double[][] verticals 	= TwoDHaar.getVerticalsFromOrderedList(transform);
    	double[][] diagonals 	= TwoDHaar.getDiagonalsFromOrderedList(transform);
    	
    	//System.out.println("horizontals.length == " + horizontals.length);
    	//TwoDHaar.displaySample(horizontals, horizontals.length, 0);
    	//System.out.println();
    	//System.out.println("verticals.length == " + verticals.length);
    	//TwoDHaar.displaySample(verticals, verticals.length, 0);
    	//System.out.println();
    	//System.out.println("diagonals.length == " + diagonals.length);
    	//TwoDHaar.displaySample(diagonals, diagonals.length, 0);
    	//System.out.println();
    	
    	double HAA = TwoDHaar.computeAbsAvrg(horizontals, 2);
    	double HRA = TwoDHaar.computeAvrg(horizontals, 2);
    	double VAA = TwoDHaar.computeAbsAvrg(verticals, 2);
    	double VRA = TwoDHaar.computeAvrg(verticals, 2);
    	double DAA = TwoDHaar.computeAbsAvrg(diagonals, 2);
    	double DRA = TwoDHaar.computeAvrg(diagonals, 2);
    	
    	//System.out.println("HAA = " + HAA);
    	//System.out.println("VAA = " + VAA);
    	//System.out.println("DAA = " + DAA);
    	//horizontals = null;
    	//verticals = null;
    	//diagonals = null;
    	
    	if ( ( HAA >= haa_thresh ) && ( VAA < vaa_theta ) && ( DAA < daa_thresh ) ) {
    		return new HaarWaveletWinStats(EDGE_TYPE.HOR, HAA, HRA, VAA, VRA, DAA, DRA);
    	}
    	else if ( ( VAA >= vaa_theta ) && ( HAA < haa_thresh ) && ( DAA < daa_thresh ) ) {
    		return new HaarWaveletWinStats(EDGE_TYPE.VER, HAA, HRA, VAA, VRA, DAA, DRA);
    	}
    	else if ( ( (HAA >= haa_thresh) && (VAA >= vaa_theta) ) || ( DAA >= daa_thresh )) {
    		return new HaarWaveletWinStats(EDGE_TYPE.DIG, HAA, HRA, VAA, VRA, DAA, DRA);
    	}
    	else {
    		return new HaarWaveletWinStats(EDGE_TYPE.NONE, HAA, HRA, VAA, VRA, DAA, DRA);
    	}
    }
    
    // N = 2^{n}. N = 64, n = 5
    // win_size = 2^{n}
    // Test this!!!
    public static ArrayList<HaarWaveletWin> dbgDetectHaarWaveletWins(String img_path, Mat image, int window_size, int log2_window_size, 
    		double theta_HAA, double theta_VAA, double theta_DAA) {
    	final int num_rows = image.rows();
    	final int num_cols = image.cols();
    	System.out.println("num_rows = " + num_rows + " num_cols = " + num_cols);
    	ArrayList<HaarWaveletWin> haar_wavelet_windows = new ArrayList<HaarWaveletWin>();
    	//Rect roi = null;
    	for(int row = 0; row < num_rows - window_size; row += window_size) {
    		for(int col = 0; col < num_cols - window_size; col += window_size) {
    			//System.out.println("row = " + row + " col = " + col);
    			//roi = new Rect(row, col, window_size, window_size);
    			//System.out.print("roi.x + roi.width = " + (roi.x + roi.width));
    			//System.out.println(" m.cols = " + mat.cols());
    			//System.out.print("roi.y + roi.height = " + (roi.y + roi.height));
    			//System.out.println(" m.rows = " + mat.rows());
    			//Mat subImage = mat.submat(roi);
    			Mat subImage = image.submat(row, row+window_size, col, col+window_size);
    			double[][] pixvals = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(subImage);
    			//Mat subImage = new Mat(mat, roi);
    			if ( row == 384 && col == 512 ) {
    				System.out.println("DETECT EDGES");
    				System.out.println("SubMat at " + row + ", " + (row+window_size) + ", " + col + ", " + (col+window_size));
    				//TwoDHaar.displaySample(pixvals, window_size, 0);
    				//TwoDMatHaar.displayMat(subImage);
    				//TwoDMatHaarTests.displayROI(img_path, row, col, window_size, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
    			}
    			HaarWaveletWinStats estats = 
    					TwoDMatHaar.computeWaveletWinStats(pixvals, log2_window_size, 
    							theta_HAA, theta_VAA, theta_DAA);
    			if ( row == 384 && col == 512 )
    				TwoDMatHaarTests.testDetectEdgesInSubimageCmp(img_path, row, col, window_size, log2_window_size,
    								theta_HAA, theta_VAA, theta_DAA, pixvals, subImage);
    			if ( EDGE_TYPE.NONE != estats.mEdgeType ) {
    				int[] edge = new int[]{row, col, window_size};
    				haar_wavelet_windows.add(new HaarWaveletWin(edge, estats));
    			}
    		}
    	}
    	
    	Collections.sort(haar_wavelet_windows, HaarWaveletWin.HaarWavletWinColComparator);
    	
    	writeEdgeWinStatsIntoImage(image, haar_wavelet_windows);
    	Highgui.imwrite(img_path + "_" + window_size + "_DBG_Edges_.JPG", image);
    	return haar_wavelet_windows;
    }
    
    
    public static ArrayList<HaarWaveletWin> detectEdgeWins(String img_path, Mat image, int window_size, int log2_window_size, 
    		double haa_thresh, double vaa_thresh, double daa_thresh) {
    	final int num_rows = image.rows();
    	final int num_cols = image.cols();
    	//System.out.println("num_rows = " + num_rows + " num_cols = " + num_cols);
    	ArrayList<HaarWaveletWin> edge_windows = new ArrayList<HaarWaveletWin>();
    	for(int row = 0; row < num_rows - window_size; row += window_size) {
    		for(int col = 0; col < num_cols - window_size; col += window_size) {
    			Mat subImage = image.submat(row, row+window_size, col, col+window_size);
    			double[][] pixvals = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(subImage);
    			HaarWaveletWinStats estats = 
    					TwoDMatHaar.computeWaveletWinStats(pixvals, log2_window_size, 
    							haa_thresh, vaa_thresh, daa_thresh);

    			if ( EDGE_TYPE.NONE != estats.mEdgeType ) {
    				int[] edge = new int[]{row, col, window_size};
    				edge_windows.add(new HaarWaveletWin(edge, estats));
    			}
    		}
    	}
    	return edge_windows;
    }
    
    public static void writeEdgeWinStatsIntoImage(Mat image, ArrayList<HaarWaveletWin> edge_windows) {
		final Scalar sc = new Scalar(255, 0, 0);
		final Scalar color = new Scalar(255, 255, 255);
		HaarWaveletWinStats estats = null;
		Point top_left = null; Point top_right = null; Point bot_left = null; Point bot_right = null;
		DecimalFormat df = new DecimalFormat("#0.0");
		String edge_type = null;
    	for(HaarWaveletWin hew: edge_windows) {
    		int col = hew.getWinCol();
    		int row = hew.getWinRow();
    		int window_size = hew.getWinSize();
			top_left = new Point(col, row); 
			//Point top_right = new Point(col + roi.width - 1, row);
			top_right = new Point(col + window_size, row);
			//Point bot_left = new Point(col, row + roi.height -1);
			bot_left = new Point(col, row + window_size);
			//Point bot_right = new Point(roi.x + roi.width - 1, roi.y + roi.width -1);
			bot_right = new Point(col + window_size, row + window_size);
			Core.line(image, top_left, top_right, sc);
			Core.line(image, top_right, bot_right, sc);
			Core.line(image, bot_right, bot_left, sc);
			Core.line(image, bot_left, top_left, sc);
			estats = hew.getEdgeWinStats();
			final String haa = String.format(df.format(estats.mHAA) + "|" + String.format(df.format(estats.mHRA)));
	    	final String vaa = String.format(df.format(estats.mVAA) + "|" + String.format(df.format(estats.mVRA)));
	    	final String daa = String.format(df.format(estats.mDAA) + "|" + String.format(df.format(estats.mDRA)));
	    	edge_type = hew.getEdgeType().toString();
	    	Core.putText(image, edge_type, new Point(top_left.x, top_left.y+10), Core.FONT_HERSHEY_PLAIN, 0.7, color);
	    	Core.putText(image, ("r"+(int)top_left.y) + "," + "c"+((int)top_left.x), new Point(top_left.x, top_left.y+20), Core.FONT_HERSHEY_PLAIN, 0.7, color);
	    	Core.putText(image, haa, new Point(top_left.x, top_left.y + 30), Core.FONT_HERSHEY_PLAIN, 0.7, color);
	    	Core.putText(image, vaa, new Point(top_left.x, top_left.y + 40), Core.FONT_HERSHEY_PLAIN, 0.7, color);
	    	Core.putText(image, daa, new Point(top_left.x, top_left.y + 50), Core.FONT_HERSHEY_PLAIN, 0.7, color);
			
    	}
    }
    
    
}
