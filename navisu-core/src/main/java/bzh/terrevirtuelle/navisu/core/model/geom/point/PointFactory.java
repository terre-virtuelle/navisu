package bzh.terrevirtuelle.navisu.core.model.geom.point;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface PointFactory {

    Point newPoint();

    Point newPoint(int x, int y);
}
