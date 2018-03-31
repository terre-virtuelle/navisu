/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
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
    protected ShapefileObjectServices shapefileObjectServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public DepareView(ShapefileObjectServices shapefileObjectServices, RenderableLayer layer) {
        super(layer);
        this.shapefileObjectServices = shapefileObjectServices;
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

        List<Path> paths = new ArrayList<>();
        List<Polygon> polygons = new ArrayList<>();
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
                    shape.setValue("drval1", val1);
                    shape.setValue("drval2", val2);
                    setPolygonAttributes(shape, color);
                    //  layer.addRenderable(shape);
                    /*
                    Path p;
                    for (int i = 0; i < shape.getBuffer().size(); i++) {
                        p = new Path(shape.getBuffer().subBuffer(i).getPositions());
                        p.setAltitudeMode(altitudeMode);
                        paths.add(p);
                    }
                     */
                    Polygon p;
                    for (int i = 0; i < shape.getBuffer().size(); i++) {
                        p = new Polygon(shape.getBuffer().subBuffer(i).getPositions());
                        //  System.out.println(" " + shape.getValue("drval1"));
                        //  p.setValue("drval1", shape.getValue("drval1"));
                        // p.setValue("drval2", shape.getValue("drval2"));
                        p.setValue(AVKey.SHORT_DESCRIPTION, ((Double) shape.getValue("drval1")).toString());
                        p.setValue(AVKey.BALLOON_TEXT, ((Double) shape.getValue("drval2")).toString());

                        p.setAltitudeMode(altitudeMode);
                        polygons.add(p);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        wwd.redrawNow();
        /*
        Path[] pathArray = new Path[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            pathArray[i] = paths.get(i);
        }
         */
        Polygon[] array = new Polygon[polygons.size()];
        for (int i = 0; i < polygons.size(); i++) {
            array[i] = polygons.get(i);
        }
        /*
      ogr2ogr -f 'ESRI Shapefile' output.shp output.kml
      ogr2ogr outfileSimplify.shp depare.shp -simplify 0.0001  //maxi
         */
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
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        String path = Proc.getProperty("gdalPath");
        String command = path + "/ogr2ogr -f 'ESRI Shapefile' cmd/output.shp cmd/output.kml \n"
                + path + "/ogr2ogr cmd/outfileSimplify.shp cmd/depare.shp -simplify 0.0001";
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        shapefileObjectServices.openFile("cmd/outfileSimplify.shp");
        System.out.println("  "+shapefileObjectServices.getShapefile());
    }
}
