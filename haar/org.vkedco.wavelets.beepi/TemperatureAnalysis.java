package org.vkedco.wavelets.beepi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vladimir kulyukin
 */
public class TemperatureAnalysis {
    
    static final String TEMP_DIR = "C:/Users/vladimir/research/BeePI/recorded_data/";
    public static ArrayList<TemperatureLogRecord> processTemperatureLogFile(String file_path) throws FileNotFoundException, IOException {
        ArrayList<TemperatureLogRecord> tempLogRecs = new ArrayList<TemperatureLogRecord>();
        try(BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempLogRecs.add(TemperatureLogRecord.toTemperatureLogRecord(line));
            }
        }
        return tempLogRecs;
    }
     
    public static void main(String[] args) {
        try {
            ArrayList<TemperatureLogRecord> tempLogRecs = processTemperatureLogFile(TEMP_DIR + "garland_temps_march_2015.txt");
            System.out.println("num records = " + tempLogRecs.size());
        } catch (IOException ex) {
            Logger.getLogger(TemperatureAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
