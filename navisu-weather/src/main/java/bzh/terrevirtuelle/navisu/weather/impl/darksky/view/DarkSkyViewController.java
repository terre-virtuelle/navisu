/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.view;

import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.*;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DarkSkyViewController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group weatherPanel;
    @FXML
    public ImageView quit;
    @FXML
    Text title;
    @FXML
    Label latitudeLabel;
    String FXML = "weatherViewPanel.fxml";

    public DarkSkyViewController(DarkSkyComponentController darkSkyComponentController,
            GazetteerComponentServices gazetteerComponentServices,
            List<String> languageList,
            List<String> unitList,
            List<String> countryList) {

        setMouseTransparent(false);
        load();
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });

    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
