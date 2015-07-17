package org.vkedco.calc.chapter_01;

/**
 *
 * @author vladimir kulyukin
 */
import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class F_ex18_p79 extends Function {
    
    public F_ex18_p79() {}
    @Override
    public double v(double x) {
        return (x*x + x - 6)/(x - 2);
    }
}
