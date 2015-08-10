package org.vkedco.audio.music.notes;

/**
 *
 * @author Vladimir Kulyukin
 */

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.sound.sampled.*;
import org.vkedco.audio.wavlib.*;
import org.vkedco.wavelets.oneddft.OneDDFT;
import org.jscience.mathematics.number.Complex;
import org.vkedco.tolstov.chapter_01.FourierSeriesExpansion;
import org.vkedco.tolstov.utils.Harmonic;

public class AudioTrials {
    
    // change this to the directory where you place the audio files
    static final String AUDIO_DIR = 
            "C:/Users/Vladimir/Dropbox/MyShare/TEACHING/CS6890_NLP/support_materials/audio_files/";
    
    static final String AUDIO_DIR_02 = 
            "C:/Users/Vladimir/research/audio_files/wav/";
    
    static final String AUDIO_DIR_03 =
            "C:/Users/Vladimir/Android/Desktop/PianoKeyFrequencies/hattes_piano_keys/wav/";
    
    static final String AUDIO_DICTIONARY = AUDIO_DIR + "small_wav_audio_dictionary/";
    // take a path to an audio file and print various statistics of
    // that file such as total number of frames read, format encoding,
    // sample size, number of channels, etc.
    static void readSoundBytes(String audioPath, String format) {
        int totalFramesRead = 0;
        File fileIn = new File(audioPath);
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(fileIn);

            AudioFormat aufrmt = audioInputStream.getFormat();

            System.out.println("Audio Format: " + audioInputStream.getFormat());
            System.out.println("Audio Format Encoding: " + aufrmt.getEncoding());
            System.out.println("Audio Format Sample Size Bits: "
                    + aufrmt.getSampleSizeInBits());
            System.out.println("Audio Format Num Channels: " + aufrmt.getChannels());
            System.out.println("Audio Format Frame Size in Bytes: " + aufrmt.getFrameSize());

            int bytesPerFrame =
                    audioInputStream.getFormat().getFrameSize();

            if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
                bytesPerFrame = 1;
            }

            System.out.println("bytesPerFrame == " + bytesPerFrame);

            int numBytes = 1024 * bytesPerFrame;

            byte[] audioBytes = new byte[numBytes];

            //displayBytes(audioInputStream);

            try {
                int numBytesRead = 0;
                int numFramesRead = 0;
                while ((numBytesRead = audioInputStream.read(audioBytes))
                        != -1) {
                    numFramesRead = numBytesRead / bytesPerFrame;
                    totalFramesRead += numFramesRead;
                    //System.out.println("numFramesRead == " + numFramesRead);
                }
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }

            System.out.print(format + ": ");
            int b;
            for (int i = 0; i < audioBytes.length; i++) {
                b = audioBytes[i];
                System.out.print(b + " ");
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
    
    static void audioTrial_01() {
        System.out.println("MS  = " + WavFileManip.convertFrameSampleSizeToMilliSeconds(44100, 200));
        System.out.println("SEC = " + WavFileManip.convertFrameSampleSizeToSeconds(44100, 200));
        System.out.println("Frames = " + WavFileManip.convertMilliSecondsToFrameSampleSize(44100, 4));
        System.out.println("Frames = " + WavFileManip.convertSecondsToFrameSampleSize(44100, 0.25));
    }
    
    static void audioTrial_02() {
        WavFileManip.writeFrameSampleRangeToFile(AUDIO_DIR + "calcium.wav",
                                         AUDIO_DIR + "calcium_ca.wav",
                                         AUDIO_DIR + "calcium_ca.txt",
                                         200, 0, 290, 312);
    }
    
    static void audioTrial_03() {
        WavFileManip.writeFrameSampleRangeToFile(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_a.wav",
                AUDIO_DIR + "calcium_a.txt",
                200, 0, 313, 338);
    }
    
    static void audioTrial_03a() {
        WavFileManip.writeFrameSampleRangeToFile(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_si.wav",
                AUDIO_DIR + "calcium_si.txt",
                200, 0, 338, 400);
    }
    
    static void audioTrial_03b() {
        WavFileManip.writeFrameSampleRangeToFile(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_um.wav",
                AUDIO_DIR + "calcium_um.txt",
                200, 0, 395, 420);
    }
    
    static void audioTrial_03c() {
        WavFileManip.writeFrameSampleRangeToFile(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_all.wav",
                AUDIO_DIR + "calcium_all.txt",
                200, 0, 0, 1400);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file calcium.wav
    static void audioTrial_04() {
        // CALCIUM
        WavFileManip.detectSilence(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_silence.wav",
                AUDIO_DIR + "calcium_silence2.txt",
                200, 0, 1270.0, 0.005);
        
        WavFileManip.detectNonSilence(AUDIO_DIR + "calcium.wav",
                AUDIO_DIR + "calcium_non_silence.wav",
                AUDIO_DIR + "calcium_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
     // detectByCoeffMagnitude silence and non-silence in file calorie.wav
    static void audioTrial_05() {
       // CALORIE
        WavFileManip.detectSilence(AUDIO_DIR + "calorie.wav",
                AUDIO_DIR + "calorie_silence.wav",
                AUDIO_DIR + "calorie_silence2.txt",
                200, 0, 1270.0, 0.005);
        
        WavFileManip.detectNonSilence(AUDIO_DIR + "calorie.wav",
                AUDIO_DIR + "calorie_non_silence.wav",
                AUDIO_DIR + "calorie_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file calories.wav
    static void audioTrial_06() {
        // CALORIES
        WavFileManip.detectSilence(AUDIO_DIR + "calories.wav",
                AUDIO_DIR + "calories_silence.wav",
                AUDIO_DIR + "calories_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "calories.wav",
                AUDIO_DIR + "calories_non_silence.wav",
                AUDIO_DIR + "calories_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file carbohydrates.wav
    static void audioTrial_07() {
        // CARBOHYDRATES
        WavFileManip.detectSilence(AUDIO_DIR + "carbohydrates.wav",
                AUDIO_DIR + "carbohydrates_silence.wav",
                AUDIO_DIR + "carbohydrates_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "carbohydrates.wav",
                AUDIO_DIR + "carbohydrates_non_silence.wav",
                AUDIO_DIR + "carbohydrates_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file cholesterol.wav
    static void audioTrial_08() {
        // CHOLESTEROL
        WavFileManip.detectSilence(AUDIO_DIR + "cholesterol.wav",
                AUDIO_DIR + "cholesterol_silence.wav",
                AUDIO_DIR + "cholesterol_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "cholesterol.wav",
                AUDIO_DIR + "cholesterol_non_silence.wav",
                AUDIO_DIR + "cholesterol_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file dietary_fiber.wav
    static void audioTrial_09() {
         // DIETARY FIBER
        WavFileManip.detectSilence(AUDIO_DIR + "dietary_fiber.wav",
                AUDIO_DIR + "dietary_fiber_silence.wav",
                AUDIO_DIR + "dietary_fiber_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "dietary_fiber.wav",
                AUDIO_DIR + "dietary_fiber_non_silence.wav",
                AUDIO_DIR + "dietary_fiber_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file fact.wav
    static void audioTrial_10() {
        // FACT
        WavFileManip.detectSilence(AUDIO_DIR + "fact.wav",
                AUDIO_DIR + "fact_silence.wav",
                AUDIO_DIR + "fact_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "fact.wav",
                AUDIO_DIR + "fact_non_silence.wav",
                AUDIO_DIR + "fact_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file facts.wav
    static void audioTrial_11() {
        // FACTS
        WavFileManip.detectSilence(AUDIO_DIR + "facts.wav",
                AUDIO_DIR + "facts_silence.wav",
                AUDIO_DIR + "facts_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "facts.wav",
                AUDIO_DIR + "facts_non_silence.wav",
                AUDIO_DIR + "facts_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file ingredient.wav
    static void audioTrial_12() {
        // INGREDIENT
        WavFileManip.detectSilence(AUDIO_DIR + "ingredient.wav",
                AUDIO_DIR + "ingredient_silence.wav",
                AUDIO_DIR + "ingredient_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "ingredient.wav",
                AUDIO_DIR + "ingredient_non_silence.wav",
                AUDIO_DIR + "ingredient_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file ingredients.wav
    static void audioTrial_13() {
        // INGREDIENTS
        WavFileManip.detectSilence(AUDIO_DIR + "ingredients.wav",
                AUDIO_DIR + "ingredients_silence.wav",
                AUDIO_DIR + "ingredients_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "ingredients.wav",
                AUDIO_DIR + "ingredients_non_silence.wav",
                AUDIO_DIR + "ingredients_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file nutrition.wav
    static void audioTrial_14() {
        // NUTRITION
        WavFileManip.detectSilence(AUDIO_DIR + "nutrition.wav",
                AUDIO_DIR + "nutrition_silence.wav",
                AUDIO_DIR + "nutrition_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "nutrition.wav",
                AUDIO_DIR + "nutrition_non_silence.wav",
                AUDIO_DIR + "nutrition_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file nutrition_facts.wav
    static void audioTrial_15() {
        // NUTRITION FACTS
        WavFileManip.detectSilence(AUDIO_DIR + "nutrition_facts.wav",
                AUDIO_DIR + "nutrition_facts_silence.wav",
                AUDIO_DIR + "nutrition_facts_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "nutrition_facts.wav",
                AUDIO_DIR + "nutrition_facts_non_silence.wav",
                AUDIO_DIR + "nutrition_facts_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file saturated_fat.wav
    static void audioTrial_16() {
       // SATURATED FAT
        WavFileManip.detectSilence(AUDIO_DIR + "saturated_fat.wav",
                AUDIO_DIR + "saturated_fat_silence.wav",
                AUDIO_DIR + "saturated_fat_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "saturated_fat.wav",
                AUDIO_DIR + "saturated_fat_non_silence.wav",
                AUDIO_DIR + "saturated_fat_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in file serving_size.wav
    static void audioTrial_17() {
       // SERVING SIZE
        WavFileManip.detectSilence(AUDIO_DIR + "serving_size.wav",
                AUDIO_DIR + "serving_size_silence.wav",
                AUDIO_DIR + "serving_size_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "serving_size.wav",
                AUDIO_DIR + "serving_size_non_silence.wav",
                AUDIO_DIR + "serving_size_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in total_carbohydrate.wav
    static void audioTrial_18() {
        // TOTAL CARBOHYDRATE
        WavFileManip.detectSilence(AUDIO_DIR + "total_carbohydrate.wav",
                AUDIO_DIR + "total_carbohydrate_silence.wav",
                AUDIO_DIR + "total_carbohydrate_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "total_carbohydrate.wav",
                AUDIO_DIR + "total_carbohydrate_non_silence.wav",
                AUDIO_DIR + "total_carbohydrate_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in total_fat.wav
    static void audioTrial_19() {
        // TOTAL FAT
        WavFileManip.detectSilence(AUDIO_DIR + "total_fat.wav",
                AUDIO_DIR + "total_fat_silence.wav",
                AUDIO_DIR + "total_fat_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "total_fat.wav",
                AUDIO_DIR + "total_fat_non_silence.wav",
                AUDIO_DIR + "total_fat_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in trans_fat.wav
    static void audioTrial_20() {
        // TOTAL FAT
        WavFileManip.detectSilence(AUDIO_DIR + "trans_fat.wav",
                AUDIO_DIR + "trans_fat_silence.wav",
                AUDIO_DIR + "trans_fat_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "trans_fat.wav",
                AUDIO_DIR + "trans_fat_non_silence.wav",
                AUDIO_DIR + "trans_fat_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    // detectByCoeffMagnitude silence and non-silence in protein.wav
    static void audioTrial_21() {
         // PROTEIN
        WavFileManip.detectSilence(AUDIO_DIR + "protein.wav",
                AUDIO_DIR + "protein_silence.wav",
                AUDIO_DIR + "protein_silence2.txt",
                200, 0, 1270.0, 0.005);

        WavFileManip.detectNonSilence(AUDIO_DIR + "protein.wav",
                AUDIO_DIR + "protein_non_silence.wav",
                AUDIO_DIR + "protein_non_silence2.txt",
                200, 0, 1270.0, 0.005);
    }
    
    //WavAudioDictionary.buildWavAudioDictionary(AUDIO_DICTIONARY, 0, 200, 1270.0, 0.005);
    //    System.out.println(WavDictionary.mWords.size());
    //    WavDictionary.calorie_against_calories_test();
    static void audioTrial22() {
         WavDictionary.buildSegmentDictionary(AUDIO_DICTIONARY, 0, 2024, 1270.0, 0.005);
         WavDictionary.displayAudioArraySegments();
    }
    
    static void audioTrial24(String file_name, int frame_size, int freq_range_start, int freq_range_end, 
            double ath) {
        AudioEventDetection.displayFourierHarmonicsInWavFramesByAmplitude(file_name, frame_size, 
                0, freq_range_start, freq_range_end, -Math.PI, Math.PI, ath);
    }
    
    // detectByCoeffMagnitude DO4-C4
    static void audioTrial25(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting DO4-C4");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        
        IsDo4C4Present detector = new IsDo4C4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude DO5-C5
    static void audioTrial26(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting DO5-C5");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;

        IsDo5C5Present detector = new IsDo5C5Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude SOL5-G5
    static void audioTrial27(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting SOL5-G5");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsSol5G5Present detector = new IsSol5G5Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude C Major
    static void audioTrial28(String dir, String file_name, int frame_size, double cth, double ath) {
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsCMajorPresent detector = new IsCMajorPresent(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude LA4-A4
    static void audioTrial29(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting LA4-A4");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsLa4A4Present detector = new IsLa4A4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude RE6-D6
    static void audioTrial30(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting RE6-D6");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsRe6D6Present detector = new IsRe6D6Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(AUDIO_DIR_02 + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude FA4-F4
    static void audioTrial31(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting FA4-F4");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsFa4F4Present detector = new IsFa4F4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude LA0-A0
    static void audioTrial32(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting LA0-A0");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsLa0A0Present detector = new IsLa0A0Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    // detectByCoeffMagnitude SI0-B0
    static void audioTrial33(String dir, String file_name, int frame_size, double cth, double ath) {
        System.out.println("Detecting SI0-B0");
        int channel_num = 0;
        double xlower = -Math.PI;
        double xupper = Math.PI;
        IsSi0B0Present detector = new IsSi0B0Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
        AudioEventDetection.displayFourierAudioFreqEventsInWavFramesByAmplitude(dir + file_name, frame_size, channel_num, xlower, xupper, detector);
    }
    
    
    static void detect_DO4_C4(double cth, double ath) {
        int frame_size = 44100; int frame_size2 = 22050;
        audioTrial25(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial25(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    static void detect_DO5_C5(double cth, double ath) {
         int frame_size = 44100; int frame_size2 = 22050;
        audioTrial26(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial26(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    // G5 should be detected only in the files with C4 because
    // G5 is a component harmonic of C4.
    static void detect_SOL5_G5(double cth, double ath) {
         int frame_size = 44100; int frame_size2 = 22050;
        audioTrial27(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial27(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    static void detect_LA4_A4(double cth, double ath) {
        int frame_size = 44100; int frame_size2 = 22050;
        audioTrial29(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial29(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    // D6 should be detected in all audio files where G4 is present
    // because D6 is a component of G4.
    static void detect_RE6_D6(double cth, double ath) {
        int frame_size = 44100; int frame_size2 = 22050;
        audioTrial30(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial30(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    // D6 should be detected in all audio files where G4 is present
    // because D6 is a component of G4.
    static void detect_FA4_F4(double cth, double ath) {
        int frame_size = 44100; int frame_size2 = 22050;
        audioTrial31(AUDIO_DIR_02, "DO4_C4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "LA4_A4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "MI4_E4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "FA4_F4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "DO4_MI4_SOL4_C4_E4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "SOL4_G4.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "ODE_TO_JOY_RIGHT_HAND.wav", frame_size, cth, ath);
        System.out.println();
        audioTrial31(AUDIO_DIR_02, "tuning_fork_A4.wav", frame_size2, cth, ath);
        System.out.println();
    }
    
    static void detectAllNotesByHarmonicCoeffMagnitude(String dir, String file, int channel_num, int frame_size, 
            double xlower, double xupper, double cth, double ath) {

        ArrayList<FourierAudioFreqEventDetector> fds = new ArrayList<FourierAudioFreqEventDetector>();
        final double STEP = (xupper-xlower)/frame_size;
        
        fds.add(new IsLa0A0Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSi0B0Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsDo4C4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsMi4E4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsFa4F4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSol4G4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsLa4A4Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsDo5C5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsMi5E5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSol5G5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSi5B5Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsRe6D6Present(xlower, xupper, STEP, cth, ath));
       
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        
        map = AudioEventDetection.mapFourierAudioFreqEventsInWavFramesByCoeffMagnitude(dir + file, frame_size, channel_num, xlower, xupper, fds);
        
        for(Entry<Integer, ArrayList<String>> e : map.entrySet()) {
            if ( e.getValue().size() > 0 ) {
                System.out.print("Frame " + e.getKey().toString() + " -- ");
                for(String s : e.getValue() ) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
        
    }
    
    static void detectAllNotesByHarmonicAmplitude(String dir, String file, int channel_num, int frame_size, 
            double xlower, double xupper, double cth, double ath) {

        ArrayList<FourierAudioFreqEventDetector> fds = new ArrayList<FourierAudioFreqEventDetector>();
        
        final double STEP = (xupper-xlower)/frame_size;
        fds.add(new IsLa0A0Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSi0B0Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsDo4C4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsMi4E4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsFa4F4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSol4G4Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsLa4A4Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsDo5C5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsMi5E5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSol5G5Present(xlower, xupper, STEP, cth, ath));
        fds.add(new IsSi5B5Present(xlower, xupper, STEP, cth, ath));
        
        fds.add(new IsRe6D6Present(xlower, xupper, STEP, cth, ath));
       
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        
        map = AudioEventDetection.mapFourierAudioFreqEventsInWavFramesByAmplitude(dir + file, frame_size, channel_num, xlower, xupper, fds);
        
        for(Entry<Integer, ArrayList<String>> e : map.entrySet()) {
            if ( e.getValue().size() > 0 ) {
                System.out.print("Frame " + e.getKey().toString() + " -- ");
                for(String s : e.getValue() ) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
        
    }
   
    static void displayBytes(AudioInputStream ais) {
        int b;
        System.out.println("displayBytes");
        try {
            while ((b = ais.read()) != -1) {
                System.out.println("amplitude == " + b);
            }
            System.out.println("done");
        } catch (IOException ex) {
        }
        System.out.println("done");
    }
    
    static void detect4thOctaveNotes(String file, int channel_num, int frame_size, double xlower, double xupper, 
            double cth, double ath) {

        ArrayList<FourierAudioFreqEventDetector> fds = new ArrayList<FourierAudioFreqEventDetector>();
        fds.add(new IsDo4C4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        fds.add(new IsRe4D4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        fds.add(new IsMi4E4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        fds.add(new IsFa4F4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        fds.add(new IsSol4G4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        fds.add(new IsLa4A4Present(xlower, xupper, (xupper-xlower)/frame_size, cth, ath));
        
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        
        map = AudioEventDetection.mapFourierAudioFreqEventsInWavFramesByCoeffMagnitude(AUDIO_DIR_02 + file, frame_size, channel_num, xlower, xupper, fds);
        
        for(Entry<Integer, ArrayList<String>> e : map.entrySet()) {
            if ( e.getValue().size() > 0 ) {
                System.out.print("Frame " + e.getKey().toString() + " -- ");
                for(String s : e.getValue() ) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        }
        
    }
    
    static ArrayList<Integer> audioFileToArrayList(String inputPath) {
        int b;
        ArrayList<Integer> vector = new ArrayList<Integer>();
        try {
            File inFile;
            try {
                inFile = new File(inputPath);
            } catch (NullPointerException ex) {
                System.out.println("Error: on of the convertFileToAIFF" + " parameters is null!");
                return null;
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(inFile);
            while ((b = ais.read()) != -1) {
                vector.add(new Integer(b));
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } catch (UnsupportedAudioFileException ex) {
            System.err.println(ex.toString());
        }
        return vector;
    }

    static void convertFileToAIFF(String inputPath, String outputPath) {
        AudioFileFormat inFileFormat;
        File inFile;
        File outFile;
        try {
            inFile = new File(inputPath);
            outFile = new File(outputPath);
        } catch (NullPointerException ex) {
            System.out.println("Error: on of the convertFileToAIFF" + " parameters is null!");
            return;
        }
        try {
            inFileFormat = AudioSystem.getAudioFileFormat(inFile);
            System.out.println(inFileFormat + "\n");
            if (inFileFormat.getType() != AudioFileFormat.Type.AIFF) {
                System.out.println("Check 00");
                AudioInputStream inFileAIS = AudioSystem.getAudioInputStream(inFile);
                AudioFileFormat.Type[] types = AudioSystem.getAudioFileTypes(inFileAIS);
                for (AudioFileFormat.Type t : types) {
                    System.out.println(t);
                }
                System.out.println("Check 01");
                //inFileAIS.reset();
                System.out.println("Check 02");
                if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF, inFileAIS)) {
                    System.out.println("Conversion is possible!");
                    AudioSystem.write(inFileAIS, AudioFileFormat.Type.AIFF, outFile);
                    System.out.println("Successfully made AIFF file, "
                            + outFile.getPath() + ", from "
                            + inFileFormat.getType() + " file, "
                            + inFile.getPath() + ".");
                    inFileAIS.close();
                    return;
                } else {
                    System.out.println("Warning: AIFF conversion of "
                            + inFile.getPath()
                            + " is not currently supported by AudioSystem.");
                }
            } else {
                System.out.println("Input file " + inFile.getPath()
                        + " is AIFF." + " Conversion is unnecessary.");
            }
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Error: " + inFile.getPath()
                    + " is not a supported audio file type!");
            return;
        } catch (IOException ex) {
            System.out.println("Error: failure attempting to read " + inFile.getPath() + "!");
            return;
        }
    }

    public static int convertFrameSampleSizeToMilliSeconds(int sample_rate, int frame_sample_size) {
        return (int) (frame_sample_size * 1000.0) / sample_rate;
    }
    
    public static void applyOneDFTToSegments(String in_path, int frame_sample_size, int channelNum) {
        try {

            // Open the wav file specified as the first argument
            WavFile inWavFile = WavFile.openWavFile(new File(in_path));

            // Display information about the wav file
            inWavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = inWavFile.getNumChannels();
            long sampleRate = inWavFile.getSampleRate();

            System.out.println("WavFile's number of frames: " + inWavFile.getNumFrames());
            System.out.println("WavFile's sample rate: " + inWavFile.getSampleRate());

            // Create a buffer of frame_sample_size frames
            double[][] buffer = new double[numChannels][frame_sample_size];

            int framesRead;
            //int framesWritten;
            //long sample_rate = inWavFile.getSampleRate();
            // normalizer is a number of seconds
            double normalizer = WavFileManip.convertFrameSampleSizeToSeconds((int) sampleRate, frame_sample_size);
            int currFrameSampleNum = 0;

                //currZCR = ZeroCrossingRate.computeZCR01(buffer[channel_num], normalizer);
                //currAmp = WavFileManip.computeAvrgAbsAmplitude(buffer[channel_num]);
            do {
                framesRead = inWavFile.readFrames(buffer, frame_sample_size);
                if (framesRead > 0) {
                    currFrameSampleNum++;
                    Complex [] spectrum = OneDDFT.computeSpectrum(buffer[channelNum], -1, 0.001);
                    OneDDFT.displayMaxFreqMagn(spectrum);
                }

            } while (framesRead != 0);

            inWavFile.close();
            

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    // follow applyOneDDFTtoSegments to implement a function that extracts coefficients
    // for specific frequencies in a frame.
    public static void searchFramesForFourierCoeffs(String in_path, int frame_sample_size, int channelNum, 
            int freq_range_start, int freq_range_end, double xlower, double xupper, double step) {
        try {

            // Open the wav file specified as the first argument
            WavFile inWavFile = WavFile.openWavFile(new File(in_path));

            // Display information about the wav file
            inWavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = inWavFile.getNumChannels();
            long sampleRate = inWavFile.getSampleRate();

            System.out.println("WavFile's number of frames: " + inWavFile.getNumFrames());
            System.out.println("WavFile's sample rate: " + inWavFile.getSampleRate());

            // Create a buffer of frame_sample_size frames
            double[][] buffer = new double[numChannels][frame_sample_size];

            int framesRead;
            //int framesWritten;
            //long sample_rate = inWavFile.getSampleRate();
            // normalizer is a number of seconds
            double normalizer = WavFileManip.convertFrameSampleSizeToSeconds((int) sampleRate, frame_sample_size);
            int currFrameSampleNum = 0;
            double norm_step = (xupper - xlower)/frame_sample_size;

                //currZCR = ZeroCrossingRate.computeZCR01(buffer[channel_num], normalizer);
                //currAmp = WavFileManip.computeAvrgAbsAmplitude(buffer[channel_num]);
            int count_frames = 0;
            do {
                framesRead = inWavFile.readFrames(buffer, frame_sample_size);
                if (framesRead > 0) {
                    currFrameSampleNum++;
                    //Complex [] spectrum = OneDDFT.computeSpectrum(buffer[channelNum], -1, 0.001);
                    //OneDDFT.displayMaxFreqMagn(spectrum);
                    
                    ArrayList<Harmonic> clist = FourierSeriesExpansion.expandFromFunctionData(buffer[channelNum], freq_range_start, freq_range_end, xlower, xupper, norm_step);
                   
                    Harmonic maxpair = FourierSeriesExpansion.findMaxCoeffPairAboveCoeffThresh(clist, 0.001);
                    if ( maxpair != null ) {
                         System.out.println(count_frames + ")***************");
                        System.out.println("MAX = " + maxpair.toString());
                    }
                    //else {
                    //    System.out.println("MAX = NULL");
                    //}
                    /*
                    for(Harmonic cp: clist) {
                        if ( cp.getAC() != 0 || cp.getAC() != 0 )
                            System.out.println(cp.toString());
                    }
                     * 
                     */
                    count_frames++;
                }

            } while (framesRead != 0);

            inWavFile.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    // the best result is chan_num=0, frame_size = 44100/2, -Math.PI, Math.PI, 0.022);
    public static void test4thOctaveDetect(String file_name, int chan_num, int[] frame_sizes, 
            double xlower, double xupper, double cth, double ath) {
        for(int fs: frame_sizes) {
            detect4thOctaveNotes(file_name, chan_num, fs, xlower, xupper, cth, ath);
        }
    }
    
    public static void main(String[] args) {
        detectAllNotesByHarmonicCoeffMagnitude(AUDIO_DIR_02, "tuning_fork_A4.wav", 0, 44100/2, -Math.PI, Math.PI, 0.01, 0.01);
        detectAllNotesByHarmonicAmplitude(AUDIO_DIR_02, "tuning_fork_A4.wav", 0, 44100/2, -Math.PI, Math.PI, 0.01, 0.01);
    }

 
}
