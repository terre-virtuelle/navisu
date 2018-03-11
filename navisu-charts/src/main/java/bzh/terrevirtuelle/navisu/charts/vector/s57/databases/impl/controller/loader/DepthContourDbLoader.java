/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthContour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.control.Alert;
import org.postgis.LineString;
import org.postgis.MultiLineString;

/**
 * @date 28/02/2018
 * @author serge
 */
public class DepthContourDbLoader {

    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;

    public DepthContourDbLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;

    }

    @SuppressWarnings("unchecked")
    public List<DepthContour> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<DepthContour> depthContours = new ArrayList<>();
        DepthContour depthContour;
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
                    MultiLineString p = new MultiLineString(s);
                    LineString[] polyTab = p.getLines();
                    for (LineString ls : polyTab) {
                        int geoms = ls.numGeoms();
                        for (int i = 0; i < geoms; i++) {
                            depthContour = new DepthContour();
                            depthContour.setGeom(ls.toString());
                            depthContour.setValueOfDepthContour(resultSet.getString(2));
                            depthContours.add(depthContour);
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
        return depthContours;
    }

}
