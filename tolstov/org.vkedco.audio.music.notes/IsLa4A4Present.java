package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsLa4A4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE     = "LA4-A4 Present ";
    static final String DUAL_NAME   = "LA4-A4";
    static final String LETTER_NAME = "A4";
    public IsLa4A4Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.LA4_A4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.LA4_A4_FREQ_RANGE_END,
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
