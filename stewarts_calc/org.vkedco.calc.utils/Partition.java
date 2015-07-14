package org.vkedco.calc.utils;

/**
 *
 * @author vladimir kulyukin
 */
public class Partition {
    
    public static double[] partition(double from, double upto, double step) {
        if ( upto <= from ) return null;
        int n = (int)((upto - from)/step) + 1;
        double[] interval = new double[n];
       
        //System.out.println("n=" + n);
        int i;
        double curr;
        for(i = 0, curr = from; i < n; i++, curr += step) {
            //System.out.println("i=" + i + " " + "curr=" + curr);
            interval[i] = curr;
        }
        return interval;
    }
}
