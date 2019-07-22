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
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.util.WWUtil;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    protected static final String ALARM_SOUND = "/data/sounds/openSea.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");

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

    /*
    public void loadObj(RenderableLayer layer, double objXOffset, double objYOffset) {
        this.layer = layer;
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl/obj", "Georeferenced STL files (*.obj)", "*.OBJ", "*.obj");
     
        if (file != null) {
            Path path = filter(file);

            List<VertexGeometric> verticesG = objComponentServices.getVerticesG(path.toString());
        
            content = "solid " + file.getName() + "\n";
            guiAgentServices.getJobsManager().newJob("Load Obj objects", new Job() {
                @Override
                public void run(ProgressHandle progressHandle) {
                    faces = objComponentServices.getFaces(path.toString());
                    faces.stream().map((f) -> f.getVertices()).forEachOrdered((fvs) -> {
                        content += toFacet(fvs, objXOffset, objYOffset);
                    });
                    content += "endsolid " + file.getName() + "\n";
                    String filename = file.getAbsolutePath();
                    filename = filename.replace(".obj", "Wgs84.stl");
                    try {
                        Files.write(Paths.get(filename), content.getBytes(), StandardOpenOption.CREATE);
                    } catch (IOException ex) {
                        Logger.getLogger(ObjExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                    }
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                }
            });
        }
        
    }
    private String toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset) {
        String facet = "";
        Vec3d[] mainFace = new Vec3d[fvs.size()];
        int i = 0;
        List<Point3DGeo> view = new ArrayList<>();
        // Main face
        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset);//3.5
            double y = (fv.getV().y + objYOffset);//1
            Point3DGeo pt = pro4JServices.convertLambert93ToWGS84(y, x);
            view.add(pt);
            mainFace[i++] = new Vec3d(pt.getLongitude(), pt.getLatitude(), fv.getV().z); //z * 2
        }

        facet += facetToSTL(mainFace);

        return facet;
    }

    private String facetToSTL(Vec3d[] face) {
        Vec3d normal;
        Vec3d edge1 = face[1].sub(face[2]);
        Vec3d edge2 = face[2].sub(face[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();

        double z0 = face[0].z;
        double z1 = face[1].z;
        double z2 = face[2].z;
        List<Point3DGeo> points = new ArrayList<>();
        for (Vec3d v : face) {
            Point3DGeo p = new Point3DGeo(v.y, v.x, v.z);
            points.add(p);
        }
        displayServices.displayPoints3DAsPolygon(points, 15.0, layer, Material.GREEN);
        String facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + face[0].x + " " + face[0].y + " " + z0 + " \n";
        facet += "vertex " + face[1].x + " " + face[1].y + " " + z1 + " \n";
        facet += "vertex " + face[2].x + " " + face[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        return facet;
    }
     */
    public void loadObj(RenderableLayer layer, double objXOffset, double objYOffset) {
        this.layer = layer;
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl/obj", "Georeferenced STL files (*.obj)", "*.OBJ", "*.obj");
        List<FaceGeo> facesWgs84 = new ArrayList<>();
        if (file != null) {
            Path path = filter(file);
            guiAgentServices.getJobsManager().newJob("Load Obj objects", new Job() {
                @Override
                public void run(ProgressHandle progressHandle) {
                    List<Face> faces = objComponentServices.getFaces(path.toString());
                    faces.stream().map((f) -> f.getVertices()).forEachOrdered((fvs) -> {
                        facesWgs84.add(toFacet(fvs, objXOffset, objYOffset));
                    });
                    List<SolidGeo> solidWgs84 = agregate(facesWgs84);
                    System.out.println(solidWgs84.size());
                    for (SolidGeo s : solidWgs84) {
                        displayServices.displaySolidGeoAsPolygon(s, 0.0, layer, new Material(WWUtil.makeRandomColor(Color.LIGHT_GRAY)));
                    }
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                }
            });
        }

    }

    private FaceGeo toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset) {
        FaceGeo faceWgs84 = new FaceGeo("Wall");
        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset);
            double y = (fv.getV().y + objYOffset);
            Point3DGeo ptWgs84 = pro4JServices.convertLambert93ToWGS84(y, x);
            ptWgs84.setElevation(fv.getV().z);
            faceWgs84.add(ptWgs84);
        }
        //   displayServices.displayFaceGeoAsPolygon(faceWgs84, 0.0, layer, Material.GREEN);
        return faceWgs84;
    }

    /*
    Sort faces by building
     */
    private List<SolidGeo> agregate(List<FaceGeo> faces) {
        List<SolidGeo> result = new ArrayList<>();
        int i = 0;
        for (FaceGeo face : faces) {
            if (!face.isTag()) {
                SolidGeo solid = new SolidGeo(i++, "building");
                result.add(solid);
                List<Point3DGeo> vertices = face.getVertices();
                for (Point3DGeo pt : vertices) {
                    for (FaceGeo f : faces) {
                        if (!f.isTag() && f.contains(pt)) {
                            solid.add(f);
                            f.setTag(true);
                        }
                    }
                }
            }
        }

        return result;
    }

    private SolidGeo build(SolidGeo solid, FaceGeo face) {

        return null;
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
