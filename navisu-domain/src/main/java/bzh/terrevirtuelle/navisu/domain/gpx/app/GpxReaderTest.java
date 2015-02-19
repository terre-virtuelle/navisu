/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.app;

import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Route;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Track;
import bzh.terrevirtuelle.navisu.domain.gpx.model.TrackSegment;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Waypoint;
import bzh.terrevirtuelle.navisu.domain.gpx.controller.xml.Reader;
import java.util.List;

/**
 *
 * @author Serge
 */
public class GpxReaderTest {

    public GpxReaderTest() {
        Reader reader = new Reader(Gpx.class);
        Gpx gpx = (Gpx) reader.read("data/track.gpx");
        List<Track> tracks = gpx.getTrk();
        tracks.stream().map((t) -> t.getTrkseg()).forEach((trksegs) -> {
            trksegs.stream().map((trkseg) -> trkseg.getTrkpt()).forEach((trkpt) -> {
                trkpt.stream().forEach((wpt) -> {
                    System.out.println(wpt.getLatitude() + " "
                            + wpt.getLongitude() + " "
                            + wpt.getCourse() + " "
                            + wpt.getSpeed());
                });
            });
        });
        // System.out.println(gpx);
    }

    public static void main(String[] args) {
        new GpxReaderTest();
    }
}
