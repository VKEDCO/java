package org.vkedco.scicomp.linalgebra.matmanip;

// @author Vladimir Kulyukin

public class MatManip {
    
    public final static double[][] multiply(double[][] mat1, double[][] mat2) {
        final int mat1_num_cols = mat1[0].length;
        final int mat2_num_rows = mat2.length;
        
        if ( mat1_num_cols != mat2_num_rows ) {
            System.out.println("mat1_num_cols != mat2_num_rows");
            return null;
        }
        
        final int num_rows = mat1.length;
        final int num_cols = mat2[0].length;
        
        //if ( num_rows != num_cols ) return null;
        
        double[][] mult = new double[num_rows][num_cols];
        
        for(int row = 0; row < num_rows; row++) {
            for(int col = 0; col < num_cols; col++) {
                mult[row][col] = 0;
                System.out.println("computing " + "mult[" + row + "][" + col + "]");
                for(int c = 0; c < mat2_num_rows; c++) {
                   System.out.println("mat1[" + row + "][" + c + "]*mat2[" + c + "][" + col + "]");
                   System.out.println("mat1[" + row + "][" + c + "] = " + mat1[row][c]);
                   System.out.println("mat2[" + c + "][" + col + "] = " + mat2[c][col]);
                   System.out.println("mat1*mat2 = " + (mat1[row][c]*mat2[c][col]));
                   mult[row][col] += mat1[row][c]*mat2[c][col];
                   System.out.println("mult[" + row + "][" + col + "] = " + mult[row][col]);
                }
            }
        }
        
        System.out.println("CHECK: " + mult.length);
        System.out.println("CHECK: " + mult[0].length);
        
        return mult;
    }
    
    public static double[][] invert(double[][] mat) {
        final int num_rows = mat.length;
        final int num_cols = mat[0].length;
        if ( num_rows != num_cols ) return null;
        
        double[][] inv = new double[num_rows][num_rows];
        
        final int n = num_rows;
        for(int r=0; r < n; r++) {
            for(int c=0; c < n; c++) {
                if ( r == c ) 
                    inv[r][c] = 1;
                else
                    inv[r][c] = 0;
            }
        }
        
        double scalar = 0;
        for(int r=0; r < n; r++) {
            scalar = mat[r][r];
            for(int c=0; c < n; c++) {
                mat[r][c] /= scalar;
                inv[r][c] /= scalar;
            }
            for(int row=0; row < n; row++) {
                scalar = mat[row][r];
                for(int c=0; c < n; c++) {
                    if ( r == row ) { break; }
                    mat[row][c] -= scalar*mat[r][c];
                    inv[row][c] -= scalar*inv[r][c];
                }
            }
        }
        
        return inv;
    }
    
    public static void displayMat(double[][] mat) {
        final int num_rows = mat.length;
        final int num_cols = mat[0].length;
        
        System.out.println("displayMat");
        System.out.println(num_rows);
        System.out.println(num_cols);
        
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                System.out.print(mat[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void test_01() {
        double[][] mat1 = {
            {2, -2, 6},
            {1, 0, 4}
        };
        
        double[][] mat2 = {
            {-1, -4},
            {-2, 5},
            {7,  0}
        };
        
        double[][] rslt = MatManip.multiply(mat1, mat2);
        MatManip.displayMat(rslt);
    }
    
    public static void test_02() {
        double[][] mat1 = {
            {2,   4, -3},
            {-2, -4, -1},
            {0,   5,  6}
        };
        
        double[][] mat2 = {
            {1,  2, 7},
            {3,  4, 8},
            {9, -3, 5}
        };
        
        double[][] rslt = MatManip.multiply(mat1, mat2);
        MatManip.displayMat(rslt);
    }
    
    public static void test_03() {
        double[][] mat1 = {
            {1,  -3, 6},
            {5,   7, 8}
        };
        
        double[][] mat2 = {
            {2, 9, -2, 0},
            {4, 5, 3, -1},
            {1, 6, 7, 8}
        };
        
        double[][] rslt = MatManip.multiply(mat1, mat2);
        MatManip.displayMat(rslt);
    }
    
    public static void test_04() {
        double[][] mat = {
            {45, 2, 3,  67},
            {76, 3, 5,  89},
            {3, 27, 35, 19},
            {61, 4, 2,  87}
        };
        
        double[][] mat2 = {
            {45, 2, 3,  67},
            {76, 3, 5,  89},
            {3, 27, 35, 19},
            {61, 4, 2,  87}
        };
        
        System.out.println(mat[0].length);
        System.out.println(mat.length);
        double[][] inv = MatManip.invert(mat);
        MatManip.displayMat(inv);
        double[][] mat3 = MatManip.multiply(mat2, inv);
        System.out.println("mat2.length = " + mat2.length);
        System.out.println("mat2[0].length = " + mat2[0].length);
        System.out.println("CHECK 1");
        MatManip.displayMat(mat3);
        System.out.println("CHECK 2");
    }
    
    public static void test_05() {
        double[][] mat = {
            {4, 3},
            {3, 2},
        };
        
        double[][] mat2 = {
            {4, 3},
            {3, 2},
        };
        
        System.out.println(mat[0].length);
        System.out.println(mat.length);
        double[][] inv = MatManip.invert(mat);
        MatManip.displayMat(inv);
        double[][] mat3 = MatManip.multiply(mat2, inv);
        //System.out.println("mat2.length = " + mat2.length);
        //System.out.println("mat2[0].length = " + mat2[0].length);
        //System.out.println("CHECK 1");
        MatManip.displayMat(mat3);
        //System.out.println("CHECK 2");
    }
    
    public static void main(String[] args) {
        
        test_04();
        
    }
    
}
