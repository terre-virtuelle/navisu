/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.RestrictedArea;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.RESARE;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.RESTRN;
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
public class RestrictedAreaDBLoader
        extends ResultSetDBLoader {

    public RestrictedAreaDBLoader( Connection connection) {
        super(connection, "RESARE");
    }

    @Override
    public List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        objects = new ArrayList<>();
        String geom = "";
       
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        RestrictedArea object;
        try {
            while (resultSet.next()) {
                object = new RestrictedArea();
                geom = resultSet.getString("geom");
                if (geom != null) {
                    object.setGeom(geom);
                    object.setObjectName(resultSet.getString("catrea"));
                    object.setObjectName(resultSet.getString("objnam"));
                    object.setRestriction(resultSet.getString("restrn"));
                    object.setObjectName(resultSet.getString("inform"));
                    object.setObjectNameInNationalLanguage(resultSet.getString("ninfom"));
                    object.setId(resultSet.getInt("rcid"));
                    object.getLabels().put("RESARE","Restricted Area");
                    object.getLabels().put("catrea",RESARE.ATT.get(resultSet.getString("catrea")));
                    object.getLabels().put("objnam",resultSet.getString("objnam"));
                    object.getLabels().put("restrn",RESTRN.ATT.get(resultSet.getString("restrn")));
                    object.getLabels().put("inform",resultSet.getString("inform"));
                    object.getLabels().put("ninfom",resultSet.getString("ninfom"));
                    objects.add(object);;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestrictedAreaDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return objects;
    }
}
