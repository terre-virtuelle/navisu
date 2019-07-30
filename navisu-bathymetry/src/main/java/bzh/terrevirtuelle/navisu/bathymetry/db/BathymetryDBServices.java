/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface BathymetryDBServices
        extends ComponentService {

    Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName,
            String userName, String passwd, String dataFileName);

    Connection connect(String dbName, String hostName, String protocol, String port,
            String driverName,
            String userName, String passwd);

    void close();

    void create(String filename, String table);

    void insert(String filename, String table);

    void create(String filename, String table, String query);

    void insert(String filename, String table, String query);

    List<Point3Df> readFromFile(String filename);

    void createIndex(String table);

    void insert(List<Point3Df> points);

    List<Point3DGeo> retrieveAll();

    List<Point3DGeo> retrieveAround(double lat, double lon);

    List<Point3DGeo> retrieveAround(double lat, double lon, double limit);

    List<Point3DGeo> retrieveIn(String table, double latMin, double lonMin, double latMax, double lonMax);

    List<Point3DGeo> retrieveIn(Connection connection, String table, double latMin, double lonMin, double latMax, double lonMax);

    void writePointList(List<Point3DGeo> points, Path pathname, boolean latLon);

    Point3DGeo[][] mergeData(Point3DGeo[][] orgData, List<Triangle_dt> triangles);

    Point3DGeo[][] mergeData(Point3DGeo[][] orgData, List<Triangle_dt> triangles, double depth);

    DatabaseDriver getDriver();

}
