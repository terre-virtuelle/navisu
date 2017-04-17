/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.app.n2k;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.xml.XmlWriter;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN129025;

/**
 *
 * @author Serge
 */
public class PgnXmlWriterTest {

    public PgnXmlWriterTest() {
        Sentences sentences = new Sentences();
        
        PGN129025 pgn129025 = new PGN129025("{\"timestamp\":\"2011-04-25-06:24:59.777\",\"prio\":2,\"src\":36,\"dst\":255,\"pgn\":129025,\"description\":\"Position Rapid Update\",\"fields\":{\"Latitude\":52.7461333,\"Longitude\": 5.1815616}}",
                "2011-04-25-06:24:59.777",
                2, "36", 255,
                129025, "Position Rapid Update",
                52.7461333, 5.1815616);
        sentences.add(pgn129025);
        
        PGN130306 pgn130306 = new PGN130306("{\"timestamp\":\"2014-02-26-10:24:59.523\",\"prio\":\"2\",\"src\":\"1\",\"dst\":\"255\",\"pgn\":\"130306\",\"description\":\"Wind Data\",\"fields\":{\"SID\":\"0\",\"Wind Speed\":\"0.00\",\"Wind Angle\":\"306.4\",\"Reference\":\"Apparent\"}}",
                "2014-02-26-10:24:59.523",
                2, "1", 255,
                130306, "Wind Data",
                10.3, 306.4, "Apparent");

        sentences.add(pgn130306);
        
        XmlWriter writer = new XmlWriter(sentences);
        writer.write("out.xml");
    }

    public static void main(String[] args) {
        new PgnXmlWriterTest();
    }
}
