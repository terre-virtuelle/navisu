/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.paysBrest;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.domain.geometry.EdgeGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.parser.Parse;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author serge
 * @date Jul 30, 2019
 */
public class ObjPaysBrestLoader {

    protected GeodesyServices geodesyServices;
    protected GuiAgentServices guiAgentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected JTSServices jtsServices;
    protected Pro4JServices pro4JServices;
    protected ObjComponentServices objComponentServices;
    protected static final String ALARM_SOUND = "/data/sounds/openSea.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String SEP = File.separator;
    protected static final String USER_DIR = System.getProperty("user.dir");
    protected List<SolidGeo> solidGeoList;

    protected DisplayServices displayServices;
    protected RenderableLayer layer;

    public ObjPaysBrestLoader() {
    }

    public ObjPaysBrestLoader(GeodesyServices geodesyServices, GuiAgentServices guiAgentServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            JTSServices jtsServices, Pro4JServices pro4JServices,
            ObjComponentServices objComponentServices,
            DisplayServices displayServices,
            RenderableLayer layer) {
        this.geodesyServices = geodesyServices;
        this.guiAgentServices = guiAgentServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.jtsServices = jtsServices;
        this.pro4JServices = pro4JServices;
        this.objComponentServices = objComponentServices;

        this.displayServices = displayServices;
        this.layer = layer;
    }

    public List<SolidGeo> loadObj(Path path, double objXOffset, double objYOffset) {
        // System.out.println("loadObj");
        List<SolidGeo> solidWgs84List = new ArrayList<>();
        clearTmpDirs(USER_DIR + SEP + "privateData" + SEP + "obj", "*", false);
        Path filetredPath = filter(path);
        String content = null;
        try {
            content = new String(Files.readAllBytes(filetredPath));
        } catch (IOException ex) {
            Logger.getLogger(Parse.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        if (content != null) {
            // Match material library
            Pattern pMtllib = Pattern.compile("mtllib [a-zA-Z0-9_\\.]*");
            String mtllib = "";
            Matcher m;
            m = pMtllib.matcher(content);
            if (m.find()) {
                mtllib = m.group();
            }
            // Split for each group
            String[] tab = content.split("g object_[0-9]+");
            String obj = "";

            //Count of vertices per object
            Pattern pVertices = Pattern.compile("v ");
            //  Matcher m2;
            List<Integer> vCounts = new ArrayList<>();
            int c = 0;
            vCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pVertices.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vCounts.add(c);
            }
            //Count of textures per object
            Pattern pTexture = Pattern.compile("vt ");
            //  Matcher m2;
            List<Integer> vtCounts = new ArrayList<>();
            c = 0;
            vtCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pTexture.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vtCounts.add(c);
            }
            //Count of textures per object
            Pattern pNormal = Pattern.compile("vn ");
            //  Matcher m2;
            List<Integer> vnCounts = new ArrayList<>();
            c = 0;
            vnCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pNormal.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vnCounts.add(c);
            }
            String[] groups = new String[tab.length - 1];
            for (int i = 0; i < tab.length - 1; i++) {
                groups[i] = "# NaVisu " + "\n";
                groups[i] += "# Serge " + new Date() + "\n" + "\n";
                groups[i] += mtllib + "\n";
                int n = i + 1;
                groups[i] += "g object_" + n;
                groups[i] += tab[i + 1].split("f( [0-9]+/[0-9]+/[0-9]+)+")[0];
            }
            //Match faces and translate index of vertices
            Pattern pFaces = Pattern.compile("f( [0-9]+/[0-9]+/[0-9]+)+");
            Matcher m1;
            String face = "";

            for (int i = 1; i < tab.length; i++) {
                m1 = pFaces.matcher(tab[i]);
                while (m1.find()) {
                    face = m1.group();
                    face = face.replace("f", "");
                    String[] vertices = face.trim().split("\\s+");
                    String[] vertex = new String[3];
                    for (int j = 0; j < vertices.length; j++) {
                        String[] vTab = vertices[j].split("/");
                        int x = Integer.valueOf(vTab[0].trim()) - vCounts.get(i - 1);
                        int y = Integer.valueOf(vTab[1].trim()) - vtCounts.get(i - 1);
                        int z = Integer.valueOf(vTab[2].trim()) - vnCounts.get(i - 1);
                        vertex[j] = x + "/" + y + "/" + z;
                    }
                    groups[i - 1] += "f " + vertex[0] + " " + vertex[1] + " " + vertex[2] + "\n";
                }
            }
            String filename = filetredPath.getFileName().toString();
            filename = filename.replace(".obj", "_");
            String out = USER_DIR + SEP + "privateData" + SEP + "obj" + SEP + filename;
            for (int i = 0; i < groups.length; i++) {
                try {
                    int n = i + 1;
                    Files.write(Paths.get(out + n + ".obj"), groups[i].getBytes(), StandardOpenOption.CREATE);
                } catch (IOException ex) {
                    Logger.getLogger(ObjPaysBrestLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
            solidWgs84List = toWgs84(out, groups, objXOffset, objYOffset);
            solidGeoList = setTopologyProperties(solidWgs84List);
        }

        return solidGeoList;
    }

    protected List<SolidGeo> toWgs84(String out, String[] groups, double objXOffset, double objYOffset) {
        List<SolidGeo> solidWgs84List = new ArrayList<>();
        for (int i = 0; i < groups.length; i++) {
            List<FaceGeo> facesWgs84 = new ArrayList<>();
            int n = i + 1;
            List<Face> faces = objComponentServices.getFaces(out + n + ".obj");
            faces.stream().map((f) -> f.getVertices()).forEachOrdered((fvs) -> {
                facesWgs84.add(toFacet(fvs, objXOffset, objYOffset));
            });
            int index = 1;
            for (FaceGeo f : facesWgs84) {
                f.setId(index++);
            }
            solidWgs84List.add(new SolidGeo(facesWgs84, i, "Building_" + i));
        }
        return solidWgs84List;
    }

    public List<SolidGeo> loadObjBuilding(Path path, double objXOffset, double objYOffset) {
        // System.out.println("loadObj");
        List<SolidGeo> solidWgs84List = new ArrayList<>();
        clearTmpDirs(USER_DIR + SEP + "privateData" + SEP + "obj", "*", false);
        Path filetredPath = filter(path);
        String content = null;
        try {
            content = new String(Files.readAllBytes(filetredPath));
        } catch (IOException ex) {
            Logger.getLogger(Parse.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        if (content != null) {
            // Match material library
            Pattern pMtllib = Pattern.compile("mtllib [a-zA-Z0-9_\\.]*");
            String mtllib = "";
            Matcher m;
            m = pMtllib.matcher(content);
            if (m.find()) {
                mtllib = m.group();
            }
            // Split for each group
            String[] tab = content.split("g object_[0-9]+");
            String obj = "";

            //Count of vertices per object
            Pattern pVertices = Pattern.compile("v ");
            //  Matcher m2;
            List<Integer> vCounts = new ArrayList<>();
            int c = 0;
            vCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pVertices.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vCounts.add(c);
            }

            //Count of textures per object
            Pattern pTexture = Pattern.compile("vt ");
            //  Matcher m2;
            List<Integer> vtCounts = new ArrayList<>();
            c = 0;
            vtCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pTexture.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vtCounts.add(c);
            }
            //Count of textures per object
            Pattern pNormal = Pattern.compile("vn ");
            //  Matcher m2;
            List<Integer> vnCounts = new ArrayList<>();
            c = 0;
            vnCounts.add(c);
            for (int i = 1; i < tab.length; i++) {
                m = pNormal.matcher(tab[i]);
                while (m.find()) {
                    c++;
                }
                vnCounts.add(c);
            }

            /* 
             Changement repere, passage en coordonnées geographiques
             Le premier point
             */
            String lambert;
            String wgs84;

            pVertices = Pattern.compile("v [+-]?(\\d+\\.\\d+) [+-]?(\\d+\\.\\d+) [+-]?(\\d+\\.\\d+)");
            for (int i = 1; i < tab.length; i++) {
                m = pVertices.matcher(tab[i]);
                while (m.find()) {
                    lambert = m.group();
                    wgs84 = pro4JServices.convertLambert93ToWGS84(lambert, objXOffset, objYOffset);

                    tab[i] = tab[i].replace(lambert, wgs84);
                }
            }

            String[] groups = new String[tab.length - 1];
            int n = 0;
            for (int i = 0; i < tab.length - 1; i++) {
                n = i + 1;
                groups[i] = "g object_" + n;
                groups[i] += tab[i + 1].split("f( [0-9]+/[0-9]+/[0-9]+)+")[0];
            }
            //Match faces and translate index of vertices
            Pattern pFaces = Pattern.compile("f( [0-9]+/[0-9]+/[0-9]+)+");
            Matcher m1;
            String face;
            for (int i = 1; i < tab.length; i++) {
                m1 = pFaces.matcher(tab[i]);
                while (m1.find()) {
                    face = m1.group();
                    face = face.replace("f", "");
                    String[] vertices = face.trim().split("\\s+");
                    String[] vertex = new String[3];
                    for (int j = 0; j < vertices.length; j++) {
                        String[] vTab = vertices[j].split("/");
                        int x = 0;
                        int y = 0;
                        int z = 0;
                        if (i == 1 || i == 2) {
                            x = Integer.valueOf(vTab[0].trim());
                            y = Integer.valueOf(vTab[1].trim());
                            z = Integer.valueOf(vTab[2].trim());
                        }
                        if (i != 1 && i % 2 == 1) {
                            x = Integer.valueOf(vTab[0].trim()) - vCounts.get(i - 1);
                            y = Integer.valueOf(vTab[1].trim()) - vtCounts.get(i - 1);
                            z = Integer.valueOf(vTab[2].trim()) - vnCounts.get(i - 1);
                        }
                        if (i != 2 && i % 2 == 0) {
                            x = Integer.valueOf(vTab[0].trim()) - vCounts.get(i - 2);
                            y = Integer.valueOf(vTab[1].trim()) - vtCounts.get(i - 2);
                            z = Integer.valueOf(vTab[2].trim()) - vnCounts.get(i - 2);
                        }
                        vertex[j] = x + "/" + y + "/" + z;
                    }
                    groups[i - 1] += "f " + vertex[0] + " " + vertex[1] + " " + vertex[2] + "\n";
                }
            }
            String[] buildingGroups = new String[groups.length / 2];
            for (int i = 0, j = 0; i < buildingGroups.length; i++, j += 2) {
                buildingGroups[i] = "# NaVisu " + "\n"
                        + "# Translated by Lithops " + new Date() + "\n" + "\n"
                        + mtllib + "\n"
                        + groups[j] + groups[j + 1];
            }
            /*
            String orig = null;
            String local;
            boolean first = true;
            pVertices = Pattern.compile("v [+-]?(\\d+\\.\\d+) [+-]?(\\d+\\.\\d+) [+-]?(\\d+\\.\\d+)");
            for (int i = 0; i < buildingGroups.length; i++) {
                m = pVertices.matcher(buildingGroups[i]);
                while (m.find()) {
                    wgs84 = m.group();
                    if (first == true) {
                        orig = wgs84;
                        first = false;
                        //System.out.println("orig : " + orig);
                    }
                    local = geodesyServices.getDistancesXY(orig, wgs84);
                    buildingGroups[i] = buildingGroups[i].replace(wgs84, local);
                }
                first = true;
            }
             */
 /*
            
             */
            String filename = filetredPath.getFileName().toString();
            filename = filename.replace(".obj", "_");
            String out = USER_DIR + SEP + "privateData" + SEP + "obj" + SEP + filename;
            for (int i = 0; i < buildingGroups.length; i++) {
                try {
                    n = i + 1;
                    Files.write(Paths.get(out + n + ".obj"), buildingGroups[i].getBytes(), StandardOpenOption.CREATE);
                } catch (IOException ex) {
                    Logger.getLogger(ObjPaysBrestLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
            solidWgs84List = toWgs84(out, buildingGroups, objXOffset, objYOffset);
            solidGeoList = setTopologyProperties(solidWgs84List);
        }

        return solidGeoList;
    }

    private FaceGeo toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset) {
        FaceGeo faceWgs84 = new FaceGeo("Building");

        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset);
            double y = (fv.getV().y + objYOffset);
            Point3DGeo ptWgs84 = pro4JServices.convertLambert93ToWGS84(y, x);
            ptWgs84.setElevation(fv.getV().z);
            faceWgs84.add(ptWgs84);
        }
        return faceWgs84;
    }

    protected List<SolidGeo> setTopologyProperties(List<SolidGeo> solidWgs84List) {

        List<SolidGeo> result = new ArrayList<>();
        for (SolidGeo solid : solidWgs84List) {
            List<Point3DGeo> pts = new ArrayList<>();
            List<EdgeGeo> edges = new ArrayList<>();
            List<FaceGeo> faces = solid.getFaces();
            faces.forEach((f) -> {
                edges.add(f.getGround());
            });
            for (int i = 0; i < edges.size(); i++) {
                pts.add(edges.get(i).getX());
                pts.add(edges.get(i).getY());
            }
            pts.add(pts.get(0));
            Coordinate[] coordinates = jtsServices.toTabCoordinates(pts);

            // Geometry footprint = jtsServices.getConvexHull(coordinates, new GeometryFactory());
            Geometry footprint = jtsServices.getEnvelope(coordinates, new GeometryFactory());
            if (footprint != null) {
                Point3DGeo centroid = jtsServices.toPoint3D(footprint.getCentroid());

                // gov.nasa.worldwind.render.Polygon polygon = jtsServices.getPolygonFromPolygon(convexHull);
                // displayServices.displayPolygon(polygon, layer, Material.RED);
                solid.setGroundGeom(footprint);
                solid.setGround(pts);
                solid.setCentroid(centroid);
                result.add(solid);
            }
        }

        return result;
    }

    private Path filter(Path path) {
        String tmp = null;
        try {
            tmp = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            Logger.getLogger(ObjPaysBrestLoader.class
                    .getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        tmp = tmp.replace("\\", "");

        String content = "";
        String[] tab = tmp.split("\n");

        for (int i = 0; i < tab.length; i++) {
            String[] t = tab[i].trim().split("\\s+");
            if (!t[0].trim().equals("") && t[0].trim().equals("vn") && t.length == 3) {
                String tmp0 = tab[i].replaceAll("\r", "");
                String tmp1 = tab[++i].replaceAll("\r", "");
                String tmp2 = tmp0 + tmp1;
                tmp2 = tmp2.replaceAll("\r", "");
                content += tmp2 + "\n";
            } else {
                content += tab[i];
            }
        }
        try {
            Files.delete(path);
            path = Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(ObjPaysBrestLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return path;
    }

    private void clearTmpDirs(String dir, String extension, boolean rmDir) {
        try {
            Path directory = Paths.get(dir);

            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(extension)) {
                        Files.delete(file);
                    }
                    if (extension.equals("*")) {
                        Files.delete(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (rmDir == true) {
                        Files.delete(dir);
                    }
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException ex) {
            //Nothing if dir don't exist
        }
    }
}
