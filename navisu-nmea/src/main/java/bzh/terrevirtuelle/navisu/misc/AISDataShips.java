/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.misc;


import bzh.terrevirtuelle.navisu.nmea.model.AIS135;
import java.util.Map;

/**
 *
 * @author Morvan
 */
public class AISDataShips {

    private Map<Integer, AIS135> data;

    public AISDataShips(Map<Integer, AIS135> data) {
        this.data = data;
    }

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public Map<Integer, AIS135> getData() {
        return data;
    }

    /**
     * Set the value of data
     *
     * @param data new value of data
     */
    public void setData(Map<Integer, AIS135> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }



}
