package org.vkedco.wavelets.tests;

import org.vkedco.calc.utils.Partition;
import org.vkedco.calc.utils.Ripples_F_p25;
import org.vkedco.wavelets.haar.OneDHaar;

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
public class OneDHaarTests {
    
    // Run ordered and inverse ordered fast haar wavelet transform
    public static void testOrderedFHWT(double[] sample) {
        System.out.println("Original sample:");
        OneDHaar.displaySample(sample);
 
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.println("Ordered Fast Haar Transform:");
        OneDHaar.displaySample(sample);
        
        //OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        //System.err.println("Ordered Fast Inverse Haar Transform");
        //OneDHaar.displaySample(sample);
    }
    
    // Run in-place forward and inverse fast haar wavelet transform
    public static void testInverseHaar(double[] sample) {
        System.out.println("Original sample:");
         OneDHaar.displaySample(sample);
         
         OneDHaar.inPlaceFastHaarWaveletTransform(sample);
         System.out.println("In-Place Fast Haar Transform:");
         OneDHaar.displaySample(sample);
         
         System.out.println("In-Place Fast Inverse Haar Transform:");
         OneDHaar.inPlaceFastInverseHaarWaveletTransform(sample);
         OneDHaar.displaySample(sample);
     }
    
    // Example 1.12, p. 19.
    public static void ex_1_12_p19() {
        final double[] sample = {3, 1, 0, 4, 8, 6, 9, 9};
        testOrderedFHWT(sample);
    }
    
    // Ex. 1.15, p. 20
    public static void ex_1_15_p20() {
        final double[] sample = {3, 9};
        testOrderedFHWT(sample);
    }
    
    // Ex. 1.16, p. 20
     public static void ex_1_16_p20() {
        final double[] sample = {1, 7};
        testOrderedFHWT(sample);
    }
     
    // Ex. 1.17, p. 21
     public static void ex_1_17_p21() {
        final double[] sample = {2, 4, 8, 6};
        testOrderedFHWT(sample);
    }
     
     public static void ex_1_18_p21() {
        final double[] sample = {5, 7, 3, 1};
        testOrderedFHWT(sample);
     }
     
     // Ex. 1.19, p. 21
     public static void ex_1_19_p21() {
        final double[] sample = {8, 6, 7, 3, 1, 1, 2, 4};
        testOrderedFHWT(sample);
    }
     
    // Example 1.17, p. 25
     public static void example_1_17_p25() {
        final double[] sample = {5, 1, 2, 8};
        testInverseHaar(sample);
    }
     
     // Example 1.18, p. 25
     public static void example_1_18_p25() {
         final double[] sample = {3, 1, 0, 4, 8, 6, 9, 9};
         testInverseHaar(sample);
     }
     
     // Ex. 1.25, p. 26
     public static void ex_1_25_p26() {
         final double[] sample = {2, 4, 8, 6};
         testInverseHaar(sample);
     }
     
     // Ex. 1.26, p. 26
     public static void ex_1_26_p26() {
         final double[] sample = {5, 7, 3, 1};
         testInverseHaar(sample);
     }
     
     // Ex. 1.27, p. 26
     public static void ex_1_27_p26() {
         final double[] sample = {8, 6, 7, 3, 1, 1, 2, 4};
         testInverseHaar(sample); 
     }
     
      // Ex. 1.28, p. 26
     public static void ex_1_28_p26() {
         final double[] sample = {3, 1, 9, 7, 7, 9, 5, 7};
         testInverseHaar(sample); 
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
        OneDHaarTests.ex_1_31_p27(haar_coeffs, orig_ary);
        OneDHaar.displaySample(orig_ary);
    }

    public static void testInplaceFastInverseHaarTransformForNumSweeps(double[] sample, int num_sweeps) {
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, num_sweeps);
        OneDHaar.displaySample(sample);
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
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    // this is a solution to the same exercise except that in stead of
    // the in-place transforms, ordered transforms are used
    public static void ex_1_37_p30_ordered() {
        final double[] sample = {7, 2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
    }

    public static void ex_1_38_p30() {
        final double[] sample = {6, -3};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_38_p30_ordered() {
        final double[] sample = {6, -3};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 1);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_39_p30() {
        final double[] sample = {6, 2, 1, 2};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 2);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 2);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_39_p30_ordered() {
        final double[] sample = {6, 2, 1, 2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.39 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 2);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_40_p30() {
        final double[] sample = {4, 2, 2, 0};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 2);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 2);
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_40_p30_ordered() {
        final double[] sample = {4, 2, 2, 0};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 2);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_41_p30() {
        final double[] sample = {4, -1, -1, 2, 0, 1, -2, -2};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 3);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 3);
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_41_p30_ordered() {
        final double[] sample = {4, -1, -1, 2, 0, 1, -2, -2};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 3);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_42_p30() {
        final double[] sample = {5, 1, 1, 0, -3, -1, 0, 1};
        System.out.println("In-Place Fast Inverse Haar Wavelet Transform");
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 3);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 3);
        System.out.print("Original ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    public static void ex_1_42_p30_ordered() {
        final double[] sample = {5, 1, 1, 0, -3, -1, 0, 1};
        System.out.println("Ordered Fast Inverse Haar Wavelet Transform");
        // Since the transformed sample in ex 1.40 is obtained with in-place 
        // transform, invert it and then apply ordered transforms to the original
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, 3);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(sample);
        System.out.print("Inverted ");
        OneDHaar.displaySample(sample);
        OneDHaar.orderedFastHaarWaveletTransform(sample);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        System.out.println();
    }

    // solution to Ex 1.43, p. 30 is in OneDHaar.reconstructSampleTransformedInPlaceForNumIters()
    public static void ex_1_44_p30() {
        final double[] sample = {3, 1, -1, 1, 7, 1, 1, -1};
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
        OneDHaar.reconstructSampleTransformedInPlaceForNumItersWithOutput(sample, 2);
        System.out.print("Reconstructed ");
        OneDHaar.displaySample(sample);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(sample, 2);
        System.out.print("Transformed ");
        OneDHaar.displaySample(sample);
    }

    public static void testInPlaceFastHaarWaveletTransform(double[] sample, int num_sweeps) {
        OneDHaar.displaySample(sample);
        for (int i = 1; i <= num_sweeps; i++) {
            OneDHaar.doNthSweepOfInPlaceFastHaarWaveletTransform(sample, i);
            System.out.print("Sweep " + i + ": ");
            OneDHaar.displaySample(sample);
        }
    }

    public static void testInPlaceFastInverseHaarWaveletTransform(double[] sample, int num_sweeps) {
        OneDHaar.displaySample(sample);
        int n = (int) (Math.log(sample.length) / Math.log(2.0));
        for (int i = (n - num_sweeps)+1; i <= n; i++) {
            OneDHaar.doNthIterOfInPlaceFastInverseHaarWaveletTransform(sample, i);
            System.out.print("Sweep " + i + ": ");
            OneDHaar.displaySample(sample);
        }
    }

    public static void testOrderedFastInverseHaarWaveletTransform(double[] haar_transformed_sample) {
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
    }

    public static void doOrderedInverseHaar_Example_1_11_p18() {
        final double[] haar_transformed_sample = {4, -1, 2, 3};
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
    }

    public static void doOrderedInverseHaar_Example_1_12_p19() {
        final double[] haar_transformed_sample = {5, -3, 0, -1, 1, -2, 1, 0};
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        OneDHaar.orderedFastInverseHaarWaveletTransform(haar_transformed_sample);
        System.out.print("Reconstructed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
    }

    // 5.0
    // -3.0	
    // 0.0	-1.0	
    // 1.0	-2.0	1.0	0.0	
    public static void displayOrderedFreqsFromOrderedHaar_Example_1_12_p19() {
        final double[] haar_transformed_sample = {5, -3, 0, -1, 1, -2, 1, 0};
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHaar.displayOrderedFreqsFromOrderedHaar(haar_transformed_sample);
    }

    public static void displayOrderedFreqsFromInPlacedHaar_Example_1_17_p25() {
        final double[] haar_transformed_sample = {4, 2, -1, -3};
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(haar_transformed_sample);
    }

    public static void displayOrderedFreqsFromInPlacedHaar_Example_1_18_p25() {
        final double[] haar_transformed_sample = {5, 1, 0, -2, -3, 1, -1  , 0};
        System.out.print("Transformed sample: ");
        OneDHaar.displaySample(haar_transformed_sample);
        System.out.println("Ordered Frequencies:");
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(haar_transformed_sample);
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
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(temperature_sample, 4);
        System.out.print("Transformed "); OneDHaar.displaySample(temperature_sample);
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(temperature_sample);
    }
    
    // uses ordered fast haar wavelet transform and displays ordered
    // frequencies from lowest to highest from the transformed sample;
    // each frequency is displayed on a separate line
    public static void createWaterTemperatureAnalysisOrdered() {
        double[] temperature_sample = {32, 10, 20, 38, 37, 28, 38, 34, 
                                       18, 24, 18,  9, 23, 24, 28, 34};
        OneDHaar.orderedFastHaarWaveletTransform(temperature_sample);
        System.out.print("Transformed "); OneDHaar.displaySample(temperature_sample);
        OneDHaar.displayOrderedFreqsFromOrderedHaar(temperature_sample);
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
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); OneDHaar.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(frost_depths);
        System.out.println();
        
        OneDHaar.orderedFastHaarWaveletTransform(frost_depths_copy);
        System.out.print("Ordered Transformed "); OneDHaar.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHaar.displayOrderedFreqsFromOrderedHaar(frost_depths_copy);
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
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); OneDHaar.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(frost_depths);
        System.out.println();
        
        OneDHaar.orderedFastHaarWaveletTransform(frost_depths_copy);
        System.out.print("Ordered Transformed "); OneDHaar.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHaar.displayOrderedFreqsFromOrderedHaar(frost_depths_copy);
    }
     
     public static void ex_1_49_p33() {
        double[] frost_depths = {10.0, 12.0, 12.0, 7.0,  8.0, 9.1, 8.2,  9.4,
                                16.0, 15.0, 13.0, 11.0, 6.4, 9.0, 19.0, 118.0};
        double[] frost_depths_copy = new double[16];
        System.arraycopy(frost_depths, 0, frost_depths_copy, 0, 16);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(frost_depths, 4);
        System.out.print("In-Place Transformed "); OneDHaar.displaySample(frost_depths);
        System.out.println("Ordered Freqs from In-Place Haar");
        OneDHaar.displayOrderedFreqsFromInPlaceHaar(frost_depths);
        System.out.println();
        
        OneDHaar.orderedFastHaarWaveletTransform(frost_depths_copy);
        System.out.print("Ordered Transformed "); OneDHaar.displaySample(frost_depths_copy);
        System.out.println("Ordered Freqs from Ordered Haar");
        OneDHaar.displayOrderedFreqsFromOrderedHaar(frost_depths_copy);
    }
     
     static void testOneDInPlaceFHWT(double[] sample, int n) {
         System.out.println("Original sample:");
         OneDHaar.displaySample(sample);
         System.out.println();
         
         OneDHaar.inPlaceFastHaarWaveletTransform(sample);
         System.out.println("Transformed sample:");
         OneDHaar.displaySample(sample);
         System.out.println();
         
         OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(sample, n);
         System.out.println("Restored sample:");
         OneDHaar.displaySample(sample);
         System.out.println();
     }
     
     static void testOrderedFHWTForNumIters(double[] sample) {
        int n = (int)(Math.log(sample.length)/Math.log(2.0));
        double[] copy_sample = new double[sample.length];
        System.arraycopy(sample, 0, copy_sample, 0, sample.length);
         for(int i = 1; i <= n; i++) {
             System.out.println("num iters " + i);
             System.out.print("Original: "); OneDHaar.displaySample(sample);
             OneDHaar.orderedFastHaarWaveletTransformForNumIters(sample, i);
             System.out.print("Transformed: "); OneDHaar.displaySample(sample);
             OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(sample, i);
             System.out.println("Reversed: "); OneDHaar.displaySample(sample);
             for(int j = 0; j < sample.length; j++) {
                 if ( sample[j] != copy_sample[j] ) {
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
        OneDHaar.displaySample(sample);
        OneDHaar.orderedNormalizedFastHaarWaveletTransform(sample);
        OneDHaar.displaySample(sample);
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransform(sample);
        OneDHaar.displaySample(sample);
     }
     
      static void testNormalizedOrderedFHWTForNumIters(double[] sample, double thresh) {
        int n = (int)(Math.log(sample.length)/Math.log(2.0));
        double[] copy_sample = new double[sample.length];
        System.arraycopy(sample, 0, copy_sample, 0, sample.length);
         for(int i = 1; i <= n; i++) {
             System.out.println("num iters " + i);
             System.out.print("Original: "); OneDHaar.displaySample(sample);
             OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(sample, i);
             System.out.print("Transformed: "); OneDHaar.displaySample(sample);
             OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(sample, i);
             System.out.println("Reversed: "); OneDHaar.displaySample(sample);
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
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(sample, num_iters);
        System.out.print(num_iters + ") " + "Forward: "); OneDHaar.displaySample(sample);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(sample, num_iters);
        System.out.print(num_iters + ") " + "Inverse: ");  OneDHaar.displaySample(sample);
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
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(range, 3);
        
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
        
        OneDHaar.orderedNormalizedFastHaarWaveletTransformForNumIters(range, 3);
        
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
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d07_d8, 3);
        
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
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d06_d7_d08, 3);
        
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
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s06_d6_d07_d08, 3);
        
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
        
        OneDHaar.orderedNormalizedFastInverseHaarWaveletTransformForNumIters(s6_d06_d07_d08, 3);
        
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
         ripplesTest03();
    }
}
