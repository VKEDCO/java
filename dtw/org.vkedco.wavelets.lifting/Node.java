
package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Node {
    
    protected double[] mInput;
    protected double[] mOutput;
    
    public void setInput(double[] input) { mInput = input; }
    public void setOutput(double[] output) { mOutput = output; }
    public double[] getOutput() { return mOutput; }
    public double[] getInput() { return mInput; }
}
