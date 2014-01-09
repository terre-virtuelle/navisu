package bzh.terrevirtuelle.navisu.geodesy;

import bzh.terrevirtuelle.navisu.geodesy.impl.LocationFactoryImpl;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface Location {

    public static final LocationFactory factory = new LocationFactoryImpl();

    public static final double MAX_LAT = 90d;
    public static final double MIN_LAT = -90d;

    public static final double MAX_LON = 180d;
    public static final double MIN_LON = -180d;

    double getLatitudeDegree();
    double getLongitudeDegree();

    //TODO add getter for latitude/longitude in radian
}
