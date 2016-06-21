package org.vkedco.wavelets.haar;

import org.vkedco.wavelets.utils.Utils;

/**
 ************************************
 * @author Vladimir Kulyukin
 * 
 * TODO: Write the inverse ordered DWT for a specific number of
 * iterations that is smaller that the number of available iterations
 * in the 2D matrix. for example inverse DWT for 2 iterations on a 8x8
 * matrix.
 ************************************ 
 */
public class TwoDHWT {
    
    public static double computeHorChangeMagn(double[][] sig, int ttn) {
        double left_mean  = Utils.computeMean(sig, 0, ttn-1, 0, ttn/2 - 1);
        double right_mean = Utils.computeMean(sig, 0, ttn-1, ttn/2, ttn-1);
        //System.out.println("left_mean = " + left_mean);
        //System.out.println("right_mean = " + right_mean);
        return (left_mean - right_mean)/2;
    }
    
    public static double computeVerChangeMagn(double[][] sig, int ttn) {
        double upper_mean = Utils.computeMean(sig, 0, ttn/2 - 1, 0, ttn-1);
        double lower_mean = Utils.computeMean(sig, ttn/2, ttn-1, 0, ttn-1);
        return (upper_mean - lower_mean)/2.0;
    }
    
    public static double computeDiagChangeMagn(double[][] sig, int ttn) {
        double top_left_quad_mean = Utils.computeMean(sig, 0, ttn/2 - 1, 0, ttn/2 - 1);
        double bot_right_quad_mean = Utils.computeMean(sig, ttn/2, ttn-1, ttn/2, ttn-1);
        double top_right_quad_mean = Utils.computeMean(sig, 0, ttn/2 - 1, ttn/2, ttn-1);
        double bot_left_quad_mean = Utils.computeMean(sig, ttn/2, ttn-1, 0, ttn/2 - 1);
        return ((top_left_quad_mean+bot_right_quad_mean)/2 -
                (top_right_quad_mean+bot_left_quad_mean)/2)/2;
    } 
    
    public static void ordFwdDWTForNumIters(double[][] sig, int num_iters, boolean dbg_flag) {
        if ( sig.length != sig[0].length ) {
            throw new IllegalArgumentException("sig is not n x n");
        }
        if ( !Utils.isPowerOf2(sig.length) ) {
            throw new IllegalArgumentException("sig's dimension is not a power of 2");
        } 
        ordFwdDWTForNumItersAux(sig, sig[0].length, num_iters, dbg_flag); 
    }
    
    public static void ordFwdDWTForNumItersAux(double[][] sig, int ttn, int num_iters,
            boolean dbg_flag) {
        if ( dbg_flag == true ) {
            System.out.println("ORD FWD DWT for ttn = " + ttn + " num_iters = " + num_iters);
        }
        
        if ( num_iters == 0 ) return;
        
        if ( ttn >  1 ) {
            applyDWTToRowsOnce(sig, ttn);
            if ( dbg_flag == true ) {
                System.out.println("Row-Based HWT");
                Utils.display2DArray(sig, ttn, ttn);
            }
            applyDWTToColsOnce(sig, ttn);
            if ( dbg_flag == true ) {
                System.out.println();
                System.out.println("Col-Based HWT");
                Utils.display2DArray(sig, ttn, ttn);
            }
            fwdRearrangeAHVD(sig, ttn);
            if ( dbg_flag == true ) {
                System.out.println("Rearranged Matrix");
                Utils.display2DArray(sig, ttn, ttn);
                System.out.println();
            }
            
            ordFwdDWTForNumItersAux(sig, ttn >> 1, num_iters - 1, dbg_flag);
        }
    }
    
    public static void applyDWTToRowsOnce(double[][] sig, int n) {
       for(int r = 0; r < n; r++) {
           for(int c = 0; c < n; c += 2) {
               double plus  =  (sig[r][c]+sig[r][c+1])/2.0;
               double minus = (sig[r][c]-sig[r][c+1])/2.0;
               sig[r][c] = plus; sig[r][c+1] = minus;
           }
       }
    }
    
    public static void applyDWTToColsOnce(double[][] sig, int n) {
        for(int c = 0; c < n; c++) {
            for(int r = 0; r < n; r += 2) {
                double plus  = (sig[r][c] + sig[r+1][c])/2.0;
                double minus = (sig[r][c] - sig[r+1][c])/2.0;
                sig[r][c] = plus; sig[r+1][c] = minus;
            }
        }
    }
    
    public static void fwdRearrangeAHVD(double[][] sig, int ttn) {
        double[][] M = new double[ttn][ttn];
        final int ttn_1 = ttn >> 1;
        int ar = 0, ac = 0;
        int hr = 0, hc = ttn_1;
        int vr = ttn_1, vc = 0;
        int dr = ttn_1, dc = ttn_1;
        
        //System.out.println("hr = " + hr + " hc = " + hc);
        for(int sig_ah_r = 0, sig_vd_r = 1; sig_ah_r < ttn; sig_ah_r += 2, sig_vd_r += 2) {
            //System.out.println("hr = " + hr + " hc = " + hc);
            ac = 0; hc = ttn_1; vc = 0; dc = ttn_1;
            for(int c = 0; c < ttn; c += 2) {
                M[ar][ac] = sig[sig_ah_r][c];
                M[hr][hc] = sig[sig_ah_r][c+1];
                M[vr][vc] = sig[sig_vd_r][c];
                M[dr][dc] = sig[sig_vd_r][c+1];
                ac++; hc++; vc++; dc++;
            }
            ar++; hr++; vr++; dr++;
        }
        for(int r = 0; r < ttn; r++) {
            System.arraycopy(M[r], 0, sig[r], 0, ttn);
        }
        M = null;
    }
    
    /*
    public static void ordInvDWTForNumItersAttic(double[][] sig, int num_iters) {
        if ( sig.length != sig[0].length ) {
            throw new IllegalArgumentException("sig is not n x n");
        }
        if ( !Utils.isPowerOf2(sig.length) ) {
            throw new IllegalArgumentException("sig's dimension is not a power of 2");
        } 
        ordInvDWTForNumItersAuxAttic(sig, sig[0].length, num_iters); 
    }
    
    public static void ordInvDWTForNumItersAuxAttic(double[][] sig, int ttn, int num_iters) {
        
    }
     * 
     */
    
    public static void invTwoByTwoDWT(double[][] sig) {
        if ( sig.length != 2 ) {
            throw new IllegalArgumentException("sig's number of rows is not 2");
        }
        if ( sig[0].length != 2 ) {
            throw new IllegalArgumentException("sig's number of columns is not 2");
        }
        final double a0 = sig[0][0]; final double h0 = sig[0][1];
        final double v0 = sig[1][0]; final double d0 = sig[1][1];
        final double a1_0 = a0 + h0 + v0 + d0;
        final double a1_1 = a0 - h0 + v0 - d0;
        final double a1_2 = a0 + h0 - v0 - d0;
        final double a1_3 = a0 - h0 - v0 + d0;
        sig[0][0] = a1_0; sig[0][1] = a1_1;
        sig[1][0] = a1_2; sig[1][1] = a1_3;
    }
    
    // top_left_r and top_left_c are the coords in sig of a 2x2 block where
    // inv2x2DWT will be applied
    public static void invTwoByTwoDWT(double[][] sig, int top_left_r, int top_left_c) {
        final double a0 = sig[top_left_r][top_left_c]; 
        final double h0 = sig[top_left_r][top_left_c+1];
        final double v0 = sig[top_left_r+1][top_left_c]; 
        final double d0 = sig[top_left_r+1][top_left_c+1];
        final double a1_0 = a0 + h0 + v0 + d0;
        final double a1_1 = a0 - h0 + v0 - d0;
        final double a1_2 = a0 + h0 - v0 - d0;
        final double a1_3 = a0 - h0 - v0 + d0;
        sig[top_left_r][top_left_c]     = a1_0; 
        sig[top_left_r][top_left_c+1]   = a1_1;
        sig[top_left_r+1][top_left_c]   = a1_2; 
        sig[top_left_r+1][top_left_c+1] = a1_3;
    }
    
    public static void ordInvDWTForNumIters(double[][] sig, int num_inv_iters, 
            int num_fwd_iters, boolean dbg_flag) {
        
        if ( sig.length != sig[0].length ) {
            throw new IllegalArgumentException("sig is not n x n");
        }
       
        if ( !Utils.isPowerOf2(sig.length) ) {
            throw new IllegalArgumentException("sig's dimension is not a power of 2");
        } 
        final int num_avail_iters = Utils.wholeLog2(sig.length);
        final int num_applied_iters = num_avail_iters - num_fwd_iters;
        final int avrg_ttn = (int)(Math.pow(2, num_applied_iters));
        ordInvDWTForNumItersAux(sig, avrg_ttn, num_inv_iters, num_fwd_iters, dbg_flag); 
    }
    
    public static void ordInvDWTForNumItersAux(double[][] sig, int avrg_ttn, int num_inv_iters,
            int num_fwd_iters, boolean dbg_flag) {
        if ( dbg_flag == true ) {
            System.out.println("Inv Transform ttn = " + avrg_ttn + " num_iters = " + num_inv_iters);
        }
        
        if ( num_inv_iters == 0 ) return;
        
        TwoDHWT.invRearrangeAHVD(sig, avrg_ttn, dbg_flag);
        for(int r = 0; r < (avrg_ttn<<1); r+=2) {
            for(int c = 0; c < (avrg_ttn<<1); c+=2) {
                TwoDHWT.invTwoByTwoDWT(sig, r, c);
            }
        }
        if ( dbg_flag == true ) {
            System.out.println("Applying 2x2 inv DWT to blocks");
            Utils.display2DArray(sig, sig.length, sig[0].length);
        }
        ordInvDWTForNumItersAuxAttic(sig, avrg_ttn<<1, num_inv_iters-1, dbg_flag);
    }
    
    public static void ordInvDWTForNumItersAttic(double[][] sig, int num_iters, boolean dbg_flag) {
        if ( sig.length != sig[0].length ) {
            throw new IllegalArgumentException("sig is not n x n");
        }
        if ( !Utils.isPowerOf2(sig.length) ) {
            throw new IllegalArgumentException("sig's dimension is not a power of 2");
        } 
        ordInvDWTForNumItersAuxAttic(sig, 1, num_iters, dbg_flag); 
    }
    
    public static void ordInvDWTForNumItersAuxAttic(double[][] sig, int avrg_ttn, int num_iters,
            boolean dbg_flag) {
        if ( dbg_flag == true ) {
            System.out.println("Inv Transform ttn = " + avrg_ttn + " num_iters = " + num_iters);
        }
        
        if ( num_iters == 0 ) return;
        
        TwoDHWT.invRearrangeAHVD(sig, avrg_ttn, dbg_flag);
        for(int r = 0; r < (avrg_ttn<<1); r+=2) {
            for(int c = 0; c < (avrg_ttn<<1); c+=2) {
                TwoDHWT.invTwoByTwoDWT(sig, r, c);
            }
        }
        if ( dbg_flag == true ) {
            System.out.println("Applying 2x2 inv DWT to blocks");
            Utils.display2DArray(sig, sig.length, sig[0].length);
        }
        ordInvDWTForNumItersAuxAttic(sig, avrg_ttn<<1, num_iters-1, dbg_flag);
    }
    
    public static void invRearrangeAHVD(double[][] sig, int avrg_ttn, boolean dbg_flag) {
        if ( dbg_flag == true ) {
            System.out.println("Inverse rearranging matrix for avrg_ttn = " + avrg_ttn);
            Utils.display2DArray(sig, sig.length, sig[0].length);
        }
        final int n = (avrg_ttn<<1);
        double[][] M = new double[n][n];
        for(int avrg_r=0, mr=0; avrg_r < avrg_ttn; avrg_r++, mr += 2) {
            for(int avrg_c=0, mc=0; avrg_c < avrg_ttn; avrg_c++, mc += 2) {
                M[mr][mc]     = sig[avrg_r][avrg_c];
                M[mr][mc+1]   = sig[avrg_r][avrg_c+avrg_ttn];
                M[mr+1][mc]   = sig[avrg_r+avrg_ttn][avrg_c];
                M[mr+1][mc+1] = sig[avrg_r+avrg_ttn][avrg_c+avrg_ttn];
            }
        }
        for(int r = 0; r < n; r++) {
            System.arraycopy(M[r], 0, sig[r], 0, n);
        }
        M=null;
        if ( dbg_flag == true ) {
            System.out.println("Rearranged matrix for avrg_ttn = " + avrg_ttn);
            Utils.display2DArray(sig, sig.length, sig[0].length);
        }
    }
}
