
package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */
public class IsSi5B5Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE = "SI5-B5 Present ";
    static final String DUAL_NAME = "SI5-B5";
    static final String LETTER_NAME = "B5";
    
    public IsSi5B5Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.SI5_B5_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.SI5_B5_FREQ_RANGE_END,
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
