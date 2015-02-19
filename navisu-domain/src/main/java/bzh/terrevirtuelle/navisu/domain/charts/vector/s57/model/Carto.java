package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.util.*;

public class Carto
        extends Feature {

    public List<Geo> geos = new ArrayList<>();

    public Geo getGeos(int i) {
        return (Geo) this.geos.get(i);
    }

    public int cardGeos() {
        return this.geos.size();
    }

    @Override
    public String toString() {
        return "Carto{" + "geos=" + geos + '}';
    }

}
