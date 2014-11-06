/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.controller.xml;

import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
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
    Sentences sentences;
    Class claz;

    public Writer() {

    }

    public Writer(Sentences sentences) {
        this.sentences = sentences;
        this.claz = sentences.getClass();
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
            marshaller.marshal(sentences, outputFile);
        } catch (JAXBException ex) {
        }
    }
}
