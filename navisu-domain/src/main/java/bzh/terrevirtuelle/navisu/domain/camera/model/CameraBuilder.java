/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.camera.model;

/**
 *
 * @author serge
 * @date Jan 14, 2016
 *
 */
public class CameraBuilder {

    private double latitude = 0.0;
    private double longitude = 0.0;
    private double heading = 0;
    private double pitch = 0.0;
    private double fieldOfView = 0.78;
    private double nearClipDistance = 4000.0;

    private CameraBuilder() {
    }

    /**
     *
     * @return
     */
    public static CameraBuilder create() {
        return new CameraBuilder();
    }

    /**
     *
     * @return
     */
    public Camera build() {
        return new Camera(latitude, longitude,
                heading, pitch,
                fieldOfView, nearClipDistance);
    }

    /**
     *
     * @param latitude
     * @return
     */
    public CameraBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     *
     * @param longitude
     * @return
     */
    public CameraBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     *
     * @param heading
     * @return
     */
    public CameraBuilder heading(double heading) {
        this.heading = heading;
        return this;
    }

    /**
     *
     * @param pitch
     * @return
     */
    public CameraBuilder pitch(double pitch) {
        this.pitch = pitch;
        return this;
    }

    /**
     *
     * @param fieldOfView
     * @return
     */
    public CameraBuilder fieldOfView(double fieldOfView) {
        this.fieldOfView = fieldOfView;
        return this;
    }

    /**
     *
     * @param nearClipDistance
     * @return
     */
    public CameraBuilder nearClipDistance(double nearClipDistance) {
        this.nearClipDistance = nearClipDistance;
        return this;
    }
}
