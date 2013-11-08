package bzh.terrevirtuelle.navisu.core.model.geom.location.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.location.ReadWriteAbsoluteLocation;

/**
 *
 * @author Jordan Mens <jordan.mens at gmail.com>
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 */
public class ReadWriteAbsoluteLocationImpl extends AbsoluteLocationImpl implements ReadWriteAbsoluteLocation {

    protected double altitude;

    public ReadWriteAbsoluteLocationImpl(double latitude, double longitude, double altitude) {
        super(latitude, longitude, altitude);
    }

    public ReadWriteAbsoluteLocationImpl() {
        this.altitude = 0d;
    }

    @Override
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
