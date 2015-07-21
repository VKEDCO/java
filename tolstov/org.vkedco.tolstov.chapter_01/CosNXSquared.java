package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class CosNXSquared extends Function {
    
    private int mN = 0;
    
    public CosNXSquared(int n) {
        mN = n;
    }
    
    @Override
    public double v(double x) {
        final double t = mN*x;
        return Math.cos(t)*Math.cos(t);
    }
    
}
