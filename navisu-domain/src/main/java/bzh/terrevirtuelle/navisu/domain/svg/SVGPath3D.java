/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.svg;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import com.vividsolutions.jts.geom.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author serge
 * @date Jun 19, 2019
 */
public class SVGPath3D
        extends SVGPath {

    private List<SVGPath3D> svgOnTopList;
    private List<Buoyage> buoyageList;
    private Geometry geometry;
    private double height;
    private Map<String, Object> values;

    public SVGPath3D() {
        this(0.0);
    }

    public SVGPath3D(double height) {
        values = new HashMap<>();
        svgOnTopList = new ArrayList<>();
        buoyageList = new ArrayList<>();
        this.height = height;
    }

    /**
     * Get the value of values
     *
     * @return the value of values
     */
    public Map<String, Object> getValues() {
        return values;
    }

    /**
     * Set the value of values
     *
     * @param values new value of values
     */
    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public void addBuoyage(Buoyage buoyage) {
        buoyageList.add(buoyage);
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    public List<SVGPath3D> getSvgOnTopList() {
        return svgOnTopList;
    }

    public void setSvgOnTopList(List<SVGPath3D> svgOnTopList) {
        this.svgOnTopList = svgOnTopList;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Buoyage> getBuoyageList() {
        return buoyageList;
    }

    public void setBuoyageList(List<Buoyage> buoyageList) {
        this.buoyageList = buoyageList;
    }

    @Override
    public String toString() {
        return "SVGPath3D{" 
                + super.toString()
                + "svgOnTopList=" + svgOnTopList + ", buoyageList=" + buoyageList + ", geometry=" + geometry + ", height=" + height + ", values=" + values + '}';
    }

}
