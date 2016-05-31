package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Updater extends Node {
    
    public void update(double[] input) {       
        mOutput = new double[input.length];
        for(int i = 0; i < input.length; i++) {
            mOutput[i] = input[i] / 2;
        }
    }
    
}
