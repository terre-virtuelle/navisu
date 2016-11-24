/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.controller;

import bzh.terrevirtuelle.navisu.domain.cmd.Cmd;
import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFVectorFieldController;

/**
 *
 * @author serge
 */
public class CmdIncTimeNetCDFController
        implements Cmd {

    private final  NetCDFVectorFieldController netCDFvectorFieldController;
    NetCDFViewer netCDFViewer;
    private int currentTimeIndex;

    public CmdIncTimeNetCDFController(NetCDFVectorFieldController netCDFvectorFieldController) {
        this.netCDFvectorFieldController = netCDFvectorFieldController;
    }

    @Override
    public void doIt() {
        currentTimeIndex = netCDFvectorFieldController.getCurrentTimeIndex();
        if (currentTimeIndex < netCDFvectorFieldController.getTimeDimension() - 1) {
            currentTimeIndex++;
            netCDFViewer = netCDFvectorFieldController.getNetCDFViewer();

            netCDFViewer.apply(netCDFvectorFieldController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getValues(),
                    netCDFvectorFieldController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getDirections(),
                    currentTimeIndex
            );
        }
        netCDFvectorFieldController.setCurrentTimeIndex(currentTimeIndex);
    }
}
