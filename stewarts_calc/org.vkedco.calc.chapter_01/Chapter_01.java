package org.vkedco.calc.chapter_01;

import org.vkedco.calc.utils.Function;
import org.vkedco.calc.utils.FunctionLimit;

/**
 *
 * @author vladimir kulyukin
 */
public class Chapter_01 {
    
    public static void example_02_p74(double a, double delta, double step, double error) {
        Function f = new Example02_p74();
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnInterval(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromLeft(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromRight(f, a, delta, step));
        
        boolean limitExists = FunctionLimit.limitExistsAt(f, a, delta, step, error);
        System.out.println(limitExists);
        System.out.println(FunctionLimit.limitAt(f, a, delta, step)); 
    }
    
    public static void ex_17_p79(double a, double delta, double step, double error) {
        Function f = new F_ex17_p79();
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnInterval(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromLeft(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromRight(f, a, delta, step));
        
        boolean limitExists = FunctionLimit.limitExistsAt(f, a, delta, step, error);
        System.out.println(limitExists);
        System.out.println(FunctionLimit.limitAt(f, a, delta, step)); 
    }
    
    public static void ex_18_p79(double a, double delta, double step, double error) {
        Function f = new F_ex18_p79();
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnInterval(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromLeft(f, a, delta, step));
        FunctionLimit.displayFunctionValueInterval(FunctionLimit.generateFunctionValuesOnIntervalFromRight(f, a, delta, step));
        
        boolean limitExists = FunctionLimit.limitExistsAt(f, a, delta, step, error);
        System.out.println(limitExists);
        System.out.println(FunctionLimit.limitAt(f, a, delta, step)); 
    }
    
    public static void main(String[] args) {    
        ex_18_p79(2, 0.01, 0.0001, 0.1);
    }
    
}
