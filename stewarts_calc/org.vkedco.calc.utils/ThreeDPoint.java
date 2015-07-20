package org.vkedco.calc.utils;

import java.util.ArrayList;

/**
 *
 * @author vladimir kulyukin
 */
// programmatic notes on Section 11.1 in Stewart's Calculus text.
public class ThreeDPoint {
    
    private double mX = 0;
    private double mY = 0;
    private double mZ = 0;
    
    public ThreeDPoint(double x, double y, double z) {
        mX = x;
        mY = y;
        mZ = z;
    }
    
    public double getX() { return mX; }
    public double getY() { return mY; }
    public double getZ() { return mZ; }
    
    public void setX(double x) { mX = x; }
    public void setY(double y) { mY = y; }
    public void setZ(double z) { mZ = z; }
    
    public static double slopeInXYPlane(ThreeDPoint p1, ThreeDPoint p2) {
        return (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
    }
    
    public static double slopeInXZPlane(ThreeDPoint p1, ThreeDPoint p2) {
        return (p2.getZ() - p1.getZ())/(p2.getX() - p1.getX());
    }
    
    public static double slopeInYZPlane(ThreeDPoint p1, ThreeDPoint p2) {
        return (p2.getZ() - p1.getZ())/(p2.getY() - p1.getY());
    }
    
    public static boolean collinear(ArrayList<ThreeDPoint> points, double slopeThresh) {
        if ( points.size() < 3 ) return true;
        ThreeDPoint p0 = points.get(0);
        ThreeDPoint p1 = points.get(1);
        ThreeDPoint p2 = points.get(2);
        
        double xy_slope = slopeInXYPlane(p0, p1);
        double xy_slope2 = slopeInXYPlane(p1, p2);
        System.out.println("xy_slope = " + xy_slope);
        System.out.println("xy_slope2 = " + xy_slope2);
        if ( !similarSlopes(xy_slope, xy_slope2, slopeThresh) ) return false;
        
        double xz_slope = slopeInXZPlane(p0, p1);
        double xz_slope2 = slopeInXZPlane(p1, p2);
        System.out.println("xz_slope = " + xz_slope);
        System.out.println("xz_slope2 = " + xz_slope2);
        if ( !similarSlopes(xz_slope, xz_slope2, slopeThresh) ) return false;
        
        double yz_slope = slopeInYZPlane(p0, p1);
        double yz_slope2 = slopeInYZPlane(p1, p2);
        System.out.println("yz_slope = " + yz_slope);
        System.out.println("yz_slope2 = " + yz_slope2);
        if ( !similarSlopes(yz_slope, yz_slope2, slopeThresh) ) return false;
        
        p0 = p2;
        for(int i = 3; i < points.size()-1; i++) {
            p1 = points.get(i);
            xy_slope2 = slopeInXYPlane(p0, p1);
            if ( !similarSlopes(xy_slope, xy_slope2, slopeThresh) ) return false;
            xy_slope = xy_slope2;
            
            xz_slope2 = slopeInXZPlane(p0, p1);
            if ( !similarSlopes(xz_slope, xz_slope2, slopeThresh) ) return false;
            xz_slope = xy_slope2;
            
            yz_slope2 = slopeInYZPlane(p0, p1);
            if ( !similarSlopes(yz_slope, yz_slope2, slopeThresh) ) return false;
            yz_slope = yz_slope2;
            
            p0 = p1;
        }
        
        return true;
    }
    
    
    public static boolean similarSlopes(double m1, double m2, double threshold) {
        return Math.abs(m2 - m1) <= threshold;
    }
    
    public static void test01() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(0, 0, 0));
        points.add(new ThreeDPoint(1, 1, 1));
        points.add(new ThreeDPoint(2, 2, 2));
        
        System.out.println(collinear(points, 0));
    }
    
    public static void test02() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(0, 0, 0));
        points.add(new ThreeDPoint(1, 1, 1));
        points.add(new ThreeDPoint(2, 2, 2));
        points.add(new ThreeDPoint(3, 3, 3));
        
        System.out.println(collinear(points, 0));
    }
    
    public static void test03() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(-1, 0, 2));
        points.add(new ThreeDPoint(1, 1, 4));
        points.add(new ThreeDPoint(3, 2, 6));
        
        System.out.println(collinear(points, 0));
    }
    
    public static void test04() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(-1, -1, -1));
        points.add(new ThreeDPoint(-0.5, -0.5, -0.5));
        points.add(new ThreeDPoint(0, 0, 0));
        points.add(new ThreeDPoint(0.5, 0.5, 0.5));
        points.add(new ThreeDPoint(1, 1, 1));
        points.add(new ThreeDPoint(2, 2, 2));
        
        System.out.println(collinear(points, 0));
    }
    
    public static void test05() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(1, 2, 3));
        points.add(new ThreeDPoint(0, 3, 7));
        points.add(new ThreeDPoint(3, 5, 11));

        System.out.println(collinear(points, 0));
    }
    
    public static void test06() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(0, 3, -4));
        points.add(new ThreeDPoint(1, 2, -2));
        points.add(new ThreeDPoint(3, 0, 1));

        System.out.println(collinear(points, 0));
    }
   
    public static void test07() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(2, 3, 1));
        points.add(new ThreeDPoint(5, 4, 3));
        points.add(new ThreeDPoint(2, 1, 2));

        System.out.println(collinear(points, 0));
    }
    
    public static void test08() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(-2, 3, 5));
        points.add(new ThreeDPoint(1, 2, 3));
        points.add(new ThreeDPoint(7, 0, -1));

        System.out.println(collinear(points, 0));
    }
    
    public static void main(String[] args) {
        test08();
    }
}
