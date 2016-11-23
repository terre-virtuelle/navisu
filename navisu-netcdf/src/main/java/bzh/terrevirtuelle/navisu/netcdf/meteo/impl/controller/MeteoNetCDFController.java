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
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.MeteoNetCDFViewer;
import bzh.terrevirtuelle.navisu.widgets.slider.ButtonController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ScreenRelativeAnnotation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ucar.nc2.dataset.CoordinateAxis;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class MeteoNetCDFController
        extends NetCDFController
        implements Cmd {
    protected final String GROUP = "Meteo";
    private final String NAME0 = "MeteoVector";
    private final String NAME1 = "MeteoAnalytic";
    private final String NAME2 = "MeteoLegend";
    private String layerName;
    private LocalDateTime localDateTime;
    private ScreenRelativeAnnotation dateInfo;
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected RenderableLayer meteoLayerLegend;
    protected TimeSeriesVectorField timeSeriesVectorField;

    private final GuiAgentServices guiAgentServices;
    private double lonMinRef;
    private double lonMaxRef;
    private int currentTimeIndex = 0;
    private MeteoNetCDFViewer meteoNetCDFViewer;
    private final ImageButton buttonR;
    private final ImageButton buttonL;

    public MeteoNetCDFController(LayersManagerServices layersManagerServices,
            int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName);
        this.guiAgentServices = guiAgentServices;
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
            String[] t = units[units.length - 1].split("T");
            System.out.println("units[units.length - 1] " + units[units.length - 1]);
            String d = units[units.length - 1].replace("Z", "");
            localDateTime = LocalDateTime.parse(d);
        }
        doIt();
        buttonR = new ImageButton(new ImageView(
                new Image(getClass().getResourceAsStream("arrow-right-green.png"))),
                new CmdIncMeteoNetCDFController(this));
        buttonL = new ImageButton(new ImageView(
                new Image(getClass().getResourceAsStream("arrow-left-green.png"))),
                new CmdDecMeteoNetCDFController(this));
        GridPane gridPane = createGridPane(1, 6);
        Platform.runLater(() -> {
            guiAgentServices.getStatusBorderPane().setLeft(gridPane);
            gridPane.add(buttonL, 0, 0);
            gridPane.add(buttonR, 5, 0);
        });
    }

    @Override
    public final void doIt() {
        meteoNetCDFViewer = new MeteoNetCDFViewer(guiAgentServices,
                meteoLayerVector, meteoLayerAnalytic, meteoLayerLegend,
                variables,
                layerName, fileName,
                localDateTime, times, currentTimeIndex,
                timeSeriesVectorField.getMaxValue(0),
                latTab,
                lonTab,
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

    public LocalDateTime getDate() {
        return localDateTime;
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

    private GridPane createGridPane(int numRows, int numColumns) {
        GridPane grid = new GridPane();
        for (int row = 0; row < numRows; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        return grid;
    }

    class ImageButton
            extends Button {

        public ImageButton(ImageView img, Cmd cmd) {
            super("", img);

            setOnAction((ActionEvent event) -> {
                cmd.doIt();
            });
        }
    }
}
