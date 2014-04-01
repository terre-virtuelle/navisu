/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.model;

import bzh.terrevirtuelle.navisu.station.Station;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.geodesy.Location;
import java.util.Calendar;

/**
 *
 * @author Serge
 */
public class TStation
        extends Station
        implements TObject {

    protected final int id;

    public TStation(int id) {
        this.id = id;
    }

    public TStation(int id, int mmsi, double latitude, double longitude) {
        super(mmsi, latitude, longitude);
        this.id = id;
    }

    public TStation(int id, int mmsi, double latitude, double longitude, Calendar date, int epfd) {
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

    @Override
    public Object getClone() {
        TStation clone = new TStation(this.id);
        clone.setLocation(this.getLocation());
        // TODO à completer 
        // return clone avec constructeur complet
        return this;
    }

}
