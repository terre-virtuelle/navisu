/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.render.Polygon;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Mar 4, 2017
 */
public class ElevationLoader {

    protected Polygon polygon;
    protected WorldWindow wwd;
    protected String outFilename;
    protected ElevationModel model;
    protected String elevations = "";
    double latRangeMetric;
    double lonRangeMetric;
    double SQUARE_SIDE = 200;
    int PTS_COUNT = 200;

    public ElevationLoader(Polygon polygon, String outFilename) {
        this.polygon = polygon;
        this.outFilename = outFilename;
        wwd = GeoWorldWindViewImpl.getWW();
        model = this.wwd.getModel().getGlobe().getElevationModel();
    }

    public void compute() {
        List<? extends Position> positions = polygon.getBoundaries().get(0);
        double latRange = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / PTS_COUNT;
        double lonRange = (positions.get(0).getLongitude().getDegrees() - positions.get(1).getLongitude().getDegrees()) / PTS_COUNT;

        latRange = Math.abs(latRange);
        lonRange = Math.abs(lonRange);

        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees() + latRange),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees() + lonRange), 100));

        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees() + latRange),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees() + lonRange), 100));

        for (double lon = positions.get(1).getLongitude().getDegrees();
                lon > positions.get(0).getLongitude().getDegrees();
                lon -= lonRange) {
            for (double lat = positions.get(3).getLatitude().getDegrees();
                    lat > positions.get(0).getLatitude().getDegrees();
                    lat -= latRange) {
                double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                el /= 10;
                elevations += el + " ";
            }
        }
        write();
    }

    private void write() {
        double space = SQUARE_SIDE / (PTS_COUNT - 1);
        String txt
                = "<Transform rotation='0 1 0 1.57058'> \n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material/>\n"
                + "</Appearance>\n"
                + "<ElevationGrid \n"
                + "ccw='true' solid='false'"
                + " xDimension='" + PTS_COUNT + "'"
                + " xSpacing='" + space + "'"
                + " zDimension='" + PTS_COUNT + "'"
                + " zSpacing='" + space + "'"
                + " height='" + elevations + "'/>\n"
                + "</Shape> \n"
                + "</Transform> \n";
        try {
            Files.write(Paths.get(outFilename), txt.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
