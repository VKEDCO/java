package org.vkedco.tolstov.utils;

/**
 *
 * @author vladimir kulyukin
 */
public class Coeff {
    
    public static double aCoeffFromFunction(Function f, int n, double xlower, double xupper, double xstep) {
        double an = 0;
        double x = xlower;
        int i = 0;
        while ( x <= xupper ) {
            an += f.v(x)*Math.cos(n*x)*xstep;
            i++;
            x += xstep; 
        }
        return an/Math.PI;
    }
    
    // n is a frequency; fdata is generated from partition.
    public static double aCoeffFromFunctionData(double[] fdata, int n, double xlower, double xupper, double xstep) {
        //if ( n == 0 ) return a0CoeffFromFunctionData(fdata, xstep);
        double an = 0;
        double x = xlower;
        int i = 0;
        while ( x <= xupper ) {
            an += fdata[i]*Math.cos(n*x)*xstep;
            i++;
            x += xstep; 
        }
        return an/Math.PI;
    }
    
    public static double bCoeffFromFunction(Function f, int n, double xlower, double xupper, double xstep) {
        if ( n == 0 ) return 0;
        double bn = 0;
        double x = xlower;
        int i = 0;
        while ( x <= xupper ) {
            bn += f.v(x)*Math.sin(n*x)*xstep;
            i++;
            x += xstep; 
        }
        return bn/Math.PI;
    }
    
    // n is a frequency; fdata is generated from partition.
    public static double bCoeffFromFunctionData(double[] fdata, int n, double xlower, double xupper, double xstep) {
        double bn = 0;
        double x = xlower;
        int i = 0;
        while ( x <= xupper ) {
            bn += fdata[i]*Math.sin(n*x)*xstep;
            i++;
            x += xstep; 
        }
        return bn/Math.PI;
    }
    
    public static double aCoeff(double[] f, int n, double lower, double upper, double step) {
        if ( n == 0 ) return a0CoeffFromFunctionData(f, step);
        double an = 0;
        int i = 0;
        for(double x: Partition.partition(lower, upper, step)) {
            an += f[i]*Math.cos(n*x)*step;
            i++;
        }
        return an/Math.PI;
    }
    
    public static double a0CoeffFromFunctionData(double[] f, double step) {
        double a0 = 0;
        for(int i = 0; i < f.length; i++) {
            a0 += f[i]*step;
        }
        return a0/Math.PI;
    }
    
    public static double bCoeff(double[] f, int n, double lower, double upper, double step) {
        double bn = 0;
        int i = 0;
        for(double x: Partition.partition(lower, upper, step)) {
            bn += f[i]*Math.sin(n*x)*step;
            i++;
        }
        return bn;
    }
    
}
