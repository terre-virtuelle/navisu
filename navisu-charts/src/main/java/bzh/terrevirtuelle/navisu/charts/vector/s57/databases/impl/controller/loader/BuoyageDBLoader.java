/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BuoyageDBLoader
        extends ResultSetDBLoader {

    protected final String BUOYAGE_PATH = "bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo";
    protected Buoyage buoyage;
    protected Class claz;
    

    ;

    public BuoyageDBLoader(Connection connection, String acronym) {
        super(connection, acronym);
        this.connection = connection;
        this.acronym = acronym;
       
        String className = BUOYAGE.ATT.get(acronym);
        try {
            claz = Class.forName(BUOYAGE_PATH + "." + className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuoyageDBLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Buoyage> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Buoyage> buoyages = new ArrayList<>();
        String geom;
        resultSet = retrieveResultSetIn(latMin, lonMin, latMax, lonMax);
        try {
            while (resultSet.next()) {
                try {
                    buoyage = (Buoyage) claz.newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(BUOYAGE_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
                buoyage.setId(Long.parseLong(resultSet.getString("rcid")));

                String tmp = resultSet.getString("objnam");
                String name = "";
                if (tmp != null) {
                    name = tmp;
                }
                buoyage.setObjectName(name);

                geom = resultSet.getString("geom");
                buoyage.setGeom(geom);

                tmp = resultSet.getString(4);
                String shp = "0";
                if (tmp != null) {
                    shp = tmp;
                }
                if (acronym.equals("LNDMRK")) {
                    ((Landmark) buoyage).setFunction(shp);
                } else {
                    buoyage.setShape(shp);
                }
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

                String cat = resultSet.getString(7);
                if (cat == null) {
                    cat = "0";
                }
                if (acronym.equals("LNDMRK")) {
                    ((Landmark) buoyage).setCategoryOfLandMark(cat);
                    String convis = resultSet.getString("convis");
                    if (convis != null) {
                        ((Landmark) buoyage).setConspicuousVisually(convis);
                    } else {
                        ((Landmark) buoyage).setConspicuousVisually("0");
                    }
                } else {
                    buoyage.setCategoryOfMark(cat);
                }

                if (acronym.equals("DAYMAR")) {
                    String natcon = resultSet.getString("natcon");
                    if (natcon == null) {
                        natcon = "0";
                    }
                    buoyage.setNatureOfConstruction(natcon);
                }
                
              //  System.out.println(buoyage+" "+buoyage.getGeom());
                buoyages.add(buoyage);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        // System.out.println("objects : " + objects);
        return buoyages;
    }
}
