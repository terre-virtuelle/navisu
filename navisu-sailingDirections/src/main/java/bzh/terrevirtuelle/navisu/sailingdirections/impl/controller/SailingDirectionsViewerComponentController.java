/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.sailingdirections.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.ShomSailingDirections;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.sailingdirections.impl.SailingDirectionsComponentImpl;
//import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
//import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date Feb 22, 2016
 *
 */
public class SailingDirectionsViewerComponentController
        extends Widget2DController
        implements Initializable {

    private static SailingDirectionsViewerComponentController INSTANCE;
    private final String FXML = "sailingDirectionsViewer.fxml";
    private final String LAYER_NAME_0 = "SailingDirectionsPoi";
    private final String LAYER_NAME_1 = "SailingDirectionsZones";
    private final String ICON_NAME = "SailingDirections";
    private final String GROUP_NAME = "Navigation";
    private final String IN_D22 = "data/sd/shom/d22.xml";
    private final String IN_D21 = "data/sd/shom/d21.xml";
    private final WorldWindow wwd;
    private final SailingDirectionsComponentImpl instrument;
    private final GuiAgentServices guiAgentServices;
    private final S57ChartComponentServices s57ChartComponentServices;
    private final LayersManagerServices layersManagerServices;

    protected RenderableLayer sailingDirectionsPgonLayer;
    protected RenderableLayer sailingDirectionsIconsLayer;
    private NavigationDataSet navigationDataSet;
    SailingDirections sailingDirections;
    @FXML
    public Pane view;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public Button c2aButton;
    @FXML
    public Button c2bButton;
    @FXML
    public Button d21Button;
    @FXML
    public Button d22Button;
    @FXML
    public Button d23Button;

    public static SailingDirectionsViewerComponentController getInstance(SailingDirectionsComponentImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            S57ChartComponentServices s57ChartComponentServices,
            LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new SailingDirectionsViewerComponentController(instrument, keyCode, keyCombination,
                    guiAgentServices, s57ChartComponentServices, layersManagerServices);
        }
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, INSTANCE);
        guiAgentServices.getRoot().getChildren().add(INSTANCE);

        return INSTANCE;
    }

    public SailingDirectionsViewerComponentController(SailingDirectionsComponentImpl instrument,
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
        makePanel();
        sailingDirectionsPgonLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_1);
        sailingDirectionsPgonLayer.setPickEnabled(false);
        sailingDirectionsIconsLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_0);
    }

    private void makePanel() {

        c2aButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("No data");
        });
        c2bButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("No data");
        });
        d21Button.setOnMouseClicked((MouseEvent event) -> {
            Map<Pair<Double, Double>, String> result = new ShomSailingDirections(IN_D21).getPoiMap();
            if (result != null) {
                MultiPoint multiPoint = WWJ_JTS.toMultiPoint(showPoi(result));
                Point point = multiPoint.getCentroid();
                instrument.off();
                wwd.getView().goTo(Position.fromDegrees(point.getX(), point.getY()), 500000.0);
            }
        });
        d22Button.setOnMouseClicked((MouseEvent event) -> {
            Map<Pair<Double, Double>, String> result = new ShomSailingDirections(IN_D22).getPoiMap();
            if (result != null) {
                MultiPoint multiPoint = WWJ_JTS.toMultiPoint(showPoi(result));
                Point point = multiPoint.getCentroid();
                instrument.off();
                wwd.getView().goTo(Position.fromDegrees(point.getX(), point.getY()), 500000.0);
            }
        });
        d23Button.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("No data");
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });

    }

    public Set<Pair<Double, Double>> showPoi(Map<Pair<Double, Double>, String> data) {
        Set<Pair<Double, Double>> latLonSet = data.keySet();

        latLonSet.stream().map((ll) -> {
            String imageAddress = NavigationIcons.ICONS.get(ICON_NAME);
            PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(ll.getX(), ll.getY(), 0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            placemark.setValue(AVKey.DISPLAY_NAME, data.get(ll));
            PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
            normalAttributes.setImageAddress(imageAddress);
            normalAttributes.setScale(0.4);
            placemark.setAttributes(normalAttributes);
            return placemark;
        }).forEach((placemark) -> {
            sailingDirectionsIconsLayer.addRenderable(placemark);
        });

        return latLonSet;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
