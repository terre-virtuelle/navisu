/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
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
public class MnsysDbLoader {
    protected static final Logger LOGGER = Logger.getLogger(MnsysDbLoader.class.getName());
    protected Map<Pair<Double, Double>, String> marsysMap;
    protected Connection connection;

    public MnsysDbLoader(Connection connection) {
        this.connection = connection;
        marsysMap=new HashMap<>();
    }
    @SuppressWarnings("unchecked")
    public Map<Pair<Double, Double>, String> retrieveIn(double latMin, double lonMin,
            double latMax, double lonMax) {
        PGgeometry geom;
        ResultSet r;
        if (connection != null) {
            try {
                String request = S57_REQUEST_MAP.get("M_NSYS");
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";
                r = connection.createStatement().executeQuery(request);
                while (r.next()) {
                    String marsys = r.getString(2);
                    if (marsys == null || marsys.equals("9") || marsys.equals("10")) {
                        marsys = "0";
                    }
                    geom = (PGgeometry) r.getObject(1);
                    marsysMap.put(new Pair(geom.getGeometry().getFirstPoint().getY(),
                            geom.getGeometry().getFirstPoint().getX()), marsys);
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
        return marsysMap;
    }
}
