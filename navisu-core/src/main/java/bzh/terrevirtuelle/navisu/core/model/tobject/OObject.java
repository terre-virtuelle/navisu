/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.model.tobject;

import bzh.terrevirtuelle.navisu.core.util.ICloneable;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface OObject extends TObject {

    Orientation getOrientation();

    void setOrientation(Orientation orientation);

    public static OObject newBasicOObject(final int id,
            final double lat, final double lon,
            final double orientation) {
        return new OObject() {
            
            Location loc = Location.factory.newLocation(lat, lon);
            Orientation angle = Orientation.factory.newOrientation(orientation);

            @Override
            public int getID() {
                return id;
            }

            @Override
            public Location getLocation() {
                return loc;
            }

            @Override
            public void setLocation(Location location) {
                loc = location;
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
                return OObject.newBasicOObject(id,
                        loc.getLatitudeDegree(), loc.getLongitudeDegree(),
                        angle.getOrientationDegree());
            }
        };
    }
}
