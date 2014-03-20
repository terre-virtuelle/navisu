package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.core.model.tobject.TOrientedObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.ship.Ship;
import java.util.Calendar;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:19
 */
public class TShip
        extends Ship
        implements TOrientedObject {
    
    protected final int id;
    
    public TShip(int id) {
        this.id = id;
    }

    public TShip(int id, int mmsi, String name, String country, float width, float length, float draught, int shipType, int navigationalStatus, int electronicPositionDevice, String callSign) {
        super(mmsi, name, country, width, length, draught, shipType, navigationalStatus, electronicPositionDevice, callSign);
        this.id = id;
    }

    public TShip(int id, int mmsi, int imo, String name, 
            float heading, float cog, float sog, float rot, 
            float latitude, float longitude, 
            float width, float length, float draught, 
            int type, int navigationalStatus, int electronicPositionDevice, String callSign, 
            Calendar ETA, String destination, String country) {
        super(mmsi, imo, name, heading, cog, sog, rot, latitude, longitude, width, length, draught, type, navigationalStatus, electronicPositionDevice, callSign, ETA, destination, country);
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
        return Orientation.factory.newOrientation(this.getCog());
    }
    
    @Override
    public void setOrientation(Orientation orientation) {
        this.setCog((float) orientation.getOrientationDegree());
    }
    
    @Override
    public Object getClone() {
        TShip clone = new TShip(this.id);
        clone.setLocation(this.getLocation());
        clone.setOrientation(this.getOrientation());
        // TODO à completer 
        // return clone avec constructeur complet
        return this;
    }
}
