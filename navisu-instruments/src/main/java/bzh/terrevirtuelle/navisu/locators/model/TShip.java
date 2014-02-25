package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.ship.Ship;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:19
 */
public class TShip
        extends Ship
        implements TObject {

    protected final int id;

    public TShip(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Location getLocation() {
        return Location.factory.newLocation(this.getLatitude(), this.getLongitude());
    }

    @Override
    public void setLocation(Location location) {
        this.setLatitude(location.getLatitudeDegree());
        this.setLongitude(location.getLongitudeDegree());
    }

    @Override
    public Object getClone() {

        TShip clone = new TShip(this.id);
        clone.setLocation(this.getLocation());

        return clone;
    }
}
