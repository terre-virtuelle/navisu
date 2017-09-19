/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3Df;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
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

    void create(String filename);

    List<Point3Df> readFromFile(String filename);

    void createIndex();

    void insert(List<Point3Df> points);

    List<Point3D> retrieveAll();

    List<Point3D> retrieveAround(double lat, double lon);

    List<Point3D> retrieveIn(double latMin, double lonMin, double latMax, double lonMax);

    Point3D[][] mergeData(Point3D[][] orgData, int nbLat, int nbLon, List<Triangle_dt> triangles);

    DatabaseDriver getDriver();
}
