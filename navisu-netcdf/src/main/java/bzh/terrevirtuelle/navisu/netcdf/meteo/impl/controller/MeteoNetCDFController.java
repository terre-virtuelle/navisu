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
import bzh.terrevirtuelle.navisu.domain.cmd.Cmd;
import bzh.terrevirtuelle.navisu.netcdf.common.view.WwjButtonController;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.MeteoNetCDFViewer;
import bzh.terrevirtuelle.navisu.widgets.slider.ButtonController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.Scene;
import ucar.nc2.dataset.CoordinateAxis;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class MeteoNetCDFController
        extends NetCDFController
        implements Cmd {

    private static MeteoNetCDFController INSTANCE;
    protected final String GROUP = "Meteo";
    private final String NAME0 = "MeteoVector";
    private final String NAME1 = "MeteoAnalytic";
    private final String NAME2 = "MeteoLegend";
    private String layerName;
    private Date date;
    private Calendar calendar;
    private ScreenRelativeAnnotation dateInfo;
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected RenderableLayer meteoLayerLegend;
    protected TimeSeriesVectorField timeSeriesVectorField;
    private static final Logger LOGGER = Logger.getLogger(MeteoNetCDFController.class.getName());
    private final GuiAgentServices guiAgentServices;
    private final WorldWindow wwd;
    private final Scene scene;
    private final int X_OFFSET = 50;
    private final int Y_OFFSET = 100;
    private final ButtonController rightTimeButtonController;
    private final ButtonController leftTimeButtonController;
    private double lonMinRef;
    private double lonMaxRef;
    private int currentTimeIndex = 0;
    private MeteoNetCDFViewer meteoNetCDFViewer;

    public MeteoNetCDFController(LayersManagerServices layersManagerServices,
            int layerIndex,
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
        times = timeSeriesVectorField.getTimes();

        lonMinRef = timeSeriesVectorField.getMinLongitude();
        lonMaxRef = timeSeriesVectorField.getMaxLongitude();
        if (lonMinRef > 180 || lonMaxRef > 180) {
            lonMinRef -= 360;
            lonMaxRef -= 360;
        }
        List<CoordinateAxis> attr = netcdf.getNetcdfDataset().getCoordinateAxes();
        String[] units = null;
        for (CoordinateAxis a : attr) {
            if (a.getFullName().equals("time")) {
                units = a.getUnitsString().split(" ");
            }
        }
        if (units != null) {
          //  String[] t = units[units.length - 1].split("T");
         //   System.out.println("units[units.length - 1] "+ units[units.length - 1]);
         //   LocalDateTime localDateStr = LocalDateTime.parse(units[units.length - 1]);
         //   System.out.println("localDateStr : " + localDateStr);
            date = Date.from(Instant.parse(units[units.length - 1]));
            calendar = new GregorianCalendar();
            calendar.setTime(date);
        }

        rightTimeButtonController = new ButtonController();
        leftTimeButtonController = new ButtonController();

        scene = guiAgentServices.getScene();
       
        doIt();

        WwjButtonController rightButtonController
                = new WwjButtonController(guiAgentServices, meteoLayerAnalytic, "R",
                        "images/right.png", "images/right1.png", X_OFFSET, Y_OFFSET,
                        new CmdIncMeteoNetCDFController(this));
        WwjButtonController leftButtonController
                = new WwjButtonController(guiAgentServices, meteoLayerAnalytic, "L",
                        "images/left.png", "images/left1.png", X_OFFSET, Y_OFFSET,
                        new CmdDecMeteoNetCDFController(this));
        //  WwjButtonController lquitButtonController
        //         = new WwjButtonController(guiAgentServices, meteoLayerAnalytic, "L", 
        //                  "images/quit.png", "images/quit.png", 50, 600); 
        
    }

    @Override
    public final void doIt() {
        meteoNetCDFViewer = new MeteoNetCDFViewer(guiAgentServices,
                meteoLayerVector, meteoLayerAnalytic, meteoLayerLegend,
                variables,
                layerName, fileName,
                 calendar, times, currentTimeIndex,
                timeSeriesVectorField.getMaxValue(0),
                timeSeriesVectorField.getLatitudes(),
                timeSeriesVectorField.getLongitudes(),
                timeSeriesVectorField.getLatitudeDimension(),
                timeSeriesVectorField.getLongitudeDimension(),
                timeSeriesVectorField.getMinLatitude(),
                timeSeriesVectorField.getMaxLatitude(),
                lonMinRef, lonMaxRef
        );
        meteoNetCDFViewer.apply(timeSeriesVectorField.gethVFields().get(0).get(0).getValues(),
                timeSeriesVectorField.gethVFields().get(0).get(0).getDirections(),
                currentTimeIndex);
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
        this.currentTimeIndex = currentTimeIndex;
        // values = timeSeriesVectorField.getValues(currentTimeIndex);
        // directions = timeSeriesVectorField.getDirections(currentTimeIndex);
    }

    public ScreenRelativeAnnotation getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(ScreenRelativeAnnotation dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getLayerName() {
        return layerName;
    }

    public Date getDate() {
        return date;
    }

    public RenderableLayer getMeteoLayerVector() {
        return meteoLayerVector;
    }

    public RenderableLayer getMeteoLayerAnalytic() {
        return meteoLayerAnalytic;
    }

    public RenderableLayer getMeteoLayerLegend() {
        return meteoLayerLegend;
    }

    public TimeSeriesVectorField getTimeSeriesVectorField() {
        return timeSeriesVectorField;
    }

    public GuiAgentServices getGuiAgentServices() {
        return guiAgentServices;
    }

    public double getLonMinRef() {
        return lonMinRef;
    }

    public double getLonMaxRef() {
        return lonMaxRef;
    }

    public int getCurrentTimeIndex() {
        return currentTimeIndex;
    }

    public MeteoNetCDFViewer getMeteoNetCDFViewer() {
        return meteoNetCDFViewer;
    }

}
