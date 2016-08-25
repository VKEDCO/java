package org.vkedco.wavelets.tests;

import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.wavelets.haar.OneDHWT;
import org.vkedco.wavelets.utils.Utils;

/**
 * ============================================================
 * @author Vladimir Kulyukin
 * 
 * Some examples and solutions to exercises from Ch. 01 of
 * "Wavlets Made Easy" by Yves Nievergelt.
 * 
 * Chapter 4.1 in "Ripples in Mathematics" by Jensen & la Cour-Harbo
 * ============================================================
 */
public class OneDHWTTests {
    
    // Run ordered and inverse ordered fast haar wavelet transform
    public static void testOrdFHWT(double[] signal) {
        System.out.println("Original signal:");
        Utils.displaySample(signal);
 
        OneDHWT.ordFHWT(signal);
        System.out.println("Ordered FHWT:");
        Utils.displaySample(signal);
        
        //OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        //System.err.println("Ordered Fast Inverse Haar Transform");
        //OneDHaar.displaySample(sample);
    }
    
    // Run in-place forward and inverse fast haar wavelet transform
    public static void testInpInvFHWT(double[] signal) {
        System.out.println("Original signal:");
         Utils.displaySample(signal);
         
         OneDHWT.inpFHWT(signal);
         System.out.println("In-Place FHWT:");
         Utils.displaySample(signal);
         
         System.out.println("In-Place Inv FHWT:");
         OneDHWT.inpInvFHWT(signal);
         Utils.displaySample(signal);
     }
    
    // Example 1.12, p. 19.
    public static void ex_1_12_p19() {
        final double[] sample = {3, 1, 0, 4, 8, 6, 9, 9};
        testOrdFHWT(sample);
    }
    
    // Ex. 1.15, p. 10
    public static void ex_1_15_p10() {
        final double[] sample = {9, 1};
        testOrdFHWT(sample);
    }
    
    // Ex. 1.16, p. 20
    public static void ex_1_3_2_4_p11() {
        final double[] sample = {5, 1, 2, 8};
        testOrdFHWT(sample);
    }
    
    // Ex. 1.16, p. 20
     public static void ex_1_16_p20() {
        final double[] sample = {1, 7};
        testOrdFHWT(sample);
    }
     
    // Ex. 1.17, p. 21
     public static void ex_1_17_p21() {
        final double[] sample = {2, 4, 8, 6};
        testOrdFHWT(sample);
    }
     
     public static void ex_1_18_p21() {
        final double[] sample = {5, 7, 3, 1};
        testOrdFHWT(sample);
     }
     
     // Ex. 1.19, p. 21
     public static void ex_1_19_p21() {
        final double[] sample = {8, 6, 7, 3, 1, 1, 2, 4};
        testOrdFHWT(sample);
    }
     
    // Example 1.17, p. 25
     public static void example_1_17_p25() {
        final double[] sample = {5, 1, 2, 8};
        testInpInvFHWT(sample);
    }
     
     // Example 1.18, p. 25
     public static void example_1_18_p25() {
         final double[] sample = {3, 1, 0, 4, 8, 6, 9, 9};
         testInpInvFHWT(sample);
     }
     
     // Ex. 1.25, p. 26
     public static void ex_1_25_p26() {
         final double[] sample = {2, 4, 8, 6};
         testInpInvFHWT(sample);
     }
     
     // Ex. 1.26, p. 26
     public static void ex_1_26_p26() {
         final double[] sample = {5, 7, 3, 1};
         testInpInvFHWT(sample);
     }
     
     // Ex. 1.27, p. 26
     public static void ex_1_27_p26() {
         final double[] sample = {8, 6, 7, 3, 1, 1, 2, 4};
         testInpInvFHWT(sample); 
     }
     
      // Ex. 1.28, p. 26
     public static void ex_1_28_p26() {
         final double[] sample = {3, 1, 9, 7, 7, 9, 5, 7};
         testInpInvFHWT(sample); 
     }
     
    
    // Exercise 1.31, p. 27 in the book "Wavelet Algorithms"
    public static void ex_1_31_p27(double[] haar_coeffs, double[] orig_ary) {
        if (haar_coeffs.length != 4 || orig_ary.length != 4) {
            return;
        }
        set_s0_a4(haar_coeffs, orig_ary);
        set_s1_a4(haar_coeffs, orig_ary);
        set_s2_a4(haar_coeffs, orig_ary);
        set_s3_a4(haar_coeffs, orig_ary);
    }
    // haar_coeffs[0] = a_{0}^{2-2}
    // haar_coeffs[1] = c_{0}^{2-1}
    // haar_coeffs[2] = c_{0}^{2-2}
    // haar_coeffs[3] = c_{1}^{2-1}

    public static void set_s0_a4(double[] haar_coeffs, double[] orig_ary) {
        if (haar_coeffs.length != 4 || orig_ary.length != 4) {
            return;
        }
        orig_ary[0] = haar_coeffs[0] + haar_coeffs[2] + haar_coeffs[1];
    }

    public static void set_s1_a4(double[] haar_coeffs, double[] orig_ary) {
        if (haar_coeffs.length != 4 || orig_ary.length != 4) {
            return;
        }
        orig_ary[1] = haar_coeffs[0] + haar_coeffs[2] - haar_coeffs[1];
    }

    public static void set_s2_a4(double[] haar_coeffs, double[] orig_ary) {
        if (haar_coeffs.length != 4 || orig_ary.length != 4) {
            return;
        }
        orig_ary[2] = haar_coeffs[0] - haar_coeffs[2] + haar_coeffs[3];
    }

    public static void set_s3_a4(double[] haar_coeffs, double[] orig_ary) {
        if (haar_coeffs.length != 4 || orig_ary.length != 4) {
            return;
        }
        orig_ary[3] = haar_coeffs[0] - haar_coeffs[2] - haar_coeffs[3];
    }

    public static void test_ex_1_31_p27_01(double[] haar_coeffs) {
        double[] orig_ary = {0, 0, 0, 0};
        OneDHWTTests.ex_1_31_p27(haar_coeffs, orig_ary);
        Utils.displaySample(orig_ary);
    }

    public static void testInplaceFastInverseHaarTransformForNumSweeps(double[] sample, int num_sweeps) {
        OneDHWT.inpInvFHWTForNumIters(sample, num_sweeps);
        Utils.displaySample(sample);
    }

    public static void example_1_20_p29() {
        final double[] sample = {5, 4};
        testInplaceFastInverseHaarTransformForNumSweeps(sample, 1);
    }

    public static void example_1_21_p29() {
        final double[] sample = {4, 2, -1, -3};
        testInplaceFastInverseHaarTransformForNumSweeps(sample, 2);
    }

    public static void ex_1_37_p30() {
        final double[] sample = {7, 2};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 1);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 1);
        System.out.print("Original ");
        Utils.displaySample(sample);
        System.out.println();
    }

    // this is a solution to the same exercise except that in stead of
    // the in-place transforms, ordered transforms are used
    public static void ex_1_37_p30_ordered() {
        final double[] sample = {7, 2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 1);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
    }

    public static void ex_1_38_p30() {
        final double[] sample = {6, -3};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 1);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 1);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_38_p30_ordered() {
        final double[] sample = {6, -3};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 1);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_39_p30() {
        final double[] sample = {6, 2, 1, 2};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 2);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 2);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_39_p30_ordered() {
        final double[] sample = {6, 2, 1, 2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.39 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHWT.inpInvFHWTForNumIters(sample, 2);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_40_p30() {
        final double[] sample = {4, 2, 2, 0};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 2);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 2);
        System.out.print("Original ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_40_p30_ordered() {
        final double[] sample = {4, 2, 2, 0};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHWT.inpInvFHWTForNumIters(sample, 2);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_41_p30() {
        final double[] sample = {4, -1, -1, 2, 0, 1, -2, -2};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 3);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 3);
        System.out.print("Original ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_41_p30_ordered() {
        final double[] sample = {4, -1, -1, 2, 0, 1, -2, -2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHWT.inpInvFHWTForNumIters(sample, 3);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_42_p30() {
        final double[] sample = {5, 1, 1, 0, -3, -1, 0, 1};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        Utils.displaySample(sample);
        OneDHWT.inpInvFHWTForNumIters(sample, 3);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 3);
        System.out.print("Original ");
        Utils.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_42_p30_ordered() {
        final double[] sample = {5, 1, 1, 0, -3, -1, 0, 1};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHWT.inpInvFHWTForNumIters(sample, 3);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.ordInvFHWT(sample);
        System.out.print("Inverted ");
        Utils.displaySample(sample);
        OneDHWT.ordFHWT(sample);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        System.out.println();
    }

    // solution to Ex 1.43, p. 30 is in OneDHaar.reconstructSampleTransformedInPlaceForNumIters()
    public static void ex_1_44_p30() {
        final double[] sample = {3, 1, -1, 1, 7, 1, 1, -1};
        System.out.print("Transformed ");
        Utils.displaySample(sample);
        OneDHWT.reconstructSampleFromInpFHWTForNumItersWithOutput(sample, 2);
        System.out.print("Reconstructed ");
        Utils.displaySample(sample);
        OneDHWT.inpFHWTForNumIters(sample, 2);
        System.out.print("Transformed ");
        Utils.displaySample(sample);
    }

    public static void testInPlaceFastHaarWaveletTransform(double[] sample, int num_sweeps) {
        Utils.displaySample(sample);
        for (int i = 1; i <= num_sweeps; i++) {
            OneDHWT.doNthSweepOfInPlaceFastHaarWaveletTransform(sample, i);
            System.out.print("Sweep " + i + ": ");
            Utils.displaySample(sample);
        }
    }

    public static void testInPlaceFastInverseHaarWaveletTransform(double[] sample, int num_sweeps) {
        Utils.displaySample(sample);
        int n = (int) (Math.log(sample.length) / Math.log(2.0));
        for (int i = (n - num_sweeps)+1; i <= n; i++) {
            OneDHWT.doNthIterOfInPlaceFastInverseHaarWaveletTransform(sample, i);
            System.out.print("Sweep " + i + ": ");
            Utils.displaySample(sample);
        }
    }

    public static void testOrderedFastInverseHaarWaveletTransform(double[] haar_transformed_sample) {
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        OneDHWT.ordInvFHWT(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        Utils.displaySample(haar_transformed_sample);
    }

    public static void doOrderedInverseHaar_Example_1_11_p18() {
        final double[] haar_transformed_sample = {4, -1, 2, 3};
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        OneDHWT.ordInvFHWT(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        Utils.displaySample(haar_transformed_sample);
    }

    public static void doOrderedInverseHaar_Example_1_12_p19() {
        final double[] haar_transformed_sample = {5, -3, 0, -1, 1, -2, 1, 0};
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        OneDHWT.ordInvFHWT(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        Utils.displaySample(haar_transformed_sample);
    }

    // 5.0
    // -3.0	
    // 0.0	-1.0	
    // 1.0	-2.0	1.0	0.0	
    public static void displayOrderedFreqsFromOrderedHaar_Example_1_12_p19() {
        final double[] haar_transformed_sample = {5, -3, 0, -1, 1, -2, 1, 0};
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHWT.displayOrdFreqsOfOrdFHWT(haar_transformed_sample);
    }

    public static void displayOrderedFreqsFromInPlacedHaar_Example_1_17_p25() {
        final double[] haar_transformed_sample = {4, 2, -1, -3};
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHWT.displayOrdFreqsOfInpFHWT(haar_transformed_sample);
    }

    public static void displayOrderedFreqsFromInPlacedHaar_Example_1_18_p25() {
        final double[] haar_transformed_sample = {5, 1, 0, -2, -3, 1, -1  , 0};
        System.out.print("Transformed sample: ");
        Utils.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHWT.displayOrdFreqsOfInpFHWT(haar_transformed_sample);
    }
    
    // Here is the output for example 1.6.1, p. 31-32:
    // 25.9375
    // 3.6875	
    // -4.625	-5.0	
    // -4.0	-1.75	3.75	-3.75	
    // 11.0	-9.0	4.5	2.0	-3.0	4.5	-0.5	-3.0	
 
    // uses in-place fast haar wavelet transform and displays ordered
    // frequenices from lowest to highest from the transfomred sample
    // each frequencie is displayed on a separate line
    public static void creakWaterTemperatureAnalysisInPlace() {
        double[] temperature_sample = {32, 10, 20, 38, 37, 28, 38, 34, 
                                       18, 24, 18,  9, 23, 24, 28, 34};
        OneDHWT.inpFHWTForNumIters(temperature_sample, 4);
        System.out.print("Transformed "); Utils.displaySample(temperature_sample);
        OneDHWT.displayOrdFreqsOfInpFHWT(temperature_sample);
    }
    
    // uses ordered fast haar wavelet transform and displays ordered
    // frequencies from lowest to highest from the transformed sample;
    // each frequency is displayed on a separate line
    public static void createWaterTemperatureAnalysisOrdered() {
        double[] temperature_sample = {32, 10, 20, 38, 37, 28, 38, 34, 
                                       18, 24, 18,  9, 23, 24, 28, 34};
        OneDHWT.ordFHWT(temperature_sample);
        System.out.print("Transformed "); Utils.displaySample(temperature_sample);
        OneDHWT.displayOrdFreqsOfOrdFHWT(temperature_sample);
    }
    
    /*
     Here is the output for exercise 1.47, p. 33
     40.494
     1.481
    -5.650	1.738	
    -11.825	0.125	1.250	4.275	
    -2.5	0.650	-0.75	0.5	1.0	1.5	-5.550      1.0
    
    Here is an interpretation of this output.
    
    Line 00: 40.494
    Line 00: The average frost depth for the months of December and January is 40.494cm.
    
    Line 01: 1.481
    Line 01: From December to January the average frost depth changed by (-2)*1.481cm.
    
    Line 02: -5.650	1.738	
    Line 02: From the first 2 weeks of December to the second two weeks of December
             the average frost depth changed by (-2)*(-5.650). From the first 2 weeks of
             January to the second 2 weeks of January the average frost depth changed
             by (-2)*1.738.
    
    Line 03: -11.825	0.125	1.250	4.275
    Line 03: From the 1st week of December to the 2nd week of December, the average
             frost depth changed by (-2)*(-11.825)cm. 
             From the 3rd week of December to the 4th week of December, the average
             frost depth changed by (-2)*(0.125)cm.
             From the 1st week of January to the 2nd week of January, the average
             frost depth changed by (-2)*(1.250)cm.
             From the 3rd week of January to the 4th week of January, the average
             frost depth changed by (-2)*(4.275)cm.
    
    Line 04: -2.5	0.650	-0.75	0.5	1.0	1.5	-5.550      1.0
    Line 04: In the 1st week of December, the average frost depth changed by (-2)*(-2.5)cm. 
             In the 2nd week of December, the average frost depth changed by (-2)*(0.65)cm.
             In the 3rd week of December, the average frost depth changed by (-2)(-0.75)cm.
             In the 4th week of December, the average frost depth changed by (-2)(0.5)cm.
             In the 1st week of January, the average frost depth changed by (-2)(1.0)cm.
             In the 2nd week of January, the average frost depth changed by (-2)(1.5)cm.
             In the 3rd week of January, the average frost depth changed by (-2)(-5.55)cm.
             In the 4th week of January, the average frost depth changed by (-2)(1.0)cm.
     */
    public static void ex_1_47_p33() {
        double[] frost_depths = {22.0, 27.0, 48.8, 47.5, 47.0, 48.5, 48.0, 47.0,
                                 43.0, 41.0, 41.0, 38.0, 36.0, 47.1, 34.0, 32.0};
        double[] frost_depths_copy = new double[16];
        System.arraycopy(frost_depths, 0, frost_depths_copy, 0, 16);
        OneDHWT.inpFHWTForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); Utils.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHWT.displayOrdFreqsOfInpFHWT(frost_depths);
        System.out.println();
        
        OneDHWT.ordFHWT(frost_depths_copy);
        System.out.print("Ordered Transformed "); Utils.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHWT.displayOrdFreqsOfOrdFHWT(frost_depths_copy);
    }
    
    /*
     * Here is the output for ex 1.48, p. 33.
     * 35.619
     * -6.519
     * -7.15	-0.988	
     * -7.95	-2.75	-0.5	0.875	
     * -2.0	-2.90	0.0	0.0     -0.650    -0.35     -1.0    -6.75	
     */
     public static void ex_1_48_p33() {
        double[] frost_depths = {12.0, 16.0, 27.0, 32.8, 33.5, 33.5, 39.0, 39.0,
                                 40.0, 41.3, 41.3, 42.0, 43.0, 45.0, 35.5, 49.0};
        double[] frost_depths_copy = new double[16];
        System.arraycopy(frost_depths, 0, frost_depths_copy, 0, 16);
        OneDHWT.inpFHWTForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); Utils.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHWT.displayOrdFreqsOfInpFHWT(frost_depths);
        System.out.println();
        
        OneDHWT.ordFHWT(frost_depths_copy);
        System.out.print("Ordered Transformed "); Utils.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHWT.displayOrdFreqsOfOrdFHWT(frost_depths_copy);
    }
     
     public static void ex_1_49_p33() {
        double[] frost_depths = {10.0, 12.0, 12.0, 7.0,  8.0, 9.1, 8.2,  9.4,
                                16.0, 15.0, 13.0, 11.0, 6.4, 9.0, 19.0, 118.0};
        double[] frost_depths_copy = new double[16];
        System.arraycopy(frost_depths, 0, frost_depths_copy, 0, 16);
        OneDHWT.inpFHWTForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); Utils.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHWT.displayOrdFreqsOfInpFHWT(frost_depths);
        System.out.println();
        
        OneDHWT.ordFHWT(frost_depths_copy);
        System.out.print("Ordered Transformed "); Utils.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHWT.displayOrdFreqsOfOrdFHWT(frost_depths_copy);
    }
     
     static void testOneDInPlaceFHWT(double[] sample, int n) {
         System.out.println("Original sample:");
         Utils.displaySample(sample);
         System.out.println();
         
         OneDHWT.inpFHWT(sample);
         System.out.println("Transformed sample:");
         Utils.displaySample(sample);
         System.out.println();
         
         OneDHWT.inpInvFHWTForNumIters(sample, n);
         System.out.println("Restored sample:");
         Utils.displaySample(sample);
         System.out.println();
     }
     
    static void testOrdFHWTForNumIters(double[] signal) {
        int n = (int)(Math.log(signal.length)/Math.log(2.0));
        double[] copy_signal = new double[signal.length];
        System.arraycopy(signal, 0, copy_signal, 0, signal.length);
        for(int i = 1; i <= n; i++) {
             System.out.println("num iters " + i);
             System.out.print("Original: "); Utils.displaySample(signal);
             OneDHWT.ordFHWTForNumIters(signal, i);
             System.out.print("Transformed: "); Utils.displaySample(signal);
             OneDHWT.ordInvFHWTForNumIters(signal, i);
             System.out.println("Reversed: "); Utils.displaySample(signal);
             for(int j = 0; j < signal.length; j++) {
                 if ( signal[j] != copy_signal[j] ) {
                     System.out.println("FALSE");
                     return;
                 }
             }
             System.out.println("TRUE");
        }
     }
     
     static void testNormalizedOrderedFHWT(double[] sample) {
        int n = (int)(Math.log(sample.length)/Math.log(2.0));
        double[] copy_sample = new double[sample.length];
        System.out.print("Original sample: "); Utils.displaySample(sample);
        OneDHWT.ordNormFHWT(sample);
        System.out.print("Transformed sample: "); Utils.displaySample(sample);
        OneDHWT.ordNormInvFHWT(sample);
        System.out.print("Restored sample: "); Utils.displaySample(sample);
     }
     
      static void testNormalizedOrderedFHWTForNumIters(double[] sample, double thresh) {
        int n = (int)(Math.log(sample.length)/Math.log(2.0));
        double[] copy_sample = new double[sample.length];
        System.arraycopy(sample, 0, copy_sample, 0, sample.length);
         for(int i = 1; i <= n; i++) {
             System.out.println("num iters " + i);
             System.out.print("Original: "); Utils.displaySample(sample);
             OneDHWT.ordNormFHWTForNumIters(sample, i);
             System.out.print("Transformed: "); Utils.displaySample(sample);
             OneDHWT.orderedNormInvFHWTForNumIters(sample, i);
             System.out.println("Reversed: "); Utils.displaySample(sample);
             for(int j = 0; j < sample.length; j++) {
                 if ( Math.abs(sample[j] - copy_sample[j]) > thresh ) {
                     System.out.println("FALSE");
                     return;
                 }
             }
             System.out.println("TRUE");
         }
     }
    
    static void ripplesTest01() {
        double[] sample01 = {56, 40, 8, 24, 48, 48, 40, 16};
        double[] sample02 = {56, 40, 8, 24, 48, 48, 40, 16};
        double[] sample03 = {56, 40, 8, 24, 48, 48, 40, 16};
        ripplesTestAux01(sample01, 1);
        ripplesTestAux01(sample02, 2);
        ripplesTestAux01(sample03, 3);
    }
    static void ripplesTestAux01(double[] sample, int num_iters) {
        OneDHWT.ordFHWTForNumIters(sample, num_iters);
        System.out.print(num_iters + ") " + "Forward: "); Utils.displaySample(sample);
        OneDHWT.ordInvFHWTForNumIters(sample, num_iters);
        System.out.print(num_iters + ") " + "Inverse: ");  Utils.displaySample(sample);
        System.out.println();
    }
    
    static void ripplesTest02() {
        double[] domain = Partition.partition(0, 511, 1);
        double[] range  = new double[512];
        Ripples_F_p25 f = new Ripples_F_p25();
        
        for(int i = 0; i < 512; i++)  {
            range[i] = f.v(domain[i]);
        }
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        OneDHWT.ordNormFHWTForNumIters(range, 3);
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D8");
        //for(int i = 256; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D7");
        //for(int i = 128; i < 256; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D6");
        //for(int i = 64; i < 128; i++)  {
        //    System.out.println(range[i]);
        //}
        
        System.out.println("S6");
        for(int i = 0; i < 64; i++)  {
            System.out.println(range[i]);
        }
    }
    
    static void ripplesTest03() {
        double[] domain = Partition.partition(0, 511, 1);
        double[] range  = new double[512];
        Ripples_F_p25 f = new Ripples_F_p25();
        
        for(int i = 0; i < 512; i++)  {
            range[i] = f.v(domain[i]);
        }
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        OneDHWT.ordNormFHWTForNumIters(range, 3);
        
        //System.out.println("RANGE");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(range[i]);
        //}
        
        int d8_start = 256; int d8_end = 511;
        int d7_start = 128; int d7_end = 255;
        int d6_start = 64;  int d6_end = 127;
        int s6_start = 0;   int s6_end = 63;
        
        // 06, 06, 07, d8
        double[] s06_d06_d07_d8 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d8_start && i <= d8_end ) {
                s06_d06_d07_d8[i] = range[i];
            }
            else {
                s06_d06_d07_d8[i] = 0;
            }
        }
        
        //System.out.println("S06_D06_D07_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d07_d8[i]);
        //}
        
        OneDHWT.orderedNormInvFHWTForNumIters(s06_d06_d07_d8, 3);
        
        //System.out.println("Inversed S06_D06_D07_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d07_d8[i]);
        //}
        
        // 06, 06, d7, 08
        double[] s06_d06_d7_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d7_start && i <= d7_end ) {
                s06_d06_d7_d08[i] = range[i];
            }
            else {
                s06_d06_d07_d8[i] = 0;
            }
        }
        
        //System.out.println("S06_D06_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d7_d08[i]);
        //}
        
        OneDHWT.orderedNormInvFHWTForNumIters(s06_d06_d7_d08, 3);
        
        //System.out.println("Inverse S06_D06_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d06_d7_d08[i]);
        //}
        
        // 06, d6, 07, 08
        double[] s06_d6_d07_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= d6_start && i <= d6_end ) {
                s06_d6_d07_d08[i] = range[i];
            }
            else {
                s06_d6_d07_d08[i] = 0;
            }
        }
        
        //System.out.println("S06_D6_D7_D8");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d6_d07_d08[i]);
        //}
        
        OneDHWT.orderedNormInvFHWTForNumIters(s06_d6_d07_d08, 3);
        
        //System.out.println("Inverse S06_D6_D07_D08");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(s06_d6_d07_d08[i]);
        //}
        
        // s6, 06, 07, 08
        double[] s6_d06_d07_d08 = new double[range.length];
        for(int i = 0; i < 512; i++) {
            if ( i >= s6_start && i <= s6_end ) {
                s6_d06_d07_d08[i] = range[i];
            }
            else {
                s6_d06_d07_d08[i] = 0;
            }
        }
        
        //System.out.println("S6_D06_D07_D08");
        //for(int i = 0; i < 512; i++) {
        //   System.out.println(s6_d06_d07_d08[i]);
        //}
        
        OneDHWT.orderedNormInvFHWTForNumIters(s6_d06_d07_d08, 3);
        
        System.out.println("Inversed S6_D06_D07_D08");
        for(int i = 0; i < 512; i++) {
            System.out.println(s6_d06_d07_d08[i]);
        }
        
        /*
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
        // Inversed S06_D06_D07_D8
        System.out.println("Inversed S06_D06_D07_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d07_d8[i]);
        }
        
        
        System.out.println("S06_D06_D7_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d7_d08[i]);
        }
        */
        
        /*
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
        System.out.println("Inversed S06_D06_D07_D8");
        for(int i = 0; i < 512; i++) {
            System.out.println(s06_d06_d07_d8[i]);
        }
        */
        
        //for(int i = 0; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D8");
        //for(int i = 256; i < 512; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D7");
        //for(int i = 128; i < 256; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("D6");
        //for(int i = 64; i < 128; i++)  {
        //    System.out.println(range[i]);
        //}
        
        //System.out.println("RANGE");
        //for(int i = 0; i < 512; i++) {
        //    System.out.println(range[i]);
        //}
    }
    
    
           
    
    public static void main(String[] args) {
        //double sample[] = {1, 2, 3, 4};
        //ex_1_15_p10();
        //ex_1_3_2_4_p11();
        //ex_1_12_p19();
        //ex_1_16_p20();
        //ex_1_19_p21();
        //testNormalizedOrderedFHWT(sample);
        double sig1[] = {1, 7};
        double sig2[] = {5, 1, 2, 8};
        double sig3[] = {3, 1, 0, 4, 8, 6, 9, 9};
        double sig4[] = {2.5, -1, -0.5, -0.5};
        OneDHWT.ordInvFHWTForNumIters(sig4, 2, 2);
        Utils.displaySample(sig4);
    }
}
