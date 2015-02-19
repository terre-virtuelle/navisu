/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.controller.xml;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model.ChartSymbols;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model.Patterns;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 *
 * @author Serge Morvan
 */
public class Writer {

    FileOutputStream outputFile = null;
    ChartSymbols data;
    Class claz;

    public Writer() {

    }

    public Writer(ChartSymbols data) {
        this.data = data;
        this.claz = data.getClass();
    }

    public void write(String fileName) {
        try {
            outputFile = new FileOutputStream(new File(fileName));
        } catch (FileNotFoundException ex) {
        }
        JAXBContext jAXBContext;
        Marshaller marshaller;

        try {
            jAXBContext = JAXBContext.newInstance(claz);
            marshaller = jAXBContext.createMarshaller();
            marshaller.marshal(data, outputFile);
        } catch (JAXBException ex) {
        }
    }
}
