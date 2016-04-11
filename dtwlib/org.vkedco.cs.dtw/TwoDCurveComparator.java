package org.vkedco.cs.dtw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author vladimir kulyukin
 */
public class TwoDCurveComparator {
    
    public static double compareTwoDCurve(double[] curve_one, double[] curve_two, IDTWSimilarity isim, IWarpingWindow iww) {
        return DTW.dpDTW2ColDoubleCost(curve_one, curve_two, isim, iww);
    }
    
    public static double compareTwoDCurve(String curveFilePathOne, String curveFilePathTwo, IDTWSimilarity isim, IWarpingWindow iww) {
        double[] curve_one = TwoDCurveComparator.fileToPrimitiveDoubleArray(curveFilePathOne);
        double[] curve_two = TwoDCurveComparator.fileToPrimitiveDoubleArray(curveFilePathTwo);
        final double sim = DTW.dpDTW2ColDoubleCost(curve_two, curve_two, isim, iww);
        curve_one = null;
        curve_two = null;
        return sim;
    }
    
    public static double euclidDistance(double[] n_dim_point_one, double[] n_dim_point_two) {
        if ( n_dim_point_one.length != n_dim_point_two.length )
            return Double.MAX_VALUE;
        else {
            double dist = 0;
            for(int i = 0; i < n_dim_point_one.length; i++) {
                dist += Math.pow(Math.abs(n_dim_point_one[i] - n_dim_point_two[i]), 2.0);
            }
            return Math.sqrt(dist);
        }
    }
    
    public static double manhattanDistance(double[] curve_one, double[] curve_two) {
        if ( curve_one.length != curve_two.length )
            return Double.MAX_VALUE;
        else {
            double dist = 0;
            for(int i = 0; i < curve_one.length; i++) {
                dist += Math.abs(curve_one[i] - curve_two[i]);
            }
            return dist;
        }
    }
    
    static double vectorMagn(double[] v) {
        double magn = 0;
        for(double x: v) {
            magn += x*x;
        }
        return Math.sqrt(magn);
    }
    
    static double dotProduct(double[] v1, double[] v2) {
        if ( v1.length != v2.length) {
            throw new IllegalArgumentException("input vectors are of the same length");
        }
        double dp = 0;
        for(int i = 0; i < v1.length; i++) {
            dp += v1[i] * v2[i];
        }
        return dp;
    }
    
    public static double cosineSimilarity(double[] v1, double[] v2) {
        return dotProduct(v1, v2)/(vectorMagn(v1) * vectorMagn(v2));
    }
    
    static void test_01() {
        double[] seq1 = {1, 2, 3};
        double sim = TwoDCurveComparator.compareTwoDCurve(seq1, seq1, new AbsModSim(), new DefaultWarpingWindow(3));
        System.out.println("sim == " + sim);
    }

    static void test_02() {
        double[] seq1 = {1, 2, 3};
        double[] seq2 = {1, 3, 3};
        double sim = TwoDCurveComparator.compareTwoDCurve(seq1, seq2, new AbsModSim(), new DefaultWarpingWindow(3));
        System.out.println("sim == " + sim);
    }

    static void test_03() {
        double[] seq1 = {1, 2, 3};
        double[] seq2 = {2, 3, 4};
        double sim = TwoDCurveComparator.compareTwoDCurve(seq2, seq1, new AbsModSim(), new DefaultWarpingWindow(3));
        System.out.println("sim == " + sim);
    }
    
    static double[] fileToPrimitiveDoubleArray(String path)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path) );
            String strLine;
            List<Double> doubles = new ArrayList<Double>();
            
            while ( ( strLine = br.readLine() ) != null ) {
                doubles.add(Double.valueOf(strLine));
            }
            double[] prim_doubles = new double[doubles.size()];
            int i = 0;
            for(Double db: doubles) {
                prim_doubles[i++] = db.doubleValue();
            }
            return prim_doubles;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TwoDCurveComparator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TwoDCurveComparator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    static void test_ex_4_4_p34_s8() {
        double[] ex_4_4_512_no_noise_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_no_noise_p34.txt");
        double[] ex_4_4_512_with_noise_cdf44_s8_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_cdf44_s8_p34.txt");
        double[] ex_4_4_512_with_noise_hwt_s8_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_hwt_s8_p34.txt");
        
        double cdf44_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s8_p34, 
                new AbsModSim(), DTW.MaxWW);
        double hwt_sim = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s8_p34, 
                new AbsModSim(), DTW.MaxWW);
        double cdf44_hwt_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s8_p34, ex_4_4_512_with_noise_hwt_s8_p34, 
                new AbsModSim(), DTW.MaxWW);
        
        double cdf44_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s8_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s8_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double cdf44_hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s8_p34, ex_4_4_512_with_noise_hwt_s8_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        
        double eucd_cdf44 = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s8_p34);
        double eucd_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        double eucd_cdf44_hwt = TwoDCurveComparator.euclidDistance(ex_4_4_512_with_noise_cdf44_s8_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        
        double mand_cdf44 = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s8_p34);
        double mand_hwt   = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        double mand_cdf44_hwt = TwoDCurveComparator.manhattanDistance(ex_4_4_512_with_noise_cdf44_s8_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        
        double cosi_cdf44 = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s8_p34);
        double cosi_hwt   = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        double cosi_cdf44_hwt = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_with_noise_cdf44_s8_p34, ex_4_4_512_with_noise_hwt_s8_p34);
        
        System.out.println("cdf44_s8_dtw         == " + cdf44_dtw);
        System.out.println("hwt_s8_dtw           == " + hwt_sim);
        System.out.println("cdf44_hwt_s8_dwt     == " + cdf44_hwt_dtw);
        System.out.println();
        
        System.out.println("cdf44_s8_dtw_se      == " + cdf44_dtw_se);
        System.out.println("hwt_s8_dtw_se        == " + hwt_dtw_se);
        System.out.println("cdf44_hwt_s8_dwt_se  == " + cdf44_hwt_dtw_se);
        System.out.println();
        
        System.out.println("eucd_cdf44_s8        == " + eucd_cdf44);
        System.out.println("eucd_hwt_s8          == " + eucd_hwt);
        System.out.println("eucd_cdf44_hwt_s8    == " + eucd_cdf44_hwt);
        System.out.println();
        
        System.out.println("mand_cdf44_s8        == " + mand_cdf44);
        System.out.println("mand_hwt_s8          == " + mand_hwt);
        System.out.println("mand_cdf44_hwt_s8    == " + mand_cdf44_hwt);
        System.out.println();
        
        System.out.println("cosi_cdf44_s8        == " + cosi_cdf44);
        System.out.println("cosi_hwt_s8          == " + cosi_hwt);
        System.out.println("cosi_cdf44_hwt       == " + cosi_cdf44_hwt);

        System.out.println("=============");
        System.out.println();
        System.out.println();

    }
    
    static void test_ex_4_4_p34_s7() {
        double[] ex_4_4_512_no_noise_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_no_noise_p34.txt");
        double[] ex_4_4_512_with_noise_cdf44_s7_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_cdf44_s7_p34.txt");
        double[] ex_4_4_512_with_noise_hwt_s7_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_hwt_s7_p34.txt");
        
        double cdf44_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s7_p34, 
                new AbsModSim(), DTW.MaxWW);
        double hwt_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s7_p34, 
                new AbsModSim(), DTW.MaxWW);
        double cdf44_hwt_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s7_p34, ex_4_4_512_with_noise_hwt_s7_p34, 
                new AbsModSim(), DTW.MaxWW);
        
        double cdf44_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s7_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s7_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double cdf44_hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s7_p34, ex_4_4_512_with_noise_hwt_s7_p34,
                new SquaredEuclideanSim(), DTW.MaxWW);
        
        double euclidDist_cdf44 = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s7_p34);
        double euclidDist_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        double eucd_cdf44_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_with_noise_cdf44_s7_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        
        double mand_cdf44       = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s7_p34);
        double mand_hwt         = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        double mand_cdf44_hwt   = TwoDCurveComparator.manhattanDistance(ex_4_4_512_with_noise_cdf44_s7_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        
        double cosi_cdf44     = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s7_p34);
        double cosi_hwt       = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        double cosi_cdf44_hwt = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_with_noise_cdf44_s7_p34, ex_4_4_512_with_noise_hwt_s7_p34);
        
        System.out.println("cdf44_s7_sim        == " + cdf44_dtw);
        System.out.println("hwt_s7_sim          == " + hwt_dtw);
        System.out.println("cdf44_hwt_s7_sim    == " + cdf44_hwt_dtw);
        System.out.println();
        
        System.out.println("cdf44_s7_dtw_se      == " + cdf44_dtw_se);
        System.out.println("hwt_s7_dtw_se        == " + hwt_dtw_se);
        System.out.println("cdf44_hwt_s7_dwt_se  == " + cdf44_hwt_dtw_se);
        System.out.println();
        
        System.out.println("euclidDist_cdf44_s7 == " + euclidDist_cdf44);
        System.out.println("euclidDist_hwt_s7   == " + euclidDist_hwt);
        System.out.println("eucd_cdf44_hwt_s7   == " + eucd_cdf44_hwt);
        System.out.println();
        
        System.out.println("mand_cdf44_s7       == " + mand_cdf44);
        System.out.println("mand_hwt_s7         == " + mand_hwt);
        System.out.println("mand_cdf44_hwt_s7   == " + mand_cdf44_hwt);
        System.out.println();
        
        System.out.println("cosi_cdf44_s7       == " + cosi_cdf44);
        System.out.println("cosi_hwt_s7         == " + cosi_hwt);
        System.out.println("cosi_cdf44_hwt      == " + cosi_cdf44_hwt);
 
        System.out.println("=============");
        System.out.println();
        System.out.println();
    }
    
    static void test_ex_4_4_p34_s6() {
        double[] ex_4_4_512_no_noise_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_no_noise_p34.txt");
        double[] ex_4_4_512_with_noise_cdf44_s6_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_cdf44_s6_p34.txt");
        double[] ex_4_4_512_with_noise_hwt_s6_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_hwt_s6_p34.txt");
        
        double cdf44_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s6_p34, 
                new AbsModSim(), DTW.MaxWW);
        double hwt_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s6_p34, 
                new AbsModSim(), DTW.MaxWW);
        double cdf44_hwt_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s6_p34, ex_4_4_512_with_noise_hwt_s6_p34, 
                new AbsModSim(), DTW.MaxWW);
        
        double cdf44_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s6_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s6_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double cdf44_hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s6_p34, ex_4_4_512_with_noise_hwt_s6_p34,
                new SquaredEuclideanSim(), DTW.MaxWW);
        
        double euclidDist_cdf44 = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s6_p34);
        double euclidDist_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        double eucd_cdf44_hwt = TwoDCurveComparator.euclidDistance(ex_4_4_512_with_noise_cdf44_s6_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        
        double mand_cdf44     = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s6_p34);
        double mand_hwt       = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        double mand_cdf44_hwt = TwoDCurveComparator.manhattanDistance(ex_4_4_512_with_noise_cdf44_s6_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        
        double cosi_cdf44     = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s6_p34);
        double cosi_hwt       = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        double cosi_cdf44_hwt = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_with_noise_cdf44_s6_p34, ex_4_4_512_with_noise_hwt_s6_p34);
        
        System.out.println("cdf44_s6_dtw        == " + cdf44_dtw);
        System.out.println("hwt_s6_dtw          == " + hwt_dtw);
        System.out.println("cdf44_hwt_s6_sim    == " + cdf44_hwt_dtw);
        System.out.println();
          
        System.out.println("cdf44_s6_dtw_se      == " + cdf44_dtw_se);
        System.out.println("hwt_s6_dtw_se        == " + hwt_dtw_se);
        System.out.println("cdf44_hwt_s6_dwt_se  == " + cdf44_hwt_dtw_se);
        System.out.println();
        
        System.out.println("euclidDist_cdf44_s6 == " + euclidDist_cdf44);
        System.out.println("euclidDist_hwt_s6   == " + euclidDist_hwt);
        System.out.println("eucd_cdf44_hwt_s6   == " + eucd_cdf44_hwt);
        System.out.println();
        
        System.out.println("mand_cdf44_s6       == " + mand_cdf44);
        System.out.println("mand_hwt_s6         == " + mand_hwt);
        System.out.println("mand_cdf44_hwt_s6   == " + mand_cdf44_hwt);
        System.out.println();
        
        System.out.println("cosi_cdf44_s6       == " + cosi_cdf44);
        System.out.println("cosi_hwt_s6         == " + cosi_hwt);
        System.out.println("cosi_cdf44_hwt      == " + cosi_cdf44_hwt);
        
        System.out.println("=============");
        System.out.println();
        System.out.println();
    }
    
    static void test_ex_4_4_p34_s5() {
        double[] ex_4_4_512_no_noise_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_no_noise_p34.txt");
        double[] ex_4_4_512_with_noise_cdf44_s5_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_cdf44_s5_p34.txt");
        double[] ex_4_4_512_with_noise_hwt_s5_p34 = 
                fileToPrimitiveDoubleArray("C:/Users/vladimir/programming/java/NetBeans/DTWLib/ex_4_4_512_with_noise_hwt_s5_p34.txt");
        
        double cdf44_s5_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s5_p34, 
                new AbsModSim(), DTW.MaxWW);
        double hwt_s5_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s5_p34, 
                new AbsModSim(), DTW.MaxWW);
        double cdf44_hwt_s5_dtw = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s5_p34, ex_4_4_512_with_noise_hwt_s5_p34, 
                new AbsModSim(), DTW.MaxWW);
        
        double cdf44_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s5_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s5_p34, 
                new SquaredEuclideanSim(), DTW.MaxWW);
        double cdf44_hwt_dtw_se = DTW.dpDTW2ColDoubleCost(ex_4_4_512_with_noise_cdf44_s5_p34, ex_4_4_512_with_noise_hwt_s5_p34,
                new SquaredEuclideanSim(), DTW.MaxWW);
        
        double euclidDist_cdf44 = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s5_p34);
        double euclidDist_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s5_p34);
        double eucd_cdf44_hwt   = TwoDCurveComparator.euclidDistance(ex_4_4_512_with_noise_cdf44_s5_p34, ex_4_4_512_with_noise_hwt_s5_p34);
         
        double mand_cdf44     = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s5_p34);
        double mand_hwt       = TwoDCurveComparator.manhattanDistance(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s5_p34);
        double mand_cdf44_hwt = TwoDCurveComparator.manhattanDistance(ex_4_4_512_with_noise_cdf44_s5_p34, ex_4_4_512_with_noise_hwt_s5_p34);
        
        double cosi_cdf44 = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_cdf44_s5_p34);
        double cosi_hwt   = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_no_noise_p34, ex_4_4_512_with_noise_hwt_s5_p34);
        double cosi_cdf44_hwt = TwoDCurveComparator.cosineSimilarity(ex_4_4_512_with_noise_cdf44_s5_p34, ex_4_4_512_with_noise_hwt_s5_p34);
        
        System.out.println("cdf44_s5_dtw        == " + cdf44_s5_dtw);
        System.out.println("hwt_s5_dtw          == " + hwt_s5_dtw);
        System.out.println("cdf44_hwt_s5_dtw    == " + cdf44_hwt_s5_dtw);
        System.out.println();
        
        System.out.println("cdf44_s5_dtw_se      == " + cdf44_dtw_se);
        System.out.println("hwt_s5_dtw_se        == " + hwt_dtw_se);
        System.out.println("cdf44_hwt_s5_dwt_se  == " + cdf44_hwt_dtw_se);
        System.out.println();
        
        System.out.println("euclidDist_cdf44_s5 == " + euclidDist_cdf44);
        System.out.println("euclidDist_hwt_s5   == " + euclidDist_hwt);
        System.out.println("eucd_cdf44_hwt_s5   == " + eucd_cdf44_hwt);
        System.out.println();
        
        System.out.println("cdf44_s5_dtw_se      == " + cdf44_dtw_se);
        System.out.println("hwt_s5_dtw_se        == " + hwt_dtw_se);
        System.out.println("cdf44_hwt_s5_dwt_se  == " + cdf44_hwt_dtw_se);
        System.out.println();
        
        System.out.println("mand_cdf44_s5       == " + mand_cdf44);
        System.out.println("mand_hwt_s5         == " + mand_hwt);
        System.out.println("mand_cdf44_hwt_s5   == " + mand_cdf44_hwt);
        System.out.println();
        
        System.out.println("cosi_cdf44_s5       == " + cosi_cdf44);
        System.out.println("cosi_hwt_s5         == " + cosi_hwt);        
        System.out.println("cosi_cdf44_hwt      == " + cosi_cdf44_hwt);
        
        System.out.println("=============");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        test_ex_4_4_p34_s8();
        test_ex_4_4_p34_s7();
        test_ex_4_4_p34_s6();
        test_ex_4_4_p34_s5();
        //double[] v1 = {1, 2, 3};
        //double[] v2 = {7, 8, 10};
        //System.out.println(cosineSimilarity(v1, v2));
    }

}
