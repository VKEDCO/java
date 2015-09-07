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
    
    public enum SIGNAL { D8, D7, D6, S6 };
    
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
    
    static void multires_fig_4_4_p28(String message, int range_start, int range_end) {
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
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal);
        System.out.println("=========================");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(signal, 3);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    // Fig. 4.4 on p. 28 in "Ripples in Mathematics."
    static void fig_4_4_p28(SIGNAL signal) {
        switch ( signal ) {
            case D8: fig_4_4_06_06_07_d8_p28(); break;
            case D7: fig_4_4_06_06_d7_08_p28(); break;
            case D6: fig_4_4_06_d6_07_08_p28(); break;
            case S6: fig_4_4_s6_06_07_08_p28(); break;
        }
    }
    
    static void fig_4_4_06_06_07_d8_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-06-07-d8, p. 28", D8_START, D8_END);
    }
    
    static void fig_4_4_06_06_d7_08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-06-d7-08, p. 28", D7_START, D7_END);
    }
    
    static void fig_4_4_06_d6_07_08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-d6-07-08, p. 28", D6_START, D6_END);
    }
    
    static void fig_4_4_s6_06_07_08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, s6-06-07-08, p. 28", S6_START, S6_END);
    }
    
    // prints the range values for the plot in Fig. 4.5, p.29
    // in "Ripples in Mathematics."
    static void fig_4_5_p29() {
        
        final int n = sRange.length;
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        double[] originalSignal = new double[n];
        System.arraycopy(sRange, 0, originalSignal, 0, n);
        
        // original signal
        System.out.println("ORIGINAL SIGNAL");
        display_signal(sRange); 
        System.out.println("=========================");
        
        System.out.println("TRANSFORMED SIGNAL");
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
        System.out.println("=========================");
        
        System.out.println("INVERTED SIGNAL");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
        System.out.println("=========================");
        
        double[] signalDifference = new double[n];
        for(int i = 0; i < n; i++) {
            signalDifference[i] = sRange[i] - originalSignal[i];
        }
        
        System.out.println("SIGNAL DIFFERENCE");
        display_signal(signalDifference);
    }
    
    // d8 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_d8_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
    }
    
    // d7 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_d7_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D7_START, D7_END);
    }
    
    // d6 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_d6_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D6_START, D6_END);
    }
    
    // s6 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_s6_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, S6_START, S6_END);
    }
    
    
    // prints the range values for the plot in Fig. 4.6, p.29
    // in "Ripples in Mathematics."
    static void fig_4_6_p29(SIGNAL signal) {
        switch ( signal ) {
            case D8: 
                fig_4_6_06_06_07_d8_p29();
                break;
            case D7:
                fig_4_6_06_06_d7_08_p29();
                break;
            case D6:
                fig_4_6_06_d6_07_08_p29();
                break;
            case S6:
                fig_4_6_s6_06_07_08_p29();
                break;
        }
    }
    
    static void fig_4_6_06_06_07_d8_p29() {
        multires_fig_4_6_p29("06-06-07-d8, Fig. 4.6, p. 29", D8_START, D8_END);
    }
    
    static void fig_4_6_06_06_d7_08_p29() {
        multires_fig_4_6_p29("06-06-d7-08, Fig. 4.6, p. 29", D7_START, D7_END);
    }
    
    static void fig_4_6_06_d6_07_08_p29() {
        multires_fig_4_6_p29("06-d6-07-08, Fig. 4.6, p. 29", D6_START, D6_END);
    }
    
    static void fig_4_6_s6_06_07_08_p29() {
        multires_fig_4_6_p29("s6-06-07-08, Fig. 4.6, p. 29", S6_START, S6_END);
    }
    
    static void multires_fig_4_6_p29(String message, int range_start, int range_end) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        
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
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal);
        System.out.println("=========================");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(signal, 3);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    public static void main(String[] args) {
        fig_4_6_p29(SIGNAL.S6);
    }
    
}
