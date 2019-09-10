/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_HOMONIM_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_LOW_BATHYMETRY_CLUT;
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.domain.lut.Clut;
import bzh.terrevirtuelle.navisu.domain.raster.RasterInfo;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.GridBox3D;
import bzh.terrevirtuelle.navisu.visualization.view.Display;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import bzh.terrevirtuelle.navisu.visualization.view.impl.controller.DisplayController;
import bzh.terrevirtuelle.navisu.visualization.view.impl.controller.JfxViewer;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.kml.KMLDocumentBuilder;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class DisplayImpl
        implements Display, DisplayServices, ComponentState {

    @UsedService
    GeodesyServices geodesyServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected static final String SEP = File.separator;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected DisplayController displayController;
    ShapeAttributes attrs0;

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
    public void displayPoints3D(List<Point3DGeo> points, RenderableLayer layer) {
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        PointPlacemark pointPlacemark;
        PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
        // attrs.setImageAddress("images/point.png");
        // attrs.setScale(1.0);
        for (Point3DGeo p : points) {
            pointPlacemark = new PointPlacemark(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation()));
            String label = String.format("%4f°, %4f° \n %2d m", p.getLatitude(), p.getLongitude(), (int) p.getElevation());
            pointPlacemark.setValue(AVKey.DISPLAY_NAME, label);
            pointPlacemark.setAttributes(attrs);
            pointPlacemarks.add(pointPlacemark);
        }
        layer.addRenderables(pointPlacemarks);
        wwd.redrawNow();
    }

    @Override
    public void displayPoints3DAsPath(List<Point3DGeo> points, RenderableLayer layer) {
        ArrayList<Position> pathPositions = new ArrayList<>();
        for (Point3DGeo p : points) {
            pathPositions.add(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation()));
        }
        Path path = createPath(pathPositions, Material.YELLOW);
        layer.addRenderable(path);
        wwd.redrawNow();
    }

    @Override
    public void displayPoints3DAsPath(List<Point3DGeo> points, double height, RenderableLayer layer, Material material) {
        ArrayList<Position> pathPositions = new ArrayList<>();
        for (Point3DGeo p : points) {
            pathPositions.add(Position.fromDegrees(p.getLatitude(), p.getLongitude(), height));
        }
        Path path = createPath(pathPositions, material);
        List<Path> l = new ArrayList<>();
        l.add(path);
        layer.addRenderable(path);
        wwd.redrawNow();
    }

    @Override
    public void displayPoints3DAsPolygon(List<Point3DGeo> points, double height, RenderableLayer layer, Material material) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Point3DGeo p : points) {
            positions.add(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation() + height));
        }
        Polygon polygon = new Polygon(positions);
        ShapeAttributes shapeAttributes = createAttributes(material);
        polygon.setAttributes(shapeAttributes);
        layer.addRenderable(polygon);
        wwd.redrawNow();
    }

    @Override
    public void displayFaceGeoAsPolygon(FaceGeo face, double height, RenderableLayer layer, Material material) {
        displayPoints3DAsPolygon(face.getVertices(), height, layer, material);
    }

    public void displayPoints3DAsPolygon(List<Point3DGeo> points, String label, double height, RenderableLayer layer, Material material) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Point3DGeo p : points) {
            positions.add(Position.fromDegrees(p.getLatitude(), p.getLongitude(), p.getElevation() + height));
        }
        Polygon polygon = new Polygon(positions);
        ShapeAttributes shapeAttributes = createAttributes(material);
        polygon.setAttributes(shapeAttributes);
        //polygon.set
        layer.addRenderable(polygon);
        wwd.redrawNow();
    }

    public void displayFaceGeoAsPolygon(FaceGeo face, String label, double height, RenderableLayer layer, Material material) {
        displayPoints3DAsPolygon(face.getVertices(), height, layer, material);
    }

    @Override
    public void displaySolidGeoAsPolygon(SolidGeo solid, double height, RenderableLayer layer, Material material) {
        List<FaceGeo> list = new ArrayList<>(solid.getFaces());
        for (FaceGeo f : list) {
            displayFaceGeoAsPolygon(f, "" + solid.getId(), height, layer, material);
        }
    }

    @Override
    public void displayBuildingGeoAsPolygon(SolidGeo solid, double height, RenderableLayer layer, Material material) {
        List<FaceGeo> faces = new ArrayList<>(solid.getFaces());
        faces.forEach((f) -> {
            displayFaceGeoAsPolygon(f, height, layer, material);
        });
        List<FaceGeo> roof = new ArrayList<>(solid.getRoof());
        roof.forEach((r) -> {
            displayFaceGeoAsPolygon(r, height, layer, material);
        });
    }

    @Override
    public void displayPositionsAsPath(List<Position> points, RenderableLayer layer, Material material) {
        Path path = createPath(points, material);
        layer.addRenderable(path);
        wwd.redrawNow();
    }

    @Override
    public void displayGrid(List<List<Point3DGeo>> latLons, RenderableLayer layer, Material material) {

    }

    @Override
    public void displayPoints3DAsTriangles(List<Point3DGeo> points, RenderableLayer layer, Material material) {

    }

    @Override
    public void displayPaths(GridBox3D gridBox3D, RenderableLayer layer, Material material, double verticalExaggeration) {
        displayGrid(gridBox3D.getGrid(), layer, material, verticalExaggeration);
    }

    @Override
    public void displayGridBox3D(GridBox3D gridBox3D, RenderableLayer layer, Material material, double verticalExaggeration) {

    }

    @Override
    public void displayGrid(Point3DGeo[][] latLons, RenderableLayer layer, Material material, double verticalExaggeration) {
        ArrayList<Position> pathIsoLatPositions;
        List<Path> latPaths = new ArrayList<>();
        Path path0 = null;
        for (Point3DGeo[] p : latLons) {
            pathIsoLatPositions = new ArrayList<>();
            for (Point3DGeo pp : p) {
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
        for (Point3DGeo k : latLons[0]) {
            pathIsoLonPositions = new ArrayList<>();
            for (Point3DGeo[] row : latLons) {
                pathIsoLonPositions.add(Position.fromDegrees(row[i].getLatitude(),
                        row[i].getLongitude(),
                        row[i].getElevation() * verticalExaggeration));
                path1 = createPath(pathIsoLonPositions, material);
            }
            i++;
            lonPaths.add(path1);
        }
        layer.addRenderables(lonPaths);
        wwd.redrawNow();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void displayRasterInfo(RasterInfo rasterInfo, GeoViewServices geoViewServices, String GROUP) {

        SurfaceImageLayer layerTif = new SurfaceImageLayer();
        layerTif.setOpacity(1);
        layerTif.setPickEnabled(false);
        try {
            layerTif.addImage(rasterInfo.getImageDir() + SEP + rasterInfo.getDemColorRelief());
        } catch (IOException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layerTif));
        layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layerTif));

        GeoWorldWindViewImpl.getWW().redrawNow();
    }

    @Override
    public Path createPath(List<Position> pathPositions, Material material) {
        Path p = new Path(pathPositions);
        ShapeAttributes attrs1 = new BasicShapeAttributes();
        attrs1.setOutlineOpacity(1.0);
        attrs1.setOutlineWidth(1d);
        attrs1.setOutlineMaterial(material);
        p.setAltitudeMode(WorldWind.ABSOLUTE);
        p.setAttributes(attrs1);
        return p;
    }

    @Override
    public List<Path> createPaths(Point3DGeo[][] latLons, double verticalExaggeration) {
        List<Position> positions;
        List<Path> result = new ArrayList<>();

        Path path;
        int latLength = latLons[0].length;
        int lonLength = latLons[1].length;

        for (int i = 0; i < latLength - 1; i++) {
            for (int j = 0; j < lonLength - 1; j++) {
                positions = new ArrayList<>();
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i + 1][j + 1].getLatitude(),
                        latLons[i + 1][j + 1].getLongitude(),
                        latLons[i + 1][j + 1].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i][j + 1].getLatitude(),
                        latLons[i][j + 1].getLongitude(),
                        latLons[i][j + 1].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));

                path = new Path(positions);

                result.add(path);
                // All triangles are created
                positions = new ArrayList<>();

                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i + 1][j + 1].getLatitude(),
                        latLons[i + 1][j + 1].getLongitude(),
                        latLons[i + 1][j + 1].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i + 1][j].getLatitude(),
                        latLons[i + 1][j].getLongitude(),
                        latLons[i + 1][j].getElevation() * verticalExaggeration));
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                path = new Path(positions);

                result.add(path);
            }
        }
        return result;
    }

    @Override
    public List<List<Path>> createPaths(List<Point3DGeo[][]> latLons, double verticalExaggeration) {
        List<List<Path>> result = new ArrayList<>();
        for (Point3DGeo[][] pts : latLons) {
            result.add(createPaths(pts, verticalExaggeration));
        }
        return result;
    }

    @Override
    public List<List<Path>> createPaths(GridBox3D box3D, double verticalExaggeration) {
        List<Point3DGeo[][]> latLons = new ArrayList<>();
        latLons.add(box3D.getGrid());
        List<List<Path>> result = createPaths(latLons, verticalExaggeration);
        result.add(box3D.getSidePaths());
        return result;
    }

    @Override
    public List<Path> displayGridAsTriangles(Point3DGeo[][] latLons,
            RenderableLayer layer, Material material,
            double verticalExaggeration) {

        List<Position> positions;
        List<Path> result = new ArrayList<>();

        Path path;
        int latLength = latLons.length;
        int lonLength = latLons[0].length;

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
                positions.add(Position.fromDegrees(latLons[i][j].getLatitude(),
                        latLons[i][j].getLongitude(),
                        latLons[i][j].getElevation() * verticalExaggeration));
                path = new Path(positions);
                path.setAttributes(createAttributes(material.getDiffuse()));
                // Only South triangles are display, for optimization
                result.add(path);

            }
        }
        layer.addRenderables(result);
        wwd.redrawNow();
        return result;
    }

    @Override
    public void displayGridAsPolygon(Point3DGeo[][] latLons, RenderableLayer layer, Material material, double verticalExaggeration) {
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
            /* 
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (height - t.A.z) * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, (height - t.B.z) * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, (height - t.C.z) * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, (height - t.A.z) * verticalExaggeration));
             */
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, t.A.z * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.B.x, t.B.y, t.B.z * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.C.x, t.C.y, t.C.z * verticalExaggeration));
            pathPositions.add(Position.fromDegrees(t.A.x, t.A.y, t.A.z * verticalExaggeration));
            Path p = new Path(pathPositions);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineOpacity(1.0);
            attrs.setOutlineWidth(1.0);

            double h = (t.A.z + t.B.z + t.C.z) / 3.0;
            Color color = SHOM_HOMONIM_BATHYMETRY_CLUT.getColor(h);
            attrs.setOutlineMaterial(new Material(color));
            p.setAttributes(attrs);
            double az = t.A.z;
            double bz = t.B.z;
            double cz = t.C.z;
            double moy = (az + bz + cz) / 3.0;
            p.setValue(AVKey.DISPLAY_NAME, (int) az + ", " + (int) bz + ", " + (int) cz + ": " + (int) moy);
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

    protected ShapeAttributes createAttributes(Color col) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawInterior(false);
        normAttributes.setDrawOutline(true);
        normAttributes.setOutlineMaterial(new Material(col));
        return normAttributes;
    }

    protected ShapeAttributes createAttributes(Material material) {
        ShapeAttributes normAttributes = new BasicShapeAttributes();
        normAttributes.setDrawInterior(false);
        normAttributes.setDrawOutline(true);
        normAttributes.setInteriorMaterial(material);
        normAttributes.setOutlineMaterial(material);
        return normAttributes;
    }

    @Override
    public Map<Double, Material> createCLUT(String fileName) {
        Clut clut = new Clut(fileName);
        return null;
    }

    @Override
    public void displayGrid(GridBox3D box, RenderableLayer layer, Material material, double verticalExaggeration) {
        displayGridAsTriangles(box.getGrid(), layer, material, verticalExaggeration);
        displayPaths(box.getSidePaths(), layer, material, verticalExaggeration);

    }

    @Override
    public void displayPaths(List<Path> paths, RenderableLayer layer, Material material, double verticalExaggeration) {
        ShapeAttributes attrs1 = createAttributes(material);
        List<Path> result = new ArrayList<>();
        for (Path p : paths) {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation() * verticalExaggeration));
            }
            result.add(new Path(tmpPos));
        }
        result.forEach((p) -> {
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs1);
        });

        layer.addRenderables(result);
        wwd.redrawNow();
    }

    @Override
    public void displayPaths(List<Path> paths, double elevation, RenderableLayer layer, Material material) {
        for (Path p : paths) {
            displayPath(p, elevation, layer, material);
        }
    }

    @Override
    public void displayPath(Path path, double elevation, RenderableLayer layer, Material material) {
        ShapeAttributes attrs1 = createAttributes(material);
        path.setAttributes(attrs1);
        path.setAltitudeMode(WorldWind.ABSOLUTE);
        layer.addRenderable(path);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygonsFromPaths(List<Path> paths, RenderableLayer layer, Material material) {
        List<Polygon> result = new ArrayList<>();
        paths.forEach((p) -> {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation()));
            }
            result.add(new Polygon(tmpPos));
        });
        result.forEach((p) -> {
            attrs0 = createAttributes(new Material(WWUtil.makeRandomColor(Color.LIGHT_GRAY)));
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs0);
        });
        layer.addRenderables(result);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygonsFromPaths(List<Path> paths, RenderableLayer layer, Material material, double verticalExaggeration) {
        List<Polygon> result = new ArrayList<>();
        paths.forEach((p) -> {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), (pp.getElevation() * verticalExaggeration)));
            }
            result.add(new Polygon(tmpPos));
        });
        result.forEach((p) -> {
            attrs0 = createAttributes(material);
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs0);
        });
        layer.addRenderables(result);
        wwd.redrawNow();

    }

    @Override
    public void displayPaths(List<Path> paths, RenderableLayer layer, Material material, double verticalExaggeration, double verticalOffset) {
        ShapeAttributes attrs0 = createAttributes(material);
        List<Path> result = new ArrayList<>();
        paths.forEach((p) -> {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), (pp.getElevation() * verticalExaggeration) + verticalOffset));
            }
            result.add(new Path(tmpPos));
        });
        result.forEach((p) -> {
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs0);
        });
        layer.addRenderables(result);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygons(List<Polygon> polygons, RenderableLayer layer, Material material, double verticalExaggeration) {
        ShapeAttributes attrs0 = createAttributes(material);
        List<Polygon> result = new ArrayList<>();
        polygons.forEach((p) -> {
            Iterable<? extends Position> positions = p.getBoundaries().get(0);
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation() * verticalExaggeration));
            }
            result.add(new Polygon(tmpPos));
        });
        result.forEach((p) -> {
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs0);
        });

        layer.addRenderables(result);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygon(Polygon poly, RenderableLayer layer, Material material) {
        List<Polygon> polygons = new ArrayList<>();
        polygons.add(poly);
        displayPolygons(polygons, layer, material);
    }

    @Override
    public void displayPolygon(Polygon poly, RenderableLayer layer, ShapeAttributes attr) {
        List<Polygon> polygons = new ArrayList<>();
        polygons.add(poly);
        displayPolygons(polygons, layer, attr);
    }

    @Override
    public void displayPolygons(List<Polygon> poly, RenderableLayer layer, Material material) {
        ShapeAttributes attrs0 = createAttributes(material);
        for (Polygon p : poly) {
            p.setAttributes(attrs0);
        }
        layer.addRenderables(poly);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygons(List<Polygon> poly, RenderableLayer layer, ShapeAttributes attr) {
        for (Polygon p : poly) {
            p.setAttributes(attr);
        }
        layer.addRenderables(poly);
        wwd.redrawNow();
    }

    @Override
    public void displayPolygons(List<Polygon> polygons, RenderableLayer layer, Material material, double verticalExaggeration, double verticalOffset) {
        ShapeAttributes attrs0 = createAttributes(material);
        List<Path> result = new ArrayList<>();
        polygons.forEach((p) -> {
            Iterable<? extends Position> positions = p.getBoundaries().get(0);
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), (pp.getElevation() * verticalExaggeration) + verticalOffset));
            }
            result.add(new Path(tmpPos));
        });
        result.forEach((p) -> {
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs0);
        });
        layer.addRenderables(result);
        wwd.redrawNow();
    }

    @Override
    public void exportWKML(String outputFilename, List<Path> paths) {
        List<Path> result = new ArrayList<>();
        paths.forEach((p) -> {
            Iterable<? extends Position> positions = p.getPositions();
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation()));
            }
            result.add(new Path(tmpPos));
        });
        Path[] pathTab = new Path[result.size()];
        for (int i = 0; i < result.size(); i++) {
            pathTab[i] = result.get(i);
        }
        try {
            Writer stringWriter = new StringWriter();
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            kmlBuilder.writeObjects(pathTab);
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File(outputFilename)));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void exportKML(String outputFilename, List<Path> paths) {

    }

    @Override
    public void exportWKMLPolygons(String outputFilename, List<Polygon> paths) {
        List<Polygon> result = new ArrayList<>();
        paths.forEach((p) -> {
            Iterable<? extends Position> positions = p.getBoundaries().get(0);
            List<Position> tmpPos = new ArrayList<>();
            for (Position pp : positions) {
                tmpPos.add(new Position(pp.getLatitude(), pp.getLongitude(), pp.getElevation()));
            }
            result.add(new Polygon(tmpPos));
        });
        Polygon[] pathTab = new Polygon[result.size()];
        for (int i = 0; i < result.size(); i++) {
            pathTab[i] = result.get(i);
        }
        try {
            Writer stringWriter = new StringWriter();
            KMLDocumentBuilder kmlBuilder = new KMLDocumentBuilder(stringWriter);
            kmlBuilder.writeObjects(pathTab);
            kmlBuilder.close();
            String xmlString = stringWriter.toString();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new StreamSource(new StringReader(xmlString)), new StreamResult(new File(outputFilename)));
        } catch (IOException | IllegalArgumentException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void exportWKML(List<Path> paths) {
        exportWKML("privateData/kml/out.kml", paths);
    }

    @Override
    public void exportKML(List<Path> paths) {
        exportKML("privateData/kml/out.kml", paths);
    }

    @Override
    public void exportWKMLPolygons(List<Polygon> polygons) {
        DisplayImpl.this.exportWKMLPolygons("privateData/kml/out.kml", polygons);
    }

    @Override
    public String mergeKML(String inputFilename, String outputFilename) {
        java.nio.file.Path path = Paths.get("privateData/kml/tmp.kml");
        try {
            Files.deleteIfExists(path);
            File file = new File("privateData/kml/tmp.kml");
            file.createNewFile();
            Files.lines(Paths.get(inputFilename)).forEach(this::init);
            Files.lines(Paths.get(outputFilename)).skip(2).forEach(this::init);
            String s = "</Document>\n";
            Files.write(path, s.getBytes(), StandardOpenOption.APPEND);
            s = "</kml>\n";
            Files.write(path, s.getBytes(), StandardOpenOption.APPEND);
            Files.copy(path, Paths.get(inputFilename), StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(path);

        } catch (IOException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return inputFilename;
    }

    private void init(String s) {
        if (!s.trim().contains("</Document>") && !s.trim().contains("</kml>")) {
            s = s.trim();
            s += "\n";
            try {
                Files.write(Paths.get("privateData/kml/tmp.kml"), s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }

    @Override
    public List<Polygon> createPolygons(List<Path> paths) {
        List<Polygon> result = new ArrayList<>();
        for (Path p : paths) {
            Iterable<? extends Position> poly = p.getPositions();
            result.add(new Polygon(poly));
        }
        return result;
    }

    @Override
    public void exportASC(String outputFilename, Point3DGeo[][] pts) {

        int nrows = pts[0].length - 1;
        int ncols = pts[1].length - 1;
        double xllcorner = pts[0][0].getLongitude();
        double yllcorner = pts[0][0].getLatitude();
        double cellsize = Math.abs((pts[0][0].getLatitude() - pts[nrows][0].getLatitude()) / nrows);

        double NODATA_value = -99999.00;
        String result = "ncols " + ncols + "\n";
        result += "nrows " + nrows + "\n";
        result += "xllcorner " + xllcorner + "\n";
        result += "yllcorner " + yllcorner + "\n";
        result += "cellsize " + cellsize + "\n";
        result += "NODATA_value " + NODATA_value + "\n";
        for (int i = nrows; i >= 0; i--) {
            for (int j = 0; j < ncols; j++) {
                double z = pts[i][j].getElevation();
                result += Double.toString(z) + " ";
            }
            result += "\n";
        }
        try {
            Files.write(Paths.get(outputFilename), result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public Point3DGeo[][] importASC(String inputFilename) {
        /*
          ncols        1000
          nrows        1000
          xllcorner    599962.500000000000
          yllcorner    6825037.500000000000
          cellsize     75.000000000000
          NODATA_value  -99999.00
          111.10 110.60 110.60
         */

        String data = "";
        int ncols;
        int nrows;
        double xllcorner;
        double yllcorner;
        double cellsize;
        double NODATA_value;
        try {
            data = new String(Files.readAllBytes(Paths.get(inputFilename)));
        } catch (IOException ex) {
            Logger.getLogger(DisplayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        String[] dataTab = data.split("\n");
        ncols = Integer.parseInt(dataTab[0].split("\\s+")[1]);
        nrows = Integer.parseInt(dataTab[1].split("\\s+")[1]);
        xllcorner = Double.parseDouble(dataTab[2].split("\\s+")[1]);
        yllcorner = Double.parseDouble(dataTab[3].split("\\s+")[1]);
        cellsize = Double.parseDouble(dataTab[4].split("\\s+")[1]);
        NODATA_value = Double.parseDouble(dataTab[5].split("\\s+")[1]);
        Point3DGeo[][] result = new Point3DGeo[nrows + 1][ncols + 1];

        int u = 0;
        int v = 0;

        for (int i = dataTab.length - 1; i >= 6; i--) {
            String[] rowTab = dataTab[i].split("\\s+");
            for (int j = 0; j < rowTab.length; j++) {
                result[u][v] = new Point3DGeo(yllcorner + u * cellsize, xllcorner + v * cellsize, Double.valueOf(rowTab[j]));
                v++;
            }
            u++;
            v = 0;
        }

        return result;
    }

    @Override
    public List<List<Path>> displayGridAsTriangles(List<Point3DGeo[][]> grids, RenderableLayer layer, double verticalExaggeration) {
        Material[] materials = {Material.GREEN, Material.BLUE, Material.YELLOW, Material.PINK,
            Material.GRAY, Material.MAGENTA, Material.ORANGE, Material.RED};
        List<List<Path>> result = new ArrayList<>();
        int i = 0;
        for (Point3DGeo[][] g : grids) {
            result.add(displayGridAsTriangles(g, layer, materials[i++ % 8], 2));
        }
        return result;
    }

    @Override
    public JfxViewer getJfxViewer() {
        JfxViewer jfxViewer = new JfxViewer(guiAgentServices);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, jfxViewer);
        guiAgentServices.getRoot().getChildren().add(jfxViewer);

        jfxViewer.setVisible(true);
        jfxViewer.setScene(guiAgentServices.getScene());
        return jfxViewer;
    }
}
