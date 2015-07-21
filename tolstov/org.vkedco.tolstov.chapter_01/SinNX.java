package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class SinNX extends Function {
    
    private int mN = 0;
    
    public SinNX(int n) { mN = n; }
    
    @Override
    public double v(double x) {
        return Math.sin(mN * x);
    }
    
}
