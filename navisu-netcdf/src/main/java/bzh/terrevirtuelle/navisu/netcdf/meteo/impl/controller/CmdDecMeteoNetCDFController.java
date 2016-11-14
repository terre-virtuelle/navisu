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
public class CmdDecMeteoNetCDFController
        implements Cmd {

    private final MeteoNetCDFController meteoNetCDFController;
    private int currentTimeIndex;
    private MeteoNetCDFViewer meteoNetCDFViewer;

    public CmdDecMeteoNetCDFController(MeteoNetCDFController meteoNetCDFController) {
        this.meteoNetCDFController = meteoNetCDFController;
    }

    @Override
    public void doIt() {
        currentTimeIndex = meteoNetCDFController.getCurrentTimeIndex();
        if (currentTimeIndex > 0) {
            currentTimeIndex--;
            meteoNetCDFViewer = meteoNetCDFController.getMeteoNetCDFViewer();
            meteoNetCDFViewer.apply(
                    meteoNetCDFController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getValues(),
                    meteoNetCDFController.getTimeSeriesVectorField().gethVFields().get(0).get(currentTimeIndex).getDirections()
            );
            meteoNetCDFController.setCurrentTime(currentTimeIndex);
        }
    }
}
