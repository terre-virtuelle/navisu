/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoEditorImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.WorldWindow;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RoutePhotoEditorController
        extends Widget2DController {

    private final RoutePhotoEditorImpl instrument;
    private final String FXML = "routephotoeditor.fxml";

    private String routeName;
    private WorldWindow wwd;
    private RoutePhotoEditorController routePhotoEditorController;

    private final S57ChartServices s57ChartServices;
    private GuiAgentServices guiAgentServices;
    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public TextField latTF;
    @FXML
    public TextField lonTF;
    @FXML
    public TextField altTF;
    @FXML
    public TextField focalLengthTF;
    @FXML
    public TextField headingTF;
    @FXML
    public TextField widthTF;
    @FXML
    public TextField curEyePointXTF;
    @FXML
    public TextField curEyePointYTF;
    @FXML
    public TextField curEyePointZTF;
    @FXML
    public TextField curEyePointTTF;
    @FXML
    public TextField curEyePositionXTF;
    @FXML
    public TextField curEyePositionYTF;
    @FXML
    public TextField curEyePositionZTF;
    @FXML
    public TextField farClipDistanceTF;
    @FXML
    public TextField fieldOfViewTF;
    @FXML
    public TextField forwardVectorXTF;
    @FXML
    public TextField forwardVectorYTF;
    @FXML
    public TextField forwardVectorZTF;
    @FXML
    public TextField forwardVectorTTF;
    @FXML
    public TextField horizonDistanceTF;
    @FXML
    public TextField nearClipDistanceTF;
    @FXML
    public TextField pitchTF;
    @FXML
    public TextField rollTF;
    @FXML
    public TextField upVectorXTF;
    @FXML
    public TextField upVectorYTF;
    @FXML
    public TextField upVectorZTF;
    @FXML
    public TextField upVectorTTF;
    @FXML
    public TextField viewportXTF;
    @FXML
    public TextField viewportYTF;
    @FXML
    public TextField viewportZTF;
    @FXML
    public TextField viewportTTF;
    @FXML
    public TextField routeTF;
    @FXML
    public TextField photoTF;
    @FXML
    public Button gotoButton;
    @FXML
    public Button saveButton;

    public RoutePhotoEditorController(RoutePhotoEditorImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.instrument = instrument;
        this.s57ChartServices = instrument.getS57ChartServices();
        this.guiAgentServices = instrument.getGuiAgentServices();
        wwd = GeoWorldWindViewImpl.getWW();

        load(FXML);
       // setTranslateX(225.0);

        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);

        });
        gotoButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("gotoButton not implemented");
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("saveButton not implemented");
        });

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
        view.setOpacity(0.8);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
    }

}
