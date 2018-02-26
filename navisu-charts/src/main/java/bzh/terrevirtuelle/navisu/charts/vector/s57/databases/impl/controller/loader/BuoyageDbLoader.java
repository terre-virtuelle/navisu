/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.S57DBComponentController.S57_REQUEST_MAP;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDbLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.postgis.PGgeometry;

/**
 *
 * @author serge
 */
public class BuoyageDbLoader {

    protected final String BUOYAGE_PATH = "bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo";
    protected Connection connection;
    protected String acronym;
    protected Buoyage buoyage;
    protected String request;
    protected Class claz;
    protected Map<Pair<Double, Double>, String> marsysMap;
    protected double lat;
    protected double lon;
    protected ResultSet resultSet;

    public BuoyageDbLoader(Connection connection, String acronym,
            Map<Pair<Double, Double>, String> marsysMap) {
        this.connection = connection;
        this.acronym = acronym;
        this.marsysMap = marsysMap;

        String className = BUOYAGE.ATT.get(acronym);
        try {
            claz = Class.forName(BUOYAGE_PATH + "." + className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BUOYAGE_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    public List<Buoyage> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
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
                    String tmp = resultSet.getString("objnam");
                    String name = "";
                    if (tmp != null) {
                        name = tmp;
                    }
                    buoyage.setObjectName(name);

                    geom = (PGgeometry) resultSet.getObject("geom");
                    lat = geom.getGeometry().getFirstPoint().getY();
                    lon = geom.getGeometry().getFirstPoint().getX();
                    buoyage.setLatitude(lat);
                    buoyage.setLongitude(lon);

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
    }

}
