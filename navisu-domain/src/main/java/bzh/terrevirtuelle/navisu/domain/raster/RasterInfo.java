/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.raster;

/**
 *
 * @author serge
 * @date Apr 4, 2019
 */
public class RasterInfo {

    private String name;

    private int lonSize;

    private int latSize;

    private double latMin;

    private double lonMin;

    private double latMax;

    private double lonMax;

    private String imageDir;

    private String crs;

    private String demColorRelief;

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

    public RasterInfo() {
    }

    public RasterInfo(String name, int lonSize, int latSize,
            double lonMin, double lonMax,
            double latMin, double latMax,
            String imageDir, String crs) {
        this.name = name;
        this.lonSize = lonSize;
        this.latSize = latSize;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latMax = latMax;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
    }

    public RasterInfo(String name, int lonSize, int latSize, 
            double latMin, double lonMin, double lonMax, 
            String imageDir, String crs, String demColorRelief) {
        this.name = name;
        this.lonSize = lonSize;
        this.latSize = latSize;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.lonMax = lonMax;
        this.imageDir = imageDir;
        this.crs = crs;
        this.demColorRelief = demColorRelief;
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
     * Get the value of latSize
     *
     * @return the value of latSize
     */
    public int getLatSize() {
        return latSize;
    }

    /**
     * Set the value of latSize
     *
     * @param latSize new value of latSize
     */
    public void setLatSize(int latSize) {
        this.latSize = latSize;
    }

    /**
     * Get the value of lonSize
     *
     * @return the value of lonSize
     */
    public int getLonSize() {
        return lonSize;
    }

    /**
     * Set the value of lonSize
     *
     * @param lonSize new value of lonSize
     */
    public void setLonSize(int lonSize) {
        this.lonSize = lonSize;
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

}
