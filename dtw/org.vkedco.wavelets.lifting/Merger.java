package org.vkedco.wavelets.lifting;

/**
 * @author Vladimir Kulyukin
 */

public class Merger extends Node {
    
    private double[] mLeftHandInput;
    private double[] mRightHandInput;
    
    
    public void setLeftHandInput(double[] input) {
        mLeftHandInput = input;
    }
    
    public void setRightHandInput(double[] input) {
        mRightHandInput = input;
    }
    
    public void merge() {
        if ( mRightHandInput == null || mLeftHandInput == null )
            return;
        mOutput = new double[mLeftHandInput.length + mRightHandInput.length];
        for(int i = 0, j = 0; i < mLeftHandInput.length; i++, j += 2) {
            mOutput[j]   = mLeftHandInput[i];
            mOutput[j+1] = mRightHandInput[i];
        }
    }
    
    
    
    public static void main(String[] args) {
        Splitter splitter = new Splitter();
        final double[] s1 = {1, 2, 3, 4, 5, 6, 7, 8};
        splitter.split(s1);
        final double[] evens = splitter.getEvens();
        final double[] odds  = splitter.getOdds();
        System.out.print("Evens: ");
        for(int i = 0; i < evens.length; i++) {
            System.out.print(evens[i] + " ");
        }
        System.out.println();
        System.out.print("Odds: ");
        for(int i = 0; i < odds.length; i++) {
            System.out.print(odds[i] + " ");
        }
        System.out.println();
        
        Merger merger = new Merger();
        merger.setLeftHandInput(evens);
        merger.setRightHandInput(odds);
        merger.merge();
        double[] mergedSignal = merger.getOutput();
        System.out.println();
        System.out.print("Merged signal: ");
        
        for(int i = 0; i < mergedSignal.length; i++) {
            System.out.print(mergedSignal[i] + " ");
        }
        
        System.out.println();
    }
}
