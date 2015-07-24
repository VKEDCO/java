package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;
/**
 *
 * @author vladimir kulyukin
 * 
 * Example 04, p. 27 in Tolstov's "Fourier Series."
 */


public class FofX extends Function {
    
    public double mH = 0;
    public FofX(double shift) {
        mH = shift;
    }
    
    @Override
    public double v(double x) {
        return x + mH;
    }
    
    @Override
    public double[] generateRangeInterval(double[] domain_values) {
        double[] rslt = new double[domain_values.length];
        for(int i = 0; i < domain_values.length; i++) {
            rslt[i] = this.v(domain_values[i]);
        }
        return rslt;
    }
}
