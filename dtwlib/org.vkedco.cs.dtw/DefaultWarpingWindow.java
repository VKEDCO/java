package org.vkedco.cs.dtw;

/**
 *
 * @author Vladimir Kulyukin
 */
public class DefaultWarpingWindow implements IWarpingWindow {
    
    private int mDistanceThresh = 0;

    @Override
    public boolean isIn(int r, int c) {
        return Math.abs(r - c) <= mDistanceThresh;
    }
    
    public DefaultWarpingWindow(int th) {
        mDistanceThresh = th;
    }
    
    
    
}
