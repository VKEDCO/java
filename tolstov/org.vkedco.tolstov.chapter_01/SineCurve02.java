package org.vkedco.tolstov.chapter_01;

import java.util.ArrayList;
import org.vkedco.tolstov.utils.Function;

/**
 *
 * @author vladimir kulyukin
 * 
 * f(x) = pi/2 +
 *        [-0.75*cos(10*x)   + 0.5*sin(10*x)] + 
 *        [    5*cos(20*x)   -  40*sin(20*x)] + 
 *        [  -50*cos(50*x)   +  50*sin(50*x)] +
 *        [  0.8*cos(500*x)  - 0.95*sin(500*x)] +
 *        [  200*cos(2100*x) + 75*sin(2100*x)] +
 *        [  32*cos(44100*x) - 340.25*sin(44100*x)] 
 */
public class SineCurve02 extends Function {
    
    private SineCurve mCurve = null;
    
    public SineCurve02() {
        CoeffPair cp0 = new CoeffPair(0, Math.PI/2, 0);
        CoeffPair cp1 = new CoeffPair(10, -0.75, 0.5);
        CoeffPair cp2 = new CoeffPair(20, 5, -40);
        CoeffPair cp3 = new CoeffPair(50, -50, 50);
        CoeffPair cp4 = new CoeffPair(500, 0.8, -0.95);
        CoeffPair cp5 = new CoeffPair(2100, 200, 75);
        CoeffPair cp6 = new CoeffPair(44100, 32, -340.25);
        ArrayList<CoeffPair> list;
        list = new ArrayList<>();
        list.add(cp0);
        list.add(cp1);
        list.add(cp2);
        list.add(cp3);
        list.add(cp4);
        list.add(cp5);
        list.add(cp6);
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
