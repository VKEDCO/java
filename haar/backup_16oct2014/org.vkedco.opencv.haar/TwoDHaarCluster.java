package org.vkedco.opencv.haar;

import java.util.ArrayList;
import java.util.HashSet;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.vkedco.compvision.dog.DOGConstants;
import org.vkedco.compvision.dog.Gradients;
import org.vkedco.compvision.dog.RGOT;

public class TwoDHaarCluster {
	
	// VK: 21sept2014
    // a cluster is an array list of HaarEdgeWins (HaarEdgeWin.java).
    // win_size is the size of the 2D subimage where the 2D haar filter is applied. win_size must be equal to an integral power of 2.
    // For example, if win_size = 64, then log2_win_size = log2(64) = 5.
    // This method finds clusters by the geometric proximity of HaarEdgeWins. A HaarEdgeWin belongs to a cluster
    // If a HaarEdgeWin does not belong to any cluster, it forms a singleton cluster by itself.
    public static final ArrayList<ArrayList<HaarWaveletWin>> findHaarWaveletWinClustersByGeometricProximity(Mat img, ArrayList<HaarWaveletWin> haar_wavelet_windows, int win_size, int log2_win_size) {
    	//System.out.println("findClusters: " + "N == " + N + " n== " + n);
    	ArrayList<ArrayList<HaarWaveletWin>> clusters = new ArrayList<ArrayList<HaarWaveletWin>>();
    	
    	for(HaarWaveletWin haar_wavelet_win: haar_wavelet_windows) {
    		//System.out.println("Looking for clusters for " + edge_win.toString());
    		TwoDHaarCluster.findClusterForHaarWaveletWinByGeometricProximity(haar_wavelet_win, clusters);
    		//System.out.println("Begin clusters so far **********");
    		//TwoDHaarCluster.displayFoundClusters(clusters);
    		//System.out.println("End clusters so far   **********");
    	}
    	
    	/*
    	// the block of code below merges found clusters.
    	// keep merging clusters
    	// pop a cluster: find all clusters that intersect with it, pop them, merge them with the found cluster.
    	ArrayList<EdgeWin> curr_cluster = null;
    	LinkedList<ArrayList<EdgeWin>> q = new LinkedList<ArrayList<EdgeWin>>();
    	q.addAll(clusters);
    	System.out.println("clusters' size = " + clusters.size());
    	ArrayList<ArrayList<EdgeWin>> merged_clusters = new ArrayList<ArrayList<EdgeWin>>();
    	while ( !q.isEmpty() ) {
    		curr_cluster = q.pop();
    		System.out.println("CURR CLUSTER");
    		TwoDMatHaar.displayCluster(curr_cluster);
    		for(ArrayList<EdgeWin> qcluster: q) {
    			if ( TwoDMatHaar.doClustersIntersect(curr_cluster, qcluster) ) {
    				System.out.println("Intersection Found");
    				TwoDMatHaar.displayCluster(qcluster);
    				curr_cluster = TwoDMatHaar.mergeClusters(curr_cluster, qcluster);
    			}
    		}
    		merged_clusters.add(curr_cluster);
    	}
    	
    	return merged_clusters;
    	*/
    	
    	return clusters;
    }
    
    static boolean doClustersIntersect(ArrayList<HaarWaveletWin> cluster01, ArrayList<HaarWaveletWin> cluster02) {
    	for(HaarWaveletWin ew01 : cluster01) {
    		for (HaarWaveletWin ew02 : cluster02) {
    			if ( ew01.equals(ew02) )
    				return true;
    		}
    	}
    	return false;
    }
    
    static ArrayList<HaarWaveletWin> mergeClusters(ArrayList<HaarWaveletWin> cluster01, ArrayList<HaarWaveletWin> cluster02) {
    	HashSet<HaarWaveletWin> mergedClusters = new HashSet<HaarWaveletWin>();
    	mergedClusters.addAll(cluster01);
    	mergedClusters.addAll(cluster02);
    	ArrayList<HaarWaveletWin> mergedClusters01 = new ArrayList<HaarWaveletWin>();
    	for(HaarWaveletWin ew: mergedClusters)
    		mergedClusters01.add(ew);
    	mergedClusters = null;
    	return mergedClusters01;
    }
    
    
    
    static int getEdgeWinRow(int[] edge_win) {
    	return edge_win[0];
    }
    
    static int getEdgeWinCol(int[] edge_win) {
    	return edge_win[1];
    }
    
    static int getEdgeWinSize(int[] edge_win) {
    	return edge_win[2];
    }
    
    static boolean isHomeClusterFor(HaarWaveletWin input_edge_win, ArrayList<HaarWaveletWin> cluster) {
    	if ( cluster.isEmpty() ) return true;
    	
    	for(HaarWaveletWin cluster_edge_win: cluster) {
    		if ( input_edge_win.equals(cluster_edge_win) ) continue;
			System.out.println("EW01: " + input_edge_win.toString());
	    	System.out.println("EW02: " + cluster_edge_win.toString());
			if ( TwoDHaarCluster.areHorNeighbors(input_edge_win, cluster_edge_win) ) {
				//cluster.add(input_edge_win);
				return true;
			}
			else if ( TwoDHaarCluster.areVerNeighbors(input_edge_win, cluster_edge_win) ) {
				//cluster.add(input_edge_win);
				return true;
			}
			else if ( TwoDHaarCluster.areDigNeighbors(input_edge_win, cluster_edge_win) ) {
				//cluster.add(input_edge_win);
				return true;
			}
		}
    	return false;
    }
    
    static void findClusterForHaarWaveletWinByGeometricProximity(HaarWaveletWin input_edge_win, ArrayList<ArrayList<HaarWaveletWin>> clusters) {
    	boolean cluster_found = false;
    	// an edge window can be placed into multiple clusters
    	for(ArrayList<HaarWaveletWin> cluster: clusters) {
    		if ( TwoDHaarCluster.isHomeClusterFor(input_edge_win, cluster) ) {
    			cluster.add(input_edge_win);
    			cluster_found = true;
    		}
    	}
    	
    	// if no cluster is found for an edge window, then the edge window becomes
    	// its own cluster
    	if ( cluster_found == false ) {
    		ArrayList<HaarWaveletWin> cluster = new ArrayList<HaarWaveletWin>();
    		cluster.add(input_edge_win);
    		clusters.add(cluster);
    	}
    }
    
    static String edgeWinToString(int[] edge_win) {
    	return "EdgeWin: " + "r = " + TwoDHaarCluster.getEdgeWinRow(edge_win) +
    			" c = " + TwoDHaarCluster.getEdgeWinCol(edge_win) +
    			" N = " + TwoDHaarCluster.getEdgeWinSize(edge_win);
    }
    
    public static boolean areHorNeighbors(HaarWaveletWin edge_win_01, HaarWaveletWin edge_win_02) {
    	final int size_01 = edge_win_01.getWinSize();
    	final int size_02 = edge_win_02.getWinSize();
    	
    	final int top_left_r_01 = edge_win_01.getWinRow();
    	final int top_left_c_01 = edge_win_01.getWinCol();
    	
    	//final int top_right_r_01 = edge_win_01[0];
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01 = top_left_r_01 + size_01 - 1;
    	final int bot_left_c_01 = top_left_c_01;
    	
    	//final int bot_right_r_01 = edge_win_01[0] + N_01 - 1;
    	final int bot_right_c_01 = top_right_c_01;
    	
    	final int top_left_r_02 = edge_win_02.getWinRow();
    	final int top_left_c_02 = edge_win_02.getWinCol();
    	
    	//final int top_right_r_02 = edge_win_02[0];
    	final int top_right_c_02 = top_left_c_02 + size_02 - 1;
    	
    	final int bot_left_r_02 = top_left_r_02 + size_02 - 1;
    	final int bot_left_c_02 = top_left_c_02;
    	
    	//final int bot_right_r_02 = edge_win_02[0] + N_02 - 1;
    	final int bot_right_c_02 = top_right_c_02;
    	
    	boolean rslt = false;
    	if ( top_left_r_01 == top_left_r_02 ) {
    		rslt = Math.abs(top_right_c_01 - top_left_c_02)  == 1 ||
    				Math.abs(top_left_c_01 - top_right_c_02) == 1;
    	}
    	if ( bot_left_r_01 == bot_left_r_02 ) {
    		rslt = Math.abs(bot_left_c_01 - bot_right_c_02)  == 1 ||
    				Math.abs(bot_left_c_02 - bot_right_c_01) == 1;
    	}
    	
    	System.out.println("AreHorNeighbors? == " + rslt);
    	
    	return rslt;
    }
    
    public static final boolean areVerNeighbors(HaarWaveletWin edge_win_01, HaarWaveletWin edge_win_02) {
    	final int size_01 = edge_win_01.getWinSize();
    	final int size_02 = edge_win_02.getWinSize();
    	
    	final int top_left_r_01 = edge_win_01.getWinRow();
    	final int top_left_c_01 = edge_win_01.getWinCol();
    	
    	final int top_right_r_01 = top_left_r_01;
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01  = top_left_r_01 + size_01 - 1;
    	//final int bot_left_c_01 = edge_win_01[1];
    	
    	final int bot_right_r_01 = bot_left_r_01;
    	//final int bot_right_c_01 = edge_win_01[0] + N_01 - 1;
    	
    	final int top_left_r_02 = edge_win_02.getWinRow();
    	final int top_left_c_02 = edge_win_02.getWinCol();
    	
    	final int top_right_r_02 = top_left_r_02;
    	final int top_right_c_02 = top_left_c_02 + size_02 - 1;
    	
    	final int bot_left_r_02  = top_left_r_02 + size_02 - 1;
    	//final int bot_left_c_02 = edge_win_02[1];
    	
    	final int bot_right_r_02 = bot_left_r_02;
    	//final int bot_right_c_02 = edge_win_02[0] + N_02 - 1;
    	
    	boolean rslt = false;
    	if ( top_left_c_01 == top_left_c_02 ) {
    		rslt = Math.abs(top_left_r_02 - bot_left_r_01)  == 1 ||
    				Math.abs(top_left_r_01 - bot_left_r_02) == 1;
    	}
    	if ( top_right_c_01 == top_right_c_02 ) {
    		rslt = Math.abs(bot_right_r_01 - top_right_r_02)  == 1 ||
    				Math.abs(top_right_r_01 - bot_right_r_02) == 1;
    	}
 
    	System.out.println("AreVerNeighbors? == " + rslt);
    	
    	return rslt;
    }
    
    public static final boolean areDigNeighbors(HaarWaveletWin edge_win_01, HaarWaveletWin edge_win_02) {
    	final int size_01 = edge_win_01.getWinSize();
    	final int size_02 = edge_win_02.getWinSize();
    	
    	final int top_left_r_01  = edge_win_01.getWinRow();
    	final int top_left_c_01  = edge_win_01.getWinCol();
    	
    	final int top_right_r_01 = top_left_r_01;
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01  = top_left_r_01 + size_01 - 1;
    	final int bot_left_c_01  = top_left_c_01;
    	
    	final int bot_right_r_01 = bot_left_r_01;
    	final int bot_right_c_01 = top_right_c_01;
    	
    	final int top_left_r_02  = edge_win_02.getWinRow();
    	final int top_left_c_02  = edge_win_02.getWinCol();
    	
    	final int top_right_r_02 = top_left_r_02;
    	final int top_right_c_02 = top_left_c_02 + size_02 - 1;
    	
    	final int bot_left_r_02  = top_left_r_02 + size_02 - 1;
    	final int bot_left_c_02  = top_left_c_02;
    	
    	final int bot_right_r_02 = bot_left_r_02;
    	final int bot_right_c_02 = top_right_c_02;
    	
    	System.out.println("top_left_01  = " + top_left_r_01 + "," + top_left_c_01);
    	System.out.println("top_right_01 = " + top_right_r_01 + "," + top_right_c_01);
    	System.out.println("bot_left_01  = " + bot_left_r_01 + "," + bot_left_c_01);
    	System.out.println("bot_right_01 = " + bot_right_r_01 + "," + bot_right_c_01);
    	System.out.println("top_left_02  = " + top_left_r_02 + "," + top_left_c_02);
    	System.out.println("top_right_02 = " + top_right_r_02 + "," + top_right_c_02);
    	System.out.println("bot_left_02  = " + bot_left_r_02 + "," + bot_left_c_02);
    	System.out.println("bot_right_02 = " + bot_right_r_02 + "," + bot_right_c_02);
    	
    	
    	boolean rslt = false;
    	// case 01
    	if ( top_left_c_01 == bot_right_c_02  && top_left_r_01 == bot_right_r_02 ) {
    		rslt = true;
    	}
    	// case 02
    	else if ( top_right_c_01 + 1 == bot_left_c_02 && top_right_r_01 == bot_left_r_02 - 1 ) {
    		rslt = true;
    	}
    	// case 03
    	else if ( bot_right_r_01 + 1 == top_left_r_02 && bot_right_c_01 == top_left_c_02 - 1) {
    		rslt = true;
    	}
    	// case 04
    	else if ( bot_left_c_01 == top_right_c_02 + 1 && bot_right_r_01 + 1 == top_right_r_02 ) {
    		rslt = true;
    	}
    	// case 05
    	else if ( top_left_c_02 == bot_right_c_01 + 1 && top_left_r_02 == bot_right_r_01 + 1 ) {
    		rslt = true;
    	}
    	// case 06
    	else if ( top_right_c_02 + 1 == bot_left_c_01 && top_right_r_02 == bot_left_r_01 + 1 ) {
    		rslt = true;
    	}
    	// case 07
    	else if ( bot_right_r_02 + 1 == top_left_r_01 && bot_right_c_02 + 1 == top_left_c_01 ) {
    		rslt = true;
    	}
    	// case 08
    	else if ( bot_left_c_02 == top_right_c_01 + 1 && bot_right_r_02 + 1 == top_right_r_01 ) {
    		rslt = true;
    	}
    	else {
    		rslt = false;
    		//return false;
    	}
    	System.out.println("areDigNeighbors? == " + rslt);
    	return rslt;
    }
    
    public static final void displayFoundClusters(ArrayList<ArrayList<HaarWaveletWin>> clusters) {
    	for(ArrayList<HaarWaveletWin> cluster: clusters) {
    		System.out.println("===========");
    		displayCluster(cluster);
    		System.out.println("===========");
    	}
    }
    
    static void displayCluster(ArrayList<HaarWaveletWin> cluster) {
    	for(HaarWaveletWin edge_win: cluster) {
    		System.out.print("" + edge_win.toString() + " ");
			System.out.println();
		}
    }
    
    /*
     	192 320 64 
		256 256 64 
		256 320 64 
		320 320 64 
		384 384 64 
     */
    public static final void markFoundClusters(String img_path, Mat mat, ArrayList<ArrayList<HaarWaveletWin>> clusters, int N, int cluster_size) {
    	Scalar sc = new Scalar(255, 255, 255);
    	for(ArrayList<HaarWaveletWin> cluster: clusters) {
    		if ( cluster.size() < cluster_size ) continue;
    		System.out.println("Cluser Size == " + cluster.size());
    		displayCluster(cluster);
    		int min_x = cluster.get(0).getWinCol(); 
    		int min_y = cluster.get(0).getWinRow();
    		int max_x = min_x; int max_y = min_y;
    		for(HaarWaveletWin edge_win: cluster) {
    			int edge_win_col = edge_win.getWinCol();
    			int edge_win_row = edge_win.getWinRow();
    			if ( edge_win_col < min_x ) {
    				min_x = edge_win_col;
    			}
    			
    			if ( edge_win_col > max_x ) {
    				max_x = edge_win_col;
    			}
    			
    			if ( edge_win_row < min_y ) {
    				min_y = edge_win_row;
    			}
    			
    			if ( edge_win_row > max_y ) {
    				max_y = edge_win_row;
    			}
    		}
    		System.out.println("min_x == " + min_x + " " + "min_y == " + min_y);
    		System.out.println("max_x == " + max_x + " " + "max_y == " + max_y);
    		Point top_left  = new Point(min_x, min_y);
    		Point top_right = new Point(max_x + N - 1, min_y);
    		Point bot_left  = new Point(min_x, max_y + N - 1);
    		Point bot_right = new Point(max_x + N - 1, max_y + N - 1);
    		System.out.println("marking " + top_left.toString() + " " + top_right.toString());
    		Core.line(mat, top_left, top_right, sc);
    		System.out.println("marking " + top_right.toString() + " " + bot_right.toString());
    		Core.line(mat, top_right, bot_right, sc);
    		System.out.println("marking " + bot_right.toString() + " " + bot_left.toString());
    		Core.line(mat, bot_right, bot_left, sc);
    		System.out.println("marking " + bot_left.toString() + " " + top_left.toString());
    		Core.line(mat, bot_left, top_left, sc);
    		
    	}
    	Highgui.imwrite(img_path + "_" + N + "_Clusters_.JPG", mat);
    }
    
    public static final void markDOGsForFoundWindows(String img_path, Mat mat, ArrayList<HaarWaveletWin> haar_edge_windows, 
    		int window_size, int cluster_size) {
    	System.out.println("MARK DOGS FOR FOUND CLUSTERS");
    	Scalar COLOR_WHITE = new Scalar(255, 255, 255);
    	Mat dog_mat = mat.clone();
    	Mat temp_mat = null;
    		HashSet<String> markedRGOTs = new HashSet<String>();
    		for(HaarWaveletWin edge_win: haar_edge_windows) {
    			int edge_win_col = edge_win.getWinCol(); // x
    			int edge_win_row = edge_win.getWinRow(); // y
    			String hash_set_val = "" + edge_win_col + " " + edge_win_row;
    			if ( markedRGOTs.contains(hash_set_val) ) continue;
    			markedRGOTs.add(hash_set_val);
    			Rect mask = new Rect(edge_win_col, edge_win_row, window_size, window_size);
    			temp_mat = new Mat(dog_mat, mask);
    			RGOT rgot = Gradients
    							.getMostFrequentRGOT(
    									Gradients
    										.computeRGOTTableForMat(temp_mat, 
    												DOGConstants.THETA_THRESH, 
    												DOGConstants.MAGN_THRESH));
    			Point top_left  = new Point(edge_win_col, edge_win_row);
    			Point top_right = new Point(edge_win_col + window_size, edge_win_row);
    			Point bot_left  = new Point(edge_win_col, edge_win_row + window_size);
    			Point bot_right = new Point(edge_win_col + window_size, edge_win_row + window_size);
        		System.out.println("marking dog " + top_left.toString() + " " + bot_right.toString());
        		Core.line(dog_mat, top_left, top_right, COLOR_WHITE);
        		//System.out.println("marking dog " + top_right.toString() + " " + bot_right.toString());
        		Core.line(dog_mat, top_right, bot_right, COLOR_WHITE);
        		//System.out.println("marking dog " + bot_right.toString() + " " + bot_left.toString());
        		Core.line(dog_mat, bot_right, bot_left, COLOR_WHITE);
        		//System.out.println("marking dog " + bot_left.toString() + " " + top_left.toString());
        		Core.line(dog_mat, bot_left, top_left, COLOR_WHITE);
				Core.putText(dog_mat, "" + 
							rgot.getTheta(),
						new org.opencv.core.Point(top_left.x, top_left.y + 20),
						Core.FONT_HERSHEY_COMPLEX_SMALL, 0.5, COLOR_WHITE);
				Core.putText(dog_mat, "" + rgot.getFreq(),
						new org.opencv.core.Point(top_left.x, top_left.y
								+ (window_size / 2)),
						Core.FONT_HERSHEY_COMPLEX_SMALL, 0.5, COLOR_WHITE);
    		}
    	
    	Highgui.imwrite(img_path + "_" + window_size + "_DOGsForEdgeWins.JPG", dog_mat);
    }
}
