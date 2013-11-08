package bzh.terrevirtuelle.navisu.core.model.geom.point.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.point.Point;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class PointImpl implements Point {

    protected int x, y;

    public PointImpl() {
        this.x = 0;
        this.y = 0;
    }

    public PointImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
