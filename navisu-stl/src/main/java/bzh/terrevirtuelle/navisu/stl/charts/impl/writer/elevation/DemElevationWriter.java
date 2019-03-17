/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.writer.elevation;

//import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Mar 4, 2017
 */
public abstract class DemElevationWriter {

    protected GeodesyServices geodesyServices;
    protected Polygon polygon;

    protected String elevationsStr = "";
    protected String bottomStr = "";
    protected double earthSpaceX;
    protected double earthSpaceY;
    protected List<? extends Position> positions;
    protected double tileSideX;
    protected double tileSideY;
    protected double tileSpaceX;
    protected double tileSpaceY;
    protected int ptsCountX;
    protected int ptsCountY;
    protected String BOTTOM_STR;
    protected double bottom;
    protected double magnification;
    protected List<Point3DGeo> bottomPositions;
    protected List<Point3DGeo> topPositions;
    protected int index;
    protected String TEXTURE = "common/metal.jpg";
    int i = 0;
    protected double offset;

    public DemElevationWriter(
            GeodesyServices geodesyServices,
            List<? extends Position> positions,
            int index,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification,
            double offset) {
        this.geodesyServices = geodesyServices;
        this.positions = positions;
        this.index = index;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.earthSpaceX = earthSpaceX;
        this.earthSpaceY = earthSpaceY;
        this.bottom = bottom;
        this.magnification = magnification;
        this.offset = offset;
    }

    public abstract double[][] getElevations(List<? extends Position> positions,
            int ptsCountX, int ptsCountY,
            double latInc, double lonInc, double offset);

    public String computeDEM() {

        NumberFormat formatter = new DecimalFormat("#0.0000");

        BOTTOM_STR = Double.toString(bottom);
        bottomPositions = new ArrayList<>();
        topPositions = new ArrayList<>();

        double latRangeMetric = geodesyServices.getDistanceM(positions.get(3), positions.get(0));
        double lonRangeMetric = geodesyServices.getDistanceM(positions.get(0), positions.get(1));

        double scaleLat = tileSideY / latRangeMetric;
        double scaleLon = tileSideX / lonRangeMetric;

        ptsCountX = (int) (lonRangeMetric / earthSpaceX) + 1;
        ptsCountY = (int) (latRangeMetric / earthSpaceY) + 1;

        tileSpaceX = tileSideX / (ptsCountX - 1);
        tileSpaceY = tileSideY / (ptsCountY - 1);

        magnification *= scaleLat;

        String result = "";

        double latInc = (positions.get(3).getLatitude().getDegrees() - positions.get(0).getLatitude().getDegrees()) / (ptsCountY - 1);
        double lonInc = (positions.get(1).getLongitude().getDegrees() - positions.get(0).getLongitude().getDegrees()) / (ptsCountX - 1);

        latInc = Math.abs(latInc);
        lonInc = Math.abs(lonInc);

        double[][] elevations = getElevations(positions, ptsCountX, ptsCountY, latInc, lonInc, offset);

        for (int i = 0; i < ptsCountX; i++) {
            for (int j = 0; j < ptsCountY; j++) {
                double el = elevations[i][j] * magnification;
                elevationsStr += formatter.format(el) + " ";
                bottomStr += BOTTOM_STR + " ";
            }
            elevationsStr += "\n ";
        }

        result += createDEM(elevationsStr,
                ptsCountX, tileSpaceX,
                ptsCountY, tileSpaceY,
                "<ImageTexture DEF='Ortho' url='image_" + index + ".jpg'/> \n"
                + "<TextureTransform  rotation='-1.57' />\n",
                "Digital elevation model");

        result += createDEM(bottomStr,
                ptsCountX, tileSpaceX,
                ptsCountY, tileSpaceY,
                "\n",
                "Sea level");

        //  North face
        double pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int u = 0; u < ptsCountX; u++) {
            double el = elevations[u][0];
            el *= magnification;
            topPositions.add(new Point3DGeo(pos, el, 0.0));
            bottomPositions.add(new Point3DGeo(pos, bottom, 0.0));
            pos += tileSpaceX;
        }
        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "North face");

        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int u = 0; u < ptsCountX; u++) {
            double el = elevations[u][ptsCountY - 1];
            el *= magnification;
            topPositions.add(new Point3DGeo(pos, el, -tileSideX));
            bottomPositions.add(new Point3DGeo(pos, bottom, -tileSideX));
            pos += tileSpaceX;
        }
        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "South face");

        // East face
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int v = 0; v < ptsCountY; v++) {
            double el = elevations[0][v];
            el *= magnification;
            topPositions.add(new Point3DGeo(0.0, el, pos));
            bottomPositions.add(new Point3DGeo(0.0, bottom, pos));
            pos += tileSpaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'>",
                bottomPositions, topPositions, TEXTURE, "East face");
        // West face
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int v = 0; v < ptsCountY; v++) {
            double el = elevations[ptsCountY - 1][v];
            el *= magnification;
            topPositions.add(new Point3DGeo(-tileSideX, el, pos));
            bottomPositions.add(new Point3DGeo(-tileSideX, bottom, pos));
            pos += tileSpaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'>",
                bottomPositions, topPositions, TEXTURE, "West face");
        return result;
    }

    public String createDEM(String height,
            int xDimension, double xSpacing,
            int zDimension, double zSpacing,
            String texture, String comment) {
        String txt = " <!--" + comment + "-->\n"
                + "<Transform rotation='0 1 0 1.57058' translation='0.0 0.15 0.0'>\n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + texture
                + "<Material diffuseColor='1.0 1.0 1.0'/> \n"
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

    public String createBoundaryFace(String transform, List<Point3DGeo> bottom,
            List<Point3DGeo> height, String texture, String comment) {
        String txt = " <!--" + comment + "-->\n"
                + transform
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material "
                + "diffuseColor='.38 .42 .44' />\n"
                + "<ImageTexture DEF='Dem_Side' url='" + texture + "'/> \n"
                + "</Appearance>\n"
                + "<IndexedFaceSet colorPerVertex='false' "; //IndexedLineSet

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
        coord += height.get(0).toString().replace(",", "").replace("{", "").replace("}", ",") + " ";
        coord += height.get(1).toString().replace(",", "").replace("{", "").replace("}", ",") + " ";
        coord += bottom.get(1).toString().replace(",", "").replace("{", "").replace("}", ",") + " ";
        coord += bottom.get(0).toString().replace(",", "").replace("{", "").replace("}", ",") + " ";
        for (int i = 2; i < bottom.size(); i++) {
            coord += height.get(i).toString().replace(",", "").replace("{", "").replace("}", ",") + " "
                    + bottom.get(i).toString().replace(",", "").replace("{", "").replace("}", ",") + " ";
        }
        coord += "' /> \n";
        txt += coord;

        txt += " </IndexedFaceSet> \n" //IndexedLineSet
                + "</Shape> \n";
        if (!transform.equals("")) {
            txt += "</Transform> \n";
        }
        return txt;
    }

}
