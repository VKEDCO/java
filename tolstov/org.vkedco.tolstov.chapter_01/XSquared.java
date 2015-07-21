package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class XSquared extends Function {
    // horizontal shift
    // if mH < 0, there is a shift to the right
    // if mH > 0, there is a shift to the left
    protected double mH = 0;
    
    public XSquared(double h) {
        mH = h;
    }
    
    @Override
    public double v(double t) {
        return Math.pow(t + mH, 2);
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
