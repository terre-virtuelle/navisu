/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.slider;

import bzh.terrevirtuelle.navisu.widgets.Widget2D;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

/**
 * @date 16 avril 2015
 * @author Serge Morvan
 */
public class SliderController
        extends Widget2DController {

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    @FXML
    public Group sliderPanel;
    @FXML
    public Slider slider;
    @FXML
    public Button quit;

    String filename;
    static String DEFAULT_FILE_NAME = "SliderPanel.fxml";
    private String uri = CSS_STYLE_PATH + "slider.css";

    public SliderController() {
        this(DEFAULT_FILE_NAME);
    }

    public SliderController(String filename) {
        setMouseTransparent(false);
        this.filename = filename;
        load();
    }

    public SliderController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        filename = DEFAULT_FILE_NAME;
        setMouseTransparent(false);
        load();
    }

    public SliderController(String filename, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.filename = filename;
        setMouseTransparent(false);
        load();
    }

    private void load() {

        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filename));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        sliderPanel.getStylesheets().add(uri);
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            //System.out.println("..............");
        });

        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
    }

    public Button getQuit() {
        return quit;
    }

    public Slider getSlider() {
        return slider;
    }

}
