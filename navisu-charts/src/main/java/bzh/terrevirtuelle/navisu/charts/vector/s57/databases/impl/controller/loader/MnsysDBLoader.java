/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.ResultSetDBLoader.S57_REQUEST_MAP;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.postgis.PGgeometry;

/**
 *
 * @author serge
 */
public class MnsysDBLoader {

    protected static final Logger LOGGER = Logger.getLogger(MnsysDBLoader.class.getName());
    protected Connection connection;

    public MnsysDBLoader(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unchecked")
    public String retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        ResultSet r;
        String request;
        String marsys = "";
        boolean first = true;
        if (connection != null) {
            try {
                while (true) {
                    request = S57_REQUEST_MAP.get("M_NSYS");
                    request += "(" + lonMin + ", " + latMin + ", "
                            + lonMax + ", " + latMax + ", "
                            + "4326);";
                    r = connection.createStatement().executeQuery(request);
                    while (r.next()) {
                        marsys = r.getString(2);
                        if (marsys.equals("1") || marsys.equals("2") || marsys.equals("9") || marsys.equals("10")) {
                            break;
                        }
                    }
                   
                    if (latMin <= 0) {
                        latMin -= 0.01;
                    } else {
                        latMin += 0.01;
                    }
                    if (lonMin <= 0) {
                        lonMin -= 0.01;
                    } else {
                        lonMin += 0.01;
                    }
                    if (latMax <= 0) {
                        latMax -= 0.01;
                    } else {
                        latMax += 0.01;
                    }
                    if (lonMax <= 0) {
                        lonMax -= 0.01;
                    } else {
                        lonMax += 0.01;
                    }
                    if (marsys.equals("1") || marsys.equals("2") || marsys.equals("9") || marsys.equals("10")) {
                        break;
                    }
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
        return marsys;
    }
}
