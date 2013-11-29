/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.model.datamodel;

import bzh.terrevirtuelle.navisu.core.model.geom.location.Location;
import bzh.terrevirtuelle.navisu.core.util.ICloneable;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface TObject extends ICloneable {

    int getID();

    Location getLocation();
    void     setLocation(Location location);
}
