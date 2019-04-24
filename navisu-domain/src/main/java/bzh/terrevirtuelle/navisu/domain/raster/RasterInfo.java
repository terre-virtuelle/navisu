/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.raster;

import bzh.terrevirtuelle.navisu.domain.util.Pair;

/**
 *
 * @author serge
 * @date Apr 4, 2019
 */
public class RasterInfo {

    private String name;
    private double pixelLonSize;
    private double pixelLatSize;
    private int line;
    private int col;
    private double latMin;
    private double lonMin;
    private double latMax;
    private double lonMax;
    private String imageDir;
    private String crs;
    private String demColorRelief;

    public RasterInfo() {
    }

    public RasterInfo(String name,
            double pixelLatSize, double pixelLonSize,
            double latMin, double lonMin,
            double latMax, double lonMax,
            String imageDir, String crs) {
        this.name = name;
        this.pixelLonSize = pixelLonSize;
        this.pixelLatSize = pixelLatSize;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
    }

    public RasterInfo(String name,
             double latMin, double lonMin,
            double latMax, double lonMax,
            String imageDir, String crs) {
        this.name = name;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
    }

    public RasterInfo(String name, 
            int line, int col, 
            double latMin, double lonMin, 
            double latMax, double lonMax, 
            String imageDir, String crs) {
        this.name = name;
        this.line = line;
        this.col = col;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
    }

    public RasterInfo(String name, 
            double pixelLonSize, double pixelLatSize, 
            int line, int col, double latMin,
            double lonMin, double latMax, double lonMax, 
            String imageDir,
            String crs, 
            String demColorRelief) {
        this.name = name;
        this.pixelLonSize = pixelLonSize;
        this.pixelLatSize = pixelLatSize;
        this.line = line;
        this.col = col;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
        this.demColorRelief = demColorRelief;
    }

    public double getPixelLonSize() {
        return pixelLonSize;
    }

    public void setPixelLonSize(double pixelLonSize) {
        this.pixelLonSize = pixelLonSize;
    }

    public double getPixelLatSize() {
        return pixelLatSize;
    }

    public void setPixelLatSize(double pixelLatSize) {
        this.pixelLatSize = pixelLatSize;
    }


    /**
     * Get the value of demColorRelief
     *
     * @return the value of demColorRelief
     */
    public String getDemColorRelief() {
        return demColorRelief;
    }

    /**
     * Set the value of demColorRelief
     *
     * @param demColorRelief new value of demColorRelief
     */
    public void setDemColorRelief(String demColorRelief) {
        this.demColorRelief = demColorRelief;
    }

    /**
     * Get the value of col
     *
     * @return the value of col
     */
    public int getCol() {
        return col;
    }

    /**
     * Set the value of col
     *
     * @param col new value of col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Get the value of line
     *
     * @return the value of line
     */
    public int getLine() {
        return line;
    }

    /**
     * Set the value of line
     *
     * @param line new value of line
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Get the value of crs
     *
     * @return the value of crs
     */
    public String getCrs() {
        return crs;
    }

    /**
     * Set the value of crs
     *
     * @param crs new value of crs
     */
    public void setCrs(String crs) {
        this.crs = crs;
    }

    /**
     * Get the value of dir
     *
     * @return the value of dir
     */
    public String getImageDir() {
        return imageDir;
    }

    /**
     * Set the value of dir
     *
     * @param dir new value of dir
     */
    public void setImageDir(String dir) {
        this.imageDir = dir;
    }

    /**
     * Get the value of lonMax
     *
     * @return the value of lonMax
     */
    public double getLonMax() {
        return lonMax;
    }

    /**
     * Set the value of lonMax
     *
     * @param lonMax new value of lonMax
     */
    public void setLonMax(double lonMax) {
        this.lonMax = lonMax;
    }

    /**
     * Get the value of latMax
     *
     * @return the value of latMax
     */
    public double getLatMax() {
        return latMax;
    }

    /**
     * Set the value of latMax
     *
     * @param latMax new value of latMax
     */
    public void setLatMax(double latMax) {
        this.latMax = latMax;
    }

    /**
     * Get the value of lonMin
     *
     * @return the value of lonMin
     */
    public double getLonMin() {
        return lonMin;
    }

    /**
     * Set the value of lonMin
     *
     * @param lonMin new value of lonMin
     */
    public void setLonMin(double lonMin) {
        this.lonMin = lonMin;
    }

    /**
     * Get the value of latMin
     *
     * @return the value of latMin
     */
    public double getLatMin() {
        return latMin;
    }

    /**
     * Set the value of latMin
     *
     * @param latMin new value of latMin
     */
    public void setLatMin(double latMin) {
        this.latMin = latMin;
    }

    

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RasterInfo{" + "name=" + name + ", pixelLonSize=" + pixelLonSize + ", pixelLatSize=" + pixelLatSize + ", line=" + line + ", col=" + col + ", latMin=" + latMin + ", lonMin=" + lonMin + ", latMax=" + latMax + ", lonMax=" + lonMax + ", imageDir=" + imageDir + ", crs=" + crs + ", demColorRelief=" + demColorRelief + '}';
    }

    

}
