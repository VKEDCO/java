package org.vkedco.tolstov.chapter_01;

import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class SinNXSinMX extends Function {
    
    private int mN = 0;
    private int mM = 0;
    
    public SinNXSinMX(int n, int m) { mN = n; mM = m; }
    
    @Override
    public double v(double v) {
        return Math.sin(mN * v)*Math.sin(mM * v);
    }
    
}
