
package org.vkedco.wavelets.beepi;

import org.vkedco.wavelets.ripples.ApplyDWT;
import org.vkedco.wavelets.utils.Utils;

/**
 *
 * @author vladimir kulyukin
 */
public class Buzz {
    
    static void display_signal(double[] signal) {
        for(double d: signal) {
            System.out.println(d);
        }
    }
    
    public static void processBuzzWavFile(String inpath, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int num_scales, int scale_num) {
        double[] ds = Utils.readInPrimDoublesFromLineFile(inpath);
        double[] sub_ds = Utils.largestSubsignalOfPowerOf2(ds);
        ds = null;
        processBuzzWavFileAux(sub_ds, dwt, coeff, sub_ds.length, num_scales, scale_num);
    }
    
    static void processBuzzWavFileAux(double[] signal, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int signal_size, int num_scales, int scale_num) {
        ApplyDWT.forwardDWTForNumIters(signal, dwt, num_scales);
        double[] signal_transform = ApplyDWT.multiresSignalReconstruct(signal, dwt, coeff, num_scales, scale_num);
        display_signal(signal_transform);
    }
    
    public static void main(String[] args) {
        processBuzzWavFile("C://Users//vladimir//research//audio_files//wav//beepi//garland_07jul15_04aug15//2015-07-20_18-51-10_44100_1000.txt",
                ApplyDWT.DWT.CDF44, ApplyDWT.COEFF.S, 5, 1);
    }
}
