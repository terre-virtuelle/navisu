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
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Chapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.TextPart;
import bzh.terrevirtuelle.navisu.domain.util.Degrees;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.sailingdirections.impl.SailingDirectionsComponentImpl;
//import bzh.terrevirtuelle.navisu.navigation.util.WWJ_JTS;
//import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    private final String IN_D22 = "data/sd/shom/d22.xml";
    private final WorldWindow wwd;
    private final SailingDirectionsComponentImpl instrument;
    private final GuiAgentServices guiAgentServices;
    private final S57ChartComponentServices s57ChartComponentServices;
    private final LayersManagerServices layersManagerServices;

    protected RenderableLayer sailingDirectionsPgonLayer;
    protected RenderableLayer sailingDirectionsIconsLayer;
    private NavigationDataSet navigationDataSet;
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
        makePanel();
        sailingDirectionsPgonLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_1);
        sailingDirectionsPgonLayer.setPickEnabled(false);
        sailingDirectionsIconsLayer = layersManagerServices.getInstance(GROUP_NAME, LAYER_NAME_0);
    }

    private void makePanel() {

        c2aButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
        });
        c2bButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
        });
        d21Button.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
        });
        d22Button.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
        });
        d23Button.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Work in progress");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
