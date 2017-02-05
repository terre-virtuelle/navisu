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
    TextArea summaryTa;
    @FXML
    TextArea timeTa;

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

        System.out.println("\nCurrently\n");
        String[] f = currently.get().getFieldsArray();
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i] + ": " + currently.get().getByKey(f[i]));
         //   currently.get().precipProbability()
        }
        System.out.println("\n");
    }

    public void setWindSpeed(String data) {
        this.windSpeedData.setText(data);
    }

    public void setWindBearing(String data) {
        this.windBearingData.setText(data);
    }

    public void setVisibility(String data) {
        this.visibilityData.setText(data);
    }

    public void setHumidity(String data) {
        this.humidityData.setText(data);
    }

    public void setTemperature(String data) {
        this.temperatureData.setText(data);
    }

    public void setApparentTemperature(String data) {
        this.apparentTemperatureData.setText(data);
    }

    public void setPressure(String data) {
        this.pressureData.setText(data);
    }

    public void setDewPoint(String data) {
        this.dewPointData.setText(data);
    }

    public void setPrecipProbability(String data) {
        this.precipProbabilityData.setText(data);
    }

    public void setPrecipType(String data) {
        this.precipTypeData.setText(data);
    }

    public void setPrecipIntensity(String data) {
        this.precipIntensityData.setText(data);
    }

    public void setCloudCover(String data) {
        this.cloudCoverData.setText(data);
    }

    public void setSummary(String data) {
        this.summaryTa.setText(data);
    }

    public void setTime(String data) {
        this.timeTa.setText(data);
    }
}
