/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.VertexGeometric;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date @author Serge Morvan
 */
public class ObjExportToSTL {

    protected GeodesyServices geodesyServices;
    protected GuiAgentServices guiAgentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected JTSServices jtsServices;
    protected Pro4JServices pro4JServices;
    protected ObjComponentServices objComponentServices;
    protected DisplayServices displayServices;
    protected double objXOffset;
    protected double objYOffset;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected RenderableLayer layer;
    protected String content;
    protected List<Face> faces = null;
    protected static final String ALARM_SOUND = "/data/sounds/openSea.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    // List<Point3D> points = new ArrayList<>();

    public ObjExportToSTL(GeodesyServices geodesyServices,
            GuiAgentServices guiAgentServices,
            JTSServices jtsServices,
            ObjComponentServices objComponentServices,
            Pro4JServices pro4JServices,
            DisplayServices displayServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices) {
        this.geodesyServices = geodesyServices;
        this.guiAgentServices = guiAgentServices;
        this.jtsServices = jtsServices;
        this.objComponentServices = objComponentServices;
        this.pro4JServices = pro4JServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.displayServices = displayServices;
    }

    public List<Point3D> loadObj(RenderableLayer layer, double objXOffset, double objYOffset, boolean isTerrain) {
        this.layer = layer;
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl/obj", "Georeferenced STL files (*.obj)", "*.OBJ", "*.obj");
        List< Point3D> bounds = null;

        if (file != null) {
            Path path = filter(file);

            List<VertexGeometric> verticesG = objComponentServices.getVerticesG(path.toString());
            bounds = getBounds(verticesG, objXOffset + 3.8, objYOffset + .5, isTerrain);

            content = "solid " + file.getName() + "  Bounds :  " + bounds + "\n";
            guiAgentServices.getJobsManager().newJob("Load Obj objects", new Job() {
                @Override
                public void run(ProgressHandle progressHandle) {
                    faces = objComponentServices.getFaces(path.toString());
                    faces.stream().map((f) -> f.getVertices()).forEachOrdered((fvs) -> {
                        content += toFacet(fvs, objXOffset, objYOffset, isTerrain);
                    });
                    content += "endsolid " + file.getName() + "\n";
                    String filename = file.getAbsolutePath();
                    filename = filename.replace(".obj", "Wgs84.stl");
                    try {
                        Files.write(Paths.get(filename), content.getBytes(), StandardOpenOption.CREATE);
                    } catch (IOException ex) {
                        Logger.getLogger(ObjExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                    }
                    //  displayServices.displayPoints3DAsPath(points, 150.0, layer, Material.GREEN);
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                }
            });
        }
        return bounds;
    }

    private List<Point3D> getBounds(List<VertexGeometric> verticesG, double objXOffset, double objYOffset, boolean isTerrain) {
        double lonMax93 = Double.NEGATIVE_INFINITY;
        double latMax93 = Double.NEGATIVE_INFINITY;
        double latMin93 = Double.MAX_VALUE;
        double lonMin93 = Double.MAX_VALUE;
        List<Point3D> result = new ArrayList<>();
        List<Point3D> tmp = new ArrayList<>();
        //   if (isTerrain == true) {
        List<Point3D> points = new ArrayList<>();

        for (VertexGeometric v : verticesG) {
            points.add(pro4JServices.convertLambert93ToWGS84(v.y + objYOffset, v.x + objXOffset));
        }

        for (Point3D p : points) {
            double x = p.getLongitude();
            double y = p.getLatitude();
            if (x > lonMax93) {
                lonMax93 = x;
            }
            if (x < lonMin93) {
                lonMin93 = x;
            }
            if (y > latMax93) {
                latMax93 = y;
            }
            if (y < latMin93) {
                latMin93 = y;
            }
        }
        for (Point3D p : points) {
            if (p.getLatitude() == latMin93) {
                //  ptSW = p;
                tmp.add(p);
            } else {
                if (p.getLongitude() == lonMax93) {
                    // ptSW = p;
                    tmp.add(p);
                } else {
                    if (p.getLatitude() == latMax93) {
                        // ptNE = p;
                        tmp.add(p);
                    } else {
                        if (p.getLongitude() == lonMin93) {
                            //  ptNW = p;
                            tmp.add(p);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getLatitude() == latMin93) {
                result.add(tmp.get(i));
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getLongitude() == lonMax93) {
                result.add(tmp.get(i));
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getLatitude() == latMax93) {
                result.add(tmp.get(i));
            }
        }
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).getLongitude() == lonMin93) {
                result.add(tmp.get(i));
            }
        }
        result.add(result.get(0));
        // }
        return result;
    }

    private String toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset, boolean isTerrain) {
        String facet = "";
        Vec3d[] mainFace = new Vec3d[3];
        int i = 0;
        List<Point3D> view = new ArrayList<>();
        // Main face
        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset + 3.8);//3.5
            double y = (fv.getV().y + objYOffset + .5);//1
            Point3D pt = pro4JServices.convertLambert93ToWGS84(y, x);
            view.add(pt);
            // displayServices.displayPoints3DAsPath(view, 150.0, layer, Material.GREEN);
            mainFace[i++] = new Vec3d(pt.getLongitude(), pt.getLatitude(), fv.getV().z); //z * 2
        }

        facet += facetToSTL(mainFace);
        // facet += rotateFacetToSTL(mainFace,5.42);
        if (isTerrain == true) {
            //Create new vertices and faces to close the volume
            Vec3d ww0 = new Vec3d(mainFace[0].x, mainFace[0].y, 0.0);
            Vec3d ww1 = new Vec3d(mainFace[1].x, mainFace[1].y, 0.0);
            Vec3d ww2 = new Vec3d(mainFace[2].x, mainFace[2].y, 0.0);

            Vec3d[] f0 = {mainFace[0], ww0, mainFace[1]};
            facet += facetToSTL(mainFace);

            // 
            Vec3d[] f1 = {ww0, ww1, mainFace[1]};
            facet += facetToSTL(f1);

            Vec3d[] f2 = {ww1, mainFace[1], ww2};
            facet += facetToSTL(f2);

            Vec3d[] f3 = {ww2, mainFace[1], mainFace[1]};
            facet += facetToSTL(f3);

            Vec3d[] f4 = {ww2, mainFace[0], mainFace[2]};
            facet += facetToSTL(f4);

            Vec3d[] f5 = {ww0, mainFace[0], ww2};
            facet += facetToSTL(f5);
        } else {

        }
        return facet;
    }

    private String facetToSTL(Vec3d[] face) {
        String facet = "";
        Vec3d normal;
        Vec3d edge1 = face[1].sub(face[2]);
        Vec3d edge2 = face[2].sub(face[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();

        double z0 = face[0].z;
        double z1 = face[1].z;
        double z2 = face[2].z;
        List<Point3D> points = new ArrayList<>();
        for (Vec3d v : face) {
            Point3D p = new Point3D(v.y, v.x, v.z);
            points.add(p);
            // System.out.println(p);
        }
        displayServices.displayPoints3DAsPath(points, 15.0, layer, Material.GREEN);
        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + face[0].x + " " + face[0].y + " " + z0 + " \n";
        facet += "vertex " + face[1].x + " " + face[1].y + " " + z1 + " \n";
        facet += "vertex " + face[2].x + " " + face[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        return facet;
    }

    private String rotateFacetToSTL(Vec3d[] face, double angle) {

        double x0 = face[0].x;
        double x1 = face[1].x;
        double x2 = face[2].x;

        double y0 = face[0].y;
        double y1 = face[1].y;
        double y2 = face[2].y;

        double z0 = face[0].z;
        double z1 = face[1].z;
        double z2 = face[2].z;

        //Rotation
        double tetha = Math.toRadians(angle);
        double xx0 = x0;
        double yy0 = y0;

        double xx1 = (x1 - x0) * Math.cos(tetha) - (y1 - y0) * Math.sin(tetha) + x0;
        double yy1 = (x1 - x0) * Math.sin(tetha) + (y1 - y0) * Math.cos(tetha) + y0;

        double xx2 = (x2 - x0) * Math.cos(tetha) - (y2 - y0) * Math.sin(tetha) + x0;
        double yy2 = (x2 - x0) * Math.sin(tetha) + (y2 - y0) * Math.cos(tetha) + y0;

        Vec3d ww0 = new Vec3d(xx0, yy0, z0);
        Vec3d ww1 = new Vec3d(xx1, yy1, z1);
        Vec3d ww2 = new Vec3d(xx2, yy2, z2);

        Vec3d[] faceRot = {ww0, ww1, ww2};

        String facet = "";
        Vec3d normal;
        Vec3d edge1 = faceRot[1].sub(faceRot[2]);
        Vec3d edge2 = faceRot[2].sub(faceRot[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();
        // System.out.println(xx1+" "+yy1);
        List<Point3D> points = new ArrayList<>();
        for (Vec3d v : faceRot) {
            Point3D p = new Point3D(v.y, v.x, v.z);
            points.add(p);
            // System.out.println(p);
        }
        displayServices.displayPoints3DAsPath(points, 150.0, layer, Material.GREEN);

        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + xx0 + " " + yy0 + " " + z0 + " \n";
        facet += "vertex " + xx1 + " " + yy1 + " " + z1 + " \n";
        facet += "vertex " + xx2 + " " + yy2 + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        return facet;
    }

    private String facetToPath(Vec3d[] face) {
        String facet = "";
        Vec3d normal;
        Vec3d edge1 = face[1].sub(face[2]);
        Vec3d edge2 = face[2].sub(face[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();

        double z0 = face[0].z;
        double z1 = face[1].z;
        double z2 = face[2].z;

        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + face[0].x + " " + face[0].y + " " + z0 + " \n";
        facet += "vertex " + face[1].x + " " + face[1].y + " " + z1 + " \n";
        facet += "vertex " + face[2].x + " " + face[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        return facet;
    }

    private Path filter(File file) {
        String tmp = null;
        Path path = null;
        try {
            tmp = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException ex) {
            Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        if (tmp != null) {
            //File from BMO contains \ folows whith carriage return ?
            tmp = tmp.replaceAll("\\\\[\\n\\r]", "");
            try {
                Files.delete(file.toPath());
                path = Files.write(file.toPath(), tmp.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(ObjExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return path;
    }
}
