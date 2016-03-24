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
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.SdShom;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.SdShomCatalog;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.ShomSailingDirections;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.sailingdirections.impl.SailingDirectionsComponentImpl;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
//import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
//import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.vividsolutions.jts.geom.Point;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Feb 22, 2016
 *
 */
public class SailingDirectionsEditorComponentController
        extends Widget2DController
        implements Initializable {

    private static SailingDirectionsEditorComponentController INSTANCE;
    private final String FXML = "sailingDirectionsViewer.fxml";
    private final String LAYER_NAME_0 = "SailingDirectionsPoi";
    private final String LAYER_NAME_1 = "SailingDirectionsZones";
    private final String ICON_NAME = "SailingDirections";
    private final String GROUP_NAME = "Navigation";
    private final String ROOT = "data/sd/shom/";
    private final String IN_SHOM_CATALOG_FILE_NAME = ROOT + "instructionsNautiquesShom.xml";
    private final double POI_ALTITUDE = 500000.0;
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
    public ComboBox sdComboBox;

    public static SailingDirectionsEditorComponentController getInstance(SailingDirectionsComponentImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            S57ChartComponentServices s57ChartComponentServices,
            LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new SailingDirectionsEditorComponentController(instrument, keyCode, keyCombination,
                    guiAgentServices, s57ChartComponentServices, layersManagerServices);
        }
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, INSTANCE);
        guiAgentServices.getRoot().getChildren().add(INSTANCE);

        return INSTANCE;
    }

    public SailingDirectionsEditorComponentController(SailingDirectionsComponentImpl instrument,
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
        sailingDirectionsPgonLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_1);
        sailingDirectionsPgonLayer.setPickEnabled(false);
        sailingDirectionsIconsLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map<String, String> sdShomCatalogMap = new HashMap<>();
        SdShomCatalog sdShomCatalog = new SdShomCatalog();
        try {
            sdShomCatalog = ImportExportXML.imports(sdShomCatalog, IN_SHOM_CATALOG_FILE_NAME);
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(SailingDirectionsViewerComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        List<SdShom> sdShoms = sdShomCatalog.getSdShoms();
        sdShoms.stream().filter((sd) -> (sd.getFilename() != null && sd.getFilename().length() != 0)).forEach((sd) -> {
            sdShomCatalogMap.put(sd.getName() + " : " + sd.getDescription(), sd.getFilename());
        });
        sdComboBox.getItems().clear();
        sdComboBox.setItems(FXCollections.observableList(new ArrayList<>(sdShomCatalogMap.keySet())));

        sdComboBox.setOnAction((event) -> {
            String filename = sdShomCatalogMap.get(sdComboBox.getSelectionModel().getSelectedItem().toString());

            ShomSailingDirections shomSailingDirections = new ShomSailingDirections(ROOT + filename);
            showPoi(shomSailingDirections.getPoiMap());

            instrument.off();

            Point point = shomSailingDirections.getCentroid();
            wwd.getView().goTo(Position.fromDegrees(point.getX(), point.getY()), POI_ALTITUDE);
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
}
