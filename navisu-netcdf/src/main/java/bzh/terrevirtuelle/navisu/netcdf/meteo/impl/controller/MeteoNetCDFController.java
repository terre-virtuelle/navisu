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
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.MeteoNetCDFViewer;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import ucar.nc2.dataset.CoordinateAxis;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class MeteoNetCDFController
        extends NetCDFController {

    protected final String GROUP = "Meteo";
    private final String NAME0 = "MeteoVector";
    private final String NAME1 = "MeteoAnalytic";
    private final String NAME2 = "MeteoLegend";
    private String layerName;
    private Date date = null;
    private ScreenRelativeAnnotation dateInfo;
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected RenderableLayer meteoLayerLegend;
    protected TimeSeriesVectorField timeSeriesVectorField;
    private static final Logger LOGGER = Logger.getLogger(MeteoNetCDFController.class.getName());
    private final GuiAgentServices guiAgentServices;
    private final WorldWindow wwd;

    public MeteoNetCDFController(LayersManagerServices layersManagerServices, int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName);
        this.guiAgentServices = guiAgentServices;
        wwd = GeoWorldWindViewImpl.getWW();
        layerName = NAME0 + "_" + Integer.toString(layerIndex);
        meteoLayerVector = layersManagerServices.getInstance(GROUP, layerName);
        layerName = NAME1 + "_" + Integer.toString(layerIndex);
        meteoLayerAnalytic = layersManagerServices.getInstance(GROUP, layerName);
        meteoLayerLegend = layersManagerServices.getInstance(GROUP, NAME2);

        timeSeriesVectorField = new TimeSeriesVectorField(time, height, latitudes, longitudes, u, v);
        latTab = timeSeriesVectorField.getLatitudes();
        lonTab = timeSeriesVectorField.getLongitudes();
        values = timeSeriesVectorField.getValues(0);
        directions = timeSeriesVectorField.getDirections(0);

        double lonMinRef = timeSeriesVectorField.getMinLongitude();
        double lonMaxRef = timeSeriesVectorField.getMaxLongitude();

        if (lonMinRef > 180 || lonMaxRef > 180) {
            lonMinRef -= 360;
            lonMaxRef -= 360;
        }

        displayInfo(fileName);

        MeteoNetCDFViewer meteoNetCDFViewer
                = new MeteoNetCDFViewer(guiAgentServices,
                        meteoLayerVector, meteoLayerAnalytic, meteoLayerLegend,
                        layerName, timeSeriesVectorField.getMaxValue(0),
                        timeSeriesVectorField.gethVFields().get(0).get(0).getValues(),
                        timeSeriesVectorField.gethVFields().get(0).get(0).getDirections(),
                        timeSeriesVectorField.getLatitudes(),
                        timeSeriesVectorField.getLongitudes(),
                        timeSeriesVectorField.getLatitudeDimension(),
                        timeSeriesVectorField.getLongitudeDimension(),
                        timeSeriesVectorField.getMinLatitude(),
                        timeSeriesVectorField.getMaxLatitude(),
                        lonMinRef, lonMaxRef
                );
    }

    private void displayInfo(String fileName) {
        String[] nameTab = fileName.split("\\/");
        String name = nameTab[nameTab.length - 1];

        List<CoordinateAxis> attr = netcdf.getNetcdfDataset().getCoordinateAxes();
        String[] units = null;
        for (CoordinateAxis a : attr) {
            if (a.getFullName().equals("time")) {
                units = a.getUnitsString().split(" ");
            }
        }
        if (units != null) {
            date = Date.from(Instant.parse(units[units.length - 1]));
            if (date != null) {
                AnnotationAttributes defaultAttributes = new AnnotationAttributes();
                defaultAttributes.setBackgroundColor(new Color(0f, 0f, 0f, 0f));
                defaultAttributes.setTextColor(Color.YELLOW);
                defaultAttributes.setLeaderGapWidth(14);
                defaultAttributes.setCornerRadius(0);
                defaultAttributes.setSize(new Dimension(300, 0));
                defaultAttributes.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT); // use strict dimension width - 200
                defaultAttributes.setFont(Font.decode("Arial-BOLD-12"));
                defaultAttributes.setBorderWidth(0);
                defaultAttributes.setHighlightScale(1);             // No highlighting either
                defaultAttributes.setCornerRadius(0);

                ScreenRelativeAnnotation fileInfo = new ScreenRelativeAnnotation(name, 0.1, 0.99);
                fileInfo.setKeepFullyVisible(true);
                fileInfo.setXMargin(5);
                fileInfo.setYMargin(5);
                fileInfo.getAttributes().setDefaults(defaultAttributes);
                meteoLayerAnalytic.addRenderable(fileInfo);

                dateInfo = new ScreenRelativeAnnotation("\n" + date.toString(), 0.1, 0.99);
                dateInfo.setKeepFullyVisible(true);
                dateInfo.setXMargin(5);
                dateInfo.setYMargin(5);
                dateInfo.getAttributes().setDefaults(defaultAttributes);
                meteoLayerAnalytic.removeRenderable(dateInfo);
                meteoLayerAnalytic.addRenderable(dateInfo);
            }
        }
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

    public ScreenRelativeAnnotation getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(ScreenRelativeAnnotation dateInfo) {
        this.dateInfo = dateInfo;
    }

}
