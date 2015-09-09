package org.vkedco.calc.utils;

/**
 *=======================================
 * Sinusoid curve defined on p. 25 in "Ripples in Mathematics"
 * by Jensen & la Cour-Harbo.
 * 
 * @author vladimir kulyukin
 * 
 *========================================
 */
public class Ripples_F_p25 extends Function {
    
    public Ripples_F_p25() {}
    @Override
    public double v(double x) { return Math.sin(4*Math.PI*x/512.0); } 
}
