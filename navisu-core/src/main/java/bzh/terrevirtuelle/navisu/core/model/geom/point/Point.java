package bzh.terrevirtuelle.navisu.core.model.geom.point;

import bzh.terrevirtuelle.navisu.core.model.geom.point.impl.PointFactoryImpl;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface Point {

    public static final PointFactory factory = new PointFactoryImpl();

    int getX();
    int getY();
}
