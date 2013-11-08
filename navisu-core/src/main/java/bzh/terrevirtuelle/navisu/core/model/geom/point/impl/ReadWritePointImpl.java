package bzh.terrevirtuelle.navisu.core.model.geom.point.impl;

import bzh.terrevirtuelle.navisu.core.model.geom.point.ReadWritePoint;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class ReadWritePointImpl extends PointImpl implements ReadWritePoint {

    public ReadWritePointImpl() {
        super();
    }

    public ReadWritePointImpl(int x, int y) {
        super(x, y);
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
