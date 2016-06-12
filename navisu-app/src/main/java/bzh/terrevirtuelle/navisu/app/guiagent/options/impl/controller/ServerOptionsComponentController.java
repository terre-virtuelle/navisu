/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.ServerOptionsComponentImpl;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ServerOptionsComponentController
        extends Widget2DController
        implements Initializable {

    GuiAgentServices guiAgentServices;
    DataServerServices dataServerServices;

    private final String FXML = "serverOptionsController.fxml";
    private static ServerOptionsComponentController INSTANCE;
    private final ServerOptionsComponentImpl component;
    @FXML
    public Pane view;
    @FXML
    public Tab serialT;
    @FXML
    public Tab networkT;
    @FXML
    public Button quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public Button addConnectionB;
    @FXML
    public Button removeConnectionB;
    @FXML
    public ChoiceBox portNameCB;
    @FXML
    public ChoiceBox baudRateCB;
    @FXML
    public ChoiceBox databitsCB;
    @FXML
    public ChoiceBox stopBitsCB;
    @FXML
    public ChoiceBox parityCB;
    @FXML
    public RadioButton tcpRB;
    @FXML
    public RadioButton udpRB;
    @FXML
    public RadioButton gpsdRB;
    @FXML
    public TextField dataPortTF;
    @FXML
    public TextField addressTF;
    @FXML
    public TableView connectionsTV;
    @FXML
    public TableColumn typeTC;
    @FXML
    public TableColumn dataPortTC;
    @FXML
    public TableColumn parametersTC;
    @FXML
    public TableColumn statusTC;
    @FXML
    public TabPane parameterTP;
    String[] serialPortNames;
    private final ObservableList<Connection> connections = FXCollections.observableArrayList();

    /**
     *
     * @param component
     * @param keyCode
     * @param keyCombination
     * @param guiAgentServices
     */
    @SuppressWarnings("LeakingThisInConstructor")
    private ServerOptionsComponentController(ServerOptionsComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            DataServerServices dataServerServices) {
        super(keyCode, keyCombination);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ServerOptionsComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.dataServerServices = dataServerServices;
        serialPortNames = dataServerServices.getSerialPortNames();
        System.out.println("serialPortNames "+ serialPortNames);
            if (serialPortNames != null) {
                for (String pn : serialPortNames) {
                    portNameCB.setValue(pn);
                }
            }
    }

    public static ServerOptionsComponentController getInstance(ServerOptionsComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices, DataServerServices dataServerServices) {
        if (INSTANCE == null) {
            INSTANCE = new ServerOptionsComponentController(component, keyCode, keyCombination,
                    guiAgentServices, dataServerServices);
        }
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, INSTANCE);
        guiAgentServices.getRoot().getChildren().add(INSTANCE);
        return INSTANCE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
        serialT.setUserData("Serial");
        networkT.setUserData("Network");
        connectionsTV.setItems(connections);

        typeTC.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        dataPortTC.setCellValueFactory(
                new PropertyValueFactory<>("dataPort"));
        parametersTC.setCellValueFactory(
                new PropertyValueFactory<>("parameters"));
        statusTC.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        addConnectionB.setOnMouseClicked((MouseEvent event) -> {

            serialPortNames = dataServerServices.getSerialPortNames();
            if (serialPortNames != null) {
                for (String pn : serialPortNames) {
                    portNameCB.setValue(pn);
                }
            }
            String parameters;
            String portName = null;
            String type = parameterTP.getSelectionModel().getSelectedItem().getUserData().toString().trim();
            String baudRate = baudRateCB.getValue().toString().trim();
            String databits = databitsCB.getValue().toString().trim();
            String stopBits = stopBitsCB.getValue().toString().trim();
            String parity = parityCB.getValue().toString();
            if (type.equals("Serial")) {
                if (portNameCB.getValue() != null) {
                    portName = portNameCB.getValue().toString();
                    if (portName != null) {
                        parameters = baudRate + "/"
                                + databits + "/"
                                + stopBits + "/"
                                + parity;
                        connections.add(new Connection(type, portName, parameters, "Open"));
                    }
                }
            } else if (type.equals("Network")) {

            }
        });

    }
}
