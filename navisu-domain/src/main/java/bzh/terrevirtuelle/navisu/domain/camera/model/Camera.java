/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.camera.model;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 * @date Jan 14, 2016
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camera", propOrder = {
    "latitude",
    "longitude",
    "heading",
    "pitch",
    "fieldOfView",
    "nearClipDistance"
})
@XmlRootElement
public class Camera
        implements NavigationData{

    private double heading;
    private double latitude;
    private double longitude;
    private double fieldOfView = 0.78;//45 deg
    private double nearClipDistance = 4000.0;
    private double pitch = 0.0;

    /**
     *
     */
    public Camera() {
    }

    /**
     *
     * @param latitude
     * @param longitude
     */
    public Camera(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param heading
     */
    public Camera(double latitude, double longitude, double heading) {
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param heading
     * @param pitch
     * @param fieldOfView
     * @param nearClipDistance
     */
    public Camera(double latitude, double longitude,
            double heading, double pitch,
            double fieldOfView, double nearClipDistance) {
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fieldOfView = fieldOfView;
        this.nearClipDistance = nearClipDistance;
        this.pitch = pitch;
    }

    /**
     * Get the value of pitch
     *
     * @return the value of pitch
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Set the value of pitch
     *
     * @param pitch new value of pitch
     */
    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    /**
     * Get the value of nearClipDistance
     *
     * @return the value of nearClipDistance
     */
    public double getNearClipDistance() {
        return nearClipDistance;
    }

    /**
     * Set the value of nearClipDistance
     *
     * @param nearClipDistance new value of nearClipDistance
     */
    public void setNearClipDistance(double nearClipDistance) {
        this.nearClipDistance = nearClipDistance;
    }

    /**
     * Get the value of fieldOfView
     *
     * @return the value of fieldOfView
     */
    public double getFieldOfView() {
        return fieldOfView;
    }

    /**
     * Set the value of fieldOfView
     *
     * @param fieldOfView new value of fieldOfView
     */
    public void setFieldOfView(double fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of heading
     *
     * @return the value of heading
     */
    public double getHeading() {
        return heading;
    }

    /**
     * Set the value of heading
     *
     * @param heading new value of heading
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     *
     * @return
     */
    @Override
    public String getGeometry() {
        String geom = "POINT(";
        geom += longitude + " " + latitude + ")";
        return geom;
    }

    @Override
    public String toString() {
        return "Camera{" + " latitude=" + latitude + ", longitude=" + longitude
                + "heading=" + heading + ", pitch=" + pitch
                + ", fieldOfView=" + fieldOfView + ", nearClipDistance=" + nearClipDistance 
                + '}';
    }

}
