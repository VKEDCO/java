package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */
public class IsRe4D4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "RE4-D4 Present ";
    static final String DUAL_NAME = "RE4-D4";
    static final String LETTER_NAME = "D4";
    public IsRe4D4Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.RE4_D4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.RE4_D4_FREQ_RANGE_END,
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
