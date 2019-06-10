/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.DepareExportToSTL;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 *
 * @author serge
 * @date Jun 5, 2019
 */
public class DepareExportToSVG {

    private Shapefile shapefile;

    private String filename;

    private double scaleLat;

    private double scaleLon;

    private double sideY;
    String result = null;
    protected Set<Map.Entry<String, Object>> entries;
    List<Path> pathList = new ArrayList<>();

    public DepareExportToSVG(Shapefile shapefile) {
        this.shapefile = shapefile;
    }

    public List<? extends Shape> export(String filename, double scaleLat, double scaleLon, double sideY) {
        this.filename = filename;
        this.scaleLat = scaleLat;
        this.scaleLon = scaleLon;
        this.sideY = sideY;
        List<SVGPath> paths = new ArrayList<>();

        /*
        SVGPath result = new SVGPath();
        result.setStrokeLineJoin(StrokeLineJoin.ROUND);
        result.setStrokeWidth(4);
        result.setStroke(javafx.scene.paint.Color.RED);
        result.setFill(null);
        result.setOpacity(1.0);

        result.setContent("M0,0 L800,0 800,450 0,450 0,0 z");
         */
        this.filename = filename;
        while (shapefile.hasNext()) {
            try {
                ShapefileRecord record = shapefile.nextRecord();
                createPolygon(record, scaleLat, scaleLon);
            } catch (Exception ex) {
                Logger.getLogger(DepareExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

        return paths;
    }

    protected List<Path> createPolygon(ShapefileRecord record, double latScale, double lonScale) {

        if (record.getAttributes() != null) {
            entries = record.getAttributes().getEntries();
            entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                if (e.getKey().equalsIgnoreCase("drval1")) {
                    //Case e=-9.0 m ?
                    double h = (Double) e.getValue();
                    if (h < 0.0) {
                        h = 0.0;
                    }
                    for (int i = 0; i < record.getNumberOfParts(); i++) {
                        VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
                        Iterable<? extends Position> pos = buffer.getPositions();
                        List<Position> cwTopList = new ArrayList<>();
                        for (Position p : pos) {
                            cwTopList.add(new Position(p.getLatitude(), p.getLongitude(), 0));
                        }
                        String content = createContent(cwTopList);
                        System.out.println(content);
                    }
                }
            });
        }
        return pathList;
    }

    public String createContent(List<Position> topList) {
        String result = "";
        //  "M0,0 L800,0 800,450 0,450 0,0 z"
        result = "M" + topList.get(0).getLongitude().getDegrees() + "," + topList.get(0).getLatitude().getDegrees() + " ";
        result += "L";
        for (int i = 1; i < topList.size(); i++) {
            result += topList.get(i).getLongitude().getDegrees() + "," + topList.get(i).getLatitude().getDegrees() + " ";
        }
        result += "z";
        return result;
    }
/*
    public Vec3d toVec3d(Position position, double latMin, double lonMin, double latScale, double lonScale) {

        double elvScale = (latScale + lonScale) / 2;

        double lon = position.getLongitude().getDegrees();
        double lat = position.getLatitude().getDegrees();
        double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
        double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
        latM *= latScale;
        lonM *= lonScale;

        double elv = position.getElevation() * elvScale;

        return new Vec3d(lonM, latM, elv);//retour en xyz
    }
    */
    /**
     * Get the value of sideY
     *
     * @return the value of sideY
     */
    public double getSideY() {
        return sideY;
    }

    /**
     * Set the value of sideY
     *
     * @param sideY new value of sideY
     */
    public void setSideY(double sideY) {
        this.sideY = sideY;
    }

    /**
     * Get the value of scaleLon
     *
     * @return the value of scaleLon
     */
    public double getScaleLon() {
        return scaleLon;
    }

    /**
     * Set the value of scaleLon
     *
     * @param scaleLon new value of scaleLon
     */
    public void setScaleLon(double scaleLon) {
        this.scaleLon = scaleLon;
    }

    /**
     * Get the value of scaleLat
     *
     * @return the value of scaleLat
     */
    public double getScaleLat() {
        return scaleLat;
    }

    /**
     * Set the value of scaleLat
     *
     * @param scaleLat new value of scaleLat
     */
    public void setScaleLat(double scaleLat) {
        this.scaleLat = scaleLat;
    }

    /**
     * Get the value of filename
     *
     * @return the value of filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Set the value of filename
     *
     * @param filename new value of filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Get the value of shapefile
     *
     * @return the value of shapefile
     */
    public Shapefile getShapefile() {
        return shapefile;
    }

    /**
     * Set the value of shapefile
     *
     * @param shapefile new value of shapefile
     */
    public void setShapefile(Shapefile shapefile) {
        this.shapefile = shapefile;
    }

}
