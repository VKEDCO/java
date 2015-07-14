package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */

// Function from ex. 01, p. 335 from James Stewart's "Calculus: Early Transcendentals", 3rd Edition.
public class F_ex01_p335 extends Function {
    
    public F_ex01_p335() {}
    
    @Override
    public double v(double x) {
        return 16 - x*x;
    }
    
}
