/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.view.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetry;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetryServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.impl.controller.DisplayBathymetryController;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Feb 25, 2017
 */
public class DisplayBathymetryImpl
        implements DisplayBathymetry, DisplayBathymetryServices,
        InstrumentDriver, ComponentState {

    final String NAME = "BathyStl";
    final String LAYER_NAME = "BathyShom";
    final String LIMIT = "100";
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    DisplayServices displayServices;
    @UsedService
    DelaunayServices delaunayServices;
    @UsedService
    JTSServices jtsServices;

    protected static final String GROUP = "Bathymetry data";
    protected RenderableLayer layer;
    protected DisplayBathymetryController controller;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected static final Logger LOGGER = Logger.getLogger(DisplayBathymetryImpl.class.getName());

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentInitiated() {
        layer = layersManagerServices.getLayer(GROUP, LAYER_NAME);
        controller = DisplayBathymetryController.getInstance(this,
                bathymetryDBServices, guiAgentServices, 
                displayServices,delaunayServices,jtsServices,
                LIMIT, layer);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;
        if (!category.equals(NAME)) {
        } else {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        String[] tab = files;

    }

    @Override
    public void displayAllSounding(){
        controller.displayAllSounding();
    }

/*
    @Override
    public Geometry createConcaveHull(List<Point3D> points3d, double threshold) {
       return controller.createConcaveHull(points3d, threshold);
    }
*/
    

    @Override
    public void displaySounding(double lat, double lon, double depth, RenderableLayer l) {
  controller.displaySounding(lat, lon, depth, l);
    }

    @Override
    public void displaySounding(List<Point3D> points, RenderableLayer l) {
  controller.displaySounding(points, l);
    }

}
