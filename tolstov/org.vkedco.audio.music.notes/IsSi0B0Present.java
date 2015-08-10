
package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsSi0B0Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE         = "SI0-B0 Present ";
    static final String LETTER_NAME     = "B0";
    static final String DUAL_NAME       = "SI0-B0";
    public IsSi0B0Present(double xlower, double xupper, double step, 
            double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.SI0_B0_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.SI0_B0_FREQ_RANGE_END,
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
