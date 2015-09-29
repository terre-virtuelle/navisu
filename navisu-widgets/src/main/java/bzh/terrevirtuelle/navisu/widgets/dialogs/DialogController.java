/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.dialogs;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DialogController
        extends Widget2DController {

    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public TextField label;
    private String text;
    private final String FXML = "dialog.fxml";
    private final Scene scene;
    private final StackPane pane;

    public DialogController(Scene scene, StackPane pane, String text) {
        this.scene = scene;
        this.pane = pane;
        this.text = text;
        load(FXML);  
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
        label.setText(text);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            scene.removeEventFilter(KeyEvent.KEY_RELEASED, this);
            pane.getChildren().remove(this);
            setVisible(false);
        });
    }

}
