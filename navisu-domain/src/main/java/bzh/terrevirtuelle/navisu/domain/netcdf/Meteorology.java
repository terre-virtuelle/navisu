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
            "Categorical_freezing_rain_yes1_no0_surface_Mixed_intervals_Average",
            "Categorical_snow_yes1_no0_surface_Mixed_intervals_Average",
            "Convective_Available_Potential_Energy_surface",
            "Convective_Available_Potential_Energy_surface",
            "Convective_inhibition_surface",
            "Convective_inhibition_surface",
            "Direction_of_swell_waves_surface",
            "Direction_of_wind_waves_from_which_surface",
            "Geopotential_height_zeroDegC_isotherm",
            "height_above_ground",
            "height_above_ground1",
            "lat",
            "lon",
            "LatLon_Projection",
            "Mean_period_of_swell_waves_surface",
            "Mean_period_of_wind_waves_surface",
            "Pressure_reduced_to_MSL_msl",
            "reftime",
            "Relative_humidity_height_above_ground",
            "Significant_height_of_combined_wind_waves_and_swell_surface",
            "Significant_height_of_swell_waves_surface",
            "Significant_height_of_wind_waves_surface",
            "Snow_depth_surface",
            "Surface_wind_gust_surface",
            "Temperature_height_above_ground",
            "time",
            "time1",
            "time1_bounds",
            "Total_cloud_cover_entire_atmosphere",
            "Total_cloud_cover_unknownLevel10_Mixed_intervals_Average",
            "Total_precipitation_surface_3_Hour_Accumulation",
            "Total_precipitation_surface_Mixed_intervals_Accumulation",
            "u-component_of_current_surface",
            "u-component_of_wind_height_above_ground",
            "unknownlevel10",
            "v-component_of_current_surface",
            "v-component_of_wind_height_above_ground"
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
