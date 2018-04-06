/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWUtil;
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
    protected RenderableLayer layer;
    protected RenderableLayer simpleDeparelayer;
    protected RenderableLayer depare3DLayer;
    protected ShapefileObjectServices shapefileObjectServices;
    protected JTSServices jtsServices;
    protected TopologyServices topologyServices;
    protected DelaunayServices delaunayServices;
    protected DisplayServices displayServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected List<Path> paths = new ArrayList<>();
    protected List<Polygon> polygons = new ArrayList<>();
    protected List<SurfacePolygons> surfacePolygons = new ArrayList<>();

    int altitudeMode = WorldWind.RELATIVE_TO_GROUND;
    protected double latMin;
    protected double lonMin;
    protected double latMax;
    protected double lonMax;
    double simplify;
    double magnify;
    double maxHeight = 0.0;
    boolean createElevation;

    public DepareView(ShapefileObjectServices shapefileObjectServices,
            JTSServices jtsServices,
            TopologyServices topologyServices,
            DelaunayServices delaunayServices,
            DisplayServices displayServices,
            double latMin, double lonMin, double latMax, double lonMax,
            RenderableLayer layer, RenderableLayer simpleDeparelayer, RenderableLayer depare3DLayer,
            double simplify, double magnify, boolean showElevation) {
        this.shapefileObjectServices = shapefileObjectServices;
        this.jtsServices = jtsServices;
        this.topologyServices = topologyServices;
        this.delaunayServices = delaunayServices;
        this.displayServices = displayServices;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.layer = layer;
        this.simpleDeparelayer = simpleDeparelayer;
        this.depare3DLayer = depare3DLayer;
        this.simplify = simplify;
        this.magnify = magnify;
        this.createElevation = showElevation;
    }

    public void display(Shapefile shp) {

        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                createSurfacePolygons(record, layer, false, false);
                if (!Shapefile.isPolygonType(record.getShapeType())) {
                    continue;
                }

                Polygon p;
                for (int i = 0; i < shape.getBuffer().size(); i++) {
                    p = new Polygon(shape.getBuffer().subBuffer(i).getPositions());
                    p.setValue(AVKey.SHORT_DESCRIPTION, ((Double) shape.getValue("drval1")).toString());
                    p.setValue(AVKey.BALLOON_TEXT, ((Double) shape.getValue("drval2")).toString());
                    p.setValue(AVKey.ABOVE_MEAN_SEA_LEVEL, ((Double) shape.getValue("drval2")).toString());
                    p.setAltitudeMode(altitudeMode);
                    polygons.add(p);
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        //Create kml output.kml file
        Polygon[] array = new Polygon[polygons.size()];
        for (int i = 0; i < polygons.size(); i++) {
            array[i] = polygons.get(i);
        }
        creatKML(array);

        //Simplify data and create depare.shp
        String path = Proc.getProperty("gdalPath");
        String command = path + "/ogr2ogr -f 'ESRI Shapefile' cmd/output.shp cmd/output.kml \n"
                + path + "/ogr2ogr cmd/outfileSimplify.shp cmd/depare.shp -simplify " + simplify;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        //Create objectShapefile with data simplified
        //Create extruded polygons with data simplified
        Shapefile simplifiedShape = new Shapefile("cmd/outfileSimplify.shp");
        while (simplifiedShape.hasNext()) {
            try {
                record = simplifiedShape.nextRecord();
                createSurfacePolygons(record, simpleDeparelayer, false, true);
                if (createElevation) {
                    createSurfacePolygons(record, depare3DLayer, true, false);
                }
            } catch (Exception ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        /*
        Point3D[][] grid = delaunayServices.toGrid(latMin, lonMin, latMax, lonMax, 10, 10, 10);
        List<Point3D> lats = new ArrayList<>();
        List<Point3D> lons = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

            }
        }
         */
        // displayServices.displayGrid(lats, lons, Material.MAGENTA, layer);

        // Concave hull for connectivity with socle
        /*
        List<Point3D> point3DList = new ArrayList<>();
        latLonSet.forEach((ll) -> {
            point3DList.add(new Point3D(ll.getLatitude().getDegrees(),
                    ll.getLongitude().getDegrees(), maxHeight * magnify));
        });

        // delaunayServices.getTriangulation(point3DList);
        // System.out.println(delaunay);
        Geometry geom = jtsServices.getConcaveHull(point3DList, 0.001);//0.01 Threshold a préciser
        Polygon polygonBoundary = topologyServices.jtsPolygonToWwjPolygon(geom, maxHeight * magnify);

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.RED);
        normalAttributes.setOutlineOpacity(0.5);
        normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        polygonBoundary.setAttributes(normalAttributes);

        simpleDeparelayer.addRenderable(polygonBoundary);
        Iterable<? extends LatLon> positions = polygonBoundary.getOuterBoundary();
        /*
               List<Position> positions = new ArrayList<>();
        latLonSet.forEach((ll) -> {
            positions.add(new Position(ll.getLatitude(),
                    ll.getLongitude(), maxHeight*magnify));
        });
        
        System.out.println(positions);
         */
 /*
        Polygon polygon = new Polygon(positions);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.RED);
      //  normalAttributes.setInteriorMaterial(Material.RED);
        normalAttributes.setOutlineOpacity(0.5);
        normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        simpleDeparelayer.addRenderable(polygon);
         */
        wwd.redrawNow();

    }

    protected void createSurfacePolygons(ShapefileRecord record, RenderableLayer layer, boolean isHeight, boolean simp) {

        if (record != null) {

            if (record.getAttributes() != null) {
                entries = record.getAttributes().getEntries();
                entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                    if (e.getKey().equalsIgnoreCase("drval1")) {
                        val1 = (Double) e.getValue();
                    }
                    if (e.getKey().equalsIgnoreCase("drval2")) {
                        val2 = (Double) e.getValue();
                        if (val2 > maxHeight) {
                            maxHeight = val2;
                        }
                    }
                    color = defineColor(val1, val2);
                });
            }

            createPolygon(layer, record, isHeight, magnify, maxHeight, simp);
            shape.setValue("drval1", val1);
            shape.setValue("drval2", val2);
            shape.setValue(AVKey.DISPLAY_NAME,
                    "[" + Double.toString(val1) + ", " + Double.toString(val2) + "]");
            setPolygonAttributes(shape, color);
            surfacePolygons.add(shape);
        }
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
            Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
