/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.DredgedArea;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class DredgedAreaDBLoader
        extends ResultSetDBLoader {

    public DredgedAreaDBLoader(Connection connection) {
        super(connection, "DRGARE");
    }

    @Override
    public List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        objects = new ArrayList<>();
        String geom = "";

        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
       DredgedArea object;
        try {
            while (resultSet.next()) {
                object = new DredgedArea();
                geom = resultSet.getString("geom");
                if (geom != null) {
                    object.setGeom(geom);
                    object.setObjectName(resultSet.getString("objnam"));
                    object.setId(resultSet.getInt("rcid"));
                    object.setDepthRangeValue1(Double.toString(resultSet.getDouble("drval1")));
                    object.setDepthRangeValue2(Double.toString(resultSet.getDouble("drval2")));
                    object.getLabels().put("DRGARE", "Dredged Area");
                    object.getLabels().put("objnam", resultSet.getString("objnam"));
                    object.getLabels().put("inform", resultSet.getString("inform"));
                    object.getLabels().put("ninfom", resultSet.getString("ninfom"));
                    object.getLabels().put("drval1", Double.toString(resultSet.getDouble("drval1")));
                    object.getLabels().put("drval2", Double.toString(resultSet.getDouble("drval2")));
                    objects.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DredgedAreaDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return objects;
    }
}
