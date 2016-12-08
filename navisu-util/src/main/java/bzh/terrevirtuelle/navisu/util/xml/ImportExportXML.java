/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * NaVisu
 *
 * @param <T>
 * @date 26 oct. 2015
 * @author Serge Morvan
 */
public class ImportExportXML<T> {

    public static <T> T exports(T data, File file) throws JAXBException, FileNotFoundException {
        if (file != null) {
            FileOutputStream outputFile;
            outputFile = new FileOutputStream(file);
            JAXBContext jAXBContext;
            Marshaller marshaller;
            jAXBContext = JAXBContext.newInstance(data.getClass());
            marshaller = jAXBContext.createMarshaller();
            marshaller.marshal(data, outputFile);
        }
        return data;
    }

    public static <T> T exports(T data, String filename) throws JAXBException, FileNotFoundException {
        FileOutputStream outputFile;
        outputFile = new FileOutputStream(new File(filename));
        JAXBContext jAXBContext;
        Marshaller marshaller;
        jAXBContext = JAXBContext.newInstance(data.getClass());
        marshaller = jAXBContext.createMarshaller();
        marshaller.marshal(data, outputFile);
        return data;
    }

    public static <T> T exports(T data, StringWriter xmlString) throws JAXBException {
        JAXBContext jAXBContext;
        Marshaller marshaller;
        jAXBContext = JAXBContext.newInstance(data.getClass());
        marshaller = jAXBContext.createMarshaller();
        marshaller.marshal(data, xmlString);
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T imports(T data, File file) throws FileNotFoundException, JAXBException {
        if (file != null) {
            FileInputStream inputFile = new FileInputStream(new File(file.getPath()));
            Unmarshaller unmarshaller;
            JAXBContext jAXBContext;
            jAXBContext = JAXBContext.newInstance(data.getClass());
            unmarshaller = jAXBContext.createUnmarshaller();
            data = (T) unmarshaller.unmarshal(inputFile);
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T imports(T data, String filename) throws FileNotFoundException, JAXBException {
        File file = new File(filename);
        FileInputStream inputFile = new FileInputStream(new File(file.getPath()));
        Unmarshaller unmarshaller;
        JAXBContext jAXBContext;
        jAXBContext = JAXBContext.newInstance(data.getClass());
        unmarshaller = jAXBContext.createUnmarshaller();
        data = (T) unmarshaller.unmarshal(inputFile);
        return data;
    }
@SuppressWarnings("unchecked")
    public static <T> T imports(T data, StringReader xmlString) throws JAXBException {
        Unmarshaller unmarshaller;
        JAXBContext jAXBContext;
        jAXBContext = JAXBContext.newInstance(data.getClass());
        unmarshaller = jAXBContext.createUnmarshaller();
        data = (T) unmarshaller.unmarshal(xmlString);
        return data;
    }
}
