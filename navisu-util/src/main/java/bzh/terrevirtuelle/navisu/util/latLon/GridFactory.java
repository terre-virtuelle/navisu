/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.latLon;

import java.util.List;

/**
 *
 * @author Serge
 */
public class GridFactory {

    private List<Double> latList;
    private List<Double> lonList;
    private final double minLat;
    private final double maxLat;
    private final double minLon;
    private final double maxLon;

    public GridFactory(List<Double> latList, List<Double> lonList) {
        this.latList = latList;
        this.lonList = lonList;
        minLat = getMin(latList);
        maxLat = getMax(latList);
        minLon = getMin(lonList);
        maxLon = getMax(lonList);
    }

    public double[] getSector() {
        double[] sector = new double[4];
        sector[0] = minLat;
        sector[1] = maxLat;
        sector[2] = minLon;
        sector[3] = maxLon;
        return sector;
    }

    public final double getMax(List<Double> list) {
        return list.stream().max(Double::compare).get();
    }

    public final double getMin(List<Double> list) {
        return list.stream().min(Double::compare).get();
    }

    /**
     * Get the value of lonList
     *
     * @return the value of lonList
     */
    public List<Double> getLonList() {
        return lonList;
    }

    /**
     * Set the value of lonList
     *
     * @param lonList new value of lonList
     */
    public void setLonList(List<Double> lonList) {
        this.lonList = lonList;
    }

    /**
     * Get the value of latList
     *
     * @return the value of latList
     */
    public List<Double> getLatList() {
        return latList;
    }

    /**
     * Set the value of latList
     *
     * @param latList new value of latList
     */
    public void setLatList(List<Double> latList) {
        this.latList = latList;
    }

    public double getLatRange() {
        return Math.abs(maxLat - minLat);
    }

    public double getLonRange() {
        return Math.abs(maxLon - minLon);
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public double getMaxLon() {
        return maxLon;
    }

    @Override
    public String toString() {
        return "GridFactory{" + "latList=" + latList + ", lonList=" + lonList + ", minLat=" + minLat + ", maxLat=" + maxLat + ", minLon=" + minLon + ", maxLon=" + maxLon + '}';
    }

    public String getDisplaySector() {
        double[] sector = getSector();
        return sector[0] + "_" + sector[1] + "_" + sector[2] + "_" + sector[3];
    }
}
