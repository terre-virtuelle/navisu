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
public abstract class NetCDFVectorFieldController
        implements NetCDFController, Cmd {

    private static final Logger LOGGER = Logger.getLogger(NetCDFVectorFieldController.class.getName());
    protected String fileName;
    protected Netcdf netcdf;
    protected List<Variable> variables;
    protected List<Attribute> attributes;
    protected Array u = null;
    protected Array v = null;
    protected Array latitudes = null;
    protected Array longitudes = null;
    protected Array height = null;
    protected Array time = null;
    protected Array reftime = null;
    protected TimeSeriesVectorField timeSeriesVectorField;

    public NetCDFVectorFieldController(String fileName) {
        this.fileName = fileName;
        netcdf = new Netcdf(fileName);
        variables = netcdf.getVariables();
        attributes = netcdf.getAttributes();
    }

    public NetCDFVectorFieldController(String fileName,
            String uComponent, String uComponent2, String uComponent3,
            String vComponent, String vComponent2, String vComponent3) {
        this.fileName = fileName;
        netcdf = new Netcdf(fileName);
        variables = netcdf.getVariables();
        attributes = netcdf.getAttributes();
        try {
            u = netcdf.read(uComponent);
        } catch (Exception e) {
            try {
                u = netcdf.read(uComponent2);
            } catch (Exception e2) {
                try {
                    u = netcdf.read(uComponent3);
                } catch (Exception e3) {
                    LOGGER.log(Level.SEVERE, "File not NetCDF compliant u ", e3);
                }
            }
        }
        try {
            v = netcdf.read(vComponent);
        } catch (Exception e) {
            try {
                v = netcdf.read(vComponent2);
            } catch (Exception e2) {
                try {
                    u = netcdf.read(vComponent3);
                } catch (Exception e3) {
                    LOGGER.log(Level.SEVERE, "File not NetCDF compliant u ", e3);
                }
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
        timeSeriesVectorField
                = new TimeSeriesVectorField(time, height, latitudes, longitudes, u, v);
    }

    public Netcdf getNetcdf() {
        return netcdf;
    }

    public String getFileName() {
        return fileName;
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

    public TimeSeriesVectorField getTimeSeriesVectorField() {
        return timeSeriesVectorField;
    }

    @Override
    public abstract int getCurrentTimeIndex();

    @Override
    public abstract NetCDFViewer getNetCDFViewer();

    @Override
    public abstract void setCurrentTimeIndex(int i);
}
