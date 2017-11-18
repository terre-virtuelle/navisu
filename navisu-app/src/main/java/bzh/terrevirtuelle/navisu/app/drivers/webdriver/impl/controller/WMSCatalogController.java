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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

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

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private final String FXML = "catalogListView.fxml";
    protected String viewgroupstyle = "cataloglistview.css";
    private final String SITES = "data/wms/wms.txt";
    @FXML
    public Group view;
    @FXML
    public Pane viewpane;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public ListView listView;
    @FXML
    public TextField urlTextField;
    @FXML
    public Button gotoButton;

    private final GuiAgentServices guiAgentServices;
    private WebDriver driver;
    private List<String> values;

    public WMSCatalogController(GuiAgentServices guiAgentServices, WebDriver driver) {
        this.guiAgentServices = guiAgentServices;
        this.driver = driver;
        values = new ArrayList<>();
        Path path = Paths.get(SITES);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> values.add(s));
        } catch (IOException ex) {
            Logger.getLogger(WMSCatalogController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
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
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);
        viewpane.setOpacity(0.8);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(opacitySlider.getValue());
            });
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        gotoButton.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
                driver.open(progressHandle, urlTextField.getText());
            });
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
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
