package bzh.terrevirtuelle.navisu.geodesy;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface OrientationFactory {

    Orientation newOrientation();

    Orientation newOrientation(double orientation);

}
