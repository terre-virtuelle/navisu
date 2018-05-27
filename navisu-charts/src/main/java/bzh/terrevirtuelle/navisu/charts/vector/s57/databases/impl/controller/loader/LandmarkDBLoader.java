/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.geom.LatLon;
import java.sql.Connection;
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
public class LandmarkDBLoader
        extends ResultSetDBLoader {

    TopologyServices topologyServices;
    List<Landmark> landmarks;
    String marsys;

    public LandmarkDBLoader(TopologyServices topologyServices, Connection connection, String marsys) {
        super(connection, "LNDMRK");
        this.topologyServices = topologyServices;
        this.marsys = marsys;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Landmark> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        String geom;
        landmarks = new ArrayList<>();
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        Landmark object;
        try {
            while (resultSet.next()) {
                geom = resultSet.getString("geom");
                if ((geom.contains("MULTIPOINT") || geom.contains("POINT")) && !geom.contains("EMPTY")) {
                    object = new Landmark();
                    object.setGeom(geom);
                    object.setId(Long.parseLong(resultSet.getString("rcid")));

                    LatLon latLon = topologyServices.wktMultiPointToWwjLatLon(geom);
                    object.setLatitude(latLon.getLatitude().getDegrees());
                    object.setLongitude(latLon.getLongitude().getDegrees());

                    object.setMarsys(marsys);

                    String tmp = resultSet.getString("objnam");
                    String name = "";
                    if (tmp != null) {
                        name = tmp;
                    }
                    object.setObjectName(name);

                    tmp = resultSet.getString("functn");
                    String functn = "0";
                    if (tmp != null) {
                        functn = tmp;
                    }
                    object.setFunction(functn);

                    tmp = resultSet.getString("colour");
                    String col = "0";
                    if (tmp != null) {
                        col = tmp;
                    }
                    object.setColour(col);

                    tmp = resultSet.getString("colpat");
                    String colPat = "0";
                    if (tmp != null) {
                        colPat = tmp;
                    }
                    object.setColourPattern(colPat);

                    String catlmk = resultSet.getString("catlmk");
                    if (catlmk == null) {
                        catlmk = "0";
                    }
                    object.setCategoryOfLandMark(catlmk);

                    String status = resultSet.getString("status");
                    if (status == null) {
                        status = "0";
                    }
                    object.setStatus(status);

                    String convis = resultSet.getString("convis");
                    if (convis == null) {
                        convis = "0";
                    }
                    object.setConspicuousVisually(convis);

                    object.getLabels().put("LNDMRK", "Landmark");
                    landmarks.add(object);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LandmarkDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return landmarks;
    }

}
