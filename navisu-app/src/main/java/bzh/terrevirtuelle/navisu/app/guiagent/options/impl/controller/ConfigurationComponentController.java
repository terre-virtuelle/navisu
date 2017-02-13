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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private final String FXML = "configurationController.fxml";
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected Properties properties;
    private static ConfigurationComponentController INSTANCE;
    private final ConfigurationComponentImpl component;
    @FXML
    public Pane view;
    @FXML
    public ImageView quit;
    @FXML
    public Button okButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button defaultButton;
    @FXML
    public Button helpButton;
    @FXML
    public TextField s57TF;
    @FXML
    public TextField darkSkyTF;
    @FXML
    public TextField allCountriesTF;
    @FXML
    public TextField allCountriesIndexTF;
    String s57Path;
    String darkSkyKey;
    String allCountriesPath;
    String allCountriesIndexPath;
    String s57PathOld;
    String darkSkyKeyOld;
    String allCountriesPathOld;
    String allCountriesIndexPathOld;

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
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        darkSkyTF.setText(properties.getProperty("darkSkyApiKey").trim());
        s57TF.setText(properties.getProperty("s57ChartsDir").trim());
        allCountriesTF.setText(properties.getProperty("allCountriesPath").trim());
        allCountriesIndexTF.setText(properties.getProperty("luceneAllCountriesIndexPath").trim());

        s57PathOld = s57TF.getText().trim();
        darkSkyKeyOld = darkSkyTF.getText();
        allCountriesPathOld = allCountriesTF.getText();
        allCountriesIndexPathOld = allCountriesIndexTF.getText();

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        okButton.setOnMouseClicked((MouseEvent event) -> {
            s57Path = s57TF.getText();
            darkSkyKey = darkSkyTF.getText();
            allCountriesPath = allCountriesTF.getText();
            allCountriesIndexPath = allCountriesIndexTF.getText();
            save();
        });
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            s57TF.setText(s57PathOld);
            darkSkyTF.setText(darkSkyKeyOld);
            allCountriesTF.setText(allCountriesPathOld);
            allCountriesIndexTF.setText(allCountriesIndexPathOld);
            s57Path = s57TF.getText();
            darkSkyKey = darkSkyTF.getText();
            allCountriesPath = allCountriesTF.getText();
            allCountriesIndexPath = allCountriesIndexTF.getText();
            save();
        });
        defaultButton.setOnMouseClicked((MouseEvent event) -> {
            s57Path = "";
            darkSkyKey = "";
            allCountriesPath = "";
            allCountriesIndexPath = "";
            save();
        });
        helpButton.setOnMouseClicked((MouseEvent event) -> {

        });
    }

    private void save() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_NAME)) {
            properties.setProperty("s57ChartsDir", s57Path);
            properties.setProperty("darkSkyApiKey", darkSkyKey);
            properties.setProperty("allCountriesPath", allCountriesPath);
            properties.setProperty("luceneAllCountriesIndexPath", allCountriesIndexPath);
            properties.store(output, null);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
