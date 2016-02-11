package org.vkedco.wavelets.tests;

/**
 *****************************************************************
 * Programmatic Notes on Ch. 5, "Ripples in Math: The
 * Discrete Wavelet Transform" by A. Jensen and A. la Cour-Harbo
 * 
 * @author Vladimir Kulyukin
 *****************************************************************
 */

import org.vkedco.wavelets.haar.OneDHaar;
import org.vkedco.wavelets.utils.Utils;

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
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(sig_copy, num_iters);
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
    
    public static double[][] computeInverseHaarMatrix(int num_iters) {
        final int len = (int)(Math.pow(2, num_iters));
        double[][] im = new double[len][len];
        
        for(int i = 0; i < len; i++) {
            double[] sig = constructBasisSignal(len, i);
            OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(sig, num_iters);
            for(int r = 0; r < len; r++) {
                im[r][i] = sig[r];
            }
            sig = null;
        }
        return im;
    }
    
    public static double[][] computeDirectHaarMatrix(int num_iters) {
        final int len = (int)(Math.pow(2, num_iters));
        double[][] dm = new double[len][len];
        
        for(int i = 0; i < len; i++) {
            double[] sig = constructBasisSignal(len, i);
            OneDHaar.orderedFastHaarWaveletTransformForNumIters(sig, num_iters);
            for(int r = 0; r < len; r++) {
                dm[r][i] = sig[r];
            }
            sig = null;
        }
        return dm;
    }
    
    public static void main(String[] args) {
        double[][] im = computeInverseHaarMatrix(3);
        double[][] dm = computeDirectHaarMatrix(3);
        Utils.display2DArray(im, 8, 8);
        System.out.println();
        Utils.display2DArray(dm, 8, 8);
    }
    
}
