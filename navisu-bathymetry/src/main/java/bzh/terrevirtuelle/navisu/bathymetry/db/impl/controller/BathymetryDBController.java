/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.db.impl.controller;

import bzh.terrevirtuelle.navisu.bathymetry.db.impl.BathymetryDBImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class BathymetryDBController
        implements SelectListener {

    private static final BathymetryDBController INSTANCE;
    protected WorldWindow wwd;
    private BathymetryDBImpl bathymetryDBImpl;

    static {
        INSTANCE = new BathymetryDBController();
    }

    private BathymetryDBController() {
         wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);

    }

    public static BathymetryDBController getInstance() {
        return INSTANCE;
    }

    @Override
    public void selected(SelectEvent event) {
        System.out.println("event " + event);
        if (event.isRightClick()) {
            Object topObject = event.getTopObject();
            if (topObject != null) {
                // if (topObject.getClass() == SurfacePolygons.class) {
                System.out.println(topObject.getClass().getName());
                //  }
            }
        }
    }

    public void setBathymetryDB(BathymetryDBImpl bathymetryDBImpl) {
        this.bathymetryDBImpl = bathymetryDBImpl;
    }

    
}
