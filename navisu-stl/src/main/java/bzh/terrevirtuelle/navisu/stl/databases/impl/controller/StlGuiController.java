/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class StlGuiController {

    protected double latRange;
    protected double lonRange;
    protected double latRangeMetric;
    protected double lonRangeMetric;
    protected double latScale;
    protected double lonScale;
    protected double globalScale;

    protected double tileSideX;
    protected double tileSideY;
    protected double tileSideZ;
    protected int tileCount;
    protected GeodesyServices geodesyServices;

    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public StlGuiController(GeodesyServices geodesyServices) {
        this.geodesyServices = geodesyServices;
    }

    public void setTileSideX(double tileSideX) {
        this.tileSideX = tileSideX;
    }

    public void setTileSideY(double tileSideY) {
        this.tileSideY = tileSideY;
    }

    public void setTileSideZ(double tileSideZ) {
        this.tileSideZ = tileSideZ;
    }

    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

    public void initTile(double tileSideX, double tileSideY, double tileSideZ, int tileCount) {
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.tileSideZ = tileSideZ;
        this.tileCount = tileCount;
    }

    public String initScale(double latMin, double lonMin,
            double latMax, double lonMax) {
        latRangeMetric = geodesyServices.getDistanceM(latMin, lonMin, latMax, lonMin);
        lonRangeMetric = geodesyServices.getDistanceM(latMin, lonMin, latMin, lonMax);
        double scaleLat = latRangeMetric / (tileSideY / 1000.0);
        double scaleLon = lonRangeMetric / (tileSideX / 1000.0);

        globalScale = (scaleLat + scaleLon) / 2;//Arrondi pour l'affichage
        globalScale /= tileCount;
        double sc = 1;
        if (globalScale <= 1000) {
            sc = 100;
        } else if (globalScale > 1000 && globalScale <= 10000) {
            sc = 1000;
        } else if (globalScale > 10000 && globalScale <= 100000) {
            sc = 1000;
        } else if (globalScale > 100000 && globalScale <= 1000000) {
            sc = 10000;
        } else if (globalScale > 1000000 && globalScale <= 10000000) {
            sc = 100000;
        }
        globalScale /= sc;
        return "1/" + Integer.toString((int) (Math.round(globalScale) * sc));
    }

    public List<Polygon> createAndDisplayTiles(RenderableLayer layer, Material material,
            double hight, double latMin, double lonMin, double latMax, double lonMax,
            int line, int col) {
        if (layer.getNumRenderables() >= 1) {
            layer.removeAllRenderables();
        }

        latRange = latMax - latMin;
        lonRange = lonMax - lonMin;

        latRange /= line;
        lonRange /= col;

        latRange = Math.abs(latRange);
        lonRange = Math.abs(lonRange);

        double orgLat = latMin;
        double orgLon = lonMin;

        List<Polygon> tiles = new ArrayList<>();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                List<Position> positions = new ArrayList<>();
                positions.add(new Position(Angle.fromDegrees(orgLat + i * latRange), Angle.fromDegrees(orgLon + j * lonRange), hight));
                positions.add(new Position(Angle.fromDegrees(orgLat + i * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), hight));
                positions.add(new Position(Angle.fromDegrees(orgLat + (i + 1) * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), hight));
                positions.add(new Position(Angle.fromDegrees(orgLat + (i + 1) * latRange), Angle.fromDegrees(orgLon + j * lonRange), hight));
                positions.add(new Position(Angle.fromDegrees(orgLat + i * latRange), Angle.fromDegrees(orgLon + j * lonRange), hight));
                Polygon polygon = new Polygon(positions);

                BasicShapeAttributes normalAttributes = new BasicShapeAttributes();
                normalAttributes.setInteriorMaterial(material);
                normalAttributes.setOutlineOpacity(0.5);
                normalAttributes.setInteriorOpacity(0.1);
                normalAttributes.setOutlineMaterial(material);
                normalAttributes.setOutlineWidth(2);
                normalAttributes.setDrawOutline(true);
                normalAttributes.setDrawInterior(false);
                normalAttributes.setEnableLighting(true);

                BasicShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
                highlightAttributes.setOutlineMaterial(material);
                highlightAttributes.setOutlineOpacity(1);
                highlightAttributes.setInteriorMaterial(material);
                highlightAttributes.setInteriorOpacity(0.2);

                polygon.setAltitudeMode(WorldWind.ABSOLUTE);
                polygon.setAttributes(normalAttributes);
                polygon.setHighlightAttributes(highlightAttributes);
                tiles.add(polygon);
            }
            layer.addRenderables(tiles);
            wwd.redrawNow();
        }
        return tiles;
    }

}
