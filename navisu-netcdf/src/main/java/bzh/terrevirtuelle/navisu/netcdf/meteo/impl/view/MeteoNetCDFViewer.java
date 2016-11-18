/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.analytics.AnalyticSurfaceAttributes;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.netcdf.Meteorology;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.symbols.Arrow;
import bzh.terrevirtuelle.navisu.widgets.slider.SliderController;
import bzh.terrevirtuelle.navisu.widgets.textlist.ListController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 */
public class MeteoNetCDFViewer {

    private final GuiAgentServices guiAgentServices;
    private final RenderableLayer meteoLayerVector;
    private final RenderableLayer meteoLayerAnalytic;
    private AnnotationAttributes annotationAttributes;
    private ScreenRelativeAnnotation dateInfo;

    private String name;
    private final String fileName;
    private Calendar date = null;
    private Calendar dateOrig = null;
    private final RenderableLayer meteoLayerLegend;
    private final double maxValue;
    private final WorldWindow wwd;
    private final List<Variable> variables;
    private double[] values;
    private double[] directions;
    private final double[] latTab;
    private final double[] lonTab;
    private final double[] times;
    private int currentTimeIndex;
    private final int latDimension;
    private final int lonDimension;
    private final double minLat;
    private final double maxLat;
    private final double minLon;
    private final double maxLon;
    private ScreenRelativeAnnotation fileInfo;
    private ScreenRelativeAnnotation dataInfo;
    private AnalyticSurfaceController analyticSurfaceController;
    private SliderController opacitySliderController;
    private final String DATA_INFO = "Speed and direction of wind 10m above ground";

    public MeteoNetCDFViewer(GuiAgentServices guiAgentServices,
            RenderableLayer meteoLayerVector, RenderableLayer meteoLayerAnalytic,
            RenderableLayer meteoLayerLegend,
            List<Variable> variables,
            String name, String fileName,
            Calendar date, double[] times, int index,
            double maxValue,
            double[] latTab, double[] lonTab,
            int latDim, int lonDim,
            double minLat, double maxLat,
            double minLon, double maxLon) {

        wwd = GeoWorldWindViewImpl.getWW();

        this.guiAgentServices = guiAgentServices;
        this.meteoLayerVector = meteoLayerVector;
        this.meteoLayerAnalytic = meteoLayerAnalytic;
        this.meteoLayerLegend = meteoLayerAnalytic;
        this.variables = variables;
        this.name = name;
        this.fileName = fileName;
        this.date = date;
        this.times = times;
        this.maxValue = maxValue;
        this.latTab = latTab;
        this.lonTab = lonTab;
        this.latDimension = latDim;
        this.lonDimension = lonDim;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.dateOrig = new GregorianCalendar();
        dateOrig.setTime(date.getTime());
        
        Platform.runLater(() -> {
            if (opacitySliderController != null) {
                if (guiAgentServices.getRoot().getChildren().contains(opacitySliderController)) {
                    guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, opacitySliderController);
                    guiAgentServices.getRoot().getChildren().remove(opacitySliderController);
                }
            }
            opacitySliderController = new SliderController();
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, opacitySliderController);
            guiAgentServices.getRoot().getChildren().add(opacitySliderController);
            opacitySliderController.setTranslateY(-30.0);
            opacitySliderController.setTranslateX(480.0);
            opacitySliderController.setRotate(-90);
            opacitySliderController.setVisible(true);
            opacitySliderController.getSlider().setMin(0.0);
            opacitySliderController.getSlider().setMax(1.0);
            opacitySliderController.getSlider().setValue(1.0);
            opacitySliderController.getSlider().setTooltip(new Tooltip(name + " opacity"));
            opacitySliderController.getSlider().valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                AnalyticSurfaceAttributes attrs = new AnalyticSurfaceAttributes();
                attrs.setDrawShadow(false);
                attrs.setDrawOutline(false);
                attrs.setInteriorOpacity(opacitySliderController.getSlider().getValue());
                analyticSurfaceController.getSurface().setSurfaceAttributes(attrs);
                wwd.redrawNow();
            });
            
            //Liste des donnees presentes dans le fic
            //****/
            ListController textListController = new ListController();
            variables.stream().forEach((v) -> {
                if (Meteorology.isValid(v.getFullName())) {
                    textListController.getListView().getItems().add(v.getFullName());
                }
            });
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, textListController);
            guiAgentServices.getRoot().getChildren().add(textListController);

            createAnnotationAttributes();
            displayFileInfo(fileName, DATA_INFO);
            displayDateInfo();
            //*/
        });
    }
            
    public void apply(double[] values, double[] directions, int index) {
        this.values = values;
        this.directions = directions;
        this.currentTimeIndex = index;
        java.awt.EventQueue.invokeLater(() -> {
            displayDateInfo();
            createAnalyticSurface();
            createVectors();
            wwd.redrawNow();
        });
    }

    private void createAnalyticSurface() {
        if (analyticSurfaceController == null) {
            analyticSurfaceController = new AnalyticSurfaceController(
                    meteoLayerAnalytic, meteoLayerLegend,
                    values,
                    latDimension,
                    lonDimension,
                    minLat, maxLat,
                    minLon, maxLon,
                    0.0, maxValue,//min, max values in m/s
                    1.0,//opacity
                    "Meteo", "m/s");//legends
        } else {
            analyticSurfaceController.apply(values);
        }

    }

    private void createVectors() {
        List<Arrow> arrows = new ArrayList<>();
        int l = 0;
        for (int h = 0; h < latDimension; h += 10) {
            for (int w = 0; w < lonDimension; w += 10) {
                System.out.print("  "+latTab[h]+","+ lonTab[w]);  
                if ((!Double.isNaN(values[l + w]) && !Double.isNaN(directions[l + w]))) {
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

    private void createAnnotationAttributes() {
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

    private void displayFileInfo(String fileName, String dataInfoStr) {
        String[] nameTab = fileName.split("\\/");
        name = nameTab[nameTab.length - 1];
        fileInfo = new ScreenRelativeAnnotation(name, 0.1, 0.99);
        fileInfo.setKeepFullyVisible(true);
        fileInfo.setXMargin(5);
        fileInfo.setYMargin(5);
        fileInfo.getAttributes().setDefaults(annotationAttributes);
        meteoLayerAnalytic.addRenderable(fileInfo);
        dataInfo = new ScreenRelativeAnnotation("\n" + dataInfoStr, 0.1, 0.99);
        dataInfo.setKeepFullyVisible(true);
        dataInfo.setXMargin(5);
        dataInfo.setYMargin(5);
        dataInfo.getAttributes().setDefaults(annotationAttributes);
        meteoLayerAnalytic.addRenderable(dataInfo);
    }

    private void displayDateInfo() {
        if (dateInfo != null) {
            meteoLayerAnalytic.removeRenderable(dateInfo);
        }
        if (date != null) {
            date.set(Calendar.HOUR, dateOrig.get(Calendar.HOUR) + (int) times[currentTimeIndex]);
            dateInfo = new ScreenRelativeAnnotation("\n\n" + date.getTime().toString().trim(), 0.1, 0.99);
            dateInfo.setKeepFullyVisible(true);
            dateInfo.setXMargin(5);
            dateInfo.setYMargin(5);
            if (annotationAttributes == null) {
                createAnnotationAttributes();
            }
            dateInfo.getAttributes().setDefaults(annotationAttributes);
            meteoLayerAnalytic.addRenderable(dateInfo);
            // System.out.println("date : " + date.get(Calendar.HOUR));
            // System.out.println("time : " + times[currentTimeIndex]);
        }
    }
}
