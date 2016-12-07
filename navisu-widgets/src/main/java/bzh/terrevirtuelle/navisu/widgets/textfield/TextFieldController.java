/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.textfield;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class TextFieldController
        extends Widget2DController {
    
    @FXML
    public Group web;
    @FXML
    TextField server;
    @FXML
    Slider transSlider ;
    
    
    public TextFieldController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WebServerURL.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
       
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public void setServer(String server) {
        this.server.setText(server);
    }

    public TextField getTextField() {
        return server;
    }
    
    public void setStyleTextField(String style) {
        web.setStyle(style);
    }

}
