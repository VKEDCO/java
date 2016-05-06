package org.vkedco.wavelets.cv;

import org.vkedco.wavelets.ripples.ApplyDWT;
import org.vkedco.wavelets.utils.Utils;

/**
 *
 * @author vladimir kulyukin
 */
public class BeePiCV {
    
    // 64 - [0, 1]; 65 - [2,3]; 66 - [4, 5]; 67 - [6, 7]; 68 - [8, 9];
    // 69 - [10, 11]; 70 - [12, 13]
    public static int getIndexOfFirstValueSTDStepsFromMeanInRange(double[] signal, int start, int end, int num_steps) {
        double mean = Utils.computeMeanInRange(signal, start, end);
        double std = Utils.computeCorrectedSTDInRange(signal, start, end);
        System.out.println("mean = " + mean);
        System.out.println("std  = " + std);
        for(int i = start; i <= end; i++) {
            if ( (signal[i] < (mean - num_steps*std)) || (signal[i] > (mean + num_steps*std)) ) {
                return i;
            }
        }
        return -1;
    }
    
    // start, end, index returned by the previous message
    // 2*(index - start)
    public static void main(String[] args) {
        double[] sig  = Utils.readInPrimDoublesFromLineFile("C:\\Users\\vladimir\\research\\BeePI\\2015-05-07_16-44-02_ver_0_20_fd.txt");
        double[] sig2 = Utils.largestSubsignalOfPowerOf2(sig);
        System.out.println(sig.length);
        System.out.println(sig2.length);
        ApplyDWT.forwardDWTForNumIters(sig2, ApplyDWT.DWT.HWT, 1);
        int dstart = ApplyDWT.getDCoeffsStart(sig2.length, 1);
        int dend   = ApplyDWT.getDCoeffsEnd(sig2.length, 1);
        System.out.println("dstart = " + dstart);
        System.out.println("dend   = " + dend);
        Utils.displaySignalRange(sig2, dstart, dend);
        System.out.println(Utils.computeMeanInRange(sig2, dstart, dend));
        System.out.println(Utils.computeCorrectedSTDInRange(sig2, dstart, dend));
        int index = BeePiCV.getIndexOfFirstValueSTDStepsFromMeanInRange(sig2, dstart, dend, 1);
        System.out.println("end of grass row = " + 2*(index-dstart));
    }
    
}
