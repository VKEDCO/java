package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import org.vkedco.tolstov.utils.Coeff;
import org.vkedco.tolstov.utils.Function;
/**
 *
 * @author vladimir kulyukin
 */
public class FourierSeriesExpansion {
    
    public static final double COEFF_EXP_THRESH = 0.0001;
    
    public static ArrayList<CoeffPair> expandFromFunctionData(double[] fdata, int from_freq, int upto_freq, double xlower, double xupper, double step) {
        ArrayList<CoeffPair> coeffs = new ArrayList<>();
        double an; double bn;
        for(int n = from_freq; n <= upto_freq; n++) {
            an = Coeff.aCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(an) <= COEFF_EXP_THRESH ) an = 0;
            bn = Coeff.bCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(bn) <= COEFF_EXP_THRESH ) bn = 0;
            coeffs.add(new CoeffPair(n, an, bn));
        }
        return coeffs;
    }
    
    public static double approximateFromExpansionCoeffsAt(double x, ArrayList<CoeffPair> coeffs) {
        double y = 0;
        for(CoeffPair cp: coeffs) {
          int n = cp.getN();
          //System.out.println(cp.toString());
          if ( n == 0 ) 
              y += cp.getA();
          else
              y += (cp.getA()*Math.cos(n*x) + cp.getB()*Math.sin(n*x));
        }
        //System.out.println("y minus pi/2 = " + (y-Math.PI/2));
        return y;
    }
    
    public static void approximateFromExpansionCoeffsOverRange(double from, double upto, double step, ArrayList<CoeffPair> coeffs, Function f) {
        double x = from;
        double diff_sum = 0;
        int count = 0;
        double curr_diff = 0;
        while ( x <= upto ) {
            System.out.println("f(" + x + ") = " + f.v(x));
            System.out.println("af(" + x + ") = " + approximateFromExpansionCoeffsAt(x, coeffs));
            curr_diff = Math.abs(f.v(x)-approximateFromExpansionCoeffsAt(x, coeffs));
            System.out.println("|f(x)-af(x)| = " + curr_diff);
            diff_sum += curr_diff;
            x += step;
            count++;
        }
        System.out.println("average diff = " + diff_sum/count);
    }
    
}
