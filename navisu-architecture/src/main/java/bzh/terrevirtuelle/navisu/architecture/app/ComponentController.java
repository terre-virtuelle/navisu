/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 */
public class ComponentController implements Initializable {

    @FXML
    public StackPane root;
    @FXML
    public ListView<String> inOutLV;
    @FXML
    public ListView<String> modulesLV;
    @FXML
    public ListView<String> componentsLV;
    @FXML
    public ListView<String> modules1LV;
    @FXML
    public Label label;

    private Map<String, List<Component>> componentsMap;

    public ComponentController(Map<String, List<Component>> componentsMap) {
        this.componentsMap = componentsMap;
    }

    public StackPane getRoot() {
        return root;
    }

    public void initGUI(Map<String, List<Component>> componentMap) {

        Set<String> modules = componentMap.keySet();
        
        List<String> list = new ArrayList<>();
        list.addAll(modules);
        Collections.sort(list);
        ObservableList<String> oList = FXCollections.observableArrayList(list);

        System.out.println("oList : " + oList);
        modulesLV.setItems(oList);
        System.out.println("modulesLV : " + modulesLV);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        System.out.println("componentsMap : " + componentsMap);
        initGUI(componentsMap);
    }

}
