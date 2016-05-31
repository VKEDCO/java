package org.vkedco.wavelets.lifting;

import java.util.ArrayList;

/**
 * @author Vladimir Kulyukin
 */
public class ForwardDWTNetwork {
    
    Storage D1;
    Storage S0;
    Storage D0;
    Splitter SPLITTER1;
    Predictor PREDICTOR1;
    Updater UPDATER1;
    Minus MINUS1;
    Plus PLUS1;
    Wire WIRE1; Wire WIRE2; Wire WIRE3;
    Wire WIRE4; Wire WIRE5; Wire WIRE6;
    Wire WIRE7; Wire WIRE8; Wire WIRE9;
    ArrayList<Wire> NETWORK;
    
    public ForwardDWTNetwork() {
        D1 = new Storage();
        S0 = new Storage();
        D0 = new Storage();
        SPLITTER1 = new Splitter();
        PREDICTOR1 = new Predictor();
        UPDATER1 = new Updater();
        MINUS1  = new Minus();
        PLUS1 = new Plus();
        WIRE1 = new Wire(D1, SPLITTER1);
        WIRE2 = new Wire(SPLITTER1, PREDICTOR1);
        WIRE3 = new Wire(SPLITTER1, MINUS1);
        WIRE4 = new Wire(PREDICTOR1, MINUS1);
        WIRE5 = new Wire(MINUS1, UPDATER1);
        WIRE6 = new Wire(MINUS1, D0);
        WIRE7 = new Wire(SPLITTER1, PLUS1);
        WIRE8 = new Wire(UPDATER1, PLUS1);
        WIRE9 = new Wire(PLUS1, S0);
        
        NETWORK = new ArrayList<>();
        NETWORK.add(WIRE1); NETWORK.add(WIRE2); NETWORK.add(WIRE3);
        NETWORK.add(WIRE4); NETWORK.add(WIRE5); NETWORK.add(WIRE6);
        NETWORK.add(WIRE7); NETWORK.add(WIRE8); NETWORK.add(WIRE9);
    }
    
    public Storage processSignal(Storage storage) {
        D1.setInput(storage.getInput());
        D1.setOutput(storage.getOutput());
        
        for(Wire w: NETWORK) {
            w.transfer();
        }
        
        System.out.print("Course Samples:\t");
        for(double d: S0.getOutput()) {
            System.out.print(d + " ");
        }
        System.out.println();
        
        System.out.print("Wavelets:\t");
        for(double d: D0.getOutput()) {
            System.out.print(d + " ");
        }
        System.out.println();
        return S0;
    }
    
    public static void main(String[] args) {
        final double[] signal = {11, 3};
        final double[] signal2 = {5, 1, 2, 8};
        
        ForwardDWTNetwork net1 = new ForwardDWTNetwork();
        ForwardDWTNetwork net2 = new ForwardDWTNetwork();
        
        //Storage storage1 = new Storage();
        //storage1.setInput(signal); storage1.setOutput(signal);
        //Storage output = net1.processSignal(storage1);
        
        Storage storage2 = new Storage();
        storage2.setInput(signal2);
        storage2.setOutput(signal2);
        Storage output2 = net1.processSignal(storage2);
        double[] output2_signal = output2.getOutput();
        for(int i = 0; i < output2_signal.length; i++) {
            System.out.print(output2_signal[i] + " ");
        }
        System.out.println();
        Storage output3 = net2.processSignal(output2);
        double[] output3_signal = output3.getOutput();
        for(int i = 0; i < output3_signal.length; i++) {
            System.out.print(output3_signal[i] + " ");
        }
        System.out.println();
        
        //processSignal(signal2);
        
    }
    
}
