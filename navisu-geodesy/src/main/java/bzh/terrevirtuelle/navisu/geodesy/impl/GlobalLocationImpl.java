package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.GlobalLocation;

/**
 *
 * @author Jordan Mens jordan.mens at gmail.com
 * @author Thibault Pensec thibault.pensec at gmail.com
 */
public class GlobalLocationImpl extends LocationImpl implements GlobalLocation {

    protected double altitude;

    public GlobalLocationImpl() {
        super();
        this.altitude = 0;
    }

    public GlobalLocationImpl(double latitude, double longitude, double altitude) {
        super(latitude, longitude);
        this.altitude = altitude;
    }

    @Override
    public double getAltitude() {
        return this.altitude;
    }
}
