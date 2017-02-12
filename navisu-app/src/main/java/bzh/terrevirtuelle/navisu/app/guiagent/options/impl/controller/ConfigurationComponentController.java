/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.ConfigurationComponentImpl;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ConfigurationComponentController
        extends Widget2DController
        implements Initializable {

    GuiAgentServices guiAgentServices;
    DataServerServices dataServerServices;

    private final String FXML = "configurationController.fxml";
    private static ConfigurationComponentController INSTANCE;
    private final ConfigurationComponentImpl component;
    @FXML
    public Pane view;
    
    @FXML
    public ImageView quit;
    
    
    /**
     *
     * @param component
     * @param keyCode
     * @param keyCombination
     * @param guiAgentServices
     */
    @SuppressWarnings("unchecked")
    private ConfigurationComponentController(ConfigurationComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices) {
        super(keyCode, keyCombination);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.dataServerServices = dataServerServices;
        
    }

    public static ConfigurationComponentController getInstance(ConfigurationComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices) {
        if (INSTANCE == null) {
            INSTANCE = new ConfigurationComponentController(component, keyCode, keyCombination,
                    guiAgentServices);
        }
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, INSTANCE);
        guiAgentServices.getRoot().getChildren().add(INSTANCE);
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        
    }
}
