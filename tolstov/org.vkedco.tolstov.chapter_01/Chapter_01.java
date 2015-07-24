package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import org.vkedco.tolstov.utils.Coeff;
import org.vkedco.tolstov.utils.Function;
import org.vkedco.tolstov.utils.Partition;

/**
 *
 * @author vladimir kulyukin
 * 
 * Programmatic Notes on Ch.01, "Fourier Series" by G. Tolstov.
 */
public class Chapter_01 {
    
    // formula 5.2, p. 11
    public static double cos_5_2_p11(int n, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        CosNX cosnx = new CosNX(n);
        for(double x: p) {
           rslt += cosnx.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.2, p. 11
    public static double sin_5_2_p11(int n, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        SinNX sinnx = new SinNX(n);
        for(double x: p) {
            rslt += sinnx.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.3, p. 11
    public static double cos_5_3_p11(int n, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        CosNXSquared f = new CosNXSquared(n);
        for(double x: p) {
            rslt += f.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.3, p. 11
    public static double sin_5_3_p11(int n, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        SinNXSquared f = new SinNXSquared(n);
        for(double x: p) {
            rslt += f.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.4, p. 11
    public static double cos_5_4_p11(int n, int m, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        CosNXCosMX f = new CosNXCosMX(n, m);
        for(double x: p) {
            rslt += f.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.4, p. 11
    public static double sin_5_4_p11(int n, int m, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        SinNXSinMX f = new SinNXSinMX(n, m);
        for(double x: p) {
            rslt += f.v(x)*step;
        }
        return rslt;
    }
    
    // formula 5.5, p. 11
    public static double equation_5_5_p11(int n, int m, double lower, double upper, double step) {
        double[] p = Partition.partition(lower, upper, step);
        double rslt = 0;
        SinNXCosMX f = new SinNXCosMX(n, m);
        for(double x: p) {
            rslt += f.v(x)*step;
        }
        return rslt;
    }
    
    // test formula 5.2 cos(nx), sin(nx) on [n_start, n_end] and [a, a+2PI]
    public static void test_equation_5_2_p11(int n_start, int n_end, double a, double step) {
        for(int n = n_start; n <= n_end; n++) {
            System.out.println("cos(" + n + "x) = " + cos_5_2_p11(n, a, a+2*Math.PI, step));
            System.out.println("sin(" + n + "x) = " + sin_5_2_p11(n, a, a+2*Math.PI, step));
        }
    }
    
    // test formula 5.3 on [n_start, n_end] and [a, a+2PI]
    public static void test_equation_5_3_p11(int n_start, int n_end, double a, double step) {
        for(int n = n_start; n <= n_end; n++) {
            System.out.println("cos(" + n + "x) = " + cos_5_3_p11(n, a, a+2*Math.PI, step));
            System.out.println("sin(" + n + "x) = " + sin_5_3_p11(n, a, a+2*Math.PI, step));
        }
    }
    
    // test formula 5.4 on [n_start, n_end], [m_start, m_end], and [a, a+2PI]
    public static void test_equation_5_4_p11(int n_start, int n_end, int m_start, int m_end, double a, double step) {
        for(int n = n_start; n <= n_end; n++) {
            for(int m = m_start; m <= m_end; m++) {
                if ( n != m ) {
                    System.out.println("cos(" + n + "x)*" + "cos(" + m + "x) = " + cos_5_4_p11(n, m, a, a+2*Math.PI, step));
                    System.out.println("sin(" + n + "x)*" + "sin(" + m + "x) = " + sin_5_4_p11(n, m, a, a+2*Math.PI, step));
                }
            }
        }
    }
    
     // test formula 5.5 on [n_start, n_end], [m_start, m_end], and [a, a+2PI]
    public static void test_equation_5_5_p11(int n_start, int n_end, int m_start, int m_end, double a, double step) {
        for(int n = n_start; n <= n_end; n++) {
            for(int m = m_start; m <= m_end; m++) {
                System.out.println("sin(" + n + "x)*" + "cos(" + m + "x) = " + equation_5_5_p11(n, m, a, a+2*Math.PI, step));
            }
        }
    }
    
    public static void example_01_p25a(double shift, double from, double upto, double step) {
        Function xs = new XSquared(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        double a0 = Coeff.a0CoeffFromFunctionData(range, step);
        double a1 = Coeff.aCoeff(range, 1, from, upto, step);
        double a2 = Coeff.aCoeff(range, 2, from, upto, step);
        double a3 = Coeff.aCoeff(range, 3, from, upto, step);
        double a4 = Coeff.aCoeff(range, 4, from, upto, step);
        double a5 = Coeff.aCoeff(range, 5, from, upto, step);
        
        double b1 = Coeff.bCoeff(range, 1, from, upto, step);
        double b2 = Coeff.bCoeff(range, 2, from, upto, step);
        double b3 = Coeff.bCoeff(range, 3, from, upto, step);
        double b4 = Coeff.bCoeff(range, 4, from, upto, step);
        double b5 = Coeff.bCoeff(range, 5, from, upto, step);
        
        System.out.println("a0 = " + a0);
        System.out.println("a1 = " + a1);
        System.out.println("a2 = " + a2);
        System.out.println("a3 = " + a3);
        System.out.println("a4 = " + a4);
        System.out.println("a5 = " + a5);
        
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("b3 = " + b3);
        System.out.println("b4 = " + b4);
        System.out.println("b5 = " + b5);
    }
    
    public static void example_01_p25b(double shift, double from, double upto, double step) {
        Function xs = new XSquared(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        
        for(CoeffPair cp: coeffs) {
            System.out.println("a" + cp.getN() + " = " + cp.getA());
            System.out.println("b" + cp.getN() + " = " + cp.getB());
        }
    }
    
    public static void example_01_p25c(double x, double shift, double from, double upto, double step) {
        Function xs = new XSquared(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        
        for(CoeffPair cp: coeffs) {
            System.out.println("a" + cp.getN() + " = " + cp.getA());
            System.out.println("b" + cp.getN() + " = " + cp.getB());
        }
        
        double ay = FourierSeriesExpansion.approximateFromExpansionCoeffsAt(x, coeffs);
        System.out.println("y  = " + xs.v(x));
        System.out.println("ay = " + ay);
    }
    
    // example_01_p25d(0, -Math.PI, Math.PI, 0.0001) approximates f(x) = (x-0)^2 on
    // [-pi, pi] in increments 0.0001.
    // example_01_p25(-Math.PI, 0, 2*Math.PI, 0.0001) approximates f(x) = (x-pi)^2 on
    // [0, 2pi] in increments 0.0001.
    // example_01_p25(Math.PI, -2*Math.PI, 0, 0.0001) approximates f(x) = (x+pi)^2 on
    // [-2pi, 0] in increments 0.0001.
    // example_01_p25(-(Math.PI+0.5), 0.5, 0.5+2*Math.PI, 0.0001) approximates f(x) = (x-(pi+0.5))^2
    // on [0.5, 0.5+2pi] in increments 0.0001
    // example_01_p25(Math.PI+0.5, -0.5-2*Math.PI, -0.5, 0.0001) approximates f(x) = (x+(pi+0.5))^2
    // on [-0.5-2pi, -0.5] in increments of 0.0001.
    // Since f(x)=x^2 is an even function the average difference will be close to pi^2/3.0.
    public static void example_01_p25d(double shift, double from, double upto, double step) {
        Function xs = new XSquared(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, xs);    
    }
    
    // example_02_p25a(0, -Math.PI, Math.PI, 0.0001) approximates f(x) = |x-0| on
    // [-pi, pi] in increments 0.0001. Since f(x)=|x| is even, the average difference should be
    // pi/2.
    // example_02_p25a(-0.5, -Math.PI+0.5, Math.PI+0.5, 0.0001) approximates f(x) = |x-0.5| on
    // [-pi+1/2, pi+1/2] in increments 0.0001. Since f(x)=|x| is even, the average difference should be
    // pi/2.
    // example_02_p25a(-1, -Math.PI+1, Math.PI+1, 0.0001) approximates f(x)=|x-1| on 
    // [-pi+1, pi+1] in increments of 0.0001. Since f(x) = |x| is even, the average difference should
    // be pi/2.
    public static void example_02_p25a(double shift, double from, double upto, double step) {
        Function xs = new XAbs(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, xs); 
    }
    
    public static void example_03_p26a(double shift, double from, double upto, double step) {
        Function f = new SinXAbs(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = f.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, f); 
    }
    
    public static void example_03_p26b(double x, double shift, double from, double upto, double step) {
        Function xs = new SinXAbs(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 5, from, upto, step);
        
        for(CoeffPair cp: coeffs) {
            System.out.println("a" + cp.getN() + " = " + cp.getA());
            System.out.println("b" + cp.getN() + " = " + cp.getB());
        }
        
        double ay = FourierSeriesExpansion.approximateFromExpansionCoeffsAt(x, coeffs);
        System.out.println("y  = " + xs.v(x));
        System.out.println("ay = " + ay);
    }
    
    public static void example_04_p27a(double shift, double from, double upto, double step) {
        Function f = new FofX(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = f.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 100, from, upto, step);
        FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, f); 
    }
    
    public static void example_04_p27b(double x, double shift, double from, double upto, double step) {
        Function xs = new FofX(shift);
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = xs.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, 0, 100, from, upto, step);
        
        for(CoeffPair cp: coeffs) {
            System.out.println("a" + cp.getN() + " = " + cp.getA());
            System.out.println("b" + cp.getN() + " = " + cp.getB());
        }
        
        double ay = FourierSeriesExpansion.approximateFromExpansionCoeffsAt(x, coeffs);
        System.out.println("y  = " + xs.v(x));
        System.out.println("ay = " + ay);
    }
    
    public static void test_sin_curve_01a(int freq_range_start, int freq_range_end, double from, double upto, double step) {
        Function f = new SineCurve01();
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = f.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, freq_range_start, freq_range_end, from, upto, step);
        for(CoeffPair cp: coeffs) {
            if ( Math.abs(cp.getA()) > 0.001 || Math.abs(cp.getB()) > 0.001 ) {
                System.out.println("a" + cp.getN() + " = " + cp.getA());
                System.out.println("b" + cp.getN() + " = " + cp.getB());
            }
        }
        //FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, f);
    }
    
    public static void test_sin_curve_02a(int freq_range_start, int freq_range_end, double from, double upto, double step) {
        Function f = new SineCurve02();
        double[] partition = Partition.partition(from, upto, step);
        
        double[] range = f.generateRangeInterval(partition);
        ArrayList<CoeffPair> coeffs = null;
        coeffs = FourierSeriesExpansion.expandFromFunctionData(range, freq_range_start, freq_range_end, from, upto, step);
        for(CoeffPair cp: coeffs) {
            if ( Math.abs(cp.getA()) > 0.001 || Math.abs(cp.getB()) > 0.001 ) {
                System.out.println("a" + cp.getN() + " = " + cp.getA());
                System.out.println("b" + cp.getN() + " = " + cp.getB());
            }
        }
        //FourierSeriesExpansion.approximateFromExpansionCoeffsOverRange(from, upto, step, coeffs, f);
    }
    
    public static void main(String[] args) {
        test_sin_curve_02a(44000, 44100, 0, 2*Math.PI, 0.0001);
        //example_03_p26b(0, 0, -Math.PI, Math.PI, 0.0001);
    }
}
