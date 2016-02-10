/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.measuretools.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.navigation.measuretools.impl.MeasureToolsImpl;
import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.SurfacePolylines;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import java.awt.Component;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.swing.JLabel;

/**
 * NaVisu
 *
 * @date 18 juil. 2015
 * @author Serge Morvan
 */
public class MeasureToolsController
        extends Widget2DController
        implements Initializable {

    private static MeasureToolsController INSTANCE;
    private WorldWindow wwd;
    private MeasureToolsImpl instrument;
    private GuiAgentServices guiAgentServices;
    private S57ChartComponentServices s57ChartComponentServices;
    private LayersManagerServices layersManagerServices;

    private JLabel[] pointLabels;
    private final String FXML = "measuretools.fxml";
    @FXML
    public Pane view;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public ComboBox shapeCombo;
    @FXML
    public ComboBox pathTypeCombo;
    @FXML
    public ComboBox unitsCombo;
    @FXML
    public ComboBox anglesCombo;
    @FXML
    public CheckBox followCheck;
    @FXML
    public CheckBox showControlsCheck;
    @FXML
    public CheckBox showAnnotationCheck;
    @FXML
    public CheckBox rubberBandCheck;
    @FXML
    public CheckBox freeHandCheck;
    @FXML
    public Button newButton;
    @FXML
    public Button pauseButton;
    @FXML
    public Button endButton;
    @FXML
    public Text lengthLabel;
    @FXML
    public Text areaLabel;
    @FXML
    public Text widthLabel;
    @FXML
    public Text heightLabel;
    @FXML
    public Text headingLabel;
    @FXML
    public Text centerLabel;

    MeasureTool measureTool;
    private JLabel[] pointsLabel;
    private String unit = "Km/Km\u00b2";
    private final String NAME = "CoastalLines";
    private final String GROUP = "Navigation";
    protected RenderableLayer coastalLinesLayer;

    public static MeasureToolsController getInstance(MeasureToolsImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            S57ChartComponentServices s57ChartComponentServices,
            LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new MeasureToolsController(instrument, keyCode, keyCombination,
                    guiAgentServices, s57ChartComponentServices, layersManagerServices);
        }
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, INSTANCE);
        guiAgentServices.getRoot().getChildren().add(INSTANCE);

        return INSTANCE;
    }

    private MeasureToolsController(MeasureToolsImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            S57ChartComponentServices s57ChartComponentServices,
            LayersManagerServices layersManagerServices) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        this.guiAgentServices = guiAgentServices;
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.layersManagerServices = layersManagerServices;
        wwd = GeoWorldWindViewImpl.getWW();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        view.setOpacity(0.8);

        measureTool = new MeasureTool(wwd);
        measureTool.setController(new MeasureToolController());
        addlisteners();
        makePanel();
    }

    private void addlisteners() {
        measureTool.addPropertyChangeListener((PropertyChangeEvent event) -> {
            switch (event.getPropertyName()) {
                case MeasureTool.EVENT_POSITION_ADD:
                case MeasureTool.EVENT_POSITION_REMOVE:
                case MeasureTool.EVENT_POSITION_REPLACE:
                    fillPointsPanel();
                    break;
                case MeasureTool.EVENT_ARMED:
                    if (measureTool.isArmed()) {
                        newButton.setDisable(true);
                        pauseButton.setText("Pause");
                        pauseButton.setDisable(false);
                        endButton.setDisable(false);
                        ((Component) wwd).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    } else {
                        newButton.setDisable(false);
                        pauseButton.setText("Pause");
                        pauseButton.setDisable(true);
                        endButton.setDisable(true);
                        ((Component) wwd).setCursor(Cursor.getDefaultCursor());
                    }
                    break;
                case MeasureTool.EVENT_METRIC_CHANGED:
                    switch (unit) {
                        case "M/M\u00b2":
                            updateUnits("m", "m2", 1, 1);
                            break;
                        case "Km/Km\u00b2":
                            updateUnits("Km", "Km2", 1e-3, 1e-6);
                            break;
                        case "Km/Hectare":
                            updateUnits("Km", "H", 1e-3, 1e-4);
                            break;
                        case "Feet/Feet\u00b2":
                            updateUnits("F", "F2", 0.3048, 0.092903);
                            break;
                        case "Miles/Miles\u00b2":
                            updateUnits("M", "M2", 0.000621371, 3.86102e-7);
                            break;
                        case "Nm/Miles\u00b2":
                            updateUnits("Nm", "M2", 0.000539957, 3.86102e-7);
                            break;
                        case "Yards/Acres":
                            updateUnits("y", "a", 1.09361, 0.000247105);
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void makePanel() {

        shapeCombo.setOnAction((event) -> {
            String item = shapeCombo.getSelectionModel().getSelectedItem().toString();
            switch (item) {
                case "Line":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_LINE);
                    break;
                case "Path":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_PATH);
                    break;
                case "Polygon":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
                    break;
                case "Circle":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE);
                    break;
                case "Ellipse":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_ELLIPSE);
                    break;
                case "Square":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_SQUARE);
                    break;
                case "Rectangle":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_QUAD);
                    break;
                case "CoastalLine":
                    measureTool.setMeasureShapeType(MeasureTool.SHAPE_PATH);
                    List<SurfacePolylines> coastalLines = s57ChartComponentServices.getCoastalLines();
                    coastalLineDisplay(coastalLines);
                    coastalLineToJTS(coastalLines);
                    break;
                default:
                    break;
            }
        });
        pathTypeCombo.setOnAction((Event event) -> {
            String item = pathTypeCombo.getSelectionModel().getSelectedItem().toString();
            switch (item) {
                case "Linear":
                    measureTool.setPathType(AVKey.LINEAR);
                    break;
                case "Rhumb":
                    measureTool.setPathType(AVKey.RHUMB_LINE);
                    break;
                case "Great circle":
                    measureTool.setPathType(AVKey.GREAT_CIRCLE);
                    break;
                default:
                    break;
            }
        });
        unitsCombo.setOnAction((Event event) -> {
            String item = unitsCombo.getSelectionModel().getSelectedItem().toString();
            switch (item) {
                case "M/M\u00b2":
                    unit = "M/M\u00b2";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.METERS);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.SQUARE_METERS);
                    updateUnits("m", "m2", 1, 1);
                    break;
                case "Km/Km\u00b2":
                    unit = "Km/Km\u00b2";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.SQUARE_KILOMETERS);
                    updateUnits("Km", "Km2", 1e-3, 1e-6);
                    break;
                case "Km/Hectare":
                    unit = "Km/Hectare";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.HECTARE);
                    updateUnits("Km", "H", 1e-3, 1e-4);
                    break;
                case "Feet/Feet\u00b2":
                    unit = "Feet/Feet\u00b2";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.FEET);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.SQUARE_FEET);
                    updateUnits("F", "F2", 0.3048, 0.092903);
                    break;
                case "Miles/Miles\u00b2":
                    unit = "Miles/Miles\u00b2";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.MILES);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.SQUARE_MILES);
                    updateUnits("M", "M2", 0.000621371, 3.86102e-7);
                    break;
                case "Nm/Miles\u00b2":
                    unit = "Nm/Miles\u00b2";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.NAUTICAL_MILES);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.SQUARE_MILES);
                    updateUnits("Nm", "M2", 0.000539957, 3.86102e-7);
                    break;
                case "Yards/Acres":
                    unit = "Yards/Acres";
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.YARDS);
                    measureTool.getUnitsFormat().setAreaUnits(UnitsFormat.ACRE);
                    updateUnits("y", "a", 1.09361, 0.000247105);
                    break;
                default:
                    break;
            }

        });
        anglesCombo.setOnAction((Event event) -> {
            String item = anglesCombo.getSelectionModel().getSelectedItem().toString();
            measureTool.getUnitsFormat().setShowDMS(item.equals("DMS"));
        });

        followCheck.setOnAction((event) -> {
            measureTool.setFollowTerrain(followCheck.isSelected());
            wwd.redraw();
        });
        showControlsCheck.setOnAction((event) -> {
            measureTool.setFollowTerrain(showControlsCheck.isSelected());
            wwd.redraw();
        });
        rubberBandCheck.setOnAction((event) -> {
            measureTool.setFollowTerrain(rubberBandCheck.isSelected());
            measureTool.getController().setUseRubberBand(rubberBandCheck.isSelected());
            freeHandCheck.setDisable(!rubberBandCheck.isSelected());
            wwd.redraw();
        });
        freeHandCheck.setOnAction((event) -> {
            measureTool.getController().setFreeHand(freeHandCheck.isSelected());
            wwd.redraw();
        });
        showAnnotationCheck.setOnAction((event) -> {
            measureTool.setShowAnnotation(showAnnotationCheck.isSelected());
            wwd.redraw();
        });
        newButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool.clear();
            measureTool.setArmed(true);
        });
        pauseButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool.setArmed(!measureTool.isArmed());
            pauseButton.setText(!measureTool.isArmed() ? "Resume" : "Pause");
            pauseButton.setDisable(false);
            ((Component) wwd).setCursor(!measureTool.isArmed() ? Cursor.getDefaultCursor()
                    : Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        });
        endButton.setOnMouseClicked((MouseEvent event) -> {
            measureTool.setArmed(false);
        });
        this.pointLabels = new JLabel[100];
        for (int i = 0; i < this.pointLabels.length; i++) {
            this.pointLabels[i] = new JLabel("");
        }
        quit.setOnMouseClicked((MouseEvent event) -> {
            measureTool.setArmed(false);
            instrument.off();
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
    }

    private void fillPointsPanel() {
        int i = 0;
        if (measureTool.getPositions() != null) {
            for (LatLon pos : measureTool.getPositions()) {
                if (i == this.pointLabels.length) {
                    break;
                }
                String las = String.format("Lat %7.4f\u00B0", pos.getLatitude().getDegrees());
                String los = String.format("Lon %7.4f\u00B0", pos.getLongitude().getDegrees());
                pointLabels[i++].setText(las + "  " + los);
            }
        }
        // Clear remaining labels
        for (; i < this.pointLabels.length; i++) {
            pointLabels[i].setText("");
        }

    }

    private void updateUnits(String lengthUnit, String squareUnit,
            double convertLengthCoeff, double convertSquareCoeff) {
        // Update length label
        double value = measureTool.getLength();
        String s;
        if (value <= 0) {
            s = "na";
        } else {
            s = String.format("%,7.1f " + lengthUnit, value * convertLengthCoeff);
        }
        lengthLabel.setText(s);

        // Update area label
        value = measureTool.getArea();
        if (value < 0) {
            s = "na";
        } else {
            s = String.format("%,7.1f " + squareUnit, value * convertSquareCoeff);
        }
        areaLabel.setText(s);

        // Update width label
        value = measureTool.getWidth();
        if (value < 0) {
            s = "na";
        } else {
            s = String.format("%,7.1f " + lengthUnit, value * convertLengthCoeff);
        }
        widthLabel.setText(s);

        // Update height label
        value = measureTool.getHeight();
        if (value < 0) {
            s = "na";
        } else {
            s = String.format("%,7.1f " + lengthUnit, value * convertLengthCoeff);
        }
        heightLabel.setText(s);

        updateOrientationPosition();
    }

    private void updateOrientationPosition() {
        String s;
        // Update heading label
        Angle angle = measureTool.getOrientation();
        if (angle != null) {
            s = String.format("%,6.2f\u00B0", angle.degrees);
        } else {
            s = "na";
        }
        headingLabel.setText(s);

        // Update center label
        Position center = measureTool.getCenterPosition();
        if (center != null) {
            s = String.format("%,7.4f\u00B0 %,7.4f\u00B0", center.getLatitude().degrees, center.getLongitude().degrees);
        } else {
            s = "na";
        }
        centerLabel.setText(s);
    }

    private void coastalLineDisplay(List<SurfacePolylines> data) {
        coastalLinesLayer = layersManagerServices.getInstance(GROUP, NAME);
        // coastalLinesLayer.setPickEnabled(false);
        coastalLinesLayer.addRenderables(data);
    }

    private Geometry coastalLineToJTS(List<SurfacePolylines> data) {
        WKTReader wkt = new WKTReader();
        Geometry coastalLines = null;
        try {
            coastalLines = wkt.read(WWJ_JTS.surfacePolylinesToWkt(data));
        } catch (ParseException ex) {
            Logger.getLogger(MeasureToolsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (coastalLines != null) {
            System.out.println(coastalLines.toText());
        }
        return coastalLines;
    }
}
