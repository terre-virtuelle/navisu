/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.util.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.SingleAREA_ShapefileLoader;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.Group;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class SlConsEditorController
        extends Widget2DController {

    private final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected final String SEP = File.separator;
    String USER_DIR = System.getProperty("user.dir");
    private final String HISTOLITT = USER_DIR + SEP + "data" + SEP + "shapefile" + SEP + "histolitt" + SEP + "france.shp";
    private final String FXML = "slconseditor.fxml";
    protected String viewgroupstyle = "configuration.css";
    private MeasureTool measureTool;

    private List<Position> positions;

    private int size;
    private String routeName;
    private String author;
    private String version;
    private float elevation;
    private final double MIN_DISTANCE = 0.1; // minimal distance between 2 Wp
    List<ShapefileRecord> records;

    private Polygon offset;

    private final GeodeticCalculator geoCalc;
    private final Ellipsoid reference = Ellipsoid.WGS84;//default
    private final double KM_TO_NAUTICAL = 0.53879310;
    private final double KM_TO_METER = 1000;
    private GlobalCoordinates waypointA;
    private GlobalCoordinates waypointB;

    private final WorldWindow wwd;
    protected RenderableLayer selectLayer;
    Polygon selectPolygon;
    BasicShapeAttributes normalAttributes;

    protected DriverManagerServices driverManagerServices;
    protected GuiAgentServices guiAgentServices;
    protected LayersManagerServices layersManagerServices;
    protected ShapefileObjectServices shapefileObjectServices;
    protected TopologyServices topologyServices;

    @FXML
    public Group view;
    @FXML
    public Pane viewpane;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public ComboBox unitsCombo;
    @FXML
    public ComboBox anglesCombo;
    @FXML
    public CheckBox showControlsCheck;
    @FXML
    public CheckBox showAnnotationCheck;
    @FXML
    public Button newButton;
    @FXML
    public Button pauseButton;
    @FXML
    public Button endButton;
    @FXML
    public Button openButton;
    @FXML
    public Button snapshotButton;
    @FXML
    public Button dataButton;
    @FXML
    public TextField lengthText;
    @FXML
    public TextField totalLengthText;
    @FXML
    public TextField headingText;
    @FXML
    public TextField namesText;
    @FXML
    public TextField wpNameText;
    @FXML
    public TextField wpDescText;
    @FXML
    public TextField routeNameText;
    @FXML
    public TextField versionText;
    @FXML
    public TextField authorText;
    @FXML
    public TextField elevationTF;

    public SlConsEditorController(
            GuiAgentServices guiAgentServices,
            LayersManagerServices layersManagerServices,
            ShapefileObjectServices shapefileObjectServices,
            DriverManagerServices driverManagerServices,
            TopologyServices topologyServices,
            RenderableLayer selectLayer,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.layersManagerServices = layersManagerServices;
        this.guiAgentServices = guiAgentServices;
        this.shapefileObjectServices = shapefileObjectServices;
        this.driverManagerServices = driverManagerServices;
        this.topologyServices = topologyServices;
        this.selectLayer = selectLayer;
        wwd = GeoWorldWindViewImpl.getWW();
        geoCalc = new GeodeticCalculator();

        load(FXML);
        initPanel();
        setTranslateX(280.0);
    }

    final void load(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);

        viewpane.setOpacity(0.8);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(opacitySlider.getValue());
            });
        });
    }

    @SuppressWarnings("unchecked")
    private void initPanel() {
        elevation = Float.parseFloat(elevationTF.getText());

        initMeasureTool();
        newButton.setOnMouseClicked((MouseEvent event) -> {
            newAction();
        });

        openButton.setOnMouseClicked((MouseEvent event) -> {
            initMeasureTool();
            newAction();
            shapefileObjectServices.openFile(HISTOLITT);
            Shapefile shape = shapefileObjectServices.getShapefile();
            records = shapefileObjectServices.getRecords();
            measureTool.setArmed(!measureTool.isArmed());
            //  fillMesureTool();
        });
        pauseButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool.setArmed(!measureTool.isArmed());
            Platform.runLater(() -> {
                pauseButton.setText(!measureTool.isArmed() ? "Resume" : "Pause");
                pauseButton.disarm();
            });
            ((Component) wwd).setCursor(!measureTool.isArmed() ? Cursor.getDefaultCursor()
                    : Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
            if (measureTool != null) {
                measureTool.clear();
                measureTool.setArmed(false);
                if (offset != null) {
                    measureTool.getLayer().removeRenderable(offset);
                }
            }
        });
        endButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool.setArmed(false);
            selectingArea();
            List<Geometry> geometries = recordsScan(records);
            Geometry geom = selectingArea();
            List<Geometry> selectdGeom = topologyServices.within(geom, geometries);
            List<Layer> layers = shapefileObjectServices.getLayers();
            for (Layer l : layers) {
                l.dispose();
            }
            wwd.redrawNow();
          //  System.out.println(selectdGeom);
            List<Path> paths = topologyServices.jtsLineString2Path(selectdGeom);
            for (Path p : paths) {
                p.setAttributes(normalAttributes);
            }
            // selectLayer.removeRenderable(selectPolygon);
            selectLayer.dispose();
            selectLayer.addRenderables(paths);
            wwd.redrawNow();
        });

        snapshotButton.setOnMouseClicked((MouseEvent event) -> {
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Not yet implemented");
                    //  new ScreenShotAction(GeoWorldWindViewImpl.getWW(), instrument.getGuiAgentServices().getStage());

                }
            });
        });

        routeNameText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                routeName = routeNameText.getText();
            }
        });
        versionText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                version = versionText.getText();
            }
        });
        authorText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                author = authorText.getText();
            }
        });
        wpNameText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                //  track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().get(i)
            }
        });
        wpDescText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                //  route.getRtept().get(route.getRtept().size() - 1).setDesc(newvalue);
            }
        });
        elevationTF.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                elevation = Float.parseFloat(newvalue);
            }
        });

        unitsCombo.setOnAction((event) -> {
            String item = (String) unitsCombo.getSelectionModel().getSelectedItem();
            switch (item.trim()) {
                case "M":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.METERS);
                    break;
                case "Km":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
                    break;
                case "Feet":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.FEET);
                    break;
                case "Miles":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.MILES);
                    break;
                case "Nm":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.NAUTICAL_MILES);
                    break;
                case "Yards":
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.YARDS);
                    break;
                default:
                    break;
            }
        });
    }

    private void initMeasureTool() {
        measureTool = new MeasureTool(wwd);
        measureTool.setController(new MeasureToolController());
        measureTool.setMeasureShapeType(MeasureTool.SHAPE_PATH);
        measureTool.setPathType(AVKey.GREAT_CIRCLE);
        measureTool.setFollowTerrain(true);
        measureTool.setShowAnnotation(true);
        measureTool.setShowControlPoints(true);
        measureTool.setLineColor(Color.RED);
        measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.NAUTICAL_MILES);
        // Handle measure tool events
        measureTool.addPropertyChangeListener((PropertyChangeEvent event) -> {
            switch (event.getPropertyName()) {
                case MeasureTool.EVENT_POSITION_ADD:
                case MeasureTool.EVENT_POSITION_REPLACE:
                    Platform.runLater(() -> {
                        wpNameText.clear();
                        wpDescText.clear();
                    });
                    fillPointsPanel();    // Update position list when changed
                    break;
                case MeasureTool.EVENT_ARMED:
                    if (measureTool.isArmed()) {
                        Platform.runLater(() -> {
                            newButton.disarm();
                            pauseButton.setText("Pause");
                            pauseButton.arm();
                            endButton.arm();
                            ((Component) wwd).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        });
                    } else {
                        Platform.runLater(() -> {
                            newButton.arm();
                            pauseButton.setText("Pause");
                            pauseButton.disarm();
                            endButton.disarm();
                            ((Component) wwd).setCursor(Cursor.getDefaultCursor());
                        });
                    }
                    break;
                case MeasureTool.EVENT_METRIC_CHANGED:
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void newAction() {
        routeName = routeNameText.getText();
        version = versionText.getText();
        author = authorText.getText();
        positions = new CopyOnWriteArrayList();
        /*
        gpx = GpxBuilder.create()
                .creator(author)
                .version(version)
                .trk(tracks)
                .build();
         */
        measureTool.clear();
        measureTool.setArmed(true);
    }

    private void fillPointsPanel() {
        int length = measureTool.getPositions().size();
        Position pos = measureTool.getPositions().get(length - 1);
        String las = String.format("Lat %7.4f\u00B0", pos.getLatitude().getDegrees());
        String los = String.format("Lon %7.4f\u00B0", pos.getLongitude().getDegrees());
        Platform.runLater(() -> {
            namesText.setText(las + "  " + los + "\n");
            if (length > 1) {
                totalLengthText.setText(String.format("%.2f", measureTool.getLength() * 0.000539956803));
                lengthText.setText(String.format("%.2f", (getDistanceNm(measureTool.getPositions().get(length - 2), measureTool.getPositions().get(length - 1)))));
                headingText.setText(String.format("%.1f", (getAzimuth(measureTool.getPositions().get(length - 2), measureTool.getPositions().get(length - 1)))));
            }
        });
    }

    private double getDistanceNm(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getEllipsoidalDistance() / KM_TO_METER * KM_TO_NAUTICAL;
    }

    private double getAzimuth(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getAzimuth();
    }

    public void shapeScan(Shapefile shp) {
        System.out.println("shp : " + shp.getNumberOfRecords());
        ShapefileRecord record;
        int i = 0;
        while (shp.hasNext()) {
            try {
                record = shp.nextRecord();
                if (record != null) {
                    Iterable<? extends Position> pos = record.getCompoundPointBuffer().getPositions();
                    for (Position p : pos) {
                        System.out.print(p + " ");
                    }
                    System.out.println("");
                }
            } catch (Exception ex) {
                Logger.getLogger(SingleAREA_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            i++;
        }
    }

    public List<Geometry> recordsScan(List<ShapefileRecord> records) {
        List<Geometry> geometries = new ArrayList<>();
        List<List<Position>> positionList = new ArrayList<>();
        if (records != null) {
            for (ShapefileRecord sfr : records) {
                List<Position> posList = new ArrayList<>();
                Iterable<? extends Position> pos = sfr.getCompoundPointBuffer().getPositions();
                for (Position p : pos) {
                    posList.add(p);
                }
                positionList.add(posList);
            }
            for (List<Position> pl : positionList) {
                geometries.add(topologyServices.wwjPositions2JtsGeometry(pl));
            }
        }
        return geometries;
    }

    public Geometry selectingArea() {
        List<? extends Position> pos = measureTool.getPositions();
        List<Position> selected = new ArrayList<>();
        for (int i = 1; i < pos.size(); i++) {
            selected.add(new Position(pos.get(i).getLatitude(), pos.get(i).getLongitude(), 100.0));
        }
        selectPolygon = new Polygon(selected);
        normalAttributes = makeNormalAttributes(Material.RED);
        selectPolygon.setAttributes(normalAttributes);
        selectPolygon.setHighlightAttributes(makeHighlighAttributes(normalAttributes, Material.RED));
        selectLayer.addRenderable(selectPolygon);
        wwd.redrawNow();
        return topologyServices.wwjPolygonToJtsGeometry(selectPolygon);

    }

    private BasicShapeAttributes makeNormalAttributes(Material material) {
        BasicShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(material);
        normalAttributes.setInteriorMaterial(material);
        normalAttributes.setInteriorOpacity(0.1);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(true);
        return normalAttributes;
    }

    private BasicShapeAttributes makeHighlighAttributes(BasicShapeAttributes normalAttributes, Material material) {
        BasicShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineOpacity(1);
        highlightAttributes.setOutlineMaterial(material);
        return highlightAttributes;
    }
}
