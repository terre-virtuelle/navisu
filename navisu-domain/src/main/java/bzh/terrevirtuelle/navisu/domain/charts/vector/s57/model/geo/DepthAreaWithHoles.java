/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class DepthAreaWithHoles
        extends DepthArea {

    private List<String> geoms;

    /**
     *
     * @param geoms
     * @param id
     */
    public DepthAreaWithHoles(List<String> geoms, Long id) {
        super(id);
        this.geoms = geoms;
    }

    /**
     *
     * @param geoms
     */
    public DepthAreaWithHoles(List<String> geoms) {
        this.geoms = geoms;
    }

    /**
     *
     * @param id
     */
    public DepthAreaWithHoles(Long id) {
        super(id);
        geoms = new ArrayList<>();
    }

    /**
     *
     */
    public DepthAreaWithHoles() {
         geoms = new ArrayList<>();
    }

    /**
     * Get the value of geoms
     *
     * @return the value of geoms
     */
    public List<String> getGeoms() {
        return geoms;
    }

    /**
     * Set the value of geoms
     *
     * @param geoms new value of geoms
     */
    public void setGeoms(List<String> geoms) {
        this.geoms = geoms;
    }

    @Override
    public String toString() {
        return "DepthAreaWithHoles{" + "geom =" + geom + "geoms=" + geoms + '}';
    }

}
