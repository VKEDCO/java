package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.haar.OneDHaar;

/**
 *****************************
 * @author vladimir kulyukin
 *****************************
 */
public class ApplyDWT {
    public static enum DWT   { CDF44, HWT };
    public static enum COEFF { S, D };
    
    public static void forwardDWTForNumIters(double[] signal, ApplyDWT.DWT dwt, int num_iters, int range_start, int range_end) {
        if ( dwt == ApplyDWT.DWT.CDF44 ) {
            CDF44.orderedDWTForNumIters(signal, num_iters, false);
        }
        else if ( dwt == ApplyDWT.DWT.HWT ) {
            OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(signal, num_iters);
        }
        else {
            throw new IllegalArgumentException("Illegal dwt value: " + dwt);
        }
    }
    
    public static void forwardDWTForNumIters(double[] signal, ApplyDWT.DWT dwt, int num_iters) {
        if ( dwt == ApplyDWT.DWT.CDF44 ) {
            CDF44.orderedDWTForNumIters(signal, num_iters, false);
        }
        else if ( dwt == ApplyDWT.DWT.HWT ) {
            OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(signal, num_iters);
        }
        else {
            throw new IllegalArgumentException("Illegal dwt value: " + dwt);
        }
    }
    
    public static void inverseDWTForNumIters(double[] signal, ApplyDWT.DWT dwt, int num_iters) {
        if ( dwt == ApplyDWT.DWT.CDF44 ) {
            CDF44.orderedInverseDWTForNumIters(signal, num_iters, false);
        }
        else if ( dwt == ApplyDWT.DWT.HWT ) {
            OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        }
        else {
            throw new IllegalArgumentException("Illegal dwt value: " + dwt);
        }
    }
    
    public static void subtractSignals(double[] signal0, double[] signal1) {
        for(int i = 0; i < signal0.length; i++) {
            signal0[i] -= signal1[i];
        }
    }
    
    public static void filterSignalRange(double[] signal, double[] filtered_signal, int range_start, int range_end) {
        for(int i = 0; i < filtered_signal.length; i++) {
            if ( i >= range_start && i <= range_end ) {
                filtered_signal[i] = signal[i];
            }
            else {
                filtered_signal[i] = 0;
            }
        }
    }
    
    public static void subtractSignalReconstructedFromRangeFromSignal(double[] signal, ApplyDWT.DWT dwt, int num_iters, int range_start, int range_end) {
        // signal_transform holds the DWT transform of signal_tranform of ApplyDWT.DWT
        double[] signal_transform           = new double[signal.length];
        // - signal_with_filtered_range holds the slow variations filtered from the signal
        // transform in the required range [range_start, range_end];
        // - the reconstructed signal is reconstructed from filtered_range_from_signal_transform.
        double[] filtered_range_from_signal_transform = new double[signal.length];
        
        // 0. copy signal into signal_transform
        System.arraycopy(signal, 0, signal_transform, 0, signal_transform.length);
        // 1. apply forward DWT to signal
        forwardDWTForNumIters(signal_transform, dwt, num_iters, range_start, range_end);
        // 2. filter the required range from signal transform into filtered_range_from_signal_transform;
        //    filtered_range_from_signal_transform contains slow variations.
        filterSignalRange(signal_transform, filtered_range_from_signal_transform, range_start, range_end);
        // 3. reconstruct signal from slow variations for the same number of iterations
        inverseDWTForNumIters(filtered_range_from_signal_transform, dwt, num_iters);
        // 4. subtract the reconstructed signal from the original signal to keep the fast variations;
        //    fast variations = original signal - signal reconstructed from slow variations.
        subtractSignals(signal, filtered_range_from_signal_transform);
        filtered_range_from_signal_transform    = null;
        signal_transform                        = null;
    }
    
    public static void keepFastVariationsInSignal(double[] signal, ApplyDWT.DWT dwt, int num_iters, int range_start, int range_end) {
        // signal_transform holds the DWT transform of signal_tranform of ApplyDWT.DWT
        double[] signal_transform           = new double[signal.length];
        // - signal_with_filtered_range holds the slow variations filtered from the signal
        // transform in the required range [range_start, range_end];
        // - the reconstructed signal is reconstructed from filtered_range_from_signal_transform.
        double[] filtered_range_from_signal_transform = new double[signal.length];
        
        // 0. copy signal into signal_transform
        System.arraycopy(signal, 0, signal_transform, 0, signal_transform.length);
        // 1. apply forward DWT to signal
        forwardDWTForNumIters(signal_transform, dwt, num_iters, range_start, range_end);
        // 2. filter the required range from signal transform into filtered_range_from_signal_transform;
        //    filtered_range_from_signal_transform contains slow variations.
        filterSignalRange(signal_transform, filtered_range_from_signal_transform, range_start, range_end);
        // 3. reconstruct signal from slow variations for the same number of iterations
        inverseDWTForNumIters(filtered_range_from_signal_transform, dwt, num_iters);
        // 4. subtract the reconstructed signal from the original signal to keep the fast variations;
        //    fast variations = original signal - signal reconstructed from slow variations.
        subtractSignals(signal, filtered_range_from_signal_transform);
        filtered_range_from_signal_transform = null;
        signal_transform           = null;
    }
    
    // keep the fast variations. Given signal's size, n, and number of scales, num_scales, apply DWT for a given
    // number of scales. filter the s part of the transform. reconstruct the signal from the filtered s values.
    // subtract the reconstructed signal from the original signal
    public static void genericKeepFastVarsInSignal(double[] signal, ApplyDWT.DWT dwt, int num_iters) {
        // signal_transform holds the DWT transform of signal_tranform of ApplyDWT.DWT
        double[] signal_transform           = new double[signal.length];
        // - signal_with_filtered_range holds the slow variations filtered from the signal
        // transform in the required range [range_start, range_end];
        // - the reconstructed signal is reconstructed from filtered_range_from_signal_transform.
        double[] filtered_range_from_signal_transform = new double[signal.length];
        
        // 0. copy signal into signal_transform.
        System.arraycopy(signal, 0, signal_transform, 0, signal_transform.length);
        // 1. apply forward DWT to signal.
        forwardDWTForNumIters(signal_transform, dwt, num_iters);
        // 2. filter the required range from signal transform into filtered_range_from_signal_transform;
        //    filtered_range_from_signal_transform contains slow variations.
        final int range_end = (signal.length/((int)Math.pow(2, num_iters)) - 1);
        //System.out.println("range end == " + range_end);
        filterSignalRange(signal_transform, filtered_range_from_signal_transform, 0, range_end);
        // 3. reconstruct signal from slow variations for the same number of iterations
        inverseDWTForNumIters(filtered_range_from_signal_transform, dwt, num_iters);
        // 4. subtract the reconstructed signal from the original signal to keep the fast variations;
        //    fast variations = original signal - signal reconstructed from slow variations.
        subtractSignals(signal, filtered_range_from_signal_transform);
        filtered_range_from_signal_transform = null;
        signal_transform = null;
    }
    
    // apply genericKeepFastVarsInSignal for a number of iterations
    // subtract the fast iterations from the slow iterations
    public static void genericKeepSlowVarsInSignal(double[] signal, ApplyDWT.DWT dwt, int num_iters) {
        double[] signal_copy = new double[signal.length];
        System.arraycopy(signal, 0, signal_copy, 0, signal.length);
        genericKeepFastVarsInSignal(signal_copy, dwt, num_iters);
        //System.out.println("N == " + signal.length);
        ApplyDWT.subtractSignals(signal, signal_copy);
    }
    
    public static int getSCoeffsEnd(int signal_len, int curr_scale) {
        return (int)(signal_len/Math.pow(2.0, curr_scale))-1;
    }
    
    public static int getDCoeffsStart(int signal_len, int curr_scale) {
        return (int)(signal_len/Math.pow(2.0, curr_scale));
    }
    
    public static int getDCoeffsEnd(int signal_len, int curr_scale) {
        return 2*getDCoeffsStart(signal_len, curr_scale) - 1;
    }
    
    // double[] multiresSignalReconstruct(double[] signal_transform, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int scale_num)
    // 1. compute the range_start and range_end from coeff, signal size, and scale_num
    // 2. set to zeros stuff outside of the range
    // 3. reconstruct the signal and return
    
    public static double[] multiresSignalReconstruct(double[] signal_transform, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int num_scales, int scale_num) {
        final int signal_size = signal_transform.length;
        int range_start = 0;
        int range_end = 0;
        System.out.println("Signal_size = " + signal_size);
        
        switch ( coeff ) {
            case S: 
                range_end = ApplyDWT.getSCoeffsEnd(signal_size, scale_num);
                break;
            case D:
                range_start = ApplyDWT.getDCoeffsStart(signal_size, scale_num);
                range_end = ApplyDWT.getDCoeffsEnd(signal_size, scale_num);
                System.out.println("D's start = " + range_start);
                System.out.println("D's end   = " + range_end);
                break;
            default:
                throw new IllegalArgumentException("multiresSignalReconstruct: unknown coeff type");
        }
        
        double[] reconstructed_signal = new double[signal_size];
        System.arraycopy(signal_transform, 0, reconstructed_signal, 0, signal_size);
        for(int i = 0; i < signal_size; i++) {
            if ( i < range_start || i > range_end ) {
                reconstructed_signal[i] = 0;
            }
        }
        
        ApplyDWT.inverseDWTForNumIters(reconstructed_signal, dwt, num_scales);
        
        return reconstructed_signal;
    }
    
    /*
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
    */
}
