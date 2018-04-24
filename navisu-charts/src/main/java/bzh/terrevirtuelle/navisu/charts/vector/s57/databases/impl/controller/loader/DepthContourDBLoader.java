/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthContour;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date 28/02/2018
 * @author serge
 */
public class DepthContourDBLoader
        extends ResultSetDBLoader {

    public DepthContourDBLoader(TopologyServices topologyServices,
            Connection connection) {
        super(connection, "DEPCNT");
    }

    @Override
    public List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        objects = new ArrayList<>();
        String geom = "";
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        DepthContour object;
        try {
            while (resultSet.next()) {
                object = new DepthContour();
                geom = resultSet.getString(1);
                if (geom != null) {
                    object.setGeom(geom);
                    object.setValueOfDepthContour(Double.toString(resultSet.getDouble(2)));
                    object.getLabels().put("", Double.toString(resultSet.getDouble(2)) + " m");
                    objects.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepthContourDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return objects;
    }
}
