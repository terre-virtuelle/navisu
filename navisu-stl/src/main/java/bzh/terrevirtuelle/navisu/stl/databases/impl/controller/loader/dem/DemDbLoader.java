/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.dem;

import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class DemDbLoader {

    private final Connection connection;
    private final DemDBServices demDBServices;
    private List<Point3DGeo> points;
    private double maxElevation = 0.0;
    private double minElevation = Double.MAX_VALUE;

    public DemDbLoader(Connection connection, DemDBServices demDBServices) {
        this.connection = connection;
        this.demDBServices = demDBServices;
    }

    public DEM retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        points = demDBServices.retrieveIn(connection, "elevation", latMin, lonMin, latMax, lonMax);
       //System.out.println("latMin : " + latMin +" lonMin : " +lonMin+" latMax : " + latMax +"  lonMax : " + lonMax);
        points.stream().filter((p) -> (p.getElevation() > maxElevation)).forEachOrdered((p) -> {
            maxElevation = p.getElevation();
        });
        points.stream().filter((p) -> (p.getElevation() < minElevation)).forEachOrdered((p) -> {
            minElevation = p.getElevation();
        });
        DEM tmp = new DEM(points);
        tmp.setMaxElevation(maxElevation);
        tmp.setMinElevation(minElevation);
        return tmp;
    }

}
