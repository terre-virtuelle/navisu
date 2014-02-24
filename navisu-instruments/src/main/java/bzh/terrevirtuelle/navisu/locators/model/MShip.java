package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.core.model.iobject.OObject;
import bzh.terrevirtuelle.navisu.core.model.iobject.TObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.ship.Ship;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:19
 */
public class MShip
        extends Ship
        implements TObject, OObject {
    
    protected final int id;
    
    public MShip(int id) {
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
    public Orientation getOrientation() {
        return Orientation.factory.newOrientation(this.getHeading());
    }
    
    @Override
    public void setOrientation(Orientation orientation) {
        this.setHeading((float) orientation.getOrientationDegree());
    }
    
    @Override
    public Object getClone() {
        
        MShip clone = new MShip(this.id);
        clone.setLocation(this.getLocation());
        clone.setOrientation(this.getOrientation());
        return clone;
    }
}
