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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    protected String viewgroupstyle = "configuration.css";
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    protected List<? extends Position> positions;
    protected double latRangeMetric;
    protected double lonRangeMetric;
    protected double scaleLatFactor;
    protected double scaleLonFactor;
    // Les parametres a initialiser via l'interface
    // Taille de la dalle en mm
    protected double TILE_SIDE = 200;
    // Resolution du MNT
    protected int PTS_COUNT = 200;
    // Position du socle
    protected double BOTTOM = 0.0;
    // exagération en vertical du MNT
    protected double MAGNIFICATION = 10;
    protected int line;
    protected int column;
    protected Polygon polyEnveloppe;
    protected Geometry geometryEnveloppe;
    protected boolean firstShow = true;

    protected WKTReader wktReader;
    protected Geometry geometry;
    protected KMLSurfacePolygonImpl polygon;

    protected String GROUP;
    protected String NAME;
    protected RenderableLayer layer;
    protected WorldWindow wwd;

    @FXML
    public Group configgroup;
    @FXML
    public Pane view;
    @FXML
    public ImageView quit;
    @FXML
    public Button computeButton;
    @FXML
    private ChoiceBox<String> tileCB;

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
        //  Layer buildings = new OSMBuildingsStlLayer();
        //  layerTreeServices.addGeoLayer("Buildings", buildings);
        // wwd.getModel().getLayers().add(buildings);
    }

    /*
      Chargement du code FXML du widget
      le parametre polygon represente la frontiere de la cartes
    
     */
    public void showGUI(KMLSurfacePolygonImpl polygon) {
        if (firstShow == true) {
            this.polygon = polygon;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            setTranslateX(300);
            setTranslateY(-150);
            String uri = CSS_STYLE_PATH + viewgroupstyle;
            configgroup.getStylesheets().add(uri);
            // Affichage du widget
            Platform.runLater(() -> {
                guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
                guiAgentServices.getRoot().getChildren().add(this);
                firstShow = false;
            });
        }
        polyEnveloppe = displayChartBoundaries(polygon);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileCB.setItems(FXCollections.observableArrayList("1", "4", "9", "25"));
        tileCB.setValue("1");

        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        computeButton.setOnMouseClicked((MouseEvent event) -> {
            int tiles = Integer.parseInt(tileCB.getValue());
            line = column = (int) Math.sqrt(tiles);
            OUT_PATH = OUT_DIR + OUT_FILE;
            // le calcul est placé dans un Job, pour récupérer la main
            // dans Navisu
            guiAgentServices.getJobsManager().newJob(OUT_PATH, (progressHandle) -> {
                // forEach dalle
                /*
                Affichage de/des dalles aprés le choix utilisateur
                 */
                displayTiles(polyEnveloppe, line, column);

                /*
                initialisation des parametres pour chaque dalles
                 */
                initParameters();
                /*
                lancement de la gération du x3d
                 */
                s57StlChartComponentController.compute(OUT_DIR, OUT_FILE,
                        scaleLatFactor, scaleLonFactor, MAGNIFICATION,
                        TILE_SIDE,
                        PTS_COUNT, BOTTOM,
                        polyEnveloppe, // dalle en WWJ
                        geometryEnveloppe);     // dalle en JTS
                /*
                Fin du calcul our une dalle
                 */
                instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                // endEach
            });
        });
    }

    public Polygon displayChartBoundaries(KMLSurfacePolygonImpl polygon) {
        String result = WwjJTS.locationsToWKT(polygon.getLocations());
        WKTReader wkt = new WKTReader();
        Geometry geom = null;
        try {
            geom = wkt.read(result);
        } catch (ParseException ex) {
            Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Polygon newPolygon = null;
        if (geom != null) {
            polyEnveloppe = WwjJTS.wktPolygonToPolygon(geom.getEnvelope());

            List<List<? extends Position>> positions1 = polyEnveloppe.getBoundaries();
            List<? extends Position> points = positions1.get(0);
            double x = WwjGeodesy.getDistanceM(points.get(0), points.get(1));
            double y = WwjGeodesy.getDistanceM(points.get(2), points.get(1));
            /*
            Un carre = la hauteur / nb
             */

            Position newPosition = WwjGeodesy.getPosition(points.get(1), 270, y * 1000);//270=West

            List<Position> newEnveloppe = new ArrayList<>();
            points.forEach((p) -> {
                newEnveloppe.add(p);
            });
            newEnveloppe.remove(0);
            newEnveloppe.add(0, newPosition);
            Position newP3 = new Position(newEnveloppe.get(3).getLatitude(),
                    newPosition.getLongitude(), 100.0);
            newEnveloppe.remove(3);
            newEnveloppe.add(3, newP3);

            newEnveloppe.remove(4);
            newEnveloppe.add(4, newPosition);
            newPolygon = new Polygon(newEnveloppe);

            /*
            Un carre = la largueur / nb a finir
             */
 /*
            Position newPosition = WwjGeodesy.getPosition(points.get(1), 0, x * 1000);//0=North

            List<Position> newEnveloppe = new ArrayList<>();
            points.forEach((p) -> {
                newEnveloppe.add(p);
            });
            newEnveloppe.remove(2);
            newEnveloppe.add(2, newPosition);
            Position newP3 = new Position(newEnveloppe.get(3).getLatitude(),
                    newPosition.getLongitude(), 100.0);
            newEnveloppe.remove(3);
            newEnveloppe.add(3, newP3);

            newEnveloppe.remove(4);
            newEnveloppe.add(4, newPosition);
            newPolygon = new Polygon(newEnveloppe);
             */
            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.GREEN);
            normalAttributes.setOutlineOpacity(0.2);
            normalAttributes.setInteriorOpacity(0.2);
            normalAttributes.setOutlineMaterial(Material.GREEN);
            normalAttributes.setOutlineWidth(2);
            normalAttributes.setDrawOutline(true);
            normalAttributes.setDrawInterior(true);

            newPolygon.setAttributes(normalAttributes);

            layer.addRenderable(newPolygon);
            wwd.redrawNow();
        }
        return newPolygon;//l'enveloppe est reduite a un carre
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
        positions = polyEnveloppe.getBoundaries().get(0);
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));
        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        scaleLatFactor = TILE_SIDE / latRangeMetric;
        scaleLonFactor = TILE_SIDE / lonRangeMetric;
        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader0 = new WKTReader();

        if (wkt != null) {
            try {
                  geometryEnveloppe = wktReader0.read(wkt);// l'enveloppe au sens de JTS, pour ne faire clacul q'une fois
               /*
                Geometry geometry = wktReader0.read(wkt);
                CoordinateList list = new CoordinateList(geometry.getCoordinates());
                list.closeRing();
                GeometryFactory geometryFactory = new GeometryFactory();
                LinearRing ring = geometryFactory.createLinearRing(list.toCoordinateArray());
                geometryEnveloppe = geometryFactory.createPolygon(ring, null);
*/
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(S57StlComponentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
