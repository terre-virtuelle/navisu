/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.agents.ship.ShipAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Oct 12, 2019
 */
@XmlRootElement(name = "ShipAgentBathymetryCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipAgentBathymetryCmd
        implements NavigationCmd {

    private static ShipAgentBathymetryCmd INSTANCE;
    protected KmlComponentServices kmlComponentServices;
    protected ShipAgentServices shipAgentServices;
    protected BathymetryDBServices bathymetryDBServices;
    protected LayersManagerServices layersManagerServices;

    protected static final String GROUP_0 = "S57 charts";
    protected static final String S57_LAYER = "S57Stl";
    protected static final String DB_NAME = "BathyShomDB";
    protected static final String SHIP_NAME = "data/collada/rov.dae"; //drone.dae
    protected RenderableLayer s57Layer;
    protected Ship ship;
    protected ArrayList<Position> pathPositions;
    protected ShapeAttributes attrs;
    protected Path path;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected final String HOST = "localhost";
    protected final String PROTOCOL = "jdbc:postgresql://";
    protected final String PORT = "5432";
    protected final String DRIVER = "org.postgresql.Driver";
    protected final String USER = "admin";
    protected final String PASSWD = "admin";
    protected Connection bathyConnection;
    protected NavigationDataSet navigationDataSet;

    public static ShipAgentBathymetryCmd getInstance(ShipAgentServices shipAgentServices,
            LayersManagerServices layersManagerServices,
            BathymetryDBServices bathymetryDBServices,
            KmlComponentServices kmlComponentServices) {
        if (INSTANCE == null) {
            INSTANCE = new ShipAgentBathymetryCmd(shipAgentServices, layersManagerServices,
                    bathymetryDBServices, kmlComponentServices);
        }
        return INSTANCE;
    }

    public ShipAgentBathymetryCmd(ShipAgentServices shipAgentServices, LayersManagerServices layersManagerServices,
            BathymetryDBServices bathymetryDBServices, KmlComponentServices kmlComponentServices) {
        this.bathymetryDBServices = bathymetryDBServices;
        this.kmlComponentServices = kmlComponentServices;
        this.shipAgentServices = shipAgentServices;
        this.layersManagerServices=layersManagerServices;
        s57Layer = layersManagerServices.getLayer(GROUP_0, S57_LAYER);
        bathyConnection = bathymetryDBServices.connect(DB_NAME, HOST, PROTOCOL, PORT, DRIVER, USER, PASSWD);
        ship = new Ship("Lithops", 48.36, -4.49, 10.0, 270.0);
        //  System.out.println("bathyConnection : " + bathyConnection);
        shipAgentServices.init(ship, s57Layer, SHIP_NAME);
        shipAgentServices.setScale(0.5, 0.5, 0.5);
        pathPositions = new ArrayList<>();
        createAttributes();

        path = new Path(pathPositions);
        path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        path.setVisible(true);
        path.setValue("TYPE", "ShipTrack");
        path.setPathType(AVKey.GREAT_CIRCLE);
        path.setAttributes(attrs);
        s57Layer.addRenderable(path);
        wwd.redrawNow();
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg) {
        //  System.out.println("arg : " + arg);
        Depth d = (Depth) arg;
        double lat = d.getLatitude();
        double lon = d.getLongitude();

        navigationDataSet = new NavigationDataSet();

        List<Point3DGeo> points = bathymetryDBServices.retrieveIn(bathyConnection, "bathy", lat, lon, lat + 0.0015, lon + 0.0015);
        //  System.out.println("points : " + points);
        points.forEach((p) -> {
            navigationDataSet.add(p);

        });
        shipAgentServices.setPosition(d.getLatitude(), d.getLongitude(), points.get(0).getElevation());
        shipAgentServices.setHeading(270.0);
        navigationDataSet.add(ship);
        pathPositions.add(new Position(Angle.fromDegrees(lat), Angle.fromDegrees(lon), 10.0));
        path.setPositions(pathPositions);
        wwd.redrawNow();
        return navigationDataSet;
    }

    protected void createAttributes() {
        attrs = new BasicShapeAttributes();
        attrs.setOutlineMaterial(Material.GREEN);
        attrs.setOutlineWidth(2);
    }
}
