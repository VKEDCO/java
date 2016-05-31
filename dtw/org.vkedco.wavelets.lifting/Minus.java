
package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Minus extends Node {
    
    private double[] mLeftHandInput; 
    private double[] mRightHandInput;
    
    public void minus() {
        if ( mLeftHandInput == null || mRightHandInput == null ) return;
        mOutput = new double[mLeftHandInput.length];
        for(int i = 0; i < mLeftHandInput.length; i++) {
            mOutput[i] = mLeftHandInput[i] - mRightHandInput[i];
        }
        
        mLeftHandInput  = null;
        mRightHandInput = null;
    }
    
    public void setLeftHandInput(double[] input) {
        mLeftHandInput = input;
    }
    
    public double[] getLeftHandInput() {
        return mLeftHandInput;
    }
    
    public void setRightHandInput(double[] input) {
        mRightHandInput = input;
    }
    
    public double[] getRightHandInput() {
        return mRightHandInput;
    }
    
}
