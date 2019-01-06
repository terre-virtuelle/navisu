/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.buildings.osm.impl.controller;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * NaVisu project
 *
 * @date Jan 3, 2019 11:03:46 AM
 * @author Serge Morvan
 */
public class OsmComponentController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group view;
    @FXML
    public Pane viewPane;
    @FXML
    public Button quit;
    @FXML
    public Button requestButton;
    @FXML
    public Button helpButton;

    public OsmComponentController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
