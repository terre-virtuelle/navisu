/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public abstract class ResultSetDBLoader {

    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;
    protected List<String> geometry;
    protected List<Geo> objects;

    public ResultSetDBLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;
    }

    @SuppressWarnings("unchecked")
    public List<String> retrieveGeometriesIn(double latMin, double lonMin,
            double latMax, double lonMax) {
        geometry = new ArrayList<>();
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        try {
            while (resultSet.next()) {
                geometry.add(resultSet.getString("geom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        
        return geometry;
    }

    abstract List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax);

    @SuppressWarnings("unchecked")
    public ResultSet retrieveResultSetIn(double latMin, double lonMin, double latMax, double lonMax) {

        if (connection != null) {
            try {
                request = "SELECT ST_AsText(ST_ClipByBox2D(geom, ST_MakeEnvelope";
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", 4326))) AS geom";
                String tmp = S57_REQUEST_MAP.get(acronym);
                if (!tmp.trim().equals("")) {
                    request += ", " + tmp;
                }
                request += " FROM " + acronym.toLowerCase() + " ;";
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
        //System.out.println(request);
        return resultSet;
    }
}
