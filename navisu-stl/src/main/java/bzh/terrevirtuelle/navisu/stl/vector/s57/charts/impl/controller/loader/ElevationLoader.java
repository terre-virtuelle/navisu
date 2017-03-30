/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.util.Point3D;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Mar 4, 2017
 */
public class ElevationLoader {

    protected Polygon polygon;
    protected WorldWindow wwd;
    protected ElevationModel model;
    protected String elevations = "";
    protected String bottom = "";
    protected double SQUARE_SIDE = 200;
    protected int PTS_COUNT = 200;
    protected List<Point3D> bottomPositions;
    protected List<Point3D> topPositions;
    double space;

    public ElevationLoader(Polygon polygon) {
        this.polygon = polygon;
        wwd = GeoWorldWindViewImpl.getWW();
        model = this.wwd.getModel().getGlobe().getElevationModel();
        bottomPositions = new ArrayList<>();
        topPositions = new ArrayList<>();
        space = SQUARE_SIDE / (PTS_COUNT - 1);
    }

    public String compute() {
        String result = "";
        List<? extends Position> positions = polygon.getBoundaries().get(0);
        double latRange = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / PTS_COUNT;
        double lonRange = (positions.get(0).getLongitude().getDegrees() - positions.get(1).getLongitude().getDegrees()) / PTS_COUNT;

        latRange = Math.abs(latRange);
        lonRange = Math.abs(lonRange);

        for (double lon = positions.get(1).getLongitude().getDegrees();
                lon > positions.get(0).getLongitude().getDegrees();
                lon -= lonRange) {
            for (double lat = positions.get(3).getLatitude().getDegrees();
                    lat > positions.get(0).getLatitude().getDegrees();
                    lat -= latRange) {
                double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
                el /= 10;
                elevations += el + " ";
                bottom += "-0.10 ";
            }
        }
        result += createDEM(elevations, "<ImageTexture DEF='MYTEXT' url='\"image.jpg\"'/> \n"
                + "<TextureTransform  rotation='-1.57' />\n");
        result += createDEM(bottom, "\n");

        double lat0 = positions.get(3).getLatitude().getDegrees();
        double pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lon = positions.get(1).getLongitude().getDegrees();
                lon > positions.get(0).getLongitude().getDegrees();
                lon -= lonRange) {
            double el = model.getElevation(Angle.fromDegrees(lat0), Angle.fromDegrees(lon));
            el /= 10;
            topPositions.add(new Point3D(pos, el, 0.0));
            bottomPositions.add(new Point3D(pos, -0.10, 0.0));
            pos += space;
        }
        result += createBoundaryFace("", bottomPositions, topPositions);

        lat0 = positions.get(0).getLatitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lon = positions.get(1).getLongitude().getDegrees();
                lon > positions.get(0).getLongitude().getDegrees();
                lon -= lonRange) {
            double el = model.getElevation(Angle.fromDegrees(lat0), Angle.fromDegrees(lon));
            el /= 10;
            topPositions.add(new Point3D(pos, el, -SQUARE_SIDE));
            bottomPositions.add(new Point3D(pos, -0.10, -SQUARE_SIDE));
            pos += space;
        }
        result += createBoundaryFace("", bottomPositions, topPositions);

        double lon0 = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latRange) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= 10;
            topPositions.add(new Point3D(0.0, el, pos));
            bottomPositions.add(new Point3D(0.0, -0.10, pos));
            pos += space;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n", bottomPositions, topPositions);

        lon0 = positions.get(0).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latRange) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= 10;
            topPositions.add(new Point3D(-SQUARE_SIDE, el, pos));
            bottomPositions.add(new Point3D(-SQUARE_SIDE, -0.10, pos));
            pos += space;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n", bottomPositions, topPositions);

        return result;
    }

    private String createDEM(String height, String texture) {

        String txt
                = "<Transform rotation='0 1 0 1.57058'> \n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + texture
                + "<Material diffuseColor='.38 .42 .44'/> \n"
                + "</Appearance>\n"
                + "<ElevationGrid \n"
                + "ccw='true' solid='false'"
                + " xDimension='" + PTS_COUNT + "'"
                + " xSpacing='" + space + "'"
                + " zDimension='" + PTS_COUNT + "'"
                + " zSpacing='" + space + "'"
                + " height='" + height + "'/>\n"
                + "</Shape> \n"
                + "</Transform> \n";
        return txt;
    }

    private String createBoundaryFace(String transform, List<Point3D> bottom, List<Point3D> height) {
        String txt = transform + "\n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material "
                + "diffuseColor='.38 .42 .44' />\n"
                + "<ImageTexture DEF='MYTEXT' url='\"metal.jpg\"'/> \n"
                + "</Appearance>\n"
                + "<IndexedFaceSet colorPerVertex='false' ";

        String coordIndex = "coordIndex='";
        coordIndex += "0 1 2 3 -1,";
        coordIndex += "1 4 5 2 -1,";
        int[] tabCoord = {1, 4, 5, 2};
        int[] buff = new int[4];
        for (int i = 1; i < bottomPositions.size() - 2; i++) {
            buff[0] = tabCoord[1];
            buff[1] = tabCoord[1] + 2;
            buff[2] = tabCoord[2] + 2;
            buff[3] = tabCoord[2];
            coordIndex += Integer.toString(buff[0]) + " "
                    + Integer.toString(buff[1]) + " "
                    + Integer.toString(buff[2]) + " "
                    + Integer.toString(buff[3]) + " -1,";
            tabCoord[0] = buff[0];
            tabCoord[1] = buff[1];
            tabCoord[2] = buff[2];
            tabCoord[3] = buff[3];
        }
        coordIndex += "' solid='false'>\n";
        txt += coordIndex;

        String coord = "<Coordinate point='";
        coord += topPositions.get(0).toString() + " ";
        coord += topPositions.get(1).toString() + " ";
        coord += bottomPositions.get(1).toString() + " ";
        coord += bottomPositions.get(0).toString() + " ";
        for (int i = 2; i < bottomPositions.size(); i++) {
            coord += topPositions.get(i).toString() + " "
                    + bottomPositions.get(i).toString() + " ";
        }
        coord += "' /> \n";
        txt += coord;

        txt += " </IndexedFaceSet> \n"
                + "</Shape> \n";
        if (!transform.equals("")) {
            txt += "</Transform> \n";
        }
        return txt;
    }

}
