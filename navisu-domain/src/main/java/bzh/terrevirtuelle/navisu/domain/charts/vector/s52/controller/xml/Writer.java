/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.controller.xml;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s52.Symbols;
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
    Symbols symbols;
    Class claz;

    public Writer() {

    }

    public Writer(Symbols symbols) {
        this.symbols = symbols;
        this.claz = symbols.getClass();
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
            marshaller.marshal(symbols, outputFile);
        } catch (JAXBException ex) {
        }
    }
}
