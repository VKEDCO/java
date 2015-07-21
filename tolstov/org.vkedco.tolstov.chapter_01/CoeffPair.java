package org.vkedco.tolstov.chapter_01;

/**
 *
 * @author vladimir kulyukin
 */
public class CoeffPair {

    private int mN = 0;
    private double mA = 0;
    private double mB = 0;
    
    public CoeffPair(int n, double a, double b) {
        mN = n;
        mA = a;
        mB = b;
    }
    
    public void setN(int n) { mN = n; }
    public void setA(double a) { mA = a; }
    public void setB(double b) { mB = b; }
    
    public int getN() { return mN; }
    public double getA() { return mA; }
    public double getB() { return mB; }
    
    @Override
    public String toString() {
        return "CoeffPair: <n=" + mN + "," + "A=" + mA + "," + "B=" + mB + ">"; 
    }
}
