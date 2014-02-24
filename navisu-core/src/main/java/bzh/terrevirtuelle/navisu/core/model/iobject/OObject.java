/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.model.iobject;

import bzh.terrevirtuelle.navisu.core.util.ICloneable;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface OObject extends ICloneable {

    int getID();

    Orientation getOrientation();

    void setOrientation(Orientation orientation);

    public static OObject newBasicOObject(final int id, final double orientation) {
        return new OObject() {

            Orientation angle = Orientation.factory.newOrientation(orientation);

            @Override
            public int getID() {
                return id;
            }

            @Override
            public Orientation getOrientation() {
                return angle;
            }

            @Override
            public void setOrientation(Orientation orientation) {
                angle = orientation;
            }

            @Override
            public Object getClone() {
                return OObject.newBasicOObject(id, angle.getOrientationDegree());
            }
        };
    }
}
