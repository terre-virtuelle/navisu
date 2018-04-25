/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.util.Pair;
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

    protected Map<Pair<Double, Double>, String> marsysMap;

    public LandmarkDBLoader(Connection connection,Map<Pair<Double, Double>, String> marsysMap) {
        super(connection, "LNDMRK");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        PGgeometry geom;
        /*
        List<Buoyage> buoyages = new ArrayList<>();
        PGgeometry geom;

        if (connection != null) {
            try {
                request = S57_REQUEST_MAP.get(acronym);
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";

                resultSet = connection
                        .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)
                        .executeQuery(request);

                while (resultSet.next()) {
                    try {
                        buoyage = (Buoyage) claz.newInstance();
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(BUOYAGE_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                    }
                    

                    

                    tmp = resultSet.getString(3);
                    String shp = "0";
                    if (tmp != null) {
                        shp = tmp;
                    }
                    buoyage.setShape(shp);

                    tmp = resultSet.getString("colour");
                    String col = "0";
                    if (tmp != null) {
                        col = tmp;
                    }
                    buoyage.setColour(col);

                    tmp = resultSet.getString("colpat");
                    String colPat = "0";
                    if (tmp != null) {
                        colPat = tmp;
                    }
                    buoyage.setColourPattern(colPat);

                    buoyage.setId(resultSet.getLong("rcid"));
                    
                    String cat = resultSet.getString(7);
                    if (cat == null) {
                        cat = "0";
                    }
                    buoyage.setCategoryOfMark(cat);

                    String ma = marsysMap.get(new Pair(lat, lon));
                    if (ma == null) {
                        ma = "1";
                    }
                    buoyage.setMarsys(ma);

                    buoyages.add(buoyage);
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
        return buoyages;
         */

        objects = new ArrayList<>();
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        Landmark object;
        try {
            while (resultSet.next()) {
                object = new Landmark();
                geom = (PGgeometry) resultSet.getObject("geom");
                lat = geom.getGeometry().getFirstPoint().getY();
                lon = geom.getGeometry().getFirstPoint().getX();
                object.setLatitude(lat);
                object.setLongitude(lon);
                
                String tmp = resultSet.getString("objnam");
                String name = "";
                if (tmp != null) {
                    name = tmp;
                }
                object.setObjectName(name);

                object.getLabels().put("LNDMRK", "Landmark");
                objects.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PontoonDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return objects;
    }

}
