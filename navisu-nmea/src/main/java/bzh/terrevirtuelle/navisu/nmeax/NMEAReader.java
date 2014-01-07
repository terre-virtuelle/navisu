/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmeax;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.impl.PrintHandler;
import bzh.terrevirtuelle.navisu.nmea.controller.parser.impl.NMEAFileParser;

/**
 *
 * @author Serge Morvan
 */
public final class NMEAReader {

    private NMEAReader() {
        NMEAFileParser nmeaFileParser = new NMEAFileParser(new PrintHandler());
        nmeaFileParser.parse("../data/nmea.txt");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new NMEAReader();
    }
}
