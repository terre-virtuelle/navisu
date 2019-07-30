/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;

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
    protected int color = 0;
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
    /*
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
                    int index = 1;
                    for (FaceGeo f : facesWgs84) {
                        f.setId(index++);
                    }
                    List<SolidGeo> solidWgs84List = agregate(facesWgs84);
                    System.out.println("solidWgs84 : " + solidWgs84List.size());
                    instrumentDriverManagerServices.open(DATA_PATH + ALARM_SOUND, "true", "1");
                    List<SolidGeo> valid = setTopologyProperties(solidWgs84List);

                    Material[] materials = {Material.GREEN, Material.BLUE, Material.YELLOW, Material.PINK,
                        Material.CYAN, Material.MAGENTA, Material.ORANGE, Material.RED};
                    for (SolidGeo solid : valid) {
                        displayServices.displaySolidGeoAsPolygon(solid, 0.0, layer, materials[color++ % 8]);
                    }
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
        return faceWgs84;
    }

    private List<SolidGeo> agregate(List<FaceGeo> faces) {
        System.out.println("faces : " + faces.size());
        List<SolidGeo> result = new ArrayList<>();

        int solidIndex = 0;
        int faceIndex = 0;
        boolean OK = false;
        Set<FaceGeo> faceSet = new HashSet<>();
        int i = 0;
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
            OK = true;
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
        int c = 1;
        List<SolidGeo> result = new ArrayList<>();
        for (SolidGeo solid : solidWgs84List) {
            List<Point3DGeo> points = new ArrayList<>();
            Set<FaceGeo> faces = solid.getFaces();
            for (FaceGeo f : faces) {
                points.addAll(f.getVertices());
            }
            Coordinate[] coordinates = jtsServices.toTabCoordinates(points);
            ConvexHull convexhull = new ConvexHull(coordinates, new GeometryFactory());
            Geometry geom = convexhull.getConvexHull();
            gov.nasa.worldwind.render.Path path = jtsServices.getPathFromPolygon(geom);
            displayServices.displayPath(path, 20, layer, Material.WHITE);
            if (geom.getArea() != 0) {
                Point3DGeo centroid = jtsServices.toPoint3D(geom.getCentroid());
                result.add(solid);
                solid.setCentroid(centroid);
            }
        }
        return result;
    }

    private Path filter(File file) {
        String tmp = null;
        Path path = null;
        try {
            tmp = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        } catch (IOException ex) {
            Logger.getLogger(DaeExportToSTL.class
                    .getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        if (tmp != null) {
            //File from BMO contains \ folows whith carriage return ?
            tmp = tmp.replaceAll("\\\\[\\n\\r]", "");
            try {
                Files.delete(file.toPath());
                path = Files.write(file.toPath(), tmp.getBytes(), StandardOpenOption.CREATE);

            } catch (IOException ex) {
                Logger.getLogger(ObjExportToSTL.class
                        .getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return path;
    }
*/
}
