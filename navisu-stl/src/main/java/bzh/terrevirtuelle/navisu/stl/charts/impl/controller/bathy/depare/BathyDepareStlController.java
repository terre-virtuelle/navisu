/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.controller.bathy.depare;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetryServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BathyDepareStlController {

    protected static final Logger LOGGER = Logger.getLogger(BathyDepareStlController.class.getName());
    protected BathymetryDBServices bathymetryDBServices;
    protected DisplayBathymetryServices displayBathymetryServices;
    protected GuiAgentServices guiAgentServices;
    protected Polygon polyEnvelope;
    protected List<? extends Position> positions;
    protected Path pathname;
    protected boolean latLon;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected double maxElevation = 0.0;

    public BathyDepareStlController(BathymetryDBServices bathymetryDBServices,
            DisplayBathymetryServices displayBathymetryServices,
            GuiAgentServices guiAgentServices, RenderableLayer layer,
            Polygon polyEnvelope) {
        this.bathymetryDBServices = bathymetryDBServices;
        this.displayBathymetryServices = displayBathymetryServices;
        this.guiAgentServices = guiAgentServices;
        this.layer = layer;
        this.polyEnvelope = polyEnvelope;

        Iterable<? extends LatLon> bounds = polyEnvelope.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }

        double latMin = listLatLon.get(0).getLatitude().getDegrees();
        double lonMin = listLatLon.get(0).getLongitude().getDegrees();
        double latMax = listLatLon.get(2).getLatitude().getDegrees();
        double lonMax = listLatLon.get(2).getLongitude().getDegrees();
        List<Point3D> points = bathymetryDBServices.retrieveIn("bathy", latMin, lonMin, latMax, lonMax);

        points.stream().filter((p) -> (maxElevation < p.getElevation())).forEachOrdered((p) -> {
            maxElevation = p.getElevation();
        });
    }

    public void writePointList(List<? extends Position> positions, Path pathname, boolean latLon) {
        double latMin = positions.get(0).getLatitude().getDegrees();
        double lonMin = positions.get(0).getLongitude().getDegrees();
        double latMax = positions.get(2).getLatitude().getDegrees();
        double lonMax = positions.get(2).getLongitude().getDegrees();

        List<Point3D> points = bathymetryDBServices.retrieveIn("bathy", latMin, lonMin, latMax, lonMax);
        bathymetryDBServices.writePointList(points, pathname, latLon);
    }

    public void displayDelaunaySounding(List<? extends Position> positions, RenderableLayer l, double maxElevation) {
        double latMin = positions.get(0).getLatitude().getDegrees();
        double lonMin = positions.get(0).getLongitude().getDegrees();
        double latMax = positions.get(2).getLatitude().getDegrees();
        double lonMax = positions.get(2).getLongitude().getDegrees();

        List<Point3D> points = bathymetryDBServices.retrieveIn("bathy", latMin, lonMin, latMax, lonMax);
        displayBathymetryServices.displayDelaunaySounding(points, l, maxElevation);
        
    }

    public double getMaxElevation() {
        return maxElevation;
    }

    
}
