package org.vkedco.compvision.dog;

import java.util.ArrayList;
import java.util.HashSet;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class DOGCluster {
	
	public static final ArrayList<ArrayList<DOGWin>> findClustersByGeometricProximity(Mat img, ArrayList<DOGWin> dog_windows, int win_size, int log2_win_size) {
    	//System.out.println("findClusters: " + "N == " + N + " n== " + n);
    	ArrayList<ArrayList<DOGWin>> clusters = new ArrayList<ArrayList<DOGWin>>();
    	
    	for(DOGWin dog_win: dog_windows) {
    		//System.out.println("Looking for clusters for " + edge_win.toString());
    		DOGCluster.findClusterForDogWinByGeometricProximity(dog_win, clusters);
    		//System.out.println("Begin clusters so far **********");
    		DOGCluster.displayFoundClusters(clusters);
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
	
	static void findClusterDOGWinByGeometricProximity(DOGWin input_dog_win, ArrayList<ArrayList<DOGWin>> clusters) {
    	boolean cluster_found = false;
    	// an edge window can be placed into multiple clusters
    	for(ArrayList<DOGWin> cluster: clusters) {
    		if ( DOGCluster.isHomeClusterFor(input_dog_win, cluster) ) {
    			cluster.add(input_dog_win);
    			cluster_found = true;
    		}
    	}
    	
    	// if no cluster is found for an edge window, then the edge window becomes
    	// its own cluster
    	if ( cluster_found == false ) {
    		ArrayList<DOGWin> cluster = new ArrayList<DOGWin>();
    		cluster.add(input_dog_win);
    		clusters.add(cluster);
    	}
    }
	
	static boolean doClustersIntersect(ArrayList<DOGWin> cluster01, ArrayList<DOGWin> cluster02) {
    	for(DOGWin dw01 : cluster01) {
    		for (DOGWin dw02 : cluster02) {
    			if ( dw01.equals(dw02) )
    				return true;
    		}
    	}
    	return false;
    }
    
    static ArrayList<DOGWin> mergeClusters(ArrayList<DOGWin> cluster01, ArrayList<DOGWin> cluster02) {
    	HashSet<DOGWin> mergedClusters = new HashSet<DOGWin>();
    	mergedClusters.addAll(cluster01);
    	mergedClusters.addAll(cluster02);
    	ArrayList<DOGWin> mergedClusters01 = new ArrayList<DOGWin>();
    	for(DOGWin dw: mergedClusters)
    		mergedClusters01.add(dw);
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
    
    static boolean isHomeClusterFor(DOGWin input_dog_win, ArrayList<DOGWin> cluster) {
    	if ( cluster.isEmpty() ) return true;
    	
    	for(DOGWin cluster_dog_win: cluster) {
    		if ( input_dog_win.equals(cluster_dog_win) ) continue;
			System.out.println("DW01: " + input_dog_win.toString());
	    	System.out.println("DW02: " + cluster_dog_win.toString());
			if ( 	!DOGCluster.areHorNeighbors(input_dog_win, cluster_dog_win)
					||
					!DOGCluster.areVerNeighbors(input_dog_win, cluster_dog_win)
					||
					!DOGCluster.areDigNeighbors(input_dog_win, cluster_dog_win)
					) {
				continue;
			}
			else if ( cluster_dog_win.similarDOGValue(input_dog_win)) {
				return true;
			}
			
		}
    	return false;
    }
    
    static void findClusterForDogWinByGeometricProximity(DOGWin input_dog_win, ArrayList<ArrayList<DOGWin>> clusters) {
    	boolean cluster_found = false;
    	// an edge window can be placed into multiple clusters
    	for(ArrayList<DOGWin> cluster: clusters) {
    		if ( DOGCluster.isHomeClusterFor(input_dog_win, cluster) ) {
    			cluster.add(input_dog_win);
    			cluster_found = true;
    		}
    	}
    	
    	// if no cluster is found for an edge window, then the edge window becomes
    	// its own cluster
    	if ( cluster_found == false ) {
    		ArrayList<DOGWin> cluster = new ArrayList<DOGWin>();
    		cluster.add(input_dog_win);
    		clusters.add(cluster);
    	}
    }
      
//    static String dogWinToString(int[] dog_win) {
//    	return "EdgeWin: " + "r = " + DOGCluster.getDogWinRow(dog_win) +
//    			" c = " + TwoDHaarCluster.getEdgeWinCol(edge_win) +
//    			" N = " + TwoDHaarCluster.getEdgeWinSize(edge_win);
//    }
    
    static boolean areHorNeighbors(DOGWin dog_win_01, DOGWin dog_win_02) {
    	final int size_01 = dog_win_01.getWinSize();
    	final int size_02 = dog_win_02.getWinSize();
    	
    	final int top_left_r_01 = dog_win_01.getWinRow();
    	final int top_left_c_01 = dog_win_01.getWinCol();
    	
    	//final int top_right_r_01 = edge_win_01[0];
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01 = top_left_r_01 + size_01 - 1;
    	final int bot_left_c_01 = top_left_c_01;
    	
    	//final int bot_right_r_01 = edge_win_01[0] + N_01 - 1;
    	final int bot_right_c_01 = top_right_c_01;
    	
    	final int top_left_r_02 = dog_win_02.getWinRow();
    	final int top_left_c_02 = dog_win_02.getWinCol();
    	
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
    
    public static final boolean areVerNeighbors(DOGWin dog_win_01, DOGWin dog_win_02) {
    	final int size_01 = dog_win_01.getWinSize();
    	final int size_02 = dog_win_02.getWinSize();
    	
    	final int top_left_r_01 = dog_win_01.getWinRow();
    	final int top_left_c_01 = dog_win_01.getWinCol();
    	
    	final int top_right_r_01 = top_left_r_01;
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01  = top_left_r_01 + size_01 - 1;
    	//final int bot_left_c_01 = edge_win_01[1];
    	
    	final int bot_right_r_01 = bot_left_r_01;
    	//final int bot_right_c_01 = edge_win_01[0] + N_01 - 1;
    	
    	final int top_left_r_02 = dog_win_02.getWinRow();
    	final int top_left_c_02 = dog_win_02.getWinCol();
    	
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
    
    public static final boolean areDigNeighbors(DOGWin dog_win_01, DOGWin dog_win_02) {
    	final int size_01 = dog_win_01.getWinSize();
    	final int size_02 = dog_win_02.getWinSize();
    	
    	final int top_left_r_01  = dog_win_01.getWinRow();
    	final int top_left_c_01  = dog_win_01.getWinCol();
    	
    	final int top_right_r_01 = top_left_r_01;
    	final int top_right_c_01 = top_left_c_01 + size_01 - 1;
    	
    	final int bot_left_r_01  = top_left_r_01 + size_01 - 1;
    	final int bot_left_c_01  = top_left_c_01;
    	
    	final int bot_right_r_01 = bot_left_r_01;
    	final int bot_right_c_01 = top_right_c_01;
    	
    	final int top_left_r_02  = dog_win_02.getWinRow();
    	final int top_left_c_02  = dog_win_02.getWinCol();
    	
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
    
    
    /*
     	192 320 64 
		256 256 64 
		256 320 64 
		320 320 64 
		384 384 64 
     */
    public static final void markFoundClusters(String img_path, Mat mat, ArrayList<ArrayList<DOGWin>> clusters, int N, int cluster_size) {
    	Scalar sc = new Scalar(255, 255, 255);
    	for(ArrayList<DOGWin> cluster: clusters) {
    		if ( cluster.size() < cluster_size ) continue;
    		System.out.println("Cluser Size == " + cluster.size());
    		DOGCluster.displayCluster(cluster);
    		int min_x = cluster.get(0).getWinCol(); 
    		int min_y = cluster.get(0).getWinRow();
    		int max_x = min_x; int max_y = min_y;
    		for(DOGWin dog_win: cluster) {
    			int dog_win_col = dog_win.getWinCol();
    			int edge_win_row = dog_win.getWinRow();
    			if ( dog_win_col < min_x ) {
    				min_x = dog_win_col;
    			}
    			
    			if ( dog_win_col > max_x ) {
    				max_x = dog_win_col;
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
    
    public static final void displayFoundClusters(ArrayList<ArrayList<DOGWin>> clusters) {
    	for(ArrayList<DOGWin> cluster: clusters) {
    		System.out.println("===========");
    		DOGCluster.displayCluster(cluster);
    		System.out.println("===========");
    	}
    }
    
    static void displayCluster(ArrayList<DOGWin> cluster) {
    	for(DOGWin dog_win: cluster) {
    		System.out.print("" + dog_win.toString() + " ");
			System.out.println();
		}
    }
	

}
