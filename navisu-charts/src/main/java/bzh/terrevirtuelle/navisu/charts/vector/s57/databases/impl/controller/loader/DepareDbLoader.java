/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwind.util.WWUtil;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @date 28/02/2018
 * @author serge
 */
public class DepareDbLoader {

    protected DatabaseServices databaseServices;
    protected String databaseName;
    protected String user;
    protected String passwd;

    public DepareDbLoader(DatabaseServices databaseServices,
            String databaseName,
            String user,
            String passwd) {
        this.databaseServices = databaseServices;
        this.databaseName = databaseName;
        this.user = user;
        this.passwd = passwd;
    }

    @SuppressWarnings("unchecked")
    public Shapefile retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        /*
        pgsql2shp -f depar_0 -h localhost 
        -u admin -P admin s57NP5DB 
        "SELECT geom FROM depare WHERE geom && ST_MakeEnvelope(-4.55,48.25,-4.3,48.42,4326)";
         */
        databaseServices.spatialDBToShapefile(databaseName,
                user, passwd,
                latMin, lonMin, latMax, lonMax);
        return null;

    }

    public Shapefile createLayerFromSource(Object source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }

        Shapefile shp = null;
        Layer layer = null;
        try {
            shp = new Shapefile(source);
        } finally {
            WWIO.closeStream(shp, source.toString());
        }

        return shp;
    }
}
