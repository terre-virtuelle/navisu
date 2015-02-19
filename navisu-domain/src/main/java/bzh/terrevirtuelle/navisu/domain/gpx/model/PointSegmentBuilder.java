/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.gpx.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge
 */
public class PointSegmentBuilder {
        protected List<Point> points = new ArrayList<>();

    private PointSegmentBuilder() {
    }
     public static PointSegmentBuilder create() {
        return new PointSegmentBuilder();
    }

    public PointSegment build() {
        return new PointSegment(points);
    }   

    public PointSegmentBuilder points(List<Point> pt) {
        this.points = pt;
        return this;
    }
    
}
