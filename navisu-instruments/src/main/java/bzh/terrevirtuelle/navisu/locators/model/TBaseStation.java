package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.buoys.BaseStation;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;

/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:19
 */
public class TBaseStation
        extends BaseStation
        implements TObject {

    protected final int id;

    private int shapeId = 1;

    public TBaseStation(int id) {
        this.id = id;
    }

    public TBaseStation(int id, int mmsi, double latitude, double longitude) {
        super(mmsi, latitude, longitude);
        this.id = id;
    }

    public TBaseStation(int id, int mmsi, double latitude, double longitude, Calendar date, int epfd) {
        super(mmsi, latitude, longitude, date, epfd);
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

    /**
     * Get the value of shapeId
     *
     * @return the value of shapeId
     */
    public int getShapeId() {
        return shapeId;
    }

    /**
     * Set the value of shapeId
     *
     * @param shapeId new value of shapeId
     */
    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    @Override
    public Object getClone() {
        TBaseStation clone = new TBaseStation(this.id);
        clone.setLocation(this.getLocation());
        // TODO à completer 
        // return clone avec constructeur complet
        return this;
    }

    @Override
    public String toString() {
        return "TBaseStation{" + "id=" + id + ", shapeId=" + shapeId + "BaseStation : "  +super.toString() +  '}';
    }
}
