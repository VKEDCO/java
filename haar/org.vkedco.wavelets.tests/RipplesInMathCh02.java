package org.vkedco.wavelets.tests;

import java.text.DecimalFormat;
import org.vkedco.wavelets.haar.OneDHaar;

/**
 *******************************************************
 * Programmatic Notes on Ch02, "Ripples in Mathematics",
 * by A. Jensen & A. la Cour-Harbo written with a deep
 * gratitude to the authors for this wonderful text.
 * 
 * 
 * @author Vladimir Kulyukin
 ********************************************************
 */

public class RipplesInMathCh02 {
    
    static DecimalFormat df = new DecimalFormat("0.00");
    static final double[] signal_p7 = {56, 40, 8, 24, 48, 48, 40, 16};
    
    static void page7() {
        computeTable_2_1_p7(signal_p7);
    }
    
    static void page8() {
        computeTable_2_2_p8(signal_p7);
    }
    
    static void page9() {
        computeTable_2_3_p9(signal_p7);
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
    
    static void printTable(double[] signalFor3InverseIters, double[] signalFor2InverseIters, 
            double[] signalFor1InverseIter, double[] signalFor3Iters, int lineLen) {
        printHyphenLineSeparator(lineLen);
        printSignalOnOneLine(signalFor3InverseIters);
        printHyphenLineSeparator(lineLen);
        printSignalOnOneLine(signalFor2InverseIters);
        printHyphenLineSeparator(lineLen);
        printSignalOnOneLine(signalFor1InverseIter);
        printHyphenLineSeparator(lineLen);
        printSignalOnOneLine(signalFor3Iters);
        printHyphenLineSeparator(lineLen);
        System.out.println();
    }
    
    static void computeTable_2_1_p7(double[] signal) {
        computeTableForSignalOnPage7(signal, "Table 2.1, p. 7, Ch. 2, \"Ripples in Mathematics\"" );
    }
    
    static void computeTable_2_2_p8(double[] signal) {
        computeTableForSignalOnPage7(signal, 4, "Table 2.2, p. 8, Ch. 2, \"Ripples in Mathematics\"" );
    }
    
    static void computeTable_2_3_p9(double[] signal) {
        computeTableForSignalOnPage7(signal, 9, "Table 2.3, p. 9, Ch. 2, \"Ripples in Mathematics\"" );
    }
    
    // threshold the processed signal
    static void computeTableForSignalOnPage7(double[] signal, double thresh, String tableTitle) {
        System.out.println(tableTitle);
        
        final int n = signal.length;
        double[] signalFor1InverseIter  = new double[n];
        double[] signalFor2InverseIters = new double[n];
        double[] signalFor3InverseIters = new double[n];
        double[] signalFor3Iters        = new double[n];
        
        System.arraycopy(signal, 0, signalFor3Iters, 0, n);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor3Iters, 3);
        
        System.arraycopy(signalFor3Iters, 0, signalFor3InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor3InverseIters, 3, thresh); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor2InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor2InverseIters, 2, thresh); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor1InverseIter,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor1InverseIter, 1, thresh); 
       
        OneDHaar.thresholdSignal(signalFor3Iters, thresh);
        
        printTable(signalFor3InverseIters, signalFor2InverseIters, signalFor1InverseIter, signalFor3Iters, 62);
    }
    
    // no thresholding of processed signal
    static void computeTableForSignalOnPage7(double[] signal, String tableTitle) {
        System.out.println(tableTitle);
        
        final int n = signal.length;
        double[] signalFor1InverseIter  = new double[n];
        double[] signalFor2InverseIters = new double[n];
        double[] signalFor3InverseIters = new double[n];
        double[] signalFor3Iters        = new double[n];
        
        System.arraycopy(signal, 0, signalFor3Iters, 0, n);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signalFor3Iters, 3);
        
        System.arraycopy(signalFor3Iters, 0, signalFor3InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor3InverseIters, 3); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor2InverseIters,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor2InverseIters, 2); 
        
        System.arraycopy(signalFor3Iters, 0, signalFor1InverseIter,  0, n);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signalFor1InverseIter, 1); 
       
        printTable(signalFor3InverseIters, signalFor2InverseIters, signalFor1InverseIter, signalFor3Iters, 62);
    }
    
    public static void main(String[] args) {
        page7();
        page8();
        page9();
    }
    
}
