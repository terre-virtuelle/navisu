/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.country.CountryCode;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.domain.Location;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.view.DarkSkyViewController;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.sun.javafx.scene.control.skin.DatePickerContent;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.util.Callback;
import javafx.util.StringConverter;

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
    List<String> unitList;
    String unit;
    List<String> countryList;
    private ObservableList observableList = FXCollections.observableArrayList();
    private DateCell iniCell = null;
    private DateCell endCell = null;
    private LocalDate iniDate;
    private LocalDate endDate;
    private double latitude;
    private double longitude;
    private String town;
    private String country;
    private String countryCode;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.uuuu", Locale.ENGLISH);
    private DarkSkyComponentController darkSkyComponentController;
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
                languageMB.setText(language.trim());
            });
        });

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
                unitMB.setText(unit);
            });
        });
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
                countryCode = CountryCode.CODE.get(country);
                countryMB.setText(country.trim() + "   " + "\"" + countryCode + "\"");
            });
        });

        townTF.setOnAction(a -> {
            town = townTF.getText().trim();
        });
        datePicker.setValue(LocalDate.now());
        datePicker.setConverter(new StringConverter<LocalDate>() {

            @Override
            public String toString(LocalDate object) {
                if (iniDate != null && endDate != null) {
                    return iniDate.format(formatter) + " - " + endDate.format(formatter);
                }
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                if (string.contains("-")) {
                    try {
                        iniDate = LocalDate.parse(string.split("-")[0].trim(), formatter);
                        endDate = LocalDate.parse(string.split("-")[1].trim(), formatter);
                    } catch (DateTimeParseException dte) {
                        return LocalDate.parse(string, formatter);
                    }
                    return iniDate;
                }
                return LocalDate.parse(string, formatter);
            }
        });
        final Callback<DatePicker, DateCell> dayCellFactory
                = (final DatePicker datePicker1) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(LocalDate.now().plusDays(6))
                        || item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.showingProperty().addListener((obs, b, b1) -> {
            if (b1) {
                DatePickerContent content = (DatePickerContent) ((DatePickerSkin) datePicker.getSkin()).getPopupContent();

                List<DateCell> cells = content.lookupAll(".day-cell").stream()
                        .filter(ce -> !ce.getStyleClass().contains("next-month"))
                        .map(n -> (DateCell) n)
                        .collect(Collectors.toList());

                // select initial range
                if (iniDate != null && endDate != null) {
                    int ini = iniDate.getDayOfMonth();
                    int end = endDate.getDayOfMonth();
                    cells.stream()
                            .forEach(ce -> ce.getStyleClass().remove("selected"));
                    cells.stream()
                            .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                            .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                            .forEach(ce -> ce.getStyleClass().add("selected"));
                }
                iniCell = null;
                endCell = null;
                content.setOnMouseDragged(e -> {
                    Node n = e.getPickResult().getIntersectedNode();
                    DateCell c = null;
                    if (n instanceof DateCell) {
                        c = (DateCell) n;
                    } else if (n instanceof Text) {
                        c = (DateCell) (n.getParent());
                    }
                    if (c != null && c.getStyleClass().contains("day-cell")
                            && !c.getStyleClass().contains("next-month")) {
                        if (iniCell == null) {
                            iniCell = c;
                        }
                        endCell = c;
                    }
                    if (iniCell != null && endCell != null) {
                        int ini = (int) Math.min(Integer.parseInt(iniCell.getText()),
                                Integer.parseInt(endCell.getText()));
                        int end = (int) Math.max(Integer.parseInt(iniCell.getText()),
                                Integer.parseInt(endCell.getText()));
                        cells.stream()
                                .forEach(ce -> ce.getStyleClass().remove("selected"));
                        cells.stream()
                                .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                                .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                                .forEach(ce -> ce.getStyleClass().add("selected"));
                    }
                });
                content.setOnMouseReleased(e -> {
                    if (iniCell != null && endCell != null) {
                        iniDate = LocalDate.of(datePicker.getValue().getYear(),
                                datePicker.getValue().getMonth(),
                                Integer.parseInt(iniCell.getText()));
                        endDate = LocalDate.of(datePicker.getValue().getYear(),
                                datePicker.getValue().getMonth(),
                                Integer.parseInt(endCell.getText()));
                        System.out.println("Selection from " + iniDate + " to " + endDate);

                        datePicker.setValue(iniDate);
                        int ini = iniDate.getDayOfMonth();
                        int end = endDate.getDayOfMonth();
                        cells.stream()
                                .forEach(ce -> ce.getStyleClass().remove("selected"));
                        cells.stream()
                                .filter(ce -> Integer.parseInt(ce.getText()) >= ini)
                                .filter(ce -> Integer.parseInt(ce.getText()) <= end)
                                .forEach(ce -> ce.getStyleClass().add("selected"));
                    }
                    endCell = null;
                    iniCell = null;
                });
            }
        });
        requestButton.setOnAction(a -> {
            DarkSkyViewController darkSkyViewController = new DarkSkyViewController();
            if (town == null) {
                town = townTF.getText();
            }
            Location location = gazetteerComponentServices.searchGeoName(town, countryCode);
            latitudeLabel.setText(Double.toString(location.getLatitude()));
            longitudeLabel.setText(Double.toString(location.getLongitude()));
            darkSkyComponentController.init(unit, language, location.getLatitude(), location.getLongitude());
            darkSkyComponentController.update();
            List<DataPoint> datapoints = darkSkyComponentController.getForecast();
            datapoints.forEach((d) -> {
                darkSkyViewController.setTemperature(Double.toString(d.getTemperatureMax()));
                darkSkyViewController.setHumidity(Double.toString(d.getHumidity()));
                darkSkyViewController.setVisibility(Double.toString(d.getVisibility()));
                darkSkyViewController.setPressure(Double.toString(d.getPressure()));
                darkSkyViewController.setWindSpeed(Double.toString(d.getWindSpeed()));
                darkSkyViewController.setWindBearing(Double.toString(d.getWindBearing()));
                darkSkyViewController.setSummary(d.getSummary());
                // System.out.println(d.toJsonString());
            }); //  Alert alert = new Alert(AlertType.INFORMATION);
            //  alert.setTitle("Example");
            // alert.setContentText("You clicked " + cb.getItems().get((Integer)newValue));
            //  alert.showAndWait();

            guiAgentServices.getRoot().getChildren().add(darkSkyViewController); //Par defaut le widget n'est pas visible Ctrl-A
            darkSkyViewController.setVisible(true);
            System.out.println(System.getProperty("user.home"));
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
