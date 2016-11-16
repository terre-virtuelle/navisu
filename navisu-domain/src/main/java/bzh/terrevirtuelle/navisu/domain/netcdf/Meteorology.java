/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author serge
 * @date Sep 6, 2016
 */
public class Meteorology
        extends Netcdf {

    private static final List<String> VARIABLE_NAME_LIST = Arrays.asList(
            "Pressure_reduced_to_MSL_msl",
            "Pressure_surface",
             "Pressure_height_above_ground",
            "Relative_humidity_height_above_ground",
            "Snow_depth_surface",
            "Surface_wind_gust_surface",
            "Temperature_height_above_ground",
            "Total_cloud_cover_entire_atmosphere",
            "Total_cloud_cover_unknownLevel10_Mixed_intervals_Average",
            "Total_precipitation_surface_3_Hour_Accumulation",
            "Total_precipitation_surface_Mixed_intervals_Accumulation"
    );

    public Meteorology(String fileName) {
        super(fileName);
        variableNameList = VARIABLE_NAME_LIST;
    }

    public List<String> getVARIABLE_NAME_LIST() {
        return VARIABLE_NAME_LIST;
    }

    static public boolean isValid(String name) {
        boolean result = false;
        for (String s : VARIABLE_NAME_LIST) {
            if (s.equalsIgnoreCase(name)) {
                result = true;
            }
        }
        return result;
    }
}
