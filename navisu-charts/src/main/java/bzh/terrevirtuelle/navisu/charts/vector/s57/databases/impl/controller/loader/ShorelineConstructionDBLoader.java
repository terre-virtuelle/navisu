/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

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
public class ShorelineConstructionDBLoader
            extends ResultSetDBLoader {

    public ShorelineConstructionDBLoader(Connection connection, String acronym) {
        super(connection, acronym);
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
            Logger.getLogger(ShorelineConstructionDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return polyString;
    }

}
