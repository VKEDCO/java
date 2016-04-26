package org.vkedco.wavelets.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void display2DArray(double[][] ary, int num_rows, int num_cols) {
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                System.out.print(ary[r][c] + " ");
            }
            System.out.println();
        }
    }
    
    public static double[] readInPrimDoublesFromLineFile(String inpath) {
        ArrayList<Double> nonPrimDoubles = new ArrayList<>();
        double[] primDoubles = null;
        
        try {
            BufferedReader bufRdr = new BufferedReader(new FileReader(new File(inpath) ) );
            String line = null;
            while ( (line = bufRdr.readLine() ) != null ) {
                nonPrimDoubles.add(Double.valueOf(line));
            }
            
            primDoubles = new double[nonPrimDoubles.size()];
            int i = 0;
            for(Double d: nonPrimDoubles) {
                primDoubles[i++] = d;
            }
            nonPrimDoubles.clear();
            nonPrimDoubles = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return primDoubles;
    }
    
    public static void main(String[] args) {
        double[] signal_1 = {1};
        double[] signal_2 = {1, 2};
        double[] signal_3 = {1, 2, 3};
        double[] signal_4 = {1, 2, 3, 4};
        double[] signal_11 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        
        double[] signal_1c  = largestSubsignalOfPowerOf2(signal_1);
        double[] signal_2c  = largestSubsignalOfPowerOf2(signal_2);
        double[] signal_3c  = largestSubsignalOfPowerOf2(signal_3);
        double[] signal_4c  = largestSubsignalOfPowerOf2(signal_4);
        double[] signal_11c = largestSubsignalOfPowerOf2(signal_11);
        
        displaySample(signal_1c);
        displaySample(signal_2c);
        displaySample(signal_3c);
        displaySample(signal_4c);
        displaySample(signal_11c);
        
        System.out.println(largestPowerOf2NoGreaterThan(1323000));
    }
    
    
}
