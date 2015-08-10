package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 */
public class IsCMajorPresent extends FourierAudioFreqEventDetector {
    
    static final String MESSAGE     = "C Major Present ";
    static final String NOTE_NAME   = "C Major";
    static final String DUAL_NAME   = "DO4-MI4-SOL4/C4-E4-G4";
    static final String LETTER_NAME = "C4-E4-G4";
    public IsCMajorPresent(double xlower, double xupper, double step, double cthresh, double athresh) {
        super(0, 0, xlower, xupper, step, cthresh, athresh);
    }
    
    @Override
    public boolean detectByCoeffMagnitude(double[] sample_sequence) {
        IsDo4C4Present do_c4 = new IsDo4C4Present(this.mXLower, this.mXUpper, this.mStep, this.mCoeffThresh, this.mAmpThresh);
        IsMi4E4Present mi_e4 = new IsMi4E4Present(this.mXLower, this.mXUpper, this.mStep, this.mCoeffThresh, this.mAmpThresh);
        IsSol4G4Present sol_g4 = new IsSol4G4Present(this.mXLower, this.mXUpper, this.mStep, this.mCoeffThresh, this.mAmpThresh);
        
        return do_c4.detectByCoeffMagnitude(sample_sequence) && mi_e4.detectByCoeffMagnitude(sample_sequence) && sol_g4.detectByCoeffMagnitude(sample_sequence);
    }
    
    @Override
    public String message() {
        return MESSAGE;
    }
    
    @Override
    public String getNoteName() {
        return NOTE_NAME;
    }
    
    @Override
    public String getLetterName() {
        return LETTER_NAME;
    }
    
}
