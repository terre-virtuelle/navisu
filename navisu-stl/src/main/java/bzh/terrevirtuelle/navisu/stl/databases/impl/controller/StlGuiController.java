/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

    public void displayGuiGridBM(RenderableLayer layer) {
     
        //Position origPos = new Position(Angle.fromDegrees(48.33713333), Angle.fromDegrees(-4.63665), 100.0);//21/12/18
        Position origPos = new Position(Angle.fromDegrees(48.33748333), Angle.fromDegrees(-4.63665), 100.0);
        double x = 820;
        double y = 820;
        int xNbWest = 0;
        int xNbEast = 29;//27
        int yNbSouth = 5;
        int yNbNorth = 17;//9
        double azimuth = 5.614477142;//5.614477142;
        double azimuthS = 180 - azimuth;
        double bearing = 90 - azimuth;
        Position[][] posTab = new Position[yNbSouth + yNbNorth][xNbWest + xNbEast];
        for (int i = 0; i < yNbSouth; i++) {
            Position position = geodesyServices.getPosition(origPos, azimuthS, y * (yNbSouth - i - 1), 100.0);
            for (int j = 0; j < xNbEast; j++) {
                posTab[i][j] = geodesyServices.getPosition(position, bearing, x * j, 100.0);
            }
        }
        azimuthS = 360 - azimuth;
        for (int i = yNbSouth; i < yNbNorth + yNbSouth; i++) {
            Position position = geodesyServices.getPosition(origPos, azimuthS, y * (i - yNbSouth + 1), 100.0);
            for (int j = 0; j < xNbEast; j++) {
                posTab[i][j] = geodesyServices.getPosition(position, bearing, x * j, 100.0);
            }
        }
        /*
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        for (int i = 0; i < yNbSouth + yNbNorth; i++) {
            for (int j = 0; j < posTab[1].length; j++) {
                PointPlacemark pp =new PointPlacemark(posTab[i][j]);
                pp.setLabelText(i +", "+ j);
                pointPlacemarks.add(pp);
            }
        }
        layer.addRenderables(pointPlacemarks);
         */
        Material material = new Material(Color.MAGENTA);

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(material);
        normalAttributes.setDrawInterior(true);
        normalAttributes.setInteriorOpacity(0.01);
        normalAttributes.setOutlineMaterial(material);

        BasicShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(material);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setInteriorMaterial(material);
        highlightAttributes.setInteriorOpacity(0.2);
        layer.setPickEnabled(true);
        addListener();
        List<Polygon> polygons = new ArrayList<>();
        List<String> exluded = Arrays.asList(
                "55-115", "56-115", "57-115", "58-115", "59-115", "60-115", "61-115", "62-115", "63-115", "64-115", "65-115", "66-115", "67-115", "68-115", "69-115", "70-115",
                "72-115", "73-115", "74-115", "75-115", "76-115", "77-115", "78-115", "79-115", "80-115", "81-115", "82-115", "83-115", "84-115", "85-115",
                "55-114", "56-114", "57-114", "58-114", "59-114", "60-114", "61-114", "62-114", "63-114", "64-114", "65-114", "66-114", "67-114", "68-114", "69-114", "70-114",
                "73-114", "74-114", "75-114", "76-114", "77-114", "78-114", "79-114", "80-114", "81-114", "82-114", "83-114", "84-114", "85-114",
                "55-113", "56-113", "57-113", "58-113", "59-113", "60-113", "61-113", "62-113", "63-113", "64-113", "65-113", "66-113", "69-113", "80-113", "81-113", "82-113", "83-113", "84-113", "85-113",
                "55-112", "56-112", "57-112", "58-112", "59-112", "60-112", "64-112", "82-112",
                "55-111", "56-111", "57-111", "58-111", "59-111", "60-11", "82-111",
                "55-110", "56-110", "57-110", "58-110", "82-110",
                "55-109", "56-109", "57-109", "58-109", "59-109", "82-109",
                "55-108", "56-108", "57-108", "58-108", "82-108",
                "55-106", "60-106",
                "60-105", "81-105", "82-105",
                "55-104", "82-104",
                "55-103", "56-103", "74-103", "75-103", "82-103",
                "55-102", "68-102", "69-102", "70-102", "71-102", "72-102", "73-102", "74-102",
                "55-101", "66-101", "67-101", "68-101", "69-101", "70-101", "71-101", "72-101", "73-101",
                "55-100", "63-100", "65-100", "66-100", "67-100", "68-100", "69-100", "70-100", "71-100", "72-100",
                "55-99", "61-99", "62-99", "63-99", "64-99", "65-99", "66-99", "67-99", "68-99", "69-99", "70-99", "71-99",
                "55-98", "58-98", "59-98", "60-98", "61-98", "62-98", "63-98", "64-98", "65-98", "66-98", "67-98", "68-98", "69-98", "70-98", "71-98", "82-98",
                "55-97", "56-97", "57-97", "58-97", "59-97", "60-97", "61-97", "62-97", "63-97", "64-97", "65-97", "66-97", "67-97", "68-97", "69-97", "70-97", "71-97", "82-97", "85-97",
                "55-96", "56-96", "57-96", "58-96", "59-96", "60-96", "61-96", "62-96", "63-96", "64-96", "65-96", "66-96", "67-96", "68-96", "69-96", "80-96", "81-96", "82-96", "85-96",
                "55-95", "56-95", "57-95", "58-95", "59-95", "60-95", "61-95", "62-95", "63-95", "64-95", "65-95", "66-95", "67-95", "68-95", "69-95", "70-95", "73-95", "74-95", "79-95", "80-95", "81-95", "82-95", "85-95"
        );
        for (int i = 0; i < yNbSouth + yNbNorth - 1; i++) {
            for (int j = 0; j < posTab[1].length - 1; j++) {
                int valX = i + 95;
                int valY = j + 55;
                String label = valY + "-" + valX;
                if (!exluded.contains(label)) {
                    List<Position> positions = new ArrayList<>();
                    positions.add(posTab[i][j]);
                    positions.add(posTab[i][j + 1]);
                    positions.add(posTab[i + 1][j + 1]);
                    positions.add(posTab[i + 1][j]);
                    positions.add(posTab[i][j]);
                    Polygon polygon = new Polygon(positions);
                    polygon.setAltitudeMode(WorldWind.ABSOLUTE);
                    polygon.setAttributes(normalAttributes);
                    polygon.setHighlightAttributes(highlightAttributes);
                    polygon.setValue("Key", label);
                    polygon.setValue(AVKey.DISPLAY_NAME, label);
                    polygons.add(polygon);
                }
            }
        }
        layer.addRenderables(polygons);
        wwd.redrawNow();
    }

    private void addListener() {
        Material material = new Material(Color.MAGENTA);
        ShapeAttributes pickedAttributes = new BasicShapeAttributes();
        pickedAttributes.setInteriorMaterial(material);
        pickedAttributes.setDrawInterior(true);
        pickedAttributes.setInteriorOpacity(1.0);
        pickedAttributes.setOutlineMaterial(material);
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            // System.out.println("o : "+o);
            if (event.isLeftClick() && o != null) {
                if (o.getClass() == Polygon.class) {
                    Polygon polygon = ((Polygon) o);
                    System.out.println((polygon).getBoundaries());
                    polygon.setAttributes(pickedAttributes);
                    polygon.setHighlightAttributes(pickedAttributes);
                    wwd.redrawNow();
                }
            }
        });
    }
}
