/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.analytics.AnalyticSurfaceAttributes;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.symbols.Arrow;
import bzh.terrevirtuelle.navisu.widgets.slider.SliderController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author serge
 */
public class MeteoNetCDFViewer {

    protected GuiAgentServices guiAgentServices;
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected String name;
    protected RenderableLayer meteoLayerLegend;
    protected double maxValue;
    private final WorldWindow wwd;
    protected double[] values;
    protected double[] directions;
    protected double[] latTab;
    protected double[] lonTab;
    protected int latDimension;
    protected int lonDimension;
    double minLat;
    double maxLat;
    double minLon;
    double maxLon;

    public MeteoNetCDFViewer(GuiAgentServices guiAgentServices,
            RenderableLayer meteoLayerVector, RenderableLayer meteoLayerAnalytic,
            RenderableLayer meteoLayerLegend,
            String name, double maxValue,
            double[] values, double[] directions,
            double[] latTab, double[] lonTab,
            int latDim, int lonDim,
            double minLat, double maxLat,
            double minLon, double maxLon) {

        wwd = GeoWorldWindViewImpl.getWW();

        this.guiAgentServices = guiAgentServices;
        this.meteoLayerVector = meteoLayerVector;
        this.meteoLayerAnalytic = meteoLayerAnalytic;
        this.meteoLayerLegend = meteoLayerAnalytic;
        this.name = name;
        this.maxValue = maxValue;
        this.values = values;
        this.directions = directions;
        this.latTab = lonTab;
        this.lonTab = lonTab;
        this.latDimension = latDim;
        this.lonDimension = lonDim;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        java.awt.EventQueue.invokeLater(() -> {
            createAnalyticSurface();
          //  createVectors();
            wwd.redrawNow();
        });
    }

    private void createAnalyticSurface() {
        AnalyticSurfaceController analyticSurfaceController = new AnalyticSurfaceController(
                meteoLayerAnalytic, meteoLayerLegend,
                values,
                latDimension,
                lonDimension,
                minLat, maxLat,
                minLon, maxLon,
                0.0, maxValue,//min, max values in m/s
                1.0,//opacity
                "Meteo", "m/s");//legends
/*
        SliderController opacitySliderController = new SliderController();
        Platform.runLater(() -> {
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, opacitySliderController);
            guiAgentServices.getRoot().getChildren().add(opacitySliderController);
            opacitySliderController.setTranslateY(-260.0);
            opacitySliderController.setTranslateX(-440.0);
            opacitySliderController.setRotate(-90);
            opacitySliderController.setVisible(true);
            opacitySliderController.getSlider().setMin(0.0);
            opacitySliderController.getSlider().setMax(1.0);
            opacitySliderController.getSlider().setValue(1.0);
            opacitySliderController.getSlider().setTooltip(new Tooltip(name + " opacity"));
        });
        opacitySliderController.getSlider().valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            AnalyticSurfaceAttributes attrs = new AnalyticSurfaceAttributes();
            attrs.setDrawShadow(false);
            attrs.setDrawOutline(false);
            attrs.setInteriorOpacity(opacitySliderController.getSlider().getValue());
            analyticSurfaceController.getSurface().setSurfaceAttributes(attrs);
            wwd.redrawNow();
        });
        SliderController timeSliderController = new SliderController("TimeSliderPanel.fxml");
        Platform.runLater(() -> {
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, timeSliderController);
            guiAgentServices.getRoot().getChildren().add(timeSliderController);
            timeSliderController.setTranslateY(300.0);
            timeSliderController.setVisible(true);
            timeSliderController.getSlider().setMin(0.0);
            timeSliderController.getSlider().setMax(1.0);
            timeSliderController.getSlider().setValue(10.0);
            timeSliderController.getSlider().setShowTickMarks(true);
            timeSliderController.getSlider().setShowTickLabels(true);
            timeSliderController.getSlider().setTooltip(new Tooltip(name + " time"));
        });
        timeSliderController.getSlider().valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            wwd.redrawNow();
        });
         */
    }

    private void createVectors() {
        List<Arrow> arrows = new ArrayList<>();
        int l = 0;
        for (int h = 0; h < latDimension; h += 10) {
            for (int w = 0; w < lonDimension; w += 1) {
                if ((!Double.isNaN(latTab[h]) && !Double.isNaN(lonTab[w])
                        && !Double.isNaN(values[l + w]) && !Double.isNaN(directions[l + w]))) {

                    Arrow arrow = new Arrow(latTab[h], lonTab[w], values[l + w]);
                    double alpha = -Math.toDegrees(directions[l + w]) + arrow.getRotation();
                    if (alpha < 0) {
                        alpha = 360 + alpha;
                    }
                    if (!Double.isNaN(alpha)) {
                        arrow.setRotation(alpha);
                        arrow.setValue(AVKey.DISPLAY_NAME, String.format("%.1f m/s , %.1f , %.1f , %.1f ",
                                values[l + w], latTab[h], lonTab[w], Math.toDegrees(directions[l + w])));  //+" m/s");
                        arrows.add(arrow);
                    }
                }
            }
            l += lonDimension;
        }
         meteoLayerVector.addRenderables(arrows);
    }
}
