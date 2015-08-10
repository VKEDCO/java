package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */

public class IsLa0A0Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "LA0-A0 Present ";
    static final String DUAL_NAME = "LA0-A0";
    static final String LETTER_NAME = "A0";
    public IsLa0A0Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.LA0_A0_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.LA0_A0_FREQ_RANGE_END,
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

