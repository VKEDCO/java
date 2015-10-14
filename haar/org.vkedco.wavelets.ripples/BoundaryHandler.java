package org.vkedco.wavelets.ripples;

/**
 *
 * @author vladimir kulyukin
 * 
 * TODO: Test it on the example of signal of lengths 2, 4, and 8
 */
public class BoundaryHandler {
    
    public static int mirror_wrapup(int start, int end, int i) {
        if ( start > end ) throw new IllegalArgumentException("Start = " + start + " > End = " + end);  
        final int n = (end - start + 1);
        final int real_i = start + i;
        if ( end >= start ) {
            if ( real_i >= start && real_i <= end ) {
                return real_i;
            }
            else {
                if ( real_i > end ) {
                    return start + (n - ((real_i % n) + 1));
                }
                else if ( real_i < start ) {
                    return start + (Math.abs(real_i+1) % n);
                }
            }
        }
        return -1;
    }
    
    public static void test_mirror_wrapup(int start, int end, int i) {
        System.out.println(start + ", " + end + ", " + i + " -> "    + BoundaryHandler.mirror_wrapup(start, end, i));
        //System.out.println(start + ", " + end + ", " + (-i) + " -> " + BoundaryHandler.mirror_wrapup(start, end, -i));
    }
    
    public static void main(String[] args) {
        //test_mirror_wrapup(2, 3, 0);
        //test_mirror_wrapup(2, 3, 1);
        //test_mirror_wrapup(0, 4, -5);
        System.out.println(4 >> 1);
        System.out.println(4 >> 2);
        System.out.println(2 << 1);
        System.out.println(2 << 2);
    }
    
}
