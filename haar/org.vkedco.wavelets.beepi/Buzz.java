
package org.vkedco.wavelets.beepi;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vkedco.wavelets.ripples.ApplyDWT;
import org.vkedco.wavelets.utils.Utils;

/**
 *
 * @author vladimir kulyukin
 */
public class Buzz {
    
    static void display_signal(double[] signal) {
        for(double d: signal) {
            System.out.println(d);
        }
    }
    
    public static void doMultiresAnalysisOfBuzzWavFile(String inpath, String dwt_path, String inverse_path, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, 
            int num_scales, int scale_num) {
        double[] ds = Utils.readInPrimDoublesFromLineFile(inpath);
        double[] sub_ds = Utils.largestSubsignalOfPowerOf2(ds);
        ds = null;
        try {
            System.out.println("signal length = " + sub_ds.length);
            doMultiresAnalysisOfBuzzWavFileAux(dwt_path, inverse_path, sub_ds, dwt, coeff, sub_ds.length, num_scales, scale_num);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void doMultiresAnalysisOfBuzzWavFileAux(String dwt_path, String inverse_path, double[] signal, ApplyDWT.DWT dwt, ApplyDWT.COEFF coeff, int signal_size, int num_scales, int scale_num) throws UnsupportedEncodingException {
        ApplyDWT.forwardDWTForNumIters(signal, dwt, num_scales);
        double[] reconstructed_signal = new double[signal.length];
        ApplyDWT.multiresSignalReconstruct(signal, reconstructed_signal, dwt, coeff, num_scales, scale_num);
        
        System.out.println("dwt signal length = " + signal.length);
        System.out.println("inv signal length = " + reconstructed_signal.length);
        
        try ( 
              Writer dwt_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dwt_path),     "utf-8"));
              Writer inv_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inverse_path), "utf-8"))  
            ) 
        {
            for(double d: signal) { dwt_writer.write(d + "\n"); }
            for(double d: reconstructed_signal) { inv_writer.write(d + "\n"); }
            dwt_writer.flush();
            inv_writer.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // thresh could be, for example, the mean of the transformed signal
    static void keepFastVarsInBuzzWavFile(String signal_path, String output_path, ApplyDWT.DWT dwt, int num_iters, double thresh) {
        // 1. read in the signal from a file
        double[] signal = Utils.readInPrimDoublesFromLineFile(signal_path);
        double[] power_of_2_signal = Utils.largestSubsignalOfPowerOf2(signal);
        signal = null;
        // 2. keep the fast vars in the signal
        ApplyDWT.genericKeepFastVarsInSignal(power_of_2_signal, dwt, num_iters);
        // 3. output the fast vars into a file for graphing
        try (
                Writer out_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_path), "utf-8"));
            )
        {
            for(double d: power_of_2_signal) {
                if ( Math.abs(d) >= thresh ) {
                    out_writer.write(d + "\n");
                }
                else {
                    out_writer.write(0.0 + "\n");
                }
            }
            out_writer.flush();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void keepSlowVarsInBuzzWavFile(String signal_path, String output_path, ApplyDWT.DWT dwt, int num_iters, double thresh) {
        // 1. read in the signal from a file
        double[] signal = Utils.readInPrimDoublesFromLineFile(signal_path);
        double[] power_of_2_signal = Utils.largestSubsignalOfPowerOf2(signal);
        signal = null;
        // 2. keep the fast vars in the signal
        ApplyDWT.genericKeepSlowVarsInSignal(power_of_2_signal, dwt, num_iters);
        // 3. output the fast vars into a file for graphing
        try (
                Writer out_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_path), "utf-8"));
            )
        {
            for(double d: power_of_2_signal) {
                if ( Math.abs(d) >= thresh ) {
                    out_writer.write(d + "\n");
                }
                else {
                    out_writer.write(0.0 + "\n");
                }
            }
            out_writer.flush();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Buzz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //genericKeepFastVarsInSignal(double[] signal, ApplyDWT.DWT dwt, int num_iters)
    public static void main(String[] args) {
        keepFastVarsInBuzzWavFile(
                "C://Users//vladimir//research//audio_files//wav//beepi//garland_07jul15_04aug15//2015-07-20_18-51-10_44100_1000.txt",
                "C://Users//vladimir//research//audio_files//wav//beepi//garland_07jul15_04aug15//2015-07-20_18-51-10_44100_1000_hwt_fast_vars_5.txt",
                ApplyDWT.DWT.HWT, 5, 0.0
        );
    }
}
