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
import java.util.ArrayList;
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

    public ElevationLoader(Polygon polygon, String outFilename) {
        this.polygon = polygon;
        this.outFilename = outFilename;
        wwd = GeoWorldWindViewImpl.getWW();
        model = this.wwd.getModel().getGlobe().getElevationModel();
    }

    public void compute() {
        //  System.out.println("polygon : " + polygon.getBoundaries());
        List<? extends Position> positions = polygon.getBoundaries().get(0);
        double latRange = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / 200;
        double lonRange = (positions.get(0).getLongitude().getDegrees() - positions.get(1).getLongitude().getDegrees()) / 200;
        //    System.out.println("polygon.getBoundaries().get(0) : " + polygon.getBoundaries().get(0));
        //   System.out.println("0 : " + positions.get(0).getLatitude().getDegrees() + " " + positions.get(0).getLongitude().getDegrees());
        //  System.out.println("1 : " + positions.get(1).getLatitude().getDegrees() + " " + positions.get(1).getLongitude().getDegrees());
        //  System.out.println("2 : " + positions.get(2).getLatitude().getDegrees() + " " + positions.get(2).getLongitude().getDegrees());
        //  System.out.println("3 : " + positions.get(3).getLatitude().getDegrees() + " " + positions.get(3).getLongitude().getDegrees());
        //  System.out.println("latRange : " + latRange);
        //  System.out.println("lonRange : " + lonRange);
        latRange = Math.abs(latRange);
        lonRange = Math.abs(lonRange);

        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees() + latRange),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees() + lonRange), 100));

        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees() + latRange),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees() + lonRange), 100));

        List<Double> l = new ArrayList<>();
        /*
        for (double lon = positions.get(0).getLongitude().getDegrees();
                lon < positions.get(1).getLongitude().getDegrees();
                lon += lonRange) {
            for (double lat = positions.get(0).getLatitude().getDegrees();
                    lat < positions.get(3).getLatitude().getDegrees();
                    lat += latRange) {
                double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                l.add(el);
                el=-el;
                el*=10;
                elevations += el + " ";
            }
        }
         */
        for (double lon = positions.get(1).getLongitude().getDegrees();
                lon > positions.get(0).getLongitude().getDegrees();
                lon -= lonRange) {
            for (double lat = positions.get(3).getLatitude().getDegrees();
                    lat > positions.get(0).getLatitude().getDegrees();
                    lat -= latRange) {
                double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                el *= 2;
                elevations += el + " ";
            }
        }

        //    System.out.println("latRangeMetric : " + latRangeMetric);
        //    System.out.println("lonRangeMetric : " + lonRangeMetric);
      //  latRangeMetric *= 5;
      //  lonRangeMetric *= 5;
        /*
        System.out.println("latRangeMetric : " +latRangeMetric
        +" lonRangeMetric : " + lonRangeMetric + " " + 200/latRangeMetric 
                +" " + 200/lonRangeMetric +"  " + l.size());
       // latRangeMetric : 84.86264308357366 
       // lonRangeMetric : 84.73739519670914 
       //                 2.3567495983248814 
       //                 2.3602330415717945 
       */
        write();
    }

    private void write() {
        String txt
                = //"<Transform rotation='0 0 1 3.14'> \n"
                 "<Transform rotation='0 1 0 1.57058'> \n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material/>\n"
                + "</Appearance>\n"
                + "<ElevationGrid \n"
                + "ccw='false' solid='false'"
                + " xDimension='200'"
                + " xSpacing='" + lonRangeMetric + "'"
                + " zDimension='200'"
                + " zSpacing='" + latRangeMetric + "'"
                + " height='" + elevations + "'/>\n"
                + "</Shape> \n"
               // + "</Transform> \n"
                + "</Transform> \n";
        try {
            Files.write(Paths.get(outFilename), txt.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
