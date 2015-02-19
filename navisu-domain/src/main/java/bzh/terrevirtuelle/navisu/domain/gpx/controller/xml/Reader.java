/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.controller.xml;

import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Serge Morvan
 */
public class Reader {

    Class claz;
    Object object;

    public Reader() {

    }

    public Reader(Class claz) {
        this.claz = claz;
    }

    public Object read(String fileName) {
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException ex) {
        }
        JAXBContext jAXBContext;
        Unmarshaller unmarshaller;

        try {
            jAXBContext = JAXBContext.newInstance(claz);
            unmarshaller = jAXBContext.createUnmarshaller();
            object = unmarshaller.unmarshal(inputFile);
        } catch (JAXBException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return object;
    }
}
