/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.GpxBuilder;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Point;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Track;
import bzh.terrevirtuelle.navisu.domain.gpx.model.TrackBuilder;
import bzh.terrevirtuelle.navisu.domain.gpx.model.TrackSegment;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Waypoint;
import bzh.terrevirtuelle.navisu.domain.gpx.model.WaypointBuilder;

import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import bzh.terrevirtuelle.navisu.instruments.routeeditor.impl.RouteEditorImpl;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.operation.buffer.BufferOp;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.TerrainProfileLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RouteEditorController
        extends InstrumentController {

    private final RouteEditorImpl instrument;
    private final String FXML = "routeeditor.fxml";

    private MeasureTool measureTool;
    private TerrainProfileLayer profile = new TerrainProfileLayer();

    private Gpx gpx;
    private List<Track> tracks;// 1 seul Track par Gpx/Route
    private Track track;
    private List<List<Waypoint>> waypoints;// 1 Liste de WP par TS
    private List<TrackSegment> trackSegments;
    private List<Point> boundaries;
    private List<Position> positions;
    private List<Position> pathPositions;
    private int size;
    private String routeName;
    private String author;
    private String version;
    private final double MIN_DISTANCE = 0.5; // minimal distance between 2 Wp
    private final double BUFFER_DISTANCE = 0.01; //unit is decimal degrees
    private Polygon offset;
    private final GeodeticCalculator geoCalc;
    private final Ellipsoid reference = Ellipsoid.WGS84;//default
    private final double KM_TO_NAUTICAL = 0.53879310;
    private final double KM_TO_METER = 1000;
    private GlobalCoordinates waypointA;
    private GlobalCoordinates waypointB;

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
    public Button bufferButton;
    @FXML
    public Button gpxButton;
    @FXML
    public Button kmlButton;
    @FXML
    public Button openButton;
    @FXML
    public Button snapshotButton;
    @FXML
    public TextField lengthText;
    @FXML
    public TextField totalLengthText;
    @FXML
    public TextField headingText;
    @FXML
    TextField namesText;
    @FXML
    TextField nameText;
    @FXML
    TextField descText;
    @FXML
    TextField routeNameText;
    @FXML
    TextField versionText;
    @FXML
    TextField authorText;

    public RouteEditorController(RouteEditorImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.instrument = instrument;
        geoCalc = new GeodeticCalculator();
        positions = new ArrayList();

        /*
         // Add terrain profile layer
         profile.setEventSource(GeoWorldWindViewImpl.getWW());
         profile.setFollow(TerrainProfileLayer.FOLLOW_PATH);
         profile.setShowProfileLine(false);
         */
        load(FXML);
        quit.setOnMouseClicked((MouseEvent event) -> {
            this.instrument.off();
            measureTool.clear();
            measureTool.setArmed(false);
            if (offset != null) {
                measureTool.getLayer().removeRenderable(offset);
            }
        });

        initMeasureTool();
        newButton.setOnMouseClicked((MouseEvent event) -> {
            routeName = routeNameText.getText();
            version = versionText.getText();
            author = authorText.getText();
            positions = new CopyOnWriteArrayList();
            waypoints = new ArrayList();
            track = TrackBuilder.create().name(routeName).build();
            tracks = new ArrayList<>();
            tracks.add(track);
            boundaries = new ArrayList();
            gpx = GpxBuilder.create().creator(author).version(version)
                    .trk(tracks)
                    .build();
            trackSegments = new ArrayList<>();
            measureTool.clear();
            measureTool.setArmed(true);
            bufferButton.disarm();
            gpxButton.disarm();
            kmlButton.disarm();
        });
        openButton.setOnMouseClicked((MouseEvent event) -> {
            Label fileLabel = new Label();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("GPX files (*.gpx)", "*.gpx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(instrument.getGuiAgentServices().getStage());
            if (file != null) {
                fileLabel.setText(file.getPath());
            }
            FileInputStream inputFile = null;
            gpx = null;
            try {
                inputFile = new FileInputStream(new File(file.getPath()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Unmarshaller unmarshaller = null;
            JAXBContext jAXBContext;
            try {
                jAXBContext = JAXBContext.newInstance(Gpx.class);
                unmarshaller = jAXBContext.createUnmarshaller();
                gpx = (Gpx) unmarshaller.unmarshal(inputFile);
            } catch (JAXBException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            showGpx();
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
        endButton.setOnMouseClicked((MouseEvent event) -> {
            filter();
            measureTool.setArmed(false);
            bufferButton.arm();
            gpxButton.arm();
            kmlButton.arm();
        });
        bufferButton.setOnMouseClicked((MouseEvent event) -> {
            bufferFromJTS(positions);
            showBuffer();
        });
        gpxButton.setOnMouseClicked((MouseEvent event) -> {
            fillGpx();
            fillGpxExtensions();
            exportGpx();
        });
        kmlButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Not yet implemented");
        });
        snapshotButton.setOnMouseClicked((MouseEvent event) -> {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    System.out.println("Not yet implemented");
                    //  new ScreenShotAction(GeoWorldWindViewImpl.getWW());
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
        nameText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                //  track.getTrkseg().get(track.getTrkseg().size() - 1).getTrkpt().get(i)
            }
        });
        descText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                //  route.getRtept().get(route.getRtept().size() - 1).setDesc(newvalue);
            }
        });
        unitsCombo.setOnAction((event) -> {
            String item = (String) unitsCombo.getSelectionModel().getSelectedItem();
            if (item.trim().equals("M")) {
                measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.METERS);
            } else {
                if (item.trim().equals("Km")) {
                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
                } else {
                    if (item.trim().equals("Feet")) {
                        measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.FEET);
                    } else {
                        if (item.trim().equals("Miles")) {
                            measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.MILES);
                        } else {
                            if (item.trim().equals("Nm")) {
                                measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.NAUTICAL_MILES);
                            } else {
                                if (item.trim().equals("Yards")) {
                                    measureTool.getUnitsFormat().setLengthUnits(UnitsFormat.YARDS);
                                }
                            }
                        }
                    }
                }
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
                        nameText.clear();
                        descText.clear();
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

    private void filter() {
        size = measureTool.getPositions().size();
        Position ref;
        int i = 0;
        if (size > 1) {
            measureTool.getPositions().stream().forEach((p) -> {
                positions.add(p);
            });
            for (Position p : positions) {
                if (i < positions.size() - 1) {
                    ref = positions.get(i);
                    for (int j = i + 1; j < positions.size(); j++) {
                        if (getDistanceNm(ref, positions.get(j)) < MIN_DISTANCE) {
                            positions.remove(j);
                        }
                    }
                    i++;
                }
            }
        }
    }

    private void fillGpx() {
        size = positions.size();
        for (int i = 0; i < size; i++) {
            Position pos = positions.get(i);
            Waypoint wp = WaypointBuilder.create()
                    .latitude(pos.getLatitude().getDegrees())
                    .longitude(pos.getLongitude().getDegrees())
                    .name("WP" + i)
                    .build();
            if (i < size - 1) {
                TrackSegment ts = new TrackSegment();
                trackSegments.add(ts);
                track.getTrkseg().add(ts);
                ts.getTrkpt().add(wp);
            }
            if (i >= 1) {
                track.getTrkseg().get(i - 1).getTrkpt().add(wp);
                track.getTrkseg().get(i - 1).getTrkpt().get(0).setCourse((float) getAzimuth(measureTool.getPositions().get(i), measureTool.getPositions().get(i + 1)));
            }
        }
    }

    private void fillGpxExtensions() {
        boundaries = gpx.getBoundaries().getBounds();
        pathPositions.stream().forEach((p) -> {
            boundaries.add(new Point(p.getLatitude().getDegrees(), p.getLongitude().getDegrees()));
        });

    }

    private void bufferFromJTS(List<Position> positions) {

        if (measureTool.isArmed() == false) {
            Coordinate[] coordinates = new Coordinate[positions.size()];
            for (int i = 0; i < positions.size(); i++) {
                coordinates[i] = new Coordinate(positions.get(i).getLongitude().getDegrees(),
                        positions.get(i).getLatitude().getDegrees());
            }
            Geometry geom = new GeometryFactory().createLineString(coordinates);
            BufferOp bufferOp = new BufferOp(geom);
            bufferOp.setEndCapStyle(BufferOp.CAP_ROUND);
            Geometry buffer = bufferOp.getResultGeometry(BUFFER_DISTANCE);
            pathPositions = new ArrayList<>();
            for (Coordinate c : buffer.getCoordinates()) {
                pathPositions.add(Position.fromDegrees(c.y, c.x, 100));
            }
            if (!pathPositions.isEmpty()) {
                offset = new Polygon(pathPositions);
            }
        }
    }

    private void showBuffer() {
        if (offset != null) {
            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.WHITE);
            normalAttributes.setInteriorOpacity(0.3);
            normalAttributes.setOutlineMaterial(Material.YELLOW);
            normalAttributes.setOutlineWidth(2);
            normalAttributes.setDrawOutline(true);
            normalAttributes.setDrawInterior(true);
            normalAttributes.setEnableLighting(true);
            offset.setAttributes(normalAttributes);

            ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
            highlightAttributes.setOutlineMaterial(Material.WHITE);
            highlightAttributes.setOutlineOpacity(1);
            highlightAttributes.setInteriorOpacity(0.8);
            offset.setHighlightAttributes(highlightAttributes);

            measureTool.getLayer().addRenderable(offset);
        }
    }

    private void showGpx() {
        System.out.println("Gpx : " + gpx);
    }

    private void exportGpx() {
        if (gpx != null) {
            try {
                FileOutputStream outputFile;
                outputFile = new FileOutputStream(new File("data/gpx/" + routeName + ".gpx"));
                JAXBContext jAXBContext;
                Marshaller marshaller;
                jAXBContext = JAXBContext.newInstance(Gpx.class);
                marshaller = jAXBContext.createMarshaller();
                marshaller.marshal(gpx, outputFile);
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ex " + ex);
            }
        }
    }

    public double getDistanceNm(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getEllipsoidalDistance() / KM_TO_METER * KM_TO_NAUTICAL;
    }

    private double getAzimuth(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getAzimuth();
    }
}
