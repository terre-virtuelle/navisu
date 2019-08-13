/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.loader.paysBrest;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.domain.geometry.FaceGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import com.owens.oobjloader.builder.Face;
import com.owens.oobjloader.builder.FaceVertex;
import com.vividsolutions.jts.algorithm.ConvexHull;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ObjPaysbrestLoader() {
    }

    public ObjPaysbrestLoader(GeodesyServices geodesyServices, GuiAgentServices guiAgentServices,
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            JTSServices jtsServices, Pro4JServices pro4JServices,
            ObjComponentServices objComponentServices) {
        this.geodesyServices = geodesyServices;
        this.guiAgentServices = guiAgentServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.jtsServices = jtsServices;
        this.pro4JServices = pro4JServices;
        this.objComponentServices = objComponentServices;
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
        solidGeoList = setTopologyProperties(solidWgs84List);
        instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
        return solidGeoList;
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
        return faceWgs84;
    }

    /*
    Agregate faces by building
     */
    private List<SolidGeo> agregate(List<FaceGeo> faces) {
        List<SolidGeo> result = new ArrayList<>();
        int solidIndex = 0;
        int faceIndex = 0;
        int i;
        Set<FaceGeo> faceSet = new HashSet<>();
        faceSet.add(faces.get(faceIndex));
        List<FaceGeo> l1 = new ArrayList<>();
        List<FaceGeo> l2 = new ArrayList<>();
        List<FaceGeo> l4 = new ArrayList<>();

        while (faceIndex < faces.size()) {
            i = faceIndex;
            for (int j = 0; j < faces.get(i).getVertices().size(); j++) {
                for (int u = 0; u < faces.size(); u++) {
                    for (int v = 0; v < faces.get(u).getVertices().size(); v++) {
                        if (faces.get(i).getVertices().get(j).equals(faces.get(u).getVertices().get(v))) {
                            if (faceSet.add(faces.get(u)) == true) {
                                l1.add(faces.get(u));
                                l4.add(faces.get(u));
                                faceIndex++;
                            }
                        }
                    }
                }
            }
            boolean OK = true;
            int size;
            while (OK == true) {
                size = l1.size();
                OK = false;
                for (int k = 0; k < size; k++) {
                    for (int j = 0; j < l1.get(k).getVertices().size(); j++) {
                        for (int u = 0; u < faces.size(); u++) {
                            for (int v = 0; v < faces.get(u).getVertices().size(); v++) {
                                if (l1.get(k).getVertices().get(j).equals(faces.get(u).getVertices().get(v))) {
                                    if (faceSet.add(faces.get(u)) == true) {
                                        l2.add(faces.get(u));
                                        l4.add(faces.get(u));
                                        OK = true;
                                        faceIndex++;
                                    }
                                }
                            }
                        }
                    }
                }
                l1.clear();
                l1.addAll(l2);
                l2.clear();
            }
            List<FaceGeo> l3 = new ArrayList<>(faceSet);
            Collections.sort(l3, (FaceGeo solid2, FaceGeo solid1) -> solid2.getId() - solid1.getId());
            SolidGeo solid = new SolidGeo(solidIndex++, "building");
            result.add(solid);
            l4.forEach((f) -> {
                solid.add(f);
            });
            l4.clear();
            faceIndex++;
        }
        return result;
    }

    protected List<SolidGeo> setTopologyProperties(List<SolidGeo> solidWgs84List) {
        List<SolidGeo> result = new ArrayList<>();
        for (SolidGeo solid : solidWgs84List) {

            Set<Position> positions = new HashSet<>();
            Set<FaceGeo> faces = solid.getFaces();
            faces.forEach((f) -> {
                List<Point3DGeo> pts = f.getVertices();
                for (Point3DGeo p : pts) {
                    positions.add(new Position(Angle.fromDegrees(p.getLatitude()),
                            Angle.fromDegrees(p.getLongitude()), p.getElevation()));
                }
            });
            if (positions.size() > 2) {
                gov.nasa.worldwind.render.Polygon polygonWWJ = new gov.nasa.worldwind.render.Polygon(positions);
           
                Iterable<? extends Position> boundary = polygonWWJ.outerBoundary();
                List<Point3DGeo> points = new ArrayList<>();
                for (Position p : boundary) {
                    points.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), p.getElevation()));
                }
                Coordinate[] coordinates = jtsServices.toTabCoordinates(points);
              /*  
               for(Coordinate c : coordinates){
                   System.out.println("c : " + c);
               }
*/
                GeometryFactory fact = new GeometryFactory();
                Polygon ground = fact.createPolygon(coordinates);
                
                
                ConvexHull convex = new ConvexHull(ground);
                Geometry convexHull = convex.getConvexHull();
                
               // System.out.println("convexHull : " + convexHull);
                
                Point3DGeo centroid = jtsServices.toPoint3D(convexHull.getCentroid());

                solid.setGround(points);
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
            Logger.getLogger(ObjPaysbrestLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        if (tmp != null) {
            //File from BMO contains \ folows whith carriage return ?
            tmp = tmp.replaceAll("\\\\[\\n\\r]", "");
            try {
                Files.delete(path);
                path = Files.write(path, tmp.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(ObjPaysbrestLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return path;
    }
}
