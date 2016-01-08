package org.vkedco.wavelets.utils;

/**
 *************************************************************** 
 * @author vladimir kulyukin
 ***************************************************************
 */

public class Utils {
    
    public static void displaySample(double[] sample) {
        System.out.print("Sample: ");
        for (int i = 0; i < sample.length; i++) {
            System.out.print(sample[i] + " ");
        }
        System.out.println();
    }
    
    public static void displaySample(int[] sample) {
        System.out.print("Sample: ");
        for (int i = 0; i < sample.length; i++) {
            System.out.print(sample[i] + " ");
        }
        System.out.println();
    }

    public static boolean isPowerOf2(int n) {
        if (n < 1) {
            return false;
        } else {
            double p_of_2 = (Math.log(n) / Math.log(2));
            return Math.abs(p_of_2 - (int) p_of_2) == 0;
        }
    }
    
    // if n = 2^x; this method returns x
    public static int powVal(int n) {
        return (int)(Math.log(n)/Math.log(2));
    }
    
    public static int largestPowerOf2NoGreaterThan(int i) {
        if ( isPowerOf2(i) )
            return i;
        else {
            int curr = i-1;
            while ( curr > 0 ) {
                if ( isPowerOf2(curr) ) {
                    return curr;
                }
                else {
                    --curr;
                }
            }
            return 0;
        }
    }
    
    public static double[] largestSubsignalOfPowerOf2(double[] signal) {
        if ( isPowerOf2(signal.length) )
            return signal;
        else {
            int i = largestPowerOf2NoGreaterThan(signal.length);
            if ( i == 0 ) return null;
            double[] subsignal = new double[i];
            System.arraycopy(signal, 0, subsignal, 0, i);
            return subsignal;
        }
    }
}
