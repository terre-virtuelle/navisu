/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.impl.controller;

import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;

/**
 *
 * @author serge
 * @date Nov 23, 2016
 */
public interface NetCDFController {

    public void doIt();

    public int getCurrentTimeIndex();

    public NetCDFViewer getNetCDFViewer();

    public void setCurrentTimeIndex(int i);
}
