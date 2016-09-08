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
public class VField 
        extends Field {

    VectorGeoData[][] data;

    public VField(int length, int width, double height) {
        super(height);
        data = new VectorGeoData[length][width];
    }

    @Override
    public String toString() {
        return "VField{" + "data=" + data + '}';
    }

}
