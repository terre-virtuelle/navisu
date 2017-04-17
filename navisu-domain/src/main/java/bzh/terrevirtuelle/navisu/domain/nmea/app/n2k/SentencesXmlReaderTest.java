/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.nmea.app.n2k;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.xml.XmlReader;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;

/**
 *
 * @author Serge
 */
public class SentencesXmlReaderTest {

    public SentencesXmlReaderTest() {
        XmlReader reader = new XmlReader(Sentences.class);
        Sentences sentences = (Sentences)reader.read("out.xml");
        System.out.println(sentences);
    }
    public static void main(String[] args) {
        new SentencesXmlReaderTest();
    }
}
