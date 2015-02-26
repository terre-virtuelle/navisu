/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.xml;

import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            JAXBContext jAXBContext = null;
            Marshaller marshaller = null;
            jAXBContext = JAXBContext.newInstance(claz);
            marshaller = jAXBContext.createMarshaller();
            marshaller.marshal(sentences, outputFile);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
