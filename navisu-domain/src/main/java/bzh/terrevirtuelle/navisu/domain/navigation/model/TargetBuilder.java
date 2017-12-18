/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.model;

/**
 *
 * @author serge
 * @param <T>
 */
public class TargetBuilder<T extends NavigationData> {

    private T navigationData = null;

    private double latitude = 0.0;

    private double longitude = 0.0;

    private long id = 0;

    private double distance = -1;

    private double azimuth = 511;

    private TargetBuilder() {
    }

    public static TargetBuilder create() {
        return new TargetBuilder();
    }

    @SuppressWarnings("unchecked")
    public Target build() {
        return new Target(navigationData, latitude, longitude, id, distance, azimuth);

    }

    public TargetBuilder navigationData(T navigationData) {
        this.navigationData = navigationData;
        return this;
    }
    public TargetBuilder latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
    public TargetBuilder longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }
    public TargetBuilder id(long id) {
        this.id = id;
        return this;
    }
    public TargetBuilder distance(double distance) {
        this.distance = distance;
        return this;
    }
    public TargetBuilder azimuth(double azimuth) {
        this.azimuth = azimuth;
        return this;
    }
}
