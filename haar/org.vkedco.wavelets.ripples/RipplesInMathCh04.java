package org.vkedco.wavelets.tests;

import java.util.Arrays;
import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
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
        multires_fig_4_4_p28("Fig. 4.4, 06-06-07-d8, p. 28", D8_START, D8_END);
    }
    
    static void fig_4_4_s06_d06_d7_d08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-06-d7-08, p. 28", D7_START, D7_END);
    }
    
    static void fig_4_4_s06_d6_d07_d08_p28() {
        multires_fig_4_4_p28("Fig. 4.4, 06-d6-07-08, p. 28", D6_START, D6_END);
    }
    
    static void fig_4_4_s6_d06_d07_d08_p28() {
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
        multires_fig_4_6_p29("06-06-07-d8, Fig. 4.6, p. 29", D8_START, D8_END);
    }
    
    static void fig_4_6_s06_d06_d7_d08_p29() {
        multires_fig_4_6_p29("06-06-d7-08, Fig. 4.6, p. 29", D7_START, D7_END);
    }
    
    static void fig_4_6_s06_d6_d07_d08_p29() {
        multires_fig_4_6_p29("06-d6-07-08, Fig. 4.6, p. 29", D6_START, D6_END);
    }
    
    static void fig_4_6_s6_d06_d07_d08_p29() {
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
        multires_fig_4_8_p30("06-06-07-d8, Fig. 4.8, p. 30", D8_START, D8_END);
    }
    
    // d7 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s06_d06_d7_d08_p30() {
        multires_fig_4_8_p30("06-06-d7-08, Fig. 4.8, p. 30", D7_START, D7_END);
    }
    
    // d6 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s06_d6_d07_d08_p30() {
        multires_fig_4_8_p30("06-d6-07-08, Fig. 4.8, p. 30", D6_START, D6_END);
    }
    
    // s6 range values for Fig. 4.8, p. 30 in "Ripples in Mathematics."
    static void fig_4_8_s6_d06_d07_d08_p30() {
        multires_fig_4_8_p30("s6-06-07-08, Fig. 4.8, p. 30", S6_START, S6_END);
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
        keep_top_N_percent(sRange, D8_START, D8_END, percent);
        keep_top_N_percent(sRange, D7_START, D7_END, percent);
        keep_top_N_percent(sRange, D6_START, D6_END, percent);
        keep_top_N_percent(sRange, S6_START, S6_END, percent);
        
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
        
        keep_top_N_percent(sRange, D8_START, D8_END, percent);
        keep_top_N_percent(sRange, D7_START, D7_END, percent);
        keep_top_N_percent(sRange, D6_START, D6_END, percent);
        keep_top_N_percent(sRange, S6_START, S6_END, percent);
        
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
        multires_fig_4_10_p32("06-06-07-D8, Fig. 4.10, p. 32", D8_START, D8_END);
    }
    
    // d7 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_d06_D7_d08_p32() {
        multires_fig_4_10_p32("06-06-D7-08, Fig. 4.10, p. 32", D7_START, D7_END);
    }
    
    // d6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_D6_d07_d08_p32() {
        multires_fig_4_10_p32("06-D6-07-08, Fig. 4.10, p. 32", D6_START, D6_END);
    }
    
    // s6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_S6_d06_d07_d08_p32() {
        multires_fig_4_10_p32("S6-06-07-08, Fig. 4.10, p. 32", S6_START, S6_END);
    }

    static void keep_top_N_percent(double[] signal, int range_start, int range_end, double percent) {
        if ( percent < 0.0 || percent > 100.0 ) return;
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
        // set all values in signal less than the sorted_range_min to 0.0
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
    
    static void testTopNPercent() {
        double[] a1 = {20, 1, 17, 11, 2, 8, 10, 4, 9, 19};
        
        topNPercent(a1, 100.0);
        Utils.displaySample(a1);
        keep_top_N_percent(a1, 0, 9, 100.0);
        Utils.displaySample(a1);
    }
    
    public static void main(String[] args) {
        fig_4_11_top_100_p32();
        //testTopNPercent();
    }
}
