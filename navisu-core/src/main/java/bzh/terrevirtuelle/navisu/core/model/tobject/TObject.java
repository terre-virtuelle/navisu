/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.model.tobject;

import bzh.terrevirtuelle.navisu.core.util.ICloneable;
import bzh.terrevirtuelle.navisu.geodesy.Location;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface TObject extends ICloneable {

    int getID();

    Location getLocation();
    void     setLocation(Location location);

    public static TObject newBasicTObject(final int id, final double lat, final double lon) {
        return new TObject() {

            Location loc = Location.factory.newLocation(lat, lon);

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
            public Object getClone() {
                return TObject.newBasicTObject(id, loc.getLatitudeDegree(), loc.getLongitudeDegree());
            }
        };
    }
}
