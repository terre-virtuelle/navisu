/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.nmea.app.n2k;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.xml.Reader;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;

/**
 *
 * @author Serge
 */
public class SentencesXmlReaderTest {

    public SentencesXmlReaderTest() {
        Reader reader = new Reader(Sentences.class);
        Sentences sentences = (Sentences)reader.read("out.xml");
        System.out.println(sentences);
    }
    public static void main(String[] args) {
        new SentencesXmlReaderTest();
    }
}
