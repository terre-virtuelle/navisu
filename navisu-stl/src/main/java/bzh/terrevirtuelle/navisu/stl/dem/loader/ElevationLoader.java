/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.dem.loader;

//import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.render.Polygon;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    protected GeodesyServices geodesyServices;
    protected Polygon polygon;
    protected WorldWindow wwd;
    protected ElevationModel model;
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
    protected List<Point3D> bottomPositions;
    protected List<Point3D> topPositions;
    protected int index;
    protected String TEXTURE = "common/metal.jpg";
int i=0;
    public ElevationLoader(
            GeodesyServices geodesyServices,
            List<? extends Position> positions,
            int index,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification) {
        this.geodesyServices = geodesyServices;
        this.positions = positions;
        this.index = index;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.earthSpaceX = earthSpaceX;
        this.earthSpaceY = earthSpaceY;
        this.bottom = bottom;
        this.magnification = magnification;
        wwd = GeoWorldWindViewImpl.getWW();
        model = wwd.getModel().getGlobe().getElevationModel();

    }

    public double[] getBestElevations(List<LatLon> locations) {
        Globe globe = wwd.getModel().getGlobe();
        Sector sector = Sector.boundingSector(locations);
        double[] elevations = new double[locations.size()];

        // Iterate until the best resolution is achieved. Use the elevation model to determine the best elevation.
        double targetResolution = globe.getElevationModel().getBestResolution(sector);
        double actualResolution = Double.MAX_VALUE;
       // while (actualResolution > targetResolution) {
            // try {
            actualResolution = globe.getElevations(sector, locations, targetResolution, elevations);
            // Thread.sleep(2); // give the system a chance to retrieve data from the disk cache or the server
            //  } catch (InterruptedException ex) {
            //      Logger.getLogger(ElevationLoader.class.getName()).log(Level.SEVERE, null, ex);
            //  }
            System.out.println(i++);
      //  }
        return elevations;
    }

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

        double longitude = positions.get(1).getLongitude().getDegrees();
        double latitude;// = positions.get(3).getLatitude().getDegrees();

        //To load best elevation model
        //    double latView = geodesyServices.getPosition(positions.get(0), 0.0, latRangeMetric / 2.0).getLatitude().getDegrees();
        //   double lonView = geodesyServices.getPosition(positions.get(0), 90.0, lonRangeMetric / 2.0).getLongitude().getDegrees();
        //   wwd.getView().setEyePosition(Position.fromDegrees(latView, lonView, 10000));
        //   wwd.redrawNow();
        /* 

        for (int i = 0; i < ptsCountX; i++) {
            latitude = positions.get(3).getLatitude().getDegrees();
            for (int j = 0; j < ptsCountY; j++) {
                double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
                if (el < 0) {
                    el = 0;
                }
                el *= magnification;
                elevationsStr += formatter.format(el) + " ";
                bottomStr += BOTTOM_STR + " ";
                latitude -= latInc;
            }
            elevationsStr += "\n ";
            longitude -= lonInc;
        }
         */
        List<LatLon> locations = new ArrayList<>();
        for (int i = 0; i < ptsCountX; i++) {
            latitude = positions.get(3).getLatitude().getDegrees();
            for (int j = 0; j < ptsCountY; j++) {
                latitude -= latInc;
                locations.add(new LatLon(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude)));
            }
            elevationsStr += "\n ";
            longitude -= lonInc;
        }
        double[] elevations = getBestElevations(locations);
        for (double el : elevations) {
            if (el < 0) {
                el = 0;
            }
            el *= magnification;
            elevationsStr += formatter.format(el) + " ";
            bottomStr += BOTTOM_STR + " ";
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
        latitude = positions.get(3).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        double pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();

        for (int i = 0; i < ptsCountX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el *= magnification;
            topPositions.add(new Point3D(pos, el, 0.0));
            bottomPositions.add(new Point3D(pos, bottom, 0.0));
            pos += tileSpaceX;
            longitude -= lonInc;
        }
        result += createBoundaryFace("", bottomPositions, topPositions, TEXTURE, "North face");

        // South face
        latitude = positions.get(0).getLatitude().getDegrees();
        longitude = positions.get(1).getLongitude().getDegrees();
        pos = 0.0;
        topPositions.clear();
        bottomPositions.clear();
        for (int i = 0; i < ptsCountX; i++) {
            double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
            el *= magnification;
            topPositions.add(new Point3D(pos, el, -tileSideX));
            bottomPositions.add(new Point3D(pos, bottom, -tileSideX));
            pos += tileSpaceX;
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
            el *= magnification;
            topPositions.add(new Point3D(0.0, el, pos));
            bottomPositions.add(new Point3D(0.0, bottom, pos));
            pos += tileSpaceY;
        }
        result += createBoundaryFace("<Transform rotation='0 1 0 3.14116'>",
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
            el *= magnification;
            topPositions.add(new Point3D(-tileSideX, el, pos));
            bottomPositions.add(new Point3D(-tileSideX, bottom, pos));
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

    public String createBoundaryFace(String transform, List<Point3D> bottom,
            List<Point3D> height, String texture, String comment) {
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
