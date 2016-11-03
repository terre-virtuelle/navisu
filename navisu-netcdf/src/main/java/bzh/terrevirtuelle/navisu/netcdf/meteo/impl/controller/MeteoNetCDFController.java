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
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Logger;

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
    private String name;
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
        name = NAME0 + "_" + Integer.toString(layerIndex);
        meteoLayerVector = layersManagerServices.getInstance(GROUP, name);
        name = NAME1 + "_" + Integer.toString(layerIndex);
        meteoLayerAnalytic = layersManagerServices.getInstance(GROUP, name);
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
        MeteoNetCDFViewer meteoNetCDFViewer
                = new MeteoNetCDFViewer(guiAgentServices,
                        meteoLayerVector, meteoLayerAnalytic, meteoLayerLegend,
                        name, timeSeriesVectorField.getMaxValue(0),
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
