/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.postgis.PGgeometry;

/**
 *
 * @author serge
 */
public class PontonDbLoader {

    
    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;

    public PontonDbLoader( Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;

    }

    @SuppressWarnings("unchecked")
    public List<String> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<String> lines = new ArrayList<>();
        PGgeometry geom;

        if (connection != null) {
            try {
                request = S57_REQUEST_MAP.get(acronym);
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";

                resultSet = connection
                        .createStatement()
                        .executeQuery(request);

                while (resultSet.next()) {
                    lines.add(resultSet.getString(1));
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database connection fail");
            alert.show();
        }
        return lines;
    }

}
