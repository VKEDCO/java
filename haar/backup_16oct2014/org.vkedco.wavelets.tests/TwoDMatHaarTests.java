package org.vkedco.wavelets.tests;

/***************************************************************
 * 
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

import java.util.ArrayList;
import java.util.HashSet;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.vkedco.opencv.haar.HaarBlur;
import org.vkedco.opencv.haar.HaarWaveletWin;
import org.vkedco.opencv.haar.HaarWaveletWinStats;
import org.vkedco.opencv.haar.TwoDHaarCluster;
import org.vkedco.opencv.haar.TwoDMatHaar;
import org.vkedco.wavelets.haar.TwoDHaar;

public class TwoDMatHaarTests {
	static String HOME = "";
	static final String IMG_PATH =  "/home/vladimir/programming/opencv/images/BlurImages/edges/";
	static final String IMG_PATH2 = "/home/vladimir/programming/opencv/images/BlurImages/";

	static {
		HOME = System.getProperty("user.home");
		System.load(HOME + "/opencv_2.4.4/bin/libopencv_java244.so");
	}
	
	public static void test_ordered_haar(Mat mat, int n, int num_steps_forward, int num_steps_backward) {
		double[][] pixels = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(mat);
		TwoDHaar.displaySample(pixels, pixels[0].length, 0);
		pixels = null;
		ArrayList<double[][]> transform = TwoDMatHaar.orderedFastHaarWaveletTransformForNumIters(mat, n, num_steps_forward);
		for(double[][] ary: transform) {
			int len = ary[0].length;
			System.out.println("dim == " + len);
			TwoDHaar.displaySample(ary, len, 0);
		}
	}
		
	public static void test_01(String img_name) {
		Mat mat = Highgui.imread(IMG_PATH + img_name);
		HaarBlur.estimateImageBlur(mat, 1.5, 0.001);
		System.out.println("BLUR_EXTENT = " + HaarBlur.getBlurExtent());
		System.out.println("IS BLURRED  = " + HaarBlur.getIsBlurred());
		//test_ordered_haar(mat, 4, 2, 2);
	}
	
	public static void test_ordered_image_haar(String img_name, int row, int col, int size, int num_steps_forward) {
		System.out.println("test_ordered_image_haar");
		Mat mat = Highgui.imread(IMG_PATH + img_name);
		System.out.println("mat.size = " + mat.rows() + ", " + mat.cols());
		double[][] pixels = TwoDMatHaar.get2DSquarePixArrayFromGrayscalMatAt(mat, row, col, size);
		System.out.println("test_ordered_image_haar check 01");
		System.out.println("pixels' num_rows = " + pixels.length);
		System.out.println("pixels' num_cols = " + pixels[0].length);
		
		TwoDHaar.displaySample(pixels, pixels[0].length, 0);
		int n = (int) (Math.log(pixels[0].length) / Math.log(2.0));
		ArrayList<double[][]> transform = TwoDHaar.orderedFastHaarWaveletTransformForNumIters(pixels, n, num_steps_forward);
		transform = null;
	}
	
	// win_size = N = 2^{log2_win_size}
	// E.g. win_size = 64, then log2_win_size = 5.
	final static double HAA_THRESH = 15.0;
	final static double VAA_THRESH = 15.0;
	final static double DAA_THRESH = 15.0;
	public static void test_detect_haar_wavelet_wins_and_clusters(String img_path, int win_size, int log2_win_size,
			double haa_thresh, double vaa_thresh, double daa_thresh) {
		//String img_path = IMG_PATH2 + img_name;
		// load a grayscale image
		Mat mat = Highgui.imread(img_path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		Mat dog_mat = mat.clone();
		System.out.println("mat.size = " + mat.rows() + ", " + mat.cols());
		ArrayList<HaarWaveletWin> haar_wavelet_wins = 
				TwoDMatHaar.dbgDetectHaarWaveletWins(img_path, mat, win_size, log2_win_size, 
						haa_thresh, vaa_thresh, daa_thresh);
		ArrayList<ArrayList<HaarWaveletWin>> clusters = 
				TwoDHaarCluster.findHaarWaveletWinClustersByGeometricProximity(mat, haar_wavelet_wins, win_size, log2_win_size);
		
		TwoDHaarCluster.markFoundClusters(img_path, mat, clusters, win_size, 5);
		TwoDHaarCluster.markDOGsForFoundWindows(img_path, dog_mat, haar_wavelet_wins, win_size, 0);
	}
	
	public static void testEdgeWin() {
		HaarWaveletWin ew01 = new HaarWaveletWin(new int[]{384, 384, 64}, null);
		HaarWaveletWin ew02 = new HaarWaveletWin(new int[]{320, 320, 64}, null);
		System.out.println(TwoDHaarCluster.areDigNeighbors(ew01, ew02));
	}
	
	// a cluster = HashSet<EdgeWin>
	public static void testClusters() {
		HashSet<HaarWaveletWin> clusters = new HashSet<HaarWaveletWin>();
		HaarWaveletWin ew01 = new HaarWaveletWin(new int[]{64, 192, 64}, null);
		HaarWaveletWin ew02 = new HaarWaveletWin(new int[]{0, 256, 64}, null);
		System.out.println(TwoDHaarCluster.areVerNeighbors(ew01, ew02));
		clusters.add(ew01);
		clusters.add(ew02);
		//clusters.remove(ew01);
		System.out.println(ew01.equals(ew01));
		System.out.println(clusters.size());
		HashSet<HaarWaveletWin> clusters02 = new HashSet<HaarWaveletWin>();
		clusters02.add(ew01);
		clusters02.add(ew02);
		clusters.addAll(clusters02);
		System.out.println(clusters.size());
	}
	
	public static void testNeighbors() {
		HaarWaveletWin ew01 = new HaarWaveletWin(new int[]{0, 256, 64}, null);
		HaarWaveletWin ew02 = new HaarWaveletWin(new int[]{64, 192, 64}, null);
		System.out.println(TwoDHaarCluster.areVerNeighbors(ew01, ew02));
		System.out.println(TwoDHaarCluster.areHorNeighbors(ew01, ew02));
		System.out.println(TwoDHaarCluster.areDigNeighbors(ew01, ew02));
	}
	
	public static void testDetectEdgesInSubimage(String img_path, int row, int col, int window_size,
			int log2_window_size, double haa_thresh, double vaa_thresh, double daa_thresh) {
		System.out.println("\n\ntestDetectEdgesInSubimage");
		//String img_path = IMG_PATH2 + img_name;
		Mat mat = Highgui.imread(img_path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		//Rect roi = new Rect(row, col, window_size, window_size);
		Mat subImage = mat.submat(row, row+window_size, col, col+window_size);
		//Mat subImage = new Mat(mat, roi);
		
		//displayROI(img_path, row, col, window_size, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		TwoDMatHaar.displayMat(subImage);
		double [][] pixvals = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(subImage);
		//TwoDHaar.displaySample(pixvals, window_size, 0);
		HaarWaveletWinStats estats = 
				TwoDMatHaar.computeWaveletWinStats(pixvals, log2_window_size, 
						haa_thresh, vaa_thresh, daa_thresh);
		System.out.println("HAA = " + estats.mHAA);
		System.out.println("VAA = " + estats.mVAA);
		System.out.println("DAA = " + estats.mDAA);
		
//		Scalar sc = new Scalar(255, 0, 0);
//		Point top_left = new Point(col, row); 
//		//Point top_right = new Point(col + roi.width - 1, row);
//		Point top_right = new Point(col + window_size, row);
//		//Point bot_left = new Point(col, row + roi.height -1);
//		Point bot_left = new Point(col, row + window_size);
//		//Point bot_right = new Point(roi.x + roi.width - 1, roi.y + roi.width -1);
//		Point bot_right = new Point(col + window_size, row + window_size);
//		Core.line(mat, top_left, top_right, sc);
//		Core.line(mat, top_right, bot_right, sc);
//		Core.line(mat, bot_right, bot_left, sc);
//		Core.line(mat, bot_left, top_left, sc);
		HaarWaveletWin hew = new HaarWaveletWin(new int[]{row, col, window_size}, estats);
		ArrayList<HaarWaveletWin> edge_wins = new ArrayList<>();
		edge_wins.add(hew);
		TwoDMatHaar.writeEdgeWinStatsIntoImage(mat, edge_wins);
		String img_path2 = img_path + "_TEST_EDGE_DETECTION.JPG";
		Highgui.imwrite(img_path2, mat);
		System.out.println("testDetecEdgesInSubimage Done...");
	}
	
	public static void testDetectEdgesInSubimageCmp(String img_path, int row, int col, int window_size,
			int log2_window_size, double haa_thresh, double vaa_thresh, double daa_thresh,
			double[][] pixvals2, 
			Mat pixValsSubImage) 
	{
		System.out.println("\n\ntestDetectEdgesInSubimage");
		System.out.println("img_path = " + img_path);
		//System.out.println("row = " + row + ", col = " + col + " win_size = " + window_size);
		System.out.println("Cmp SubMat at " + row + ", " + (row+window_size) + ", " + col + ", " + (col+window_size));
		//String img_path = IMG_PATH2 + img_name;
		// 1. Read the image as image_grayscale
		Mat mat = Highgui.imread(img_path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		//Rect roi = new Rect(row, col, window_size, window_size);
		// 2. Get the submat
		Mat subImage = mat.submat(row, row+window_size, col, col+window_size);
		//Mat subImage = new Mat(mat, roi);
		
		//displayROI(img_path, row, col, window_size, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		// 3. Display the subImage
		TwoDMatHaar.displayMat(subImage);
		// 4. Get the pixvals 
		double [][] pixvals = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(subImage);
		
		if ( pixvals.length != pixvals2.length ) {
			System.out.println("Error");
			return;
		}
		// 5.  Compare pixvals with pixvals2 value by value
		
		/**
		for(int r = 0; r < pixvals.length; r++) {
			for(int c = 0; c < pixvals[0].length; c++) {
				if ( pixvals[r][c] != pixvals2[r][c] ) {
					System.out.println("ERROR in pixvals at " + r + ", " + c);
					System.out.println("pixvals = " + pixvals[r][c]);
					System.out.println("pixvals2 = " + pixvals2[r][c]);
				}
			}
		}
		if ( pixValsSubImage == null ) {
			System.out.println("pixValsSubImage == null");
		}
		if ( subImage == null ) {
			System.out.println("subImage == null");
		}
		*/
		
		for(int r = 0; r < subImage.rows(); r++) {
			for(int c = 0; c < subImage.cols(); c++) {
				if ( subImage.get(r, c)[0] != pixValsSubImage.get(r, c)[0] ) {
					System.out.println("ERROR in submats at " + r + ", " + c);
				}
			}
		}
		//TwoDHaar.displaySample(pixvals, window_size, 0);
		HaarWaveletWinStats estats = 
				TwoDMatHaar.computeWaveletWinStats(pixvals, log2_window_size, 
						haa_thresh, vaa_thresh, daa_thresh);
		System.out.println("HAA = " + estats.mHAA);
		System.out.println("VAA = " + estats.mVAA);
		System.out.println("DAA = " + estats.mDAA);
		
//		Scalar sc = new Scalar(255, 0, 0);
//		Point top_left = new Point(col, row); 
//		//Point top_right = new Point(col + roi.width - 1, row);
//		Point top_right = new Point(col + window_size, row);
//		//Point bot_left = new Point(col, row + roi.height -1);
//		Point bot_left = new Point(col, row + window_size);
//		//Point bot_right = new Point(roi.x + roi.width - 1, roi.y + roi.width -1);
//		Point bot_right = new Point(col + window_size, row + window_size);
//		Core.line(mat, top_left, top_right, sc);
//		Core.line(mat, top_right, bot_right, sc);
//		Core.line(mat, bot_right, bot_left, sc);
//		Core.line(mat, bot_left, top_left, sc);
		HaarWaveletWin hew = new HaarWaveletWin(new int[]{row, col, window_size}, estats);
		ArrayList<HaarWaveletWin> edge_wins = new ArrayList<>();
		edge_wins.add(hew);
		TwoDMatHaar.writeEdgeWinStatsIntoImage(mat, edge_wins);
		String img_path2 = img_path + "_TEST_EDGE_DETECTION.JPG";
		Highgui.imwrite(img_path2, mat);
		System.out.println("testDetecEdgesInSubimage Done...");
		
	}
	
	static void getPixValAt(String img_name, int col, int row, int window_size, int flag) {
		String img_path = IMG_PATH2 + img_name;
		Mat mat = Highgui.imread(img_path, flag);
		System.out.println(mat.channels());
		//Rect roi = new Rect(col, row, window_size, window_size);
		//Mat subImage = mat.submat(roi);
		////System.out.println(subImage.cols() + ", " +  subImage.rows());
		
		System.out.println("(" + row + ", " + col + ") = " + mat.get(row, col)[0]);
		
		//System.out.println("(" + col + ", " + row + ") = " + subImage.get(col, row)[0]);
		
		/*
		for(int r = row; r < row + window_size; r++) {
			for(int c = col; c < col + window_size; c++) {
				System.out.print("(" + r + "," + c + ")" + "=" + subImage.get(row, col)[0] + " ");
				//System.out.println(r + ", " + c + " = " + subImage.get(row, col)[1]);
				//System.out.println(r + ", " + c + " = " + subImage.get(row, col)[2]);
			}
			System.out.println();
		}
		*/
	}
	
	public static void displayROI(String img_path, int row, int col, int window_size, int flag) {
		System.out.println("displayROI");
		//String img_path = IMG_PATH2 + img_name;
		Mat mat = Highgui.imread(img_path, flag);
		System.out.println(mat.channels());
		System.out.println(mat.rows());
		System.out.println(mat.cols());
		Rect roi = new Rect(row, col, window_size, window_size);
		//Mat subImage = mat.submat(roi);
		
		Mat subImage = mat.submat(row, row+window_size, col, col+window_size);

		System.out.println("num_channels = " 	+ subImage.channels());
		System.out.println("num_rows == " 		+ subImage.rows());
		System.out.println("num_cols == " 		+ subImage.cols());
		
		for(int r = 0; r < subImage.rows(); r++) {
			for(int c = 0; c < subImage.cols(); c++) {
				System.out.print("(" + (row + r) + "," + (col + c) + ")=" + subImage.get(r, c)[0] + " ");
			}
			System.out.println();
		}
		
		//System.out.println("(" + row + ", " + col + ") = " + mat.get(row, col)[0]);
		//System.out.println("(" + col + ", " + row + ") = " + subImage.get(col, row)[0]);
		
		/*
		for(int r = row; r < row + window_size; r++) {
			for(int c = col; c < col + window_size; c++) {
				System.out.print("(" + r + "," + c + ")" + "=" + subImage.get(row, col)[0] + " ");
				//System.out.println(r + ", " + c + " = " + subImage.get(row, col)[1]);
				//System.out.println(r + ", " + c + " = " + subImage.get(row, col)[2]);
			}
			System.out.println();
		}
		*/
	}
	
	static void testGet2DPixelArrayFromGrayscaleMat(String img_path, int row, int col, int win_size) {
		Mat mat = Highgui.imread(IMG_PATH2 + img_path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		//System.out.println("CHECK 01");
		//Mat dog_mat = mat.clone();
		//System.out.println("mat.size = " + mat.rows() + ", " + mat.cols());
		Mat subImage = mat.submat(row, row + win_size, col, col+win_size);
		//System.out.println("displaying mat image");
		//TwoDMatHaar.displayMat(subImage);
		//System.out.println("CHECK 02");
		double[][] pixvals = TwoDMatHaar.getMax2DPowOf2SquarePixArrayFromGrayscaleMat(subImage);
		//System.out.println("CHECK 03");
		//displayROI(IMG_PATH2 + img_path, row, col, win_size, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		//System.out.println(pixvals.length);
		//TwoDHaar.displaySample(pixvals, win_size, 0);
		double[][] pixvals2 = TwoDMatHaar.get2DSquarePixArrayFromGrayscalMatAt(mat, row, col, win_size);
		for(int r = 0; r < pixvals.length; r++) {
			for(int c = 0; c < pixvals[0].length; c++) {
				if ( pixvals[r][c] != pixvals2[r][c] ) {
					System.out.println("ERROR at " + r + ", " + c);
				}
			}
		}
		System.out.println("NO ERROR");
	}
	
	
	public static void main(String[] args) {
		//test_ordered_image_haar("blur_edge_0001.jpg", 0, 0, 32, 2);
		//test_ordered_image_haar("blur_edge_0006.jpg", 0, 10, 16, 3);
		//test_ordered_image_haar("non_blur_edge_0002.jpg", 4, 4, 16, 3);
		//test_ordered_image_haar("blur_edge_0001.jpg", 10, 10, 16, 3);
		//test_ordered_image_haar("non_blur_edge_0003.jpg", 5, 5, 16, 3);
		//test_ordered_image_haar("no_edge_0001.jpg", 0, 0, 16, 3);
		//test_ordered_image_haar("no_edge_0002.jpg", 0, 0, 16, 3);
		//test_ordered_image_haar("non_blur_edge_0004.jpg", 5, 5, 16, 3);
		//test_ordered_image_haar("non_blur_edge_0007.jpg", 5, 5, 16, 3);
		//test_ordered_image_haar("blur_edge_0002.jpg", 5, 5, 16, 3);
		//test_ordered_image_haar("blur_edge_0004.jpg", 0, 0, 16, 3);
		//test_ordered_image_haar("blur_edge_0008.jpg", 15, 15, 16, 3);
		//test_ordered_image_haar("blur_edge_0009.jpg", 10, 10, 16, 3);
		
		test_detect_haar_wavelet_wins_and_clusters(IMG_PATH2 + "00065.jpg", 64, 5, HAA_THRESH, VAA_THRESH, DAA_THRESH);
		
		
		//testNeighbors();
		
		//testEdgeWin();
		
		//testClusters();
		//test_edge_detection("00099.JPG.JPG", 32, 4);
		//test_edge_detection("00099.JPG.JPG", 128, 6);
		//test_ordered_image_haar("no_edge_0001.jpg", 0, 0, 16, 3);
		//test_01("Blur.jpg");
		//HaarBlur.displayImageStats();
		//HaarBlur.displayEmaxes();
		
		
	}

}
