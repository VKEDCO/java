package org.vkedco.calc.utils;

/**
 *
 * @author vladimir kulyukin
 */
public class AreaAsSum {
    
    public static double area_left_point(Function f, double[] partition) {
        double area = 0;
        int n = partition.length;
        if ( n == 0 ) return area;
        for(int i = 1; i < n; i++) {
            //System.out.println(f.v(partition[i-1]) + " " + (partition[i] - partition[i-1]));
            area += f.v(partition[i-1])*(partition[i] - partition[i-1]);
        }
        return area;
    }
    
    public static double area_right_point(Function f, double[] partition) {
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
    
    public static double area_mid_point(Function f, double[] partition) {
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
    
    
}
