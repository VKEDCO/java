package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.RiemannSum;
import org.vkedco.calc.utils.Function;
import org.vkedco.calc.utils.FunctionLimit;
import org.vkedco.calc.utils.IntegralAsSum;
import org.vkedco.calc.utils.Partition;

/**
 * @author vladimir kulyukin
 */
public class Chapter_05 {

    final static String LP_AREA = "Left Point Area";
    final static String RP_AREA = "Right Point Area";
    final static String MP_AREA = "Mid Point Area";
    final static String INTEGRAL = "Integral As Sum";
    final static String EQ = " = ";
    
    public static void ex01_p335() {
        double[] p = {0, 1, 2, 3, 4};
        Function f = new F_ex01_p335();
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex02_p335() {
        double[] p = {0, 1, 2, 3, 4};
        Function f = new F_ex02_p335();
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex03_p335() {
        Function f = new F_ex03_p335();
        double[] p = {0, 1, 2, 3, 4};
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex04_p335() {
        Function f = new F_ex04_p335();
        double[] p = {0, 1, 2, 3, 4};
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex05_p335() {
        Function f = new F_ex05_p335();
        double[] p = Partition.partition(-1, 2, 0.5);
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(-1, 2, 0.001), 0.001));
    }
     
    public static void ex06_p335() {
        Function f = new F_ex06_p335();
        double[] p = Partition.partition(0, 2, 0.5);
        double[] ppoints = {0.25, 1, 1.25, 2.0};
        System.out.println("Area" + EQ +  RiemannSum.customPointArea(f, p, ppoints));
    }
    
    public static void ex07_p335() {
        Function f = new F_ex07_p335();
        double[] p = Partition.partition(0, Math.PI, Math.PI/4);
        double[] pps = {Math.PI/6, Math.PI/3, 2*Math.PI/3, 5*Math.PI/6};
        System.out.println("Area" + EQ +  RiemannSum.customPointArea(f, p, pps));
    }
    
    public static void ex09_p335() {
        Function f = new F_ex09_p335();
        double[] p = Partition.partition(0, 3, 0.1);
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
    }
    
    public static void ex23_p335() {
        int k = -1;
        int n = 5;
        double denom = 4.0*5;
        double delta = Math.PI/denom;
        Function f = new F_ex23_p335(0);
        double[] p = Partition.partition(k*Math.PI, k*Math.PI + (Math.PI/4), Math.PI/denom);
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
    }
    
    public static void ex25_p335() {
        double[] p = Partition.partition(0, Math.PI, Math.PI/10);
        Function f = new F_ex25_p335();
        System.out.println(LP_AREA + EQ + RiemannSum.leftPointArea(f, p));
        System.out.println(RP_AREA + EQ + RiemannSum.rightPointArea(f, p));
        System.out.println(MP_AREA + EQ + RiemannSum.midPointArea(f, p));
    }
    
    public static void ex26_p336() {
        System.out.println(FunctionLimit.limitAtInfinity(new F_ex26_p336(2), 0, 1, 10000));
    }
    
    public static void example01_p337() {
        double[] p =  {-2, -1.5, -1, -0.3, 0.2, 1};
        double[] ps = {-1.8, -1.2, -0.3, 0, 0.7};
        double[] p1 = {1, 3, 10};
        System.out.println("Riemann sum = " + RiemannSum.customPointArea(new F_example01_p337(), p, ps));
        System.out.println("||P|| = " + Partition.norm(p1));
    }
    
    public static void example03a_p338() {
        System.out.println("Riemann sum = " + RiemannSum.midPointArea(new F_example03a_p338(), 0, 1, 0.001));
        System.out.println("Riemann sum = " + RiemannSum.leftPointArea(new F_example03a_p338(), 0, 1, 0.001));
        System.out.println("Riemann sum = " + RiemannSum.rightPointArea(new F_example03a_p338(), 0, 1, 0.001));
        System.out.println("pi/4 = " + Math.PI/4.0);
    }
    
    public static void example03b_p338() {
        double mid_1_3 = RiemannSum.midPointArea(new F_example03b_p338(), 1, 3, 0.001);
        double mid_0_1 = RiemannSum.midPointArea(new F_example03b_p338(), 0, 1, 0.001);
        System.out.println("mid[1,3] = " + mid_1_3);
        System.out.println("mid[0,1] = " + mid_0_1);
        System.out.println("mid[1,3]-mid[0,1] = " + (mid_1_3 + mid_0_1));
        
        double left_1_3 = RiemannSum.leftPointArea(new F_example03b_p338(), 1, 3, 0.001);
        double left_0_1 = RiemannSum.leftPointArea(new F_example03b_p338(), 0, 1, 0.001);
        System.out.println("left[1,3] = " + left_1_3);
        System.out.println("left[0,1] = " + left_0_1);
        System.out.println("left[1,3]-left[0,1] = " + (left_1_3 + left_0_1));

        double right_1_3 = RiemannSum.rightPointArea(new F_example03b_p338(), 1, 3, 0.001);
        double right_0_1 = RiemannSum.rightPointArea(new F_example03b_p338(), 0, 1, 0.001);
        System.out.println("right[1,3] = " + right_1_3);
        System.out.println("right[0,1] = " + right_0_1);
        System.out.println("right[1,3]-right[0,1] = " + (right_1_3 + right_0_1));
    }
    
    public static void example04_p340() {
        Function f = new F_example_04_p340();
        for(int num_steps = 1000; num_steps <= 55000; num_steps += 1000) {
            System.out.println(num_steps + " | " + RiemannSum.infiniteLimitOfRegularPartition(f, 0, 3, num_steps));
        }
    }
    
    public static void example04b_p340() {
        Function f = new F_example_04_p340();
        double from = 0;
        double upto = 3;
        System.out.println("LP = " + RiemannSum.leftPointArea(f, from, upto, 0.001));
        System.out.println("RP = " + RiemannSum.rightPointArea(f, from, upto, 0.001));
        System.out.println("MP = " + RiemannSum.midPointArea(f, from, upto, 0.001));
    }
    
    public static void example05_p341() {
        Function f = new F_example_05_p341();
        for(int num_steps = 1000; num_steps <= 5000; num_steps += 1000) {
            System.out.println(num_steps + " | " + RiemannSum.infiniteLimitOfRegularPartition(f, 1, 2, num_steps));
        }
    }
    
    public static void example05b_p341() {
        Function f = new F_example_05_p341();
        
        for(int n = 1; n <= 10; n += 1) {
            System.out.println("n = " + n + ": " + RiemannSum.midPointRule(f, 1, 2, n));
            System.out.println("RS = " + RiemannSum.infiniteLimitOfRegularPartition(f, 1, 2, 1000));
        }
    }
    
    public static void example_07_p343() {
        Function f = new F_example_07_p343();
        System.out.println("MPR(5) = " + RiemannSum.midPointRule(f, 1, 4, 5));
        System.out.println("MPR(10) = " + RiemannSum.midPointRule(f, 1, 4, 10));
        System.out.println("MPR(20) = " + RiemannSum.midPointRule(f, 1, 4, 20));
        System.out.println("RS  = " + RiemannSum.infiniteLimitOfRegularPartition(f, 1, 4, 2000));
    }
    
    public static void ex_09_p344() {
        System.out.println(RiemannSum.midPointRule(new F_ex09_p344(), 0, 5, 5));
        System.out.println("RS = " + RiemannSum.infiniteLimitOfRegularPartition(new F_ex09_p344(), 0, 5, 1000));
    }
    
    public static void ex_25_p345() {
        Function f = new F_ex25_p345();
        System.out.println(RiemannSum.midPointRule(f, -3, 0, 100));
        System.out.println(RiemannSum.infiniteLimitOfRegularPartition(f, -3, 0, 1000));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        example05_p341();
    }
    
}
