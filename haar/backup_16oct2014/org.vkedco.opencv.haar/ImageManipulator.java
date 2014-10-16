package org.vkedco.opencv.haar;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.vkedco.wavelets.haar.TwoDHaar;

public class ImageManipulator {

	/**
	 * @param args
	 */

	static final Scalar COLOR_GREEN = new Scalar(0, 255, 0);
	static final Scalar COLOR_RED = new Scalar(0, 0, 255);
	static final Scalar COLOR_BLACK = new Scalar(0, 0, 0);
	static String HOME = "";
	static final String IMG_PATH = "/home/vladimir/programming/opencv/images/BlurImages/";

	static {
		HOME = System.getProperty("user.home");
		System.load(HOME + "/opencv_2.4.4/bin/libopencv_java244.so");
	}

	public static void main(String[] args) {
		
		int rectSize = 1;
		Mat mat = Highgui.imread(IMG_PATH + "img04.jpg");
		int centerX = 0;
		int centerY = 0;
		//TwoDMatHaar.displayMat2(mat);
		double[][] pixels = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(mat);
		TwoDHaar.displaySample(pixels, pixels[0].length, 0);
		//Mat checkMat = TwoDMatHaar.getMatFrom2DPixelArray(mat, pixels);
		//Highgui.imwrite(IMG_PATH + "img65_test.jpg", checkMat);
		//TwoDHaar.displaySample(pixels, pixels[0].length, 0);
		ArrayList<double[][]> haar_transform = TwoDMatHaar.orderedFastHaarWaveletTransformForNumIters(mat, 5, 4);
		TwoDHaar.displayOrderedHaarTransform(haar_transform);
			
		/*
		mat.convertTo(mat, CvType.CV_64FC3);
		System.out.println("Test");
		int size = (int) (mat.total() * mat.channels());
		double[] temp = new double[size];
		mat.get(0, 0, temp);
		int i = 0;
		for(int r = 0; r < mat.rows(); r++) {
			System.out.print("Row " + r + ": ");
			for(int c = 0; c < mat.rows(); c++) {
				System.out.print(temp[i++]+" ");
			}
			System.out.println();
		}
		*/
		
		
	}



}
