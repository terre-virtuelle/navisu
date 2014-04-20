/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.domain.devices.Transceiver;
import bzh.terrevirtuelle.navisu.core.model.tobject.TOrientedObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.locators.view.GTransceiver;
import java.util.Calendar;

/**
 *
 * @author Serge
 */
public class TTransceiver
        extends Transceiver
        implements TOrientedObject {

    protected final int id;
    private final int shapeId = 1;
    private GTransceiver gTransceiver;

    public TTransceiver(int id) {
        this.id = id;
    }

    public TTransceiver(int id, int mmsi, double latitude, double longitude) {
        super(mmsi, latitude, longitude);
        this.id = id;
    }

    public TTransceiver(int id, int mmsi, double latitude, double longitude, Calendar date) {
        super(mmsi, latitude, longitude, date);
        this.id = id;
    }

    public TTransceiver(int id, int mmsi, double latitude, double longitude, Calendar date, int epfd) {
        super(mmsi, latitude, longitude, date, epfd);
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public GTransceiver getGTransceiver() {
        return gTransceiver;
    }

    public void setGTransceiver(GTransceiver gTransceiver) {
        this.gTransceiver = gTransceiver;
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

    public int getShapeId() {
        return shapeId;
    }

    @Override
    public Object getClone() {
        return this;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public void setOrientation(Orientation orientation) {
    }

   
}
