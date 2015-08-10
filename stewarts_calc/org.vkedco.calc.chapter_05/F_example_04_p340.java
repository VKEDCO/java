package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class F_example_04_p340 extends Function {
    
    public F_example_04_p340() {}
    
    @Override
    public double v(double x) {
        return x*x*x - 5*x;
    }
}
