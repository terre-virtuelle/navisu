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
public class CmdDecTimeNetCDFController
        implements Cmd {

    private final NetCDFVectorFieldController netCDFVectorFieldController;
    private int currentTimeIndex;
    private NetCDFViewer netCDFViewer;

    public CmdDecTimeNetCDFController(NetCDFVectorFieldController netCDFVectorFieldController) {
        this.netCDFVectorFieldController = netCDFVectorFieldController;
    }

    @Override
    public void doIt() {
        currentTimeIndex = netCDFVectorFieldController.getCurrentTimeIndex();
        if (currentTimeIndex > 0) {
            currentTimeIndex--;
            netCDFViewer = netCDFVectorFieldController.getNetCDFViewer();
            netCDFViewer.apply(netCDFVectorFieldController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getValues(),
                    netCDFVectorFieldController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getDirections(),
                    currentTimeIndex
            );
            netCDFVectorFieldController.setCurrentTimeIndex(currentTimeIndex);
        }
    }
}
