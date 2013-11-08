package bzh.terrevirtuelle.navisu.core.model.geom.location.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.location.Location;
import bzh.terrevirtuelle.navisu.core.model.geom.location.ReadWriteLocation;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class ReadWriteLocationImpl extends LocationImpl implements ReadWriteLocation {

    public ReadWriteLocationImpl() {
        this.latitude = 0d;
        this.longitude = 0d;
    }

    public ReadWriteLocationImpl(double latitude, double longitude) {
        super(latitude, longitude);
    }

    @Override
    public void setLatitudeDegree(double latitude) {

        if(latitude < Location.MIN_LAT || latitude > Location.MAX_LAT) {
            throw new IllegalArgumentException("Latitude value must be between " + Location.MIN_LAT + " to " + Location.MAX_LAT + "degrees");
        }

        this.latitude = latitude;
    }

    @Override
    public void setLongitudeDegree(double longitude) {

        if(longitude < Location.MIN_LON || longitude > Location.MAX_LON) {
            throw new IllegalArgumentException("Longitude value must be between " + Location.MIN_LON + " to " + Location.MAX_LON + "degrees");
        }

        this.longitude = longitude;
    }

    @Override
    public void setLatitudeLongitudeDegree(double latitude, double longitude) {
        this.setLatitudeDegree(latitude);
        this.setLongitudeDegree(longitude);
    }
}
