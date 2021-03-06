/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.ResultSetDBLoader.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.ResultSetDBLoader.SPECIAL_S57_REQUEST_MAP;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.postgis.PGgeometry;

/**
 *
 * @author serge
 * @date 24 fev 2018 NaVisu project
 */
public class TopmarDBLoader {

    protected static final Logger LOGGER = Logger.getLogger(TopmarDBLoader.class.getName());
    protected Map<Pair<Double, Double>, String> topMarksMap;
    protected Connection connection;
    protected String dbName = null;
    protected String SPECIAL_DB_NAME = "BalisageMaritimeDB";

    /**
     *
     * @param connection
     */
    public TopmarDBLoader(Connection connection) {
        this.connection = connection;
        this.topMarksMap = new HashMap<>();
    }

    /**
     * Check sector latMin, lonMin, latMax, lonMax for TopMark on buoyage
     *
     * @param latMin
     * @param lonMin
     * @param latMax
     * @param lonMax
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<Pair<Double, Double>, String> retrieveIn(double latMin, double lonMin,
            double latMax, double lonMax) {
        PGgeometry geom;
        ResultSet r;
        String request;
        if (connection != null) {
            String urlDB = null;
            try {
                urlDB = connection.getMetaData().getURL();
            } catch (SQLException ex) {
                Logger.getLogger(BuoyageDBLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] urlTab = urlDB.split("/");
            dbName = urlTab[urlTab.length - 1];
            try {
               if (dbName.equals("SPECIAL_DB_NAME")) {
                        request = SPECIAL_S57_REQUEST_MAP.get("TOPMAR");
                    } else {
                        request = S57_REQUEST_MAP.get("TOPMAR");
                    }
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";
                r = connection.createStatement().executeQuery(request);
                while (r.next()) {
                    String tm = r.getString(2);
                    if (tm == null) {
                        tm = "0";
                    }
                    geom = (PGgeometry) r.getObject(1);
                    topMarksMap.put(new Pair(geom.getGeometry().getFirstPoint().getY(),
                            geom.getGeometry().getFirstPoint().getX()), tm);
                }
            } catch (SQLException ex) {
                // topMarksMap=null;
                // LOGGER.log(Level.SEVERE, ex.toString(), ex);
                LOGGER.log(Level.INFO, "TOPMARK not define", ex);
            }
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Database connection fail");
                alert.show();
            });
        }
        return topMarksMap;
    }
}
