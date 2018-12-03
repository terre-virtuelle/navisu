/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl.LambertZone;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl.Pt3D;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Vec3d;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.util.io.IO;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.VertexGeometric;
import gov.nasa.worldwind.WorldWindow;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date @author Serge Morvan
 */
public class ObjExportToSTL {

    protected GeodesyServices geodesyServices;
    protected GuiAgentServices guiAgentServices;
    protected JTSServices jtsServices;
    protected LambertServices lambertServices;
    protected ObjComponentServices objComponentServices;
    double objXOffset;
    double objYOffset;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    String content;
    List<Face> faces = null;
    private Map<Point3D, String> objLocationObjectMap;

    public ObjExportToSTL(GeodesyServices geodesyServices,
            GuiAgentServices guiAgentServices,
            JTSServices jtsServices,
            ObjComponentServices objComponentServices,
            LambertServices lambertServices) {
        this.geodesyServices = geodesyServices;
        this.guiAgentServices = guiAgentServices;
        this.jtsServices = jtsServices;
        this.objComponentServices = objComponentServices;
        this.lambertServices = lambertServices;

        this.objLocationObjectMap = new HashMap<>();
    }

    public Pair<Pt3D, Pt3D> loadObj(double objXOffset, double objYOffset) {
        //  System.out.println(objXOffset + " " + objYOffset);
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl/obj", "Georeferenced STL files (*.obj)", "*.OBJ", "*.obj");
        Path path = filter(file);
        List<VertexGeometric> verticesG = objComponentServices.getVerticesG(path.toString());
        Pair<Pt3D, Pt3D> bounds = getBounds(verticesG, objXOffset, objYOffset);
        double latMin = bounds.getX().getY();
        double lonMin = bounds.getX().getX();
        double latMax = bounds.getY().getY();
        double lonMax = bounds.getY().getX();
        content = "solid " + file.getName() + "  Origin :  " + latMin + " " + lonMin + " 0.0 \n";//, " + latMax + " " + lonMax + " 0.0\n";
        guiAgentServices.getJobsManager().newJob("Load Obj objects", new Job() {
            @Override
            public void run(ProgressHandle progressHandle) {
                faces = objComponentServices.getFaces(path.toString());
                for (Face f : faces) {
                    List<FaceVertex> fvs = f.getVertices();
                    content += toFacet(fvs, objXOffset, objYOffset);
                }
                content += "endsolid " + file.getName() + "\n";
                String filename = file.getAbsolutePath();
                filename = filename.replace(".obj", "Wgs84.stl");
                try {
                    Files.write(Paths.get(filename), content.getBytes(), StandardOpenOption.CREATE);
                } catch (IOException ex) {
                    Logger.getLogger(ObjExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                }
            }
        });
        return bounds;
    }

    private Pair<Pt3D, Pt3D> getBounds(List<VertexGeometric> verticesG, double objXOffset, double objYOffset) {
        double lonMax93 = Double.NEGATIVE_INFINITY;
        double latMax93 = Double.NEGATIVE_INFINITY;
        double latMin93 = Double.MAX_VALUE;
        double lonMin93 = Double.MAX_VALUE;
        for (VertexGeometric v : verticesG) {
            float x = v.x;
            float y = v.y;
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
        latMin93 += objYOffset;
        latMax93 += objYOffset;
        lonMin93 += objXOffset;
        lonMax93 += objXOffset;

        Pt3D ptMin = lambertServices.convertToWGS84Deg(lonMin93, latMin93, LambertZone.Lambert93);
        Pt3D ptMax = lambertServices.convertToWGS84Deg(lonMax93, latMax93, LambertZone.Lambert93);

        System.out.println(ptMin + " " + ptMax);
        return new Pair<>(ptMin, ptMax);
    }

    private String toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset) {
        String facet = "";
        Vec3d[] vec3d = new Vec3d[3];
        Vec3d normal;
        int i = 0;
        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset);
            double y = (fv.getV().y + objYOffset);
            Pt3D pt = lambertServices.convertToWGS84Deg(x, y, LambertZone.Lambert93);
            vec3d[i++] = new Vec3d(pt.getX(), pt.getY(), fv.getV().z * 2);
        }

        Vec3d edge1 = vec3d[1].sub(vec3d[2]);
        Vec3d edge2 = vec3d[2].sub(vec3d[0]);
        normal = Vec3d.cross(edge1, edge2).normalize();

        double z0 = vec3d[0].z;
        double z1 = vec3d[1].z;
        double z2 = vec3d[2].z;

        facet = "facet normal ";
        facet += normal.x + " " + normal.y + " " + normal.z + " \n";
        facet += "outer loop \n";
        facet += "vertex " + vec3d[0].x + " " + vec3d[0].y + " " + z0 + " \n";
        facet += "vertex " + vec3d[1].x + " " + vec3d[1].y + " " + z1 + " \n";
        facet += "vertex " + vec3d[2].x + " " + vec3d[2].y + " " + z2 + " \n";
        facet += "endloop \n";
        facet += "endfacet \n";
        return facet;
    }

    private Path filter(File file) {
        String content = null;
        Path path = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException ex) {
            Logger.getLogger(DaeExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        if (content != null) {
            //File from BMO conatains \ folows whith carriage return ?
            content = content.replaceAll("\\\\[\\n\\r]", "");
            try {
                Files.delete(file.toPath());
                path = Files.write(file.toPath(), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(ObjExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return path;
    }
}
