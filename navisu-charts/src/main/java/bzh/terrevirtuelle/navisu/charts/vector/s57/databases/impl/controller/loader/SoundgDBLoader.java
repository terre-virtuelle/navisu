/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Sounding;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * @date 28/02/2018
 * @author serge
 */
public class SoundgDBLoader
        extends ResultSetDBLoader {

    protected TopologyServices topologyServices;

    public SoundgDBLoader(TopologyServices topologyServices, Connection connection) {
        super(connection, "SOUNDG");
        this.topologyServices = topologyServices;
    }

    @Override
    public List<Sounding> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Sounding> soundings = new ArrayList<>();
        List<String> geoms = new ArrayList<>();
        String geom;
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        try {
            while (resultSet.next()) {
                geom = resultSet.getString("single_geom");
                if (geom != null) {
                    if (geom.contains("POINT ZM")) {
                        geoms.add(geom);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SoundgDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        List<Point3D> points3D = topologyServices.clipPointsZM(geoms, latMin, lonMin, latMax, lonMax);
        points3D.stream().map((pt) -> new Sounding(pt.getLatitude(), pt.getLongitude(), pt.getElevation())).forEachOrdered((sounding) -> {
            soundings.add(sounding);
        });
        return soundings;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResultSet retrieveResultSetIn(double latMin, double lonMin, double latMax, double lonMax) {

        if (connection != null) {
            try {
                request = "SELECT ST_AsText((ST_Dump(geom)).geom) AS single_geom FROM soundg";
                request += " WHERE ST_Crosses(geom, ST_MakeEnvelope(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", 4326));";
                resultSet = connection
                        .createStatement()
                        .executeQuery(request);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database connection fail");
            alert.show();
        }
        return resultSet;
    }
}
