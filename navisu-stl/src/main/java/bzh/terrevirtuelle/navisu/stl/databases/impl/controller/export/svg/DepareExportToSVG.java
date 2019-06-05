/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;

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

    public DepareExportToSVG(Shapefile shapefile) {
        this.shapefile = shapefile;
    }

    public Shape export(String filename, double scaleLat, double scaleLon, double sideY) {
        this.filename = filename;
        this.scaleLat = scaleLat;
        this.scaleLon = scaleLon;
        this.sideY = sideY;
        
        SVGPath result = new SVGPath();
        result.setStrokeLineJoin(StrokeLineJoin.ROUND);
        result.setStrokeWidth(4);
        result.setStroke(javafx.scene.paint.Color.RED);
        result.setFill(null);
        result.setOpacity(1.0);
        
        result.setContent("M0,0 L800,0 800,450 0,450 0,0 z");
        
        return result;
    }

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
