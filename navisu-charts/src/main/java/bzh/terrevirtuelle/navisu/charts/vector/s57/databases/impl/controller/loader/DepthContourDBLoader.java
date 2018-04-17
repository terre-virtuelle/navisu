/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

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

    List<String> geometry;
    List<DepthContour> objects;

    public DepthContourDBLoader(TopologyServices topologyServices,
            Connection connection, String acronym) {
        super(topologyServices, connection, acronym);
    }

    @Override
    public List<String> retrieveGeometriesIn(double latMin, double lonMin, double latMax, double lonMax) {
        geometry = super.retrieveGeometriesIn(latMin, lonMin, latMax, lonMax);
        if (geometry != null) {
            geometry = topologyServices.clipWKTMultiString(geometry, latMin, lonMin, latMax, lonMax);
        }
        return geometry;
    }

    public List<DepthContour> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        geometry = new ArrayList<>();
        objects = new ArrayList<>();

        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        try {
            while (resultSet.next()) {
                DepthContour depthContour = new DepthContour();
                depthContour.setGeom(resultSet.getString(1));
                depthContour.setValueOfDepthContour(Double.toString(resultSet.getDouble(2)));
                objects.add(depthContour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepthContourDBLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }
}
