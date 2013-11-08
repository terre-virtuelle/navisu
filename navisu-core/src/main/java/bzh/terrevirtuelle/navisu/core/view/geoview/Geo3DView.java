package bzh.terrevirtuelle.navisu.core.view.geoview;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface Geo3DView extends GeoView {

    void   setPitch(double pitch);
    double getPitch();

    void   setRoll(double roll);
    double getRoll();

    void   setHeading(double heading);
    double getHeading();
}
