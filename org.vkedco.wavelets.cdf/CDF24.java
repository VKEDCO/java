package org.vkedco.wavelets.cdf;

import org.vkedco.wavelets.utils.Utils;

/**
 ****************************************
 * @author vladimir kulyukin
 ****************************************
 */
public class CDF24 {
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
    
    // CDF(4, 4) Inverse wavelet coefficients
    static final double IG0 = H3;
    static final double IG1 = G3;  // -h0
    static final double IG2 = H1;
    static final double IG3 = G1;  // -h2
    
    public static void orderedDWT(double[] signal, boolean dbg_flag) {
        final int N = signal.length;
        if ( N < 4 || !Utils.isPowerOf2(N) ) {
            System.out.println("No DWT will be done: signal's length is < 4 or not a power of 2");
            return;
        }
        int i, j, mid;
        double[] D4 = null;

        int numScalesToDo = Utils.wholeLog2(N)-1; 
        int currScale  = 0;
        int signal_length = N;
        while ( signal_length >= 4 )  {
            mid = signal_length >> 1; // n / 2;
            if ( dbg_flag ) System.out.println("MID           = " + mid);
            if ( dbg_flag ) System.out.println("signal_length = " + signal_length);
            D4 = new double[signal_length]; // temporary array that saves the scalers and wavelets
            for(i = 0, j = 0; j < signal_length-3; i += 1, j += 2) {
                if ( dbg_flag ) {
                    final String cursig = "s^{" + (currScale+1) + "}_{" + (numScalesToDo-1) + "}";
                    final String prvsig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                    System.out.println("FWD SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + j + "]+H1*" + prvsig + "[" + (j+1) + "]+" +
                        "H2*" + prvsig + "[" + (j+2) + "]+" + "H3*" + prvsig + "[" + (j+3) + "]; " );
                    System.out.println("FWD WVL:  " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + j + "]+" + "G1*" + prvsig + "[" + (j+1) + "]+" +
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
            D4[i]     = H0*signal[signal_length-2] + H1*signal[signal_length-1] + H2*signal[0] + H3*signal[1];
            // cdf44[mid+i] is the corresponding wavelet for d4[i]
            D4[mid+i] = G0*signal[signal_length-2] + G1*signal[signal_length-1] + G2*signal[0] + G3*signal[1];
            
            if ( dbg_flag ) {
                final String cursig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                final String prvsig = "s^{" + (currScale-1) + "}_{" + (numScalesToDo+1) + "}";
                System.out.println("FWD SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + (signal_length-2) + "]+H1*" + prvsig + "[" + (signal_length-1) + "]+" +
                       "H2*" + prvsig + "[" + 0 + "]+" + "H3*" + prvsig + "[" + 1 + "]; " );
                System.out.println("FWD WVL:  " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + (signal_length-2) + "]+" + "G1*" + prvsig + "[" + (signal_length-1) + "]+" +
                       "G2*" + prvsig + "[" + 0 + "]+" + "G3*" + prvsig + "[" + 1 + "]" );
            }
            
            System.arraycopy(D4, 0, signal, 0, D4.length);
            D4 = null;
            signal_length >>= 1; // signal_length gets halved at each iteration/scale
        }
    }
    
    public static void orderedDWTForNumIters(double[] signal, int num_iters, boolean dbg_flag) {
        final int N = signal.length;
        if ( N < 4 || !Utils.isPowerOf2(N) ) {
            System.out.println("No DWT will be done: signal's length is < 4 or not a power of 2");
            return;
        }
        int i, j, mid;
        double[] D4 = null;

        if ( dbg_flag ) { 
            System.out.print("=>INPUT: "); Utils.displaySample(signal);
        }
        
        int numScalesToDo = Utils.wholeLog2(N)-1; 
        int currScale  = 0;
        int signal_length = N;
        while ( signal_length >= 4 )  {
            
            mid = signal_length >> 1; // n / 2;
            if ( dbg_flag ) System.out.println("MID           = " + mid);
            if ( dbg_flag ) System.out.println("signal_length = " + signal_length);
            D4 = new double[signal_length]; // temporary array that saves the scalers and wavelets
            for(i = 0, j = 0; j < signal_length-3; i += 1, j += 2) {
                if ( dbg_flag ) {
                    final String cursig = "s^{" + (currScale+1) + "}_{" + (numScalesToDo-1) + "}";
                    final String prvsig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                    System.out.println("FWD SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + j + "]+H1*" + prvsig + "[" + (j+1) + "]+" +
                        "H2*" + prvsig + "[" + (j+2) + "]+" + "H3*" + prvsig + "[" + (j+3) + "]; " );
                    System.out.println("FWD WVL:  " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + j + "]+" + "G1*" + prvsig + "[" + (j+1) + "]+" +
                        "G2*" + prvsig + "[" + (j+2) + "]+" + "G3*" + prvsig + "[" + (j+3) + "]" );
                }
                // cdf44[i] is a scaled sample
                D4[i]     = H0*signal[j] + H1*signal[j+1] + H2*signal[j+2] + H3*signal[j+3];
                // cdf44[mid+i] is the corresponding wavelet for d4[i]
                D4[mid+i] = G0*signal[j] + G1*signal[j+1] + G2*signal[j+2] + G3*signal[j+3];
            }

            // cdf44[i] is a scaled sample with a mirror wrap-up
            D4[i]     = H0*signal[signal_length-2] + H1*signal[signal_length-1] + H2*signal[0] + H3*signal[1];
            // cdf44[mid+i] is the corresponding wavelet for d4[i]
            D4[mid+i] = G0*signal[signal_length-2] + G1*signal[signal_length-1] + G2*signal[0] + G3*signal[1];
            
            if ( dbg_flag ) {
                final String cursig = "s^{" + currScale + "}_{" + numScalesToDo + "}";
                final String prvsig = "s^{" + (currScale-1) + "}_{" + (numScalesToDo+1) + "}";
                System.out.println("FWD SCL:  " + cursig + "[" + i + "]=" + "H0*" + prvsig + "[" + (signal_length-2) + "]+H1*" + prvsig + "[" + (signal_length-1) + "]+" +
                       "H2*" + prvsig + "[" + 0 + "]+" + "H3*" + prvsig + "[" + 1 + "]; " );
                System.out.println("FWD WVL:  " + cursig + "[" + (mid+i) + "]=" + "G0*" + prvsig + "[" + (signal_length-2) + "]+" + "G1*" + prvsig + "[" + (signal_length-1) + "]+" +
                       "G2*" + prvsig + "[" + 0 + "]+" + "G3*" + prvsig + "[" + 1 + "]" );
            }
            
            System.arraycopy(D4, 0, signal, 0, D4.length);
            D4 = null;
            signal_length >>= 1; // signal_length gets halved at each iteration/scale
            
            currScale     += 1;
            numScalesToDo -= 1;
            if ( currScale >= num_iters ) return;
        }
    }
    
    
    // ordered inverse DWT; set dbg_flag to false if debugging messages are not
    // needed
    public static void orderedInverseDWT(double[] signal_transform, boolean dbg_flag) {
        final int N = signal_transform.length;
        if ( N < 4 || !Utils.isPowerOf2(N) ) {
            System.out.println("No DWT will be done: signal's length is < 4 or not a power of 2");
            return;
        } 
          
        int numInvScalesToDo = Utils.wholeLog2(N)-1; 
        int currInvScale     = 0;
        int transform_length = 4;
        
        if ( dbg_flag ) { 
            System.out.print("<=INPUT: "); Utils.displaySample(signal_transform);
        }
        
        while ( transform_length <= N ) {
            int mid = transform_length >> 1;
            if ( dbg_flag ) System.out.println("MID              = " + mid);
            if ( dbg_flag ) System.out.println("transform_length = " + transform_length);
            
            double[] inv_sig = new double[transform_length]; // restored values
            
            String cur_sig = null;
            String prv_sig = null;
            
            if ( dbg_flag ) {
                cur_sig = "s^{" + (numInvScalesToDo-1) + "}_{"  + (currInvScale+1) + "}";
                prv_sig = "s^{" + numInvScalesToDo     + "}_{"  + currInvScale     + "}";
            }
            
            inv_sig[0] = IH0*signal_transform[mid-1] + IH1*signal_transform[transform_length-1] + IH2*signal_transform[0] + IH3*signal_transform[mid];
            inv_sig[1] = IG0*signal_transform[mid-1] + IG1*signal_transform[transform_length-1] + IG2*signal_transform[0] + IG3*signal_transform[mid];
            
            if ( dbg_flag ) {
                System.out.println("INV SCL: " + cur_sig + "[" + 0 + "] = " + "IH0*" +  prv_sig + "[" + (mid-1) + "] + " +
                        "IH1*" +  prv_sig + "[" + (transform_length-1) + "] + " + "IH2*" + prv_sig + "[0] + " + "IH3*" + prv_sig + "[" + mid + "]");
                System.out.println("INV WVL: " + cur_sig + "[" + 1 + "] = " + "IG0*" + prv_sig + "[" + (mid-1) + "] + " +
                        "IG1*" + prv_sig +  "[" + (transform_length-1) + "] + " + "IG2*" + prv_sig + "[0] + " + "IG3*" + prv_sig + "[" + mid + "]");
            }
            
            
            int i = 0, j = 2;
            
            while ( i < mid-1 ) {
                if ( dbg_flag ) {
                    cur_sig = "s^{" + (numInvScalesToDo-1) + "}_{" + (currInvScale+1) + "}";
                    prv_sig = "s^{" + numInvScalesToDo     + "}_{" + currInvScale     + "}";
                }
                
                if ( dbg_flag ) {
                    System.out.println("INV SCL: " + cur_sig + "[" + j + "] = " + "IH0*" + prv_sig + "[" + i + "] + " +
                        "IH1*" + prv_sig + "[" + (mid+i) + "] + " + "IH2*" + prv_sig + "[" + (i+1) + "] + " + 
                        "IH3*" + prv_sig + "[" + (mid+i+1) + "]");
                }
                
                //           scalers                     wavelets                       
                inv_sig[j] = IH0*signal_transform[i]   + IH1*signal_transform[mid+i] + 
                         IH2*signal_transform[i+1] + IH3*signal_transform[mid+i+1];
                
                if ( dbg_flag ) {
                    System.out.println("INV WVL: " + cur_sig + "[" + (j+1) + "] = " + "IG0*" + prv_sig + "[" + i + "] + " +
                        "IG1*" + prv_sig + "[" + (mid+i) + "] + " + "IG2*" + prv_sig + "[" + (i+1) + "] + " + 
                        "IG3*" + prv_sig + "[" + (mid+i+1) + "]");
                }
                
                //             scalers                     wavelets
                inv_sig[j+1] = IG0*signal_transform[i]   + IG1*signal_transform[mid+i] + 
                               IG2*signal_transform[i+1] + IG3*signal_transform[mid+i+1];
                
                i += 1; j += 2;
            }
            
            currInvScale     += 1;
            numInvScalesToDo -= 1;
            
            System.arraycopy(inv_sig, 0, signal_transform, 0, inv_sig.length);
            transform_length <<= 1; // multiply by 2
        }
    }
    
    public static void orderedInverseDWTForNumIters(double[] signal_transform, int num_iters, boolean dbg_flag) {
        final int N = signal_transform.length;
        if ( N < 4 || !Utils.isPowerOf2(N) ) {
            System.out.println("No DWT will be done: signal's length is < 4 or not a power of 2");
            return;
        }  
        
        if ( dbg_flag ) { 
            System.out.print("<=INPUT: "); Utils.displaySample(signal_transform);
        }
        
        int numInvScalesToDo = Utils.wholeLog2(N)-1; 
        int currInvScale     = 0;
        int transform_length = N / (1 << (num_iters-1));
        
        while ( transform_length <= N ) {
            if ( currInvScale >= num_iters ) return;
            
            int mid = transform_length >> 1;
            if ( dbg_flag ) System.out.println("MID              = " + mid);
            if ( dbg_flag ) System.out.println("transform_length = " + transform_length);
            if ( dbg_flag ) System.out.println("currInvScale     = " + currInvScale);
            if ( dbg_flag ) System.out.println("num_iters        = " + num_iters);
            
            double[] inv_sig = new double[transform_length]; // restored values
            
            String cur_sig = null;
            String prv_sig = null;
            
            if ( dbg_flag ) {
                cur_sig = "s^{" + (numInvScalesToDo-1) + "}_{"  + (currInvScale+1) + "}";
                prv_sig = "s^{" + numInvScalesToDo     + "}_{"  + currInvScale     + "}";
            }
            
            inv_sig[0] = IH0*signal_transform[mid-1] + IH1*signal_transform[transform_length-1] + IH2*signal_transform[0] + IH3*signal_transform[mid];
            inv_sig[1] = IG0*signal_transform[mid-1] + IG1*signal_transform[transform_length-1] + IG2*signal_transform[0] + IG3*signal_transform[mid];
            
            if ( dbg_flag ) {
                System.out.println("INV SCL: " + cur_sig + "[" + 0 + "] = " + "IH0*" +  prv_sig + "[" + (mid-1) + "] + " +
                        "IH1*" +  prv_sig + "[" + (transform_length-1) + "] + " + "IH2*" + prv_sig + "[0] + " + "IH3*" + prv_sig + "[" + mid + "]");
                System.out.println("INV WVL: " + cur_sig + "[" + 1 + "] = " + "IG0*" + prv_sig + "[" + (mid-1) + "] + " +
                        "IG1*" + prv_sig +  "[" + (transform_length-1) + "] + " + "IG2*" + prv_sig + "[0] + " + "IG3*" + prv_sig + "[" + mid + "]");
            }
            
            
            int i = 0, j = 2;
            
            while ( i < mid-1 ) {
                if ( dbg_flag ) {
                    cur_sig = "s^{" + (numInvScalesToDo-1) + "}_{" + (currInvScale+1) + "}";
                    prv_sig = "s^{" + numInvScalesToDo     + "}_{" + currInvScale     + "}";
                }
                
                if ( dbg_flag ) {
                    System.out.println("INV SCL: " + cur_sig + "[" + j + "] = " + "IH0*" + prv_sig + "[" + i + "] + " +
                        "IH1*" + prv_sig + "[" + (mid+i) + "] + " + "IH2*" + prv_sig + "[" + (i+1) + "] + " + 
                        "IH3*" + prv_sig + "[" + (mid+i+1) + "]");
                }
                
                //           scalers                     wavelets                       
                inv_sig[j] = IH0*signal_transform[i]   + IH1*signal_transform[mid+i] + 
                             IH2*signal_transform[i+1] + IH3*signal_transform[mid+i+1];
                
                if ( dbg_flag ) {
                    System.out.println("INV WVL: " + cur_sig + "[" + (j+1) + "] = " + "IG0*" + prv_sig + "[" + i + "] + " +
                        "IG1*" + prv_sig + "[" + (mid+i) + "] + " + "IG2*" + prv_sig + "[" + (i+1) + "] + " + 
                        "IG3*" + prv_sig + "[" + (mid+i+1) + "]");
                }
                
                //             scalers                     wavelets
                inv_sig[j+1] = IG0*signal_transform[i]   + IG1*signal_transform[mid+i] + 
                               IG2*signal_transform[i+1] + IG3*signal_transform[mid+i+1];
                
                i += 1; j += 2;
            }
            
            currInvScale     += 1;
            numInvScalesToDo -= 1;
            
            System.arraycopy(inv_sig, 0, signal_transform, 0, inv_sig.length);
            transform_length <<= 1; // multiply by 2
        }
    }
    
    public static void test_fwd_cdf44(double[] s, boolean dbg_flag) {
        double[] scopy = new double[s.length];
        System.arraycopy(s, 0, scopy, 0, s.length);
        System.out.print("Input: "); Utils.displaySample(scopy);
        CDF24.orderedDWT(s, dbg_flag);
        System.out.print("FWD CDF(4,4): "); Utils.displaySample(s);
        System.out.println();
    }
    
    public static void test_fwd_inv_cdf44(double[] s, boolean dbg_flag) {
        System.out.print("Input: "); Utils.displaySample(s);
        
        CDF24.orderedDWT(s, dbg_flag);
        
        System.out.print("FWD CDF(4,4): "); Utils.displaySample(s);
        System.out.println();
        
        CDF24.orderedInverseDWT(s, dbg_flag);
        
        System.out.print("INV CDF(4,4): "); Utils.displaySample(s);
        System.out.println();
    }
    
    public static void test_fwd_inv_cdf44_for_num_iters(double[] s, int num_iters, boolean dbg_flag) {
        System.out.print("Input: "); Utils.displaySample(s);
        double[] scopy = new double[s.length];
        System.arraycopy(s, 0, scopy, 0, scopy.length);
        
        CDF24.orderedDWTForNumIters(s, num_iters, dbg_flag);
       
        System.out.print("FWD CDF(4,4) for num iters " + num_iters + ": "); Utils.displaySample(s);
        System.out.println();
        
        CDF24.orderedInverseDWTForNumIters(s, num_iters, dbg_flag);
        
        System.out.print("INV CDF(4,4) for num iters " + num_iters + ": "); Utils.displaySample(s);

        if ( Utils.areSignalsEqual(s, scopy, 0.0001) ) {
            System.out.println("CONVERSION TRUE");
        }
        else {
            System.out.println("CONVERSION FALSE");
        }
        
        System.out.println();
    }
     
    static double[] a01a = {1, 2, 3, 4};
    static double[] a01b = {4, 3, 2, 1};
    static double[] a02a = {1, 2, 3, 4, 5, 6, 7, 8};
    static double[] a02b = {8, 7, 6, 5, 4, 3, 2, 1};
    
    static double[] a03a = {1, 1, 1, 1};
    static double[] a03b = {2, 2, 2, 2};
    static double[] a03c = {3, 3, 3, 3};
    static double[] a03d = {4, 4, 4, 4};
    
    static double[] a04a = {1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16};
    static double[] a04b = {16, 15, 14, 13, 12, 11, 10, 9,  8, 7,  6,  5,  4,  3,  2,  1};
    
    public static void main(String[] args) { 
        //test_fwd_inv_cdf44_for_num_iters(a02a, 1, false);
        //test_fwd_inv_cdf44_for_num_iters(a02a, 2, false);
        //test_fwd_cdf44(a01a, true);
        test_fwd_inv_cdf44_for_num_iters(a02a, 2, true);
    }

}
