package org.vkedco.wavelets.ripples;

/**
 **************************************
 * @author vladimir kulyukin
 **************************************
 */
public class CDF22 {
    
    static int get_mid_index(int j, int l) {
        return ((1 << (j - l)) >> 1) - 1;
    }
    
    static int get_s_end_for_level(int j, int l) {
        return get_mid_index(j, l);
    }
    
    static int get_d_start_for_level(int j, int l) {
        return get_mid_index(j, l)+1;
    }
    
    public static double compute_d(double[] signal, int j, int l, int n) {
        final int start = get_d_start_for_level(j, l);
        final int end   = signal.length-1;
        
        final int two_n         = BoundaryHandler.mirror_wrapup(start, end, 2*n);
        final int two_n_plus_1  = BoundaryHandler.mirror_wrapup(start, end, 2*n+1);
        final int two_n_plus_2  = BoundaryHandler.mirror_wrapup(start, end, 2*n+2);
        
        return signal[two_n_plus_1] - (signal[two_n] + signal[two_n_plus_2])/2.0;
    }
    
    public static double compute_s(double[] signal, int j, int l, int n) {
        final int start = 0;
        final int end   = get_s_end_for_level(j, l);
        
        final int two_n         = BoundaryHandler.mirror_wrapup(start, end, 2*n);
        final double d1         = compute_d(signal, j-1, l-1, n-1);
        final double d2         = compute_d(signal, j-1, l-1, n);
        return signal[two_n] + (d1+d2)/4.0;
    }
    
    public static void main(String[] args) {
        System.out.println(get_mid_index(4, 0));
    }
    
    
}
