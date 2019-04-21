/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.delaunay.impl;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.raster.RasterInfo;
import bzh.terrevirtuelle.navisu.geometry.delaunay.Delaunay;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import gov.nasa.worldwind.geom.Position;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Sep 12, 2017
 */
public class DelaunayImpl
        implements Delaunay, DelaunayServices, ComponentState {

    protected static final String SEP = File.separator;
    protected static final String USER_DIR = System.getProperty("user.dir");
    protected final String IMAGE_DIR = USER_DIR + SEP + "privateData" + SEP + "tif";
    protected final String LUT_DIR = USER_DIR + SEP + "data" + SEP + "lut";

    protected final String VRT_DIR = USER_DIR + SEP + "data" + SEP + "gdal";
    @UsedService
    GeodesyServices geodesyServices;
   
    /**
     *
     * @param points
     * @param elevation
     * @return
     */
    @Override
    public List<Triangle_dt> createDelaunay(List<Point3DGeo> points, double elevation) {

        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLatitude(), pt.getLongitude(), elevation - pt.getElevation()));
        });
        return dt.get_triangles();
    }

    @Override
    public List<Triangle_dt> createDelaunay(List<Point3DGeo> points) {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLatitude(), pt.getLongitude(), pt.getElevation()));
        });
        return dt.get_triangles();
    }

    @Override
    public List<Triangle_dt> createDelaunay(Point3DGeo[][] points, int nbLat, int nbLon, double elevation) {
        List<Triangle_dt> triangles = new ArrayList<>();
        for (int k = 0; k < nbLat - 1; k++) {
            for (int l = 0; l < nbLon - 1; l++) {
                //  System.out.println("k : "+k+" l : "+l);
                Point3DGeo pt0 = points[k][l];
                Point3DGeo pt1 = points[k + 1][l];
                Point3DGeo pt2 = points[k][l + 1];
                Point3DGeo pt3 = points[k + 1][l + 1];
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLatitude(), pt0.getLongitude(), pt0.getElevation()),
                        new Point_dt(pt1.getLatitude(), pt1.getLongitude(), pt1.getElevation()),
                        new Point_dt(pt3.getLatitude(), pt3.getLongitude(), pt3.getElevation())));
                triangles.add(new Triangle_dt(
                        new Point_dt(pt0.getLatitude(), pt0.getLongitude(), pt0.getElevation()),
                        new Point_dt(pt3.getLatitude(), pt3.getLongitude(), pt3.getElevation()),
                        new Point_dt(pt2.getLatitude(), pt2.getLongitude(), pt2.getElevation())));
            }
        }
        return triangles;
    }

    @Override
    public List<Triangle_dt> createDelaunay(Point3DGeo[][] points, double elevation) {
        int latCount = points.length;
        int lonCount = points[0].length;
        return createDelaunay(points, latCount, lonCount, elevation);
    }

    @Override
    public Delaunay_Triangulation getTriangulation(List<Point3DGeo> points) {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        points.stream().forEach((pt) -> {
            dt.insertPoint(new Point_dt(pt.getLatitude(), pt.getLongitude(), pt.getElevation()));
        });
        return dt;
    }

    @Override
    public List<Point3DGeo> toGrid(double latMin, double lonMin,
            double latMax, double lonMax,
            double y, double x,
            double elevation) {

        double latRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMax, lonMin));
        double lonRange = geodesyServices.getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));

        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);

        Set<Position> tmp = new HashSet<>();
        for (int i = 0; i < nbLat; i++) {
            Position p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 0.0, i * 100);
            for (int j = 0; j < nbLon; j++) {
                tmp.add(geodesyServices.getPosition(p, 90.0, j * 100));
            }
        }
        List<Point3DGeo> l = new ArrayList<>();
        tmp.stream().forEach((p) -> {
            l.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), elevation));
        });

        return l;
    }

    @Override
    public Point3DGeo[][] toGridTab(double latMin, double lonMin, double latMax, double lonMax,
            double y, double x,
            double elevation) {
        Position p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 0.0, y);
        double latInc = latMin - p.getLatitude().getDegrees();
        latInc = Math.abs(latInc);
        double lat = latMin;

        p = geodesyServices.getPosition(Position.fromDegrees(latMin, lonMin), 90.0, x);
        double lonInc = lonMin - p.getLongitude().getDegrees();
        lonInc = Math.abs(lonInc);
        double lon = lonMin;

        List<List<Point3DGeo>> ptsList = new ArrayList<>();
        while (lat <= latMax) {
            List<Point3DGeo> l = new ArrayList<>();
            ptsList.add(l);
            while (lon <= lonMax) {
                l.add(new Point3DGeo(lat, lon, elevation));
                lon += lonInc;
            }
            l.add(new Point3DGeo(lat, lonMax, elevation));//last column
            lon = lonMin;
            lat += latInc;
        }

        List<Point3DGeo> l = new ArrayList<>();
        ptsList.add(l);
        lat = latMax;
        for (int i = 0; i < ptsList.get(0).size(); i++) {
            l.add(new Point3DGeo(lat, ptsList.get(0).get(i).getLongitude(), elevation));//last line
        }

        int latCount = ptsList.size();
        int lonCount = ptsList.get(0).size();
        int latLonCount;
        if (latCount > lonCount) {
            latLonCount = lonCount;
        } else {
            latLonCount = latCount;
        }

        Point3DGeo[][] ptsTab = new Point3DGeo[latLonCount][latLonCount];
        for (int i = 0; i < latLonCount; i++) {
            for (int j = 0; j < latLonCount; j++) {
                ptsTab[i][j] = ptsList.get(i).get(j);
            }
        }
        System.out.println("ptsTab " + ptsTab[0].length + " " + ptsTab[1].length);
        return ptsTab;
    }

    @Override
    public Point3DGeo[][] toGridTab(List<Point3DGeo> pts, int lines, int cols) {
        Point3DGeo[][] result = new Point3DGeo[lines][cols];
        int k = 0;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = pts.get(k++);
            }
        }
        return result;
    }

    @Override
    public Point3DGeo[][] toGridTab(DEM dem) {
        Point3DGeo[][] result;
        List<Point3DGeo> points = dem.getGrid();
        int size = points.size();
        int latSize = 1;
        int lonSize = 1;
        // Collections.reverse(points);
        double lat = points.get(0).getLatitude();
        //Count of line and columns
        int u = 0;
        while (points.get(u).getLatitude() == lat) {
            u++;
            lonSize++;
        }
        lonSize--;
        latSize = size / lonSize;

        result = new Point3DGeo[latSize][lonSize];

        int k = 0;
        for (int i = 0; i < latSize; i++) {
            for (int j = 0; j < lonSize; j++) {
                result[i][j] = points.get(k);
                k++;
            }
        }

        for (Point3DGeo p : points) {
            System.out.println(p.getLongitude() + "," + p.getLatitude() + "," + p.getElevation());
        }

        double latMin = result[0][0].getLatitude();
        double latMax = result[latSize - 1][0].getLatitude();
        double lonMin = result[0][0].getLongitude();
        double lonMax = result[0][lonSize - 1].getLongitude();
        System.out.println(latSize + " " + lonSize);
        System.out.println(lonMin + " " + lonMax + " " + latMin + " " + latMax);

        return result;
    }

    @Override
    public RasterInfo toGridTiff(DEM dem) {
        final String ROOT_IMAGE = "dem";
        Point3DGeo[][] tmpTab;
        List<Point3DGeo> points = dem.getGrid();
        int size = points.size();
        int latSize = 1;
        int lonSize = 1;
        double lat = points.get(0).getLatitude();

        //Count of line and columns
        int u = 0;
        while (points.get(u).getLatitude() == lat) {
            u++;
            lonSize++;
        }
        lonSize--;
        latSize = size / lonSize;

        // System.out.println("lonSize : " + lonSize + "   latSize : " + latSize);
        tmpTab = new Point3DGeo[latSize][lonSize];

        int k = 0;
        for (int i = 0; i < latSize; i++) {
            for (int j = 0; j < lonSize; j++) {
                tmpTab[i][j] = points.get(k);
                k++;
            }
        }

        StringWriter content = new StringWriter();
        for (Point3DGeo p : points) {
            content.append(p.getLongitude() + "," + p.getLatitude() + "," + p.getElevation() + "\n");
        }
        String csvPath = IMAGE_DIR + SEP + ROOT_IMAGE + ".csv";
        try {
            Files.write(Paths.get(csvPath), content.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        String vrt = "<OGRVRTDataSource>\n"
                + "    <OGRVRTLayer name=" + ROOT_IMAGE + ">\n"
                + "        <SrcDataSource>" + IMAGE_DIR + SEP + ROOT_IMAGE + ".csv" + "</SrcDataSource>\n"
                + "<GeometryType>wkbPoint</GeometryType>\n"
                + "<GeometryField encoding=\"PointFromColumns\" x=\"field_1\" y=\"field_2\" z=\"field_3\"/>\n"
                + "    </OGRVRTLayer>\n"
                + "</OGRVRTDataSource>";
        String vrtPath = IMAGE_DIR + SEP + "dem.vrt";
        try {
            Files.write(Paths.get(vrtPath), vrt.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        double latMin = tmpTab[0][0].getLatitude();
        double latMax = tmpTab[latSize - 1][0].getLatitude();
        double lonMin = tmpTab[0][0].getLongitude();
        double lonMax = tmpTab[0][lonSize - 1].getLongitude();
        String command = "gdal_grid -a nearest "
                + " -a_srs EPSG:4326"
                + " -txe " + lonMin + " " + lonMax + " -tye  " + latMin + " " + latMax
                + " -outsize " + lonSize + " " + latSize + " -ot INT16 -l "
                + ROOT_IMAGE + " " + IMAGE_DIR + SEP + ROOT_IMAGE + ".vrt " + IMAGE_DIR + SEP + ROOT_IMAGE + ".tif";
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        RasterInfo rasterInfo = new RasterInfo(ROOT_IMAGE + ".tif", lonSize, latSize, lonMin, lonMax, latMin, latMax, IMAGE_DIR, "EPSG:4326");
        rasterToDemTiff(rasterInfo);

        return rasterInfo;
    }

    @Override
    public RasterInfo toGridTiff(Point3DGeo[][] dem, int index) {
        final String ROOT_IMAGE = "dem";

        int latSize = dem.length;
        int lonSize = dem[0].length;

        StringWriter content = new StringWriter();

        for (int i = 0; i < latSize; i++) {
            for (int j = 0; j < lonSize; j++) {
                content.append(dem[i][j].getLongitude() + "," + dem[i][j].getLatitude() + "," + dem[i][j].getElevation() + "\n");
            }
        }
        String csvPath = IMAGE_DIR + SEP + ROOT_IMAGE + ".csv";
        try {
            Files.write(Paths.get(csvPath), content.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        String vrt = "<OGRVRTDataSource>\n"
                + "    <OGRVRTLayer name=" + ROOT_IMAGE + ">\n"
                + "        <SrcDataSource>" + IMAGE_DIR + SEP + ROOT_IMAGE + ".csv" + "</SrcDataSource>\n"
                + "<GeometryType>wkbPoint</GeometryType>\n"
                + "<GeometryField encoding=\"PointFromColumns\" x=\"field_1\" y=\"field_2\" z=\"field_3\"/>\n"
                + "    </OGRVRTLayer>\n"
                + "</OGRVRTDataSource>";
        String vrtPath = IMAGE_DIR + SEP + "dem.vrt";
        try {
            Files.write(Paths.get(vrtPath), vrt.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        double latMin = dem[0][0].getLatitude();
        double latMax = dem[latSize - 1][0].getLatitude();
        double lonMin = dem[0][0].getLongitude();
        double lonMax = dem[0][lonSize - 1].getLongitude();
        String command = "gdal_grid -a nearest "
                + " -a_srs EPSG:4326"
                + " -txe " + lonMin + " " + lonMax + " -tye  " + latMin + " " + latMax
                + " -outsize " + lonSize + " " + latSize + " -ot INT16 -l "
                + ROOT_IMAGE + " " + IMAGE_DIR + SEP + ROOT_IMAGE + ".vrt " + IMAGE_DIR + SEP + ROOT_IMAGE + "_" + index + ".tif";
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        RasterInfo rasterInfo = new RasterInfo(ROOT_IMAGE + "_" + index + ".tif", lonSize, latSize, lonMin, lonMax, latMin, latMax, IMAGE_DIR, "EPSG:4326");
        rasterToDemTiff(rasterInfo);

        return rasterInfo;
    }

    @Override
    public RasterInfo rasterToDemTiff(RasterInfo rasterInfo) {
        String output = rasterInfo.getName();
        output = output.replace(".tif", "_col.tif");
        String command = "gdaldem color-relief  "
                + rasterInfo.getImageDir() + SEP + rasterInfo.getName()
                + " " + LUT_DIR + SEP + "lutLitto3D.txt "
                + rasterInfo.getImageDir() + SEP + output;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        rasterInfo.setDemColorRelief(output);
        System.out.println("output : " + output);
        return rasterInfo;
    }

    @Override
    public Point3DGeo[][] rasterToGridTab(RasterInfo rasterInfo) {
        String command = "gdal_translate -of XYZ "
                + rasterInfo.getImageDir() + SEP + rasterInfo.getName()
                + " " + rasterInfo.getImageDir() + SEP + "out.glz";
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        List<String> content = null;
        try (Stream<String> lines = Files.lines(Paths.get(rasterInfo.getImageDir() + SEP + "out.glz"))) {
            content = lines.collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(DelaunayImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        List<Point3DGeo> point3DGeos = new ArrayList<>();
        if (content != null) {
            for (String s : content) {
                String t[] = s.split("\\s+");
                point3DGeos.add(new Point3DGeo(Double.parseDouble(t[1]), Double.parseDouble(t[0]), Double.parseDouble(t[2])));
            }
        }
        return toGridTab(point3DGeos, rasterInfo.getLatSize(), rasterInfo.getLonSize());
    }

    @Override
    public List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold) {
        List<Triangle_dt> tmp1 = new ArrayList<>();
        triangles.stream().filter((t) -> (t.getBoundingBox().getWidth() < threshold)).forEach((t) -> {
            tmp1.add(t);
        });
        return tmp1;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Point3DGeo[][] mergePointsToGrid(List<Point3DGeo> points, Point3DGeo[][] grid) {
        int line = grid[0].length;
        int col = grid[1].length;
        Point3DGeo[][] result = new Point3DGeo[line][col];
        Point_dt[][] pointsDt = new Point_dt[line][col];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = new Point3DGeo(grid[i][j].getLatitude(), grid[i][j].getLongitude(), grid[i][j].getElevation());
                pointsDt[i][j] = new Point_dt(grid[i][j].getLatitude(), grid[i][j].getLongitude(), grid[i][j].getElevation());
            }
        }

        List<Triangle_dt> triangles = createDelaunay(points);
        System.out.println("triangles : " + triangles.size());
        double dA;
        double dB;
        double dC;
        double min;
        for (Triangle_dt t : triangles) {
            System.out.println("t : " + t);
            for (int i = 0; i < line; i++) {
                for (int j = 0; j < col; j++) {
                    if (t.contains(pointsDt[i][j])) {
                        dA = geodesyServices.getDistanceM(t.A.y, t.A.x, pointsDt[i][j].y, pointsDt[i][j].x);
                        dB = geodesyServices.getDistanceM(t.B.y, t.B.x, pointsDt[i][j].y, pointsDt[i][j].x);
                        dC = geodesyServices.getDistanceM(t.C.y, t.C.x, pointsDt[i][j].y, pointsDt[i][j].x);
                        min = Double.MAX_VALUE;
                        if (min > dA) {
                            min = dA;
                            result[i][j].setElevation(t.A.z);
                        }
                        if (min > dB) {
                            min = dB;
                            result[i][j].setElevation(t.B.z);
                        }
                        if (min > dC) {
                            result[i][j].setElevation(t.C.z);
                        }
                    }
                }
            }
        }
        return result;
    }

}
