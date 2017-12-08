/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.darksky.view;

import bzh.terrevirtuelle.navisu.app.guiagent.svg.SVGContent;
import bzh.terrevirtuelle.navisu.app.guiagent.svg.SVGLoader;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIOAlerts;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIOCurrently;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIODaily;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIOFlags;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIOHourly;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.FIOMinutely;
import bzh.terrevirtuelle.navisu.weather.impl.darksky.controller.fio.ForecastIO;
import java.nio.file.Paths;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class DarkSkyViewController
        extends Widget2DController
        implements Initializable {

    public String viewgroupstyle = "weatherpanel.css";

    @FXML
    public Group weatherViewPanel;
    @FXML
    public Pane quit;
    @FXML
    Label title;
    @FXML
    Label resultsTitle;
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
    @FXML
    Button loadWindData;
    @FXML
    Button loadTemperatureData;
    @FXML
    Button loadPressureData;
    @FXML
    Button loadWindData1;
    @FXML
    Button loadTemperatureData1;
    @FXML
    Button loadPressureData1;
    @FXML
    LineChart<String, Double> hoursLineChart;
    @FXML
    LineChart<String, Double> daysLineChart;
    @FXML
    NumberAxis hyAxis;
    @FXML
    NumberAxis dyAxis;
    @FXML
    StackPane iconId;
    private String town;
    private String country;
    private String unit;

    String FXML = "weatherViewPanel.fxml";
    private ForecastIO fio;
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private static final String CSS_ICONES_PATH = Paths.get(System.getProperty("user.dir") + "/css/cssImages/meteoicons/").toUri().toString();
    public String iconadress = "";
    public String forecasticon = "";

    public DarkSkyViewController() {
        setMouseTransparent(false);
        load();
    }

    public DarkSkyViewController(String town, String country, String unit) {
        this.town = town;
        this.country = country;
        this.unit = unit;
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
        setOpacity(1.0);
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        weatherViewPanel.getStylesheets().add(uri);

    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Label getTitle() {
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
        resultsTitle.setText("Results request forecast for " + town);
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
        loadWindData.setOnMouseClicked((MouseEvent event) -> {
            showHoursWindData(fio);
        });
        loadTemperatureData.setOnMouseClicked((MouseEvent event) -> {
            showHoursTemperatureData(fio);
        });
        loadPressureData.setOnMouseClicked((MouseEvent event) -> {
            showHoursPressureData(fio);
        });
        loadWindData1.setOnMouseClicked((MouseEvent event) -> {
            showDaysWindData(fio);
        });
        loadTemperatureData1.setOnMouseClicked((MouseEvent event) -> {
            showDaysTemperatureData(fio);
        });
        loadPressureData1.setOnMouseClicked((MouseEvent event) -> {
            showDaysPressureData(fio);
        });
        /*iconId.setBackground(new Background(new BackgroundFill(
                Color.AQUAMARINE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        SVGContent content = SVGLoader.load(getClass().getResource("meteoicones/Cloud-Download.svg").toString());
        iconId.getChildren().add(content);
         */

    }

    public void showData(ForecastIO fio) {
        this.fio = fio;

        FIOCurrently currently = new FIOCurrently(fio);
        if (currently.get() != null) {
            this.windSpeedData.setText(currently.get().windSpeed() != null ? Double.toString(currently.get().windSpeed() * 2) : "NC");
            this.windBearingData.setText(currently.get().windBearing() != null ? Double.toString(currently.get().windBearing()) : "NC");
            this.visibilityData.setText(currently.get().visibility() != null ? Double.toString(currently.get().visibility()) : "NC");
            this.humidityData.setText(currently.get().humidity() != null ? Double.toString(currently.get().humidity()) : "NC");
            this.temperatureData.setText(currently.get().temperature() != null ? Double.toString(currently.get().temperature()) : "NC");
            this.apparentTemperatureData.setText(currently.get().apparentTemperature() != null ? Double.toString(currently.get().apparentTemperature()) : "NC");
            this.pressureData.setText(currently.get().pressure() != null ? Double.toString(currently.get().pressure()) : "NC");
            this.dewPointData.setText(currently.get().dewPoint() != null ? Double.toString(currently.get().dewPoint()) : "NC");
            this.precipProbabilityData.setText(currently.get().precipProbability() != null ? Double.toString(currently.get().precipProbability()) : "NC");
            this.precipTypeData.setText(currently.get().precipType() != null ? currently.get().precipType() : "NC");
            this.precipIntensityData.setText(currently.get().precipIntensity() != null ? Double.toString(currently.get().precipIntensity()) : "NC");
            this.cloudCoverData.setText(currently.get().cloudCover() != null ? Double.toString(currently.get().cloudCover()) : "NC");
            this.summaryData.setText(currently.get().summary() != null ? currently.get().summary() : "NC");
            this.timeData.setText(currently.get().time() != null ? currently.get().time() : "NC");
            showHoursWindData(fio);
            showDaysWindData(fio);

            forecasticon = currently.get().icon();
            forecasticon = deleteCharAt(forecasticon, 0);
            forecasticon = removeLastChar(forecasticon);
            iconadress = "meteoicons/" + forecasticon + ".svg";
            SVGContent content = SVGLoader.load(getClass().getResource(iconadress).toString());
            iconId.getChildren().add(content);
        } else {
            System.out.println("Données non transmises");
        }
    }

    @SuppressWarnings("unchecked")
    public void showHoursWindData(ForecastIO fio) {
        if (hoursLineChart.getData() != null) {
            hoursLineChart.getData().clear();
        }

        hyAxis.setAutoRanging(false);
        hyAxis.setLowerBound(0);
        hyAxis.setUpperBound(50);
        FIOHourly hourly = new FIOHourly(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("WindSpeed");
        if (hourly.hours() > 0) {
            for (int i = 0; i < hourly.hours(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("H+" + Integer.toString(i), hourly.getHour(i).windSpeed() * 2);
                String windBearing = hourly.getHour(i).windBearing().toString();
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + '\n'
                            + "S : " + data.getYValue().toString() + " Kn" + '\n'
                            + "B : " + windBearing + " °");
                    Tooltip.install(node, tooltip);
                });
            }
        }
        hoursLineChart.getData().addAll(dataSeries1);
    }

    @SuppressWarnings("unchecked")
    public void showHoursTemperatureData(ForecastIO fio) {
        if (hoursLineChart.getData() != null) {
            hoursLineChart.getData().clear();
        }
        hyAxis.setAutoRanging(false);
        hyAxis.setLowerBound(-30);
        hyAxis.setUpperBound(60);
        FIOHourly hourly = new FIOHourly(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Temperature");
        if (hourly.hours() > 0) {
            for (int i = 0; i < hourly.hours(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("H+" + Integer.toString(i), hourly.getHour(i).temperature());
                String appTemp = hourly.getHour(i).apparentTemperature().toString();
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + '\n'
                            + "T : " + data.getYValue().toString() + " °" + '\n'
                            + "A : " + appTemp + " °");
                    Tooltip.install(node, tooltip);
                });
            }
        }
        hoursLineChart.getData().addAll(dataSeries1);
    }

    @SuppressWarnings("unchecked")
    public void showHoursPressureData(ForecastIO fio) {
        if (hoursLineChart.getData() != null) {
            hoursLineChart.getData().clear();
        }
        hyAxis.setAutoRanging(false);
        hyAxis.setLowerBound(900);
        hyAxis.setUpperBound(1050);

        FIOHourly hourly = new FIOHourly(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Pressure");
        if (hourly.hours() > 0) {
            for (int i = 0; i < hourly.hours(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("H+" + Integer.toString(i), hourly.getHour(i).pressure());
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + '\n'
                            + "P : " + data.getYValue().toString() + " hPa");
                    Tooltip.install(node, tooltip);
                });
            }
        }
        hoursLineChart.getData().addAll(dataSeries1);
    }

    @SuppressWarnings("unchecked")
    public void showDaysWindData(ForecastIO fio) {
        if (daysLineChart.getData() != null) {
            daysLineChart.getData().clear();
        }
        dyAxis.setAutoRanging(false);
        dyAxis.setLowerBound(0);
        dyAxis.setUpperBound(50);
        FIODaily daily = new FIODaily(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("WindSpeed");
        if (daily.days() > 0) {
            for (int i = 0; i < daily.days(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("D+" + Integer.toString(i), daily.getDay(i).windSpeed() * 2);
                String windBearing = daily.getDay(i).windBearing().toString();
                String summary = daily.getDay(i).summary();
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + "\n"
                            + "S : " + data.getYValue().toString() + " Kn" + '\n'
                            + "B : " + windBearing + " °" + "\n"
                            + summary);
                    Tooltip.install(node, tooltip);
                });
            }
        }
        daysLineChart.getData().addAll(dataSeries1);
    }

    @SuppressWarnings("unchecked")
    public void showDaysTemperatureData(ForecastIO fio) {
        if (daysLineChart.getData() != null) {
            daysLineChart.getData().clear();
        }
        dyAxis.setAutoRanging(false);
        dyAxis.setLowerBound(-30);
        dyAxis.setUpperBound(60);
        FIODaily daily = new FIODaily(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Temperature");
        if (daily.days() > 0) {
            for (int i = 0; i < daily.days(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("D+" + Integer.toString(i), daily.getDay(i).temperatureMax());
                String tempMin = daily.getDay(i).temperatureMin().toString();
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + '\n'
                            + "Max : " + data.getYValue().toString() + " °" + '\n'
                            + "Min : " + tempMin + " °");
                    Tooltip.install(node, tooltip);
                });
            }
        }
        daysLineChart.getData().addAll(dataSeries1);
    }

    @SuppressWarnings("unchecked")
    public void showDaysPressureData(ForecastIO fio) {
        if (daysLineChart.getData() != null) {
            daysLineChart.getData().clear();
        }
        dyAxis.setAutoRanging(false);
        dyAxis.setLowerBound(900);
        dyAxis.setUpperBound(1050);

        FIODaily daily = new FIODaily(fio);
        XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Pressure");
        if (daily.days() > 0) {
            for (int i = 0; i < daily.days(); i++) {
                XYChart.Data<String, Double> data
                        = new XYChart.Data<>("D+" + Integer.toString(i), daily.getDay(i).pressure());
                dataSeries1.getData().add(data);
                data.nodeProperty().addListener(observable -> {
                    final Node node = data.getNode();
                    final Tooltip tooltip = new Tooltip(
                            data.getXValue() + '\n'
                            + "P : " + data.getYValue().toString() + " hPa"
                    );
                    Tooltip.install(node, tooltip);
                });
            }
        }
        daysLineChart.getData().addAll(dataSeries1);
    }

    public void printIcon(ForecastIO fio) {
        FIOCurrently currently = new FIOCurrently(fio);
        System.out.println(currently.get().icon());
    }

    public String adressIcon(ForecastIO fio) {
        FIOCurrently currently = new FIOCurrently(fio);
        forecasticon = currently.get().icon();
        forecasticon = deleteCharAt(forecasticon, 0);
        forecasticon = removeLastChar(forecasticon);
        iconadress = "meteoicons/" + forecasticon + ".svg";

        return iconadress;
    }

    private static String deleteCharAt(String strValue, int index) {
        return strValue.substring(0, index) + strValue.substring(index + 1);

    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
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

}
