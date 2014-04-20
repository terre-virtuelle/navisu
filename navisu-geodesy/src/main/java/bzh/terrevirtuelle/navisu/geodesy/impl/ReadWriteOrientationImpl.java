package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.geodesy.ReadWriteOrientation;

/**
 *
 * @author Thibault Pensec thibault.pensec at gmail.com
 * @author Jordan Mens jordan.mens at gmail.com
 */
public class ReadWriteOrientationImpl
        extends OrientationImpl
        implements ReadWriteOrientation {

    public ReadWriteOrientationImpl() {
        this.orientation = 0d;
    }

    public ReadWriteOrientationImpl(double orientation) {
        super(orientation);
    }

    @Override
    public void setOrientationDegree(double orientation) {
        if (orientation < Orientation.MIN_ORIENTATION || orientation > Orientation.MAX_ORIENTATION) {
            throw new IllegalArgumentException("Orientation value must be between "
                    + Orientation.MIN_ORIENTATION + " to "
                    + Orientation.MAX_ORIENTATION + "degrees");
        }
        this.orientation = orientation;
    }
}
