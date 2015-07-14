package org.vkedco.calc.utils;

/**
 *
 * @author vladimir kulyukin
 */
public class IntegralAsSum {
    
    public static double sum(Function f, double[] partition, double step) {
        double integral = 0;
        for(int i = 0; i < partition.length; i++) {
            integral += f.v(partition[i])*step;
        }
        return integral;
    }
    
}
