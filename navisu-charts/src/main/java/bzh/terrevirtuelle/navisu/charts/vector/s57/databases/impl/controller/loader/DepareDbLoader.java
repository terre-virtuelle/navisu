/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthArea;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DepthAreaWithHoles;
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
    static int count = 0;

    public DepareDbLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;

    }

    @SuppressWarnings("unchecked")
    public List<DepthAreaWithHoles> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<DepthAreaWithHoles> depthAreas = new ArrayList<>();
        DepthAreaWithHoles depthAreaWithHoles;
        DepthArea depthArea;
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
                      //  System.out.println("p : " + p);
                        int geoms = pp.numGeoms();
                       // System.out.println("geoms : " + geoms);
                        depthAreaWithHoles = new DepthAreaWithHoles();
                        String tmp = pp.getSubGeometry(0).toString();
                        depthAreaWithHoles.setGeom(tmp);
                      //  System.out.println("tmp : " + tmp);
                        depthAreaWithHoles.setDepthRangeValue1(resultSet.getString(2));
                        depthAreaWithHoles.setDepthRangeValue2(resultSet.getString(3));
                        depthAreas.add(depthAreaWithHoles);
                        if (geoms >= 1) {
                            for (int i = 1; i < geoms; i++) {
                                tmp = pp.getSubGeometry(i).toString();
                               // System.out.println("tmp : " + tmp);
                                depthAreaWithHoles.getGeoms().add(tmp);
                            }
                        }
                     //  System.out.println(depthAreas);
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
