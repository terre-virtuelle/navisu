/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.view;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.FIOCurrently;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.ForecastIO;
import javafx.scene.control.TextArea;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DarkSkyViewController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group weatherViewPanel;
    @FXML
    public ImageView quit;
    @FXML
    Text title;
    @FXML
    Label windSpeedLabel;
    @FXML
    Label windSpeedData;
    @FXML
    Label windBearingLabel;
    @FXML
    Label windBearingData;
    @FXML
    Label humidityLabel;
    @FXML
    Label humidityData;
    @FXML
    Label visibilityLabel;
    @FXML
    Label visibilityData;
    @FXML
    Label temperatureLabel;
    @FXML
    Label temperatureData;
    @FXML
    Label pressureLabel;
    @FXML
    Label pressureData;
    @FXML
    Label dewPointLabel;
    @FXML
    Label dewPointData;
    @FXML
    Label apparentTemperatureLabel;
    @FXML
    Label apparentTemperatureData;
    @FXML
    Label precipProbabilityLabel;
    @FXML
    Label precipProbabilityData;
    @FXML
    Label precipTypeLabel;
    @FXML
    Label precipTypeData;
    @FXML
    Label precipIntensityLabel;
    @FXML
    Label precipIntensityData;
    @FXML
    Label cloudCoverLabel;
    @FXML
    Label cloudCoverData;
    @FXML
    Label summaryLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label summaryData;
    @FXML
    Label timeData;

    String FXML = "weatherViewPanel.fxml";
    private ForecastIO fio;

    public DarkSkyViewController() {
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

    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windSpeedLabel.setText(tr("wheater.darsky.windSpeed"));
        windBearingLabel.setText(tr("wheater.darsky.windBearing"));
        visibilityLabel.setText(tr("wheater.darsky.visibility"));
        humidityLabel.setText(tr("wheater.darsky.humidity"));
        temperatureLabel.setText(tr("wheater.darsky.temperature"));
        apparentTemperatureLabel.setText(tr("wheater.darsky.apparentTemperature"));
        pressureLabel.setText(tr("wheater.darsky.pressure"));
        dewPointLabel.setText(tr("wheater.darsky.dewPoint"));
        precipProbabilityLabel.setText(tr("wheater.darsky.precipProbability"));
        precipTypeLabel.setText(tr("wheater.darsky.precipType"));
        precipIntensityLabel.setText(tr("wheater.darsky.precipIntensity"));
        cloudCoverLabel.setText(tr("wheater.darsky.cloudCover"));
        summaryLabel.setText(tr("wheater.darsky.summary"));
        timeLabel.setText(tr("wheater.darsky.time"));
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });

    }

    public void showData(ForecastIO fio) {
        this.fio = fio;
        FIOCurrently currently = new FIOCurrently(fio);
        this.windSpeedData.setText(Double.toString(currently.get().windSpeed()));
        this.windBearingData.setText(Double.toString(currently.get().windBearing()));
        this.visibilityData.setText(Double.toString(currently.get().visibility()));
        this.humidityData.setText(Double.toString(currently.get().humidity()));
        this.temperatureData.setText(Double.toString(currently.get().temperature()));
        this.apparentTemperatureData.setText(Double.toString(currently.get().apparentTemperature()));
        this.pressureData.setText(Double.toString(currently.get().pressure()));
        this.dewPointData.setText(Double.toString(currently.get().dewPoint()));
        this.precipProbabilityData.setText(Double.toString(currently.get().precipProbability()));
        this.precipTypeData.setText(currently.get().precipType());
        this.precipIntensityData.setText(Double.toString(currently.get().precipIntensity()));
        this.cloudCoverData.setText(Double.toString(currently.get().cloudCover()));
        this.summaryData.setText(currently.get().summary());
        this.timeData.setText(currently.get().time());  
    }

}
