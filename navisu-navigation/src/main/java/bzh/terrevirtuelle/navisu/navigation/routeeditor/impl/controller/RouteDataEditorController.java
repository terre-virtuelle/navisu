/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
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
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import java.util.ArrayList;

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
    private final S57ChartComponentServices s57ChartServices;
    private final LayersManagerServices layersManagerServices;
    private NavigationDataSet navigationDataSet;
    private List<NavigationalWarnings> avurnavList;
    private List<SailingDirections> sailingDirectionsList;
    private List<Gpx> gpxList;
    private File file;
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
            S57ChartComponentServices s57ChartServices,
            String groupName, String layerName,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.layersManagerServices = layersManagerServices;
        this.guiAgentServices = guiAgentServices;
        this.s57ChartServices = s57ChartServices;
        navigationDataSet = new NavigationDataSet();
        if(sailingDirectionsList==null){
            sailingDirectionsList=new ArrayList<>();
        }
        if(avurnavList==null){
            avurnavList=new ArrayList<>();
        }
        
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
            file = IO.fileChooser(guiAgentServices.getStage(),
                    "privateData/nds", "NDS files (*.nds)", "*.nds", "*.NDS");
            try {
                navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
            } catch (FileNotFoundException | JAXBException ex) {
                Logger.getLogger(RouteDataEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sailingDirectionsList = navigationDataSet.get(SailingDirections.class);
            gpxList = navigationDataSet.get(Gpx.class);
            avurnavList = navigationDataSet.get(NavigationalWarnings.class);
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            if (navigationDataSet != null) {
                try {
                    ImportExportXML.exports(navigationDataSet, file);
                } catch (JAXBException | FileNotFoundException ex) {
                    Logger.getLogger(RouteDataEditorController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                   // System.out.println("RouteDataEditorController " + ex);
                }
            }
        });
        dataTextArea.setWrapText(true);
        dataTextArea.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            dataTextArea.setScrollTop(Double.MAX_VALUE);
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
                // dataTextArea.setText(landmarks.valueProperty().get());
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

    public void avurnavPrint(List<NavigationalWarnings> avurnavList) {
        String[] tmp;
        String str = "";
        for (NavigationalWarnings a : avurnavList) {
            if (a.getId() != 0) {
                tmp = new String[5];
                tmp[0] = Translator.tr("navigation.avurnav.avurnav") + " "
                        + Translator.tr("navigation.avurnav.id").toLowerCase() + " : " + a.getId() + "\n";
                tmp[1] = Translator.tr("navigation.avurnav.globalZone") + " : " + a.getGlobalZone() + "\n";
                tmp[2] = Translator.tr("navigation.avurnav.broadcastTime") + " : " + a.getBroadcastTime() + "\n";
                tmp[3] = Translator.tr("navigation.avurnav.expirationDate") + " : " + a.getExpirationDate() + "\n";
                tmp[4] = Translator.tr("navigation.avurnav.description") + " : " + a.getDescription() + "\n\n";
                tmp[4] = tmp[4].replace("\t", "");
                str += tmp[0] + tmp[1] + tmp[2] + tmp[3] + tmp[4];
            }
        }
        dataTextArea.setText(str);
    }

    public void sailingDirectionsPrint(List<SailingDirections> sailingDirectionsList) {
        String[] tmp;
        String str = "";
        for (SailingDirections a : sailingDirectionsList) {
            if (a.getId() != 0) {
                tmp = new String[4];
                tmp[0] = Translator.tr("navigation.sailingDirections.sailingDirections") + " "
                        + Translator.tr("navigation.sailingDirections.id").toLowerCase() + " : " + a.getId() + "\n";
             //   tmp[1] = Translator.tr("navigation.sailingDirections.zoneName") + " : " + a.getZoneName()+ "\n";
                tmp[2] = Translator.tr("navigation.sailingDirections.book") + " : " + a.getBook() + "\n";
              //  tmp[3] = Translator.tr("navigation.sailingDirections.description") + " : " + a.getDescription() + "\n\n";
                tmp[3] = tmp[3].replace("\t", "");
                str += tmp[0] + tmp[1] + tmp[2] + tmp[3];
            }
        }
        dataTextArea.setText(str);
    }

    public void setNavigationDataSet(NavigationDataSet navigationDataSet) {
        this.navigationDataSet = navigationDataSet;
    }
    
}
