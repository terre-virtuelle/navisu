/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.lut.Clut;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.visualization.view.Display;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import bzh.terrevirtuelle.navisu.visualization.view.impl.controller.DisplayController;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        PointPlacemark pointPlacemark;
        PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
        attrs.setImageAddress("images/point.png");
        attrs.setScale(1.0);
        for (Point3D p : points) {
            pointPlacemark = new PointPlacemark(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation()));
            String label=String.format("%4f°, %4f° \n %2d m", p.getLatitude(), p.getLongitude(), (int)p.getElevation());
            pointPlacemark.setValue(AVKey.DISPLAY_NAME, label);
            pointPlacemark.setAttributes(attrs);
            pointPlacemarks.add(pointPlacemark);
        }
        layer.addRenderables(pointPlacemarks);
    }

    @Override
    public void displayGrid(List<List<Point3D>> latLons, Material material, RenderableLayer layer) {

    }

    @Override
    public void displayGrid(Point3D[][] latLons, Material material, RenderableLayer layer, double verticalExaggeration) {
        ArrayList<Position> pathIsoLatPositions;
        List<Path> latPaths = new ArrayList<>();
        Path path0 = null;
        for (Point3D[] p : latLons) {
            pathIsoLatPositions = new ArrayList<>();
            for (Point3D pp : p) {
                pathIsoLatPositions.add(Position.fromDegrees(pp.getLatitude(),
                        pp.getLongitude(),
                        pp.getElevation() * verticalExaggeration));
                path0 = createPath(pathIsoLatPositions, material);
            }
            latPaths.add(path0);
        }

        layer.addRenderables(latPaths);

        List<Path> lonPaths = new ArrayList<>();
        ArrayList<Position> pathIsoLonPositions;
        Path path1 = null;
        int i = 0;
        for (Point3D k : latLons[0]) {
            pathIsoLonPositions = new ArrayList<>();
            for (Point3D[] row : latLons) {
                pathIsoLonPositions.add(Position.fromDegrees(row[i].getLatitude(),
                        row[i].getLongitude(),
                        row[i].getElevation() * verticalExaggeration));
                path1 = createPath(pathIsoLonPositions, material);
            }
            i++;
            lonPaths.add(path1);
        }
        layer.addRenderables(lonPaths);
    }

    /*
        matrice.length     //  2
        matrice[0].length  //  4
        matrice[1].length  //  7
     */
    @Override
    public void displayGridAsPolygon(Point3D[][] latLons, Material material, RenderableLayer layer, double verticalExaggeration) {
        ArrayList<Polygon> polygons = null;

        int latLength = latLons[0].length;
        int lonLength = latLons[1].length;

        List<Position> positions;
        polygons = new ArrayList<>();
        Polygon polygon;
        Color color = null;
        for (int i = 0; i < latLength - 1; i++) {
            for (int j = 0; j < lonLength - 1; j++) {
                positions = new ArrayList<>();
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i][j + 1].getLatitude(),
                        latLons[i][j + 1].getLongitude(),
                        latLons[i][j + 1].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i + 1][j + 1].getLatitude(),
                        latLons[i + 1][j + 1].getLongitude(),
                        latLons[i + 1][j + 1].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i + 1][j].getLatitude(),
                        latLons[i + 1][j].getLongitude(),
                        latLons[i + 1][j].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                polygon = new Polygon(positions);
                color = SHOM_LOW_BATHYMETRY_CLUT.getColor(latLons[i][j].getElevation());

                polygon.setAttributes(createAttributes(color));
                polygons.add(polygon);
                layer.addRenderables(polygons);
                polygons.clear();
            }
        }
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
        p.setAltitudeMode(WorldWind.ABSOLUTE);
        p.setAttributes(attrs0);
        return p;
    }

    protected ShapeAttributes createAttributes(Color col) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawInterior(false);
        // normAttributes.setInteriorMaterial(new Material(col));
        normAttributes.setDrawOutline(true);
        normAttributes.setOutlineMaterial(new Material(col));
        return normAttributes;
    }

    @Override
    public Map<Double, Material> createCLUT(String fileName) {
        Clut clut = new Clut(fileName);
        System.out.println(clut.getContent());
        return null;
    }

    @Override
    public void displayGrid(GridBox3D box, Material material, RenderableLayer s57Layer, double verticalExaggeration, boolean isBaseDisplayed) {
        displayGrid(box.getGridElevation(), material, s57Layer, verticalExaggeration);
        if (isBaseDisplayed == true) {
            displayGrid(box.getGridBase(), material, s57Layer, verticalExaggeration);
        }
        displayGrid(box.getSideLat0(), material, s57Layer, verticalExaggeration);
        displayGrid(box.getSideLat1(), material, s57Layer, verticalExaggeration);
        displayGrid(box.getSideLon0(), material, s57Layer, verticalExaggeration);
        displayGrid(box.getSideLon1(), material, s57Layer, verticalExaggeration);
    }

}
