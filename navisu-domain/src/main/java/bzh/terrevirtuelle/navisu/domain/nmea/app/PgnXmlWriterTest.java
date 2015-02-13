/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.app;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.xml.Writer;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;


/**
 *
 * @author Serge
 */
public class PgnXmlWriterTest {

    public PgnXmlWriterTest() {
        Sentences sentences = new Sentences();
        PGN130306 pgn130306 = new PGN130306("1", "{\"timestamp\":\"2014-02-26-10:24:59.523\",\"prio\":\"2\",\"src\":\"1\",\"dst\":\"255\",\"pgn\":\"130306\",\"description\":\"Wind Data\",\"fields\":{\"SID\":\"0\",\"Wind Speed\":\"0.00\",\"Wind Angle\":\"306.4\",\"Reference\":\"Apparent\"}}", 
	                          "2014-02-26-10:24:59.523", 2, 255,
	                          new Integer("130306"), 
	                          10.3, 306.4, "Apparent",
	                          "Wind Data");
        sentences.add(pgn130306);
        Writer writer = new Writer(sentences);
        writer.write("out.xml");
    }

    public static void main(String[] args) {
        new PgnXmlWriterTest();
    }
}
