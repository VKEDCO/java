package org.vkedco.wavelets.tests;

import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.wavelets.haar.OneDHaar;

/**
 * Programmatic Notes on Ch. 4 in "Ripples in Mathematics" by
 * A. Jensen & A. la Cour-Harbo.
 * 
 * @author Vladimir Kulyukin
 */
public class RipplesInMathCh04 {
    
    static double[] sDomain = Partition.partition(0, 511, 1);
    static double[] sRange  = new double[512];
    static Ripples_F_p25 sRipples_F_p25 = new Ripples_F_p25();
    
    // prints the range values for the plot in Fig. 4.1, p. 26
    // in "Ripples in Mathematics."
    static void fig_4_1_p26() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        for(int i = 0; i < 512; i++)  {
            System.out.println(sRange[i]);
        }
    }
    // prints the range values for the plot in Fig. 4.2, p.26
    // in "Ripples in Mathematics."
    static void fig_4_2_p26() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        for(int i = 0; i < 512; i++)  {
            System.out.println(sRange[i]);
        }
    }
    
    // d8 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d8_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
 
        final int d8_start = 256; final int d8_end = 511;
        fig_4_3_p27(d8_start, d8_end);
    }
    
    // d7 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d7_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        final int d7_start = 128; final int d7_end = 255;
        fig_4_3_p27(d7_start, d7_end);
    }
    
    // d6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        final int d6_start = 64;  final int d6_end = 127;
        fig_4_3_p27(d6_start, d6_end);
    }
    
    // s6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_s6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        int d8_start = 256; int d8_end = 511;
        int d7_start = 128; int d7_end = 255;
        int d6_start = 64;  int d6_end = 127;
        int s6_start = 0;   int s6_end = 63;
        
        fig_4_3_p27(s6_start, s6_end);
    }
        
    
    static void fig_4_3_p27(int range_start, int range_end) {
        
        //for(int i = 0; i < 512; i++)  {
        //    sRange[i] = sRipples_F_p25.v(sDomain[i]);
        //}
        
        //OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        //int d8_start = 256; int d8_end = 511;
        //int d7_start = 128; int d7_end = 255;
        //int d6_start = 64;  int d6_end = 127;
        //int s6_start = 0;   int s6_end = 63;
        
        // 06, 06, 07, d8
        double[] s06_d06_d07_d8 = new double[sRange.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= range_start && i <= range_end ) {
                s06_d06_d07_d8[i] = sRange[i];
            }
            else {
                s06_d06_d07_d8[i] = 0;
            }
        }
        
        for(int i = range_start; i <= range_end; i++) {
            System.out.println(s06_d06_d07_d8[i]);
        }
    }
    
    
    
    static void ripplesTest03() {
        double[] domain = Partition.partition(0, 511, 1);
        double[] range  = new double[512];
        Ripples_F_p25 f = new Ripples_F_p25();
        
        for(int i = 0; i < 512; i++)  {
            range[i] = f.v(domain[i]);
        }
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(range, 3);
        
        //System.out.println("RANGE");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(range[i]);
        //}
        
        int d8_start = 256; int d8_end = 511;
        int d7_start = 128; int d7_end = 255;
        int d6_start = 64;  int d6_end = 127;
        int s6_start = 0;   int s6_end = 63;
        
        // 06, 06, 07, d8
        double[] s06_d06_d07_d8 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d8_start && i <= d8_end ) {
                s06_d06_d07_d8[i] = range[i];
            }
            else {
                s06_d06_d07_d8[i] = 0;
            }
        }
        
        //System.out.println("S06_D06_D07_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d07_d8[i]);
        //}
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
        //System.out.println("Inversed S06_D06_D07_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d07_d8[i]);
        //}
        
        // 06, 06, d7, 08
        double[] s06_d06_d7_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d7_start && i <= d7_end ) {
                s06_d06_d7_d08[i] = range[i];
            }
            else {
                s06_d06_d07_d8[i] = 0;
            }
        }
        
        //System.out.println("S06_D06_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d7_d08[i]);
        //}
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d7_d08, 3);
        
        //System.out.println("Inverse S06_D06_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d7_d08[i]);
        //}
        
        // 06, d6, 07, 08
        double[] s06_d6_d07_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d6_start && i <= d6_end ) {
                s06_d6_d07_d08[i] = range[i];
            }
            else {
                s06_d6_d07_d08[i] = 0;
            }
        }
        
        //System.out.println("S06_D6_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d6_d07_d08[i]);
        //}
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d6_d07_d08, 3);
        
        //System.out.println("Inverse S06_D6_D07_D08");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d6_d07_d08[i]);
        //}
        
        // s6, 06, 07, 08
        double[] s6_d06_d07_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= s6_start && i <= s6_end ) {
                s6_d06_d07_d08[i] = range[i];
            }
            else {
                s6_d06_d07_d08[i] = 0;
            }
        }
        
        //System.out.println("S6_D06_D07_D08");
        //for(int i = 0; i < 512; i++) {
        //   System.out.println(s6_d06_d07_d08[i]);
        //}
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s6_d06_d07_d08, 3);
        
        System.out.println("Inversed S6_D06_D07_D08");
        for(int i = 0; i < 512; i++) {
            System.out.println(s6_d06_d07_d08[i]);
        }
        
        /*
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
        // Inversed S06_D06_D07_D8
        System.out.println("Inversed S06_D06_D07_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d07_d8[i]);
        }
        
        
        System.out.println("S06_D06_D7_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d7_d08[i]);
        }
        */
        
        /*
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
        System.out.println("Inversed S06_D06_D07_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d07_d8[i]);
        }
        */
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D8");
        //for(int i = 256; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D7");
        //for(int i = 128; i < 256; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D6");
        //for(int i = 64; i < 128; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("RANGE");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(range[i]);
        //}
    }
    
    public static void main(String[] args) {
        fig_4_3_d6_p27();
    }
    
}
