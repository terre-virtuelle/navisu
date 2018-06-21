/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.objects3D;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class GridBox3D {

    private Point3D[][] gridElevation;

    private Point3D[][] gridBase;

    private Point3D[][] sideLat0;

    private Point3D[][] sideLat1;

    private Point3D[][] sideLon0;

    private Point3D[][] sideLon1;

    private double baseAltitude;

    private double verticalExaggeration;

    private Path latPath0;
    private Path latPath1;
    private Path lonPath0;
    private Path lonPath1;

    public GridBox3D(Point3D[][] grid) {
        this(grid, 0.0, 1.0);
    }

    public GridBox3D(Point3D[][] grid, double baseAltitude) {
        this(grid, baseAltitude, 1.0);
    }

    public GridBox3D(Point3D[][] grid, double baseAltitude, double verticalExaggeration) {
        this.baseAltitude = baseAltitude;
        this.verticalExaggeration = verticalExaggeration;

        gridElevation = new Point3D[grid[0].length][grid[1].length];
        gridBase = new Point3D[grid[0].length][grid[1].length];
        sideLat0 = new Point3D[2][grid[0].length];
        sideLat1 = new Point3D[2][grid[0].length];
        sideLon0 = new Point3D[grid[1].length][2];
        sideLon1 = new Point3D[grid[1].length][2];
       
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[1].length; j++) {
                gridElevation[i][j] = new Point3D(grid[i][j].getLatitude(), grid[i][j].getLongitude(), grid[i][j].getElevation());
                gridBase[i][j] = new Point3D(grid[i][j].getLatitude(), grid[i][j].getLongitude(), baseAltitude);
            }
        }

        ArrayList<Position> pathIsoLatPositions;
        List<Path> latPaths = new ArrayList<>();

        Path path = null;
        for (Point3D[] p : grid) {
            pathIsoLatPositions = new ArrayList<>();
            for (Point3D pp : p) {
                pathIsoLatPositions.add(Position.fromDegrees(pp.getLatitude(),
                        pp.getLongitude(),
                        pp.getElevation() * verticalExaggeration));
                path = new Path(pathIsoLatPositions);

            }
            latPaths.add(path);
        }
        latPath0 = latPaths.get(0);
        int k = 0;
        for (Position p : latPath0.getPositions()) {
            sideLat0[0][k] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), baseAltitude);
            sideLat0[1][k] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation() * verticalExaggeration);
            k++;
        }
        latPath1 = latPaths.get(latPaths.size() - 1);
        k = 0;
        for (Position p : latPath1.getPositions()) {
            sideLat1[0][k] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), baseAltitude);
            sideLat1[1][k] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation() * verticalExaggeration);
            k++;
        }

        List<Path> lonPaths = new ArrayList<>();
        ArrayList<Position> pathIsoLonPositions;
        int i = 0;
        for (Point3D p : grid[0]) {
            pathIsoLonPositions = new ArrayList<>();
            for (Point3D[] row : grid) {
                pathIsoLonPositions.add(Position.fromDegrees(row[i].getLatitude(),
                        row[i].getLongitude(),
                        row[i].getElevation() * verticalExaggeration));
                path = new Path(pathIsoLonPositions);
            }
            i++;
            lonPaths.add(path);
        }
        
        lonPath0 = lonPaths.get(0);
         k = 0;
        for (Position p : lonPath0.getPositions()) {
            sideLon0[k][0] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), baseAltitude);
            sideLon0[k][1] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation() * verticalExaggeration);
            k++;
        }
        lonPath1 = lonPaths.get(lonPaths.size() - 1);
         k = 0;
        for (Position p : lonPath1.getPositions()) {
            sideLon1[k][0] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), baseAltitude);
            sideLon1[k][1] = new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation() * verticalExaggeration);
            k++;
        }
    }

    /**
     * Get the value of baseAltitude
     *
     * @return the value of baseAltitude
     */
    public double getBaseAltitude() {
        return baseAltitude;
    }

    /**
     * Set the value of baseAltitude
     *
     * @param baseAltitude new value of baseAltitude
     */
    public void setBaseAltitude(double baseAltitude) {
        this.baseAltitude = baseAltitude;
    }

    public int getLatCount() {
        return gridElevation[0].length;
    }

    public int getLonCount() {
        return gridElevation[1].length;
    }

    /**
     * Get the value of sideLon1
     *
     * @return the value of sideLon1
     */
    public Point3D[][] getSideLon1() {
        return sideLon1;
    }

    /**
     * Set the value of sideLon1
     *
     * @param sideLon1 new value of sideLon1
     */
    public void setSideLon1(Point3D[][] sideLon1) {
        this.sideLon1 = sideLon1;
    }

    /**
     * Get the value of sideLon0
     *
     * @return the value of sideLon0
     */
    public Point3D[][] getSideLon0() {
        return sideLon0;
    }

    /**
     * Set the value of sideLon0
     *
     * @param sideLon0 new value of sideLon0
     */
    public void setSideLon0(Point3D[][] sideLon0) {
        this.sideLon0 = sideLon0;
    }

    /**
     * Get the value of sideLat1
     *
     * @return the value of sideLat1
     */
    public Point3D[][] getSideLat1() {
        return sideLat1;
    }

    /**
     * Set the value of sideLat1
     *
     * @param sideLat1 new value of sideLat1
     */
    public void setSideLat1(Point3D[][] sideLat1) {
        this.sideLat1 = sideLat1;
    }

    /**
     * Get the value of sideLat0
     *
     * @return the value of sideLat0
     */
    public Point3D[][] getSideLat0() {
        return sideLat0;
    }

    /**
     * Set the value of sideLat0
     *
     * @param sideLat0 new value of sideLat0
     */
    public void setSideLat0(Point3D[][] sideLat0) {
        this.sideLat0 = sideLat0;
    }

    /**
     * Get the value of base
     *
     * @return the value of base
     */
    public Point3D[][] getGridBase() {
        return gridBase;
    }

    /**
     * Set the value of base
     *
     * @param base new value of base
     */
    public void setGridBase(Point3D[][] base) {
        this.gridBase = base;
    }

    /**
     * Get the value of grid
     *
     * @return the value of grid
     */
    public Point3D[][] getGridElevation() {
        return gridElevation;
    }

    /**
     * Set the value of grid
     *
     * @param grid new value of grid
     */
    public void setGridElevation(Point3D[][] grid) {
        this.gridElevation = grid;
    }

    public double getVerticalExaggeration() {
        return verticalExaggeration;
    }

    public Path getLatPath0() {
        return latPath0;
    }

    public Path getLatPath1() {
        return latPath1;
    }

    public Path getLonPath0() {
        return lonPath0;
    }

    public Path getLonPath1() {
        return lonPath1;
    }

}
