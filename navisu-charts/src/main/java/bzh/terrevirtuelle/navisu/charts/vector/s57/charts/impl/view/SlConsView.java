/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class SlConsView {

    protected JTSServices jtsServices;
    protected GeodesyServices geodesyServices;

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected Polygon polygon;

    public SlConsView(JTSServices jtsServices, GeodesyServices geodesyServices, RenderableLayer layer) {
        this.jtsServices = jtsServices;
        this.geodesyServices = geodesyServices;
        this.layer = layer;
    }

    public void display(List<? extends Geo> objects) {
        List<ExtrudedPolygon> extrudedPolygons = new ArrayList<>();
        for (Geo geo : objects) {
            String geometry = geo.getGeom();
            if (geometry.contains("MULTILINESTRING") || geometry.contains("LINESTRING")) {
                List<Point3DGeo> points = jtsServices.getBuffer(geometry, 0.00012, BufferParameters.CAP_FLAT);
                double area = jtsServices.getPolygon(points).getArea();
                if (area < 2E-5) {
                    List<Position> positions = new ArrayList<>();
                    points.forEach((p) -> {
                        positions.add(Position.fromDegrees(p.getLatitude(),
                                p.getLongitude(),
                                p.getElevation() / 5));
                    });
                    polygon = new Polygon(positions);
                    ExtrudedPolygon extrudedPolygon = new ExtrudedPolygon(positions, 5.0);
                    extrudedPolygon.setValue(AVKey.DISPLAY_NAME, area);
                    extrudedPolygons.add(extrudedPolygon);
                }
            }

        }
        layer.addRenderables(extrudedPolygons);
        wwd.redrawNow();
    }
}
