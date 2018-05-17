/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.kml.KMLDocumentBuilder;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
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
public class ShapefileView {

    protected String label;
    protected Color color = Color.CYAN;
    protected ShapefileRecord record;
    protected Set<Map.Entry<String, Object>> entries;
    protected ShapeAttributes highlightAttributes = new BasicShapeAttributes();
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected int altitudeMode = WorldWind.RELATIVE_TO_GROUND;
    

    public ShapefileView() {
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setInteriorMaterial(new Material(new Color(47,77,88)));
        highlightAttributes.setInteriorOpacity(.5);
      //  highlightAttributes.setEnableLighting(true);
    }

    protected Shapefile createShapeFileFromSource(Object source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        Shapefile shp = new Shapefile(source);
        return shp;
    }

    protected void creatKML(Polygon[] array) {
        try {
            Writer stringWriter = new StringWriter();
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            kmlBuilder.writeObjects(array);
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File("cmd/output.kml")));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(ShapefileView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    protected void creatKML(Path[] array) {
        try {
            Writer stringWriter = new StringWriter();
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            kmlBuilder.writeObjects(array);
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File("cmd/output.kml")));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(ShapefileView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
