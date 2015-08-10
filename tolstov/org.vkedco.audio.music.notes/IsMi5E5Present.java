
package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsMi5E5Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "MI5-E5 Present ";
    static final String DUAL_NAME = "MI5-E5";
    static final String LETTER_NAME = "E5";
    
    public IsMi5E5Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.MI5_E5_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.MI5_E5_FREQ_RANGE_END,
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
