package org.vkedco.audio.music.notes;

import java.util.ArrayList;
import org.vkedco.tolstov.chapter_01.FourierSeriesExpansion;
import org.vkedco.tolstov.utils.Harmonic;

/**
 *
 * @author vladimir kulyukin
 */

public class FourierAudioFreqEventDetector {
    
    protected int mFreqRangeStart;
    protected int mFreqRangeEnd;
    protected double mXLower;
    protected double mXUpper;
    protected double mStep;
    protected double mCoeffThresh;
    protected double mAmpThresh;
    
    public FourierAudioFreqEventDetector(int fr_start, int fr_end, double xlower, double xupper, 
            double step, double coeff_thresh, double amp_thresh) 
    {
        mFreqRangeStart = fr_start;
        mFreqRangeEnd = fr_end;
        mXLower = xlower;
        mXUpper = xupper;
        mStep = step;
        mCoeffThresh = coeff_thresh;
        mAmpThresh = amp_thresh;
    }
    
    public boolean detectByCoeffMagnitude(double[] sample_sequence) 
    {
        ArrayList<Harmonic> clist = FourierSeriesExpansion.expandFromFunctionData(sample_sequence, 
                mFreqRangeStart, mFreqRangeEnd, mXLower, mXUpper, mStep);
        Harmonic maxcp = FourierSeriesExpansion.findMaxCoeffPairAboveCoeffThresh(clist, mCoeffThresh);
        clist = null;
        return maxcp != null;
    }
    
    public boolean detectByAmplitude(double[] sample_sequence) 
    {
        ArrayList<Harmonic> clist = FourierSeriesExpansion.expandFromFunctionData(sample_sequence, 
                mFreqRangeStart, mFreqRangeEnd, mXLower, mXUpper, mStep);
        Harmonic maxcp = FourierSeriesExpansion.findMaxCoeffPairAboveAmplitudeThresh(clist, mAmpThresh);
        clist = null;
        return maxcp != null;
    }
    
    public String message() {
        return "";
    }
    
    public String getDualName() {
        return "";
    }
    
    public String getLetterName() {
        return "";
    }
    
    public String getNoteName() {
        return "";
    }
}
