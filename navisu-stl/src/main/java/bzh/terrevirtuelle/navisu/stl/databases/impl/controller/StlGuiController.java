/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.util.io.IO;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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

    protected TextField objectsTF;
    protected Map<String, CheckBox> s57SelectionMap;
    protected List<String> selectedObjects;
    protected CheckBox allCB;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public StlGuiController(GeodesyServices geodesyServices,
            Map<String, CheckBox> s57SelectionMap, CheckBox allCB,
            List<String> selectedObjects,
            TextField objectsTF) {
        this.geodesyServices = geodesyServices;
        this.s57SelectionMap = s57SelectionMap;
        this.allCB = allCB;
        this.selectedObjects = selectedObjects;
        this.objectsTF = objectsTF;
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

    public void initS57Gui() {
        s57SelectionMap.keySet().forEach((cb) -> {
            s57SelectionMap.get(cb).setOnAction((ActionEvent event) -> {
                initS57Gui(cb);
            });
        });
    }

    public void initS57Gui(String selected) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!selectedObjects.contains("ALL")) {
                    if (selected.contains("ALL")) {
                        selectedObjects.clear();
                        selectedObjects.add("ALL");
                        objectsTF.setText("");
                        for (String s : s57SelectionMap.keySet()) {
                            objectsTF.appendText(s + " ; ");
                            s57SelectionMap.get(s).setSelected(true);
                        }
                    } else {
                        if (selectedObjects.contains(selected)) {
                            selectedObjects.remove(selected);
                            objectsTF.setText(objectsTF.getText().replace(selected + " ; ", ""));
                        } else {
                            selectedObjects.add(selected);
                            objectsTF.appendText(selected + " ; ");
                        }
                    }
                } else {//selectedObjects.contains("ALL")
                    if (selected.contains("ALL")) {
                        selectedObjects.clear();
                        selectedObjects.remove("ALL");
                        objectsTF.setText("");
                        for (String s : s57SelectionMap.keySet()) {;
                            s57SelectionMap.get(s).setSelected(false);
                        }
                    } else {
                        selectedObjects.remove("ALL");
                        objectsTF.setText("");
                        allCB.setSelected(false);
                        for (String s : s57SelectionMap.keySet()) {
                            objectsTF.appendText(s + " ; ");
                            selectedObjects.add(s);
                        }
                        if (selectedObjects.contains(selected)) {
                            selectedObjects.remove(selected);
                            s57SelectionMap.get(selected).setSelected(false);
                            objectsTF.setText(objectsTF.getText().replace(selected + " ; ", ""));
                        } else {
                            selectedObjects.add(selected);
                            objectsTF.appendText(selected + " ; ");
                        }
                    }
                }
                // System.out.println("selectedObjects : " + selectedObjects);
            }

        });

    }

    public Pair<Double, Double> getLatLonKML(String filename) {
        Pair<Double, Double> result = null;
        String latLonString = "";
        String latString = "";
        String lonString = "";
        double latitude = 0.0;
        double longitude = 0.0;
/*
        try {
                tileNumberU = Integer.parseInt(tileNumberUTF.getText());
                tileNumberV = Integer.parseInt(tileNumberVTF.getText());
                kmlLat = Double.parseDouble(kmlLatTF.getText());
                kmlLon = Double.parseDouble(kmlLonTF.getText()); 
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tile number or KML location is empty");
                alert.show();
            }
            if (tileNumberU == 0 || tileNumberV == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tile number is equal at zero");
                alert.show();
            } else {
                File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl", "Georeferenced STL files (*.stl)", "*.STL", "*.stl");
                if (file != null) {
                    Pair pair = new Pair(tileNumberU, tileNumberV);
                    if (kmlObjectMap.get(pair) == null) {
                        kmlObjectMap.put(pair, new ArrayList<>());
                    }
                    kmlObjectMap.get(pair).add(file.getAbsolutePath());
                    kmlObjectLocationMap.put(file.getAbsolutePath(), new Pair(kmlLat, kmlLon));
                }
            }
        
        */
        
        
        
        
        
        try {
            latLonString = new String(Files.readAllBytes(Paths.get("data/stl/buildings/portzic/doc.kml")));
        } catch (IOException ex) {
            Logger.getLogger(StlGuiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] latLonTab = latLonString.split("\n");
        boolean match = false;
        for (int i = 0; i < latLonTab.length; i++) {
            if (latLonTab[i].contains("<Model>")) {
                while (!latLonTab[i].contains("<latitude>")) {
                    i++;
                }
                latString = latLonTab[i];
                lonString = latLonTab[i + 1];
                match = true;
            }
        }
        if (match == true) {
            latString = latString.replace("<latitude>", "");
            lonString = lonString.replace("<longitude>", "");
            latitude = Double.parseDouble(latString);
            longitude = Double.parseDouble(lonString);
        }
        double elevationModel = wwd.getModel().getGlobe().getElevation(Angle.fromDegreesLatitude(latitude),
                Angle.fromDegreesLongitude(longitude));
        return result;
    }
}
