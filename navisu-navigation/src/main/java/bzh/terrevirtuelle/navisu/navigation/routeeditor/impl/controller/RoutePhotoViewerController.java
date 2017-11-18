/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoViewerImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RoutePhotoViewerController
        extends Widget2DController {

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private GuiAgentServices guiAgentServices;
    private final RoutePhotoViewerImpl instrument;
    private final String FXML = "routephotoviewer.fxml";
    protected String viewgroupstyle = "routeeditor.css";
    @FXML
    public Group view;
    @FXML
    public Pane viewpane;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public ImageView imageView;

    public RoutePhotoViewerController(RoutePhotoViewerImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        this.guiAgentServices = instrument.getGuiAgentServices();
        load(FXML);
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
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
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);
        viewpane.setOpacity(0.8);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(opacitySlider.getValue());
            });
        });
    }

    public void load(Image image) {
        Platform.runLater(() -> {
            imageView.setImage(image);
        });
    }

}
