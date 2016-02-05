package org.vkedco.wavelets.tests;

import java.util.Arrays;
import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.calc.utils.Ripples_F_p33;
import org.vkedco.calc.utils.Ripples_F_ex_4_3_p33;
import org.vkedco.calc.utils.Ripples_F_ex_4_4_p34;
import org.vkedco.wavelets.haar.OneDHaar;
import org.vkedco.wavelets.ripples.CDF44;
import org.vkedco.wavelets.utils.Utils;


/**
 **************************************************************** 
 * Programmatic Notes on Ch. 4 in "Ripples in Mathematics" by
 * A. Jensen & A. la Cour-Harbo.
 * 
 * @author Vladimir Kulyukin
 *****************************************************************
 */

public class RipplesInMathCh04 {
    
    public enum SIGNAL { D10, D9, D8, D7, D6, S6 };
    
    static double[] sDomain = Partition.partition(0, 511, 1);
    static double[] sDomainFig_4_12_p33 = Partition.partition(0.0, 1.0, 1.0/1024.0);
    static double[] sRangeFig_4_12_p33 = new double[1024];
    static double[] sRange  = new double[512];
    static Ripples_F_p25 sRipples_F_p25 = new Ripples_F_p25();
    static Ripples_F_p33 sRipples_F_p33 = new Ripples_F_p33();
    
    static final int D10_START_1024  = 512;
    static final int D10_END_1024    = 1023;
    
    static final int D9_START_1024   = 256;
    static final int D9_END_1024     = 511;
    
    static final int D8_START_1024   = 128;
    static final int D8_END_1024     = 255;
    
    static final int D7_START_1024   = 64;
    static final int D7_END_1024     = 127;
    
    static final int D6_START_1024   = 32;
    static final int D6_END_1024     = 63;
    
    static final int S6_START_1024   = 0;
    static final int S6_END_1024     = 31;
    
    static final int D8_START_512   = 256; 
    static final int D8_END_512     = 511;
    static final int D7_START_512   = 128; 
    static final int D7_END_512     = 255;
    static final int D6_START_512   = 64; 
    static final int D6_END_512     = 127;
    static final int S6_START_512   = 0;   
    static final int S6_END_512     = 63;
    
    static final int D5_START_512   = 32;
    static final int D5_END_512     = 63;
    static final int S5_START_512   = 0;
    static final int S5_END_512     = 31;
    
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
        display_signal_range(sRange, D8_START_512, D8_END_512);
    }
    
    // d7 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d7_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D7_START_512, D7_END_512);
    }
    
    // d6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_d6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D6_START_512, D6_END_512);
    }
    
    // s6 range values for Fig. 4.3, p. 27 in "Ripples in Mathematics."
    static void fig_4_3_s6_p27() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, S6_START_512, S6_END_512);
    }
    
    static void fig_4_12_p33() {
        
        for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }
        
        //CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 6, false);
        
        display_signal(sRangeFig_4_12_p33);
    }
    
    static void multires_fig_4_13_p34(String message, int range_start, int range_end) {
        for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }

        CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 6, false);
        
        double[] signal = new double[sRangeFig_4_12_p33.length];
        for(int i = 0; i < 1024; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal[i] = sRangeFig_4_12_p33[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal);
        System.out.println("Inversed Signal");
        System.out.println("=========================");
        CDF44.orderedInverseDWTForNumIters(signal, 6, false);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void fig_4_13_s06_d06_d07_d08_d09_D10_p34() {
        multires_fig_4_13_p34("Fig. 4.13, 06-06-07-08-09-D10, p. 33", D10_START_1024, D10_END_1024);
    }
    
    static void fig_4_13_s06_d06_d07_d08_D9_d010_p34() {
        multires_fig_4_13_p34("Fig. 4.13, 06-06-07-08-D9-010, p. 33", D9_START_1024, D9_END_1024);
    }
    
    static void fig_4_13_s06_d06_d07_D8_d09_d010_p34() {
        multires_fig_4_13_p34("Fig. 4.13, 06-06-07-D8-09-010, p. 33", D8_START_1024, D8_END_1024);
    }
    
    static void fig_4_13_s06_d06_D7_d08_d09_d010_p34() {
        multires_fig_4_13_p34("Fig. 4.13, 06-06-D7-08-09-010, p. 33", D7_START_1024, D7_END_1024);
    }
    
    static void fig_4_13_s06_D6_d07_d08_d09_d010_p34() {
        multires_fig_4_13_p34("Fig. 4.13, 06-D6-07-08-09-010, p. 33", D6_START_1024, D6_END_1024);
    }
    
    static void fig_4_13_S6_d06_d07_d08_d09_d010_p34() {
        multires_fig_4_13_p34("Fig. 4.13, S6-06-07-08-09-010, p. 33", S6_START_1024, S6_END_1024);
    }
    
    // 1) Given a signal, apply several forward scales to it.
    // 2) Inverse the signal on S.
    // 3) Subtract the inversed signal from the original signal to
    //    remove the slow variations.
    // 4) what happens if we subtract the original signal from the inversed signal?
    //    the fast variations become negative.
    static void fig_4_15_p35() {
       for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }
        
        double[] sRangeFig_4_12_p33_original = new double[sRangeFig_4_12_p33.length];
        System.arraycopy(sRangeFig_4_12_p33, 0, sRangeFig_4_12_p33_original, 0, sRangeFig_4_12_p33.length);

        CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 6, false);
        
        double[] signal = new double[sRangeFig_4_12_p33.length];
        for(int i = 0; i < 1024; i++) {
            if ( i >= S6_START_1024 && i <= S6_END_1024 ) {
                signal[i] = sRangeFig_4_12_p33[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        CDF44.orderedInverseDWTForNumIters(signal, 6, false);
        
        for(int i = 0; i < sRangeFig_4_12_p33_original.length; i++) {
            sRangeFig_4_12_p33_original[i] -= signal[i];
        }
        
        display_signal(sRangeFig_4_12_p33_original);
        System.out.println("=========================");
    }
    
    static void fig_4_15a_p35() {
       for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }
        
        double[] sRangeFig_4_12_p33_original = new double[sRangeFig_4_12_p33.length];
        System.arraycopy(sRangeFig_4_12_p33, 0, sRangeFig_4_12_p33_original, 0, sRangeFig_4_12_p33.length);

        CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 6, false);
        
        double[] signal = new double[sRangeFig_4_12_p33.length];
        for(int i = 0; i < 1024; i++) {
            if ( i >= S6_START_1024 && i <= S6_END_1024 ) {
                signal[i] = sRangeFig_4_12_p33[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        CDF44.orderedInverseDWTForNumIters(signal, 6, false);
        
        for(int i = 0; i < sRangeFig_4_12_p33_original.length; i++) {
            signal[i] -=  sRangeFig_4_12_p33_original[i];
        }
        
        display_signal(signal);
        System.out.println("=========================");
    }
       
    static void display_signal_range(double[] signal, int range_start, int range_end) {
        for(int i = range_start; i <= range_end; i++) {
            System.out.println(signal[i]);
        }
    }
    
    static void display_signal_range_with_zeros(double[] signal, int range_start, int range_end) {
        for(int i = 0; i < signal.length; i++) {
            if ( i >= range_start && i <= range_end ) {
                System.out.println(signal[i]);
            }
            else {
                System.out.println(0);
            }
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
            case D8: fig_4_4_s06_d06_d07_d8_p28(); break;
            case D7: fig_4_4_s06_d06_d7_d08_p28(); break;
            case D6: fig_4_4_s06_d6_d07_d08_p28(); break;
            case S6: fig_4_4_s6_d06_d07_d08_p28(); break;
        }
    }
    
    static void fig_4_4_s06_d06_d07_d8_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-06-07-d8, p. 28", D8_START_512, D8_END_512);
    }
    
    static void fig_4_4_s06_d06_d7_d08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-06-d7-08, p. 28", D7_START_512, D7_END_512);
    }
    
    static void fig_4_4_s06_d6_d07_d08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-d6-07-08, p. 28", D6_START_512, D6_END_512);
    }
    
    static void fig_4_4_s6_d06_d07_d08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, s6-06-07-08, p. 28", S6_START_512, S6_END_512);
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
        
        // transformed signal after applying 3 iterations of 1D ordered HWT
        System.out.println("TRANSFORMED SIGNAL");
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
        System.out.println("=========================");
        
        // inverted signal
        System.out.println("INVERTED SIGNAL");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
        System.out.println("=========================");
        
        // difference b/w the inverted and original signals
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
        display_signal_range(sRange, D7_START_512, D7_END_512);
    }
    
    // d6 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_d6_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, D6_START_512, D6_END_512);
    }
    
    // s6 range values for Fig. 4.6, p. 29 in "Ripples in Mathematics."
    static void fig_4_6_s6_p29() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        
        sRange[200] = 2;
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        display_signal_range(sRange, S6_START_512, S6_END_512);
    }
    
    
    // prints the range values for the plot in Fig. 4.6, p.29
    // in "Ripples in Mathematics."
    static void fig_4_6_p29(SIGNAL signal) {
        switch ( signal ) {
            case D8: 
                fig_4_6_s06_d06_d07_d8_p29();
                break;
            case D7:
                fig_4_6_s06_d06_d7_d08_p29();
                break;
            case D6:
                fig_4_6_s06_d6_d07_d08_p29();
                break;
            case S6:
                fig_4_6_s6_d06_d07_d08_p29();
                break;
        }
    }
    
    static void fig_4_6_s06_d06_d07_d8_p29() {
        multires_fig_4_6_p29("06-06-07-d8, Fig. 4.6, p. 29", D8_START_512, D8_END_512);
    }
    
    static void fig_4_6_s06_d06_d7_d08_p29() {
        multires_fig_4_6_p29("06-06-d7-08, Fig. 4.6, p. 29", D7_START_512, D7_END_512);
    }
    
    static void fig_4_6_s06_d6_d07_d08_p29() {
        multires_fig_4_6_p29("06-d6-07-08, Fig. 4.6, p. 29", D6_START_512, D6_END_512);
    }
    
    static void fig_4_6_s6_d06_d07_d08_p29() {
        multires_fig_4_6_p29("s6-06-07-08, Fig. 4.6, p. 29", S6_START_512, S6_END_512);
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
    
    public static void addNoiseToSignal(double[] signal) {
        for(int i = 0; i < signal.length; i++) {
            signal[i] += Math.random()/2.0;
        }
    }
    
    static void fig_4_7_p30() {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange); 
        display_signal(sRange);
    }
    
    static void multires_fig_4_8_p30(String message, int range_start, int range_end) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        
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
    
    // d8 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s06_d06_d07_d8_p30() {
        multires_fig_4_8_p30("06-06-07-d8, Fig. 4.8, p. 30", D8_START_512, D8_END_512);
    }
    
    // d7 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s06_d06_d7_d08_p30() {
        multires_fig_4_8_p30("06-06-d7-08, Fig. 4.8, p. 30", D7_START_512, D7_END_512);
    }
    
    // d6 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s06_d6_d07_d08_p30() {
        multires_fig_4_8_p30("06-d6-07-08, Fig. 4.8, p. 30", D6_START_512, D6_END_512);
    }
    
    // s6 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s6_d06_d07_d08_p30() {
        multires_fig_4_8_p30("s6-06-07-08, Fig. 4.8, p. 30", S6_START_512, S6_END_512);
    }
    
    static void fig_4_9_p31(double percent) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        System.out.println("=========================");
        System.out.println("Signal with Noise:");
        display_signal(sRange);
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sRange, 3);
        
        //double percent = 20.0;
        keep_top_N_percent(sRange, D8_START_512, D8_END_512, percent);
        keep_top_N_percent(sRange, D7_START_512, D7_END_512, percent);
        keep_top_N_percent(sRange, D6_START_512, D6_END_512, percent);
        keep_top_N_percent(sRange, S6_START_512, S6_END_512, percent);
        
        System.out.println("=========================");
        System.out.println("Processed Signal with Noise:");
        display_signal(sRange);
        System.out.println("=========================");
        System.out.println("Inversed Signal with Noise:");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(sRange, 3);
        display_signal(sRange);
        System.out.println("=========================");
    }
    
    // =========================================================================
    static void multires_fig_4_10_p32(String message, int range_start, int range_end) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        
        CDF44.orderedDWTForNumIters(sRange, 3, false);
        
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
        System.out.println("inverse signal");
        CDF44.orderedInverseDWTForNumIters(signal, 3, false);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void multires_fig_4_10_p32(String message, int range_start, int range_end, double top_n_percent) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        
        CDF44.orderedDWTForNumIters(sRange, 3, false);
        
        double[] signal = new double[sRange.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal[i] = sRange[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        // keep only top_n percent of the computed coeffs in range
        keep_top_N_percent(signal, range_start, range_end, top_n_percent);
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal);
        System.out.println("=========================");
        CDF44.orderedInverseDWTForNumIters(signal, 3, false);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void fig_4_11_p32_top_n(double percent) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        System.out.println("=========================");
        System.out.println("Signal with Noise:");
        display_signal(sRange);
        
        CDF44.orderedDWTForNumIters(sRange, 3, false);
        
        keep_top_N_percent(sRange, D8_START_512, D8_END_512, percent);
        keep_top_N_percent(sRange, D7_START_512, D7_END_512, percent);
        keep_top_N_percent(sRange, D6_START_512, D6_END_512, percent);
        keep_top_N_percent(sRange, S6_START_512, S6_END_512, percent);
        
        System.out.println("=========================");
        System.out.println("Processed Signal with Noise:");
        display_signal(sRange);
        System.out.println("=========================");
        System.out.println("Inversed Signal with Noise:");
        CDF44.orderedInverseDWTForNumIters(sRange, 3, false);
        display_signal(sRange);
        System.out.println("=========================");
    }
    
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=10%
    static void fig_4_11_top_10_p32() {
        fig_4_11_p32_top_n(10.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=20%
    static void fig_4_11_top_20_p32() {
        fig_4_11_p32_top_n(20.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=30%
    static void fig_4_11_top_30_p32() {
        fig_4_11_p32_top_n(30.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=40%
    static void fig_4_11_top_40_p32() {
        fig_4_11_p32_top_n(40.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=50%
    static void fig_4_11_top_50_p32() {
        fig_4_11_p32_top_n(50.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=60%
    static void fig_4_11_top_60_p32() {
        fig_4_11_p32_top_n(60.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=70%
    static void fig_4_11_top_70_p32() {
        fig_4_11_p32_top_n(70.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=80%
    static void fig_4_11_top_80_p32() {
        fig_4_11_p32_top_n(80.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=90%
    static void fig_4_11_top_90_p32() {
        fig_4_11_p32_top_n(90.0);
    }
    
    // s6 range values for Fig. 4.11, p. 32 in "Ripples in Mathematics." top=100%
    static void fig_4_11_top_100_p32() {
        fig_4_11_p32_top_n(100.0);
    }
    
    // =============================

    // d8 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_d06_d07_D8_p32() {
        multires_fig_4_10_p32("06-06-07-D8, Fig. 4.10, p. 32", D8_START_512, D8_END_512);
    }
    
    // d7 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_d06_D7_d08_p32() {
        multires_fig_4_10_p32("06-06-D7-08, Fig. 4.10, p. 32", D7_START_512, D7_END_512);
    }
    
    // d6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_D6_d07_d08_p32() {
        multires_fig_4_10_p32("06-D6-07-08, Fig. 4.10, p. 32", D6_START_512, D6_END_512);
    }
    
    // s6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_S6_d06_d07_d08_p32() {
        multires_fig_4_10_p32("S6-06-07-08, Fig. 4.10, p. 32", S6_START_512, S6_END_512);
    }

    static void keep_top_N_percent(double[] signal, int range_start, int range_end, double percent) {
        if ( percent < 0.0 || percent > 100.0 ) throw new IllegalArgumentException("percent < 0 or > 100");
        if ( percent == 100.0 ) return; // no need to do anything
        
        final int range_length = range_end - range_start + 1;
        double[] sorted_range = new double[range_length];
        // copy the signal segment into sorted_range
        System.arraycopy(signal, range_start, sorted_range, 0, range_length);
        Arrays.sort(sorted_range); // sort the range
        final int percent_len = (int) (range_length * (percent/100.0));
        final int sorted_range_min_index = Math.max(0, Math.min(range_length - percent_len, range_length - 1));
        
        if ( sorted_range_min_index > range_length - 1 ) {
            System.out.println("range cannot be discretized");
            return;
        }
        
        /*
        System.out.println("range_start            = " + range_start);
        System.out.println("range_end              = " + range_end);
        System.out.println("range_length           = " + range_length);
        System.out.println("percent_length         = " + percent_len);
        System.out.println("min_index              = " + sorted_range_min_index);
        System.out.println("max_index              = " + (range_length - 1));
        System.out.println("sorted_range_min       = " + sorted_range[sorted_range_min_index]);
        System.out.println("sorted_range_max       = " + sorted_range[range_length-1]);
        */
                
        final double sorted_range_min = Math.abs(sorted_range[sorted_range_min_index]);
        // set all values in signal less than the abs(sorted_range_min) to 0.0
        for(int i = range_start; i < range_end; i++) {
            if ( Math.abs(signal[i]) < sorted_range_min ) {
                signal[i] = 0.0;
            }
        }
    }
    
    // Define a function that takes an array, start, end, percent
    // copy array[start, end]
    // sort the array
    // compute the start and end index
    // range_min = sorted_array[start]
    // range_max = sorted_array[end]
    // go through array[start, end] and step all indices outslide of [range_min, range_max]
    // to 0.
    
    
    // 10% coefficients.
    // Given an array, sort it, then take the top percent
    public static void topNPercent(double[] ary, double percent) {
        for(int i = 0; i < ary.length; i++) { System.out.print(ary[i] + " "); }
        System.out.println();
        Arrays.sort(ary);
        for(int i = 0; i < ary.length; i++) { System.out.print(ary[i] + " "); }
        System.out.println();
        int len = (int) (ary.length * (percent/100.0));
        System.out.println(len);
        int end = ary.length - 1;
        int start = Math.max(0, end - len + 1);
        System.out.println("start = " + start + " end = " + end); 
    }
    
    // this is for ex. 4.3, p. 33
    static double[] generate_chirp(int len) {
        double[] chirp_signal = new double[len];
        
        double[] chirp_domain = Partition.partition(0, len, 1);
 
        Ripples_F_ex_4_3_p33 chirp = new Ripples_F_ex_4_3_p33();
        for(int i = 0; i < len; i++) {
            chirp_signal[i] = chirp.v(chirp_domain[i]);
        }
        
        chirp_domain = null;
        
        return chirp_signal;
    }
    
    static void display_chirp_512() {
        double[] chirp_signal = generate_chirp(512);
        display_signal(chirp_signal);
        chirp_signal = null;
    }
    
    static void display_chirp_1024() {
        double[] chirp_signal = generate_chirp(1024);
        display_signal(chirp_signal);
        chirp_signal = null;
    }
    
    static void testTopNPercent() {
        double[] a1 = {20, 1, 17, 11, 2, 8, 10, 4, 9, 19};
        
        topNPercent(a1, 100.0);
        Utils.displaySample(a1);
        keep_top_N_percent(a1, 0, 9, 100.0);
        Utils.displaySample(a1);
    }
    
    static void multires_ex_4_3_cdf44_p33(String message, int range_start, int range_end, int signal_size, int num_scales) {
        double[] chirp_signal = generate_chirp(signal_size);
        
        CDF44.orderedDWTForNumIters(chirp_signal, num_scales, false);
        
        double[] signal = new double[signal_size];
        for(int i = 0; i < signal_size; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal[i] = chirp_signal[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal);
        System.out.println("Inversed Signal");
        System.out.println("=========================");
        CDF44.orderedInverseDWTForNumIters(signal, num_scales, false);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void ex_4_3_chirp_512_cdf44_d8_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D8, p. 33", D8_START_512, D8_END_512, 512, 3);
    }
    
    static void ex_4_3_chirp_512_cdf44_d7_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D7, p. 33", D7_START_512, D7_END_512, 512, 3);
    }
    
    static void ex_4_3_chirp_512_cdf44_d6_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D6, p. 33", D6_START_512, D6_END_512, 512, 3);
    }
    
    static void ex_4_3_chirp_512_cdf44_s6_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, S6, p. 33", S6_START_512, S6_END_512, 512, 3);
    }
    
    static void ex_4_3_chirp_512_cdf44_d5_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D5, p. 33", D5_START_512, D5_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_s5_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, S5, p. 33", S5_START_512, S5_END_512, 512, 4);
    }
    
    // this is for ex. 4.4, p. 34, w/o noise
    static double[] generate_signal_without_noise_ex_4_4_p34(int len) {
        double[] signal = new double[len];
        double[] signal_domain = Partition.partition(0.0, 1.0, 1.0/len);
 
        Ripples_F_ex_4_4_p34 sf = new Ripples_F_ex_4_4_p34();
        
        for(int i = 0; i < len; i++) {
            signal[i] = sf.v(signal_domain[i]);
        }
        
        signal_domain = null;
        
        return signal;
    }
    
    static double[] generate_signal_with_noise_ex_4_4_p34(int len, int x, double v) {
        double[] signal = new double[len];
        double[] signal_domain = Partition.partition(0.0, 1.0, 1.0/len);
 
        Ripples_F_ex_4_4_p34 sf = new Ripples_F_ex_4_4_p34();
        
        for(int i = 0; i < len; i++) {
            signal[i] = sf.v(signal_domain[i]);
        }
        
        // add noise at x
        signal[x] = v;
        signal_domain = null;
        
        return signal;
    }
    
    static void display_signal_without_noise_ex_4_4_p34_512() {
        double[] signal = generate_signal_without_noise_ex_4_4_p34(512);
        display_signal(signal);
        signal = null;
    }
    
    static void display_signal_without_noise_ex_4_4_p34_1024() {
        double[] signal = generate_signal_without_noise_ex_4_4_p34(1024);
        display_signal(signal);
        signal = null;
    }
    
    static void display_signal_with_noise_ex_4_4_p34_512() {
        double[] signal = generate_signal_with_noise_ex_4_4_p34(512, 200, 4.0);
        display_signal(signal);
        signal = null;
    }
    
    static void display_signal_with_noise_ex_4_4_p34_1024() {
        double[] signal = generate_signal_with_noise_ex_4_4_p34(1024, 500, 4.0);
        display_signal(signal);
        signal = null;
    }
    
    // ex 4.4, p. 34
    static void multires_ex_4_4_cdf44_p34(String message, int range_start, int range_end, int signal_size, int num_scales) {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(signal_size, 200, 4.0);
        
        CDF44.orderedDWTForNumIters(noisy_signal, num_scales, false);
        
        double[] signal_transform = new double[signal_size];
        for(int i = 0; i < signal_size; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal_transform[i] = noisy_signal[i];
            }
            else {
                signal_transform[i] = 0;
            }
        }
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal_transform);
        System.out.println("Inversed Signal");
        System.out.println("=========================");
        CDF44.orderedInverseDWTForNumIters(signal_transform, num_scales, false);
        display_signal(signal_transform);
        System.out.println("=========================");
    }
    static void ex_4_4_512_cdf44_d8_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, D8, p. 34", D8_START_512, D8_END_512, 512, 3);
    }
    
    static void ex_4_4_512_cdf44_d7_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, D7, p. 34", D7_START_512, D7_END_512, 512, 3);
    }
    
    static void ex_4_4_512_cdf44_d6_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, D6, p. 34", D6_START_512, D6_END_512, 512, 3);
    }
    
    static void ex_4_4_512_cdf44_s6_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, S6, p. 34", S6_START_512, S6_END_512, 512, 3);
    }
    
    static void ex_4_4_512_cdf44_d5_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, D5, p. 34", D5_START_512, D5_END_512, 512, 4);
    }
    
    static void ex_4_4_512_cdf44_s5_p34() {
        multires_ex_4_4_cdf44_p34("Ex. 4.4, CDF44, S5, p. 34", S5_START_512, S5_END_512, 512, 4);
    }
    
    static void multires_ex_4_4_hwt_p34(String message, int range_start, int range_end, int signal_size, int num_scales) {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(signal_size, 200, 4.0);
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(noisy_signal, num_scales);
        
        double[] signal_transform = new double[signal_size];
        for(int i = 0; i < signal_size; i++) {
            if ( i >= range_start && i <= range_end ) {
                signal_transform[i] = noisy_signal[i];
            }
            else {
                signal_transform[i] = 0;
            }
        }
        
        System.out.println("=========================");
        System.out.println(message);
        display_signal(signal_transform);
        System.out.println("Inversed Signal");
        System.out.println("=========================");
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(signal_transform, num_scales);
        display_signal(signal_transform);
        System.out.println("=========================");
    }
    
    static void ex_4_4_512_hwt_d8_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, D8, p. 34", D8_START_512, D8_END_512, 512, 3);
    }
    
    static void ex_4_4_512_hwt_d7_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, D7, p. 34", D7_START_512, D7_END_512, 512, 3);
    }
    
    static void ex_4_4_512_hwt_d6_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, D6, p. 34", D6_START_512, D6_END_512, 512, 3);
    }
    
    static void ex_4_4_512_hwt_s6_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, S6, p. 34", S6_START_512, S6_END_512, 512, 3);
    }
    
    static void ex_4_4_512_hwt_d5_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, D5, p. 34", D5_START_512, D5_END_512, 512, 4);
    }
    
    static void ex_4_4_512_hwt_s5_p34() {
        multires_ex_4_4_hwt_p34("Ex. 4.4, HWT, S5, p. 34", S5_START_512, S5_END_512, 512, 4);
    }
    
    
    public static void main(String[] args) {
        //display_signal_with_noise_ex_4_4_p34_512();
        
        //ex_4_4_512_cdf44_d8_p34(); //done
        //ex_4_4_512_hwt_d8_p34();   //done
        
        //ex_4_4_512_cdf44_d7_p34();  //done
        //ex_4_4_512_hwt_d7_p34();    //done
        
        //ex_4_4_512_cdf44_d6_p34();   //done
        //ex_4_4_512_hwt_d6_p34();     //done
        
        //ex_4_4_512_cdf44_s6_p34();    //done
        //ex_4_4_512_hwt_s6_p34();      //done
        
        //ex_4_4_512_cdf44_d5_p34();    //done
        //ex_4_4_512_hwt_d5_p34();      //done
        
        //ex_4_4_512_cdf44_s5_p34();    //done
        //ex_4_4_512_hwt_s5_p34();      //done
    }
}
