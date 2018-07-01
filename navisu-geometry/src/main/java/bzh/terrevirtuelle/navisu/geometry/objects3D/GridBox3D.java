/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.objects3D;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class GridBox3D {

    private double baseAltitude;

    private double verticalExaggeration;
    ArrayList<Position> topIsoLatPositions0 = new ArrayList<>();
    ArrayList<Position> baseIsoLatPositions0 = new ArrayList<>();
    ArrayList<Position> topIsoLatPositions1 = new ArrayList<>();
    ArrayList<Position> baseIsoLatPositions1 = new ArrayList<>();
    ArrayList<Position> topIsoLonPositions0 = new ArrayList<>();
    ArrayList<Position> baseIsoLonPositions0 = new ArrayList<>();
    ArrayList<Position> topIsoLonPositions1 = new ArrayList<>();
    ArrayList<Position> baseIsoLonPositions1 = new ArrayList<>();
    private List<Polygon> sidePolygons;
    private List<Path> sidePaths;
    int line;
    int col;

    public GridBox3D(Point3D[][] grid) {
        this(grid, 0.0, 1.0);
    }

    public GridBox3D(Point3D[][] grid, double baseAltitude) {
        this(grid, baseAltitude, 1.0);
    }

    public GridBox3D(Point3D[][] grid, double baseAltitude, double verticalExaggeration) {
        this.baseAltitude = baseAltitude;
        this.verticalExaggeration = verticalExaggeration;
        sidePolygons = new ArrayList<>();
        sidePaths = new ArrayList<>();

        line = grid[0].length;
        col = grid[1].length;

        for (int i = 0; i < col; i++) {
            topIsoLatPositions0.add(Position.fromDegrees(grid[0][i].getLatitude(),
                    grid[0][i].getLongitude(),
                    grid[0][i].getElevation() * verticalExaggeration));
            baseIsoLatPositions0.add(Position.fromDegrees(grid[0][i].getLatitude(),
                    grid[0][i].getLongitude(),
                    baseAltitude));
        }

        for (int i = 0; i < col; i++) {
            topIsoLatPositions1.add(Position.fromDegrees(grid[line - 1][i].getLatitude(),
                    grid[line - 1][i].getLongitude(),
                    grid[line - 1][i].getElevation() * verticalExaggeration));
            baseIsoLatPositions1.add(Position.fromDegrees(grid[line - 1][i].getLatitude(),
                    grid[line - 1][i].getLongitude(),
                    baseAltitude));
        }

        for (int i = 0; i < line; i++) {
            topIsoLonPositions0.add(Position.fromDegrees(grid[i][0].getLatitude(),
                    grid[i][0].getLongitude(),
                    grid[i][0].getElevation() * verticalExaggeration));
            baseIsoLonPositions0.add(Position.fromDegrees(grid[i][0].getLatitude(),
                    grid[i][0].getLongitude(),
                    baseAltitude));
        }

        for (int i = 0; i < line; i++) {
            topIsoLonPositions1.add(Position.fromDegrees(grid[i][col - 1].getLatitude(),
                    grid[i][col - 1].getLongitude(),
                    grid[i][col - 1].getElevation() * verticalExaggeration));
            baseIsoLonPositions1.add(Position.fromDegrees(grid[i][col - 1].getLatitude(),
                    grid[i][col - 1].getLongitude(),
                    baseAltitude));
        }
    }

    public List<Polygon> getSidePolygons() {
         //South
        for (int l = 0; l < col - 1; l++) {
            List<Position> b0 = new ArrayList<>();
            b0.add(baseIsoLatPositions0.get(l));
            b0.add(baseIsoLatPositions0.get(l + 1));
            b0.add(topIsoLatPositions0.get(l + 1));
            b0.add(baseIsoLatPositions0.get(l));
            sidePolygons.add(new Polygon(b0));
            
            List<Position> t0 = new ArrayList<>();
            t0.add(baseIsoLatPositions0.get(l));
            t0.add(topIsoLatPositions0.get(l + 1));
            t0.add(topIsoLatPositions0.get(l));
            t0.add(baseIsoLatPositions0.get(l));
            sidePolygons.add(new Polygon(t0));
             
        }
        //East
        for (int l = 0; l < line - 1; l++) {
            List<Position> b1 = new ArrayList<>();
            b1.add(baseIsoLonPositions1.get(l));
            b1.add(baseIsoLonPositions1.get(l + 1));
            b1.add(topIsoLonPositions1.get(l + 1));
            b1.add(baseIsoLonPositions1.get(l));
            sidePolygons.add(new Polygon(b1));
            
            List<Position> t1 = new ArrayList<>();
            t1.add(baseIsoLonPositions1.get(l));
            t1.add(topIsoLonPositions1.get(l + 1));
            t1.add(topIsoLonPositions1.get(l));
            t1.add(baseIsoLonPositions1.get(l));
            sidePolygons.add(new Polygon(t1));
             
        }
        //North
        for (int l = 0; l < col - 1; l++) {
            List<Position> b2 = new ArrayList<>();
            b2.add(baseIsoLatPositions1.get(l));
            b2.add(topIsoLatPositions1.get(l + 1));
            b2.add(baseIsoLatPositions1.get(l + 1));
            b2.add(baseIsoLatPositions1.get(l));
            sidePolygons.add(new Polygon(b2));

            List<Position> t2 = new ArrayList<>();
            t2.add(baseIsoLatPositions1.get(l));
            t2.add(topIsoLatPositions1.get(l));
            t2.add(topIsoLatPositions1.get(l + 1));
            t2.add(baseIsoLatPositions1.get(l));
            sidePolygons.add(new Polygon(t2));
        }
        //West
        for (int l = 0; l < line - 1; l++) {
            List<Position> b3 = new ArrayList<>();
            b3.add(baseIsoLonPositions0.get(l));
            b3.add(topIsoLonPositions0.get(l + 1));
            b3.add(baseIsoLonPositions0.get(l + 1));
            b3.add(baseIsoLonPositions0.get(l));
           sidePolygons.add(new Polygon(b3));
        
            List<Position> t3 = new ArrayList<>();
            t3.add(baseIsoLonPositions0.get(l));
            t3.add(topIsoLonPositions0.get(l));
            t3.add(topIsoLonPositions0.get(l + 1));
            t3.add(baseIsoLonPositions0.get(l));
            sidePolygons.add(new Polygon(t3));
             
        }
        //Base
        List<Position> b4 = new ArrayList<>();
        b4.add(baseIsoLatPositions0.get(0));
        b4.add(baseIsoLatPositions1.get(col - 1));
        b4.add(baseIsoLatPositions0.get(col - 1));
        b4.add(baseIsoLatPositions0.get(0));
        sidePolygons.add(new Polygon(b4));

        List<Position> t4 = new ArrayList<>();
        t4.add(baseIsoLatPositions0.get(0));
        t4.add(baseIsoLatPositions1.get(0));
        t4.add(baseIsoLatPositions1.get(col-1));
        t4.add(baseIsoLatPositions0.get(0));
     //   sidePolygons.add(new Polygon(t4));
        
       /* 
        List<Position> b = new ArrayList<>();
        b.add(baseIsoLatPositions0.get(0));
        b.add(baseIsoLatPositions1.get(0));
        b.add(baseIsoLatPositions1.get(col - 1));
        b.add(baseIsoLatPositions0.get(0));
        sidePolygons.add(new Polygon(b));
*//*
        List<Position> t4 = new ArrayList<>();
        t4.add(baseIsoLatPositions0.get(0));
        t4.add(baseIsoLatPositions1.get(col - 1));
        t4.add(baseIsoLatPositions0.get(col - 1));
        t4.add(baseIsoLatPositions0.get(0));
      //  sidePolygons.add(new Polygon(t4));
    */   
        return sidePolygons;
    }

    public List<Path> getSidePaths() {

        for (int l = 0; l < col - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLatPositions0.get(l));
            b.add(baseIsoLatPositions0.get(l + 1));
            b.add(topIsoLatPositions0.get(l + 1));
            b.add(baseIsoLatPositions0.get(l));
            sidePaths.add(new Path(b));

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions0.get(l));
            t.add(topIsoLatPositions0.get(l + 1));
            t.add(topIsoLatPositions0.get(l));
            t.add(baseIsoLatPositions0.get(l));
            sidePaths.add(new Path(b));

            /*
            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions0.get(l));
            t.add(topIsoLatPositions0.get(l));
            t.add(topIsoLatPositions0.get(l + 1));
            t.add(baseIsoLatPositions0.get(l));
            sidePaths.add(new Path(b));
             */
        }
        for (int l = 0; l < line - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLonPositions1.get(l));
            b.add(baseIsoLonPositions1.get(l + 1));
            b.add(topIsoLonPositions1.get(l + 1));
            b.add(baseIsoLonPositions1.get(l));
            sidePaths.add(new Path(b));

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions1.get(l));
            t.add(topIsoLonPositions1.get(l + 1));
            t.add(topIsoLonPositions1.get(l));
            t.add(baseIsoLonPositions1.get(l));
            sidePaths.add(new Path(b));

            /*
            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions1.get(l));
            t.add(topIsoLonPositions1.get(l));
            t.add(topIsoLonPositions1.get(l + 1));
            t.add(baseIsoLonPositions1.get(l));
            sidePaths.add(new Path(b));
             */
        }
        for (int l = 0; l < col - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLatPositions1.get(l));
            b.add(baseIsoLatPositions1.get(l + 1));
            b.add(topIsoLatPositions1.get(l + 1));
            b.add(baseIsoLatPositions1.get(l));
            sidePaths.add(new Path(b));

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions1.get(l));
            t.add(topIsoLatPositions1.get(l + 1));
            t.add(topIsoLatPositions1.get(l));
            t.add(baseIsoLatPositions1.get(l));
            sidePaths.add(new Path(b));

            /*
            List<Position> t = new ArrayList<>();
            t.add(baseIsoLatPositions1.get(l));
            t.add(topIsoLatPositions1.get(l));
            t.add(topIsoLatPositions1.get(l + 1));
            t.add(baseIsoLatPositions1.get(l));
            sidePaths.add(new Path(b));
             */
        }
        for (int l = 0; l < line - 1; l++) {
            List<Position> b = new ArrayList<>();
            b.add(baseIsoLonPositions0.get(l));
            b.add(baseIsoLonPositions0.get(l + 1));
            b.add(topIsoLonPositions0.get(l + 1));
            b.add(baseIsoLonPositions0.get(l));
            sidePaths.add(new Path(b));

            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions0.get(l));
            t.add(topIsoLonPositions0.get(l + 1));
            t.add(topIsoLonPositions0.get(l));
            t.add(baseIsoLonPositions0.get(l));
            sidePaths.add(new Path(b));

            /*
            List<Position> t = new ArrayList<>();
            t.add(baseIsoLonPositions0.get(l));
            t.add(topIsoLonPositions0.get(l));
            t.add(topIsoLonPositions0.get(l + 1));
            t.add(baseIsoLonPositions0.get(l));
            sidePaths.add(new Path(b));
             */
        }
        List<Position> b = new ArrayList<>();
        b.add(baseIsoLatPositions0.get(0));
        b.add(baseIsoLatPositions0.get(col - 1));
        b.add(baseIsoLatPositions1.get(col - 1));
        b.add(baseIsoLatPositions0.get(0));
        sidePaths.add(new Path(b));
        List<Position> t = new ArrayList<>();
        t.add(baseIsoLatPositions0.get(0));
        t.add(baseIsoLatPositions1.get(col - 1));
        t.add(baseIsoLatPositions1.get(0));
        t.add(baseIsoLatPositions0.get(0));
        sidePaths.add(new Path(b));
        return sidePaths;
    }
}
