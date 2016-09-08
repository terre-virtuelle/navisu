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
public class Wind
        extends VectorGeoData {

    public Wind(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public Wind(double latitude, double longitude, double uComponent, double vComponent) {
        super(latitude, longitude, uComponent, vComponent);
    }

    /**
     * Get the value of speed
     *
     * @return the value of vector
     */
    public double getSpeed() {
        return getData();
    }

    /**
     * Set the value of speed
     *
     * @param speed new value of vector
     */
    public void setSpeed(double speed) {
        setData(speed);
    }

}
