package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.core.model.tobject.TOrientedObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.ship.Ship;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:19
 */
public class OShip
        extends Ship
        implements TOrientedObject {
    
    protected final int id;
    
    public OShip(int id) {
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
      //  System.out.println("this.getHeading() " + this.getCog());
        return Orientation.factory.newOrientation(this.getCog()/10);
    }
    
    @Override
    public void setOrientation(Orientation orientation) {
        this.setCog((float) orientation.getOrientationDegree());
    }
    
    @Override
    public Object getClone() {
        
        OShip clone = new OShip(this.id);
        clone.setLocation(this.getLocation());
        clone.setOrientation(this.getOrientation());
        return clone;
    }
}
