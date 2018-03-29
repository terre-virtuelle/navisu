/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.kml.KMLDocumentBuilder;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;
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
public class DepareView
        extends PolygonView {

    protected double val1;
    protected double val2;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public DepareView(RenderableLayer layer) {
        super(layer);
    }

    public void display(Shapefile shp) {
        ShapeAttributes capAttrs = new BasicShapeAttributes();
        capAttrs.setDrawOutline(true);
        capAttrs.setDrawInterior(true);
        capAttrs.setOutlineMaterial(Material.BLUE);
        capAttrs.setInteriorMaterial(Material.CYAN);
        capAttrs.setEnableLighting(true);

        ShapeAttributes sideAttrs = new BasicShapeAttributes();
        sideAttrs.setOutlineWidth(3);
        sideAttrs.setDrawOutline(true);
        sideAttrs.setDrawInterior(true);
        sideAttrs.setOutlineMaterial(Material.BLUE);
        sideAttrs.setInteriorMaterial(Material.BLUE);
        sideAttrs.setEnableLighting(true);
        int altitudeMode = WorldWind.CONSTANT;

        //  List<Polygon> polygons = new ArrayList<>();
        List<Path> paths = new ArrayList<>();
        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equalsIgnoreCase("drval1")) {
                                val1 = (Double) e.getValue();
                            }
                            if (e.getKey().equalsIgnoreCase("drval2")) {
                                val2 = (Double) e.getValue();
                            }
                            color = defineColor(val1, val2);
                        });
                    }
                    if (!Shapefile.isPolygonType(record.getShapeType())) {
                        continue;
                    }

                    createPolygon(record);
                    setPolygonAttributes(shape, color);
                    layer.addRenderable(shape);
                   // Polygon p;
                    Path p;
                    for (int i = 0; i < shape.getBuffer().size(); i++) {
                        p = new Path(shape.getBuffer().subBuffer(i).getPositions());
                        p.setAltitudeMode(altitudeMode);
                        // polygons.add(p);
                        paths.add(p);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        wwd.redrawNow();
        /*
        System.out.println("polygons : " + polygons.size());
        Polygon[] polygonArray = new Polygon[polygons.size()];
        for(int i = 0; i < polygons.size(); i++){
            polygonArray[i]=polygons.get(i);
        }

        try {
            Writer stringWriter = new StringWriter();
            // Create a document builder that will write KML to the StringWriter
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            System.out.println("kmlBuilder : " + kmlBuilder);
            kmlBuilder.writeObjects(polygonArray);
            kmlBuilder.close();
            // Get the exported document as a string
            String xmlString = stringWriter.toString();
            // Set up a transformer to pretty-print the XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            // Write the pretty-printed document to stdout
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File("output.kml")));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
         */
        Path[] pathArray = new Path[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            pathArray[i] = paths.get(i);
        }

        try {
            Writer stringWriter = new StringWriter();
            // Create a document builder that will write KML to the StringWriter
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            System.out.println("kmlBuilder : " + kmlBuilder);
            kmlBuilder.writeObjects(pathArray);
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File("output.kml")));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
