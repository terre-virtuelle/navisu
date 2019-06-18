/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml.KMLWriter;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.DepareExportToSTL;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author serge
 * @date Jun 5, 2019
 */
public class DepareExportToSVG {

    protected GeodesyServices geodesyServices;
    protected DisplayServices displayServices;
    private Shapefile shapefile;
    private String filenameRoot;
    protected double latMin;
    protected double lonMin;
    private double scaleLat;
    private double scaleLon;
    private double sideX;
    private double sideY;
    private String resultG = null;
    protected List<Position> positions;
    protected Set<Map.Entry<String, Object>> entries;
    List<Path> pathList = new ArrayList<>();
    double maxMax = Double.MIN_VALUE;
    KMLWriter kmlWriter;
    boolean first = true;
    protected ShapeAttributes normalAttributes;
    protected ShapeAttributes highlightAttributes;
    protected RenderableLayer layer;

    //Laser IMT 450x300;
    public DepareExportToSVG(GeodesyServices geodesyServices,
            DisplayServices displayServices,
            Shapefile shapefile, String filenameRoot,
            double latMin, double lonMin,
            double sideX, double sideY,
            double scaleLat, double scaleLon,
            RenderableLayer layer) {
        this.geodesyServices = geodesyServices;
        this.displayServices = displayServices;
        this.shapefile = shapefile;
        this.filenameRoot = filenameRoot;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.sideX = sideX;
        this.sideY = sideY;
        this.scaleLat = scaleLat;
        this.scaleLon = scaleLon;
        this.layer = layer;
        kmlWriter = new KMLWriter("test.kml");
        makeAttributes();
    }

    public List<SVGPath> export() {
        List<Path> pathWWJ = new ArrayList<>();
        List<SVGPath> paths = new ArrayList<>();
        while (shapefile.hasNext()) {
            try {
                ShapefileRecord record = shapefile.nextRecord();
                List< List<Position>> depares = createDepare(record);

                for (List<Position> l : depares) {
                    List<List<Position>> posfiltered = filter(l);
                    for (List<Position> pf : posfiltered) {
                        Path path = new Path(pf);
                        pathWWJ.add(path);
                        List<Position> scaledPosition = scaling(pf, latMin, lonMin, sideY, scaleLat, scaleLon);
                        String content = createContent(scaledPosition);
                        paths.add(createPath(content));
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(DepareExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        displayServices.displayPaths(pathWWJ, 100.0, layer, Material.RED);
        return paths;

    }

    protected List<List<Position>> createDepare(ShapefileRecord record) {

        List<List<Position>> result = new ArrayList<>();
        SurfacePolygons shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        SurfacePolygon surface = new SurfacePolygon(shape.getBuffer().getPositions());

        List<Iterable<? extends LatLon>> pos = surface.getBoundaries();

        for (Iterable<? extends LatLon> i : pos) {
            List<Position> list = new ArrayList<>();
            for (LatLon l : i) {
                list.add(new Position(l, 0.0));
            }

            Polygon polygon = new Polygon(list);
            polygon.setAttributes(normalAttributes);
            if (first == true) {
                kmlWriter.write(polygon, StandardOpenOption.CREATE);
                first = false;
            } else {
                kmlWriter.write(polygon, StandardOpenOption.APPEND);
            }

            // Affichage
            result.add(list);
        }

        return result;
    }

    private void makeAttributes() {
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.RED);
        normalAttributes.setOutlineOpacity(0.5);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);

    }

    public List<Position> scaling(List<Position> positions, double latMin, double lonMin, double sideY, double latScale, double lonScale) {
        List<Position> resultPos = new ArrayList<>();
        positions.forEach((position) -> {
            double lon = position.getLongitude().getDegrees();
            double lat = position.getLatitude().getDegrees();
            double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
            double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
            latM *= latScale;
            lonM *= lonScale;
            //Zero SVG is on the top
            resultPos.add(new Position(Angle.fromDegrees(lonM), Angle.fromDegrees(sideY - latM), position.getElevation()));
        });
        return resultPos;
    }

    public String createContent(List<Position> scaledPosition) {
        String result = "";

        result = "M"
                + +(int) scaledPosition.get(0).getLatitude().getDegrees() + ","
                + (int) scaledPosition.get(0).getLongitude().getDegrees() + " ";
        result += "L";
        for (int i = 1; i < scaledPosition.size(); i++) {
            result
                    += +(int) scaledPosition.get(i).getLatitude().getDegrees() + ","
                    + (int) scaledPosition.get(i).getLongitude().getDegrees() + " ";
        }
        result += "z";
        // System.out.println(result);
        return result;
    }

    public String createWKT(String content) {

        String result = content;
        //LINESTRING(3 4,10 50,20 25)
        //M149,404 L149,409 150,412 152,413 154,414 158,413 162,409 163,406 162,403 158,400 z
        result = result.replace("L", "");
        result = result.replace("M", "POLYGON((");
        result = result.replace("z", ")");
        result = result.replace(" ", ";");
        result = result.replace(",", " ");
        result = result.replace(";", ",");
        result = result.replace(",)", "))");
        return result;
    }

    public List<List<Position>> filter(List<Position> positions) {
        List<List<Position>> result = new ArrayList<>();
        int k = 0;
        int j = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            result.add(new ArrayList<>());
            if ((positions.get(j).getLatitude().getDegrees() != positions.get(i + 1).getLatitude().getDegrees())
                    && (positions.get(j).getLongitude().getDegrees() != positions.get(i + 1).getLongitude().getDegrees())) {
                result.get(k).add(positions.get(i));
            } else {
                result.get(k).add(positions.get(i + 1));
                k++;
                j = i + 2;
                i += 3;
            }
        }

        List<List<Position>> resultFiltered = new ArrayList<>();
        for (List<Position> l : result) {
            if (l.size() > 3) {
                List<Position> lp = new ArrayList<>();
                lp.addAll(l);
                resultFiltered.add(lp);
            }
        }

        return resultFiltered;
    }

    public SVGPath createPath(String content) {

        SVGPath curve = new SVGPath();
        curve.setContent(content);
        // curve.setStroke(Color.RED);
        curve.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        curve.setStrokeWidth(1);
        curve.setFill(null);
        //  System.out.println(curve.getContent());
        //  System.out.println(curve.getStroke());
        return curve;
    }

}
