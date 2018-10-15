/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class SLConsExportToSTL {

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected TopologyServices topologyServices;
    protected StlComponentServices stlComponentServices;
    protected JTSServices jtsServices;
    protected PolylineExportToSTL polylineExportToSTL;
    protected DisplayServices displayServices;
    protected String stlFilename;

    protected Path path;
    protected double latMin;
    protected double lonMin;

    protected double latScale;
    protected double lonScale;
    protected double verticalOffset;

    public SLConsExportToSTL(TopologyServices topologyServices, StlComponentServices stlComponentServices,
            JTSServices jtsServices, DisplayServices displayServices,
            String stlFilename,
            double latMin, double lonMin,
            double latScale, double lonScale,
            double verticalOffset,
            RenderableLayer layer) {
        this.topologyServices = topologyServices;
        this.stlComponentServices = stlComponentServices;
        this.jtsServices = jtsServices;
        this.displayServices = displayServices;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latScale = latScale;
        this.lonScale = lonScale;
        this.verticalOffset = verticalOffset;
        this.stlFilename = stlFilename;
        this.layer = layer;
        polylineExportToSTL = new PolylineExportToSTL(topologyServices, stlComponentServices, jtsServices, latMin, lonMin, latScale, lonScale, verticalOffset);
    }

    public void export(List<? extends Geo> objects) {
        for (Geo geo : objects) {
            // System.out.println(jtsServices.getBuffer(geo.getGeom(), 10.0, BufferParameters.CAP_FLAT));
            if (geo.getGeom().contains("MULTILINESTRING")) {
                path = topologyServices.wktMultiLineToWwjPath(geo.getGeom(), verticalOffset);
                layer.addRenderable(path);
            } else {
                if (geo.getGeom().contains("LINESTRING")) {
                    List<Point3D> points = jtsServices.getBuffer(geo.getGeom(), 0.0001, BufferParameters.CAP_FLAT);
                    List<Position> positions = new ArrayList<>();
                    for (Point3D p : points) {
                        positions.add(Position.fromDegrees(p.getLatitude(),
                                p.getLongitude(),
                                p.getElevation() * verticalOffset));
                    }
                   // displayServices.displayPoints3D(points, layer);
                    path = displayServices.createPath(positions, Material.GREEN);
                    layer.addRenderable(path);
                }
            }
        }
    }
}
