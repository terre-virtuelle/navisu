/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.app;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Symbols;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.controller.xml.Reader;

/**
 *
 * @author Serge
 */
public class XMLReaderTest {

    public XMLReaderTest() {
        Reader reader = new Reader(Symbols.class);
        Symbols symbols = (Symbols)reader.read("out.xml");
        System.out.println(symbols);
    }
    public static void main(String[] args) {
        new XMLReaderTest();
    }
}


