package org.vkedco.audio.music.notes;

/**
 *
 * @author vladimir kulyukin
 * 
 * Mappings from keys to frequencies and back on an 88 key A440 piano board.
 */

public class PianoKeyFrequency {
    
    public static double keyToFrequency(int k) {
        return Math.pow(2.0, (k - 49)/12.0)*440.0;
    }
    
    public static int frequencyToKey(double f) {
        return (int)(12*Math.log(f/440.0)/Math.log(2.0) + 49);
    }
    
    public static void test_0th_octave() {
        int a0key = 1;
        int b0key = 2;
        double a0f = PianoKeyFrequency.keyToFrequency(a0key);
        double b0f = PianoKeyFrequency.keyToFrequency(b0key);
        
        System.out.println("A0's f = " + a0f);
        System.out.println("B0's f = " + b0f);
        
        System.out.println("A0's   real   key = " + a0key);
        System.out.println("A0's computed key = " + PianoKeyFrequency.frequencyToKey(a0f));
        System.out.println("B0's   real   key = " + b0key);
        System.out.println("B0's computed key = " + PianoKeyFrequency.frequencyToKey(b0f));   
    }
    
    // test mappings of the 4th octave
    public static void test_4th_octave() {
        int c4k = 40;
        int d4k = 42;
        int e4k = 44;
        int f4k = 45;
        int g4k = 47;
        int a4k = 49;
        int b4k = 51;
        
        double c4f = PianoKeyFrequency.keyToFrequency(c4k);
        double d4f = PianoKeyFrequency.keyToFrequency(d4k);
        double e4f = PianoKeyFrequency.keyToFrequency(e4k);
        double f4f = PianoKeyFrequency.keyToFrequency(f4k);
        double g4f = PianoKeyFrequency.keyToFrequency(g4k);
        double a4f = PianoKeyFrequency.keyToFrequency(a4k);
        double b4f = PianoKeyFrequency.keyToFrequency(b4k);
    
        System.out.println("C4's f = " + c4f);
        System.out.println("D4's f = " + d4f);
        System.out.println("E4's f = " + e4f);
        System.out.println("F4's f = " + f4f);
        System.out.println("G4's f = " + g4f);
        System.out.println("A4's f = " + a4f);
        System.out.println("B4's f = " + b4f);
        
        System.out.println("C4's   real   key = " + c4k);
        System.out.println("C4's computed key = " + PianoKeyFrequency.frequencyToKey(c4f));
        System.out.println("D4's   real   key = " + d4k);
        System.out.println("D4's computed key = " + PianoKeyFrequency.frequencyToKey(d4f));
        System.out.println("E4's   real   key = " + e4k);
        System.out.println("E4's computed key = " + PianoKeyFrequency.frequencyToKey(e4f));
        System.out.println("F4's   real   key = " + f4k);
        System.out.println("F4's computed key = " + PianoKeyFrequency.frequencyToKey(f4f));
        System.out.println("G4's   real   key = " + g4k);
        System.out.println("G4's computed key = " + PianoKeyFrequency.frequencyToKey(g4f));
        System.out.println("A4's   real   key = " + a4k);
        System.out.println("A4's computed key = " + PianoKeyFrequency.frequencyToKey(a4f));
    }
    
    public static void main(String[] args) {
        
        test_0th_octave();
      
    }
}
