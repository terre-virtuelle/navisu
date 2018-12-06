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
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.owens.oobjloader.builder.VertexGeometric;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
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
    protected double objXOffset;
    protected double objYOffset;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected String content;
    protected List<Face> faces = null;

    public ObjExportToSTL(GeodesyServices geodesyServices,
            GuiAgentServices guiAgentServices,
            JTSServices jtsServices,
            ObjComponentServices objComponentServices,
            LambertServices lambertServices,
            DisplayServices displayServices) {
        this.geodesyServices = geodesyServices;
        this.guiAgentServices = guiAgentServices;
        this.jtsServices = jtsServices;
        this.objComponentServices = objComponentServices;
        this.lambertServices = lambertServices;
    }

    public Pair<Point3D, Point3D> loadObj(RenderableLayer s57Layer, double objXOffset, double objYOffset, boolean isTerrain) {
        File file = IO.fileChooser(guiAgentServices.getStage(), "data/stl/obj", "Georeferenced STL files (*.obj)", "*.OBJ", "*.obj");
        Path path = filter(file);

        List<VertexGeometric> verticesG = objComponentServices.getVerticesG(path.toString());

        Pair<Point3D, Point3D> bounds = getBounds(verticesG, objXOffset, objYOffset, isTerrain);
        double latMin = bounds.getX().getLatitude();
        double lonMin = bounds.getX().getLongitude();
        double latMax = bounds.getY().getLatitude();
        double lonMax = bounds.getY().getLongitude();

        content = "solid " + file.getName() + "  Bounds :  " + latMin + " " + lonMin + " 0.0, " + latMax + " " + lonMax + " 0.0\n";

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
            }
        });
        return bounds;
    }

    private Pair<Point3D, Point3D> getBounds(List<VertexGeometric> verticesG, double objXOffset, double objYOffset, boolean isTerrain) {
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

        //in Lon Lat
        Pt3D ptMin = lambertServices.convertToWGS84Deg(lonMin93, latMin93, LambertZone.Lambert93);
        Pt3D ptMax = lambertServices.convertToWGS84Deg(lonMax93, latMax93, LambertZone.Lambert93);

        Point3D pMin = new Point3D(ptMin.getY(), ptMin.getX(), ptMin.getZ());
        Point3D pMax = new Point3D(ptMax.getY(), ptMax.getX(), ptMax.getZ());
        return new Pair<>(pMin, pMax);

    }

    private String toFacet(List<FaceVertex> fvs, double objXOffset, double objYOffset, boolean isTerrain) {
        String facet = "";
        Vec3d[] mainFace = new Vec3d[3];
        int i = 0;
        // Main face
        for (FaceVertex fv : fvs) {
            double x = (fv.getV().x + objXOffset);
            double y = (fv.getV().y + objYOffset);
            Pt3D pt = lambertServices.convertToWGS84Deg(x, y, LambertZone.Lambert93);
            mainFace[i++] = new Vec3d(pt.getX(), pt.getY(), fv.getV().z);
        }
        facet += facetToSTL(mainFace);
        if (isTerrain == true) {
            //Create new vertices and faces to close the volume
            Vec3d ww0 = new Vec3d(mainFace[0].x, mainFace[0].y, 0.0);
            Vec3d ww1 = new Vec3d(mainFace[1].x, mainFace[1].y, 0.0);
            Vec3d ww2 = new Vec3d(mainFace[2].x, mainFace[2].y, 0.0);

            Vec3d[] f0 = {mainFace[0], ww0, mainFace[1]};
            facet += facetToSTL(f0);

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
