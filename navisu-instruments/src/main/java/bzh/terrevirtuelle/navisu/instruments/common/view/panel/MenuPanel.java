/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.view.panel;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Serge
 */
public class MenuPanel
        extends Widget2DController
        implements Initializable {
private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "radarmenu.css";
@FXML
    public Group view;
    @FXML
    public Button quit;
    @FXML
    public Slider slider;
    @FXML
    public Pane viewpane;
    protected GuiAgentServices guiAgentServices;

    public MenuPanel(GuiAgentServices guiAgentServices, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.guiAgentServices = guiAgentServices;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);

        quit.setOnMouseClicked((MouseEvent event) -> {
            view.setVisible(false);
        });
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(slider.getValue());
            });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO 

    }

}
