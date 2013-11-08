package bzh.terrevirtuelle.navisu.core.model.geom.location.impl;


import bzh.terrevirtuelle.navisu.core.model.geom.location.AbsoluteLocation;
import bzh.terrevirtuelle.navisu.core.model.geom.location.Location;
import bzh.terrevirtuelle.navisu.core.model.geom.location.LocationFactory;


/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class LocationFactoryImpl implements LocationFactory {

    @Override
    public Location newLocation() {
        return new ReadWriteLocationImpl();
    }

    @Override
    public Location newLocation(double latitude, double longitude) {
        return new ReadWriteLocationImpl(latitude, longitude);
    }

    @Override
    public AbsoluteLocation newAbsoluteLocation(double latitude, double longitude, double altitude) {
        return new ReadWriteAbsoluteLocationImpl(latitude, longitude, altitude);
    }
}
