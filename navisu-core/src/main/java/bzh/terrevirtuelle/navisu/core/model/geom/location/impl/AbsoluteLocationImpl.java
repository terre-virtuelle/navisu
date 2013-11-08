package bzh.terrevirtuelle.navisu.core.model.geom.location.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.location.AbsoluteLocation;

/**
 *
 * @author Jordan Mens <jordan.mens at gmail.com>
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 */
public class AbsoluteLocationImpl extends LocationImpl implements AbsoluteLocation {

    protected double altitude;

    public AbsoluteLocationImpl() {
        super();
        this.altitude = 0;
    }

    public AbsoluteLocationImpl(double latitude, double longitude, double altitude) {
        super(latitude, longitude);
        this.altitude = altitude;
    }

    @Override
    public double getAltitude() {
        return this.altitude;
    }
}
