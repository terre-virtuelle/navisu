/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.bathy;

import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author serge
 * @date May 11, 2018
 */
public class BathyLoader {

    private final Connection connection;
    private final BathymetryDBServices bathymetryDBServices;
    private List<Point3DGeo> points;
    private double maxDepth = 0.0;

    public BathyLoader(Connection connection, BathymetryDBServices bathymetryDBServices) {
        this.connection = connection;
        this.bathymetryDBServices = bathymetryDBServices;
    }

    public DEM retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        points = bathymetryDBServices.retrieveIn(connection, "bathy", latMin, lonMin, latMax, lonMax);
        for(Point3DGeo p : points){
            
        }
        points.stream().filter((p) -> (p.getElevation() > maxDepth)).forEachOrdered((p) -> {
            maxDepth = p.getElevation();
        });
        
        DEM tmp = new DEM(points);
        tmp.setMaxElevation(maxDepth);
        return tmp;
    }
}
