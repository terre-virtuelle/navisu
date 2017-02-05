/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.country.Abbreviations;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.domain.Location;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.view.DarkSkyViewController;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DateCell;
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
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
        initGui();
    }

    @SuppressWarnings("unchecked")
    private void initGui() {
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
                countryMB.setText(country.trim() + "   " + "\"" + countryCode + "\"");
            });
        });
        countryMB.setOnAction(a -> {
            country = countryMB.getText().trim();
        });

        /* Request forecast */
        requestButton.setOnAction(a -> {
            DarkSkyViewController darkSkyViewController = new DarkSkyViewController();
            if (town == null) {
                town = townTF.getText();
            }
            Location location = gazetteerComponentServices.searchGeoName(town, countryCode);

            if (location != null) {
                latitudeLabel.setText(Double.toString(location.getLatitude()));
                longitudeLabel.setText(Double.toString(location.getLongitude()));

                fio = new ForecastIO(apiKey, unitCode, languageCode,
                Double.toString(location.getLatitude()), Double.toString(location.getLongitude()));
                if (fio.getForecast(Double.toString(location.getLatitude()),
                        Double.toString(location.getLongitude())) == true) {
                   // print(fio);
                   darkSkyViewController.showData(fio);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gazetteer");
                alert.setHeaderText("Attention");
                Text s = new Text(" Vous devez remplir tous les champs !");
                s.setWrappingWidth(350);
                alert.getDialogPane().setContent(s);
                alert.show();
            }

            guiAgentServices.getRoot().getChildren().add(darkSkyViewController);
            darkSkyViewController.setMouseTransparent(false);
            darkSkyViewController.setVisible(true);
        });

    }

    public void print(ForecastIO fio) {
        //Response Headers info
        System.out.println("Response Headers:");
        System.out.println("Cache-Control: " + fio.getHeaderCache_Control());
        System.out.println("Expires: " + fio.getHeaderExpires());
        System.out.println("X-Forecast-API-Calls: " + fio.getHeaderX_Forecast_API_Calls());
        System.out.println("X-Response-Time: " + fio.getHeaderX_Response_Time());
        System.out.println("\n");

        //ForecastIO info
        System.out.println("Latitude: " + fio.getLatitude());
        System.out.println("Longitude: " + fio.getLongitude());
        System.out.println("Timezone: " + fio.getTimezone());
        System.out.println("Offset: " + fio.offsetValue());
        System.out.println("\n");

        //Currently data
        FIOCurrently currently = new FIOCurrently(fio);

        System.out.println("\nCurrently\n");
        String[] f = currently.get().getFieldsArray();
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i] + ": " + currently.get().getByKey(f[i]));
        }
        System.out.println("\n");

        //Minutely data
        FIOMinutely minutely = new FIOMinutely(fio);
        if (minutely.minutes() < 0) {
            System.out.println("No minutely data.");
        } else {
            System.out.println("\nMinutely\n");
        }
        for (int i = 0; i < minutely.minutes(); i++) {
            String[] m = minutely.getMinute(i).getFieldsArray();
            System.out.println("Minute #" + (i + 1));
            for (int j = 0; j < m.length; j++) {
                System.out.println(m[j] + ": " + minutely.getMinute(i).getByKey(m[j]));
            }
            System.out.println("\n");
        }
        //Hourly data
        FIOHourly hourly = new FIOHourly(fio);
        if (hourly.hours() < 0) {
            System.out.println("No hourly data.");
        } else {
            System.out.println("\nHourly:\n");
        }
        for (int i = 0; i < hourly.hours(); i++) {
            String[] h = hourly.getHour(i).getFieldsArray();
            System.out.println("Hour #" + (i + 1));
            for (int j = 0; j < h.length; j++) {
                System.out.println(h[j] + ": " + hourly.getHour(i).getByKey(h[j]));
            }
            System.out.println("\n");
        }
        //Daily data
        FIODaily daily = new FIODaily(fio);
        if (daily.days() < 0) {
            System.out.println("No daily data.");
        } else {
            System.out.println("\nDaily:\n");
        }
        for (int i = 0; i < daily.days(); i++) {
            String[] h = daily.getDay(i).getFieldsArray();
            System.out.println("Day #" + (i + 1));
            for (int j = 0; j < h.length; j++) {
                System.out.println(h[j] + ": " + daily.getDay(i).getByKey(h[j]));
            }
            System.out.println("\n");
        }
        //Flags data
        FIOFlags flags = new FIOFlags(fio);
        System.out.println("Available Flags: ");
        for (int i = 0; i < flags.availableFlags().length; i++) {
            System.out.println(flags.availableFlags()[i]);
        }
        System.out.println("\n");
        for (int i = 0; i < flags.metarStations().length; i++) {
            System.out.println("Metar Station: " + flags.metarStations()[i]);
        }
        System.out.println("\n");
        for (int i = 0; i < flags.isdStations().length; i++) {
            System.out.println("ISD Station: " + flags.isdStations()[i]);
        }
        System.out.println("\n");
        for (int i = 0; i < flags.sources().length; i++) {
            System.out.println("Source: " + flags.sources()[i]);
        }
        System.out.println("\n");
        System.out.println("Units: " + flags.units());
        System.out.println("\n");

        //Alerts data
        FIOAlerts alerts = new FIOAlerts(fio);
        if (alerts.NumberOfAlerts() <= 0) {
            System.out.println("No alerts for this location.");
        } else {
            System.out.println("Alerts");
            for (int i = 0; i < alerts.NumberOfAlerts(); i++) {
                System.out.println(alerts.getAlert(i));
            }
        }
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
