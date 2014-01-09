package bzh.terrevirtuelle.navisu.geodesy.impl;


import bzh.terrevirtuelle.navisu.geodesy.GlobalLocation;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.LocationFactory;


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
    public GlobalLocation newAbsoluteLocation(double latitude, double longitude, double altitude) {
        return new ReadWriteGlobalLocationImpl(latitude, longitude, altitude);
    }
}
