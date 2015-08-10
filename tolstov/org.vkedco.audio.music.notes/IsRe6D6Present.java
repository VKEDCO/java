package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */
public class IsRe6D6Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "RE6-D6 Present ";
    static final String DUAL_NAME = "RE6-D6";
    static final String LETTER_NAME = "D6";
    public IsRe6D6Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.RE6_D6_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.RE6_D6_FREQ_RANGE_END,
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
    
    public String getLetterName() {
        return LETTER_NAME;
    }
}
