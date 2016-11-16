/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.controller;

import bzh.terrevirtuelle.navisu.domain.cmd.Cmd;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.MeteoNetCDFViewer;

/**
 *
 * @author serge
 */
public class CmdIncMeteoNetCDFController
        implements Cmd {

    private final MeteoNetCDFController meteoNetCDFController;
    MeteoNetCDFViewer meteoNetCDFViewer;
    private int currentTimeIndex;

    public CmdIncMeteoNetCDFController(MeteoNetCDFController meteoNetCDFController) {
        this.meteoNetCDFController = meteoNetCDFController;
    }

    @Override
    public void doIt() {
        currentTimeIndex = meteoNetCDFController.getCurrentTimeIndex();
        if (currentTimeIndex < meteoNetCDFController.getTimeDimension() - 1) {
            currentTimeIndex++;
            meteoNetCDFViewer = meteoNetCDFController.getMeteoNetCDFViewer();

            meteoNetCDFViewer.apply(
                    meteoNetCDFController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getValues(),
                    meteoNetCDFController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getDirections(),
                    currentTimeIndex
            );
        }
        meteoNetCDFController.setCurrentTime(currentTimeIndex);
    }
}
