/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
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

    protected GeodesyServices geodesyServices;
    protected Polygon polygon;
    // protected WorldWindow wwd;
    protected ElevationModel model;
    protected String elevationsStr = "";
    protected String bottomStr = "";
    protected double earthSpaceX;
    protected double earthSpaceY;
    protected double spaceLat;
    protected double spaceLon;
    List<? extends Position> positions;
    protected double tileSideX;
    protected double tileSideY;
    protected double tileSpaceX;
    protected double tileSpaceY;
    protected int ptsCountsX;
    protected int ptsCountsY;
    protected String BOTTOM_STR;
    protected double bottom;
    protected double magnification;
    protected List<Point3D> bottomPositions;
    protected List<Point3D> topPositions;
    protected int index;
    protected String TEXTURE = "common/metal.jpg";

    public ElevationLoader(
            GeodesyServices geodesyServices,
            ElevationModel model,
            List<? extends Position> positions,
            int index,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification) {
        this.geodesyServices = geodesyServices;
        this.model = model;
        this.positions = positions;
        this.index = index;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.earthSpaceX = earthSpaceX;
        this.earthSpaceY = earthSpaceY;
        this.bottom = bottom;
        this.magnification = magnification;
    }

    public String computeDEM() {

        BOTTOM_STR = Double.toString(bottom);
        bottomPositions = new ArrayList<>();
        topPositions = new ArrayList<>();
            ptsCountsX = 200; //confusion entre pas du MNT et pas sur la tuile generee
            ptsCountsY = 200;
            earthSpaceX = tileSideX / (ptsCountsX - 1);
            earthSpaceY = tileSideY / (ptsCountsY - 1);

        double latRangeMetric = geodesyServices.getDistanceM(positions.get(3), positions.get(0));
        double lonRangeMetric = geodesyServices.getDistanceM(positions.get(0), positions.get(1));

        String result = "";

        double latInc = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / (ptsCountsY - 1);
        double lonInc = (positions.get(0).getLongitude().getDegrees() - positions.get(1).getLongitude().getDegrees()) / (ptsCountsX - 1);

        latInc = Math.abs(latInc);
        lonInc = Math.abs(lonInc);

        double longitude = positions.get(1).getLongitude().getDegrees();
        double latitude;
        for (int i = 0; i < ptsCountsY; i++) {
            latitude = positions.get(3).getLatitude().getDegrees();
            for (int j = 0; j < ptsCountsX; j++) {
                double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
                if (el < 0) {
                    el = 0;
                }
                el /= magnification;
                elevationsStr += el + " ";
                bottomStr += BOTTOM_STR + " ";
                latitude -= latInc;
            }
            longitude -= lonInc;
        }
        result += createDEM(elevationsStr,
                ptsCountsX, earthSpaceX,
                ptsCountsY, earthSpaceY,
                "<ImageTexture DEF='Ortho' url='\"image_" + index + ".jpg\"'/> \n"
                + "<TextureTransform  rotation='-1.57' />\n",
                "Digital elevation model");

        result += createDEM(bottomStr,
                ptsCountsX, earthSpaceX,
                ptsCountsY, earthSpaceY,
                "\n",
                "Sea level");

        //  North face
        latitude = positions.get(3).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        double pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();

        for (int i = 0; i < ptsCountsX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el /= magnification;
            topPositions.add(new Point3D(pos, el, 0.0));
            bottomPositions.add(new Point3D(pos, bottom, 0.0));
            pos += earthSpaceX;
            longitude -= lonInc;
        }
        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "North face");

        // South face
        latitude = positions.get(0).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int i = 0; i < ptsCountsX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el /= magnification;
            topPositions.add(new Point3D(pos, el, -tileSideX));
            bottomPositions.add(new Point3D(pos, bottom, -tileSideX));
            pos += earthSpaceX;
            longitude -= lonInc;
        }

        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "South face");

        // East face
        double lon0 = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latInc) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= magnification;
            topPositions.add(new Point3D(0.0, el, pos));
            bottomPositions.add(new Point3D(0.0, bottom, pos));
            pos += earthSpaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n",
                bottomPositions, topPositions, TEXTURE, "East face");

        // West face
        lon0 = positions.get(0).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latInc) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= magnification;
            topPositions.add(new Point3D(-tileSideX, el, pos));
            bottomPositions.add(new Point3D(-tileSideX, bottom, pos));
            pos += earthSpaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n",
                bottomPositions, topPositions, TEXTURE, "West face");

        return result;
    }

    public String computeDEM(Point3D[][] pts,
            Polygon polygon,
            int index,
            double tileSideX, double tileSideY,
            double spaceX, double spaceY,
            double bottom,
            double maxElevation,
            double magnification,
            double scaleLatFactor,
            double scaleLonFactor) {
        BOTTOM_STR = Double.toString(bottom);
        bottomPositions = new ArrayList<>();
        topPositions = new ArrayList<>();
        spaceX = tileSideX / (ptsCountsX - 1);
        spaceY = tileSideY / (ptsCountsY - 1);
        String result = "";
        double elevationMax = 0.0;
        //for(int i = 0; i < pts{
        //     if(elevationMax<p.getElevation()){
        ///         elevationMax=p.getElevation();
        //     }
        // }
        //   List<? extends Position> positions = polygon.getBoundaries().get(0);

        double latRange = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / (ptsCountsY - 1);
        double lonRange = (positions.get(0).getLongitude().getDegrees() - positions.get(1).getLongitude().getDegrees()) / (ptsCountsX - 1);

        latRange = Math.abs(latRange);
        lonRange = Math.abs(lonRange);
        double longitude = positions.get(1).getLongitude().getDegrees();
        double latitude;
        for (int i = 0; i < ptsCountsY; i++) {
            latitude = positions.get(3).getLatitude().getDegrees();
            for (int j = 0; j < ptsCountsX; j++) {
                //  double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
                double el = pts[i][j].getElevation();
                if (el < 0) {
                    el = 0;
                }
                el *= magnification;
                elevationsStr += el + " ";
                bottomStr += BOTTOM_STR + " ";
                latitude -= latRange;
            }
            longitude -= lonRange;
        }
        result += createDEM(elevationsStr,
                ptsCountsX, spaceX,
                ptsCountsY, spaceY,
                "<ImageTexture DEF='Ortho' url='\"image_" + index + ".jpg\"'/> \n"
                + "<TextureTransform  rotation='-1.57' />\n",
                "Digital elevation model");

        result += createDEM(bottomStr,
                ptsCountsX, spaceX,
                ptsCountsY, spaceY,
                "\n",
                "Sea level");

        //  North face
        latitude = positions.get(3).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        double pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();

        for (int i = 0; i < ptsCountsX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el /= magnification;
            topPositions.add(new Point3D(pos, el, 0.0));
            bottomPositions.add(new Point3D(pos, bottom, 0.0));
            pos += spaceX;
            longitude -= lonRange;
        }
        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "North face");

        // South face
        latitude = positions.get(0).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int i = 0; i < ptsCountsX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el /= magnification;
            topPositions.add(new Point3D(pos, el, -tileSideX));
            bottomPositions.add(new Point3D(pos, bottom, -tileSideX));
            pos += spaceX;
            longitude -= lonRange;
        }

        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "South face");

        // East face
        double lon0 = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latRange) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= magnification;
            topPositions.add(new Point3D(0.0, el, pos));
            bottomPositions.add(new Point3D(0.0, bottom, pos));
            pos += spaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n",
                bottomPositions, topPositions, TEXTURE, "East face");

        // West face
        lon0 = positions.get(0).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (double lat = positions.get(2).getLatitude().getDegrees();
                lat > positions.get(1).getLatitude().getDegrees();
                lat -= latRange) {
            double el = model.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon0));
            el /= magnification;
            topPositions.add(new Point3D(-tileSideX, el, pos));
            bottomPositions.add(new Point3D(-tileSideX, bottom, pos));
            pos += spaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'> \n",
                bottomPositions, topPositions, TEXTURE, "West face");

        return result;
    }

    public String createDEM(String height, int xDimension, double xSpacing,
            int zDimension, double zSpacing,
            String texture, String comment) {
        String txt = " <!--" + comment + "-->\n"
                + "<Transform rotation='0 1 0 1.57058' translation='0.0 -0.1 0.0'>\n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + texture
                + "<Material diffuseColor='.38 .42 .44'/> \n"
                + "</Appearance>\n"
                + "<ElevationGrid \n"
                + "ccw='true' solid='false'"
                + " xDimension='" + xDimension + "'"
                + " xSpacing='" + xSpacing + "'"
                + " zDimension='" + zDimension + "'"
                + " zSpacing='" + zSpacing + "'"
                + " height='" + height + "'/>\n"
                + "</Shape> \n"
                + "</Transform> \n";
        return txt;
    }

    public String createBoundaryFace(String transform, List<Point3D> bottom,
            List<Point3D> height, String texture, String comment) {
        String txt = " <!--" + comment + "-->\n"
                + transform + "\n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material "
                + "diffuseColor='.38 .42 .44' />\n"
                + "<ImageTexture DEF='Dem Side' url='" + texture + "'/> \n"
                + "</Appearance>\n"
                + "<IndexedFaceSet colorPerVertex='false' ";

        String coordIndex = "coordIndex='";
        coordIndex += "0 1 2 3 -1,";
        coordIndex += "1 4 5 2 -1,";
        int[] tabCoord = {1, 4, 5, 2};
        int[] buff = new int[4];
        for (int i = 1; i < bottom.size() - 2; i++) {
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
        coord += height.get(0).toString() + " ";
        coord += height.get(1).toString() + " ";
        coord += bottom.get(1).toString() + " ";
        coord += bottom.get(0).toString() + " ";
        for (int i = 2; i < bottom.size(); i++) {
            coord += height.get(i).toString() + " "
                    + bottom.get(i).toString() + " ";
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
