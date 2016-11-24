/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.analytics.AnalyticSurfaceAttributes;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.widgets.slider.SliderController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import ucar.nc2.Variable;
import ucar.nc2.dataset.CoordinateAxis;

/**
 *
 * @author serge
 */
public abstract class NetCDFViewer {

    private static final Logger LOGGER = Logger.getLogger(NetCDFViewer.class.getName());
    protected final RenderableLayer vectorLayer;
    protected final RenderableLayer analyticLayer;
    protected final RenderableLayer legendLayer;
    protected AnnotationAttributes annotationAttributes;
    protected ScreenRelativeAnnotation dateInfo;
    protected String name;
    protected LocalDateTime date = null;
    protected final double maxValue;
    protected final WorldWindow wwd;
    protected double[] values;
    protected double[] directions;
    protected final double[] latTab;
    protected final double[] lonTab;
    protected final double[] timeTab;
    protected int currentTimeIndex = 0;
    protected final int latDimension;
    protected final int lonDimension;
    protected final double minLat;
    protected final double maxLat;
    protected final double minLon;
    protected final double maxLon;
    protected ScreenRelativeAnnotation fileInfo;
    protected ScreenRelativeAnnotation dataInfo;
    protected LocalDateTime localDateTime;
    protected AnalyticSurfaceController analyticSurfaceController;
    protected SliderController opacitySliderController;
    protected final String DATA_INFO = "Speed and direction of wind 10m above ground";
    protected TimeSeriesVectorField timeSeriesVectorField;
    GuiAgentServices guiAgentServices;

    public NetCDFViewer(GuiAgentServices guiAgentServices,
            RenderableLayer vectorLayer,
            RenderableLayer analyticLayer,
            RenderableLayer legendLayer,
            Netcdf netcdf,
            String dataInfo,
            List<Variable> variables,
            String name, String fileName,
            TimeSeriesVectorField timeSeriesVectorField) {

        wwd = GeoWorldWindViewImpl.getWW();
        this.guiAgentServices = guiAgentServices;
        this.vectorLayer = vectorLayer;
        this.analyticLayer = analyticLayer;
        this.legendLayer = legendLayer;
        this.name = name;

        latTab = timeSeriesVectorField.getLatitudes();
        lonTab = timeSeriesVectorField.getLongitudes();
        latDimension = timeSeriesVectorField.getLatitudeDimension();
        lonDimension = timeSeriesVectorField.getLongitudeDimension();
        values = timeSeriesVectorField.getValues(0);
        maxValue = timeSeriesVectorField.getMaxValue(0);
        directions = timeSeriesVectorField.getDirections(0);
        timeTab = timeSeriesVectorField.getTimes();
        minLat = timeSeriesVectorField.getMinLatitude();
        maxLat = timeSeriesVectorField.getMaxLatitude();
        minLon = timeSeriesVectorField.getMinLongitude();
        maxLon = timeSeriesVectorField.getMaxLongitude();

        List<CoordinateAxis> attr = netcdf.getNetcdfDataset().getCoordinateAxes();
        String[] units = null;
        for (CoordinateAxis a : attr) {
            if (a.getFullName().equals("time")) {
                units = a.getUnitsString().split(" ");
            }
        }
        if (units != null) {
            String[] t = units[units.length - 1].split("T");
            if (t != null) {
                String d = units[units.length - 1].replace("Z", "");
                try {
                    localDateTime = LocalDateTime.parse(d);
                } catch (Exception e) {
                    LOGGER.log(Level.INFO, "Date not parsed");
                }
            }
        }
        Platform.runLater(() -> {
            createOpacityController();
            createAnnotationAttributes();
            displayFileInfo(fileName, dataInfo);
            displayDateInfo();
        });
    }

    public void apply(double[] values, double[] directions, int index) {
        this.values = values;
        this.directions = directions;
        this.currentTimeIndex = index;
        java.awt.EventQueue.invokeLater(() -> {
            displayDateInfo();
            createAnalyticSurface();
            //  createVectors();
            wwd.redrawNow();
        });
    }

    protected void createAnalyticSurface() {
        if (analyticSurfaceController == null) {
            analyticSurfaceController = new AnalyticSurfaceController(
                    analyticLayer, legendLayer,
                    values,
                    latDimension,
                    lonDimension,
                    minLat, maxLat,
                    minLon, maxLon,
                    0.0, maxValue,//min, max values in m/s
                    1.0,//opacity
                    "Speed", "Knt");//legends
        } else {
            analyticSurfaceController.apply(values);
        }

    }

    protected abstract void createVectors();

    protected void createAnnotationAttributes() {
        annotationAttributes = new AnnotationAttributes();
        annotationAttributes.setBackgroundColor(new Color(0f, 0f, 0f, 0f));
        annotationAttributes.setTextColor(Color.YELLOW);
        annotationAttributes.setLeaderGapWidth(14);
        annotationAttributes.setCornerRadius(0);
        annotationAttributes.setSize(new Dimension(400, 0));
        annotationAttributes.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT); // use strict dimension width - 200
        annotationAttributes.setFont(Font.decode("Arial-BOLD-12"));
        annotationAttributes.setBorderWidth(0);
        annotationAttributes.setHighlightScale(1);             // No highlighting either
        annotationAttributes.setCornerRadius(0);
    }

    protected void createOpacityController() {
        if (opacitySliderController != null) {
            if (guiAgentServices.getRoot().getChildren().contains(opacitySliderController)) {
                guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, opacitySliderController);
                guiAgentServices.getRoot().getChildren().remove(opacitySliderController);
            }
        }
        opacitySliderController = new SliderController();
        opacitySliderController.setTranslateY(-30.0);
        opacitySliderController.setTranslateX(480.0);
        opacitySliderController.setRotate(-90);
        opacitySliderController.setVisible(true);
        opacitySliderController.getSlider().setMin(0.0);
        opacitySliderController.getSlider().setMax(1.0);
        opacitySliderController.getSlider().setValue(1.0);
        opacitySliderController.getSlider().setTooltip(new Tooltip(name + " opacity"));
        AnalyticSurfaceAttributes attrs = new AnalyticSurfaceAttributes();
        attrs.setDrawShadow(false);
        attrs.setDrawOutline(false);
        opacitySliderController.getSlider().valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            attrs.setInteriorOpacity(opacitySliderController.getSlider().getValue());
            analyticSurfaceController.getSurface().setSurfaceAttributes(attrs);
            wwd.redrawNow();
        });
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, opacitySliderController);
        guiAgentServices.getRoot().getChildren().add(opacitySliderController);
    }

    protected void displayFileInfo(String fileName, String dataInfoStr) {
        String[] nameTab = fileName.split("\\/");
        name = nameTab[nameTab.length - 1];
        fileInfo = new ScreenRelativeAnnotation(name, 0.1, 0.99);
        fileInfo.setKeepFullyVisible(true);
        fileInfo.setXMargin(5);
        fileInfo.setYMargin(5);
        fileInfo.getAttributes().setDefaults(annotationAttributes);
        analyticLayer.addRenderable(fileInfo);

        dataInfo = new ScreenRelativeAnnotation("\n" + dataInfoStr, 0.1, 0.99);
        dataInfo.setKeepFullyVisible(true);
        dataInfo.setXMargin(5);
        dataInfo.setYMargin(5);
        dataInfo.getAttributes().setDefaults(annotationAttributes);
        analyticLayer.addRenderable(dataInfo);
    }

    protected void displayDateInfo() {
        if (dateInfo != null) {
            analyticLayer.removeRenderable(dateInfo);
        }
        if (localDateTime != null) {
            LocalDateTime after = localDateTime.plusHours((long) timeTab[currentTimeIndex]);
            dateInfo = new ScreenRelativeAnnotation("\n\n" + after, 0.1, 0.99);
            dateInfo.setKeepFullyVisible(true);
            dateInfo.setXMargin(5);
            dateInfo.setYMargin(5);
            if (annotationAttributes == null) {
                createAnnotationAttributes();
            }
            dateInfo.getAttributes().setDefaults(annotationAttributes);
            analyticLayer.addRenderable(dateInfo);
        }
    }
}
