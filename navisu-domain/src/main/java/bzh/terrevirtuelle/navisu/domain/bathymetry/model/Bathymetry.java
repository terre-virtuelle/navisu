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
public class Bathymetry {

    private List<Point3D> grid;

    public Bathymetry() {
        grid = new ArrayList<>();
    }

    public Bathymetry(List<Point3D> grid) {
        this.grid = grid;
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
        return "Bathymetry{" + "grid=" + grid + '}';
    }
    
}
