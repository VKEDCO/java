package org.vkedco.wavelets.tests;

import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.wavelets.haar.OneDHaar;

/**
 **************************************************************** 
 * Programmatic Notes on Ch. 4 in "Ripples in Mathematics" by
 * A. Jensen & A. la Cour-Harbo.
 * 
 * @author Vladimir Kulyukin
 *****************************************************************
 */
public class RipplesInMathCh04 {
    
    static double[] sDomain = Partition.partition(0, 511, 1);
    static double[] sRange  = new double[512];
    static Ripples_F_p25 sRipples_F_p25 = new Ripples_F_p25();
    
    static final int D8_START   = 256; 
    static final int D8_END     = 511;
    static final int D7_START   = 128; 
    static final int D7_END     = 255;
    static final int D6_START   = 64;  
    static final int D6_END     = 127;
    static final int S6_START   = 0;   
    static final int S6_END     = 63;
    
    // prints the range values for the plot in Fig. 4.1, p. 26
    // in "Ripples in Mathematics."
    static void fig_4_1_p26() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        display_signal(sRange);
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
        display_signal_range(sRange, D8_START, D8_END);
    }
    
    // d7 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d7_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D7_START, D7_END);
    }
    
    // d6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D6_START, D6_END);
    }
    
    // s6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_s6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, S6_START, S6_END);
    }
        
    static void display_signal_range(double[] signal, int range_start, int range_end) {
        for(int i = range_start; i <= range_end; i++) {
            System.out.println(signal[i]);
        }
    }
    
    static void display_signal(double[] signal) {
        for(double d: signal) {
            System.out.println(d);
        }
    }
    
    static void fig_4_4_p28(int range_start, int range_end) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        double[] signal = new double[sRange.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal[i] = sRange[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        display_signal(signal);
        System.out.println("=========================");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(signal, 3);
        display_signal(signal);   
    }
    
    static void fig_4_4_06_06_07_d8_p28() {
        fig_4_4_p28(D8_START, D8_END);
    }
    
    static void fig_4_4_06_06_d7_08_p28() {
        fig_4_4_p28(D7_START, D7_END);
    }
    
    static void fig_4_4_06_d6_07_08_p28() {
        fig_4_4_p28(D6_START, D6_END);
    }
    
    static void fig_4_4_s6_06_07_08_p28() {
        fig_4_4_p28(S6_START, S6_END);
    }
    
    public static void main(String[] args) {
        fig_4_3_s6_p27();
    }
    
}
