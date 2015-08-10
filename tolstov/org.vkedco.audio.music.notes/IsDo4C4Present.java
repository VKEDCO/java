package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsDo4C4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE         = "DO4-C4 Present ";
    static final String LETTER_NAME     = "C4";
    static final String DUAL_NAME       = "DO4-C4";
    public IsDo4C4Present(double xlower, double xupper, double step, 
            double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.DO4_C4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.DO4_C4_FREQ_RANGE_END,
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
