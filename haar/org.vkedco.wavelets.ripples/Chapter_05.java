package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.haar.OneDHaar;
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
    
    public static void main(String[] args) {
        table_5_3_p38_with_inverses();
    }
    
}
