package org.vkedco.tolstov.utils;
/**
 *
 * @author vladimir kulyukin
 */
public class Harmonic implements Comparable {

    private int mN = 0;
    private double mAC = 0;
    private double mBC = 0;
    
    public Harmonic(int n, double a, double b) {
        mN = n;
        mAC = a;
        mBC = b;
    }
    
    public void setN(int n) { mN = n; }
    public void setAC(double a) { mAC = a; }
    public void setBC(double b) { mBC = b; }
    
    public int getN() { return mN; }
    public double getAC() { return mAC; }
    public double getBC() { return mBC; }
    
    public double getA() {
        return Math.sqrt(mAC*mAC + mBC*mBC);
    }
    
    public double getPhiFromSin() {
        return Math.asin(mAC/getA());
    }
    
    public double getPhiFromCos() {
        return Math.acos(mBC/getA());
    }
    
    @Override
    public String toString() {
        return "H: <n=" + mN + "," + "A=" + mAC + "," + "B=" + mBC + "," + "R=" + this.getA()+">"; 
    }

    @Override
    public int compareTo(Object o) {
        Harmonic h = (Harmonic) o;
        double a1 = this.getA();
        double a2 = h.getA();
        if ( a1 < a2 )
            return 1;
        else if ( a1 > a1 )
            return -1;
        else
            return 0;
    }
}

