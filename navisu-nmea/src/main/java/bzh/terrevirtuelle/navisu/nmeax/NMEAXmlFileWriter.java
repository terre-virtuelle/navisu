/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmeax;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.impl.XmlHandler;
import bzh.terrevirtuelle.navisu.nmea.controller.parser.impl.NMEAFileParser;
import bzh.terrevirtuelle.navisu.nmea.controller.xml.Writer;
import bzh.terrevirtuelle.navisu.nmea.model.Sentences;

/**
 *
 * @author Serge Morvan
 */
public class NMEAXmlFileWriter {

    private Sentences sentences;
    String fileName = "gps.xml";

   private NMEAXmlFileWriter() {
       sentences = new Sentences();
        NMEAFileParser nmeaFileParser = new NMEAFileParser(new XmlHandler(sentences));
        nmeaFileParser.parse("../data/gps.txt");
        write();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new NMEAXmlFileWriter();
    }

    public void write() {
      //  String fileName = "nmea.xml";
        new Writer(sentences).write(fileName);
    }
}
