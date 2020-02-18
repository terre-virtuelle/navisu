/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.bathymetry.model;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public class DEM {

    private List<Point3DGeo> grid;

    private double maxElevation = 0.0;
    private double minElevation = Double.MAX_VALUE;

    public DEM() {
        grid = new ArrayList<>();
    }

    public DEM(List<Point3DGeo> grid) {
        this.grid = grid;
        grid.stream().filter((p) -> (p.getElevation() > maxElevation)).forEachOrdered((p) -> {
            maxElevation = p.getElevation();
        });
        grid.stream().filter((p) -> (p.getElevation() < minElevation)).forEachOrdered((p) -> {
            minElevation = p.getElevation();
        });
        setMaxElevation(maxElevation);
        setMinElevation(minElevation);
    }

    public double getMinElevation() {
        return minElevation;
    }

    public void setMinElevation(double minElevation) {
        this.minElevation = minElevation;
    }

    /**
     * Get the value of maxElevation
     *
     * @return the value of maxElevation
     */
    public double getMaxElevation() {
        return maxElevation;
    }

    /**
     * Set the value of maxElevation
     *
     * @param maxElevation new value of maxElevation
     */
    public void setMaxElevation(double maxElevation) {
        this.maxElevation = maxElevation;
    }

    /**
     * Get the value of grid
     *
     * @return the value of grid
     */
    public List<Point3DGeo> getGrid() {
        return grid;
    }

    /**
     * Set the value of grid
     *
     * @param grid new value of grid
     */
    public void setGrid(List<Point3DGeo> grid) {
        this.grid = grid;
    }

    public void add(Point3DGeo point) {
        grid.add(point);
    }

    public int size() {
        return grid.size();
    }

    @SuppressWarnings("unchecked")
    public Pair<Integer, Integer> getDimensions() {
        List<Point3DGeo> points = getGrid();
        if (points != null) {
            int size = points.size();
            int col = 1;
            int line = 1;
            double lat = points.get(0).getLatitude();

            //Count of line and columns
            int u = 0;
            while (points.get(u).getLatitude() == lat) {
                u++;
                line++;
            }
            line--;
            col = size / line;
            return new Pair(line, col);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "DEM{" + "grid=" + grid + '}';
    }

}
