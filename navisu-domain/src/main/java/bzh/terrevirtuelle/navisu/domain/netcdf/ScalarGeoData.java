/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf;

/**
 *
 * @author serge
 * @date Sep 8, 2016
 */
public class ScalarGeoData extends GeoData {

    public ScalarGeoData(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public ScalarGeoData(double latitude, double longitude, double data) {
        super(latitude, longitude, data);
    }

}
