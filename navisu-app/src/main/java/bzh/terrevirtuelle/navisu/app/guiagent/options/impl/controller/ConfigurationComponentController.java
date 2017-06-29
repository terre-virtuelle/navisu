/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.Option;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.OwnerShipOption;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.OwnerShipOptionBuilder;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.UserOption;
import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.UserOptionBuilder;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.ConfigurationComponentImpl;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ConfigurationComponentController
        extends Widget2DController
        implements Initializable {

    GuiAgentServices guiAgentServices;
    GazetteerComponentServices gazetteerComponentServices;
    DataServerServices dataServerServices;

    private final String FXML = "configurationController.fxml";
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";

    protected Properties properties;
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private static ConfigurationComponentController INSTANCE;
    private final ConfigurationComponentImpl component;
    protected String viewgroupstyle = "configuration.css";

    /* Common controls */
    @FXML
    public Group configgroup;
    @FXML
    public Pane view;
    @FXML
    public ImageView quit;
    @FXML
    public Button saveButton;
    @FXML
    public Button cancelButton;
    @FXML
    public Button defaultButton;
    @FXML
    public Button helpButton;

    /* user controls */
    @FXML
    public Tab userTab;
    @FXML
    public TextField s57TF;
    @FXML
    public TextField darkSkyTF;
    @FXML
    public TextField allCountriesTF;
    @FXML
    public TextField allCountriesIndexTF;
    @FXML
    public Button s57Button;
    @FXML
    public Button allCPathButton;
    @FXML
    public Button luceneButton;
    @FXML
    public Button buildIndexButton;

    /* Own ship controls */
    @FXML
    public Tab ownerShipTab;
    @FXML
    public TextField nameTF;
    @FXML
    public TextField mmsiTF;
    @FXML
    public TextField countryTF;
    @FXML
    public TextField lengthTF;
    @FXML
    public TextField widthTF;
    @FXML
    public TextField shipTypeTF;
    @FXML
    public TextField draughtTF;
    @FXML
    public TextField navigationalStatusTF;
    @FXML
    public TextField callSignTF;
    @FXML
    public TextField latitudeTF;
    @FXML
    public TextField longitudeTF;
    @FXML
    public TextField cogTF;
    @FXML
    public TextField sogTF;
    @FXML
    public Button browseButton;
    @FXML
    public TextField daeModelPathTF;
    @FXML
    public TextField scaleTF;

    /* Devices controls */
    @FXML
    public Tab devicesTab;
    @FXML
    public ChoiceBox<String> portNameCB;
    @FXML
    public ChoiceBox<String> baudRateCB;
    @FXML
    public ChoiceBox<String> dataBitsCB;
    @FXML
    public ChoiceBox<String> stopBitsCB;
    @FXML
    public ChoiceBox<String> parityCB;
    @FXML
    public TableView connectionTV;
    @FXML
    public TableColumn typeTC;
    @FXML
    public TableColumn dataPortTC;
    @FXML
    public TableColumn parametersTC;
    @FXML
    public TableColumn statusTC;
    @FXML
    public Button addbutton;
    @FXML
    public Button removebutton;
    @FXML
    public Button openbutton;
    @FXML
    public Button closebutton;

    String s57Path;
    String darkSkyKey;
    String allCountriesPath;
    String allCountriesIndexPath;

    String s57PathOld;
    String darkSkyKeyOld;
    String allCountriesPathOld;
    String allCountriesIndexPathOld;

    String name;
    String mmsi;
    String country;
    String length;
    String width;
    String draught;
    String shipType;
    String navigationalStatus;
    String electronicPositionDevice;
    String callSign;
    String latitude;
    String longitude;
    String cog;
    String sog;
    String daeModelPath;
    String modelScale;

    String nameOld;
    String mmsiOld;
    String countryOld;
    String lengthOld;
    String widthOld;
    String draughtOld;
    String shipTypeOld;
    String navigationalStatusOld;
    String callSignOld;
    String latitudeOld;
    String longitudeOld;
    String cogOld;
    String sogOld;
    String daeModelPathOld;
    String scaleOld;

    String portName;
    int baudRate;
    int dataBits;
    float stopBits;
    int parity;
    int portNameIndex;
    int baudRateIndex;
    int dataBitsIndex;
    int stopBitsIndex;
    int parityIndex;

    private ObservableList<String> portNameCbData = FXCollections.observableArrayList();

    protected FileChooser fileChooser;

    /**
     *
     * @param component
     * @param keyCode
     * @param keyCombination
     * @param guiAgentServices
     * @param gazetteerComponentServices
     * @param dataServerServices
     */
    @SuppressWarnings("unchecked")
    public ConfigurationComponentController(ConfigurationComponentImpl component,
            KeyCode keyCode, KeyCombination.Modifier keyCombination,
            GuiAgentServices guiAgentServices,
            GazetteerComponentServices gazetteerComponentServices,
            DataServerServices dataServerServices) {
        super(keyCode, keyCombination);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        configgroup.getStylesheets().add(uri);
        this.component = component;
        this.guiAgentServices = guiAgentServices;
        this.gazetteerComponentServices = gazetteerComponentServices;
        this.dataServerServices = dataServerServices;
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, this);
        guiAgentServices.getRoot().getChildren().add(this);
        String[] serialPortNames = dataServerServices.getSerialPortNames();
        //  System.out.println("serialPortNames " + serialPortNames);
        String tmp;
        portNameCbData.clear();
        if (portNameCB.getItems() != null) {
            portNameCB.getItems().clear();
        }
        if (serialPortNames != null) {
            portNameCbData.addAll(Arrays.asList(serialPortNames));
            portNameCB.getItems().addAll(portNameCbData);
            tmp = properties.getProperty("portNameIndex");
            if (tmp != null) {
                portNameIndex = Integer.parseInt(tmp);
                portNameCB.getSelectionModel().select(portNameIndex);
            }
        }
        tmp = properties.getProperty("baudRateIndex");
        if (tmp != null) {
            baudRateIndex = Integer.parseInt(tmp);
            baudRateCB.getSelectionModel().select(baudRateIndex);
        }
        tmp = properties.getProperty("dataBitsIndex");
        if (tmp != null) {
            dataBitsIndex = Integer.parseInt(tmp);
            dataBitsCB.getSelectionModel().select(dataBitsIndex);
        }
        tmp = properties.getProperty("stopBitsIndex");
        if (tmp != null) {
            stopBitsIndex = Integer.parseInt(tmp);
            stopBitsCB.getSelectionModel().select(stopBitsIndex);
        }
        tmp = properties.getProperty("parityIndex");
        if (tmp != null) {
            parityIndex = Integer.parseInt(tmp);
            parityCB.getSelectionModel().select(parityIndex);
        }

        //  OptionsEventTest optionsEventTest = new OptionsEventTest();
        //  optionsEventTest.subscribe();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));

        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        darkSkyTF.setText(properties.getProperty("darkSkyApiKey").trim());
        s57TF.setText(properties.getProperty("s57ChartsDir").trim());
        allCountriesTF.setText(properties.getProperty("allCountriesPath").trim());
        allCountriesIndexTF.setText(properties.getProperty("luceneAllCountriesIndexPath").trim());

        s57PathOld = s57TF.getText().trim();
        darkSkyKeyOld = darkSkyTF.getText();
        allCountriesPathOld = allCountriesTF.getText();
        allCountriesIndexPathOld = allCountriesIndexTF.getText();

        nameTF.setText(properties.getProperty("name").trim());
        mmsiTF.setText(properties.getProperty("mmsi").trim());
        countryTF.setText(properties.getProperty("country").trim());
        lengthTF.setText(properties.getProperty("length").trim());
        widthTF.setText(properties.getProperty("width").trim());
        shipTypeTF.setText(properties.getProperty("shipType").trim());
        draughtTF.setText(properties.getProperty("draught").trim());
        navigationalStatusTF.setText(properties.getProperty("navigationalStatus").trim());
        callSignTF.setText(properties.getProperty("callSign").trim());
        latitudeTF.setText(properties.getProperty("latitude").trim());
        longitudeTF.setText(properties.getProperty("longitude").trim());
        cogTF.setText(properties.getProperty("cog").trim());
        sogTF.setText(properties.getProperty("sog").trim());
        daeModelPathTF.setText(properties.getProperty("daeModelPath").trim());
        //Mise a jour de nouvelles proprietes
        if (properties.getProperty("scale") == null) {
            properties.setProperty("scale", "1.0");
        }
        scaleTF.setText(properties.getProperty("scale"));

        try {
            properties.store(new FileOutputStream(CONFIG_FILE_NAME), null);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nameOld = nameTF.getText().trim();
        mmsiOld = mmsiTF.getText().trim();
        countryOld = countryTF.getText().trim();
        lengthOld = lengthTF.getText().trim();
        widthOld = widthTF.getText().trim();
        draughtOld = shipTypeTF.getText().trim();
        shipTypeOld = draughtTF.getText().trim();
        navigationalStatusOld = navigationalStatusTF.getText().trim();
        callSignOld = callSignTF.getText().trim();
        latitudeOld = latitudeTF.getText().trim();
        longitudeOld = longitudeTF.getText().trim();
        cogOld = cogTF.getText().trim();
        sogOld = sogTF.getText().trim();
        daeModelPathOld = daeModelPathTF.getText().trim();
        scaleOld = scaleTF.getText().trim();

        quit.setOnMouseClicked((MouseEvent event) -> {
            component.off();
        });
        browseButton.setOnMouseClicked((MouseEvent event) -> {
            openFile(daeModelPathTF);
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            if (userTab.isSelected()) {
                s57Path = s57TF.getText();
                darkSkyKey = darkSkyTF.getText();
                allCountriesPath = allCountriesTF.getText();
                allCountriesIndexPath = allCountriesIndexTF.getText();
                saveUser();
            }
            if (ownerShipTab.isSelected()) {
                name = nameTF.getText();
                mmsi = mmsiTF.getText();
                country = countryTF.getText();
                length = lengthTF.getText();
                width = widthTF.getText();
                draught = shipTypeTF.getText();
                shipType = draughtTF.getText();
                navigationalStatus = navigationalStatusTF.getText();
                callSign = callSignTF.getText();
                latitude = latitudeTF.getText();
                longitude = longitudeTF.getText();
                cog = cogTF.getText();
                sog = sogTF.getText();
                daeModelPath = daeModelPathTF.getText();
                modelScale = scaleTF.getText();
                saveOwnerShip();
            }
            if (devicesTab.isSelected()) {

                portName = portNameCB.getSelectionModel().getSelectedItem();
                String baudRateS = baudRateCB.getSelectionModel().getSelectedItem();
                portNameIndex = baudRateCB.getSelectionModel().getSelectedIndex();
                switch (baudRateS) {
                    case "BAUDRATE_4800":
                        baudRate = 4800;
                        break;
                    case "BAUDRATE_9600":
                        baudRate = 9600;
                        break;
                    case "BAUDRATE_38400":
                        baudRate = 38400;
                        break;
                }

                String dataBitsS = dataBitsCB.getSelectionModel().getSelectedItem();
                dataBitsIndex = dataBitsCB.getSelectionModel().getSelectedIndex();
                switch (dataBitsS) {
                    case "DATABITS_5":
                        dataBits = 5;
                        break;
                    case "DATABITS_6":
                        dataBits = 6;
                        break;
                    case "DATABITS_7":
                        dataBits = 7;
                        break;
                    case "DATABITS_8":
                        dataBits = 8;
                        break;
                }
                String stopBitsS = stopBitsCB.getSelectionModel().getSelectedItem();
                stopBits = stopBitsCB.getSelectionModel().getSelectedIndex();
                switch (stopBitsS) {
                    case "STOPBITS_1":
                        stopBits = 1;
                        break;
                    case "DATABITS_1_5":
                        stopBits = 1.5f;
                        break;
                    case "DATABITS_2":
                        stopBits = 2;
                        break;

                }
                String parityS = parityCB.getSelectionModel().getSelectedItem();
                parity = parityCB.getSelectionModel().getSelectedIndex();
                switch (stopBitsS) {
                    case "PARITY_EVEN":
                        parity = 1;
                        break;
                    case "PARITY_NONE":
                        parity = 0;
                        break;
                    case "PARITY_ODD":
                        parity = 2;
                        break;
                }
                dataServerServices.openSerialPort(portName, baudRate, dataBits, dataBits, parity);
                saveSerialDevice(portNameIndex, baudRateIndex, dataBitsIndex, stopBitsIndex, parityIndex);
            }

        });
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            if (userTab.isSelected()) {
                s57TF.setText(s57PathOld);
                darkSkyTF.setText(darkSkyKeyOld);
                allCountriesTF.setText(allCountriesPathOld);
                allCountriesIndexTF.setText(allCountriesIndexPathOld);
                s57Path = s57TF.getText();
                darkSkyKey = darkSkyTF.getText();
                allCountriesPath = allCountriesTF.getText();
                allCountriesIndexPath = allCountriesIndexTF.getText();
                saveUser();
            }
            if (ownerShipTab.isSelected()) {
                nameTF.setText(nameOld);
                mmsiTF.setText(mmsiOld);
                countryTF.setText(countryOld);
                lengthTF.setText(lengthOld);
                widthTF.setText(widthOld);
                shipTypeTF.setText(shipTypeOld);
                draughtTF.setText(draughtOld);
                navigationalStatusTF.setText(navigationalStatusOld);
                callSignTF.setText(callSignOld);
                latitudeTF.setText(latitudeOld);
                longitudeTF.setText(longitudeOld);
                cogTF.setText(cogOld);
                sogTF.setText(sogOld);
                daeModelPathTF.setText(daeModelPathOld);
                scaleTF.setText(scaleOld);

                name = nameTF.getText();
                mmsi = mmsiTF.getText();
                country = countryTF.getText();
                length = lengthTF.getText();
                width = widthTF.getText();
                draught = shipTypeTF.getText();
                shipType = draughtTF.getText();
                navigationalStatus = navigationalStatusTF.getText();
                callSign = callSignTF.getText();
                latitude = latitudeTF.getText();
                longitude = longitudeTF.getText();
                cog = cogTF.getText();
                sog = sogTF.getText();
                daeModelPath = daeModelPathTF.getText();
                modelScale = scaleTF.getText();
                saveOwnerShip();
            }

        });
        defaultButton.setOnMouseClicked((MouseEvent event) -> {
            if (userTab.isSelected()) {
                s57Path = "";
                darkSkyKey = "";
                allCountriesPath = "";
                allCountriesIndexPath = "";

                s57TF.setText(s57Path);
                darkSkyTF.setText(darkSkyKey);
                allCountriesTF.setText(allCountriesPath);
                allCountriesIndexTF.setText(allCountriesIndexPath);
                saveUser();
            }
            if (ownerShipTab.isSelected()) {
                name = "Lithops";
                mmsi = "227769930";
                country = "France";
                length = "12";
                width = "3.75";
                draught = "13";
                shipType = "36";
                navigationalStatus = "8";
                callSign = "FG1888";
                latitude = "48.3649";
                longitude = "-4.4871";
                cog = "140.0";
                sog = "0.0";
                daeModelPath = "data/collada/lithops_0.dae";
                modelScale = "1.0";

                nameTF.setText(name);
                mmsiTF.setText(mmsi);
                countryTF.setText(country);
                lengthTF.setText(length);
                widthTF.setText(width);
                shipTypeTF.setText(shipType);
                draughtTF.setText(draught);
                navigationalStatusTF.setText(navigationalStatus);
                callSignTF.setText(callSign);
                latitudeTF.setText(latitude);
                longitudeTF.setText(longitude);
                cogTF.setText(cog);
                sogTF.setText(sog);
                daeModelPathTF.setText(daeModelPath);
                scaleTF.setText(modelScale);
                saveOwnerShip();
            }

        });
        helpButton.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Options");
            alert.setHeaderText("Données utilisateur");
            Text s = new Text("    S57ChartsDir : chemin de la racine des cartes S57 \n"
                    + "\n"
                    + "    DarkSkyApiKey : clé pour l'accès aux données météo de DarkSky  \n"
                    + "    A prendre sur le site : https://darksky.net/dev/ \n"
                    + " \n"
                    + "    AllCountriesPath : chemin vers le fichier allCountries.txt \n"
                    + "    A télécharger sur le site : http://download.geonames.org/export/dump/ "
                    + "    (318 M) \n"
                    + "\n"
                    + "     LuceneAllCountriesIndexPath : Après avoir téléchargé le fichier précédent \n"
                    + "     Il faut l'indexer puis indiqué le répertoire où se trouve l'index.\n"
                    + "\n"
                    + "     Ces deux dernières propriétés permettent la recherche des coordonnées géographiques \n"
                    + "     d'une ville. La météo à l'aide du site dark Sky en a besoin."
            );
            s.setWrappingWidth(650);
            alert.getDialogPane().setContent(s);
            alert.show();
        });
        s57Button.setOnMouseClicked((MouseEvent event) -> {
            openDir(s57TF);
        });
        allCPathButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(allCountriesTF);
        });
        luceneButton.setOnMouseClicked((MouseEvent event) -> {
            openDir(allCountriesIndexTF);
        });
        buildIndexButton.setOnMouseClicked((MouseEvent event) -> {
            if (!"".equals(allCountriesTF.getText()) && !"".equals(allCountriesIndexTF.getText())) {
                gazetteerComponentServices.buildIndex(allCountriesTF.getText(), allCountriesIndexTF.getText(), true);
            }
        });
        //  portnameCB.getOnAction(){
/*
        portNameCB.setOnAction((event) -> {
            System.out.println("SelectedItem : "+portNameCB.getSelectionModel().getSelectedItem());
        });
         */
        //  }
    }

    private void saveUser() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_NAME)) {
            properties.setProperty("s57ChartsDir", s57Path);
            properties.setProperty("darkSkyApiKey", darkSkyKey);
            properties.setProperty("allCountriesPath", allCountriesPath);
            properties.setProperty("luceneAllCountriesIndexPath", allCountriesIndexPath);

            properties.store(output, null);
            output.close();

            UserOption userOption = UserOptionBuilder.create()
                    .allCountriesIndexPath(allCountriesIndexPath)
                    .allCountriesPath(allCountriesPath)
                    .darkSkyKey(darkSkyKey)
                    .s57Path(s57Path)
                    .build();
            notifyConfEvent(userOption);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void saveOwnerShip() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_NAME)) {
            properties.setProperty("name", name);
            properties.setProperty("mmsi", mmsi);
            properties.setProperty("country", country);
            properties.setProperty("length", length);
            properties.setProperty("width", width);
            properties.setProperty("draught", draught);
            properties.setProperty("shipType", shipType);
            properties.setProperty("navigationalStatus", navigationalStatus);
            properties.setProperty("callSign", callSign);
            properties.setProperty("latitude", latitude);
            properties.setProperty("longitude", longitude);
            properties.setProperty("cog", cog);
            properties.setProperty("sog", sog);
            properties.setProperty("daeModelPath", daeModelPath);
            properties.setProperty("scale", modelScale);
            properties.store(output, null);
            output.close();

            OwnerShipOption ownerShipOption = OwnerShipOptionBuilder.create()
                    .callSign(callSign)
                    .cog(cog)
                    .country(country)
                    .daeModelPath(daeModelPath)
                    .draught(draught)
                    .latitude(latitude)
                    .longitude(longitude)
                    .length(length)
                    .mmsi(mmsi)
                    .name(name)
                    .navigationalStatus(navigationalStatus)
                    .scale(modelScale)
                    .shipType(shipType)
                    .sog(sog)
                    .width(width)
                    .build();
            notifyConfEvent(ownerShipOption);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void saveSerialDevice(int portNameIndex,
            int baudRateIndex,
            int dataBitsIndex,
            int stopBitsIndex,
            int parityIndex) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_NAME)) {
            properties.setProperty("portNameIndex", Integer.toString(portNameIndex));
            properties.setProperty("baudRateIndex", Integer.toString(baudRateIndex));
            properties.setProperty("dataBitsIndex", Integer.toString(dataBitsIndex));
            properties.setProperty("stopBitsIndex", Integer.toString(stopBitsIndex));
            properties.setProperty("parityIndex", Integer.toString(parityIndex));

            properties.store(output, null);
            output.close();

        } catch (IOException ex) {
            Logger.getLogger(ConfigurationComponentController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void openFile(TextField tf) {
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle(tr("popup.fileChooser.open"));
        String userInitialDirectory = System.getProperty("user.home");
        this.fileChooser.setInitialDirectory(new File(userInitialDirectory));
        File selectedFile = this.fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            tf.setText("No file selected");
        } else {
            tf.setText(selectedFile.getAbsolutePath());
        }
    }

    public void openDir(TextField tf) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(tr("popup.fileChooser.open"));
        String userInitialDirectory = System.getProperty("user.home");
        directoryChooser.setInitialDirectory(new File(userInitialDirectory));
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory == null) {
            tf.setText("No Directory selected");
        } else {
            tf.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void notifyConfEvent(Option option) {

        try {
            component.notifyConfEvent(option);
        } catch (Exception e) {

        }
    }
}
