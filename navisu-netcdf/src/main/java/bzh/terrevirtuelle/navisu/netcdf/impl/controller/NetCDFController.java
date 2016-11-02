/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.impl.controller;

import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.ma2.Array;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class NetCDFController {

    protected Netcdf netcdf;

    protected List<Variable> variables;

    protected Array u = null;
    protected Array v = null;
    protected Array latitudes = null;
    protected Array longitudes = null;
    protected Array height = null;
    protected double[] values;
    protected double[] directions;
    protected double[] latTab;
    protected double[] lonTab;
    protected Array time = null;
    protected Array reftime = null;
    private static final Logger LOGGER = Logger.getLogger(NetCDFController.class.getName());

    public NetCDFController(String fileName) {
        netcdf = new Netcdf(fileName);
        variables = netcdf.getVariables();
        try {
            u = netcdf.read("u-component_of_wind_height_above_ground");
        } catch (Exception e) {
            try {
                u = netcdf.read("u-component_of_wind_surface");
            } catch (Exception e1) {
                LOGGER.log(Level.SEVERE, "File not NetCDF compliant u ", e1);
            }
        }
        try {
            v = netcdf.read("v-component_of_wind_height_above_ground");
        } catch (Exception e) {
            try {
                v = netcdf.read("v-component_of_wind_surface");
            } catch (Exception e1) {
                LOGGER.log(Level.SEVERE, "File not NetCDF compliant v ", e1);
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

    }
}
