package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */
public class IsSol4G4Present extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE      = "SOL4-G4 Present ";
    static final String DUAL_NAME    = "SOL4-G4";
    static final String LETTER_NAME  = "G4";
    
    public IsSol4G4Present(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(Piano88A440NoteFrequencyRanges.SOL4_G4_FREQ_RANGE_START, 
                Piano88A440NoteFrequencyRanges.SOL4_G4_FREQ_RANGE_END,
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
