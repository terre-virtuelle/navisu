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
   
}
