package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.Function;

/**
 *
 * @author vladimir kulyukin
 * 
 * Theorem 5 on p. 340 in Stewart's Calculus text
 * 
 */
public class RegularPartitionSum extends Function {
    
    private double mA = 0;
    private double mB = 0;
    private Function mF = null;
    
    public RegularPartitionSum(Function f, double a, double b) {
        mA = a;
        mB = b;
        mF = f;
    }
    
    @Override
    public double v(double n) {
        final double step = (mB - mA)/n;
        final int in = (int)n;
        double sum = 0;
        for(int i = 0; i <= in; i++) {
            sum += mF.v(mA + i*step);
        }
        return step*sum;
    }
    
}
