/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public class ResultSetDBLoader {

    
    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;

    
    public ResultSetDBLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;
    }

    @SuppressWarnings("unchecked")
    public ResultSet retrieveResultSetIn(double latMin, double lonMin, double latMax, double lonMax) {
       
        if (connection != null) {
            try {
                request = S57_REQUEST_MAP.get(acronym);
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";
                resultSet = connection
                        .createStatement()
                        .executeQuery(request);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database connection fail");
            alert.show();
        }
        return resultSet;
    }

}
