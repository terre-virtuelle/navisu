/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCdfController;
import bzh.terrevirtuelle.navisu.widgets.slider.SliderController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class MeteoNetCdfController
        extends NetCdfController {

    protected final String GROUP = "Meteo";
    private final String NAME0 = "MeteoVector";
    private final String NAME1 = "MeteoAnalytic";
    private final String NAME2 = "MeteoLegend";
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected RenderableLayer meteoLayerLegend;
    protected TimeSeriesVectorField timeSeriesVectorField;
    private static final Logger LOGGER = Logger.getLogger(MeteoNetCdfController.class.getName());
    private GuiAgentServices guiAgentServices;
    private final WorldWindow wwd;

    public MeteoNetCdfController(LayersManagerServices layersManagerServices, int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName);
        this.guiAgentServices = guiAgentServices;
        wwd = GeoWorldWindViewImpl.getWW();
        meteoLayerVector = layersManagerServices.getInstance(GROUP, NAME0 + "_" + Integer.toString(layerIndex));
        meteoLayerAnalytic = layersManagerServices.getInstance(GROUP, NAME1 + "_" + Integer.toString(layerIndex));
        meteoLayerLegend = layersManagerServices.getInstance(GROUP, NAME2);

        timeSeriesVectorField = new TimeSeriesVectorField(time, height, latitudes, longitudes, u, v);
        latTab = timeSeriesVectorField.getLatitudes();
        lonTab = timeSeriesVectorField.getLongitudes();
        values = timeSeriesVectorField.getValues(0);
        directions = timeSeriesVectorField.getDirections(0);

        SliderController sliderController = new SliderController();
        Platform.runLater(() -> {
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, sliderController);
            guiAgentServices.getRoot().getChildren().add(sliderController);
            sliderController.setVisible(true);
            sliderController.getSlider().setMin(0.0);
            sliderController.getSlider().setMax(1.0);
        });
        sliderController.getSlider().valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            meteoLayerLegend.setOpacity(sliderController.getSlider().getValue());
            wwd.redrawNow();
        });
        /*
        List<Arrow> arrows = new ArrayList<>();
        int l = 0;
        for (int h = 0; h < latTab.length; h += 50) {
            for (int w = 0; w < lonTab.length; w += 1) {
                Arrow arrow = new Arrow(latTab[h], lonTab[w], values[l + w]);
                double alpha = -Math.toDegrees(directions[l + w]) + arrow.getRotation();
                if (alpha < 0) {
                    alpha = 360 + alpha;
                }
                // arrow.setRotation(-Math.toDegrees(directions[l + w]) + arrow.getRotation());
                arrow.setRotation(alpha);
                //  System.out.println("alpha : " + values[l + w] + " " + alpha);
                arrow.setValue(AVKey.DISPLAY_NAME, String.format("%.1f m/s , %.1f , %.1f , %.1f ",
                        values[l + w], latTab[h], lonTab[w], Math.toDegrees(directions[l + w])));  //+" m/s");
                arrows.add(arrow);
            }
            l += lonTab.length;
        }
        meteoLayerVector.addRenderables(arrows);
         */
        double lonMinRef = timeSeriesVectorField.getMinLongitude();
        double lonMaxRef = timeSeriesVectorField.getMaxLongitude();
        if(lonMinRef>180 || lonMaxRef>180){
            lonMinRef-=360;
            lonMaxRef-=360;
        }

        AnalyticSurfaceController analyticSurfaceController = new AnalyticSurfaceController(
                meteoLayerAnalytic, meteoLayerLegend,
                timeSeriesVectorField.gethVFields().get(0).get(0).getValues(),
                timeSeriesVectorField.getLatitudeDimension(),
                timeSeriesVectorField.getLongitudeDimension(),
                timeSeriesVectorField.getMinLatitude(), timeSeriesVectorField.getMaxLatitude(),
                lonMinRef, lonMaxRef,
               // timeSeriesVectorField.getMinLongitude(), timeSeriesVectorField.getMaxLongitude(),
                0.0, timeSeriesVectorField.getMaxValue(0),//min, max values in m/s
                .8,
                "Meteo", "Kts");
    }

    public int getLatitudeDimension() {
        return timeSeriesVectorField.getLatitudeDimension();
    }

    public int getLongitudeDimension() {
        return timeSeriesVectorField.getLongitudeDimension();
    }

    public int getTimeDimension() {
        return timeSeriesVectorField.getTimeDimension();
    }

    public double getMaxLatitude() {
        return timeSeriesVectorField.getMaxLatitude();
    }

    public double getMaxLongitude() {
        return timeSeriesVectorField.getMaxLongitude();
    }

    public double getMinLatitude() {
        return timeSeriesVectorField.getMinLatitude();
    }

    public double getMinLongitude() {
        return timeSeriesVectorField.getMinLongitude();
    }

    public RenderableLayer getLayer() {
        return meteoLayerVector;

    }

    public void setCurrentTime(int currentTimeIndex) {
        //this.currentTimeIndex = currentTimeIndex;
        values = timeSeriesVectorField.getValues(currentTimeIndex);
        directions = timeSeriesVectorField.getDirections(currentTimeIndex);
    }

}
