package org.vkedco.calc.chapter_05;

import org.vkedco.calc.utils.AreaAsSum;
import org.vkedco.calc.utils.Function;
import org.vkedco.calc.utils.IntegralAsSum;
import org.vkedco.calc.utils.Partition;

/**
 *
 * @author vladimir kulyukin
 */
public class Chapter_05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ex05_p335();
    }
    
    final static String LP_AREA = "Left Point Area";
    final static String RP_AREA = "Right Point Area";
    final static String MP_AREA = "Mid Point Area";
    final static String INTEGRAL = "Integral As Sum";
    final static String EQ = " = ";
    
    public static void ex01_p335() {
        double[] p = {0, 1, 2, 3, 4};
        Function f = new F_ex01_p335();
        System.out.println(LP_AREA + EQ + AreaAsSum.area_left_point(f, p));
        System.out.println(RP_AREA + EQ + AreaAsSum.area_right_point(f, p));
        System.out.println(MP_AREA + EQ + AreaAsSum.area_mid_point(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex02_p335() {
        double[] p = {0, 1, 2, 3, 4};
        Function f = new F_ex02_p335();
        System.out.println(LP_AREA + EQ + AreaAsSum.area_left_point(f, p));
        System.out.println(RP_AREA + EQ + AreaAsSum.area_right_point(f, p));
        System.out.println(MP_AREA + EQ + AreaAsSum.area_mid_point(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex03_p335() {
        Function f = new F_ex03_p335();
        double[] p = {0, 1, 2, 3, 4};
        System.out.println(LP_AREA + EQ + AreaAsSum.area_left_point(f, p));
        System.out.println(RP_AREA + EQ + AreaAsSum.area_right_point(f, p));
        System.out.println(MP_AREA + EQ + AreaAsSum.area_mid_point(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
    public static void ex04_p335() {
        Function f = new F_ex04_p335();
        double[] p = {0, 1, 2, 3, 4};
        System.out.println(LP_AREA + EQ + AreaAsSum.area_left_point(f, p));
        System.out.println(RP_AREA + EQ + AreaAsSum.area_right_point(f, p));
        System.out.println(MP_AREA + EQ + AreaAsSum.area_mid_point(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(0, 4, 0.01), 0.01));
    }
    
     public static void ex05_p335() {
        Function f = new F_ex05_p335();
        double[] p = Partition.partition(-1, 2, 0.5);
        System.out.println(LP_AREA + EQ + AreaAsSum.area_left_point(f, p));
        System.out.println(RP_AREA + EQ + AreaAsSum.area_right_point(f, p));
        System.out.println(MP_AREA + EQ + AreaAsSum.area_mid_point(f, p));
        System.out.println(INTEGRAL + EQ + IntegralAsSum.sum(f, Partition.partition(-1, 2, 0.001), 0.001));
    }
    
}
