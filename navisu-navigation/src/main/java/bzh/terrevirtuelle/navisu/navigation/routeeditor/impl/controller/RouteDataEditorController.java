/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RouteEditorImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * NaVisu
 *
 * @date 21 oct. 2015
 * @author Serge Morvan
 */
public class RouteDataEditorController
        extends Widget2DController {

    private final RouteEditorImpl instrument;
    private final String FXML = "routedataeditor.fxml";
    private GuiAgentServices guiAgentServices;
    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public TreeView dataTreeView;
    @FXML
    public TextArea highTextArea;
    @FXML
    public TextArea lowTextArea;

    public RouteDataEditorController(RouteEditorImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.instrument = instrument;
        guiAgentServices = instrument.getGuiAgentServices();
        load(FXML);
        create();
        setTranslateX(-225.0);
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
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
    }

    final void create() {
        dataTreeView.setEditable(true);
        dataTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Data");
        root.setExpanded(true);

        CheckBoxTreeItem<String> landmarks = new CheckBoxTreeItem<>("Landmarks");
        landmarks.setExpanded(true);
        root.getChildren().add(landmarks);

        CheckBoxTreeItem<String> church = new CheckBoxTreeItem<>("Church");
        landmarks.getChildren().add(church);
        CheckBoxTreeItem<String> lightHouse = new CheckBoxTreeItem<>("LightHouse");
        landmarks.getChildren().add(lightHouse);
        CheckBoxTreeItem<String> tower = new CheckBoxTreeItem<>("Tower");
        landmarks.getChildren().add(tower);
        CheckBoxTreeItem<String> waterTower = new CheckBoxTreeItem<>("WaterTower");
        landmarks.getChildren().add(waterTower);

        CheckBoxTreeItem<String> externalEnvironnement = new CheckBoxTreeItem<>("ExternalEnvironnement");
        externalEnvironnement.setExpanded(true);
        root.getChildren().add(externalEnvironnement);

        CheckBoxTreeItem<String> current = new CheckBoxTreeItem<>("Current");
        externalEnvironnement.getChildren().add(current);
        CheckBoxTreeItem<String> mto = new CheckBoxTreeItem<>("MTO");
        externalEnvironnement.getChildren().add(mto);
        CheckBoxTreeItem<String> tide = new CheckBoxTreeItem<>("Tide");
        externalEnvironnement.getChildren().add(tide);

        CheckBoxTreeItem<String> avurnav = new CheckBoxTreeItem<>("Avurnav");
        root.getChildren().add(avurnav);

        CheckBoxTreeItem<String> nauticalDocumentation = new CheckBoxTreeItem<>("NauticalDocumentation");
        nauticalDocumentation.setExpanded(true);
        root.getChildren().add(nauticalDocumentation);

        CheckBoxTreeItem<String> sailingDirection = new CheckBoxTreeItem<>("SailingDirection");
        nauticalDocumentation.getChildren().add(sailingDirection);
        CheckBoxTreeItem<String> listOfLights = new CheckBoxTreeItem<>("ListOfLights");
        nauticalDocumentation.getChildren().add(listOfLights);
        CheckBoxTreeItem<String> listOfCharts = new CheckBoxTreeItem<>("ListOfCharts");
        nauticalDocumentation.getChildren().add(listOfCharts);

        CheckBoxTreeItem<String> other = new CheckBoxTreeItem<>("Other");
        root.getChildren().add(other);

        dataTreeView.setRoot(root);
        /*
         dataTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

         @Override
         public void changed(ObservableValue observable, Object oldValue,
         Object newValue) {

         TreeItem<String> selectedItem = (TreeItem<String>) newValue;
         System.out.println("Selected Text : " + selectedItem.getValue());
         // do what ever you want 
         }

         });
         */
        root.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + root.valueProperty().get());
            }
        });
        landmarks.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + landmarks.valueProperty().get());
                highTextArea.setText(landmarks.valueProperty().get());
            }
        });
        church.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + church.valueProperty().get());
            }
        });
        lightHouse.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + lightHouse.valueProperty().get());
            }
        });
        tower.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + tower.valueProperty().get());
            }
        });
        waterTower.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + waterTower.valueProperty().get());
            }
        });
        externalEnvironnement.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + externalEnvironnement.valueProperty().get());
            }
        });
        current.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + current.valueProperty().get());
            }
        });
        mto.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + mto.valueProperty().get());
            }
        });
        tide.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + tide.valueProperty().get());
            }
        });
        avurnav.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + avurnav.valueProperty().get());
            }
        });
        nauticalDocumentation.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + nauticalDocumentation.valueProperty().get());
            }
        });
        sailingDirection.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + sailingDirection.valueProperty().get());
            }
        });
        listOfLights.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + listOfLights.valueProperty().get());
            }
        });
        listOfCharts.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + listOfCharts.valueProperty().get());
            }
        });
        other.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + other.valueProperty().get());
            }
        });
    }
}
