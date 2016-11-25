/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.controller;

import bzh.terrevirtuelle.navisu.netcdf.common.controller.CmdIncTimeNetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.CmdDecTimeNetCDFController;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.netcdf.common.view.Util;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFVectorFieldController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.view.WindNetCDFViewer;
import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import gov.nasa.worldwind.layers.RenderableLayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class WindVectorFieldController
        extends NetCDFVectorFieldController {

    protected final String GROUP = "Meteo";
    private final String NAME0 = "WindVector";
    private final String NAME1 = "WindAnalytic";
    private final String NAME2 = "WindLegend";
    private static final String U_COMPONENT = "u-component_of_wind_height_above_ground";
    private static final String U_COMPONENT_2 = "u-component_of_wind_surface";
    private static final String V_COMPONENT = "v-component_of_wind_height_above_ground";
    private static final String V_COMPONENT_2 = "v-component_of_wind_surface";
    private static final String TITLE = "Speed and direction of wind 10m above ground";
    private static final String ICON_R = "arrow-right-green.png";
    private static final String ICON_L = "arrow-left-green.png";
    private String layerName;
    protected RenderableLayer vectorLayer;
    protected RenderableLayer analyticLayer;
    protected RenderableLayer legendLayer;
    private final GuiAgentServices guiAgentServices;
    private int currentTimeIndex = 0;
    private NetCDFViewer windNetCDFViewer;

    public WindVectorFieldController(
            LayersManagerServices layersManagerServices,
            int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName, U_COMPONENT, U_COMPONENT_2, V_COMPONENT, V_COMPONENT_2);
        this.guiAgentServices = guiAgentServices;
        layerName = NAME0 + "_" + Integer.toString(layerIndex);
        vectorLayer = layersManagerServices.getInstance(GROUP, layerName);
        layerName = NAME1 + "_" + Integer.toString(layerIndex);
        analyticLayer = layersManagerServices.getInstance(GROUP, layerName);
        legendLayer = layersManagerServices.getInstance(GROUP, NAME2);

        Button buttonR = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_R))));
        buttonR.setOnAction((ActionEvent event) -> {
            new CmdIncTimeNetCDFController(this).doIt();
        });

        Button buttonL = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_L))));
        buttonL.setOnAction((ActionEvent event) -> {
            new CmdDecTimeNetCDFController(this).doIt();
        });

        GridPane gridPane = Util.createGridPane(1, 6);
        Platform.runLater(() -> {
            guiAgentServices.getStatusBorderPane().setLeft(gridPane);
            gridPane.add(buttonL, 0, 0);
            gridPane.add(buttonR, 5, 0);
        });

        doIt();
    }

    @Override
    public final void doIt() {
        windNetCDFViewer = new WindNetCDFViewer(guiAgentServices,
                vectorLayer, analyticLayer, legendLayer,
                netcdf,
                TITLE,
                variables,
                layerName, fileName,
                timeSeriesVectorField
        );
        windNetCDFViewer.apply(timeSeriesVectorField.gethVFields().get(0).get(0).getValues(),
                timeSeriesVectorField.gethVFields().get(0).get(0).getDirections(),
                currentTimeIndex);
    }

    @Override
    public int getCurrentTimeIndex() {
        return currentTimeIndex;
    }

    @Override
    public NetCDFViewer getNetCDFViewer() {
        return windNetCDFViewer;
    }

    @Override
    public void setCurrentTimeIndex(int currentTimeIndex) {
        this.currentTimeIndex = currentTimeIndex;
    }
}
