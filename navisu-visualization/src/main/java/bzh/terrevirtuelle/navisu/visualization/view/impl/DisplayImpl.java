/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.visualization.view.Display;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import bzh.terrevirtuelle.navisu.visualization.view.impl.controller.DisplayController;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class DisplayImpl
        implements Display, DisplayServices, ComponentState {

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected DisplayController displayController;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void displayPoints3D(List<Point3D> points, RenderableLayer layer) {
    }

    @Override
    public void displayPlane(double minLat, double minLon, double maxLat, double maxLon, double height,
            Material material, RenderableLayer l) {
        ArrayList<Position> pathPositions = new ArrayList<>();
        pathPositions.add(Position.fromDegrees(minLat, minLon, height));
        pathPositions.add(Position.fromDegrees(maxLat, minLon, height));
        pathPositions.add(Position.fromDegrees(maxLat, maxLon, height));
        pathPositions.add(Position.fromDegrees(minLat, maxLon, height));
        pathPositions.add(Position.fromDegrees(minLat, minLon, height));
        l.addRenderable(createPath(pathPositions, material));
    }

    @Override
    public void displayTriangle(Triangle_dt t,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l) {
        if (t.A != null && t.B != null && t.C != null) {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (t.B.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (t.C.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * verticalExaggeration) + height));
            Path p = new Path(pathPositions);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setInteriorMaterial(material);
            attrs.setDrawInterior(true);
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(0.6);
            attrs.setOutlineMaterial(material);
            p.setAttributes(attrs);
            p.setValue(AVKey.DISPLAY_NAME, (int) (height - t.A.z) + ", "
                    + (int) (height - t.B.z) + ", "
                    + (int) (height - t.C.z));
            l.addRenderable(p);
        }
    }

    @Override
    public void displayDelaunay(List<Triangle_dt> triangles,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l) {
        triangles.stream()
                .filter((t) -> (t.A != null && t.B != null && t.C != null)).map((t) -> {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (t.B.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (t.C.z * verticalExaggeration) + height));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (t.A.z * verticalExaggeration) + height));
            Path p = new Path(pathPositions);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(1.0);
            /*
            double h = (t.A.z+t.B.z+t.C.z)/3.0;
            Color color = SHOM_LOW_BATHYMETRY_CLUT.getColor(h);
            attrs.setOutlineMaterial(new Material(color));
             */
            attrs.setOutlineMaterial(material);
            p.setAttributes(attrs);
            p.setValue(AVKey.DISPLAY_NAME, (int) (height - t.A.z) + ", "
                    + (int) (height - t.B.z) + ", "
                    + (int) (height - t.C.z));
            return p;
        }
        ).map(
                (p) -> {
                    l.addRenderable(p);
                    return p;
                }
        ).forEachOrdered(
                (_item) -> {

                }
        );
        wwd.redrawNow();
    }

    @Override
    public void displayConcaveHull(Geometry concaveHull,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l) {
        Coordinate[] concaveHullCoordinates = concaveHull.getCoordinates();
        ArrayList<Position> pathPositions1 = new ArrayList<>();
        for (Coordinate concaveHullCoordinate : concaveHullCoordinates) {
            pathPositions1.add(Position.fromDegrees(concaveHullCoordinate.y,
                    concaveHullCoordinate.x,
                    (height - concaveHullCoordinate.z) * verticalExaggeration));//*10

        }
        l.addRenderable(createPath(pathPositions1, material));
        wwd.redrawNow();
    }

    @Override
    public Path createPath(List<Position> pathPositions, Material material) {
        Path p = new Path(pathPositions);
        ShapeAttributes attrs0 = new BasicShapeAttributes();
        attrs0.setOutlineOpacity(1.0);
        attrs0.setOutlineWidth(1d);
        attrs0.setOutlineMaterial(material);
        p.setAttributes(attrs0);
        return p;
    }
@Override
    public List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < threshold)).forEach((t) -> {
            tmp1.add(t);

        });
        return tmp1;
    }
}
