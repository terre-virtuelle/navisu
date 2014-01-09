package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.ReadWriteGlobalLocation;

/**
 *
 * @author Jordan Mens <jordan.mens at gmail.com>
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 */
public class ReadWriteGlobalLocationImpl extends GlobalLocationImpl implements ReadWriteGlobalLocation {

    protected double altitude;

    public ReadWriteGlobalLocationImpl(double latitude, double longitude, double altitude) {
        super(latitude, longitude, altitude);
    }

    public ReadWriteGlobalLocationImpl() {
        this.altitude = 0d;
    }

    @Override
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
