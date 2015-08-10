package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */

public class IsDo5C5Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "DO5-C5 Present ";
    static final String DUAL_NAME = "DO5-C5";
    static final String LETTER_NAME = "C5";
    public IsDo5C5Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.DO5_C5_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.DO5_C5_FREQ_RANGE_END,
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
