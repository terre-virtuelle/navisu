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
import javafx.scene.control.TextArea;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DarkSkyViewController
        extends Widget2DController
        implements Initializable {

    @FXML
    public Group weatherPanel;
    @FXML
    public ImageView quit;
    @FXML
    Text title;
    @FXML
    Label windSpeedLabel;
    @FXML
    Label windSpeedValueLabel;
    @FXML
    Label windBearingLabel;
    @FXML
    Label windBearingValueLabel;
    @FXML
    Label humidityLabel;
    @FXML
    Label humidityValueLabel;
    @FXML
    Label indiceUVLabel;
    @FXML
    Label indiceUVValueLabel;
    @FXML
    Label visibilityLabel;
    @FXML
    Label visibilityValueLabel;
    @FXML
    Label temperatureLabel;
    @FXML
    Label temperatureValueLabel;
    @FXML
    Label pressureLabel;
    @FXML
    Label pressureValueLabel;
    @FXML
    Label summaryLabel;
    @FXML
    TextArea summaryTA;
    String FXML = "weatherViewPanel.fxml";

    public void setWindSpeed(String windSpeed) {
        this.windSpeedValueLabel.setText(windSpeed);
    }

    public void setWindBearing(String windBearing) {
        this.windSpeedValueLabel.setText(windBearing);
    }

    public void setHumidity(String humidityValueLabel) {
        this.humidityValueLabel.setText(humidityValueLabel);
    }

    public void setIndiceUV(String indiceUVValueLabel) {
        this.indiceUVValueLabel.setText(indiceUVValueLabel);
    }

    public void setVisibility(String visibilityValueLabel) {
        this.visibilityValueLabel.setText(visibilityValueLabel);
    }

    public void setTemperature(String temperatureValueLabel) {
        this.temperatureValueLabel.setText(temperatureValueLabel);
    }

    public void setPressure(String pressureValueLabel) {
        this.pressureValueLabel.setText(pressureValueLabel);
    }

    public void setSummary(String summary) {
        this.summaryTA.setText(summary);
    }

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
        windSpeedLabel.setText(tr("wheater.darsky.windSpeed"));
        windBearingLabel.setText(tr("wheater.darsky.windBearing"));
        humidityLabel.setText(tr("wheater.darsky.humidity"));
        visibilityLabel.setText(tr("wheater.darsky.visibility"));
        pressureLabel.setText(tr("wheater.darsky.pressure"));
        temperatureLabel.setText(tr("wheater.darsky.temperature"));
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
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
