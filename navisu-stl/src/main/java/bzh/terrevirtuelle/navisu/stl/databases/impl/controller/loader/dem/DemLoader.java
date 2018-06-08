/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.dem;

import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class DemLoader {

    private final Connection connection;
    private final DemDBServices demDBServices;
    private List<Point3D> points;
    private double maxDepth = 0.0;

    public DemLoader(Connection connection, DemDBServices demDBServices) {
        this.connection = connection;
        this.demDBServices = demDBServices;
    }

    public DEM retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        points = demDBServices.retrieveIn(connection, "elevation", latMin, lonMin, latMax, lonMax);
        points.stream().filter((p) -> (p.getElevation() > maxDepth)).forEachOrdered((p) -> {
            maxDepth = p.getElevation();
        });
        DEM tmp = new DEM(points);
        tmp.setMaxElevation(maxDepth);
        return tmp;
    }

}
