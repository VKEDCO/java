package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class CosNXCosMX extends Function {
    
    private int mN = 0;
    private int mM = 0;
    
    public CosNXCosMX(int n, int m) { mN = n; mM = m; }
    
    @Override
    public double v(double v) {
        return Math.cos(mN * v)*Math.cos(mM * v);
    }
    
}
