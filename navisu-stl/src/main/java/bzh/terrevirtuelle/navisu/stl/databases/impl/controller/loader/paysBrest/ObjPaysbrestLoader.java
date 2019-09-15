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
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import gov.nasa.worldwind.layers.RenderableLayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author serge
 * @date Jul 30, 2019
 */
public class ObjPaysbrestLoader {

    protected GeodesyServices geodesyServices;
    protected GuiAgentServices guiAgentServices;
    protected InstrumentDriverManagerServices instrumentDriverManagerServices;
    protected JTSServices jtsServices;
    protected Pro4JServices pro4JServices;
    protected ObjComponentServices objComponentServices;
    protected static final String ALARM_SOUND = "/data/sounds/openSea.wav";
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected List<SolidGeo> solidGeoList;

    protected DisplayServices displayServices;
    protected RenderableLayer layer;

    public ObjPaysbrestLoader() {
    }

    public ObjPaysbrestLoader(GeodesyServices geodesyServices, GuiAgentServices guiAgentServices,
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

        List<FaceGeo> facesWgs84 = new ArrayList<>();

        Path filetredPath = filter(path);
        List<Face> faces = objComponentServices.getFaces(filetredPath.toString());
        faces.stream().map((f) -> f.getVertices()).forEachOrdered((fvs) -> {
            facesWgs84.add(toFacet(fvs, objXOffset, objYOffset));
        });
        int index = 1;
        for (FaceGeo f : facesWgs84) {
            f.setId(index++);
        }
        List<SolidGeo> solidWgs84List = agregate(facesWgs84);
        System.out.println("solidWgs84List : " + solidWgs84List.size());

        solidGeoList = setTopologyProperties(solidWgs84List);
        instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
        // return solidGeoList;

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

    /*
    Agregate faces by building
     */
    private List<SolidGeo> agregate(List<FaceGeo> faces) {
        List<SolidGeo> solidList = new ArrayList<>();

        int faceIndex = 0;
        int solidIndex = 0;
        List<FaceGeo> faceSet = new ArrayList<>();
        List<FaceGeo> tmpList = new ArrayList<>();
        List<FaceGeo> adList;
        List<EdgeGeo> ground = new ArrayList<>();
        FaceGeo startFace;
        FaceGeo shuttle;

        while (faceIndex < faces.size()) {
            startFace = faces.get(faceIndex);
            // System.out.println("startFace : " + startFace);
            faceSet.clear();
            faceSet.add(startFace);
            shuttle = startFace;
            adList = shuttle.getAdjacents(faces);
            faceSet.addAll(adList);
            while (!adList.isEmpty()) {
                for (FaceGeo f : adList) {
                    tmpList.addAll(f.getAdjacents(faces));
                }
                tmpList.removeAll(faceSet);
                adList.clear();
                adList = tmpList.stream().collect(Collectors.toList());
                for (FaceGeo f : adList) {
                    if (!faceSet.contains(f)) {
                        faceSet.add(f);
                    }
                }
            }
            tmpList.clear();
            faceIndex += faceSet.size();
            SolidGeo s = new SolidGeo(faceSet, solidIndex, "Building_" + solidIndex);
            solidIndex++;
            solidList.add(s);
        }
        return solidList;
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

            //  Geometry footprint = jtsServices.getConvexHull(coordinates, new GeometryFactory());
            Geometry footprint = jtsServices.getEnveloppe(coordinates, new GeometryFactory());
            Point3DGeo centroid = jtsServices.toPoint3D(footprint.getCentroid());

            // gov.nasa.worldwind.render.Polygon polygon = jtsServices.getPolygonFromPolygon(convexHull);
            // displayServices.displayPolygon(polygon, layer, Material.RED);
            solid.setGroundGeom(footprint);
            solid.setGround(pts);
            solid.setCentroid(centroid);
            result.add(solid);
        }

        return result;
    }

    private Path filter(Path path) {
        String tmp = null;

        try {
            tmp = new String(Files.readAllBytes(path));

        } catch (IOException ex) {
            Logger.getLogger(ObjPaysbrestLoader.class
                    .getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        if (tmp != null) {
            //File from BMO contains \ folows whith carriage return ?
            tmp = tmp.replaceAll("\\\\[\\n\\r]", "");
            try {
                Files.delete(path);
                path = Files.write(path, tmp.getBytes(), StandardOpenOption.CREATE);

            } catch (IOException ex) {
                Logger.getLogger(ObjPaysbrestLoader.class
                        .getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return path;
    }

}
