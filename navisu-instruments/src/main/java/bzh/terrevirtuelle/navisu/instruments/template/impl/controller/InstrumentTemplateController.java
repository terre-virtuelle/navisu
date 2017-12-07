/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.template.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.template.impl.InstrumentTemplateImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class InstrumentTemplateController
        extends Widget2DController
        implements Initializable {
private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private final String FXML = "InstrumentTemplate.fxml";
    String viewgroupstyle = "common.css";
    @FXML
    public Group view;
    @FXML
    public Pane quit;

    protected InstrumentTemplateImpl instrument;
    protected ComponentManager cm = ComponentManager.componentManager;
    String uri = CSS_STYLE_PATH + viewgroupstyle;
    
    /*
     Events subscribe zone
    
     */

    public InstrumentTemplateController(InstrumentTemplateImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        subscribe();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        view.getStylesheets().add(uri);
        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void subscribe() {

    }
}
