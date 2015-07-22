package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.haar.OneDHaar;
import org.vkedco.wavelets.haar.TwoDHaar;
/**
 *
 * @author vladimir kulyukin
 * 
 * programmatic notes on Ch. 05, "Ripples in Mathematics" by
 * A. Jensen, A. La Cour-Harbo.
 */
public class Chapter_05 {
    
    public static void table_5_1_p38() {
        double[] s_2 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] s_1 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] s_0 = {1, 1, 1, 1, 1, 1, 1, 1};
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
    }
    
    public static void table_5_1_p38_with_inverses() {
        double[] s_2 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] s_1 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] s_0 = {1, 1, 1, 1, 1, 1, 1, 1};
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("Reconstruction from S2: "); OneDHaar.displaySample(s_2);
        
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("Reconstruction from S1: "); OneDHaar.displaySample(s_1);
        
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
        
         // Inverse S_0 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("Reconstruction from S0: "); OneDHaar.displaySample(s_0);
    }
    
    public static void table_5_2_p38() {
        double[] s_2 = {1, 1, 1, 1, -1, -1, -1, -1}; 
        double[] s_1 = {1, 1, 1, 1, -1, -1, -1, -1};
        double[] s_0 = {1, 1, 1, 1, -1, -1, -1, -1};
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
    }
    
    public static void table_5_2_p38_with_inverses() {
        double[] s_2 = {1, 1, 1, 1, -1, -1, -1, -1};
        double[] s_1 = {1, 1, 1, 1, -1, -1, -1, -1};
        double[] s_0 = {1, 1, 1, 1, -1, -1, -1, -1};
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("Reconstruction from S2: "); OneDHaar.displaySample(s_2);
        
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("Reconstruction from S1: "); OneDHaar.displaySample(s_1);
        
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
        
         // Inverse S_0 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("Reconstruction from S0: "); OneDHaar.displaySample(s_0);
    }
    
    public static void table_5_3_p38() {
        double[] s_2 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        double[] s_1 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        double[] s_0 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
    }
    
    public static void table_5_3_p38_with_inverses() {
        double[] s_2 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        double[] s_1 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        double[] s_0 = {1, 1, -1, -1, 0, 0, 0, 0}; 
        
        System.out.print("S3: "); OneDHaar.displaySample(s_2);
        // 1st scale - ordered FHWT applied once.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("S2: "); OneDHaar.displaySample(s_2);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_2, 1);
        System.out.print("Reconstruction from S2: "); OneDHaar.displaySample(s_2);
        
        // 2nd scale - ordered FHWT applied twice.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("S1: "); OneDHaar.displaySample(s_1);
        
        // Inverse S_1 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_1, 2);
        System.out.print("Reconstruction from S1: "); OneDHaar.displaySample(s_1);
        
        // 3rd scale - ordered FHWT applied three times
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("S0: "); OneDHaar.displaySample(s_0); 
        
         // Inverse S_0 and print the result
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(s_0, 3);
        System.out.print("Reconstruction from S0: "); OneDHaar.displaySample(s_0);
    }
    
    public static void inverse_basis_vector(double[] bv) {
        OneDHaar.orderedFastInverseHaarWaveletTransform(bv);
        OneDHaar.displaySample(bv);
    }
    
    public static void test_inverse_basis_vectors() {
        double[] bv0 = {1, 0, 0, 0, 0, 0, 0, 0};
        double[] bv1 = {0, 1, 0, 0, 0, 0, 0, 0};
        double[] bv2 = {0, 0, 1, 0, 0, 0, 0, 0};
        double[] bv3 = {0, 0, 0, 1, 0, 0, 0, 0};
        double[] bv4 = {0, 0, 0, 0, 1, 0, 0, 0};
        double[] bv5 = {0, 0, 0, 0, 0, 1, 0, 0};
        double[] bv6 = {0, 0, 0, 0, 0, 0, 1, 0};
        double[] bv7 = {0, 0, 0, 0, 0, 0, 0, 1};
        
        inverse_basis_vector(bv0);
        inverse_basis_vector(bv1);
        inverse_basis_vector(bv2);
        inverse_basis_vector(bv3);
        inverse_basis_vector(bv4);
        inverse_basis_vector(bv5);
        inverse_basis_vector(bv6);
        inverse_basis_vector(bv7);
    }
    
    // compute the vector of the forward matrix referred to as W_{a}^{n}
    public static void forward_basis_vector(double[] bv) {
        OneDHaar.orderedFastHaarWaveletTransform(bv);
        OneDHaar.displaySample(bv);
    }
    
    public static void test_forward_basis_vectors() {
        double[] bv0 = {1, 0, 0, 0, 0, 0, 0, 0};
        double[] bv1 = {0, 1, 0, 0, 0, 0, 0, 0};
        double[] bv2 = {0, 0, 1, 0, 0, 0, 0, 0};
        double[] bv3 = {0, 0, 0, 1, 0, 0, 0, 0};
        double[] bv4 = {0, 0, 0, 0, 1, 0, 0, 0};
        double[] bv5 = {0, 0, 0, 0, 0, 1, 0, 0};
        double[] bv6 = {0, 0, 0, 0, 0, 0, 1, 0};
        double[] bv7 = {0, 0, 0, 0, 0, 0, 0, 1};
        
        forward_basis_vector(bv0);
        forward_basis_vector(bv1);
        forward_basis_vector(bv2);
        forward_basis_vector(bv3);
        forward_basis_vector(bv4);
        forward_basis_vector(bv5);
        forward_basis_vector(bv6);
        forward_basis_vector(bv7);
    }
    
    public static void testForwardHaarTransformMatrix() {
        double[][] fhtm3 = OneDHaar.computeForwardHaarTransformMatrix(3);
        double[][] fhtm4 = OneDHaar.computeForwardHaarTransformMatrix(4);
        TwoDHaar.displaySample(fhtm3, 8, 0);
        double[] sample00 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] sample01 = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.print("Input:\t"); OneDHaar.displaySample(sample00);
        OneDHaar.orderedFastHaarWaveletTransform(sample00);
        System.out.print("FHWT:\t"); OneDHaar.displaySample(sample00);
        double[] fwd_sample01 = OneDHaar.applyHaarTransformMatrix(fhtm3, sample01);
        System.out.print("MHWT:\t"); OneDHaar.displaySample(fwd_sample01);
        System.out.println();
        
        double[] sample10 = {1, 1, 1, 1, 0, 0, 0, 0};
        double[] sample11 = {1, 1, 1, 1, 0, 0, 0, 0};
        System.out.print("Input:\t"); OneDHaar.displaySample(sample10);
        OneDHaar.orderedFastHaarWaveletTransform(sample10);
        System.out.print("FHWT:\t"); OneDHaar.displaySample(sample10);
        double[] fwd_sample11 = OneDHaar.applyHaarTransformMatrix(fhtm3, sample11);
        System.out.print("MHWT:\t"); OneDHaar.displaySample(fwd_sample11);
        
        double[] sample20 = {12.0, 16.0, 27.0, 32.8, 33.5, 33.5, 39.0, 39.0,
                             40.0, 41.3, 41.3, 42.0, 43.0, 45.0, 35.5, 49.0};
        double[] sample21 = {12.0, 16.0, 27.0, 32.8, 33.5, 33.5, 39.0, 39.0,
                             40.0, 41.3, 41.3, 42.0, 43.0, 45.0, 35.5, 49.0};
        System.out.print("Input:\t"); OneDHaar.displaySample(sample20);
        OneDHaar.orderedFastHaarWaveletTransform(sample20);
        System.out.print("FHWT:\t"); OneDHaar.displaySample(sample20);
        double[] fwd_sample21 = OneDHaar.applyHaarTransformMatrix(fhtm4, sample21);
        System.out.print("MHWT:\t"); OneDHaar.displaySample(fwd_sample21);
    }
    
    public static double dotProduct(double[][] m1, double[][] m2, int row, int col, int size) {
        double dp = 0;
        for(int i = 0; i < size; i++) {
            dp += m1[row][i]*m2[i][col];
        }
        return dp;
    }
    
    public static double[][] multiplySquareMatrices(double[][] m1, double[][] m2, int size) {
        double [][] rslt = new double[size][size];
        for(int row = 0; row < size; row++) {
            double dp = 0;
            for(int col = 0; col < size; col++) {
                dp = dotProduct(m1, m2, row, col, size);
                rslt[row][col] = dp;
            }
        }
        return rslt;
    }
    
    public static void testIdentity(int n) {
        double[][] fhtm_n = OneDHaar.computeForwardHaarTransformMatrix(n);
        double[][] ihtm_n = OneDHaar.computeInverseHaarTransformMatrix(n);
        double[][] in = multiplySquareMatrices(fhtm_n, ihtm_n, (int)Math.pow(2, n));
        
        TwoDHaar.displaySample(fhtm_n, (int)Math.pow(2, n), 0);
        System.out.println("==========");
        TwoDHaar.displaySample(ihtm_n, (int)Math.pow(2, n), 0);
        System.out.println("==========");
        TwoDHaar.displaySample(in, (int)Math.pow(2, n), 0);
    }
    
    public static void testInverseHaarTransformMatrix() {
        double[][] ihtm3 = OneDHaar.computeInverseHaarTransformMatrix(3);
        double[][] ihtm4 = OneDHaar.computeInverseHaarTransformMatrix(4);
        TwoDHaar.displaySample(ihtm3, 8, 0);
        
        double[] sample00 = {1, 1, 1, 1, 1, 1, 1, 1};
        double[] sample01 = {1, 1, 1, 1, 1, 1, 1, 1};
        OneDHaar.orderedFastHaarWaveletTransform(sample00);
        OneDHaar.orderedFastHaarWaveletTransform(sample01);
        
        System.out.print("Input:\t"); OneDHaar.displaySample(sample00);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample00);
        System.out.print("IFHWT:\t"); OneDHaar.displaySample(sample00);
        double[] isample01 = OneDHaar.applyHaarTransformMatrix(ihtm3, sample01);
        System.out.print("IMHWT:\t"); OneDHaar.displaySample(isample01);
        System.out.println();
        
        double[] sample10 = {1, 1, 1, 1, 0, 0, 0, 0};
        double[] sample11 = {1, 1, 1, 1, 0, 0, 0, 0};
        OneDHaar.orderedFastHaarWaveletTransform(sample10);
        OneDHaar.orderedFastHaarWaveletTransform(sample11);
        System.out.print("Input:\t"); OneDHaar.displaySample(sample10);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample10);
        System.out.print("IFHWT:\t"); OneDHaar.displaySample(sample10);
        double[] isample11 = OneDHaar.applyHaarTransformMatrix(ihtm3, sample11);
        System.out.print("IMHWT:\t"); OneDHaar.displaySample(isample11);
        
        double[] sample20 = {12.0, 16.0, 27.0, 32.8, 33.5, 33.5, 39.0, 39.0,
                             40.0, 41.3, 41.3, 42.0, 43.0, 45.0, 35.5, 49.0};
        double[] sample21 = {12.0, 16.0, 27.0, 32.8, 33.5, 33.5, 39.0, 39.0,
                             40.0, 41.3, 41.3, 42.0, 43.0, 45.0, 35.5, 49.0};
        OneDHaar.orderedFastHaarWaveletTransform(sample20);
        OneDHaar.orderedFastHaarWaveletTransform(sample21);
        System.out.print("Input:\t"); OneDHaar.displaySample(sample20);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample20);
        System.out.print("IFHWT:\t"); OneDHaar.displaySample(sample20);
        double[] isample21 = OneDHaar.applyHaarTransformMatrix(ihtm4, sample21);
        System.out.print("IMHWT:\t"); OneDHaar.displaySample(isample21);
    }
    
    public static void main(String[] args) {
        testIdentity(4);
    }
    
}
