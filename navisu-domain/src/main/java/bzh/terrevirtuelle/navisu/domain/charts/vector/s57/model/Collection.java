package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;
import java.util.*;


public class Collection
        extends Feature 
implements Serializable {

    /**
     *
     */

    protected List<Geo> geos;

    public Collection() {
        this.geos = new ArrayList<>();
    }

    public Geo getGeos(int i) {
        return (Geo) this.geos.get(i);
    }

    public int cardGeos() {
        return this.geos.size();
    }

    @Override
    public String toString() {
        return "Collection{" + "geos=" + geos + '}';
    }

}
