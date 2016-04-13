package org.vkedco.cs.dtw;

/**
 *
 * @author vladimir kulyukin
 */
public class SquaredEuclideanSim implements IDTWSimilarity {

    @Override
    public double compare(Double x, Double y) {
        final double diff = x.doubleValue() - y.doubleValue();
        return diff * diff;
    }
    
}
