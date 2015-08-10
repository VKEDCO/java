package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */
public class IsSol5G5Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE      = "SOL5-G5 Present ";
    static final String DUAL_NAME    = "SOL5-G5";
    static final String LETTER_NAME  = "G5";
    
    public IsSol5G5Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.SOL5_G5_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.SOL5_G5_FREQ_RANGE_END,
                xlower, xupper, step, cthresh, athresh);
    }
    
    @Override
    public String message() {
        return MESSAGE;
    }
    
    @Override
    public String getDualName() {
        return DUAL_NAME;
    }
    
    @Override
    public String getLetterName() {
        return LETTER_NAME;
    }
}

