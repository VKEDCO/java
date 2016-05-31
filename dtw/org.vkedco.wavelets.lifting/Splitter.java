package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Splitter extends Node {
    
    private double[] mEvens;
    private double[] mOdds;
    
    public void split(double[] signal) {
        mEvens = new double[signal.length/2];
        mOdds  = new double[signal.length/2];
        
        for(int i = 0, j = 0; i < signal.length; i += 2, j += 1) {
            mEvens[j] = signal[i];
            mOdds[j]  = signal[i+1];
        }
    }
    
    public double[] getEvens() { return mEvens; }
    public double[] getOdds()  { return mOdds; }
    
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
    }
    
    
}
