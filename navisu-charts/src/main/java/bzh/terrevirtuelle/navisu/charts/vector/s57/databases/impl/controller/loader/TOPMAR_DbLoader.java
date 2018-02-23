/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgis.PGgeometry;

/**
 *
 * @author serge
 */
public class TOPMAR_DbLoader {

    protected static final Logger LOGGER = Logger.getLogger(TOPMAR_DbLoader.class.getName());
    protected Map<Pair<Double, Double>, String> topMarks;
    protected Connection connection;

    public TOPMAR_DbLoader(Connection connection, Map<Pair<Double, Double>, String> topMarks) {
        this.topMarks = topMarks;
    }

    public Map<Pair<Double, Double>, String> retrieveIn(double latMin, double lonMin,
            double latMax, double lonMax) {
        List<Point3D> tmp1 = new ArrayList<>();
        PGgeometry geom;
        ResultSet r;
        if (connection != null) {
            try {
                String request = S57_REQUEST_MAP.get("TOPMAR");
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";
                System.out.println("requestTopmar : " + request);
                r = connection.createStatement().executeQuery(request);
                while (r.next()) {
                    geom = (PGgeometry) r.getObject(1);

                    Point3D pt = new Point3D(geom.getGeometry().getFirstPoint().getX(),
                            geom.getGeometry().getFirstPoint().getY(),
                            0.0);
                    tmp1.add(pt);

                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            // alert();
        }
        System.out.println("List<Point3D> : " + tmp1);
        return topMarks;
    }
}
