package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 * 
 * Example 02 in Ch.01, "Fourier Series" by G. Tolstov.
 */
public class XAbs extends Function {
    
    public double mShift = 0;
    public XAbs(double shift) {
        mShift = shift;
    }
    
    @Override
    public double v(double x) {
        return Math.abs(x + mShift);
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
