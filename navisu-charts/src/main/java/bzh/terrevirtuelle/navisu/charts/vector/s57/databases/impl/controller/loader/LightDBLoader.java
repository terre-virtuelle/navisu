/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Light;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.geom.LatLon;
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
public class LightDBLoader
        extends ResultSetDBLoader {

    TopologyServices topologyServices;

    public LightDBLoader(TopologyServices topologyServices, Connection connection, String marsys) {
        super(connection, "LIGHTS");
        this.topologyServices = topologyServices;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Light> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        String geom;

        List<Light> lights = new ArrayList<>();
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        Light object;
        try {
            while (resultSet.next()) {
                geom = resultSet.getString("geom");
                if ((geom.contains("MULTIPOINT") || geom.contains("POINT")) && !geom.contains("EMPTY")) {
                    object = new Light();

//"rcid, objnam, catlit, colour, height, litchr, orient, sectr1, sectr2, siggrp, sigper, sigseq ");                
                    LatLon latLon = topologyServices.wktMultiPointToWwjLatLon(geom);
                    double lat = latLon.getLatitude().getDegrees();
                    double lon = latLon.getLongitude().getDegrees();
                    object.setLatitude(lat);
                    object.setLongitude(lon);
                    
                    object.setId(Long.parseLong(resultSet.getString("rcid")));
                    
                    String tmp = resultSet.getString("objnam");
                    String name = "";
                    if (tmp != null) {
                        name = tmp;
                    }
                    object.setObjectName(name);
         
                    
                    object.getLabels().put("LIGHTS", "Lights");
                    lights.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PontoonDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return lights;
    }

}
