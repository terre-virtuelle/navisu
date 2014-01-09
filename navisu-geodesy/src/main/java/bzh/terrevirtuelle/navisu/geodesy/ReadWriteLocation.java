package bzh.terrevirtuelle.navisu.geodesy;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface ReadWriteLocation extends Location {

    void setLatitudeDegree(double latitude);
    void setLongitudeDegree(double longitude);

    void setLatitudeLongitudeDegree(double latitude, double longitude);
}
