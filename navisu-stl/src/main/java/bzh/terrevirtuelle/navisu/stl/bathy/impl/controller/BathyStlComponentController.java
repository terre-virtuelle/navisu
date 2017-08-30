/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.bathy.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.stl.bathy.impl.BathyStlComponentImpl;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BathyStlComponentController {

    private static BathyStlComponentController INSTANCE = null;
    protected static final Logger LOGGER = Logger.getLogger(BathyStlComponentController.class.getName());
    protected DatabaseServices databaseServices;
    protected BathymetryDBServices bathymetryDBServices;
    protected GuiAgentServices guiAgentServices;
    protected WorldWindow wwd;
    protected RenderableLayer layer;
    protected BathyStlComponentImpl component;
    protected String LIMIT = "100";
    protected double maxElevation = -20.0;
    protected final double THRESHOLD = 0.0015;
    protected Geometry concaveHull;
    protected double MIN_DEPTH = 0.0;
    protected double MIN_LAT = 48.25;
    protected double MIN_LON = -4.55;
    protected double MAX_LAT = 48.45;
    protected double MAX_LON = -4.245;
    protected List<Point3D> points3d;
    NumberFormat nf4 = new DecimalFormat("0.0000");
    NumberFormat nf1 = new DecimalFormat("0.0");
    int i = 0;

    private BathyStlComponentController(BathyStlComponentImpl component,
            DatabaseServices databaseServices, GuiAgentServices guiAgentServices,
            String limit, RenderableLayer layer) {
        this.component = component;
        this.databaseServices = databaseServices;
        this.guiAgentServices = guiAgentServices;
        this.LIMIT = limit;
        this.layer = layer;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public static BathyStlComponentController getInstance(BathyStlComponentImpl component,
            DatabaseServices databaseServices, GuiAgentServices guiAgentServices,
            String limit, RenderableLayer layer) {
        if (INSTANCE == null) {
            INSTANCE = new BathyStlComponentController(component,
                    databaseServices, guiAgentServices,
                    limit, layer);
        }
        return INSTANCE;
    }

    
}
