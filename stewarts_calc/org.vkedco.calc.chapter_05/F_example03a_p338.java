package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class F_example03a_p338 extends Function {
    public F_example03a_p338() {}
    
    @Override
    public double v(double x) {
        return Math.sqrt(1 - x*x);
    }
}
