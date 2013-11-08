package bzh.terrevirtuelle.navisu.core.model.geom.point.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.point.Point;
import bzh.terrevirtuelle.navisu.core.model.geom.point.PointFactory;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class PointFactoryImpl implements PointFactory {

    @Override
    public Point newPoint() {
        return new ReadWritePointImpl();
    }

    @Override
    public Point newPoint(int x, int y) {
        return new ReadWritePointImpl(x, y);
    }
}
