/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.impl.controller;

import bzh.terrevirtuelle.navisu.domain.cmd.Cmd;
import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public abstract class NetCDFScalarFieldController
        implements NetCDFController, Cmd {

    private static final Logger LOGGER = Logger.getLogger(NetCDFScalarFieldController.class.getName());

    protected Netcdf netcdf;
    protected String fileName;
    protected List<Variable> variables;
    protected List<Attribute> attributes;
    protected String variableName;
    protected Array u = null;
    protected Array latitudes = null;
    protected Array longitudes = null;
    protected Array height = null;
    protected Array time = null;
    protected Array reftime = null;
    protected double[] values;
    protected double[] latTab;
    protected double[] lonTab;
    protected double[] timeTab;
    protected TimeSeriesVectorField timeSeriesVectorField;

    public NetCDFScalarFieldController(String fileName,
            String variableName, String variableName2) {
        this.fileName = fileName;
        netcdf = new Netcdf(fileName);
        variables = netcdf.getVariables();
        attributes = netcdf.getAttributes();
        try {
            u = netcdf.read(variableName);
        } catch (Exception e) {
            try {
                u = netcdf.read(variableName2);
            } catch (Exception e1) {
                String error = "File not NetCDF compliant " + variableName;
                LOGGER.log(Level.SEVERE, error, e1);
            }
        }
        try {
            latitudes = netcdf.read("lat");
            longitudes = netcdf.read("lon");
        } catch (Exception e1) {
            LOGGER.log(Level.SEVERE, "File not NetCDF compliant : lat/lon ", e1);
        }
        try {
            height = netcdf.read("height_above_ground");
        } catch (Exception e1) {
            height = Array.factory(double.class, new int[]{1}, new double[]{0.0});
        }
        try {
            time = netcdf.read("time");
        } catch (Exception e1) {
            LOGGER.log(Level.SEVERE, "File not NetCDF compliant : time ", e1);
        }
        try {
            reftime = netcdf.read("reftime");
        } catch (Exception e1) {
            LOGGER.log(Level.SEVERE, "File not NetCDF compliant : reftime ", e1);
        }
        try {
            reftime = netcdf.read(variableName);
        } catch (Exception e1) {
            LOGGER.log(Level.SEVERE, "File not NetCDF compliant : " + variableName, e1);
        }
        timeSeriesVectorField
                = new TimeSeriesVectorField(time, height, latitudes, longitudes, u, null);
    }

    public Netcdf getNetcdf() {
        return netcdf;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public abstract void doIt();

    @Override
    public abstract int getCurrentTimeIndex();

    @Override
    public abstract NetCDFViewer getNetCDFViewer();

    @Override
    public abstract void setCurrentTimeIndex(int i);
}
