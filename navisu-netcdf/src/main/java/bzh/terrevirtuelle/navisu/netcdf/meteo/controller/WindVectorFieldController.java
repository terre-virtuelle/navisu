/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.controller;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.CmdIncTimeNetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.CmdDecTimeNetCDFController;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFVectorFieldController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.view.WindNetCDFViewer;
import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import bzh.terrevirtuelle.navisu.netcdf.common.view.Util;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class WindVectorFieldController
        extends NetCDFVectorFieldController {

    protected final String GROUP = "Meteo";
    private String NAME0 = "WindVector";
    private String NAME1 = "WindAnalytic";
    private String NAME2 = "WindLegend";
    private static final String U_COMPONENT = "u-component_of_wind_height_above_ground";
    private static final String U_COMPONENT_2 = "u-component_of_wind_surface";
    private static final String U_COMPONENT_3 = "u-component_of_wind_msl";
    private static final String V_COMPONENT = "v-component_of_wind_height_above_ground";
    private static final String V_COMPONENT_2 = "v-component_of_wind_surface";
    private static final String V_COMPONENT_3 = "v-component_of_wind_msl";
    private static final String TITLE = "Speed and direction of wind 10m above ground";
    private static final String ICON_R = "arrow-right-green.png";
    private static final String ICON_L = "arrow-left-green.png";
    private static final String ICON_RR = "arrow-read-green.png";
    private static final String ICON_RS = "arrow-stop-green.png";
    private static final String ICON_Q = "quit.png";
    private static final String BUTTON_NAME_STYLE_CLASS = "meteo-button";
    private static final String GRID_PANE_STYLE_CLASS = "grid-pane";
    private String layerName;
    protected RenderableLayer vectorLayer;
    protected RenderableLayer analyticLayer;
    protected RenderableLayer legendLayer;
    private final GuiAgentServices guiAgentServices;
    private int currentTimeIndex = 0;
    private NetCDFViewer windNetCDFViewer;
    private Pane meteoReaderPane;
    private final LayersManagerServices layersManagerServices;
    private final LayerTreeServices layerTreeServices;

    public WindVectorFieldController(
            LayersManagerServices layersManagerServices,
            LayerTreeServices layerTreeServices,
            int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName, U_COMPONENT, U_COMPONENT_2, U_COMPONENT_3,
                V_COMPONENT, V_COMPONENT_2, V_COMPONENT_3);
        this.layersManagerServices = layersManagerServices;
        this.layerTreeServices = layerTreeServices;
        this.guiAgentServices = guiAgentServices;
        NAME0 = NAME0 + "_" + Integer.toString(layerIndex);
        vectorLayer = layersManagerServices.getLayer(GROUP, NAME0);
        NAME1 = NAME1 + "_" + Integer.toString(layerIndex);
        analyticLayer = layersManagerServices.getLayer(GROUP, NAME1);
        NAME2 = NAME2 + "_" + Integer.toString(layerIndex);
        legendLayer = layersManagerServices.getLayer(GROUP, NAME2);
        createGUI();
        doIt();
    }

    private void createGUI() {
        Button buttonR = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_R))));
        buttonR.getStyleClass().add(BUTTON_NAME_STYLE_CLASS);
        buttonR.setOnAction((ActionEvent event) -> {
            new CmdIncTimeNetCDFController(this).doIt();
        });
        Button buttonRead = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_RR))));
        buttonRead.getStyleClass().add(BUTTON_NAME_STYLE_CLASS);
        buttonRead.setOnAction((ActionEvent event) -> {
            forever();
        });
        Button buttonStop = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_RS))));
        buttonStop.getStyleClass().add(BUTTON_NAME_STYLE_CLASS);
        Button buttonL = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_L))));
        buttonL.getStyleClass().add(BUTTON_NAME_STYLE_CLASS);
        buttonL.setOnAction((ActionEvent event) -> {
            new CmdDecTimeNetCDFController(this).doIt();
        });
        Button buttonQ = new Button("", new ImageView(
                new Image(getClass().getResourceAsStream(ICON_Q))));
        buttonQ.getStyleClass().add(BUTTON_NAME_STYLE_CLASS);
        buttonQ.setOnAction((ActionEvent event) -> {
            vectorLayer.removeAllRenderables();
            vectorLayer.dispose();
            analyticLayer.removeAllRenderables();
            analyticLayer.dispose();
            legendLayer.removeAllRenderables();
            legendLayer.dispose();
            guiAgentServices.getStatusBorderPane().getChildren().remove(meteoReaderPane);
            layersManagerServices.removeLayer(vectorLayer, analyticLayer, legendLayer);
            GeoWorldWindViewImpl.getWW().redrawNow();
        });
        meteoReaderPane = new Pane();
        GridPane gridPane = Util.createGridPane(1, 5);
        Platform.runLater(() -> {
            guiAgentServices.getStatusBorderPane().setLeft(meteoReaderPane);
            meteoReaderPane.getChildren().add(gridPane);
            gridPane.setLayoutX(0.0);
            gridPane.setLayoutY(-100.0);
            gridPane.getStyleClass().add(GRID_PANE_STYLE_CLASS);
            gridPane.setMaxWidth(500.0);
            gridPane.add(buttonL, 0, 0);
            gridPane.add(buttonStop, 1, 0);
            gridPane.add(buttonRead, 2, 0);
            gridPane.add(buttonR, 3, 0);
            gridPane.add(buttonQ, 4, 0);
        });
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

    public void forever() {
        currentTimeIndex = getCurrentTimeIndex();
        guiAgentServices.getJobsManager().newJob("", (ProgressHandle progressHandle) -> {
            while (currentTimeIndex < getTimeDimension() - 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WindVectorFieldController.class.getName()).log(Level.SEVERE, null, ex);
                }
                currentTimeIndex++;
                
                windNetCDFViewer.apply(getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getValues(),
                        getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getDirections(),
                        currentTimeIndex
                );
                currentTimeIndex++;
                if (currentTimeIndex == getTimeDimension() - 2) {
                    currentTimeIndex = 0;
                }
            }
        });
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
