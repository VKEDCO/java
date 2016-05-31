package org.vkedco.wavelets.lifting;

/**
 *
 * @author Vladimir Kulyukin
 */
public class Wire {

    private Node mInputNode;
    private Node mOutputNode;
    
    public Wire(Node input, Node output) {
        mInputNode = input;
        mOutputNode = output;
    }
     
    public void transfer() {
        
        // 1) input = storage; output = split
        // 2) input = split; output = predict
        // 3) input = split; output = minus
        // 4) input = predict; output = minus;
        // 5) input = minus; output = update;
        // 6) input = update; output = plus; 
        // 7) input = split ; output = plus;
        // 8) input = plus; output = storage;
        // 9) input = minus; output = storage;
        
        // Inverse:
        // 1) input = storage; output = update
        // 2) input = storage; output = minus
        // 3) input = update; output = minus
        // 4) input = storage; output = plus
        // 5) input = minus; output = predictor
        // 6) input = predictor; output = plus;
        // 7) input = minus; output = merger
        // 8) input = plus; output = merger
        // 9) input = merger; output = storage;
        
        // 1) input = storage; output = split
        if ( mInputNode instanceof Storage && mOutputNode instanceof Splitter ) {
            //System.out.println("1) input = storage; output = split");
            ((Splitter) mOutputNode).split(mInputNode.getOutput());
            //System.out.print("Evens: "); displayArray(((Splitter) mOutputNode).getEvens());
            //System.out.print("Odds: "); displayArray(((Splitter) mOutputNode).getOdds());
            
        }
        // 2) input = split; output = predict
        else if ( mInputNode instanceof Splitter && mOutputNode instanceof Predictor ) {
            //System.out.println("2) input = split; output = predict");
            ((Predictor) mOutputNode).predict(((Splitter) mInputNode).getEvens());
            //System.out.print("PREDICT: "); displayArray(((Predictor) mOutputNode).getOutput());
        }
        // 3) input = split; output = minus
        else if ( mInputNode instanceof Splitter && mOutputNode instanceof Minus ) {
            //System.out.println("3) input = split; output = minus");
            ((Minus) mOutputNode).setLeftHandInput(((Splitter) mInputNode).getOdds());
            //System.out.print("Minus LEFT: "); displayArray(((Minus) mOutputNode).getLeftHandInput());
            ((Minus) mOutputNode).minus();
        }
        // 4) input = predict; output = minus; 
        else if ( mInputNode instanceof Predictor && mOutputNode instanceof Minus ) {
            //System.out.println("4) input = predict; output = minus;");
            ((Minus) mOutputNode).setRightHandInput(((Predictor) mInputNode).getOutput());
            //System.out.print("Minus LEFT: "); displayArray(((Minus) mOutputNode).getRightHandInput());
            ((Minus) mOutputNode).minus();
        }
        // 5) input = minus; output = update;
        else if ( mInputNode instanceof Minus && mOutputNode instanceof Updater ) {
            //System.out.println("5) input = minus; output = update;");
            ((Updater) mOutputNode).update(((Minus) mInputNode).getOutput());
            //System.out.print("Minus OUTPUT: "); displayArray(((Minus) mInputNode).getOutput());
            //System.out.print("UPDATED: "); displayArray(((Updater) mOutputNode).getOutput());
        }
        // 6) input = update; output = plus; 
        else if ( mInputNode instanceof Updater && mOutputNode instanceof Plus ) {
            //System.out.println("6) input = update; output = plus;");
            ((Plus) mOutputNode).setLeftHandInput(((Updater) mInputNode).getOutput());
            ((Plus) mOutputNode).plus();
        }
        // 7) input = split ; output = plus;
        else if ( mInputNode instanceof Splitter && mOutputNode instanceof Plus ) {
            //System.out.println("7) input = split ; output = plus;");
            ((Plus) mOutputNode).setRightHandInput(((Splitter) mInputNode).getEvens());
            ((Plus) mOutputNode).plus();
        }
        // 8) input = plus; output = storage;
        else if ( mInputNode instanceof Plus && mOutputNode instanceof Storage ) {
            //System.out.println("8) input = plus; output = storage;");
            ((Storage) mOutputNode).setInput(((Plus)  mInputNode).getOutput());
            ((Storage) mOutputNode).setOutput(((Plus) mInputNode).getOutput());
        }
        // 9) input = minus; output = storage;
        else if ( mInputNode instanceof Minus && mOutputNode instanceof Storage ) {
            //System.out.println("9) input = minus; output = storage;");
            ((Storage) mOutputNode).setInput(((Minus)  mInputNode).getOutput());
            ((Storage) mOutputNode).setOutput(((Minus) mInputNode).getOutput());
        }
        
        // inverse:
        // 1) input = storage; output = update
        else if ( mInputNode instanceof Storage && mOutputNode instanceof Updater ) {
            ((Updater) mOutputNode).update(((Storage) mInputNode).getOutput());
        }
        // 2) input = storage; output = minus
        else if ( mInputNode instanceof Storage && mOutputNode instanceof Minus ) {
            ((Minus) mOutputNode).setLeftHandInput(((Storage) mInputNode).getOutput());
            ((Minus) mOutputNode).minus();
        }
        // 3) input = update; output = minus
        else if ( mInputNode instanceof Updater && mOutputNode instanceof Minus ) {
            ((Minus) mOutputNode).setRightHandInput(((Updater) mInputNode).getOutput());
            ((Minus) mOutputNode).minus();
        }
        // 4) input = storage; output = plus
        else if ( mInputNode instanceof Storage && mOutputNode instanceof Plus ) {
           ((Plus) mOutputNode).setLeftHandInput(((Storage) mInputNode).getOutput());
           ((Plus) mOutputNode).plus(); 
        }
        // 5) input = minus; output = predictor
        else if ( mInputNode instanceof Minus && mOutputNode instanceof Predictor ) {
            ((Predictor) mOutputNode).predict(((Minus) mInputNode).getOutput());
        }
        // 6) input = predictor; output = plus
        else if ( mInputNode instanceof Predictor && mOutputNode instanceof Plus ) {
            ((Plus) mOutputNode).setRightHandInput(((Predictor) mInputNode).getOutput());
            ((Plus) mOutputNode).plus(); 
        }
        // 7) input = minus; output = merger
        else if ( mInputNode instanceof Minus && mOutputNode instanceof Merger ) {
            ((Merger) mOutputNode).setLeftHandInput(((Minus) mInputNode).getOutput());
            ((Merger) mOutputNode).merge();
        }
        // 8) input = plus; output = merger
        else if ( mInputNode instanceof Plus && mOutputNode instanceof Merger ) {
            ((Merger) mOutputNode).setRightHandInput(((Plus) mInputNode).getOutput());
            ((Merger) mOutputNode).merge();
        }
        // 9) input = merger; output = storage
        else if ( mInputNode instanceof Merger && mOutputNode instanceof Storage ) {
            ((Storage) mOutputNode).setInput(((Merger) mInputNode).getOutput());
            ((Storage) mOutputNode).setOutput(((Merger) mInputNode).getOutput());
        }
    }
    
    public void displayArray(double[] ary) {
        for(double d: ary) {
            System.out.print(d + " ");
        }
        System.out.println();
    }
    
}
