
package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.utils.Utils;

/**
 **************************************
 * @author vladimir kulyukin
 **************************************
 */
public class CDF22 {
    
    // 
    static int get_mid_index(int j, int l) {
        if ( l > j ) return -1;
        return ((1 << j) >> l) - 1;
    }
    
    static int compute_s_end_for_level(int j, int l) {
        if ( l == 0 )
            return (1 << j)-1;
        else
            return get_mid_index(j, l);
    }
    
    static int compute_d_start_for_level(int j, int l) {
        if ( l == 0 ) return -1;
        return compute_s_end_for_level(j, l) + 1;
    }
    
    static int compute_d_end_for_level(int j, int l) {
        if ( l == 0 ) return -1;
        return compute_s_end_for_level(j, l) + (1 << (j-l));
    }
    
    static final double D_NORMALIZER = 1.0/Math.sqrt(2.0);
    static final double S_NORMALIZER = Math.sqrt(2.0);
    private static boolean NORMALIZER_FLAG = true;
    
    public static void set_normalizer_flag(boolean flag_val) {
        NORMALIZER_FLAG = flag_val;
    }
    
    public static double compute_d(double[] signal, int i, int j, int l) {
        if ( l == 0 ) return 0;
        
        int prev_s_end;
        int s_two_i_plus_1;
        int s_two_i_plus_2;
        int s_two_i;
        
        double sig_two_i_plus_1 = 0;
        double sig_two_i_plus_2 = 0;
        double sig_two_i        = 0;
        
        //if ( l - 1 > 0 ) {
            prev_s_end = compute_s_end_for_level(j, l-1);
            System.out.println("prev_s_end = " + prev_s_end);
            s_two_i_plus_1 = DWTBoundaryHandler.mirror_wrapup(0, prev_s_end, 2*i + 1);
            s_two_i_plus_2 = DWTBoundaryHandler.mirror_wrapup(0, prev_s_end, 2*i + 2);
            s_two_i        = DWTBoundaryHandler.mirror_wrapup(0, prev_s_end, 2*i);
            System.out.println("2i+1 index = " + s_two_i_plus_1);
            System.out.println("2i+2 index = " + s_two_i_plus_2);
            System.out.println("2i   index = " + s_two_i);
            sig_two_i_plus_1 = signal[s_two_i_plus_1];
            sig_two_i_plus_2 = signal[s_two_i_plus_2];
            sig_two_i        = signal[s_two_i];
            System.out.println("2i+1 value = " + sig_two_i_plus_1);
            System.out.println("2i+2 value = " + sig_two_i_plus_2);
            System.out.println("2i   value = " + sig_two_i);
            System.out.println("(2i + (2i+2)) = " + (sig_two_i + sig_two_i_plus_2));
        //}
        //else {
        //    sig_two_i = 0;
        //    sig_two_i_plus_2 = 0;
        //}
            
        double d = sig_two_i_plus_1 - (sig_two_i + sig_two_i_plus_2)/2.0;
        d = NORMALIZER_FLAG ? D_NORMALIZER * d : d;
        System.out.println("d    value = " + d);
        return d;
    }
    
    // do not change j it is a constant 2^j is the signal
    // I must allocate memory
    public static double compute_s(double[] signal, int i, int j, int l) {
        System.out.print("compute_s("); Utils.displaySample(signal);
        System.out.println(", " + i + ", " + j + ", " + l);
        //int end   = 0;
        if ( l == 0 ) {
            return signal[DWTBoundaryHandler.mirror_wrapup(0, (1 << j)-1, i)];
        }
        else {
            //end = compute_s_end_for_level(j, l);
        }
        
        final int prev_s_end = compute_s_end_for_level(j, l-1);
        final int s_two_x_i  = DWTBoundaryHandler.mirror_wrapup(0, prev_s_end, 2*i);
        System.out.println("prev_s_end = " + prev_s_end);
        System.out.println("s_two_x_i  = " + s_two_x_i);
        double sig_two_x_i = signal[s_two_x_i];
        System.out.println("sig_two_x_i = " + sig_two_x_i);
        double d1 = 0; double d2 = 0; 
        if ( l > 1 ) {
            //final int prev_d_start = prev_s_end + 1;
            final int prev_d_start = compute_d_start_for_level(j, l-1);
            final int prev_d_end   = compute_d_end_for_level(j, l-1);
            System.out.println("prev_d_start = " + prev_d_start);
            // This is an error: 
            final int d_i_minus_1 = DWTBoundaryHandler.mirror_wrapup(prev_d_start, prev_d_end, i-1);
            System.out.println("d_i_minus_1 index = " + d_i_minus_1);
            final int d_i         = DWTBoundaryHandler.mirror_wrapup(prev_d_start, prev_d_end, i);
            System.out.println("d_i index         = " + d_i);
            d1  = signal[d_i_minus_1];
            d2  = signal[d_i];
            System.out.println("sig_two_x_i = " + sig_two_x_i);
            System.out.println("d1 = " + d1);
            System.out.println("d2 = " + d2);
        }
        double s = sig_two_x_i + (d1 + d2)/4.0;
        s = NORMALIZER_FLAG ? S_NORMALIZER * s: s;
        return s;
    }
    
    public static void orderedForwardTransformForNumIters(double[] signal, int num_iters) {
        final int len = signal.length;
        if ( !Utils.isPowerOf2(len) ) return;
        final int j = (int)(Math.log(signal.length)/Math.log(2.0));
        int s_end; int d_start;
        for(int level = 1; level <= num_iters; level++) {
            s_end   = compute_s_end_for_level(j, level);
            d_start = s_end + 1;
            System.out.println("j       = " + j);
            System.out.println("s_end   = " + s_end);
            System.out.println("d_start = " + d_start);
            System.out.println("level   = " + level);
            double temp_s[] = new double[s_end+1];
            for(int s_pos = 0; s_pos <= s_end; s_pos++) {
                System.out.println("compute_s for pos " + s_pos + " at level " + level);
                double cs = compute_s(signal, s_pos, j, level);
                System.out.println("computed cs == " + cs);
                temp_s[s_pos] = cs;
                System.out.println("temp_s[" + s_pos + "] = " + temp_s[s_pos]);
            }
            double temp_d[] = new double[s_end+1];
            final int d_end = compute_d_end_for_level(j, level);
            int temp_i = 0;
            // D_START
            for(int d_pos = d_start; d_pos <= d_end; d_pos++) {
                System.out.println("compute_d for pos " + (d_pos-d_start) + " at level " + level);
                temp_d[temp_i] = compute_d(signal, d_pos-d_start, j, level);
                System.out.println("temp_d[" + temp_i + "] = " + temp_d[temp_i]);
                temp_i++;
            }
            System.out.print("temp_s: "); Utils.displaySample(temp_s);
            System.out.print("temp_d: "); Utils.displaySample(temp_d);
            for(int i = 0; i <= s_end; i++) signal[i]         = temp_s[i];
            for(int i = 0; i <= s_end; i++) signal[d_start+i] = temp_d[i];
            System.out.print("level = " + level + " "); Utils.displaySample(signal);
        }
    }
    
    public static void test_01() {
        double[] signal = {2, 4};
        CDF22.orderedForwardTransformForNumIters(signal, 1);
        Utils.displaySample(signal);
    }
    
    public static void test_02() {
        double[] signal = {2, 4, 8, 10};
        CDF22.orderedForwardTransformForNumIters(signal, 2);
        Utils.displaySample(signal);
    }
    
    public static void test_cdf22(double[] signal, boolean norm_flag_val) {
        CDF22.set_normalizer_flag(norm_flag_val);
        final int num_iters = (int)(Math.log(signal.length)/Math.log(2.0));
        CDF22.orderedForwardTransformForNumIters(signal, num_iters);
        Utils.displaySample(signal);
    }
    
    public static void test_compute_s_end_for_level(int j) {
        for(int l = 0; l <= j; l++) {
            System.out.println("l = " + l + " --> s_end = " + compute_s_end_for_level(j, l));
        }
    }
    
    public static void test_compute_d_start_for_level(int j) {
        for(int l = 0; l <= j; l++) {
            System.out.println("l = " + l + " --> d_start = " + compute_d_start_for_level(j, l));
        }
    }
    
    public static void test_starts_and_ends(int j) {
        for(int lev = 0; lev <= j; lev++) {
            int s_start = 0;
            int s_end = compute_s_end_for_level(j, lev);
            int d_start = compute_d_start_for_level(j, lev);
            int d_end   = compute_d_end_for_level(j, lev);
            System.out.println("level " + lev + " s_start = " + s_start + " s_end = " + s_end + " d_start = "  + d_start + " d_end = " + d_end);
        }
    }
    
    /*******
     * 
compute_s for pos 0 at level 3
compute_s(Sample: 1.0 3.625 -1.5 0.0 0.0 -1.5 0.0 0.0 
, 0, 3, 3
mirror_wrapup(0, 1, 0)
prev_s_end = 1
s_two_x_i  = 0
sig_two_x_i = 1.0
prev_d_start = 2
mirror_wrapup(2, 7, -1)
d_i_minus_1 index = 4
mirror_wrapup(2, 7, 0)
d_i index         = 2
sig_two_x_i = 1.0
d1 = 0.0
d2 = -1.5
computed cs == 0.625
temp_s[0] = 0.625
compute_d for pos 0 at level 3
prev_s_end = 1
mirror_wrapup(0, 1, 1)
mirror_wrapup(0, 1, 2)
mirror_wrapup(0, 1, 0)
2i+1 index = 1
2i+2 index = 1
2i   index = 0
2i+1 value = 3.625
2i+2 value = 3.625
2i   value = 1.0
(2i + (2i+2)) = 4.625
d    value = 1.3125
temp_d[0] = 1.3125
temp_s: Sample: 0.625 
temp_d: Sample: 1.3125 
level = 3 Sample: 0.625 1.3125 -1.5 0.0 0.0 -1.5 0.0 0.0 
Sample: 0.625 1.3125 -1.5 0.0 0.0 -1.5 0.0 0.0 
     */
    
    public static void main(String[] args) {
        double[] signal_1a = {1, 2};
        double[] signal_1b = {1, 4};
        double[] signal_1c = {2, 1};
        double[] signal_1d = {4, 1};
        double[] signal_1e = {4, 4};
        double[] signal_2a = {1, 2, 3, 4};
        double[] signal_2b = {4, 3, 2, 1};
        double[] signal_2c = {2, 2, 5, 5};
        double[] signal_3a = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] signal_3b = {8, 7, 6, 5, 4, 3, 2, 1};
        double[] signal_3c = {1, 1, 1, 1, 4, 4, 4, 4};
        double[] signal6 = {15, 13, 11, 9, 7, 5, 3, 1};
        //test_starts_and_ends(2);
        //test_compute_s_end_for_level(3);
        //test_compute_d_start_for_level(3);
        
        test_cdf22(signal_3c, false);
        //test_compute_s(false);
        
        //test_sl_sr_dl_dr(1, 0);
        //test_sl_sr_dl_dr(1, 1);
        
        //double temp[] = {2.0, 5.0, -1.5, 0.0};
        //System.out.println(compute_s(temp, 0, 2, 2));
        //System.out.println(compute_d(signal2, 0, 0, 1));
        //System.out.println(compute_s(signal2, 0, 0, 1));
        //test(signal2);
        //System.out.println(compute_s(signal2, 2, 1, 0));
        //test(signal3);
        //test(signal4);
        //test(signal5);
        //test(signal6);
        //test(signal3);
        //test(signal5);
        //System.out.println(compute_s_end_for_level(1, 1));
        //System.out.println(compute_d_start_for_level(1, 1));
        //System.out.println(compute_s_end_for_level(3, 0));
    }
    
    static void test_compute_s(boolean flag) {
        set_normalizer_flag(flag);
        double s[] = {1.0, 3.625, -1.5, 0.0, 0.0, -1.5, 0.0, 0.0};
        double cs = compute_s(s, 0, 3, 3);
        System.out.println("computed cs == " + cs);
        //System.out.println(compute_s(s, 0, 3, 3));
    }
    
    static void test_sl_sr_dl_dr(int j, int l) {
        System.out.println("sl(" + j + "," + l + ") = " + 0);
        System.out.println("sr(" + j + "," + l + ") = " + compute_s_end_for_level(j, l));
        System.out.println("dl(" + j + "," + l + ") = " + compute_d_start_for_level(j, l));
        System.out.println("dr(" + j + "," + l + ") = " + compute_d_end_for_level(j, l));
    }
    
    
}
