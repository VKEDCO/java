package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 */
public class SineCurve extends Function {
    
    private ArrayList<CoeffPair> mCoeffList = null;
    
    public SineCurve(ArrayList<CoeffPair> coeffs) {
        mCoeffList = coeffs;
    }
    
    @Override
    public double v(double x) {
        double rslt = 0;
        for(CoeffPair cp: mCoeffList) {
            int n = cp.getN();
            double a = cp.getA();
            double b = cp.getB();
            rslt += a*Math.cos(n*x) + b*Math.sin(n*x);
        }
        return rslt;
    }
}
