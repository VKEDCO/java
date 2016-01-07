package org.vkedco.wavelets.ripples;

import org.vkedco.wavelets.utils.Utils;

/**
 * @author vladimir kulyukin
 */
public class CDF44 {
    static final double SQRT_OF_3 = Math.sqrt(3);
    static final double SQRT_OF_2 = Math.sqrt(2);
    static final double FOUR_SQRT_OF_2 = 4*SQRT_OF_2;
    
    // CDF(4,4) Forward signal coefficients
    static final double H0 = (1 + SQRT_OF_3)/FOUR_SQRT_OF_2;
    static final double H1 = (3 + SQRT_OF_3)/FOUR_SQRT_OF_2;
    static final double H2 = (3 - SQRT_OF_3)/FOUR_SQRT_OF_2;
    static final double H3 = (1 - SQRT_OF_3)/FOUR_SQRT_OF_2;
    
    // CDF(4, 4) Forward wavelet coefficients
    static final double G0 = H3;
    static final double G1 = -H2;
    static final double G2 = H1;
    static final double G3 = -H0;
    
    // CDF(4, 4) Inverse signal coefficients
    static final double IH0 = H2;
    static final double IH1 = G2;  // h1
    static final double IH2 = H0;
    static final double IH3 = G0;  // h3
    
    // CDF(4, 4) Inverse signal coefficients
    static final double IG0 = H3;
    static final double IG1 = G3;  // -h0
    static final double IG2 = H1;
    static final double IG3 = G1;  // -h2
    
    public static void orderedDWT(double[] signal, boolean dbg_flag) {
        final int N = signal.length;
        if ( !Utils.isPowerOf2(N) ) return;
        if ( N < 4 ) return;
        int i, j, mid;
        double[] D4 = null;

        int numScalesToDo = Utils.powVal(N)-1; 
        int currScale  = 0;
        for(int n = N; n >= 4; n >>= 1) {
            mid = n >> 1; // n / 2;
            if ( dbg_flag ) System.out.println("MID = " + mid);
            D4 = new double[n]; // temporary array that saves the scalers and wavelets
            for(i = 0, j = 0; j < n-3; i += 1, j += 2) {
                if ( dbg_flag ) {
                    final String cursig = "s^{" + (currScale+1) + "}_{" + (numScalesToDo-1) + "}";
                    final String prvsig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                    System.out.print("SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + j + "]+H1*" + prvsig + "[" + (j+1) + "]+" +
                        "H2*" + prvsig + "[" + (j+2) + "]+" + "H3*" + prvsig + "[" + (j+3) + "]; " );
                    System.out.println("WVL: " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + j + "]+" + "G1*" + prvsig + "[" + (j+1) + "]+" +
                        "G2*" + prvsig + "[" + (j+2) + "]+" + "G3*" + prvsig + "[" + (j+3) + "]" );
                }
                // cdf44[i] is a scaled sample
                D4[i]     = H0*signal[j] + H1*signal[j+1] + H2*signal[j+2] + H3*signal[j+3];
                // cdf44[mid+i] is the corresponding wavelet for d4[i]
                D4[mid+i] = G0*signal[j] + G1*signal[j+1] + G2*signal[j+2] + G3*signal[j+3];
            }

            currScale     += 1;
            numScalesToDo -= 1;
            
            // cdf44[i] is a scaled sample with a mirror wrap-up
            D4[i]     = H0*signal[n-2] + H1*signal[n-1] + H2*signal[0] + H3*signal[1];
            // cdf44[mid+i] is the corresponding wavelet for d4[i]
            D4[mid+i] = G0*signal[n-2] + G1*signal[n-1] + G2*signal[0] + G3*signal[1];
            
            if ( dbg_flag ) {
                final String cursig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                final String prvsig = "s^{" + (currScale-1) + "}_{" + (numScalesToDo+1) + "}";
                System.out.print("SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + (n-2) + "]+H1*" + prvsig + "[" + (n-1) + "]+" +
                       "H2*" + prvsig + "[" + 0 + "]+" + "H3*" + prvsig + "[" + 1 + "]; " );
                System.out.println("WVL: " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + (n-2) + "]+" + "G1*" + prvsig + "[" + (n-1) + "]+" +
                       "G2*" + prvsig + "[" + 0 + "]+" + "G3*" + prvsig + "[" + 1 + "]" );
            }
            
            System.arraycopy(D4, 0, signal, 0, D4.length);
            D4 = null;
        }
    }
    
    static void orderedInverseDWT(double a[], boolean dbg_flag) {
        final int N = a.length;
        int n;
        for (n = 4; n <= N; n <<= 1) {
            int i, j;
            int mid = n >> 1;
            int midPlus1 = mid + 1;
            if ( dbg_flag ) System.out.println("invTransform for: " + n);
            double rvs[] = new double[n]; // restored values
            // last smooth val  last coef.  first smooth  first coef
            rvs[0] = IH0*a[mid-1] + IH1*a[n-1] + IH2*a[0] + IH3*a[mid];
            rvs[1] = IG0*a[mid-1] + IG1*a[n-1] + IG2*a[0] + IG3*a[mid];
            if ( dbg_flag ) {
                System.out.println("rvs[" + 0 + "] = " + "IH0*a[" + (mid-1) + "] " +
                        "IH1*a[" + (n-1) + "] + " + "IH2*a[0] + " + "IH3*a[" + mid + "]");
                System.out.println("rvs[" + 1 + "] = " + "IG0*a[" + (mid-1) + "] + " +
                        "IG1*a[" + (n-1) + "] + " + "IG2*a[0] + " + "IG3*a[" + mid + "]");
            }
            j = 2;
            for (i = 0; i < mid-1; i++) {
                //     smooth val     coef. val       smooth val    coef. val
                if ( dbg_flag ) {
                    System.out.println("rvs[" + j + "] = " + "IH0*a[" + i + "] + " +
                        "IH1*a[" + (i+mid) + "] + " + "IH2*a[" + (i+1) + "] + " + "IH3*a[" + (i+midPlus1) + "]");
                }
                
                rvs[j++] = IH0*a[i] + IH1*a[i+mid] + IH2*a[i+1] + IH3*a[i+midPlus1];
                
                if ( dbg_flag ) {
                    System.out.println("rvs[" + j + "] = " + "IG0*a[" + i + "] + " +
                        "IG1*a[" + (i+mid) + "] + " + "IG2*a[" + (i+1) + "] + " + "IG3*a[" + (i+midPlus1) + "]");
                }
                
                rvs[j++] = IG0*a[i] + IG1*a[i+mid] + IG2*a[i+1] + IG3*a[i+midPlus1];
            }
            System.arraycopy(rvs, 0, a, 0, rvs.length);
            //for (i = 0; i < n; i++) {
            //    a[i] = rvs[i];
            //}
            
            if ( dbg_flag ) System.out.println("invTransform over for: " + n);
        }
    }
    
    
    
    static void display(double[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    static void display(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static void test_fwd_cdf44(double[] s, boolean dbg_flag) {
        //daub kapdaub = new daub();
        double[] scopy = new double[s.length];
        System.arraycopy(s, 0, scopy, 0, s.length);
        System.out.print("Input: "); display(scopy);
        CDF44.orderedDWT(s, dbg_flag);
        System.out.print("FWD CDF(4,4): "); display(s);
        System.out.println();
    }
    

    public static void test_fwd_inv_cdf44(double[] s, boolean dbg_flag) {
        //daub kapdaub = new daub();
        double[] scopy = new double[s.length];
        System.arraycopy(s, 0, scopy, 0, s.length);
        System.out.print("Input: "); display(scopy);
        CDF44.orderedDWT(s, dbg_flag);
        System.out.print("FWD CDF(4,4): "); display(s);
        System.out.println();
        
        CDF44.orderedInverseDWT(s, dbg_flag);
        System.out.print("INV CDF(4,4): "); display(s);
        System.out.println();
    }
    
    public static boolean are_D4_outputs_equal(double[] output1, double[] output2) {
        if ( output1.length != output2.length ) return false;
        for(int i = 0; i < output1.length; i++) {
            if ( output1[i] != output2[i] )
                return false;
        }
        return true;
    }
     
    static double[] a01a = {1, 2, 3, 4};
    static double[] a01b = {4, 3, 2, 1};
    static double[] a02a = {1, 2, 3, 4, 5, 6, 7, 8};
    static double[] a02b = {8, 7, 6, 5, 4, 3, 2, 1};
    
    static double[] a03a = {1, 1, 1, 1};
    static double[] a03b = {2, 2, 2, 2};
    static double[] a03c = {3, 3, 3, 3};
    static double[] a03d = {4, 4, 4, 4};
    
    static double[] a04a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    static double[] a04b = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    
    public static void main(String[] args) { 
        test_fwd_cdf44(a01b, true);
    }

}
