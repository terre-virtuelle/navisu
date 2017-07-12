/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.Geometry;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date Feb 26, 2017
 */
/*
Le controller est un widget
 */
public class S57StlComponentController
        extends Widget2DController
        implements Initializable {

    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected LayerTreeServices layerTreeServices;
    protected LayersManagerServices layersManagerServices;
    protected GuiAgentServices guiAgentServices;

    protected S57StlChartComponentController s57StlChartComponentController;

    protected static final String ALARM_SOUND = "/data/sounds/pling.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    // Le dir navisu-launcher est dans le PATH
    protected String OUT_DIR = "privateData/x3d/";
    // Pour le moment ce nomm est fixe, on ne fait qu'une dalle
    protected String OUT_FILE = "out.x3d";
    protected String OUT_PATH;

    // Pour récupérer le .css
    protected final String FXML = "configurationStlController.fxml";
    protected String VIEW_GROUP_STYLE = "configuration.css";
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    protected List<? extends Position> positions;
    protected double latRangeMetric;
    protected double lonRangeMetric;
    protected double scaleLatFactor;
    protected double scaleLonFactor;
    // Les parametres a initialiser via l'interface
    // Taille de la dalle en mm
    protected double DEFAULT_SIDE = 200.0;
    protected double tileSideX = DEFAULT_SIDE;
    protected double tileSideY = DEFAULT_SIDE;
    // Resolution du MNT

    protected int ptsCountX = 200;
    protected int ptsCountY = 200;
    // Position du socle
    protected double bottom = 0.0;
    // exagération en vertical du MNT
    protected double magnification = 10;
    protected int line;
    protected int column;

    protected double lonRange;
    protected double latRange;
    protected List<Position> squareEnvelopeList;
    protected List<? extends Position> envelopeList;
    protected Geometry geometryEnvelope;
    protected boolean firstShow = true;

    protected WKTReader wktReader;
    protected Geometry geometry;
    protected KMLSurfacePolygonImpl kmlPolygon;
    protected Polygon polygonEnvelope;
    protected Polygon squarePolygonEnvelope;

    protected String GROUP;
    protected String NAME;
    protected RenderableLayer layer;
    protected WorldWindow wwd;

    @FXML
    public Group configGroup;
    @FXML
    public Pane view;
    @FXML
    public ImageView quit;
    @FXML
    public ToggleButton interactiveTB;
    @FXML
    public ToggleButton squareTilesTB;
    @FXML
    public GridPane squareTilesGP;
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
    public Button computeButton;
    @FXML
    public ChoiceBox<String> tileCB;
    @FXML
    public TextField nameTF;
    @FXML
    public TextField sideXTF;
    @FXML
    public TextField sideYTF;

    final ToggleGroup latLonGroup = new ToggleGroup();
    final ToggleGroup eastWestGroup = new ToggleGroup();
    final ToggleGroup northSouthGroup = new ToggleGroup();
    final ToggleGroup interactiveSquareGroup = new ToggleGroup();

    public S57StlComponentController(GuiAgentServices guiAgentServices,
            LayerTreeServices layerTreeServices,
            LayersManagerServices layersManagerServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            S57StlChartComponentController s57StlChartComponentController,
            String GROUP,
            String NAME,
            WorldWindow wwd) {
        this.guiAgentServices = guiAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.s57StlChartComponentController = s57StlChartComponentController;
        this.GROUP = GROUP;
        this.NAME = NAME;
        this.wwd = wwd;
        layer = layersManagerServices.getLayer(GROUP, NAME);

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
                Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            configGroup.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
            Platform.runLater(() -> {
                guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
                guiAgentServices.getRoot().getChildren().add(this);
                firstShow = false;
            });
        }
        displayChartBoundaries(polygon);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileCB.setItems(FXCollections.observableArrayList("1", "4", "9", "16", "25"));
        tileCB.setValue("1");

        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });

        interactiveTB.setToggleGroup(interactiveSquareGroup);
        squareTilesTB.setToggleGroup(interactiveSquareGroup);

        interactiveTB.setOnMouseClicked((MouseEvent event) -> {
            squareTilesGP.setDisable(true);
        });
        squareTilesTB.setOnMouseClicked((MouseEvent event) -> {
            squareTilesGP.setDisable(false);
        });
        squareTilesTB.setSelected(true);

        latLonAllRB.setToggleGroup(latLonGroup);
        latLonAllRB.setSelected(false);
        latRB.setToggleGroup(latLonGroup);
        latRB.setSelected(true);
        /*
        if (eastRB.isSelected()) {
            squareLatEast();
        } else {
            squareLatWest();
        }
         */
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
        latLonAllRB.setOnAction((ActionEvent event) -> {
            System.out.println("setOnAction ");
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

        sideXTF.setOnAction((ActionEvent event) -> {
            if (squareTilesTB.isSelected()) {
                try {
                    tileSideX = Double.parseDouble(sideXTF.getText());
                    sideXTF.setText(Double.toString(tileSideX));
                    tileSideY = tileSideX;
                    sideYTF.setText(Double.toString(tileSideY));
                } catch (NumberFormatException e) {
                    tileSideX = DEFAULT_SIDE;
                    sideXTF.setText(Double.toString(tileSideX));
                }
            }
        });
        sideYTF.setOnAction((ActionEvent event) -> {
            if (squareTilesTB.isSelected()) {
                try {
                    tileSideY = Double.parseDouble(sideYTF.getText());
                    sideYTF.setText(Double.toString(tileSideY));
                    tileSideX = tileSideY;
                    sideXTF.setText(Double.toString(tileSideX));
                } catch (NumberFormatException e) {
                    tileSideY = DEFAULT_SIDE;
                    sideYTF.setText(Double.toString(tileSideY));
                }
            }
        });
        computeButton.setOnMouseClicked((MouseEvent event) -> {
            int tiles = Integer.parseInt(tileCB.getValue());
            line = column = (int) Math.sqrt(tiles);
            OUT_PATH = OUT_DIR + OUT_FILE;
            guiAgentServices.getJobsManager().newJob(OUT_PATH, (progressHandle) -> {
                // forEach dalle
                displayTiles(squarePolygonEnvelope, line, column);
                initParameters();
                s57StlChartComponentController.compute(OUT_DIR, OUT_FILE,
                        scaleLatFactor, scaleLonFactor, magnification,
                        tileSideX, tileSideY,
                        ptsCountX, ptsCountY,
                        bottom,
                        squarePolygonEnvelope, // dalle en WWJ
                        geometryEnvelope);     // dalle en JTS
                instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                // endEach
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
            Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (geom != null) {
            polygonEnvelope = WwjJTS.wktPolygonToPolygon(geom.getEnvelope());
            envelopeList = polygonEnvelope.getBoundaries().get(0);
            lonRange = WwjGeodesy.getDistanceM(envelopeList.get(1), envelopeList.get(0));
            latRange = WwjGeodesy.getDistanceM(envelopeList.get(2), envelopeList.get(1));
            squareEnvelopeList = new ArrayList<>();
            envelopeList.forEach((p) -> {
                squareEnvelopeList.add(p);
            });
        }
    }

    private void squareLatEast() {
        preNewPolygon();
        Position newPosition = WwjGeodesy.getPosition(envelopeList.get(1), 270, latRange * 1000);//270=West
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
        Position newPosition = WwjGeodesy.getPosition(envelopeList.get(0), 90, latRange * 1000);//90=East
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
        Position newPosition = WwjGeodesy.getPosition(envelopeList.get(1), 360, lonRange * 1000);//0=North
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
        Position newPosition = WwjGeodesy.getPosition(envelopeList.get(2), 180, lonRange * 1000);//180=south
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

    private void displayTiles(Polygon polyEnveloppe, int line, int col) {
        Iterable<? extends LatLon> bounds = polyEnveloppe.getOuterBoundary();
        List<LatLon> listLatLon = new ArrayList<>();
        for (LatLon s : bounds) {
            listLatLon.add(s);
        }
        double latRange = listLatLon.get(0).getLatitude().getDegrees() - listLatLon.get(2).getLatitude().getDegrees();
        double lonRange = listLatLon.get(2).getLongitude().getDegrees() - listLatLon.get(0).getLongitude().getDegrees();

        latRange /= line;
        lonRange /= col;
        double orgLat = listLatLon.get(0).getLatitude().getDegrees();
        double orgLon = listLatLon.get(0).getLongitude().getDegrees();

        List<Polygon> tiles = new ArrayList<>();
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

                layer.addRenderable(polygon1);
                wwd.redrawNow();
            }
        }
    }

    private void initParameters() {
        positions = squareEnvelopeList;
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));
        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        scaleLatFactor = tileSideY / latRangeMetric;
        scaleLonFactor = tileSideX / lonRangeMetric;
        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader0 = new WKTReader();

        if (wkt != null) {
            try {
                geometryEnvelope = wktReader0.read(wkt);// l'enveloppe au sens de JTS, pour ne faire clacul q'une fois
                /*
                Geometry geometry = wktReader0.read(wkt);
                CoordinateList list = new CoordinateList(geometry.getCoordinates());
                list.closeRing();
                GeometryFactory geometryFactory = new GeometryFactory();
                LinearRing ring = geometryFactory.createLinearRing(list.toCoordinateArray());
                geometryEnveloppe = geometryFactory.createPolygon(ring, null);
                 */

            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(S57StlComponentController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
