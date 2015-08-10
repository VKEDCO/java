
package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsFa4F4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE     = "FA4-F4 Present ";
    static final String DUAL_NAME   = "FA4-F4";
    static final String LETTER_NAME = "F4";
    public IsFa4F4Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.FA4_F4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.FA4_F4_FREQ_RANGE_END,
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

