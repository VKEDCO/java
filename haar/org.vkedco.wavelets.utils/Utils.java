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
    
    public static boolean areSignalsEqual(double[] output1, double[] output2) {
        final int n = output1.length;
        if ( output2.length != n ) return false;
        for(int i = 0; i < n; i++) {
            if ( output1[i] != output2[i] ) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean areSignalsEqual(double[] output1, double[] output2, double thresh) {
        final int n = output1.length;
        if ( output2.length != n ) return false;
        for(int i = 0; i < n; i++) {
            if ( Math.abs(output1[i] - output2[i]) > thresh ) {
                return false;
            }
        }
        return true;
    }
    
    public static double[] copySignal(double[] sig) {
        double[] sigCopy = new double[sig.length];
        System.arraycopy(sig, 0, sigCopy, 0, sig.length);
        return sigCopy;
    }
    
}
