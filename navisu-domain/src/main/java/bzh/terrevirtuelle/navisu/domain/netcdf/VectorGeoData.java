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
public class VectorGeoData
        extends GeoData {

    private double angle = 0;

    public VectorGeoData(double latitude, double longitude) {
        super(latitude, longitude);
    }

    public VectorGeoData(double latitude, double longitude, double uComponent, double vComponent) {
        super(latitude, longitude);
        setData(vectorModule(uComponent, vComponent));
        this.angle = angleCalculation(uComponent, vComponent);
    }


    /**
     * Get the value of angle
     *
     * @return the value of angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set the value of angle
     *
     * @param angle new value of angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Get the value of speed
     *
     * @return the value of vector
     */
    public double getVector() {
        return getData();
    }

    /**
     * Set the value of speed
     *
     * @param vectorModule new value of vectorModule
     */
    public void setVector(double vectorModule) {
        setData(vectorModule);
    }

    private double vectorModule(double uValue, double vValue) {
        return Math.sqrt((uValue * uValue) + (vValue * vValue));
    }

    private double angleCalculation(double uValue, double vValue) {
        double tmp = Math.atan2(vValue, uValue);

        if ((uValue >= 0 && vValue >= 0) || (uValue >= 0 && vValue < 0) || (uValue < 0 && vValue < 0)) {
            tmp = Math.PI / 2 - tmp;
        }
        if ((uValue < 0 && vValue > 0)) {
            tmp = Math.PI / 2 - tmp + 2 * Math.PI;
        }
        if ((uValue < 0 && vValue == 0)) {
            tmp = Math.PI / 2 + tmp;
        }
        return tmp;
    }

}
