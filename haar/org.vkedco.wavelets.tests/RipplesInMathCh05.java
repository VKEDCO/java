package org.vkedco.wavelets.tests;

/**
 *****************************************************************
 * Programmatic Notes on Ch. 5, "Ripples in Math: The
 * Discrete Wavelet Transform" by A. Jensen and A. la Cour-Harbo
 * 
 * @author Vladimir Kulyukin
 *****************************************************************
 */

import org.vkedco.wavelets.haar.OneDHWT;
import org.vkedco.wavelets.utils.Utils;
import org.vkedco.scicomp.linalgebra.matmanip.MatManip;


public class RipplesInMathCh05 {

    public static double[][] table_5_1 = {
        {1, 1, 1, 1, 1, 1, 1, 1},   // s3
        {1, 1, 1, 1, 0, 0, 0, 0},   // s2
        {1, 1, 0, 0, 0, 0, 0, 0},   // s1
        {1, 0, 0, 0, 0, 0, 0, 0}    // s0
    };
    
    public static double[] sig0 = {1, 0, 0, 0, 0, 0, 0, 0};
    public static double[] sig1 = {0, 1, 0, 0, 0, 0, 0, 0};
    public static double[] sig2 = {0, 0, 1, 0, 0, 0, 0, 0};
    public static double[] sig3 = {0, 0, 0, 1, 0, 0, 0, 0};
    public static double[] sig4 = {0, 0, 0, 0, 1, 0, 0, 0};
    public static double[] sig5 = {0, 0, 0, 0, 0, 1, 0, 0};
    public static double[] sig6 = {0, 0, 0, 0, 0, 0, 1, 0};
    public static double[] sig7 = {0, 0, 0, 0, 0, 0, 0, 1};
    
    // Reconstruction of Table 5.1, p. 38
    // The methods below can, of course, be combined into one method.
    // I kept them as is for illustrative purposes. I wrote them when I
    // was reconstructing the table.
    static void reconstructFromS3ForNumIters(double[] sig, int num_iters) {
        double[] sig_copy = Utils.copySignal(sig);
        OneDHWT.ordInvFHWTForNumIters(sig_copy, num_iters);
        Utils.displaySample(sig_copy);
        sig_copy = null;
    }
    
    public static void reconstructTable_Section_5_1_p38(double[] sig) {
        reconstructFromS3ForNumIters(sig, 3);
        reconstructFromS3ForNumIters(sig, 2);
        reconstructFromS3ForNumIters(sig, 1);
        Utils.displaySample(sig);
    }
    
    public static void reconstructTable_5_1_p38() {
        System.out.println("Table 5.1, p. 38");
        reconstructTable_Section_5_1_p38(sig0);
        System.out.println();
    }
    
    public static void reconstructTable_5_2_p38() {
        System.out.println("Table 5.2, p. 38");
        reconstructTable_Section_5_1_p38(sig1);
        System.out.println();
    }
    
    public static void reconstructTable_5_3_p38() {
        System.out.println("Table 5.3, p. 38");
        reconstructTable_Section_5_1_p38(sig2);
        System.out.println();
    }
    
    // These tables are not presented in Ch. 5, but can be computed.
    // Each of these are the transposes of the corresponding rows in
    // Matrix 5.1 on p. 38.
    public static void reconstructTable_5_4_p38() {
        System.out.println("Table 5.4, p. 38");
        reconstructTable_Section_5_1_p38(sig3);
        System.out.println();
    }
    
    public static void reconstructTable_5_5_p38() {
        System.out.println("Table 5.5, p. 38");
        reconstructTable_Section_5_1_p38(sig4);
        System.out.println();
    }
    
    public static void reconstructTable_5_6_p38() {
        System.out.println("Table 5.6, p. 38");
        reconstructTable_Section_5_1_p38(sig5);
        System.out.println();
    }
    
    public static void reconstructTable_5_7_p38() {
        System.out.println("Table 5.7, p. 38");
        reconstructTable_Section_5_1_p38(sig6);
        System.out.println();
    }
    
    public static void reconstructTable_5_8_p38() {
        System.out.println("Table 5.8, p. 38");
        reconstructTable_Section_5_1_p38(sig7);
        System.out.println();
    }
    
    public static double[] constructBasisSignal(int len, int bit_pos) {
        double[] bs = new double[len];
        for(int i = 0; i < len; i++) {
            if ( i != bit_pos )
                bs[i] = 0;
            else
                bs[i] = 1;
        }
        return bs;
    }
    public static double[][] computeForward1DHWTMatrix(int num_iters) {
        final int len = (int)(Math.pow(2, num_iters));
        double[][] fm = new double[len][len];
        
        for(int i = 0; i < len; i++) {
            double[] sig = constructBasisSignal(len, i);
            OneDHWT.ordFHWTForNumIters(sig, num_iters);
            for(int r = 0; r < len; r++) {
                fm[r][i] = sig[r];
            }
            sig = null;
        }
        return fm;
    }
    
    public static double[][] computeInverse1DHWTMatrix(int num_iters) {
        final int len = (int)(Math.pow(2, num_iters));
        double[][] im = new double[len][len];
        
        for(int i = 0; i < len; i++) {
            double[] sig = constructBasisSignal(len, i);
            OneDHWT.ordInvFHWTForNumIters(sig, num_iters);
            for(int r = 0; r < len; r++) {
                im[r][i] = sig[r];
            }
            sig = null;
        }
        return im;
    }

    
    public static void test1DHWTMat(int dim) {
       double[][] fwdMat = computeForward1DHWTMatrix(dim);
       double[][] invMat = computeInverse1DHWTMatrix(dim);
       final int n = (int)(Math.pow(2, dim));
       
       System.out.println("Forward " + n + " x " + n + " mat");
       Utils.display2DArray(fwdMat, n, n);
       System.out.println();
       System.out.println("Inverse " + n + " x " + n + " mat");
       Utils.display2DArray(invMat, n, n);
       System.out.println();
    }
    
    public static void testOneDHWTMat(int dim) {
       double[][] fwdMat = OneDHWT.computeFHWTMatrix(dim);
       double[][] normFwdMat = OneDHWT.computeNormFHWTMatrix(dim);
       double[][] invMat = OneDHWT.computeInvFHWTMatrix(dim);
       final int n = (int)(Math.pow(2, dim));
       
       System.out.println("Forward " + n + " x " + n + " mat");
       Utils.display2DArray(fwdMat, n, n);
       System.out.println();
       Utils.display2DArray(normFwdMat, n, n);
       System.out.println("Inverse " + n + " x " + n + " mat");
       Utils.display2DArray(invMat, n, n);
       System.out.println();
       double[][] idMat = MatManip.multiply(fwdMat, invMat);
       Utils.display2DArray(idMat, n, n);
       double[][] idMat2 = MatManip.multiply(invMat, fwdMat);
       Utils.display2DArray(idMat2, n, n);
       double[] sig0 = {1, 2, 3, 4};
       double[] normFHWT = OneDHWT.applyHWTMatrix(normFwdMat, sig0);
       Utils.displaySample(normFHWT);
       System.out.println(OneDHWT.normOf(sig0));
       System.out.println(OneDHWT.normOf(normFHWT));
    }
    
    public static void testNormalizationOfTransform(int dim, double[] sig) {
       //double[][] fwdMat = OneDHWT.computeFHWTMatrix(dim);
       double[][] normFwdMat = OneDHWT.computeNormFHWTMatrix(dim);
       //double[][] invMat = OneDHWT.computeInvFHWTMatrix(dim);
       //final int n = (int)(Math.pow(2, dim));
       
       //System.out.println("Forward " + n + " x " + n + " mat");
       //Utils.display2DArray(fwdMat, n, n);
       //System.out.println();
       //Utils.display2DArray(normFwdMat, n, n);
       //System.out.println("Inverse " + n + " x " + n + " mat");
       //Utils.display2DArray(invMat, n, n);
       //System.out.println();
       //double[][] idMat = MatManip.multiply(fwdMat, invMat);
       //Utils.display2DArray(idMat, n, n);
       //double[][] idMat2 = MatManip.multiply(invMat, fwdMat);
       //Utils.display2DArray(idMat2, n, n);
       //double[] sig0 = {1, 2, 3, 4};
       
       //Utils.displaySample(normFHWT);
       System.out.println(OneDHWT.normOf(sig));
       double[] normFHWT = OneDHWT.applyHWTMatrix(normFwdMat, sig);
       System.out.println(OneDHWT.normOf(normFHWT));
    }
    
    public static void main(String[] args) {
        double[] sig1   = {1, 2};
        double[] sig1a  = {2, 1};
        double[] sig2   = {1, 2, 3, 4};
        double[] sig2a  = {4, 3, 2, 1};
        double[] sig3   = {10, 3, 4, 5, 5, 90, 7, 345};
        double[] sig4   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        testNormalizationOfTransform(4, sig4);
    }  
}
