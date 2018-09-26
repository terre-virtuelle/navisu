/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml;

import bzh.terrevirtuelle.navisu.visualization.view.impl.DisplayImpl;
import gov.nasa.worldwind.render.AbstractShape;
import gov.nasa.worldwindx.examples.kml.KMLDocumentBuilder;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author serge
 */
public class KMLWriter {
    
    protected String filename;
    
    public KMLWriter(String filename) {
        this.filename = filename;
    }
    
    public void write(AbstractShape[] array, StandardOpenOption option) {
        if (option == StandardOpenOption.CREATE) {
            try {
                Writer stringWriter = new StringWriter();
                KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
                kmlBuilder.writeObjects(array);
                kmlBuilder.close();
                String xmlString = stringWriter.toString();
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File(filename)));
            } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
                Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            if (option == StandardOpenOption.APPEND) {
                try {
                    String result = "";
                    Writer stringWriter = new StringWriter();
                    KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
                    kmlBuilder.writeObjects(array);
                    kmlBuilder.close();
                    String xmlString = stringWriter.toString();;
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                    StringWriter stringWriter1 = new StringWriter();
                    stringWriter1.write(result);
                    transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(stringWriter1));
                    String[] outputTab = stringWriter1.toString().split("\n");
                    result = "";
                    for (int i = 2; i < outputTab.length - 2; i++) {
                        result += outputTab[i] + "\n";
                    }
                    String content = new String(Files.readAllBytes(Paths.get(filename)));
                    String[] contentTab = content.split("\n");
                    String result1 = "";
                    for (int i = 0; i < contentTab.length - 2; i++) {
                        result1 += contentTab[i] + "\n";
                    }
                    result1 += result;
                    result1 += " </Document>\n"
                            + "</kml>\n";
                    Path path = Paths.get(filename);
                    byte[] strToBytes = result1.getBytes();
                    Files.write(path, strToBytes, StandardOpenOption.CREATE);
                } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
                    Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
            
        }
    }
}
