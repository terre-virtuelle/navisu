/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.metoffice.controller;

import bzh.terrevirtuelle.navisu.weather.impl.metoffice.view.WebViewPane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author serge
 */
public class MetOfficeController {

    public MetOfficeController() {
        Stage primaryStage = new Stage();
        Pane root = new WebViewPane();
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }
    
}
