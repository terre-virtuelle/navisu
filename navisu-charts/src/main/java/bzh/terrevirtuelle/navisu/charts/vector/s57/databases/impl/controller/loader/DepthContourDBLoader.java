/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
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

    protected TopologyServices topologyServices;

    public DepthContourDBLoader(TopologyServices topologyServices,
            Connection connection, String acronym) {
        super(connection, acronym);
        this.topologyServices = topologyServices;
    }

    @SuppressWarnings("unchecked")
    public List<String> retrieveIn(double latMin, double lonMin,
            double latMax, double lonMax) {
        List<String> polyString = new ArrayList<>();

        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        try {
            while (resultSet.next()) {
                polyString.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PontonDBLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        polyString = topologyServices.clipWKTMultiString(polyString, latMin, lonMin, latMax, lonMax);
        return polyString;
    }
}
