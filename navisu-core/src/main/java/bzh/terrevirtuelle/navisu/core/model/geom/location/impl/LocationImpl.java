package bzh.terrevirtuelle.navisu.core.model.geom.location.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.location.Location;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class LocationImpl implements Location {

    protected double latitude;
    protected double longitude;

    public LocationImpl() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public LocationImpl(double latitude, double longitude) {

        if(latitude < Location.MIN_LAT || latitude > Location.MAX_LAT) {
            throw new IllegalArgumentException("Latitude value must be between " + Location.MIN_LAT + " to " + Location.MAX_LAT + "degrees");
        }

        if(longitude < Location.MIN_LON || longitude > Location.MAX_LON) {
            throw new IllegalArgumentException("Longitude value must be between " + Location.MIN_LON + " to " + Location.MAX_LON + "degrees");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double getLatitudeDegree() {
        return this.latitude;
    }

    @Override
    public double getLongitudeDegree() {
        return this.longitude;
    }
}
