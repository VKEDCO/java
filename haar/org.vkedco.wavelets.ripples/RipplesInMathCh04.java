package org.vkedco.wavelets.tests;

import java.util.Arrays;
import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.calc.utils.Ripples_F_p33;
import org.vkedco.calc.utils.Ripples_F_ex_4_3_p33;
import org.vkedco.calc.utils.Ripples_F_ex_4_4_p34;
import org.vkedco.calc.utils.Ripples_F_p25b;
import org.vkedco.calc.utils.Ripples_F_p25c;
import org.vkedco.calc.utils.Ripples_F_p25d;
import org.vkedco.calc.utils.Ripples_F_p25e;
import org.vkedco.wavelets.haar.OneDHaar;
import org.vkedco.wavelets.ripples.CDF44;
import org.vkedco.wavelets.utils.Utils;
import org.vkedco.wavelets.ripples.ApplyDWT;


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
    
    static final int D10_START_1024  = 512; // 1st iter
    static final int D10_END_1024    = 1023;
    
    static final int D9_START_1024   = 256; // 2nd iter
    static final int D9_END_1024     = 511;
    
    static final int D8_START_1024   = 128; // 3rd iter
    static final int D8_END_1024     = 255;
    
    static final int D7_START_1024   = 64;  // 4th iter
    static final int D7_END_1024     = 127;
    
    static final int D6_START_1024   = 32;  // 5th iter
    static final int D6_END_1024     = 63;
    
    static final int S6_START_1024   = 0;   // 5th iter
    static final int S6_END_1024     = 31;
    
    // ==============  512 ==================
    static final int S8_START_512   = 0;
    static final int S8_END_512     = 257;
    
    static final int D8_START_512   = 256;  // 1st iter
    static final int D8_END_512     = 511;
    
    static final int S7_START_512   = 0;
    static final int S7_END_512     = 127;
    
    static final int D7_START_512   = 128;  // 2nd iter
    static final int D7_END_512     = 255;
    
    static final int D6_START_512   = 64;   // 3rd iter
    static final int D6_END_512     = 127;
    
    static final int S6_START_512   = 0;    // 3rd iter
    static final int S6_END_512     = 63;
    
    static final int D5_START_512   = 32;   // 5th iter
    static final int D5_END_512     = 63;
    
    static final int S5_START_512   = 0;
    static final int S5_END_512     = 31;
    
    // prints the range values for the plot in Fig. 4.1, p. 26
    // in "Ripples in Mathematics."
    static void fig_4_1_p26() {
        
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }
        // Uncomment if you want to display
        //display_signal(sRange);
    }
    
    static void fig_4_1_p26b() {
        Ripples_F_p25b f = new Ripples_F_p25b();
        for(int i = 0; i < 512; i++)  {
            sRange[i] = f.v(sDomain[i]);
        }
        // Uncomment if you want to display
        //display_signal(sRange);
    }
    
    static void fig_4_1_p26c() {
        Ripples_F_p25c f = new Ripples_F_p25c();
        for(int i = 0; i < 512; i++)  {
            sRange[i] = f.v(sDomain[i]);
        }
        // Uncomment if you want to display
        //display_signal(sRange);
    }
    
    static void fig_4_1_p26d() {
        Ripples_F_p25d f = new Ripples_F_p25d();
        for(int i = 0; i < 512; i++)  {
            sRange[i] = f.v(sDomain[i]);
        }
        // Uncomment if you want to display
        //display_signal(sRange);
    }
    
    static void fig_4_1_p26e() {
        Ripples_F_p25e f = new Ripples_F_p25e();
        for(int i = 0; i < 512; i++)  {
            sRange[i] = f.v(sDomain[i]);
        }
        // Uncomment if you want to display
        //display_signal(sRange);
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
        
        //display_signal(sRangeFig_4_12_p33);
    }
    
    static void multires_fig_4_13_cdf_p34(String message, int range_start, int range_end) {
        multires_fig_4_13_p34_aux(message, ApplyDWT.DWT.CDF44, 6, range_start, range_end);
        
        /*
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
        */
    }
    
    static void multires_fig_4_13_hwt_p34(String message, int range_start, int range_end) {
        multires_fig_4_13_p34_aux(message, ApplyDWT.DWT.HWT, 6, range_start, range_end);
    }
    
    static void multires_fig_4_13_p34_aux(String message, ApplyDWT.DWT dwt, int num_iters, int range_start, int range_end) {
        for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }

        //final int NUM_ITERS = 6;
        ApplyDWT.forwardDWTForNumIters(sRangeFig_4_12_p33, dwt, num_iters, range_start, range_end);
        
        //CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 6, false);
        
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
        
        ApplyDWT.inverseDWTForNumIters(signal, dwt, num_iters);
        //CDF44.orderedInverseDWTForNumIters(signal, 6, false);
        
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void fig_4_13_D10_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, 06-06-07-08-09-D10, CDF(4,4) p. 33", D10_START_1024, D10_END_1024);
    }
    
    static void fig_4_13_D10_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, 06-06-07-08-09-D10, HWT, p. 33", D10_START_1024, D10_END_1024);
    }
     
    static void fig_4_13_D9_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, 06-06-07-08-D9-010, CDF(4,4), p. 33", D9_START_1024, D9_END_1024);
    }
    
    static void fig_4_13_D9_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, 06-06-07-08-D9-010, HWT, p. 33", D9_START_1024, D9_END_1024);
    }
    
    static void fig_4_13_D8_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, 06-06-07-D8-09-010, CDF(4,4), p. 33", D8_START_1024, D8_END_1024);
    }
    
    static void fig_4_13_D8_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, 06-06-07-D8-09-010, HWT, p. 33", D8_START_1024, D8_END_1024);
    }
    
    static void fig_4_13_D7_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, 06-06-D7-08-09-010, CDF(4,4), p. 33", D7_START_1024, D7_END_1024);
    }
    
    static void fig_4_13_D7_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, 06-06-D7-08-09-010, HWT, p. 33", D7_START_1024, D7_END_1024);
    }
    
    static void fig_4_13_D6_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, 06-D6-07-08-09-010, CDF(4,4), p. 33", D6_START_1024, D6_END_1024);
    }
    
    static void fig_4_13_D6_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, 06-D6-07-08-09-010, HWT, p. 33", D6_START_1024, D6_END_1024);
    }
    
    static void fig_4_13_S6_CDF44_p34() {
        multires_fig_4_13_cdf_p34("Fig. 4.13, S6-06-07-08-09-010, CDF(4,4), p. 33", S6_START_1024, S6_END_1024);
    }
    
    static void fig_4_13_S6_HWT_p34() {
        multires_fig_4_13_hwt_p34("Fig. 4.13, S6-06-07-08-09-010, HWT, p. 33", S6_START_1024, S6_END_1024);
    }
    
    // ================== remove slow variations with CDF44
    static void fig_4_15_cdf44_p35() {
       for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }
        
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(sRangeFig_4_12_p33, ApplyDWT.DWT.CDF44, 5, S6_START_1024, S6_END_1024);
        
        display_signal(sRangeFig_4_12_p33);
        System.out.println("=========================");  
    }
    
    // ===================== remove slow variations with HWT
    static void fig_4_15_hwt_p35() {
       for(int i = 0; i < 1024; i++)  {
            sRangeFig_4_12_p33[i] = sRipples_F_p33.v(sDomainFig_4_12_p33[i]);
        }
        
        for(int i = 1; i < 1024; i += 32) {
            sRangeFig_4_12_p33[i] += 2;
        }
        
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(sRangeFig_4_12_p33, ApplyDWT.DWT.HWT, 5, S6_START_1024, S6_END_1024);
        
        display_signal(sRangeFig_4_12_p33);
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

        CDF44.orderedDWTForNumIters(sRangeFig_4_12_p33, 5, false);
        
        double[] signal = new double[sRangeFig_4_12_p33.length];
        for(int i = 0; i < 1024; i++) {
            if ( i >= S6_START_1024 && i <= S6_END_1024 ) {
                signal[i] = sRangeFig_4_12_p33[i];
            }
            else {
                signal[i] = 0;
            }
        }
        
        CDF44.orderedInverseDWTForNumIters(signal, 5, false);
        
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
        keepTopNPercentInRange(sRange, D8_START_512, D8_END_512, percent);
        keepTopNPercentInRange(sRange, D7_START_512, D7_END_512, percent);
        keepTopNPercentInRange(sRange, D6_START_512, D6_END_512, percent);
        keepTopNPercentInRange(sRange, S6_START_512, S6_END_512, percent);
        
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
    static void multires_fig_4_10_p32(String message, int range_start, int range_end, int num_iters) {
        for(int i = 0; i < 512; i++)  {
            sRange[i] = sRipples_F_p25.v(sDomain[i]);
        }

        sRange[200] = 2; // spike at 200
        addNoiseToSignal(sRange);
        
        CDF44.orderedDWTForNumIters(sRange, num_iters, false);
        
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
        CDF44.orderedInverseDWTForNumIters(signal, num_iters, false);
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
        keepTopNPercentInRange(signal, range_start, range_end, top_n_percent);
        
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
        
        keepTopNPercentInRange(sRange, D8_START_512, D8_END_512, percent);
        keepTopNPercentInRange(sRange, D7_START_512, D7_END_512, percent);
        keepTopNPercentInRange(sRange, D6_START_512, D6_END_512, percent);
        keepTopNPercentInRange(sRange, S6_START_512, S6_END_512, percent);
        
        System.out.println("=========================");
        System.out.println("Processed Signal with Noise:");
        display_signal(sRange);
        System.out.println("=========================");
        System.out.println("Inversed Signal with Noise:");
        CDF44.orderedInverseDWTForNumIters(sRange, 3, false);
        display_signal(sRange);
        System.out.println("=========================");
    }
    
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=10%
    static void fig_4_11_top_10_p32() {
        fig_4_11_p32_top_n(10.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=20%
    static void fig_4_11_top_20_p32() {
        fig_4_11_p32_top_n(20.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=30%
    static void fig_4_11_top_30_p32() {
        fig_4_11_p32_top_n(30.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics.", top=40%
    static void fig_4_11_top_40_p32() {
        fig_4_11_p32_top_n(40.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=50%
    static void fig_4_11_top_50_p32() {
        fig_4_11_p32_top_n(50.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=60%
    static void fig_4_11_top_60_p32() {
        fig_4_11_p32_top_n(60.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=70%
    static void fig_4_11_top_70_p32() {
        fig_4_11_p32_top_n(70.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=80%
    static void fig_4_11_top_80_p32() {
        fig_4_11_p32_top_n(80.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=90%
    static void fig_4_11_top_90_p32() {
        fig_4_11_p32_top_n(90.0);
    }
    
    // Fig. 4.11, p. 32 in "Ripples in Mathematics." top=100%
    static void fig_4_11_top_100_p32() {
        fig_4_11_p32_top_n(100.0);
    }
    
    // =============================

    // d8 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_d06_d07_D8_p32() {
        multires_fig_4_10_p32("06-06-07-D8, Fig. 4.10, p. 32", D8_START_512, D8_END_512, 3);
    }
    
    // d7 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_d06_D7_d08_p32() {
        multires_fig_4_10_p32("06-06-D7-08, Fig. 4.10, p. 32", D7_START_512, D7_END_512, 3);
    }
    
    // d6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_s06_D6_d07_d08_p32() {
        multires_fig_4_10_p32("06-D6-07-08, Fig. 4.10, p. 32", D6_START_512, D6_END_512, 3);
    }
    
    // s6 range values for Fig. 4.10, p. 32 in "Ripples in Mathematics."
    static void fig_4_10_S6_d06_d07_d08_p32() {
        multires_fig_4_10_p32("S6-06-07-08, Fig. 4.10, p. 32", S6_START_512, S6_END_512, 3);
    }
    
    static void fig_4_10_S5_d06_d07_d08_p32() {
        multires_fig_4_10_p32("S5-05-06-07-08, Fig. 4.10, p. 32", S5_START_512, S5_END_512, 4);
    }
    
    static void fig_4_10_s05_D5_d06_d07_d08_p32() {
        multires_fig_4_10_p32("05-D5-06-07-08, Fig. 4.10, p. 32", D5_START_512, D5_END_512, 4);
    }

    static void keepTopNPercentInRange(double[] signal, int range_start, int range_end, double percent) {
        if ( percent < 0.0 || percent > 100.0 ) throw new IllegalArgumentException("percent < 0 or > 100");
        if ( percent == 100.0 ) return; // no need to do anything
        
        final int range_length = range_end - range_start + 1;
        double[] sorted_range = new double[range_length];
        // copy the signal segment into sorted_range
        System.arraycopy(signal, range_start, sorted_range, 0, range_length);
        Arrays.sort(sorted_range); // sort the range
        final int percent_len = (int) (range_length * (percent/100.0));
        // 100 - 90 = 10
        final int sorted_range_min_index = Math.max(0, Math.min(range_length - percent_len, range_length - 1));
        
        if ( sorted_range_min_index > range_length - 1 ) {
            throw new IllegalArgumentException("Range cannot be discretized: " 
                    + range_start + ", " + range_end + ", " + percent);
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
    
    static void multires_ex_4_3_cdf44_p33(String message, int range_start, int range_end, int signal_size, int num_scales) {
        double[] chirp_signal = generate_chirp(signal_size);
        
        //CDF44.orderedDWTForNumIters(chirp_signal, num_scales, false);
        ApplyDWT.forwardDWTForNumIters(chirp_signal, ApplyDWT.DWT.CDF44, num_scales, range_start, range_end);
        
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
        //CDF44.orderedInverseDWTForNumIters(signal, num_scales, false);
        ApplyDWT.inverseDWTForNumIters(signal, ApplyDWT.DWT.CDF44, num_scales);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void ex_4_3_chirp_512_cdf44_d8_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D8, p. 33", D8_START_512, D8_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_d7_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D7, p. 33", D7_START_512, D7_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_d6_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D6, p. 33", D6_START_512, D6_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_s6_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, S6, p. 33", S6_START_512, S6_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_d5_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, D5, p. 33", D5_START_512, D5_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_cdf44_s5_p33() {
        multires_ex_4_3_cdf44_p33("Ex. 4.3, CDF44, S5, p. 33", S5_START_512, S5_END_512, 512, 4);
    }
    
    static void multires_ex_4_3_hwt_p33(String message, int range_start, int range_end, int signal_size, int num_scales) {
        double[] chirp_signal = generate_chirp(signal_size);
        
        ApplyDWT.forwardDWTForNumIters(chirp_signal, ApplyDWT.DWT.HWT, num_scales, range_start, range_end);
        
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
        ApplyDWT.inverseDWTForNumIters(signal, ApplyDWT.DWT.HWT, num_scales);
        display_signal(signal);
        System.out.println("=========================");
    }
    
    static void multires_ex_4_3_keep_fast_vars(String message, int signal_size, ApplyDWT.DWT dwt, int num_scales) {
        double[] chirp_signal = generate_chirp(signal_size);
        ApplyDWT.genericKeepFastVarsInSignal(chirp_signal, dwt, num_scales);
        display_signal(chirp_signal);
    }
    
    static void ex_4_3_keep_fast_vars_for_num_scales(String message, int signal_size, ApplyDWT.DWT dwt, int num_scales) {
        double[] chirp_signal = generate_chirp(signal_size);
        ApplyDWT.genericKeepFastVarsInSignal(chirp_signal, dwt, num_scales);
        display_signal(chirp_signal);
    }
    
    static void fig_4_12_keep_fast_vars_for_num_scales(ApplyDWT.DWT dwt, int num_scales) {
        fig_4_12_p33();
        System.out.println("====================");
        ApplyDWT.genericKeepFastVarsInSignal(sRangeFig_4_12_p33, dwt, num_scales);
        display_signal(sRangeFig_4_12_p33);
    }
    
    static void ex_4_3_chirp_512_hwt_d8_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, D8, p. 33", D8_START_512, D8_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_hwt_d7_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, D7, p. 33", D7_START_512, D7_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_hwt_d6_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, D6, p. 33", D6_START_512, D6_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_hwt_s6_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, S6, p. 33", S6_START_512, S6_END_512, 512, 3);
    }
    
    static void ex_4_3_chirp_512_hwt_d5_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, D5, p. 33", D5_START_512, D5_END_512, 512, 4);
    }
    
    static void ex_4_3_chirp_512_hwt_s5_p33() {
        multires_ex_4_3_hwt_p33("Ex. 4.3, HWT, S5, p. 33", S5_START_512, S5_END_512, 512, 4);
    }
    
    // ================== remove slow variations with CDF44
    static void signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT dwt, int num_iters, int range_start, int range_end) {
        double[] chirp_signal = generate_chirp(512);
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(chirp_signal, dwt, num_iters, range_start, range_end);
        display_signal(chirp_signal);
        chirp_signal = null;
        System.out.println("=========================");  
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_d8() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, D8_START_512, D8_END_512);
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_d7() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, D7_START_512, D7_END_512);
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_d6() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, D6_START_512, D6_END_512);
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_s6() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, S6_START_512, S6_END_512);
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_d5() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, D5_START_512, D5_END_512);
    }
    
    static void signal_subtraction_ex_4_3_chirp_512_p33_hwt_s5() {
        signal_subtraction_ex_4_3_chirp_512_p33(ApplyDWT.DWT.HWT, 4, S5_START_512, S5_END_512);
    }
    
    // ===================== remove slow variations with HWT
    static void remove_slow_vars_in_signal_ex_4_3_chirp_512_hwt_p33() {
        double[] chirp_signal = generate_chirp(512);
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(chirp_signal, ApplyDWT.DWT.HWT, 3, S6_START_512, S6_END_512);
        display_signal(chirp_signal);
        chirp_signal = null;
        System.out.println("=========================");  
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
    
    // ex 4.4, w/ noise, p. 34, CDF(4, 4)
    static void multires_ex_4_4_with_noise_cdf44_p34(String message, int range_start, int range_end, int signal_size, int num_scales) {
        multires_ex_4_4_with_noise_p34(message, ApplyDWT.DWT.CDF44, range_start, range_end, signal_size, num_scales);
    }
    // ex 4.4, w/ noise, p. 34, HWT
    static void multires_ex_4_4_with_noise_hwt_p34(String message, int range_start, int range_end, int signal_size, int num_scales) {
        multires_ex_4_4_with_noise_p34(message, ApplyDWT.DWT.HWT, range_start, range_end, signal_size, num_scales);
    }
    
    static void multires_ex_4_4_with_noise_p34(String message, ApplyDWT.DWT dwt, int range_start, int range_end, int signal_size, int num_scales) {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(signal_size, 500, 4.0);
        
        //CDF44.orderedDWTForNumIters(noisy_signal, num_scales, false);
        ApplyDWT.forwardDWTForNumIters(noisy_signal, dwt, num_scales);
        
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
        //CDF44.orderedInverseDWTForNumIters(signal_transform, num_scales, false);
        ApplyDWT.inverseDWTForNumIters(signal_transform, dwt, num_scales);
        display_signal(signal_transform);
        System.out.println("=========================");
    }
    
    // this is the same as above but uses ApplyDWT.multiresSignalReconstruct()
    static void multires_ex_4_4_with_noise_p34(String message, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int signal_size, int num_scales, int scale_num) {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(signal_size, 500, 4.0);
        
        ApplyDWT.forwardDWTForNumIters(noisy_signal, dwt, num_scales);
        System.out.println("=========================");
        System.out.println(message);
        display_signal(noisy_signal);
        System.out.println("Inversed Signal");
        System.out.println("=========================");
        double[] signal_transform = ApplyDWT.multiresSignalReconstructFromS(noisy_signal, dwt, coeff, num_scales, scale_num);
        display_signal(signal_transform);
        System.out.println("=========================");
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_d8_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D8, p. 34", D8_START_512, D8_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_d8_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, D8, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.D, 512, 4, 1);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_s8_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, S8, p. 34", S8_START_512, S8_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_s8_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, S8, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.S, 512, 4, 1);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_d8_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D8, p. 34", D8_START_512, D8_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_d8_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, D8, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.D, 512, 4, 1);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_d7_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, D7, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.D, 512, 4, 2);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_s8_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, S8, p. 34", S8_START_512, S8_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_s8_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, S8, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.S, 512, 4, 1);
    }
   
    static void multires_ex_4_4_512_with_noise_cdf44_d7_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D7, p. 34", D7_START_512, D7_END_512, 512, 4);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_s7_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, S7, p. 34", S7_START_512, S7_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_s7_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, S7, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.S, 512, 4, 2);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_d7_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D7, p. 34", D7_START_512, D7_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_d7_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, D7, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.D, 512, 4, 2);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_s7_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, S7, p. 34", S7_START_512, S7_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_s7_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, S7, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.S, 512, 4, 2);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_d6_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D6, p. 34", D6_START_512, D6_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_d6_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, D6, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.D, 512, 4, 3);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_d6_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D6, p. 34", D6_START_512, D6_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_d6_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, D6, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.D, 512, 4, 3);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_s6_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, S6, p. 34", S6_START_512, S6_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_s6_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, S6, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.S, 512, 4, 3);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_s6_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, S6, p. 34", S6_START_512, S6_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_s6_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, S6, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.S, 512, 4, 3);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_d5_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D5, p. 34", D5_START_512, D5_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_d5_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, D5, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.D, 512, 4, 4);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_d5_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D5, p. 34", D5_START_512, D5_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_d5_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, D5, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.D, 512, 4, 4);
    }
    
    static void multires_ex_4_4_512_with_noise_cdf44_s5_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, S5, p. 34", S5_START_512, S5_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_cdf44_s5_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, CDF44, S5, B, p. 34", ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.S, 512, 4, 4);
    }
    
    static void multires_ex_4_4_512_with_noise_hwt_s5_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, S5, p. 34", S5_START_512, S5_END_512, 512, 4);
    }
    
    // same as above but uses ApplyDWT.multiresSignalReconstruct(noisy_signal, dwt, coeff, num_scales, scale_num);
    static void multires_ex_4_4_512_with_noise_hwt_s5_p34_b() {
        multires_ex_4_4_with_noise_p34("Ex. 4.4, HWT, S5, B, p. 34", ApplyDWT.DWT.HWT, ApplyDWT.COEFF.S, 512, 4, 4);
    }
    
    // ====== START: ex 4.4 with a signal size = 1024
    static void multires_ex_4_4_1024_with_noise_cdf44_d10_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D10, p. 34", D10_START_1024, D10_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_d10_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D10, p. 34", D10_START_1024, D10_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_cdf44_d9_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D9, p. 34", D9_START_1024, D9_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_d9_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D9, p. 34", D9_START_1024, D9_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_cdf44_d8_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D9, p. 34", D8_START_1024, D8_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_d8_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D9, p. 34", D8_START_1024, D8_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_cdf44_d7_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D7, p. 34", D7_START_1024, D7_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_d7_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D7, p. 34", D7_START_1024, D7_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_cdf44_d6_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, D6, p. 34", D6_START_1024, D6_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_d6_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, D6, p. 34", D6_START_1024, D6_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_cdf44_s6_p34() {
        multires_ex_4_4_with_noise_cdf44_p34("Ex. 4.4, CDF44, S6, p. 34", S6_START_1024, S6_END_1024, 1024, 5);
    }
    
    static void multires_ex_4_4_1024_with_noise_hwt_s6_p34() {
        multires_ex_4_4_with_noise_hwt_p34("Ex. 4.4, HWT, S6, p. 34", S6_START_1024, S6_END_1024, 1024, 5);
    }
    
    // ====== END: ex 4.4 with a signal size = 1024
    
    // remove slow variations with CDF44
    static void ex_4_4_cdf44_remove_slow_variations_p34() {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(1024, 500, 4.0);
        
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(noisy_signal, ApplyDWT.DWT.CDF44, 5, S6_START_1024, S6_END_1024);
        
        display_signal(noisy_signal);
        System.out.println("=========================");  
    }
    
    // remove slow variations with HWT
    static void ex_4_4_hwt_remove_slow_variations_p34() {
        double[] noisy_signal = generate_signal_with_noise_ex_4_4_p34(1024, 500, 4.0);
        
        ApplyDWT.subtractSignalReconstructedFromRangeFromSignal(noisy_signal, ApplyDWT.DWT.HWT, 5, S6_START_1024, S6_END_1024);
        
        display_signal(noisy_signal);
        System.out.println("=========================");  
    }
    
    static void test_keep_top_N_percent(double[] signal, int range_start, int range_end, double percent) {
        
        Utils.displaySample(signal);
        
        keepTopNPercentInRange(signal, range_start, range_end, percent);
        
        Utils.displaySample(signal);
    }
    
    static void keep_slow_vars_in_fig_4_12_aux(double[] signal, ApplyDWT.DWT dwt, int num_iters) {
        //fig_4_12_p33();
        ApplyDWT.genericKeepSlowVarsInSignal(signal, dwt, num_iters);
        //display_signal(signal);
    }
    
    static void keep_slow_vars_in_fig_4_12_cdf44(int num_iters) {
        fig_4_12_p33();
        keep_slow_vars_in_fig_4_12_aux(sRangeFig_4_12_p33, ApplyDWT.DWT.CDF44, num_iters);
        System.out.println("======");
        display_signal(sRangeFig_4_12_p33);
    }
    
    static void keep_slow_vars_in_fig_4_12_hwt(int num_iters) {
        fig_4_12_p33();
        keep_slow_vars_in_fig_4_12_aux(sRangeFig_4_12_p33, ApplyDWT.DWT.HWT, num_iters);
        System.out.println("======");
        display_signal(sRangeFig_4_12_p33);
    }
    
    static void testCoeffsIndices(int signal_len, int num_scales) {
        for(int scale = 1; scale <= num_scales; scale *= 2) {
            System.out.println("S's end:\t"   + signal_len + ", " + scale + ": " + ApplyDWT.getSCoeffsEnd(signal_len, scale));
            System.out.println("D's start:\t" + signal_len + ", " + scale + ": " + ApplyDWT.getDCoeffsStart(signal_len, scale));
            System.out.println("D's end:\t"   + signal_len + ", " + scale + ": " + ApplyDWT.getDCoeffsEnd(signal_len, scale));
        }   
    }
    
    static void testDoesSignalHaveFastVars() {
        // this does not have many fast variations
        fig_4_1_p26();
        System.out.println("Fast Var Coeff: " + ApplyDWT.doesSignalContainFastVars(sRange, ApplyDWT.DWT.CDF44, 3));
        // this does have a lot of fast variations
        fig_4_12_p33();
        System.out.println("Fast Var Coeff: " + ApplyDWT.doesSignalContainFastVars(sRangeFig_4_12_p33, ApplyDWT.DWT.CDF44, 3));
    }
    
    static void computeSTDs() {
        // this does not have many fast variations
        //fig_4_1_p26();
        //System.out.println("fig_4_1_p26(): HWT");
        //ApplyDWT.computeSTDsForDWTMultiresCoeffs(sRange, ApplyDWT.DWT.HWT, 3, false);

        
        fig_4_1_p26b();
        System.out.println("fig_4_1_p26b(): HWT");
        ApplyDWT.computeSTDsForDWTMultiresCoeffs(sRange, ApplyDWT.DWT.HWT, 3, true);
        
        //fig_4_1_p26c();
        //System.out.println("fig_4_1_p26c(): HWT");
        //ApplyDWT.computeSTDsForDWTMultiresCoeffs(sRange, ApplyDWT.DWT.HWT, 3, false);
        
        //fig_4_1_p26d();
        //System.out.println("fig_4_1_p26d(): HWT");
        //ApplyDWT.computeSTDsForDWTMultiresCoeffs(sRange, ApplyDWT.DWT.HWT, 3, false);
        
        //fig_4_1_p26e();
        //System.out.println("fig_4_1_p26e(): HWT");
        //ApplyDWT.computeSTDsForDWTMultiresCoeffs(sRange, ApplyDWT.DWT.HWT, 3, false);
    }
    
    public static void main(String[] args) {
        computeSTDs();
    }
}
