/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.UnderwaterAwashRock;
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
public class UnderwaterAwashRockDBLoader
        extends ResultSetDBLoader {

    protected TopologyServices topologyServices;
    protected List<UnderwaterAwashRock> underwaterAwashRocks;

    public UnderwaterAwashRockDBLoader(TopologyServices topologyServices, Connection connection) {
        super(connection, "UWTROC");
        this.topologyServices = topologyServices;
    }

    @Override
    public List<UnderwaterAwashRock> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        underwaterAwashRocks = new ArrayList<>();
        String geom = "";
        String valueOfSounding = "";
        String natureOfurface = "";
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        UnderwaterAwashRock object;
        try {
            while (resultSet.next()) {
                object = new UnderwaterAwashRock();
                geom = resultSet.getString("geom");
                if (geom != null && !geom.equals("MULTIPOINT EMPTY")) {
                    object.setGeom(geom);
                    Position position = topologyServices.wktMultiPointToPosition(geom);
                    object.setLatitude(position.getLatitude().getDegrees());
                    object.setLongitude(position.getLongitude().getDegrees());
                    natureOfurface = resultSet.getString("natsur");
                    object.setNatureOfSurface(natureOfurface);
                    valueOfSounding = resultSet.getString("valsou");
                    object.setValueOfSounding(valueOfSounding);
                    underwaterAwashRocks.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnderwaterAwashRockDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return underwaterAwashRocks;
    }
}
