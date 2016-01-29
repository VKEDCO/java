
/**
 *==============================================================
 * Sinusoid curve defined on p. 31 in "Ripples in Mathematics"
 * by Jensen & la Cour-Harbo.
 * 
 * @author vladimir kulyukin
 *==============================================================
 */

package org.vkedco.calc.utils;


public class Ripples_F_p33 extends Function {
    
    public Ripples_F_p33() {}
    @Override
    public double v(double t) { return Math.log(2.0 + Math.sin(3 * Math.PI * Math.sqrt(t))); } 
    
}
