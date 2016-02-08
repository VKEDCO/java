
package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.utils.Utils;
import org.vkedco.wavelets.ripples.CDF44;
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
        double[] signal_with_filtered_range = new double[signal.length];
        double[] signal_transform           = new double[signal.length];
        
        System.arraycopy(signal, 0, signal_transform, 0, signal_transform.length);
        // 1. apply forward DWT to signal
        forwardDWTForNumIters(signal_transform, dwt, num_iters, range_start, range_end);
        // 2. filter only the required signal range into slow variations
        filterSignalRange(signal_transform, signal_with_filtered_range, range_start, range_end);
        // 3. reconstruct signal from slow variations
        inverseDWTForNumIters(signal_with_filtered_range, dwt, num_iters);
        // 4. subtract the reconstructed signal from the original signal = fast variations
        subtractSignals(signal, signal_with_filtered_range);
        signal_with_filtered_range = null;
        signal_transform           = null;
    }
}
