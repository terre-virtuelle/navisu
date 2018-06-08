/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.bathymetry.model;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public class DEM {

    private List<Point3D> grid;

    private double maxElevation;

    public DEM() {
        grid = new ArrayList<>();
    }

    public DEM(List<Point3D> grid) {
        this.grid = grid;
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
    public List<Point3D> getGrid() {
        return grid;
    }

    /**
     * Set the value of grid
     *
     * @param grid new value of grid
     */
    public void setGrid(List<Point3D> grid) {
        this.grid = grid;
    }

    public void add(Point3D point) {
        grid.add(point);
    }

    public int size() {
        return grid.size();
    }

    @Override
    public String toString() {
        return "DEM{" + "grid=" + grid + '}';
    }

}
