package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class SinNXSquared extends Function {
    
    private int mN = 0;
    
    public SinNXSquared(int n) {
        mN = n;
    }
    
    public double v(double x) {
        final double t = mN * x;
        return Math.sin(t)*Math.sin(t);
    }
}
