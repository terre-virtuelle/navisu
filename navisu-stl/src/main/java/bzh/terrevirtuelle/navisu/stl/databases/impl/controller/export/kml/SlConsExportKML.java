/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.SurfacePolygon;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class SlConsExportKML {

    protected TopologyServices topologyServices;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected Polygon polygon;
    protected ExtrudedPolygon extrudedPolygon = null;
    protected String geometry = null;
    protected final int TRESHOLD = 20000;

    public SlConsExportKML(TopologyServices topologyServices) {
        this.topologyServices = topologyServices;
    }

    public void export(String filename, StandardOpenOption option, List<? extends Geo> objects, double elevation) {
        List<ExtrudedPolygon> extrudedPolygons = new ArrayList<>();
        objects.forEach((g) -> {
            geometry = g.getGeom();
            if (geometry.contains("MULTILINESTRING") && !geometry.contains("EMPTY")) {
                polygon = topologyServices.wktMultiLineToWwjPolygon(geometry);
                if (polygon != null) {
                    SurfacePolygon sp = new SurfacePolygon(polygon.getOuterBoundary());
                    double area = sp.getArea(wwd.getModel().getGlobe());
                    if (area <= TRESHOLD) {
                        extrudedPolygons.add(new ExtrudedPolygon(polygon.getOuterBoundary(), elevation));
                    }
                }
            }
        });
        ExtrudedPolygon[] extrudedPolygonTab = new ExtrudedPolygon[extrudedPolygons.size()];
        int i = 0;
        for (ExtrudedPolygon e : extrudedPolygons) {
            extrudedPolygonTab[i++] = e;
        }
        KMLWriter kmlWriter = new KMLWriter(filename);
        kmlWriter.write(extrudedPolygonTab, option);
    }
}
