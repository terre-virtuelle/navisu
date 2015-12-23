/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.app.nmea183;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class NmeaGenerator {

    public NmeaGenerator() {
        genHDG();
    }

    /*
     * @param device
     * @param sentence
     * @param heading
     * @param magneticDeviation local variation
     * @param magneticVariation or Declination : Declinaison in french In French
     * In French : variation = delinaison + deviation
     * $IIHDG,279.01,,,4.00,E*2B
     */
    public void genHDG() {
        List<String> sentences = new ArrayList<>();
        String sentence;
        for (int i = 0; i < 360; i++) {
            sentence = "$IIHDG,";
            sentence += Double.toString((int) (100 + Math.random() * 10));
            sentence += ",5,W,";
            sentence += Double.toString((int) ((4 + Math.random()) * 100) / 100.0) + ",E*";
            sentence += getSum(sentence);
            sentences.add(sentence.trim());
        }
        try {
            Files.write(Paths.get("data/nmea/compass/compassTest.nmea"), sentences);
        } catch (IOException ex) {
            Logger.getLogger(NmeaGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new NmeaGenerator();
    }

    private  String getSum(String in) {
        int checksum = 0;
        if (in.startsWith("$")) {
            in = in.substring(1, in.length());
        }
        int end = in.indexOf('*');
        if (end == -1) {
            end = in.length();
        }
        for (int i = 0; i < end; i++) {
            checksum = checksum ^ in.charAt(i);
        }
        String hex = Integer.toHexString(checksum);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        return hex.toUpperCase();
    }
}
