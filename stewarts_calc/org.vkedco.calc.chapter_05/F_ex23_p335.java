package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class F_ex23_p335 extends Function {
    private int mK = 0;
    
    public F_ex23_p335(int k) { mK = k; }
    @Override
    public double v(double x) {
        return Math.tan(x + mK*Math.PI);
    }
}
