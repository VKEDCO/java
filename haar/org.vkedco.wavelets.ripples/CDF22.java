

package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.utils.Utils;

/**
 **************************************
 * @author vladimir kulyukin
 **************************************
 */
public class CDF22 {
    
    static int get_mid_index(int j, int l) {
        final int i = ((1 << (j - l)) >> 1) - 1;
        return ( i < 0 ) ? 0: i;
    }
    
    static int compute_s_end_for_level(int j, int l) {
        return get_mid_index(j, l);
    }
    
    static int compute_d_start_for_level(int j, int l) {
        return get_mid_index(j, l)+1;
    }
    
    public static double compute_d(double[] signal, int j, int l, int n) {
        final int start = compute_d_start_for_level(j, l);
        final int end   = signal.length-1;
        
        final String dstr = "d_{j=" + j + "-" + l + "}^{l=" + l + "}(n=" + n + ")";
        System.out.println("**** " + dstr);
        final int two_n         = BoundaryHandler.mirror_wrapup(start, end, 2*n);
        final int two_n_plus_1  = BoundaryHandler.mirror_wrapup(start, end, 2*n+1);
        final int two_n_plus_2  = BoundaryHandler.mirror_wrapup(start, end, 2*n+2);
        
        System.out.println("start  = " + start);
        System.out.println("end    = " + end);
        System.out.println("2n     = " + two_n);
        System.out.println("2n + 1 = " + two_n_plus_1);
        System.out.println("2n + 2 = " + two_n_plus_2);
        
       
        System.out.println(dstr + "=sg[" + two_n_plus_1 + "]-" + "1/2(sg[" + two_n + "]+" + "sg[" + two_n_plus_2 + "])");
        final double d = signal[two_n_plus_1] - (signal[two_n] + signal[two_n_plus_2])/2.0;
        
        return d;
    }
    
    public static double compute_s(double[] signal, int j, int l, int n) {
        final int start = 0;
        final int end   = compute_s_end_for_level(j, l);
        
        final int two_n         = BoundaryHandler.mirror_wrapup(start, end, 2*n);
        System.out.println("s_{j=" + j + "-" + l + "}^{l=" + l + "}(n=" + n + ")");
        System.out.println("s_{j=" + j + "-" + l + "}^{l=" + l + "}(n=" + n + ")=" + "sg[" + two_n + "]");
        final double d1         = compute_d(signal, j-1, l-1, n-1);
        final double d2         = compute_d(signal, j-1, l-1, n);
        
        return signal[two_n] + (d1+d2)/4.0;
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
            for(int s_pos = 0; s_pos <= s_end; s_pos++) {
                signal[s_pos] = compute_s(signal, j, level, s_pos);
            }
            for(int d_pos = d_start; d_pos < len; d_pos++) {
                signal[d_pos] = compute_d(signal, j, level, d_pos);
            }
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
    
    public static void test(double[] signal) {
        final int num_iters = (int)(Math.log(signal.length)/Math.log(2.0));
        CDF22.orderedForwardTransformForNumIters(signal, num_iters);
        Utils.displaySample(signal);
    }
    
    public static void main(String[] args) {
        double[] signal1 = {2, 4};
        double[] signal2 = {0, 0};
        double[] signal3 = {1, 2, 3, 4};
        double[] signal4 = {1, 1, 1, 1};
        //test(signal1);
        //test(signal2);
        test(signal3);
        //test(signal4);
        //System.out.println(compute_s_end_for_level(1, 1));
        //System.out.println(compute_d_start_for_level(1, 1));
    }
    
    
}
