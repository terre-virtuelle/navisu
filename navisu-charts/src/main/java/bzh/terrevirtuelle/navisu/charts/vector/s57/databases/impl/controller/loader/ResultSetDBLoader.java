/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public abstract class ResultSetDBLoader {

    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;
    protected List<String> geometry;
    protected List<Geo> objects;

    public static final Map<String, String> S57_REQUEST_MAP
            = Collections.unmodifiableMap(new HashMap<String, String>() {
                {
                    put("TOPMAR", "SELECT geom, topshp "
                            + " FROM topmar "
                            + " WHERE geom && ST_MakeEnvelope");
                    put("M_NSYS", "SELECT geom, marsys "
                            + " FROM m_nsys "
                            + " WHERE geom && ST_MakeEnvelope");
                    put("ACHARE", "rcid, objnam, catach, restrn");
                    put("BCNCAR", "rcid, objnam, bcnshp, colour, colpat, catcam");
                    put("BCNLAT", "rcid, objnam, bcnshp, colour, colpat, catlam");
                    put("BCNISD", "rcid, objnam, bcnshp, colour, colpat, status");
                    put("BCNSAW", "rcid, objnam, bcnshp, colour, colpat, status");
                    put("BCNSPP", "rcid, objnam, bcnshp, colour, colpat, catspm");
                    put("BCNISD", "rcid, objnam, bcnshp, colour, colpat, status");
                    put("BOYCAR", "rcid, objnam, boyshp, colour, colpat, catcam");
                    put("BOYINB", "rcid, objnam, boyshp, colour, colpat, status");
                    put("BOYISD", "rcid, objnam, boyshp, colour, colpat, status");
                    put("BOYLAT", "rcid, objnam, boyshp, colour, colpat, catlam");
                    put("BOYSAW", "rcid, objnam, boyshp, colour, colpat, status");
                    put("BOYSPP", "rcid, objnam, boyshp, colour, colpat, catspm");
                    put("DAYMAR", "rcid, objnam, topshp, colour, colpat, catspm, natcon");
                    put("COALNE", "rcid, elevat");
                    put("DEPARE", "rcid, drval1, drval2");
                    put("DEPCNT", "rcid, valdco");
                    put("DOCARE", "rcid, objnam, inform, ninfom");
                    put("DRGARE", "rcid, objnam, inform, ninfom, drval1, drval2");
                    put("LIGHTS", "rcid, objnam, catlit, colour, height, litchr, orient, sectr1, sectr2, siggrp, sigper, sigseq, valnmr ");
                    put("LNDMRK", "rcid, objnam, functn, colour, colpat, catlmk, status, convis ");
                    put("MORFAC", "rcid, objnam, boyshp, colour, colpat, catmor");
                    put("NAVLNE", "rcid, orient");
                    put("PONTON", "rcid");
                    put("RESARE", "rcid, catrea, objnam, restrn, inform, ninfom");
                    put("SLCONS", "rcid");
                    put("WRECKS", "rcid, objnam, catwrk");
                }
            ;

    });
    
    public ResultSetDBLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;
    }

    abstract List<? extends Geo> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax);

    @SuppressWarnings("unchecked")
    public ResultSet retrieveResultSetIn(double latMin, double lonMin, double latMax, double lonMax) {
        
        if (connection != null) {
            try {
                request = "SELECT ST_AsText(ST_ClipByBox2D(geom, ST_MakeEnvelope";
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", 4326))) AS geom";
                String tmp = S57_REQUEST_MAP.get(acronym);
                if (!tmp.trim().equals("")) {
                    request += ", " + tmp;
                }
                request += " FROM " + acronym.toLowerCase() + " ;";
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
