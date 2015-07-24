package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class F_ex26_p336 extends Function {
    
    private double mR = 0;
    
    public F_ex26_p336(double r) { mR = r; }
    
    @Override
    public double v(double n) {
        return 0.5*n*mR*mR*Math.sin(2*Math.PI/n);
    }
}
