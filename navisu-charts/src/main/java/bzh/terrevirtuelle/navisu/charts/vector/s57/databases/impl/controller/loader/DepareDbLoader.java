/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthArea;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.control.Alert;
import org.postgis.MultiPolygon;

/**
 * @date 28/02/2018
 * @author serge
 */
public class DepareDbLoader {

    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;

    public DepareDbLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;

    }

    @SuppressWarnings("unchecked")
    public List<DepthArea> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<DepthArea> depthAreas = new ArrayList<>();
        DepthArea depthArea = null;
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
                    String s = resultSet.getString(1);
                    MultiPolygon p = new MultiPolygon(s);
                    org.postgis.Polygon[] polyTab = p.getPolygons();
                    for (org.postgis.Polygon pp : polyTab) {
                        int geoms = pp.numGeoms();
                        for (int i = 0; i < geoms; i++) {
                            depthArea = new DepthArea();
                            String tmp = pp.getSubGeometry(i).toString();
                            depthArea.setGeom(tmp);
                            depthArea.setDepthRangeValue1(resultSet.getString(2));
                            depthArea.setDepthRangeValue2(resultSet.getString(3));
                            depthAreas.add(depthArea);
                        }
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
        return depthAreas;
    }

}
