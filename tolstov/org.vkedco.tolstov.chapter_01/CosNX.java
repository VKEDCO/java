package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class CosNX extends Function {
    
    private int mN = 0;
    
    public CosNX(int n) { mN = n; }
    
    @Override
    public double v(double v) {
        return Math.cos(mN * v);
    }
    
}
