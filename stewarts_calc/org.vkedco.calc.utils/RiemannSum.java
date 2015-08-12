package org.vkedco.calc.utils;

import org.vkedco.calc.chapter_05.RegularPartitionSum;

/**
 *
 * @author vladimir kulyukin
 */
public class RiemannSum {
    
    // f is applied to the left point of each subinterval in partition
    public static double leftPointArea(Function f, double[] partition) {
        double area = 0;
        int n = partition.length;
        if ( n == 0 ) return area;
        for(int i = 1; i < n; i++) {
            //System.out.println(f.v(partition[i-1]) + " " + (partition[i] - partition[i-1]));
            area += f.v(partition[i-1])*(partition[i] - partition[i-1]);
        }
        return area;
    }
    
    // f is applied to the left point of each subinterval in partition
    public static double leftPointArea(Function f, double from, double upto, double step) {
        double area = 0;
        if ( from >= upto ) return 0;
        double currP = from;
        while ( currP <= upto - step ) {
            //System.out.println("x = " + currP);
            area += f.v(currP)*step;
            currP += step;
        }
        return area;
    }
    
    // f is applied to the right point of each subinterval in partition
    public static double rightPointArea(Function f, double[] partition) {
        double area = 0;
        int n = partition.length;
        if ( n == 0 ) return area;
        double w;
        for(int i = 1; i < n; i++) {
            w = (partition[i] - partition[i-1]);
            area += f.v(partition[i])*w;
        }
        return area;
    }
    
    // f is applied to the left point of each subinterval in partition
    public static double rightPointArea(Function f, double from, double upto, double step) {
        double area = 0;
        if ( from >= upto ) return 0;
        double currP = from + step;
        while ( currP <= upto ) {
            //System.out.println("x = " + currP);
            area += f.v(currP)*step;
            currP += step;
        }
        return area;
    }
    
    // f is applied to the mid-point of each subinterval in partition
    public static double midPointArea(Function f, double[] partition) {
        double area = 0;
        int n = partition.length;
        if ( n == 0 ) return area;
        double w;
        for(int i = 1; i < n; i++) {
            w = (partition[i]-partition[i-1]);
            //System.out.println(partition[i-1] + w/2);
            area += f.v(partition[i-1] + w/2)*w;
        }
        return area;
    }
    
    // f is applied to the mid point of each subinterval in partition
    public static double midPointArea(Function f, double from, double upto, double step) {
        double area = 0;
        if ( from >= upto ) return 0;
        double currP = from;
        while ( currP <= upto - step ) {
            //System.out.println("currP = " + currP);
            //System.out.println("x = " + (currP+step/2));
            area += f.v(currP+step/2.0)*step;
            currP += step;
        }
        return area;
    }
    
    // the height of each rectangle is computed by applying f to a specific value in partition_points.
    public static double customPointArea(Function f, double[] partition, double[] partition_points) {
        double area = 0;
        int n = partition.length;
        if ( n == 0 ) return area;
        double w;
        for(int i = 1; i < n; i++) {
            w = (partition[i]-partition[i-1]);
            //System.out.println("w=" + w + " pp=" + partition_points[i-1]);
            area += f.v(partition_points[i-1])*w;
        }
        return area;
    }
    
    // Theorem 5, p. 340 in Stewart's Calculus: If f is integrable on [a, b], then
    // integral(f, a, b) = lim_{n->infinity}{(b-a)/n*sum_{i=1}^{n}{f(a + i*(b-a)/n}
    public static double infiniteLimitOfRegularPartition(Function f, double a, double b, int num_steps) {
        Function rpF = new RegularPartitionSum(f, a, b);
        return FunctionLimit.limitAtInfinity(rpF, 1, 1, num_steps);
    }
    
    public static double midPointRule(Function f, double a, double b, int n) {
        if ( b < a ) throw new IllegalArgumentException("midPointRule: b < a");
        if ( a == b ) return 0;
        final double step = (b - a)/n;
        return RiemannSum.midPointArea(f, a, b, step);
    }
}
