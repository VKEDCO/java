package org.vkedco.calc.utils;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 *
 * @author vladimir kulyukin
 */
public class FunctionLimit {
    
    public static ArrayList<ImmutablePair<Double, Double>> generateFunctionValuesOnInterval(Function f, double a, double delta, double step) {
        ArrayList<ImmutablePair<Double, Double>> fvalues = new ArrayList<>();
        for(double x = a-delta; x <= a+delta; x += step) {
            if ( x != a ) {
                Double xd = x;
                Double yd = f.v(x);
                ImmutablePair<Double, Double> xy = new ImmutablePair<>(xd, yd);
                fvalues.add(xy);
            }
        }
        return fvalues;
    }
    
    public static ArrayList<ImmutablePair<Double, Double>> generateFunctionValuesOnIntervalFromLeft(Function f, double a, double delta, double step) {
        ArrayList<ImmutablePair<Double, Double>> fvalues = new ArrayList<>();
        for(double x = a-delta; x < a; x += step) {
                Double xd = x;
                Double yd = f.v(x);
                ImmutablePair<Double, Double> xy = new ImmutablePair<>(xd, yd);
                fvalues.add(xy);
        }
        return fvalues;
    }
    
    public static ArrayList<ImmutablePair<Double, Double>> generateFunctionValuesOnIntervalFromRight(Function f, double a, double delta, double step) {
        ArrayList<ImmutablePair<Double, Double>> fvalues = new ArrayList<>();
        for(double x = a+delta; x > a; x -= step) {
                Double xd = x;
                Double yd = f.v(x);
                ImmutablePair<Double, Double> xy = new ImmutablePair<>(xd, yd);
                fvalues.add(xy);
        }
        return fvalues;
    }
    
    static final String ARROW = "-->";
    public static void displayFunctionValueInterval(ArrayList<ImmutablePair<Double, Double>> interval) {
        for(ImmutablePair<Double, Double> p: interval) {
            System.out.println(p.getLeft() + ARROW + p.getRight());
        }
    }
    
    public static boolean limitExistsAt(Function f, double a, double delta, double step, double error) {
        ArrayList<ImmutablePair<Double, Double>> leftFmap = FunctionLimit.generateFunctionValuesOnIntervalFromLeft(f, a, delta, step);
        ArrayList<ImmutablePair<Double, Double>> rightFmap = FunctionLimit.generateFunctionValuesOnIntervalFromRight(f, a, delta, step);
        double leftMean = 0;
        double rightMean = 0;
        for(ImmutablePair<Double, Double> lp: leftFmap) {
            leftMean += lp.getRight();
        }
        for(ImmutablePair<Double, Double> rp: rightFmap) {
            rightMean += rp.getRight();
        }
        leftMean /= leftFmap.size();
        rightMean /= rightFmap.size();
        System.out.println("left mean = " + leftMean);
        System.out.println("right mean = " + rightMean);
        return Math.abs(leftMean - rightMean) <= error;
    }
    
    public static double limitAt(Function f, double a, double delta, double step) {
        ArrayList<ImmutablePair<Double, Double>> leftFmap = FunctionLimit.generateFunctionValuesOnIntervalFromLeft(f, a, delta, step);
        ArrayList<ImmutablePair<Double, Double>> rightFmap = FunctionLimit.generateFunctionValuesOnIntervalFromRight(f, a, delta, step);
        double leftMean = 0;
        double rightMean = 0;
        for(ImmutablePair<Double, Double> lp: leftFmap) {
            leftMean += lp.getRight();
        }
        for(ImmutablePair<Double, Double> rp: rightFmap) {
            rightMean += rp.getRight();
        }
        leftMean /= leftFmap.size();
        rightMean /= rightFmap.size();
        return (leftMean+rightMean)/2;
    }
    
    public static double limitAtInfinity(Function f, double start, double step, int num_steps) {
        double x = start;
        double y = 0;
        while ( num_steps >= 0 ) {
            y = f.v(x);
            x += step;
            num_steps--;
        }
        return y;
    }
}
