package bzh.terrevirtuelle.navisu.core.model.geom.location;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface LocationFactory {

    Location newLocation();

    Location newLocation(double lat, double lon);

    AbsoluteLocation newAbsoluteLocation(double latitude, double longitude, double altitude);
}
