/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import java.sql.Connection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Dec 7, 2017
 */
@XmlRootElement(name = "BathymetryCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class BathymetryCmd
        implements NavigationCmd {

    private final String HOST = "localhost";
    private final String PROTOCOL = "jdbc:postgresql://";
    private final String PORT = "5432";
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "admin";
    private final String PASSWD = "admin";
    protected Connection bathyConnection;
    private static BathymetryCmd INSTANCE;
    private NavigationDataSet navigationDataSet;
    private final BathymetryDBServices bathymetryDBServices;

    public static BathymetryCmd getInstance(BathymetryDBServices bathymetryDBServices) {
        if (INSTANCE == null) {
            INSTANCE = new BathymetryCmd(bathymetryDBServices);
        }
        return INSTANCE;
    }

    private BathymetryCmd(BathymetryDBServices bathymetryDBServices) {
        this.bathymetryDBServices = bathymetryDBServices;
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg) {

        Depth depth = (Depth) arg;
        double lat = depth.getLatitude();
        double lon = depth.getLongitude();
        System.out.println("depth : " + depth);
        navigationDataSet = new NavigationDataSet();
        
        List<Point3D> points = bathymetryDBServices.retrieveIn(bathyConnection, "bathy", lat, lon, lat + 0.0015, lon + 0.0015);
        System.out.println("points : " + points);
        points.forEach((p) -> {
            navigationDataSet.add(p);
        });
         
        return navigationDataSet;
    }

    @Override
    public NavigationDataSet doIt(String arg) {
//
       // System.out.println("arg : " + arg);
        bathyConnection = bathymetryDBServices.connect(arg, HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
      //  System.out.println("bathyConnection : " + bathyConnection);
        navigationDataSet = new NavigationDataSet();
        return null;
    }
}
