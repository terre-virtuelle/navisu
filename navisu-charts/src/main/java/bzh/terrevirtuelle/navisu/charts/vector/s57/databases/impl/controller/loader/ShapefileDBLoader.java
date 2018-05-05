/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.MnsysDBLoader.LOGGER;
import static bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.ResultSetDBLoader.S57_REQUEST_MAP;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.scene.control.Alert;

/**
 *
 * @author serge
 */
public class ShapefileDBLoader {

    protected DatabaseServices databaseServices;
    protected String databaseName;
    protected String user;
    protected String passwd;

    protected Connection connection;
    protected String request;
    protected ResultSet resultSet;
    protected String acronym;
    protected double lat;
    protected double lon;
    protected boolean clip;

    public ShapefileDBLoader(DatabaseServices databaseServices,
            String databaseName, String user, String passwd, boolean clip) {
        this.databaseServices = databaseServices;
        this.databaseName = databaseName;
        this.user = user;
        this.passwd = passwd;
        this.clip = clip;
    }

    public ShapefileDBLoader(Connection connection, String acronym) {
        this.connection = connection;
        this.acronym = acronym;
    }

    @SuppressWarnings("unchecked")
    public Shapefile retrieveIn(String table, String attributes, double latMin, double lonMin, double latMax, double lonMax) {
        String fileName = databaseServices.spatialDBToShapefile(table, attributes,
                databaseName,
                user, passwd,
                latMin, lonMin, latMax, lonMax, clip);
        return createShapefileFromSource(fileName);
    }

    @SuppressWarnings("unchecked")
    public ResultSet retrieveResultSetIn(double latMin, double lonMin, double latMax, double lonMax) {

        if (connection != null) {
            try {
                request = S57_REQUEST_MAP.get(acronym);
                request += "(" + lonMin + ", " + latMin + ", "
                        + lonMax + ", " + latMax + ", "
                        + "4326);";

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

    public Shapefile createShapefileFromSource(Object source) {
        if (WWUtil.isEmpty(source)) {
            String message = Logging.getMessage("nullValue.SourceIsNull");
            Logging.logger().severe(message);
            throw new IllegalArgumentException(message);
        }
        Shapefile shp = new Shapefile(source);

        return shp;
    }

}
