/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.controller;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.sun.javafx.collections.ObservableListWrapper;
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
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
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
    Button okButton;
    @FXML
    TextField latitudeTF;
    @FXML
    TextField longitudeTF;
    @FXML
    TextField townTF;
    @FXML
    MenuButton languageMB;
    @FXML
    MenuButton unitMB;
    @FXML
    public DatePicker datePicker;

    String FXML = "weatherPanel.fxml";
    List<String> languagelist;
    List<String> unitlist;
    private ObservableList observableList = FXCollections.observableArrayList();
    private DateCell iniCell = null;
    private DateCell endCell = null;

    private LocalDate iniDate;
    private LocalDate endDate;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.uuuu", Locale.ENGLISH);
   

    public DarkSkyController(List<String> languagelist, List<String> unitList) {
        this.languagelist = languagelist;
        this.unitlist = unitList;
        setMouseTransparent(false);
        load();
    }

    public DarkSkyController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
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

    private void initGui() {

        observableList = FXCollections.observableArrayList();
        languagelist.forEach((s) -> {
            observableList.add(new CheckMenuItem(s));
        });
        languageMB.getItems().clear();
        languageMB.getItems().addAll(observableList);
        
        observableList = FXCollections.observableArrayList();
        unitlist.forEach((s) -> {
            observableList.add(new CheckMenuItem(s));
        });
        unitMB.getItems().clear();
        unitMB.getItems().addAll(observableList);
        

        latitudeTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            System.out.println("********" + oldValue + "  " + newValue);
            if (!latitudeTF.getText().equals("") && latitudeTF.getText() != null) {

               // latitudeTF.setText(Double.toString(oldLat));

            }
        });
        longitudeTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            System.out.println("ooooooooooooo" + oldValue + "  " + newValue);
            if (!longitudeTF.getText().equals("") && longitudeTF.getText() != null) {

            }
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
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
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
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    public Button getOkButton() {
        return okButton;
    }

    public TextField getLatitudeTF() {
        return latitudeTF;
    }

    public TextField getLongitudeTF() {
        return longitudeTF;
    }

    public void setLatitudeTF(String latitude) {
        this.latitudeTF.setText(latitude);
    }

    public void setLongitudeTF(String longitude) {
        this.longitudeTF.setText(longitude);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
