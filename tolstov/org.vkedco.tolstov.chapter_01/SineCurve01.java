package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 * 
 * f(x) = sin(x) + 0.5*sin(2x) + 0.25sin(3x)
 */
public class SineCurve01 extends Function {
    
    private SineCurve mCurve = null;
    
    public SineCurve01() {
        CoeffPair cp1 = new CoeffPair(1, 1, 0);
        CoeffPair cp2 = new CoeffPair(2, 0.5, 0);
        CoeffPair cp3 = new CoeffPair(3, 0.25, 0);
        ArrayList<CoeffPair> list;
        list = new ArrayList<>();
        list.add(cp1);
        list.add(cp2);
        list.add(cp3);
        mCurve = new SineCurve(list);
    }
    
    @Override
    public double v(double x) {
        return mCurve.v(x);
    }
    
    @Override
    public double[] generateRangeInterval(double[] domain_values) {
        double[] rslt = new double[domain_values.length];
        for(int i = 0; i < domain_values.length; i++) {
            rslt[i] = this.v(domain_values[i]);
        }
        return rslt;
    }
}
