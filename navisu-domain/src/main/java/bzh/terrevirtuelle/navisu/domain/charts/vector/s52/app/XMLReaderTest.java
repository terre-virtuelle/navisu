/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.app;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model.ChartSymbols;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.controller.xml.Reader;

/**
 *
 * @author Serge
 */
public class XMLReaderTest {

    public XMLReaderTest() {
        ChartSymbols data;
        Reader reader = new Reader(ChartSymbols.class);
        // ChartSymbols data = (ChartSymbols)reader.read("out.xml");

        data = (ChartSymbols) reader.read("chartsymbols.xml");
        System.out.println(data);
    }

    public static void main(String[] args) {
        new XMLReaderTest();
    }
}
