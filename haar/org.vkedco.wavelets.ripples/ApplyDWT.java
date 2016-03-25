package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.haar.OneDHaar;

/**
 *****************************
 * @author vladimir kulyukin
 *****************************
 */
public class ApplyDWT {
    public static enum DWT { CDF44, HWT };
    
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
        filtered_range_from_signal_transform = null;
        signal_transform           = null;
    }
    
    // to keep the fast variations. Given signal's size, n, and number of scales, num_scales, apply DWT for a given
    // number of scales. filter the s part of the transform. reconstruct the signal from the filtered s values.
    // subtract the reconstructed signal from the original signal
}
