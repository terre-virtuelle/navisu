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
public class Oceanography
        extends Netcdf {

    private final List<String> VARIABLE_NAME_LIST = Arrays.asList(
            "LatLon_Projection",
            "lat",
            "lon",
            "reftime",
            "time",
            "u-component_of_current_surface",
            "v-component_of_current_surface"
    );

    public Oceanography(String fileName) {
        super(fileName);
        variableNameList = VARIABLE_NAME_LIST;
    }

}
