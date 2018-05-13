/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.controller;

import bzh.terrevirtuelle.navisu.stl.charts.impl.controller.charts.StlChartController;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetryServices;
import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.charts.impl.controller.bathy.depare.BathyDepareStlController;
import bzh.terrevirtuelle.navisu.stl.charts.impl.StlChartComponentImpl;
import bzh.terrevirtuelle.navisu.stl.charts.impl.loader.bathy.BathyElevationLoader;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.CoordinateList;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.kml.impl.KMLSurfacePolygonImpl;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwindx.examples.util.SectorSelector;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date Feb 26, 2017
 */
/*
Leader of controllers, via gui switch between different controllers
 */
public class StlChartComponentController
        extends Widget2DController
        implements Initializable {

    // Parent compoent
    protected StlChartComponentImpl component;
    // Services
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected LayerTreeServices layerTreeServices;
    protected LayersManagerServices layersManagerServices;
    protected GuiAgentServices guiAgentServices;
    protected GeodesyServices geodesyServices;
    protected BathymetryDBServices bathymetryDBServices;
    protected DisplayBathymetryServices displayBathymetryServices;

    protected StlChartController s57StlChartComponentController;

    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    // Pour récupérer le .css
    protected final String FXML = "configurationStlController.fxml";
    //protected String VIEW_GROUP_STYLE = "configuration.css";
    protected String VIEW_GROUP_STYLE = "common.css";
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    // Le dir navisu-launcher est dans le PATH
    protected String OUT_DIR = "privateData/x3d/";
    protected String OUT_FILE = "out";
    protected String outFile = OUT_FILE;
    protected Path outPathname;

    // Les parametres a initialiser via l'interface
    // Taille de la dalle en mm
    protected List<? extends Position> positions;
    protected double latRangeMetric;
    protected double DEFAULT_SIDE = 200.0;
    protected double tileSideX = DEFAULT_SIDE;
    protected double tileSideY = DEFAULT_SIDE;
    protected double earthSpaceX;
    protected double earthSpaceY;
    protected double lonRangeMetric;
    // Position du socle
    protected double bottom = -0.1;
    // exagération en vertical du MNT
    protected double magnification = 10;
    protected int line;
    protected int column;
    protected int tilesCount;
    protected List<Polygon> tiles;
    protected double lonRange;
    protected double latRange;
    protected List<Polygon> wwjTiles;
    protected List<Position> squareEnvelopeList;
    protected List<? extends Position> envelopeList;
    protected Geometry geometryEnvelope;
    protected boolean firstShow = true;
    protected WKTReader wktReader;
    protected Geometry geometry;
    protected KMLSurfacePolygonImpl kmlPolygon;
    protected Polygon polygonEnvelope;
    protected Polygon squarePolygonEnvelope;
    protected RenderableLayer layer;
    protected RenderableLayer layerBathy;
    protected WorldWindow wwd;
    protected double buoyageScale;
    protected String title;

    @FXML
    public Group configGroup;
    @FXML
    public Pane quit;
    @FXML
    public ChoiceBox<String> choiceCB;
    @FXML
    public RadioButton miRB;
    @FXML
    public RadioButton previewRB;
    @FXML
    public RadioButton tilesRB;
    @FXML
    public RadioButton latLonAllRB;
    @FXML
    public RadioButton latRB;
    @FXML
    public RadioButton lonRB;
    @FXML
    public RadioButton eastRB;
    @FXML
    public RadioButton westRB;
    @FXML
    public RadioButton northRB;
    @FXML
    public RadioButton southRB;
    @FXML
    public RadioButton latLonRB;
    @FXML
    public RadioButton lonLatRB;
    @FXML
    public Button generationButton;
    @FXML
    public ChoiceBox<Integer> countTilesCB;
    @FXML
    public TextField nameTF;
    @FXML
    public TextField magnificationTF;
    @FXML
    public TextField spaceXTF;
    @FXML
    public TextField spaceYTF;
    @FXML
    public TextField sideXTF;
    @FXML
    public TextField sideYTF;
    @FXML
    public TextField buoyScaleTF;
    @FXML
    public CheckBox baseCB;
    @FXML
    public TextField titleTF;

    final ToggleGroup latLonGroup = new ToggleGroup();
    final ToggleGroup eastWestGroup = new ToggleGroup();
    final ToggleGroup northSouthGroup = new ToggleGroup();
    final ToggleGroup interactiveSquareGroup = new ToggleGroup();
    final ToggleGroup latLonFileGroup = new ToggleGroup();
    protected KeyCode keyCode;
    protected SectorSelector selector;

    public StlChartComponentController(StlChartComponentImpl component,
            GuiAgentServices guiAgentServices,
            LayerTreeServices layerTreeServices,
            LayersManagerServices layersManagerServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            GeodesyServices geodesyServices,
            BathymetryDBServices bathymetryDBServices,
            DisplayBathymetryServices displayBathymetryServices,
            StlChartController s57StlChartComponentController,
            String GROUP,
            String NAME,
            WorldWindow wwd) {
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.geodesyServices = geodesyServices;
        this.bathymetryDBServices = bathymetryDBServices;
        this.displayBathymetryServices = displayBathymetryServices;
        this.s57StlChartComponentController = s57StlChartComponentController;
        this.wwd = wwd;
        layer = layersManagerServices.getLayer(GROUP, NAME);
        layerBathy = layersManagerServices.getLayer(GROUP, NAME + "Bathy");
        this.selector = new SectorSelector(wwd);
        this.selector.setInteriorColor(new Color(1f, 1f, 1f, 0.1f));
        this.selector.setBorderColor(new Color(1f, 0f, 0f, 0.5f));
        this.selector.setBorderWidth(3);
        //  Pour le futur, la couche OSM
        // Layer buildings = new OSMBuildingsStlLayer();
        //  layerTreeServices.addGeoLayer("Buildings", buildings);
        // wwd.getModel().getLayers().add(buildings);
        
    }

    public void showGUI(KMLSurfacePolygonImpl polygon) {

        if (firstShow == true) {
            this.kmlPolygon = polygon;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(StlChartComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            configGroup.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
            Platform.runLater(() -> {
                guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
                guiAgentServices.getRoot().getChildren().add(this);
                firstShow = false;
            });
        }
        displayChartBoundaries(polygon);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            keyCode = event.getCode();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countTilesCB.setItems(FXCollections.observableArrayList(1, 4, 9, 16, 25));
        countTilesCB.setValue(1);
        choiceCB.setItems(FXCollections.observableArrayList("MNTElevation&Carto",
                "MNTElevation",
                "Carto",
                "BathyMNT",
                "BathyDEPARE"));
        choiceCB.setValue("MNTElevation&Carto");
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        nameTF.setText(OUT_FILE);
        nameTF.setOnAction((ActionEvent event) -> {
            outFile = nameTF.getText();
            String[] outTab = outFile.split("\\.");
            outFile = outTab[0];
            nameTF.setText(outTab[0]);
        });
        magnificationTF.setOnAction((ActionEvent event) -> {
            try {
                magnification = Double.valueOf(magnificationTF.getText());
            } catch (NumberFormatException e) {
                magnification = 1.0;
            }
        });
        miRB.setToggleGroup(interactiveSquareGroup);
        tilesRB.setToggleGroup(interactiveSquareGroup);
        miRB.setDisable(true);
        tilesRB.setSelected(true);
        latLonAllRB.setToggleGroup(latLonGroup);
        latLonAllRB.setSelected(false);
        latLonAllRB.setDisable(true);
        latRB.setToggleGroup(latLonGroup);
        latRB.setSelected(true);
        lonRB.setToggleGroup(latLonGroup);
        lonRB.setSelected(false);
        eastRB.setToggleGroup(eastWestGroup);
        eastRB.setSelected(true);
        westRB.setToggleGroup(eastWestGroup);
        westRB.setSelected(false);
        northRB.setToggleGroup(northSouthGroup);
        northRB.setSelected(true);
        southRB.setToggleGroup(northSouthGroup);
        southRB.setSelected(false);
        latLonRB.setToggleGroup(latLonFileGroup);
        lonLatRB.setToggleGroup(latLonFileGroup);
        lonLatRB.setSelected(true);
        latLonRB.setDisable(true);
        lonLatRB.setDisable(true);
        countTilesCB.setOnAction((ActionEvent event) -> {
            tilesCount = countTilesCB.getValue();
            line = column = (int) Math.sqrt(tilesCount);
            wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
        });
        latLonAllRB.setOnAction((ActionEvent event) -> {
            System.out.println("Not yet implemented ");
        });
        latRB.setOnAction((ActionEvent event) -> {
            if (eastRB.isSelected()) {
                squareLatEast();
            } else {
                squareLatWest();
            }
        });
        lonRB.setOnAction((ActionEvent event) -> {
            if (northRB.isSelected()) {
                squareLonNorth();
            } else {
                squareLonSouth();
            }
        });
        eastRB.setOnAction((ActionEvent event) -> {
            if (latRB.isSelected()) {
                squareLatEast();
            }
        });
        westRB.setOnAction((ActionEvent event) -> {
            if (latRB.isSelected()) {
                squareLatWest();
            }
        });
        northRB.setOnAction((ActionEvent event) -> {
            if (lonRB.isSelected()) {
                squareLonNorth();
            }
        });
        southRB.setOnAction((ActionEvent event) -> {
            if (lonRB.isSelected()) {
                squareLonSouth();
            }
        });

        spaceXTF.setOnAction((ActionEvent event) -> {
            try {
                earthSpaceX = Double.parseDouble(spaceXTF.getText());
                spaceXTF.setText(Double.toString(earthSpaceX));

            } catch (NumberFormatException e) {
                earthSpaceX = DEFAULT_SIDE / 2;
                spaceXTF.setText(Double.toString(earthSpaceX));
            }

        });
        spaceYTF.setOnAction((ActionEvent event) -> {

            try {
                earthSpaceY = Double.parseDouble(spaceYTF.getText());
                spaceYTF.setText(Double.toString(earthSpaceY));

            } catch (NumberFormatException e) {
                earthSpaceY = DEFAULT_SIDE / 2;
                spaceYTF.setText(Double.toString(earthSpaceY));
            }

        });
        sideXTF.setOnAction((ActionEvent event) -> {

            try {
                tileSideX = Double.parseDouble(sideXTF.getText());
                sideXTF.setText(Double.toString(tileSideX));

            } catch (NumberFormatException e) {
                tileSideX = DEFAULT_SIDE;
                sideXTF.setText(Double.toString(tileSideX));
            }

        });
        sideYTF.setOnAction((ActionEvent event) -> {

            try {
                tileSideY = Double.parseDouble(sideYTF.getText());
                sideYTF.setText(Double.toString(tileSideY));

            } catch (NumberFormatException e) {
                tileSideY = DEFAULT_SIDE;
                sideYTF.setText(Double.toString(tileSideY));
            }

        });
        choiceCB.setOnAction((ActionEvent event) -> {
            if (choiceCB.getSelectionModel().getSelectedItem().equals("BathyDEPARE")) {
                latLonRB.setDisable(false);
                lonLatRB.setDisable(false);
            } else {
                latLonRB.setDisable(true);
                lonLatRB.setDisable(true);
            }
        });
        generationButton.setOnMouseClicked((MouseEvent event) -> {
            title = titleTF.getText();
            tilesCount = countTilesCB.getValue();
            line = column = (int) Math.sqrt(tilesCount);
            earthSpaceX = Double.parseDouble(spaceXTF.getText());
            spaceXTF.setText(Double.toString(earthSpaceX));
            earthSpaceY = Double.parseDouble(spaceYTF.getText());
            spaceYTF.setText(Double.toString(earthSpaceY));
            try {
                magnification = Double.valueOf(magnificationTF.getText());
            } catch (NumberFormatException e) {
                magnification = 1.0;
            }
            try {
                buoyageScale = Double.valueOf(buoyScaleTF.getText());
            } catch (NumberFormatException e) {
                buoyageScale = 1.0;
            }
            boolean base = baseCB.isSelected();
            //  wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
            guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
                wwjTiles = displayTiles(squarePolygonEnvelope, line, column);
                // forEach tile
                Date date = new Date();
                String dateString = new SimpleDateFormat("dd:MMM:yyyy").format(date);

                for (int i = 0; i < tilesCount; i++) {
                    Geometry geom = initParameters(wwjTiles.get(i).getBoundaries());
                    if (choiceCB.getSelectionModel().getSelectedItem().equals("BathyDEPARE")) {
                        outFile = nameTF.getText() + "_" + dateString + "_" + i + 1 + ":" + tilesCount + ".csv";
                        outPathname = Paths.get(OUT_DIR, outFile);
                        BathyDepareStlController bathyDepareStlController
                                = new BathyDepareStlController(bathymetryDBServices,
                                        displayBathymetryServices,
                                        guiAgentServices,
                                        layer,
                                        squarePolygonEnvelope);
                        //Max de profondeur pour l'ensemble des tuiles
                        double maxElevation = bathyDepareStlController.getMaxElevation();

                        bathyDepareStlController.writePointList(positions, outPathname, latLonRB.isSelected());
                        if (previewRB.isSelected()) {
                            bathyDepareStlController.displayDelaunaySounding(positions, layerBathy, maxElevation);
                        }
                    } else {
                        //if sur bathy ou mnt
                        outFile = nameTF.getText() + "_" + i + ".x3d";
                        outPathname = Paths.get(OUT_DIR, outFile);

                        new StlPreWriterController(outPathname, title,
                                tilesCount, i,
                                positions,
                                tileSideX, tileSideY,
                                earthSpaceX, earthSpaceY,
                                bottom, magnification, 0.0).compute();
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation")
                                || choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation&Carto")) {
                            new ElevationStlController(outPathname, title,
                                    tilesCount, i,
                                    geodesyServices,
                                    positions,
                                    tileSideX, tileSideY,
                                    earthSpaceX, earthSpaceY,
                                    bottom, magnification, 0.0
                            ).compute();
                        }
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("Carto")
                                || choiceCB.getSelectionModel().getSelectedItem().equals("MNTElevation&Carto")) {
                            s57StlChartComponentController.compute(outPathname,
                                    tilesCount,
                                    i,
                                    magnification,
                                    tileSideX, tileSideY,
                                    earthSpaceX, earthSpaceY,
                                    bottom,
                                    polygonEnvelope,
                                    geometryEnvelope);
                        }
                        if (choiceCB.getSelectionModel().getSelectedItem().equals("BathyMNT")) {
                            new BathyElevationLoader(outPathname, 
                                    title,
                                    tilesCount, i,
                                    geodesyServices,
                                    positions,
                                    tileSideX, tileSideY,
                                    earthSpaceX, earthSpaceY,
                                    bottom, magnification, 0.0
                            ).compute();
                        }

                        new StlPostWriterController(outPathname).compute();
                    }
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                    wwjTiles.get(i).setAttributes(makeHighlightAttributes());
                    wwd.redrawNow();
                }
            });
        });
    }

    public void displayChartBoundaries(KMLSurfacePolygonImpl polygon) {
        String result = WwjJTS.locationsToWKT(polygon.getLocations());
        WKTReader wkt = new WKTReader();
        Geometry geom = null;
        try {
            geom = wkt.read(result);
        } catch (ParseException ex) {
            Logger.getLogger(StlChartComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (geom != null) {
            polygonEnvelope = WwjJTS.wktPolygonToPolygon(geom.getEnvelope());
            envelopeList = polygonEnvelope.getBoundaries().get(0);
            lonRange = geodesyServices.getDistanceM(envelopeList.get(1), envelopeList.get(0));
            latRange = geodesyServices.getDistanceM(envelopeList.get(2), envelopeList.get(1));
            squareEnvelopeList = new ArrayList<>();
            envelopeList.forEach((p) -> {
                squareEnvelopeList.add(p);
            });
        }
        squareLatEast();
    }

    private void squareLatEast() {
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(1), 270, latRange);//270=West
        squareEnvelopeList.remove(0);
        squareEnvelopeList.add(0, newPosition);
        Position newP3 = new Position(squareEnvelopeList.get(3).getLatitude(),
                newPosition.getLongitude(), 100.0);
        squareEnvelopeList.remove(3);
        squareEnvelopeList.add(3, newP3);
        squareEnvelopeList.remove(4);
        squareEnvelopeList.add(4, newPosition);
        postNewPolygon();
    }

    private void squareLatWest() {
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(0), 90, latRange);//90=East
        squareEnvelopeList.remove(1);
        squareEnvelopeList.add(1, newPosition);
        Position newP = new Position(squareEnvelopeList.get(3).getLatitude(),
                newPosition.getLongitude(), 100.0);
        squareEnvelopeList.remove(2);
        squareEnvelopeList.add(2, newP);
        postNewPolygon();
    }

    private void squareLonNorth() {
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(1), 360, lonRange);//0=North
        squareEnvelopeList.remove(2);
        squareEnvelopeList.add(2, newPosition);
        Position newP3 = new Position(newPosition.getLatitude(),
                squareEnvelopeList.get(0).getLongitude(), 100.0);
        squareEnvelopeList.remove(3);
        squareEnvelopeList.add(3, newP3);
        postNewPolygon();
    }

    private void squareLonSouth() {
        preNewPolygon();
        Position newPosition = geodesyServices.getPosition(envelopeList.get(2), 180, lonRange);//180=south
        squareEnvelopeList.remove(1);
        squareEnvelopeList.add(1, newPosition);
        Position newP = new Position(newPosition.getLatitude(),
                squareEnvelopeList.get(0).getLongitude(), 100.0);
        squareEnvelopeList.remove(0);
        squareEnvelopeList.add(0, newP);
        squareEnvelopeList.remove(4);
        squareEnvelopeList.add(4, newP);
        postNewPolygon();
    }

    private void preNewPolygon() {
        squareEnvelopeList = new ArrayList<>();
        envelopeList.forEach((p) -> {
            squareEnvelopeList.add(p);
        });
    }

    private void postNewPolygon() {
        if (squarePolygonEnvelope != null) {
            layer.removeRenderable(squarePolygonEnvelope);
        }
        squarePolygonEnvelope = new Polygon(squareEnvelopeList);
        squarePolygonEnvelope.setAttributes(makeAttributes());
        layer.addRenderable(squarePolygonEnvelope);
        wwd.redrawNow();
    }

    private ShapeAttributes makeAttributes() {
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(Material.GREEN);
        normalAttributes.setOutlineOpacity(0.2);
        normalAttributes.setInteriorOpacity(0.2);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(true);
        return normalAttributes;
    }

    private ShapeAttributes makeHighlightAttributes() {
        ShapeAttributes highlightAttributes = new BasicShapeAttributes();
        highlightAttributes.setOutlineMaterial(Material.RED);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setInteriorMaterial(Material.RED);
        highlightAttributes.setInteriorOpacity(1);
        return highlightAttributes;
    }

    private List<Polygon> displayTiles(Polygon polyEnveloppe, int line, int col) {
        layer.removeAllRenderables();
        Iterable<? extends LatLon> bounds = polyEnveloppe.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }
        latRange = listLatLon.get(0).getLatitude().getDegrees() - listLatLon.get(2).getLatitude().getDegrees();
        lonRange = listLatLon.get(2).getLongitude().getDegrees() - listLatLon.get(0).getLongitude().getDegrees();

        latRange /= line;
        lonRange /= col;
        double orgLat = listLatLon.get(0).getLatitude().getDegrees();
        double orgLon = listLatLon.get(0).getLongitude().getDegrees();

        tiles = new ArrayList<>();
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                List<Position> positions0 = new ArrayList<>();
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + (j + 1) * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - (i + 1) * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                positions0.add(new Position(Angle.fromDegrees(orgLat - i * latRange), Angle.fromDegrees(orgLon + j * lonRange), 100.0));
                Polygon polygon1 = new Polygon(positions0);

                ShapeAttributes normalAttributes = new BasicShapeAttributes();
                normalAttributes.setInteriorMaterial(Material.RED);
                normalAttributes.setOutlineOpacity(0.5);
                normalAttributes.setInteriorOpacity(0.2);
                normalAttributes.setOutlineMaterial(Material.RED);
                normalAttributes.setOutlineWidth(2);
                normalAttributes.setDrawOutline(true);
                normalAttributes.setDrawInterior(true);
                normalAttributes.setEnableLighting(true);

                ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
                highlightAttributes.setOutlineMaterial(Material.RED);
                highlightAttributes.setOutlineOpacity(1);
                highlightAttributes.setInteriorMaterial(Material.RED);
                highlightAttributes.setInteriorOpacity(0.2);

                polygon1.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
                polygon1.setAttributes(normalAttributes);
                polygon1.setHighlightAttributes(highlightAttributes);
                tiles.add(polygon1);
            }
            layer.addRenderables(tiles);
            wwd.redrawNow();
        }
        return tiles;
    }

    private Geometry initParameters(List<List<? extends Position>> pos) {
        Geometry geometry1 = null;
        positions = pos.get(0);
        latRangeMetric = geodesyServices.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));
        lonRangeMetric = geodesyServices.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        //   scaleLatFactor = (earthSpaceY) / latRangeMetric;
        //   scaleLonFactor = (earthSpaceX) / lonRangeMetric;
        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader0 = new WKTReader();

        if (wkt != null) {
            try {
                geometry1 = wktReader0.read(wkt);// l'enveloppe au sens de JTS, pour ne faire clacul q'une fois

                Geometry geometry = wktReader0.read(wkt);
                CoordinateList list = new CoordinateList(geometry.getCoordinates());
                list.closeRing();
                GeometryFactory geometryFactory = new GeometryFactory();
                LinearRing ring = geometryFactory.createLinearRing(list.toCoordinateArray());
                geometryEnvelope = geometryFactory.createPolygon(ring, null);

            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(StlChartComponentController.class
                        .getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return geometry1;
    }
}
