/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.textlist;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class ListController
        extends Widget2DController {

    @FXML
    public Group view;
    @FXML
    public Button quit;
    @FXML
    public Pane viewpane;
    @FXML
    public ListView listView;
    @FXML
    public Text titleText;
    @FXML
    public Label visibleLabel;
    @FXML
    Slider opacitySlider;
    String DEFAULT = "meteoListView.fxml";
    String filename;
public static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "textlistpanel.css";
    public ListController() {
        filename = DEFAULT;
        setMouseTransparent(false);
        load();
    }

    public ListController(String filename) {
        this.filename = filename;
        setMouseTransparent(false);
        load();
    }

    public ListController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        filename = DEFAULT;
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
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        view.getStylesheets().add(uri);
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                viewpane.setOpacity(opacitySlider.getValue());
               // listView.setOpacity(opacitySlider.getValue());
            });
        });
        listView.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
        });
    }

    public Pane getView() {
        return viewpane;
    }

    public Button getQuit() {
        return quit;
    }

    public ListView getListView() {
        return listView;
    }

    public Text getTitleText() {
        return titleText;
    }

    public Label getVisibleLabel() {
        return visibleLabel;
    }

}
