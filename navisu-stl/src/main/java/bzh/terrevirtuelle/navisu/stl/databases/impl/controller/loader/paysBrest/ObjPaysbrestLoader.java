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
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println(f);System.out.println("--");System.out.println(f.printInv());       }
        List<SolidGeo> solidWgs84List = agregate(facesWgs84);
        System.out.println("solidWgs84List : " + solidWgs84List.size());
        System.out.println("faces : " + solidWgs84List.get(0).getFaces().size());

        solidGeoList = setTopologyProperties(solidWgs84List);
        instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
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
        List<SolidGeo> result = new ArrayList<>();
        int faceIndex = 0;
        int solidIndex = 0;
        List<FaceGeo> faceSet = new ArrayList<>();
        List<EdgeGeo> ground = new ArrayList<>();
        FaceGeo startFace;
        FaceGeo shuttle;
        FaceGeo last;

        for (int i = 0; i < faces.size(); i++) {
            startFace = faces.get(0);
            shuttle = faces.get(0);
            last = faces.get(1);
            faceSet.add(shuttle);
            ground.add(shuttle.getGround());
            for (int j = 0; j < faces.size(); j++) {
                if (!shuttle.equals(last) && (shuttle.isAdjacent(startFace) || last.isAdjacent(shuttle))) {
                    if (!faceSet.contains(shuttle)) {
                        faceSet.add(shuttle);
                        ground.add(shuttle.getGround());
                    }
                    last = shuttle;
                    shuttle = faces.get(0);
                } else {
                    shuttle = faces.get(j);
                }
            }
        }
        SolidGeo s = new SolidGeo(faceSet);
        s.setId(solidIndex);
        //s.setGround(ground);
        result.add(s);
        // faceSet.clear();

        //for (FaceGeo f : faceSet) {
       //     System.out.println("faceSet  : " + f.getId());
       // }
        return result;

    }

    protected List<SolidGeo> setTopologyProperties(List<SolidGeo> solidWgs84List) {

        List<SolidGeo> result = new ArrayList<>();
        for (SolidGeo solid : solidWgs84List) {
            List<Position> positions = new ArrayList<>();
            List<FaceGeo> faces = solid.getFaces();
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
                    points.add(new Point3DGeo(p.getLatitude().getDegrees(), p.getLongitude().getDegrees(), 0.0));
                }

                Coordinate[] coordinates = jtsServices.toTabCoordinates(points);
                GeometryFactory fact = new GeometryFactory();
                Polygon ground = fact.createPolygon(coordinates);

                Point3DGeo centroid = jtsServices.toPoint3D(ground.getCentroid());
                gov.nasa.worldwind.render.Path path = jtsServices.getPathFromPolygon(ground);

                solid.setGroundGeom(ground);
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
