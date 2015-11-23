/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.SailingDirections;
import bzh.terrevirtuelle.navisu.ontology.rdf.controller.RdfParser;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.xml.bind.JAXBException;

/**
 * NaVisu
 *
 * @date 21 oct. 2015
 * @author Serge Morvan
 */
public class RouteDataEditorController
        extends Widget2DController {

    private final String FXML = "routedataeditor.fxml";
    private final GuiAgentServices guiAgentServices;
    private final S57ChartServices s57ChartServices;
    private final LayersManagerServices layersManagerServices;
    private NavigationDataSet navigationDataSet;
    private List<Avurnav> avurnavList;
    private List<SailingDirections> sailingDirectionsList;
    private List<Gpx> gpxList;
    @FXML
    public Pane view;
    @FXML
    public Button quit;
    @FXML
    public Label visibleLabel;
    @FXML
    public Button openButton;
    @FXML
    public Button saveButton;
    @FXML
    public Slider opacitySlider;
    @FXML
    public TreeView dataTreeView;
    @FXML
    public TextArea dataTextArea;

    public RouteDataEditorController(
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            S57ChartServices s57ChartServices,
            String groupName, String layerName,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.layersManagerServices = layersManagerServices;
        this.guiAgentServices = guiAgentServices;
        this.s57ChartServices = s57ChartServices;
        navigationDataSet = new NavigationDataSet();
        load(FXML);
        create();
        setTranslateX(-220.0);
        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);
        });
        visibleLabel.setText("Ctrl " + keyCode.toString());
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
        openButton.setOnMouseClicked((MouseEvent event) -> {
            File file = IO.fileChooser(guiAgentServices.getStage(),
                    "privateData/nds", "NDS files (*.nds)", "*.nds", "*.NDS");
            try {
                navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
            } catch (FileNotFoundException | JAXBException ex) {
                Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sailingDirectionsList = navigationDataSet.get(SailingDirections.class);
            gpxList = navigationDataSet.get(Gpx.class);
            avurnavList = navigationDataSet.get(Avurnav.class);
            //  System.out.println(sailingDirectionsList);
            // System.out.println(gpxList);
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Nor yet implemented :-) ");
        });
    }

    final void create() {
        dataTreeView.setEditable(true);
        dataTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Data");
        root.setExpanded(true);

        CheckBoxTreeItem<String> avurnav = new CheckBoxTreeItem<>("Avurnav");
        root.getChildren().add(avurnav);

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

        CheckBoxTreeItem<String> nauticalDocumentation = new CheckBoxTreeItem<>("NauticalDocumentation");
        nauticalDocumentation.setExpanded(true);
        root.getChildren().add(nauticalDocumentation);
        CheckBoxTreeItem<String> sailingDirection = new CheckBoxTreeItem<>("SailingDirection");
        nauticalDocumentation.getChildren().add(sailingDirection);
        CheckBoxTreeItem<String> listOfLights = new CheckBoxTreeItem<>("ListOfLights");
        nauticalDocumentation.getChildren().add(listOfLights);
        CheckBoxTreeItem<String> listOfCharts = new CheckBoxTreeItem<>("ListOfCharts");
        nauticalDocumentation.getChildren().add(listOfCharts);

        CheckBoxTreeItem<String> externalEnvironnement = new CheckBoxTreeItem<>("ExternalEnvironnement");
        externalEnvironnement.setExpanded(true);
        root.getChildren().add(externalEnvironnement);
        CheckBoxTreeItem<String> current = new CheckBoxTreeItem<>("Current");
        externalEnvironnement.getChildren().add(current);
        CheckBoxTreeItem<String> mto = new CheckBoxTreeItem<>("MTO");
        externalEnvironnement.getChildren().add(mto);
        CheckBoxTreeItem<String> tide = new CheckBoxTreeItem<>("Tide");
        externalEnvironnement.getChildren().add(tide);

        CheckBoxTreeItem<String> other = new CheckBoxTreeItem<>("Other");
        root.getChildren().add(other);
        dataTreeView.setRoot(root);

        root.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + root.valueProperty().get());
            }
        });
        landmarks.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("The selected item is " + landmarks.valueProperty().get());
                dataTextArea.setText(landmarks.valueProperty().get());
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
                File file = IO.fileChooser(guiAgentServices.getStage(),
                        "data/rdf", "RDF files (*.rdf)", "*.rdf", "*.RDF");
                RdfParser rdfParser = new RdfParser(file);
                NavigationDataSet tmp = rdfParser.parse();
                navigationDataSet.addAll(tmp.getNavigationDataList());
                System.out.println(navigationDataSet);
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
