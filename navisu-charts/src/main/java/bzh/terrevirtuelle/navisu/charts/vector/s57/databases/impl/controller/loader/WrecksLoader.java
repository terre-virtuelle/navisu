/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.UnderwaterAwashRock;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wrecks;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.geom.Position;
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
public class WrecksLoader
        extends ResultSetDBLoader {

    protected TopologyServices topologyServices;
    protected List<Wrecks> wrecks;

    public WrecksLoader(TopologyServices topologyServices, Connection connection) {
        super(connection, "WRECKS");
        this.topologyServices = topologyServices;
    }

    @Override
    public List<Wrecks> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        wrecks = new ArrayList<>();
        String geom = "";
        
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        Wrecks object;
        try {
            while (resultSet.next()) {
                object = new Wrecks();
                geom = resultSet.getString("geom");
                if (geom != null && !(geom.equals("MULTIPOINT EMPTY") || geom.equals("POINT EMPTY") || geom.equals("MULTIPOLYGON EMPTY"))) {
                    object.setGeom(geom);
                    Position position = null;
                    if (geom.contains("MULTIPOINT")) {
                        position = topologyServices.wktMultiPointToPosition(geom);
                    } else {
                        if (geom.contains("POINT")) {
                            position = topologyServices.wktPointToPosition(geom);
                        }
                    }
                    object.setLatitude(position.getLatitude().getDegrees());
                    object.setLongitude(position.getLongitude().getDegrees());
                    wrecks.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WrecksLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return wrecks;
    }
}
