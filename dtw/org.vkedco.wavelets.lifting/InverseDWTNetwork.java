package org.vkedco.wavelets.lifting;

import java.util.ArrayList;

/**
 *
 * @author Vladimir Kulyukin
 */
public class InverseDWTNetwork {
    
    Storage S0;
    Storage D0;
    Storage S1;
    Merger MERGER1;
    Predictor PREDICTOR1;
    Updater UPDATER1;
    Minus MINUS1;
    Plus PLUS1;
    Wire WIRE1; Wire WIRE2; Wire WIRE3;
    Wire WIRE4; Wire WIRE5; Wire WIRE6;
    Wire WIRE7; Wire WIRE8; Wire WIRE9;
    ArrayList<Wire> NETWORK;
    
    public InverseDWTNetwork() {
        S0 = new Storage(); 
        D0 = new Storage(); 
        S1 = new Storage();
        MERGER1 = new Merger();
        PREDICTOR1 = new Predictor();
        UPDATER1 = new Updater();
        MINUS1  = new Minus();
        PLUS1 = new Plus();
        WIRE1 = new Wire(S0, MINUS1);
        WIRE2 = new Wire(D0, UPDATER1);
        WIRE3 = new Wire(UPDATER1, MINUS1);
        WIRE4 = new Wire(MINUS1, PREDICTOR1);
        WIRE5 = new Wire(D0, PLUS1);
        WIRE6 = new Wire(PREDICTOR1, PLUS1);
        WIRE7 = new Wire(MINUS1, MERGER1);
        WIRE8 = new Wire(PLUS1, MERGER1);
        WIRE9 = new Wire(MERGER1, S1);
        NETWORK = new ArrayList<>();
        NETWORK.add(WIRE1); NETWORK.add(WIRE2); NETWORK.add(WIRE3);
        NETWORK.add(WIRE4); NETWORK.add(WIRE5); NETWORK.add(WIRE6);
        NETWORK.add(WIRE7); NETWORK.add(WIRE8); NETWORK.add(WIRE9);
    }
    
    public Storage processSignal(Storage s0, Storage d0) {
        S0.setInput(s0.getInput());
        S0.setOutput(s0.getOutput());
        D0.setInput(d0.getInput());
        D0.setOutput(d0.getOutput());
        
        for(Wire w: NETWORK) { w.transfer(); }
              
        System.out.print("Inversed samples:\t");
        for(double s: S1.getOutput()) {
            System.out.print(s + " ");
        }
        System.out.println();
        return S1;
    }
    
    public static void main(String[] args) {
        final double[] s0 = {7};
        final double[] d0 = {-8};
        
        InverseDWTNetwork net1 = new InverseDWTNetwork();
        //ForwardDWTNetwork net2 = new ForwardDWTNetwork();
        
        //Storage storage1 = new Storage();
        //storage1.setInput(signal); storage1.setOutput(signal);
        //Storage output = net1.processSignal(storage1);
        
        Storage ss0 = new Storage();
        ss0.setInput(s0);
        ss0.setOutput(s0);
        Storage dd0 = new Storage();
        dd0.setInput(d0);
        dd0.setOutput(d0);
        Storage output2 = net1.processSignal(ss0, dd0);
        double[] output2_signal = output2.getOutput();
        for(int i = 0; i < output2_signal.length; i++) {
            System.out.print(output2_signal[i] + " ");
        }
        
        
        //processSignal(signal2);
        
    }
}
