package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsMi4E4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE      = "MI4-E4 Present ";
    static final String DUAL_NAME    = "MI4-E4";
    static final String LETTER_NAME  = "E4";
    public IsMi4E4Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.MI4_E4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.MI4_E4_FREQ_RANGE_END,
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

