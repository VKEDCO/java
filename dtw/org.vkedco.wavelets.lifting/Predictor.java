package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Predictor extends Node {
    
    public void predict(double[] signal) {
        setOutput(signal);
    }
}
