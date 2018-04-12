/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import gov.nasa.worldwind.formats.shapefile.Shapefile;

/**
 * @date 28/02/2018
 * @author serge
 */
public class DepthContourDbLoader
        extends DBLoader {

    public DepthContourDbLoader(DatabaseServices databaseServices, String databaseName, String user, String passwd) {
        super(databaseServices, databaseName, user, passwd);
    }

    @SuppressWarnings("unchecked")
    public Shapefile retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        return retrieveIn("depcnt", "geom,valdco", latMin, lonMin, latMax, lonMax);
    }
}
