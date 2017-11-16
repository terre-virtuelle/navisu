/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.slider;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;

import java.io.IOException;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.Polygon;

/**
 * @date 16 avril 2015
 * @author Serge Morvan
 */
public class ButtonController
        extends Widget2DController {
private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
private final String FXML = "buttonSlider.fxml";
protected String viewgroupstyle = "rightSlider.css";

    @FXML
    public Group rightSlider;
    @FXML
    public Polygon button;
    boolean color = true;
    String filename;
    static String DEFAULT_FILE_NAME = "buttonSlider.fxml";
    private String css = System.getProperty("user.dir");

    public ButtonController() {
        this(DEFAULT_FILE_NAME);
    }

    public ButtonController(String filename) {
        setMouseTransparent(false);
        this.filename = filename;
        load();
    }

    public ButtonController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        filename = DEFAULT_FILE_NAME;
        setMouseTransparent(false);
        load();
    }

    public ButtonController(String filename, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.filename = filename;
        setMouseTransparent(false);
        load();
    }

    private void load() {

        /* ----------- Répertoire des css dans le launcher -----------------*/
        //File cssfile = new File("css/slider.css");
        //css = ("file:///" + cssfile.getAbsolutePath().replace("\\", "/"));
        //System.out.println(css);
        /*---------------------------------------*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filename));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        rightSlider.getStylesheets().add(uri);

        button.setOnMouseClicked((MouseEvent t) -> {
            if (color == true) {
                button.setFill(Color.RED);
                color=false;
            }else if (color == false) {
                button.setFill(Color.web("#1fff38",1.0));
                color=true;
            }
        });

    }

}
