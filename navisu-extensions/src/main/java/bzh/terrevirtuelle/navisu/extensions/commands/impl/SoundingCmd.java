/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader.SoundgDBLoader;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Sounding;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
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
@XmlRootElement(name = "SoundingCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoundingCmd
        implements NavigationCmd {

    private final String HOST = "localhost";
    private final String PROTOCOL = "jdbc:postgresql://";
    private final String PORT = "5432";
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "admin";
    private final String PASSWD = "admin";
    protected Connection connection;

    private static SoundingCmd INSTANCE;

    private NavigationDataSet navigationDataSet;

    private final BathymetryDBServices bathymetryDBServices;
    protected TopologyServices topologyServices;

    SoundgDBLoader soundgDBLoader;

    public static SoundingCmd getInstance(BathymetryDBServices bathymetryDBServices, TopologyServices topologyServices) {
        if (INSTANCE == null) {
            INSTANCE = new SoundingCmd(bathymetryDBServices, topologyServices);
        }
        return INSTANCE;
    }

    private SoundingCmd(BathymetryDBServices bathymetryDBServices, TopologyServices topologyServices) {
        this.bathymetryDBServices = bathymetryDBServices;
        this.topologyServices = topologyServices;
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg) {
        Depth depth = (Depth) arg;
        double lat = depth.getLatitude();
        double lon = depth.getLongitude();
        navigationDataSet = new NavigationDataSet();

        List<Sounding> points = soundgDBLoader.retrieveObjectsIn(lat, lon, lat + 0.05, lon + 0.05);
        points.forEach((p) -> {
            navigationDataSet.add(new Depth(p.getLatitude(), p.getLongitude(), p.getDepth()));
        });
        return navigationDataSet;
    }

    @Override
    public NavigationDataSet doIt(String arg) {
        connection = bathymetryDBServices.connect(arg, HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        if (connection != null) {
            soundgDBLoader = new SoundgDBLoader(topologyServices, connection);
        }
        return new NavigationDataSet();
    }
}
