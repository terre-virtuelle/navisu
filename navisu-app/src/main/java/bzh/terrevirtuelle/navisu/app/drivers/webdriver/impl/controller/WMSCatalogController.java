/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date Dec 7, 2016
 */
public class WMSCatalogController
        extends Widget2DController
        implements Initializable {

    private final String FXML = "catalogListView.fxml";
    @FXML
    public Pane view;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public ListView listView;
    private final GuiAgentServices guiAgentServices;
    private WebDriver driver;
    List<String> values = Arrays.asList(
            "http://www.ifremer.fr/services/wms/oceanographie_physique",
            "http://www.ifremer.fr/services/wms/biologie",
            "http://www.ifremer.fr/services/wms/geosciences",
            "http://www.ifremer.fr/ifremerWS/WS/wms/MNT?SERVICE=WMS&VERSION=1.1.1",
            "http://www.ifremer.fr/services/wms/surveillance_littorale",
            "http://www.ifremer.fr/services/photos_anciennes",
            "http://www.ifremer.fr/services/wms/granulats_marins",
            "http://www.ifremer.fr/services/wms/sih_referentiels",
            "http://neowms.sci.gsfc.nasa.gov/wms/wms",
            "http://sedac.ciesin.columbia.edu/geoserver/wcs",
            "http://csw.geopole.org/?SERVICE=wcs",
            "http://neowms.sci.gsfc.nasa.gov/wms/wms?SERVICE=WMS"
    );

    public WMSCatalogController(GuiAgentServices guiAgentServices, WebDriver driver) {
        this.guiAgentServices = guiAgentServices;
        this.driver = driver;
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
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        guiAgentServices.getRoot().getChildren().add(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        listView.setItems(FXCollections.observableList(values));
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
                driver.open(progressHandle, newValue.toString());
            });
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
        });
    }
}
