/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.ForecastIO;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.country.Abbreviations;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.domain.Location;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.view.DarkSkyViewController;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DarkSkyController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group weatherPanel;
    @FXML
    public ImageView quit;
    @FXML
    Text title;
    @FXML
    Label latitudeLabel;
    @FXML
    Label longitudeLabel;
    @FXML
    TextField townTF;
    @FXML
    MenuButton languageMB;
    @FXML
    MenuButton unitMB;
    @FXML
    MenuButton countryMB;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button requestButton;

    String FXML = "weatherPanel.fxml";
    List<String> languageList;
    String language;
    String languageCode;
    List<String> unitList;
    String unit;
    String unitCode;
    protected Properties properties;
    List<String> countryList;
    private ObservableList observableList = FXCollections.observableArrayList();
    private LocalDate iniDate;
    private LocalDate endDate;
    private double latitude;
    private double longitude;
    private String town;
    private String country;
    private String countryCode;
    private String apiKey = null;
    private String darkSkyUrl = null;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.uuuu", Locale.ENGLISH);
    private final DarkSkyComponentController darkSkyComponentController;
    private ForecastIO fio;
    protected GazetteerComponentServices gazetteerComponentServices;
    protected GuiAgentServices guiAgentServices;
    private String navisuWeatherCache;
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    public String viewgroupstyle = "weatherpanel.css";

    public DarkSkyController(DarkSkyComponentController darkSkyComponentController,
            GazetteerComponentServices gazetteerComponentServices,
            GuiAgentServices guiAgentServices,
            List<String> languageList,
            List<String> unitList,
            List<String> countryList) {
        this.darkSkyComponentController = darkSkyComponentController;
        this.gazetteerComponentServices = gazetteerComponentServices;
        this.guiAgentServices = guiAgentServices;
        this.languageList = languageList;
        this.unitList = unitList;
        this.countryList = countryList;

        apiKey = darkSkyComponentController.getApiKey();
        darkSkyUrl = darkSkyComponentController.getDarkSkyUrl();
        town = darkSkyComponentController.getTown();
        language = darkSkyComponentController.getLanguage();
        unit = darkSkyComponentController.getUnit();
        country = darkSkyComponentController.getCountry();
        properties = darkSkyComponentController.getProperties();

        setMouseTransparent(false);
        load();
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        weatherPanel.getStylesheets().add(uri);
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
        initGui();
    }

    @SuppressWarnings("unchecked")
    private void initGui() {
        townTF.setText(town);
        townTF.setOnAction(a -> {
            town = townTF.getText().trim();
        });

        /* Language choice */
        observableList = FXCollections.observableArrayList();
        languageList.forEach((s) -> {
            observableList.add(new CheckMenuItem(s));
        });
        languageMB.getItems().clear();
        languageMB.getItems().addAll(observableList);;
        List<MenuItem> items = languageMB.getItems();
        items.forEach((item) -> {
            item.setOnAction(a -> {
                language = item.getText();
                languageCode = Abbreviations.LANG.get(language);
                languageMB.setText(language.trim());
            });
        });
        languageMB.setText(language);
        languageMB.setOnAction(a -> {
            language = languageMB.getText().trim();
        });

        /* Unit choice */
        observableList = FXCollections.observableArrayList();
        unitList.forEach((s) -> {
            observableList.add(new CheckMenuItem(s));
        });
        unitMB.getItems().clear();
        unitMB.getItems().addAll(observableList);
        items = unitMB.getItems();
        items.forEach((item) -> {
            item.setOnAction(a -> {
                unit = item.getText();
                unitCode = Abbreviations.UNIT.get(unit);
                unitMB.setText(unit.trim());

            });
        });
        unitMB.setText(unit);
        unitMB.setOnAction(a -> {
            unit = unitMB.getText().trim();
        });

        /* Country choice */
        observableList = FXCollections.observableArrayList();
        countryList.forEach((s) -> {
            observableList.add(new CheckMenuItem(s));
        });
        countryMB.getItems().clear();
        countryMB.getItems().addAll(observableList);
        items = countryMB.getItems();
        items.forEach((item) -> {
            item.setOnAction(a -> {
                country = item.getText().trim();
                countryCode = Abbreviations.CODE.get(country);
                countryMB.setText(country.trim());
            });
        });
        countryMB.setText(country);
        countryMB.setOnAction(a -> {
            country = countryMB.getText().trim();
        });

        /* Request forecast */
        requestButton.setOnAction(a -> {
            DarkSkyViewController darkSkyViewController = new DarkSkyViewController();

            town = townTF.getText().trim();
            country = countryMB.getText().trim();
            unit = unitMB.getText().trim();
            language = languageMB.getText().trim();
            countryCode = Abbreviations.CODE.get(country);

            try (OutputStream output = new FileOutputStream(darkSkyComponentController.getCACHE_FILE_NAME())) {
                properties.setProperty("town", town);
                properties.setProperty("language", language);
                properties.setProperty("unit", unit);
                properties.setProperty("country", country);
                properties.setProperty("countryCode", countryCode);
                properties.store(output, null);
            } catch (IOException ex) {
                Logger.getLogger(DarkSkyController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }

            Location location = gazetteerComponentServices.searchGeoName(town, countryCode);

            if (location != null) {
                latitudeLabel.setText(Double.toString(location.getLatitude()));
                longitudeLabel.setText(Double.toString(location.getLongitude()));

                languageCode = Abbreviations.LANG.get(language);
                unitCode = Abbreviations.UNIT.get(unit);

                fio = new ForecastIO(apiKey, unitCode, languageCode,
                        Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                if (fio.getForecast(Double.toString(location.getLatitude()),
                        Double.toString(location.getLongitude())) == true) {
                    darkSkyViewController.showData(fio);
                }

                guiAgentServices.getRoot().getChildren().add(darkSkyViewController);
                darkSkyViewController.setMouseTransparent(false);
                darkSkyViewController.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gazetteer");
                alert.setHeaderText("Attention localisation impossible");
                Text s = new Text("  Vérifiez le contenu des champs Town et Country !");
                s.setWrappingWidth(350);
                alert.getDialogPane().setContent(s);
                alert.show();
            }
        });

    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
