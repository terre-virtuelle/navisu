/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.NavigationLine;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class NavigationLineDBLoader
        extends ResultSetDBLoader {

    public NavigationLineDBLoader(TopologyServices topologyServices,
            Connection connection) {
        super(topologyServices, connection, "NavigationLine");
    }

    @Override
    public List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        objects = new ArrayList<>();
        String geom = "";
        String orient="";
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        NavigationLine object;
        try {
            while (resultSet.next()) {
                object = new NavigationLine();
                geom = resultSet.getString(1);
                orient=resultSet.getString(2);
                if (geom != null && geom.contains("MULTILINESTRING")) {
                    geom = topologyServices.clipWKTMultiLineString(geom, latMin, lonMin, latMax, lonMax);
                    object.setGeom(geom);
                    object.setOrientation(geom);
                    double o = Double.parseDouble(orient);
                    o=(int)o;
                    object.getLabels().put("ORIENT", Double.toString(o)+"°");
                    objects.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NavigationLineDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return objects;
    }
}
