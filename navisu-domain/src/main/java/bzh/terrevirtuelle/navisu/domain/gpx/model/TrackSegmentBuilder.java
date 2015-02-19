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
public class TrackSegmentBuilder {

    protected List<Waypoint> trackpoints = new ArrayList<>();
    protected Extensions extensions = new Extensions();

    private TrackSegmentBuilder() {
    }

    public static TrackSegmentBuilder create() {
        return new TrackSegmentBuilder();
    }

    public TrackSegmentBuilder trackpoints(List<Waypoint> trackpoints) {
        this.trackpoints = trackpoints;
        return this;
    }

    public TrackSegmentBuilder extensions(Extensions extensions) {
        this.extensions = extensions;
        return this;
    }

    public TrackSegment build() {
        return new TrackSegment(trackpoints, extensions);
    }
}
