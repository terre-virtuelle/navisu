/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFScalarFieldController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.view.PressureNetCDFViewer;
import gov.nasa.worldwind.layers.RenderableLayer;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class PressureScalarFieldController
        extends NetCDFScalarFieldController {

    protected final String GROUP = "Meteo";
    private final String NAME0 = "PressureVector";
    private final String NAME1 = "PressureAnalytic";
    private final String NAME2 = "PressureLegend";
    private static final String V_COMPONENT = "Pressure_surface";
    private static final String V_COMPONENT_2 = "Pressure_surface";
    private static final String TITLE = "Pressure_surface";
    private static final String ICON_R = "arrow-right-green.png";
    private static final String ICON_L = "arrow-left-green.png";
    private String layerName;
    protected RenderableLayer layerVector;
    protected RenderableLayer layerAnalytic;
    protected RenderableLayer layerLegend;
    private final GuiAgentServices guiAgentServices;
    private int currentTimeIndex = 0;
    private NetCDFViewer netCDFViewer;

    public PressureScalarFieldController(
            LayersManagerServices layersManagerServices,
            int layerIndex,
            GuiAgentServices guiAgentServices,
            String fileName) {
        super(fileName, V_COMPONENT, V_COMPONENT_2);
        this.guiAgentServices = guiAgentServices;
        layerName = NAME0 + "_" + Integer.toString(layerIndex);
        layerVector = layersManagerServices.getInstance(GROUP, layerName);
        layerName = NAME1 + "_" + Integer.toString(layerIndex);
        layerAnalytic = layersManagerServices.getInstance(GROUP, layerName);
        layerLegend = layersManagerServices.getInstance(GROUP, NAME2);
/*
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
*/
        doIt();
    }

    @Override
    public final void doIt() {
        netCDFViewer = new PressureNetCDFViewer(guiAgentServices,
                layerVector, layerAnalytic, layerLegend,
                netcdf,
                TITLE,
                variables,
                layerName, fileName,
                timeSeriesVectorField
        );
        netCDFViewer.apply(timeSeriesVectorField.gethVFields().get(0).get(0).getValues(),
                timeSeriesVectorField.gethVFields().get(0).get(0).getDirections(),
                currentTimeIndex);
    }

    @Override
    public int getCurrentTimeIndex() {
        return currentTimeIndex;
    }

    @Override
    public NetCDFViewer getNetCDFViewer() {
        return netCDFViewer;
    }

    @Override
    public void setCurrentTimeIndex(int currentTimeIndex) {
        this.currentTimeIndex = currentTimeIndex;
    }
}
