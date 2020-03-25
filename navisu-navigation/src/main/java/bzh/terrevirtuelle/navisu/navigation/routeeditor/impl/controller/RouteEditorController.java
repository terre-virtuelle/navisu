/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.GpxBuilder;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Point;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Track;
import bzh.terrevirtuelle.navisu.domain.gpx.model.TrackBuilder;
import bzh.terrevirtuelle.navisu.domain.gpx.model.TrackSegment;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Waypoint;
import bzh.terrevirtuelle.navisu.domain.gpx.model.WaypointBuilder;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RouteEditorImpl;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.operation.buffer.BufferOp;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
import javax.xml.bind.JAXBException;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Highway;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.BSplineComponentServices;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl.BSpline;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.export.NMEAExport;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import javafx.scene.Group;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RouteEditorController
        extends Widget2DController {

    private final S57ChartComponentServices s57ChartComponentServices;
    private final GuiAgentServices guiAgentServices;
    private final LayersManagerServices layersManagerServices;
    private final BSplineComponentServices bsplineComponentServices;
    private final DisplayServices displayServices;

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private final RouteEditorImpl instrument;
    private final String FXML = "routeeditor.fxml";
    protected String viewgroupstyle = "routeeditor.css";
    private MeasureTool measureTool;

    private List<NavigationalWarnings> avurnavList;
    private List<SailingDirections> sailingDirectionsList;
    private List<Gpx> gpxList;
    private File file;

    private TerrainProfileLayer profile;
    private Gpx gpx;
    private List<Track> tracks;// 1 seul Track par Gpx/Route
    private Track track;
    private List<List<Waypoint>> waypoints;// 1 Liste de WP par TS
    private List<TrackSegment> trackSegments;
    private List<Point> boundaries;
    // private List<Point> highways;
    private List<Position> positions;
    private List<Position> offsetPathPositions;
    private List<Position> highwayPathPositions;
    private Set<S57Controller> s57Controllers;
    private NavigationDataSet navigationDataSet;
    private int size;
    private String routeName;
    private String author;
    private String version;
    private float speed;
    private final double MIN_DISTANCE = 0.1; // minimal distance between 2 Wp
    private final double OFFSET_BUFFER_DISTANCE = 0.01; //unit is decimal degrees
    private final double HIGHWAY_BUFFER_DISTANCE = 0.0003; //0.0003; //unit is decimal degrees
    private double bufferDistance;
    private double highwayDistance;
    private Geometry offsetBuffer;
    private Geometry highwayBuffer;
    private Polygon offset;
    private Polygon highwayPoly;
    private Highway highway;
    private final GeodeticCalculator geoCalc;
    private final Ellipsoid reference = Ellipsoid.WGS84;//default
    private final double KM_TO_NAUTICAL = 0.53879310;
    private final double KM_TO_METER = 1000;
    private GlobalCoordinates waypointA;
    private GlobalCoordinates waypointB;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmmss");
    private final DateTimeFormatter kmlDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter kmlTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
    private final WorldWindow wwd;
    private RouteDataEditorController routeDataEditorController;
    private RenderableLayer layer;
    private GeodeticCurve geoCurve;
    protected static final String GROUP_0 = "S57 charts";
    protected static final String S57_LAYER = "S57Stl";
    protected NMEAExport nmeaExport;

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
    public TextField speedText;
    @FXML
    public TextField distPoText;
    @FXML
    public TextField heightText;
    @FXML
    public TextField distOffsetText;
    @FXML
    public CheckBox simpleEditorCB;
    @FXML
    public CheckBox gpxExportCB;
    @FXML
    public CheckBox kmlExportCB;
    @FXML
    public CheckBox nmeaExportCB;

    public RouteEditorController(RouteEditorImpl instrument,
            LayersManagerServices layersManagerServices,
            BSplineComponentServices bsplineComponentServices,
            DisplayServices displayServices,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.layersManagerServices = layersManagerServices;
        this.bsplineComponentServices = bsplineComponentServices;
        this.displayServices = displayServices;
        this.instrument = instrument;
        this.s57ChartComponentServices = instrument.getS57ChartServices();
        this.guiAgentServices = instrument.getGuiAgentServices();
        wwd = GeoWorldWindViewImpl.getWW();
        profile = new TerrainProfileLayer();
        geoCalc = new GeodeticCalculator();
        bufferDistance = OFFSET_BUFFER_DISTANCE;
        highwayDistance = HIGHWAY_BUFFER_DISTANCE;
        layer = layersManagerServices.getLayer(GROUP_0, S57_LAYER);
        nmeaExport = new NMEAExport(geoCurve, geoCalc, reference);

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
        speed = Float.parseFloat(speedText.getText());

        initMeasureTool();
        newButton.setOnMouseClicked((MouseEvent event) -> {
            newAction();
        });
        dataButton.setOnMouseClicked((MouseEvent event) -> {
            dataAction();
        });
        openButton.setOnMouseClicked((MouseEvent event) -> {
            initMeasureTool();
            newAction();
            file = IO.fileChooser(guiAgentServices.getStage(),
                    "privateData/nds", "NDS files (*.nds)", "*.nds", "*.NDS");
            try {
                navigationDataSet = new NavigationDataSet();
                navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
            } catch (FileNotFoundException | JAXBException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            measureTool.setArmed(!measureTool.isArmed());
            fillMesureTool();
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
            filter();
            positions = smooth(positions);
            displayServices.displayPositionsAsPath(positions, layer, Material.YELLOW);
            measureTool.setArmed(false);
            if (!simpleEditorCB.isSelected()) {
                bufferFromJTS();
                showBuffer();
            }
            fillGpx();
            fillGpxBoundaries();
            //  fillGpxHighway();
            if (gpxExportCB.isSelected()) {
                exportGpx();
            }
            if (kmlExportCB.isSelected()) {
                exportKML();
            }
            if (nmeaExportCB.isSelected()) {
                nmeaExport.export(positions, speed, routeName);
            }
            if (!simpleEditorCB.isSelected()) {
                exportNavigationDataset();
            }
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
        speedText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                speed = Float.parseFloat(newvalue);
            }
        });
        distOffsetText.textProperty().addListener((ov, oldvalue, newvalue) -> {
            if (!"".equals(newvalue)) {
                bufferDistance = Float.parseFloat(newvalue) / 60.0;
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
        waypoints = new ArrayList();
        track = TrackBuilder.create()
                .name(routeName)
                .build();
        tracks = new ArrayList<>();
        tracks.add(track);
        gpx = GpxBuilder.create()
                .creator(author)
                .version(version)
                .trk(tracks)
                .build();
        trackSegments = new ArrayList<>();
        measureTool.clear();
        measureTool.setArmed(true);
    }

    private void dataAction() {
        routeDataEditorController.setNavigationDataSet(navigationDataSet);
        routeDataEditorController.setVisible(true);
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
    // Take positions and smooth by BSpline

    private List<Position> smooth(List<Position> pos) {
        List<Position> result = new ArrayList<>();
        List<Point3D> points = new ArrayList<>();
        int deg = 2;
        int n = pos.size();
           System.out.println("n = " + n);
        for (Position p : pos) {
            points.add(new Point3D(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), 50.0));
        }
        double[] knots = new double[points.size() + deg + 1];
        knots[0] = 0.0;
        knots[1] = 0.0;
        int j = 0;
        for (int i = 2; i <= n; i++) {
            knots[i] = i - deg;
            j = i - deg;
        }
        knots[n + 1] = j;
        knots[n + 2] = j;
        for (int i = 0; i < knots.length; i++) {
            System.out.print(knots[i] + "  ");
        }
        //  System.out.println("");
        double[] w = new double[points.size()];
        for (int i = 0; i < w.length; i++) {
            w[i] = 1.0;
        }
        for (int i = 0; i < w.length; i++) {
            System.out.print(w[i] + " ");
        }
        //  System.out.println("");
        BSpline bSpline = bsplineComponentServices.create(points, knots, w, deg);
        List<Point3D> resultPoints = bsplineComponentServices.compute(bSpline, 0.1);
        for (Point3D p : resultPoints) {
            result.add(new Position(Angle.fromDegrees(p.getX()), Angle.fromDegrees(p.getY()), p.getZ()));
        }
        //  System.out.println("result : " + result.size());
        return result;
    }

    private void fillGpx() {
        size = positions.size();
        TrackSegment ts = new TrackSegment();
        for (int i = 0; i < size - 1; i++) {
            Position pos = positions.get(i);
            Waypoint wp = WaypointBuilder.create()
                    .latitude(pos.getLatitude().getDegrees())
                    .longitude(pos.getLongitude().getDegrees())
                    .name("WP" + i)
                    .speed(speed)
                    .build();
            if (i < size - 1) {
                trackSegments.add(ts);
                track.getTrkseg().add(ts);
                ts.getTrkpt().add(wp);
            }
            if (i >= 1) {
                track.getTrkseg().get(i - 1).getTrkpt().add(wp);
                track.getTrkseg().get(i - 1).getTrkpt().get(0).setCourse((float) getAzimuth(positions.get(i), positions.get(i + 1)));
            }
        }
    }

    private void fillGpxBoundaries() {
        boundaries = gpx.getBoundaries().getBounds();
        if (boundaries != null && offsetPathPositions != null) {
            offsetPathPositions.stream().forEach((p) -> {
                boundaries.add(new Point(p.getLatitude().getDegrees(), p.getLongitude().getDegrees()));
            });
        }
    }

    private void bufferFromJTS() {

        if (measureTool.isArmed() == false) {
            Coordinate[] coordinates = new Coordinate[positions.size()];
            for (int i = 0; i < positions.size(); i++) {
                coordinates[i] = new Coordinate(positions.get(i).getLongitude().getDegrees(),
                        positions.get(i).getLatitude().getDegrees());
            }
            Geometry geom = new GeometryFactory().createLineString(coordinates);

            BufferOp bufferOp = new BufferOp(geom);
            bufferOp.setEndCapStyle(BufferParameters.CAP_FLAT);//CAP_ROUND);
            offsetBuffer = bufferOp.getResultGeometry(bufferDistance);
            offsetPathPositions = new ArrayList<>();
            for (Coordinate c : offsetBuffer.getCoordinates()) {
                offsetPathPositions.add(Position.fromDegrees(c.y, c.x, 100));
            }
            if (!offsetPathPositions.isEmpty()) {
                offset = new Polygon(offsetPathPositions);
            }
            offset.setValue(AVKey.DISPLAY_NAME, routeName);

            bufferOp = new BufferOp(geom);
            bufferOp.setEndCapStyle(BufferParameters.CAP_FLAT);

            bufferOp.setQuadrantSegments(1);

            highwayBuffer = bufferOp.getResultGeometry(highwayDistance);
            highwayPathPositions = new ArrayList<>();
            for (Coordinate c : highwayBuffer.getCoordinates()) {
                highwayPathPositions.add(Position.fromDegrees(c.y, c.x, 1));
            }
            int i = 0;
            for (Position p : highwayPathPositions) {
                PointPlacemark pp = new PointPlacemark(p);
                pp.setLabelText(Integer.toString(i));
                pp.setValue(AVKey.DISPLAY_NAME, i);
                measureTool.getLayer().addRenderable(pp);
                i++;
            }
            int highwayPathPositionsSize = highwayPathPositions.size();
            System.out.println("highwayPathPositionsSize " + highwayPathPositionsSize);
            for (int j = 0; j < highwayPathPositionsSize - 1; j++) {
                System.out.println(getDistanceM(highwayPathPositions.get(j), highwayPathPositions.get(j + 1)));
            }

            if (!highwayPathPositions.isEmpty()) {
                highwayPoly = new Polygon(highwayPathPositions);
                highway = new Highway(1);
                highway.setName(routeName);
                highway.setGeometry(WwjJTS.toPolygonWkt(highwayPathPositions));
                highway.setDescription("Range : " + Integer.toString((int) (highwayDistance * 3600)) + " sec");
                gpx.setHighway(highway);
            }
            highwayPoly.setValue(AVKey.DISPLAY_NAME, routeName);
        }
    }

    public void showBuffer() {
        if (offset != null) {
            ShapeAttributes offsetNormalAttributes = new BasicShapeAttributes();
            offsetNormalAttributes.setInteriorMaterial(Material.WHITE);
            offsetNormalAttributes.setInteriorOpacity(0.3);
            offsetNormalAttributes.setOutlineMaterial(Material.YELLOW);
            offsetNormalAttributes.setOutlineWidth(2);
            offsetNormalAttributes.setDrawOutline(true);
            offsetNormalAttributes.setDrawInterior(true);
            offsetNormalAttributes.setEnableLighting(true);
            offset.setAttributes(offsetNormalAttributes);

            ShapeAttributes offsetHighlightAttributes = new BasicShapeAttributes(offsetNormalAttributes);
            offsetHighlightAttributes.setOutlineMaterial(Material.WHITE);
            offsetHighlightAttributes.setOutlineOpacity(1);
            offsetHighlightAttributes.setInteriorOpacity(0.8);
            offset.setHighlightAttributes(offsetHighlightAttributes);
            measureTool.getLayer().addRenderable(offset);
        }
        if (highwayPoly != null) {
            ShapeAttributes offsetNormalAttributes = new BasicShapeAttributes();
            offsetNormalAttributes.setOutlineMaterial(Material.RED);
            offsetNormalAttributes.setOutlineWidth(2);
            offsetNormalAttributes.setDrawOutline(true);
            offsetNormalAttributes.setDrawInterior(false);
            offsetNormalAttributes.setEnableLighting(true);
            highwayPoly.setAttributes(offsetNormalAttributes);

            ShapeAttributes offsetHighlightAttributes = new BasicShapeAttributes(offsetNormalAttributes);
            offsetHighlightAttributes.setOutlineMaterial(Material.GREEN);
            offsetHighlightAttributes.setOutlineOpacity(1);
            highwayPoly.setHighlightAttributes(offsetHighlightAttributes);
            measureTool.getLayer().addRenderable(highwayPoly);
        }
    }

    public Geometry getBuffer() {
        return offsetBuffer;
    }

    @SuppressWarnings("unchecked")
    private void fillMesureTool() {
        positions = new CopyOnWriteArrayList();
        Position pos;
        for (TrackSegment ts : gpx.getTrk().get(0).getTrkseg()) {
            for (int i = 0; i < ts.getTrkpt().size(); i++) {
                pos = new Position(
                        Angle.fromDegrees(ts.getTrkpt().get(i).getLatitude()),
                        Angle.fromDegrees(ts.getTrkpt().get(i).getLongitude()), 0.0);
                positions.add(pos);
            }
        }

        size = positions.size();
        Position ref;
        int i = 0;
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
        ArrayList<Position> positionsList = new ArrayList<>();
        positionsList.addAll(positions);
        measureTool.setPositions(positionsList);
    }

    private void exportGpx() {
        if (gpx != null) {
            try {
                ImportExportXML.exports(gpx, "privateData/gpx/" + routeName + ".gpx");
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    private void exportKML() {
        if (positions != null) {
            List<String> kml = new ArrayList<>();
            String header = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                    + "<kml xmlns=\"http://earth.google.com/kml/2.0\">\n"
                    + "    <Document>";
            kml.add(header);
            String body;
            for (int i = 0; i < positions.size() - 1; i++) {
                body = "<Placemark>"
                        + "<description>"
                        + "<![CDATA["
                        + "WP" + i
                        + "<br>Lat : " + String.format("%.4f", positions.get(i).getLatitude().degrees)
                        + "<br>Lon : " + String.format("%.4f", positions.get(i).getLongitude().degrees)
                        + "<br>Time : " + LocalTime.now(Clock.systemUTC()).format(kmlTimeFormatter)
                        + "<br>Day : " + LocalDate.now(Clock.systemUTC()).format(kmlDateFormatter)
                        + "<br>Speed : " + speed + " Kts"
                        + "]]>"
                        + "</description>"
                        + "<Point>"
                        + "<coordinates>" + positions.get(i).getLongitude().degrees + ","
                        + positions.get(i).getLatitude().degrees + ",10" + "</coordinates>"
                        + "</Point>"
                        + "</Placemark>"
                        + "<Placemark>"
                        + "<Style>"
                        + "<LineStyle>"
                        + "<color>501400FF</color>"
                        + "<width>2</width>"
                        + "</LineStyle>"
                        + "</Style>"
                        + "<LineString>"
                        + "<coordinates>"
                        + positions.get(i).getLongitude().degrees + "," + positions.get(i).getLatitude().degrees + ",10\n"
                        + positions.get(i + 1).getLongitude().degrees + "," + positions.get(i + 1).getLatitude().degrees + ",0"
                        + "</coordinates>"
                        + "</LineString>"
                        + "</Placemark>";
                kml.add(body);
            }
            int i = positions.size() - 1;
            body = "<Placemark>"
                    + "<description>"
                    + "<![CDATA["
                    + "WP" + i
                    + "<br>Lat : " + String.format("%.4f", positions.get(i).getLatitude().degrees)
                    + "<br>Lon : " + String.format("%.4f", positions.get(i).getLongitude().degrees)
                    + "<br>Time : " + LocalTime.now(Clock.systemUTC()).format(kmlTimeFormatter)
                    + "<br>Day : " + LocalDate.now(Clock.systemUTC()).format(kmlDateFormatter)
                    + "<br>Speed : " + speed + " Kts"
                    + "]]>"
                    + "</description>"
                    + "<Point>"
                    + "<coordinates>"
                    + positions.get(i).getLongitude().degrees + ",\n"
                    + positions.get(i).getLatitude().degrees + ",10"
                    + "</coordinates>"
                    + "</Point>"
                    + "</Placemark>";
            kml.add(body);
            String footer = " </Document>"
                    + "</kml>";
            kml.add(footer);
            Path path = Paths.get("privateData/kml/" + routeName + ".kml");
            try {
                Files.write(path, kml, Charset.defaultCharset());
            } catch (IOException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void exportNavigationDataset() {
        s57Controllers = new HashSet<>();
        s57Controllers = s57ChartComponentServices.getS57Controllers();
        if (s57Controllers != null) {
            navigationDataSet = new NavigationDataSet();
            navigationDataSet.add(gpx);
            navigationDataSet.addAll(s57ChartComponentServices.getS57Charts());
            Coordinate buoyagePosition;
            for (S57Controller sc : s57Controllers) {
                buoyagePosition = new Coordinate(sc.getNavigationData().getLongitude(), sc.getNavigationData().getLatitude());
                if (offsetBuffer.contains(new GeometryFactory().createPoint(buoyagePosition))) {
                    navigationDataSet.add(sc.getNavigationData());
                }
            }
            try {
                ImportExportXML.exports(navigationDataSet, "privateData/nds/" + routeName + ".nds");
            } catch (JAXBException | FileNotFoundException ex) {
                System.out.println("ex " + ex);
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        } else {
            System.out.println("Il faut charger au moins une carte S57");
        }
    }

    public void setRouteDataEditorController(RouteDataEditorController routeDataEditorController) {
        this.routeDataEditorController = routeDataEditorController;
    }

    private double getDistanceNm(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getEllipsoidalDistance() / KM_TO_METER * KM_TO_NAUTICAL;
    }

    private double getDistanceM(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getEllipsoidalDistance() / KM_TO_METER;
    }

    private double getAzimuth(Position posA, Position posB) {
        waypointA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        waypointB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(reference, waypointA, waypointB).getAzimuth();
    }
}
