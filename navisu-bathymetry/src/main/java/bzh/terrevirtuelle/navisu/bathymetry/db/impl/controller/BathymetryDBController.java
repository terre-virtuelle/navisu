/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller;

import bzh.terrevirtuelle.navisu.bathymetry.db.impl.BathymetryDBImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import java.sql.Connection;
import java.util.List;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBController {

    private static final BathymetryDBController INSTANCE;
    protected WorldWindow wwd;
    private BathymetryDBImpl bathymetryDBImpl;
    List<Point3D> points;
    private Connection connection;

    static {
        INSTANCE = new BathymetryDBController();
    }

    private BathymetryDBController() {
        wwd = GeoWorldWindViewImpl.getWW();

        wwd.addPositionListener((PositionEvent event) -> {
            Position pos = event.getPosition();
            if (pos != null && connection !=null) {
                points = bathymetryDBImpl.retrieve(pos.getLatitude().getDegrees(), pos.getLongitude().getDegrees());
            }
        });
    }

    public static BathymetryDBController getInstance() {
        return INSTANCE;
    }

    public void setBathymetryDB(BathymetryDBImpl bathymetryDBImpl) {
        this.bathymetryDBImpl = bathymetryDBImpl;
       
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
