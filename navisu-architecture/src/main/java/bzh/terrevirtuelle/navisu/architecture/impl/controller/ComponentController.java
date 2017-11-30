/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.controller;

import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentViewer;
import bzh.terrevirtuelle.navisu.architecture.impl.model.Selection;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 *
 * @author serge
 */
public class ComponentController
        implements Initializable {

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
    public Button computeButton;
    @FXML
    public Button clearIOButton;
    @FXML
    public Button clearModulesButton;
    @FXML
    public Button clearComponentsButton;
    @FXML
    public Button helpButton;
    @FXML
    public Button snapshotButton;

    private final Map<String, List<Component>> componentsMap;
    private final ComponentViewer componentViewer;

    public ComponentController(ComponentViewer componentViewer) {
        this.componentsMap = componentViewer.getComponentsMap();
        this.componentViewer = componentViewer;
    }

    public StackPane getRoot() {
        return root;
    }

    public void initGUI(Map<String, List<Component>> componentMap) {
        List<String> list = new ArrayList<>(Arrays.asList("Services", "Events"));
        ObservableList<String> oList = FXCollections.observableArrayList(list);
        inOutLV.setItems(oList);
        inOutLV.getSelectionModel().selectFirst();
        inOutLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Set<String> modules = componentMap.keySet();
        list.clear();
        list.addAll(modules);
        Collections.sort(list);
        oList = FXCollections.observableArrayList(list);
        modulesLV.setItems(oList);
        modulesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Collection<List<Component>> colComponents = componentMap.values();
        list.clear();
        colComponents.forEach((l) -> {
            l.forEach((c) -> {
                list.add(c.getName());
            });
        });
        Collections.sort(list);
        oList = FXCollections.observableArrayList(list);
        componentsLV.setItems(oList);
        componentsLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private Selection compute() {
        Selection selection = new Selection();
        //You must choose an item, Services by default
        if (inOutLV.getSelectionModel().getSelectedItems().isEmpty()) {
            inOutLV.getSelectionModel().selectFirst();
        }
        if (modulesLV.getSelectionModel().getSelectedItems().isEmpty()
                && componentsLV.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText("Visualisation de l'architecture en composants et en modules");
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            String s = "Vous devez sélectionner au moins un composant.";
            alert.setContentText(s);
            alert.show();
            return null;
        }
        selection.addInpuOutput(inOutLV.getSelectionModel().getSelectedItems());
        selection.addModules(modulesLV.getSelectionModel().getSelectedItems());
        selection.addComponents(componentsLV.getSelectionModel().getSelectedItems());

        componentViewer.runScene(selection);
        return selection;
    }

    private void initControl() {
        computeButton.setOnMouseClicked((MouseEvent event) -> {
            compute();
        });
        clearIOButton.setOnMouseClicked((MouseEvent event) -> {
            inOutLV.getSelectionModel().clearSelection();
        });
        clearModulesButton.setOnMouseClicked((MouseEvent event) -> {
            modulesLV.getSelectionModel().clearSelection();
        });
        clearComponentsButton.setOnMouseClicked((MouseEvent event) -> {
            componentsLV.getSelectionModel().clearSelection();
        });
        helpButton.setOnMouseClicked((MouseEvent event) -> {
            help();
        });
        snapshotButton.setOnMouseClicked((MouseEvent event) -> {
            componentViewer.export();
        });
    }
//TODO internationaliser

    private void help() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Visualisation de l'architecture en composants et en modules");
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        String s = "1-Liste Services-Events : sélection multiple possible.\n"
                + "Si aucun item n'est sélectioné, l'item Services est pris par défaut\n"
                + "2-Liste Modules : sélection multiple possible. \n"
                + "La dépendance de chacun des composants du module sera affichée.\n"
                + "Si aucun item n'est sélectionné. On doit sélectionner au moins un composant\n"
                + "dans la liste suivante.\n"
                + "3-Liste Components :  sélection unique.\n"
                + "\n"
                + "Ctrl+roulette : zoom\n"
                + "Double clic : arrangement des composants.";
        alert.setContentText(s);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGUI(componentsMap);
        initControl();
    }

}
