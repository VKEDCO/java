package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import java.util.Collections;
import org.vkedco.tolstov.utils.Coeff;
import org.vkedco.tolstov.utils.Function;
import org.vkedco.tolstov.utils.Harmonic;

/**
 *
 * @author vladimir kulyukin
 */
public class FourierSeriesExpansion {
    
    public static final double COEFF_EXP_THRESH = 0.0001;
    
    public static ArrayList<Harmonic> expandFromFunctionData(double[] fdata, int from_freq, int upto_freq, double xlower, double xupper, double step) {
        ArrayList<Harmonic> coeffs = new ArrayList<Harmonic>();
        double an; double bn;
        for(int n = from_freq; n <= upto_freq; n++) {
            an = Coeff.aCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(an) < COEFF_EXP_THRESH ) an = 0;
            bn = Coeff.bCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(bn) < COEFF_EXP_THRESH ) bn = 0;
            coeffs.add(new Harmonic(n, an, bn));
        }
        return coeffs;
    }
    
    public static ArrayList<Harmonic> expandFromFunctionDataSortedByAmplitude(double[] fdata, int from_freq, int upto_freq, double xlower, double xupper, double step) {
        ArrayList<Harmonic> hrms = new ArrayList<Harmonic>();
        double an; double bn;
        for(int n = from_freq; n <= upto_freq; n++) {
            an = Coeff.aCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(an) < COEFF_EXP_THRESH ) an = 0;
            bn = Coeff.bCoeffFromFunctionData(fdata, n, xlower, xupper, step);
            if ( Math.abs(bn) < COEFF_EXP_THRESH ) bn = 0;
            hrms.add(new Harmonic(n, an, bn));
        }
        Collections.sort(hrms);
        return hrms;
    }
    
    
    public static Harmonic findMaxCoeffPairAboveCoeffThresh(ArrayList<Harmonic> clist, double thresh) {
        Harmonic maxpair = clist.get(0);
        for(Harmonic cp: clist) {
            if ( (Math.abs(cp.getAC()) >= thresh && Math.abs(maxpair.getAC()) < Math.abs(cp.getAC()) ) ||
                 (Math.abs(cp.getBC()) >= thresh && Math.abs(maxpair.getBC()) < Math.abs(cp.getBC()) ) ) {
                maxpair = cp;
            }
        }
        
        if ( (Math.abs(maxpair.getAC()) < thresh  && Math.abs(maxpair.getBC()) < thresh ) ) {
                maxpair = null;
            }
        return maxpair;
    }
    
    public static Harmonic findMaxCoeffPairAboveAmplitudeThresh(ArrayList<Harmonic> clist, double thresh) {
        Harmonic maxpair = clist.get(0);
        for(Harmonic cp: clist) {
            if ( cp.getA() > maxpair.getA() ) {
                maxpair = cp;
            }
        }
        
        if ( maxpair.getA() < thresh ) { maxpair = null; }
        
        return maxpair;
    }
    
    public static double approximateFromExpansionCoeffsAt(double x, ArrayList<Harmonic> coeffs) {
        double y = 0;
        for(Harmonic cp: coeffs) {
          int n = cp.getN();
          //System.out.println(cp.toString());
          if ( n == 0 ) 
              y += cp.getAC();
          else
              y += (cp.getAC()*Math.cos(n*x) + cp.getBC()*Math.sin(n*x));
        }
        //System.out.println("y minus pi/2 = " + (y-Math.PI/2));
        return y;
    }
    
    public static void approximateFromExpansionCoeffsOverRange(double from, double upto, double step, ArrayList<Harmonic> coeffs, Function f) {
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
    
    public static void displayCoeffPairList(ArrayList<Harmonic> cplist) {
        for(Harmonic cp: cplist) {
            System.out.println(cp.toString());
        }
    }
    
}

