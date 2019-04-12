/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.objects3D;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class GridBox3D {

    private final double baseAltitude = 0.0;
    private double verticalExaggeration;
    Point3DGeo[][] grid;
    ArrayList<Position> topIsoLatPositions0 = new ArrayList<>();
    ArrayList<Position> baseIsoLatPositions0 = new ArrayList<>();
    ArrayList<Position> topIsoLatPositions1 = new ArrayList<>();
    ArrayList<Position> baseIsoLatPositions1 = new ArrayList<>();
    ArrayList<Position> topIsoLonPositions0 = new ArrayList<>();
    ArrayList<Position> baseIsoLonPositions0 = new ArrayList<>();
    ArrayList<Position> topIsoLonPositions1 = new ArrayList<>();
    ArrayList<Position> baseIsoLonPositions1 = new ArrayList<>();
    private List<Path> sidePaths;
    private List<List<Path>> sidePathsBySide;
    private List<Path> gridPaths;
    private List<Path> basePaths;

    int line;
    int col;

    public GridBox3D(Point3DGeo[][] grid) {
        this(grid, 1.0);
    }

    public GridBox3D(Point3DGeo[][] grid, double verticalExaggeration) {
        this.grid = grid;
        this.verticalExaggeration = verticalExaggeration;

        sidePaths = new ArrayList<>();
        sidePathsBySide = new ArrayList<>();
        basePaths = new ArrayList<>();

        line = grid.length;
        col = grid[0].length;

        // Sides
        // South
        for (int i = 0; i < col; i++) {
            topIsoLatPositions0.add(Position.fromDegrees(grid[0][i].getLatitude(),
                    grid[0][i].getLongitude(),
                    grid[0][i].getElevation() * verticalExaggeration));
            baseIsoLatPositions0.add(Position.fromDegrees(grid[0][i].getLatitude(),
                    grid[0][i].getLongitude(),
                    baseAltitude));

        }
        // North
        for (int i = 0; i < col; i++) {
            topIsoLatPositions1.add(Position.fromDegrees(grid[line - 1][i].getLatitude(),
                    grid[line - 1][i].getLongitude(),
                    grid[line - 1][i].getElevation() * verticalExaggeration));
            baseIsoLatPositions1.add(Position.fromDegrees(grid[line - 1][i].getLatitude(),
                    grid[line - 1][i].getLongitude(),
                    baseAltitude));
        }
        // West
        for (int i = 0; i < line; i++) {
            topIsoLonPositions0.add(Position.fromDegrees(grid[i][0].getLatitude(),
                    grid[i][0].getLongitude(),
                    grid[i][0].getElevation() * verticalExaggeration));
            baseIsoLonPositions0.add(Position.fromDegrees(grid[i][0].getLatitude(),
                    grid[i][0].getLongitude(),
                    baseAltitude));
        }
        // East
        for (int i = 0; i < line; i++) {
            topIsoLonPositions1.add(Position.fromDegrees(grid[i][col - 1].getLatitude(),
                    grid[i][col - 1].getLongitude(),
                    grid[i][col - 1].getElevation() * verticalExaggeration));
            baseIsoLonPositions1.add(Position.fromDegrees(grid[i][col - 1].getLatitude(),
                    grid[i][col - 1].getLongitude(),
                    baseAltitude));
        }

        gridPaths = createPaths(grid);
    }

    public Point3DGeo[][] getGrid() {
        return this.grid;
    }

    public List<Path> getPaths() {
        List<Path> result = new ArrayList<>();
        result.addAll(gridPaths);
        result.addAll(getSidePaths());
        return result;
    }

    private List<Path> createPaths(Point3DGeo[][] latLons) {
        List<Position> positions0;
        List<Position> positions1;
        List<Path> result = new ArrayList<>();

        Path path0;
        Path path1;

        int latLength = latLons.length;
        int lonLength = latLons[0].length;

        for (int i = 0; i < latLength -1; i++) {
            for (int j = 0; j < lonLength - 1; j++) {
                positions0 = new ArrayList<>();
                positions0.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                positions0.add(Position.fromDegrees(latLons[i][j + 1].getLatitude(),
                        latLons[i][j + 1].getLongitude(),
                        latLons[i][j + 1].getElevation() * verticalExaggeration));
                positions0.add(Position.fromDegrees(latLons[i + 1][j + 1].getLatitude(),
                        latLons[i + 1][j + 1].getLongitude(),
                        latLons[i + 1][j + 1].getElevation() * verticalExaggeration));
                positions0.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                path0 = new Path(positions0);
                result.add(path0);

                positions1 = new ArrayList<>();
                positions1.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                positions1.add(Position.fromDegrees(latLons[i + 1][j + 1].getLatitude(),
                        latLons[i + 1][j + 1].getLongitude(),
                        latLons[i + 1][j + 1].getElevation() * verticalExaggeration));
                positions1.add(Position.fromDegrees(latLons[i + 1][j].getLatitude(),
                        latLons[i + 1][j].getLongitude(),
                        latLons[i + 1][j].getElevation() * verticalExaggeration));
                positions1.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                path1 = new Path(positions1);
                result.add(path1);
            }
        }
        System.out.println("GridBox3D createPaths " + result.size());
        return result;
    }

    public List<List<Path>> getSidePathsBySide() {
        getSidePaths();
       
        return sidePathsBySide;
    }

    public List<Path> getSidePathsSouth() {
        getSidePaths();
        return sidePathsBySide.get(0);
    }

    public List<Path> getSidePathsEast() {
        getSidePaths();
        return sidePathsBySide.get(1);
    }

    public List<Path> getSidePathsNorth() {
        getSidePaths();
        return sidePathsBySide.get(2);
    }

    public List<Path> getSidePathsWest() {
        getSidePaths();
        return sidePathsBySide.get(3);
    }

    public List<Path> getBasePaths() {
        return basePaths;
    }

    public List<Path> getSidePaths() {
        List<Path> sides0 = new ArrayList<>();
        for (int l = 0; l < col - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLatPositions0.get(l));
            b.add(baseIsoLatPositions0.get(l + 1));
            b.add(topIsoLatPositions0.get(l + 1));
            b.add(baseIsoLatPositions0.get(l));
            Path p0 = new Path(b);
            sidePaths.add(p0);
            sides0.add(p0);

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions0.get(l));
            t.add(topIsoLatPositions0.get(l + 1));
            t.add(topIsoLatPositions0.get(l));
            t.add(baseIsoLatPositions0.get(l));
            Path t0 = new Path(t);
            sidePaths.add(t0);
            sides0.add(t0);
        }
        sidePathsBySide.add(sides0);

        List<Path> sides1 = new ArrayList<>();
        for (int l = 0; l < line - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLonPositions1.get(l));
            b.add(baseIsoLonPositions1.get(l + 1));
            b.add(topIsoLonPositions1.get(l + 1));
            b.add(baseIsoLonPositions1.get(l));

            Path p0 = new Path(b);
            sidePaths.add(p0);
            sides1.add(p0);

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions1.get(l));
            t.add(topIsoLonPositions1.get(l + 1));
            t.add(topIsoLonPositions1.get(l));
            t.add(baseIsoLonPositions1.get(l));
            Path t0 = new Path(t);
            sidePaths.add(t0);
            sides1.add(t0);
        }
        sidePathsBySide.add(sides1);

        List<Path> sides2 = new ArrayList<>();
        for (int l = 0; l < col - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLatPositions1.get(l));
            b.add(topIsoLatPositions1.get(l + 1));
            b.add(baseIsoLatPositions1.get(l + 1));
            b.add(baseIsoLatPositions1.get(l));
            Path p0 = new Path(b);
            sidePaths.add(p0);
            sides2.add(p0);

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions1.get(l));
            t.add(topIsoLatPositions1.get(l));
            t.add(topIsoLatPositions1.get(l + 1));
            t.add(baseIsoLatPositions1.get(l));
            Path t0 = new Path(t);
            sidePaths.add(t0);
            sides2.add(t0);
        }
        sidePathsBySide.add(sides2);

        List<Path> sides3 = new ArrayList<>();
        for (int l = 0; l < line - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLonPositions0.get(l));
            b.add(topIsoLonPositions0.get(l + 1));
            b.add(baseIsoLonPositions0.get(l + 1));
            b.add(baseIsoLonPositions0.get(l));
            Path p0 = new Path(b);
            sidePaths.add(p0);
            sides3.add(p0);

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions0.get(l));
            t.add(topIsoLonPositions0.get(l));
            t.add(topIsoLonPositions0.get(l + 1));
            t.add(baseIsoLonPositions0.get(l));
            Path t0 = new Path(t);
            sidePaths.add(t0);
            sides3.add(t0);
        }
        sidePathsBySide.add(sides3);

        List<Position> b = new ArrayList<>();
        b.add(baseIsoLatPositions0.get(0));
        b.add(baseIsoLatPositions1.get(col - 1));
        b.add(baseIsoLatPositions0.get(col - 1));
        b.add(baseIsoLatPositions0.get(0));
        Path p4 = (new Path(b));
        sidePaths.add(p4);
        basePaths.add(p4);

        List<Position> t = new ArrayList<>();
        t.add(baseIsoLatPositions0.get(0));
        t.add(baseIsoLatPositions1.get(0));
        t.add(baseIsoLatPositions1.get(col - 1));
        t.add(baseIsoLatPositions0.get(0));
        Path p5 = (new Path(t));
        sidePaths.add(p5);
        basePaths.add(p5);
        System.out.println("sidePaths : "+sidePaths.size());
        return sidePaths;
    }

    public double getVerticalExaggeration() {
        return verticalExaggeration;
    }

    public GridBox3D[] split(int n) {
        GridBox3D[] result = new GridBox3D[n*n];
        
        int k = 0;
        int l0 = line / (n - 1);
        int l1 = line / (n - 1) + line % n;
        int c0 = col / (n - 1);
        int c1 = col / (n - 1) + col % n;
        System.out.println(line +" "+col+" "+l0+" "+c0+" "+l1+" "+c1);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                Point3DGeo[][] gTab = new Point3DGeo[l0][c0];
                for (int u = 0; u < l0; u++) {
                    for (int v = 0; v < c0; v++) {
                        gTab[u][v] = grid[u + i][v + j];
                    }
                }
                result[k++] = new GridBox3D(gTab, verticalExaggeration);
            }
        }
        Point3DGeo[][] gTab = new Point3DGeo[l1][c1];
        for (int u = 0; u < l1; u++) {
            for (int v = 0; v < c1; v++) {
                gTab[u][v] = grid[u + n - 1][v + n - 1];
            }
        }
        result[k] = new GridBox3D(gTab, verticalExaggeration);
        
        return result;
    }
}
