package org.vkedco.wavelets.tests;

import java.text.DecimalFormat;
import org.vkedco.wavelets.haar.OneDHaar;

/**
 *****************************
 * @author Vladimir Kulyukin
 *****************************
 */

public class RipplesInMathCh02 {
    
    static DecimalFormat df = new DecimalFormat("0.00");
    static final double[] signal_p7 = {56, 40, 8, 24, 48, 48, 40, 16};
    
    static void page7() {
        computeTable_2_1_p7(signal_p7);
    }
    
    static void page8() {
        computeTable_2_2_p8(signal_p7, 4);
    }
    
    static void printHyphenLineSeparator(int len) {
        final String hyphen = "-";
        for(int i = 0; i < len; i++)
            System.out.print(hyphen);
        System.out.println();
    }
    
    static void printSignalOnOneLine(double[] signal) {
        for(double d: signal)
            System.out.print(df.format(d) + "\t");
        System.out.println();
    }
    
    static void computeTable_2_1_p7(double[] signal) {
        System.out.println("Table 2.1, p. 7, Ch. 2, \"Ripples in Mathematics\"");
        final int n = signal.length;
        
        double[] signalFor1Iter  = new double[n];
        double[] signalFor2Iters = new double[n];
        double[] signalFor3Iters = new double[n];
        
        System.arraycopy(signal, 0, signalFor1Iter,  0, n);
        System.arraycopy(signal, 0, signalFor2Iters, 0, n);
        System.arraycopy(signal, 0, signalFor3Iters, 0, n);
        
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor1Iter,  1);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor2Iters, 2);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor3Iters, 3);
        
        int len = 70;
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signal);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor1Iter);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor2Iters);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor3Iters);
        printHyphenLineSeparator(len); 
    }
    
    static void computeTable_2_2_p8(double[] signal, double thresh) {
        System.out.println("Table 2.2, p. 7, Ch. 2, \"Ripples in Mathematics\"");
        
        final int n = signal.length;
        double[] signalFor1InverseIter  = new double[n];
        double[] signalFor2InverseIters = new double[n];
        double[] signalFor3InverseIters = new double[n];
        double[] signalFor3Iters        = new double[n];
        
        System.arraycopy(signal, 0, signalFor3Iters, 0, n);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor3Iters, 3);
        
        System.arraycopy(signalFor3Iters, 0, signalFor3InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor3InverseIters, 3, 4); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor2InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor2InverseIters, 2, 4); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor1InverseIter,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor1InverseIter, 1, 4); 
       
        OneDHaar.thresholdSignal(signalFor3Iters, thresh);
        
        int len = 70;
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor3InverseIters);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor2InverseIters);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor1InverseIter);
        printHyphenLineSeparator(len);
        printSignalOnOneLine(signalFor3Iters);
        printHyphenLineSeparator(len);
    }
    
    public static void main(String[] args) {
        page7();
        page8();
    }
    
}
