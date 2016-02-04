package org.vkedco.calc.utils;

/**
 *
 * @author vladimir kulyukin
 */
public class Ripples_F_ex_4_4_p34 extends Function {
    
    public Ripples_F_ex_4_4_p34() {}
    @Override
    public double v(double t) {
        if ( 0 <= t && t < 0.25 ) {
            return Math.sin(4*Math.PI*t);
        }
        else if ( 0.25 <= t && t < 0.75 ) {
            return 1 + Math.sin(4*Math.PI*t);
        }
        else if ( 0.75 <= t && t <= 1 ) {
            return Math.sin(4*Math.PI*t);
        }
        else {
            throw new IllegalArgumentException("t == " + t);
        }
    }
}
