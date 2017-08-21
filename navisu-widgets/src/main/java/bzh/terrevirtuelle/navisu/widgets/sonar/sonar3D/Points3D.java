package bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D;

import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_BATHYMETRY_CLUT_JAVA_FX;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

import org.jdelaunay.delaunay.ConstrainedMesh;
import org.jdelaunay.delaunay.error.DelaunayError;
import org.jdelaunay.delaunay.geometries.DPoint;
import org.jdelaunay.delaunay.geometries.DTriangle;

/**
 *
 * @author sphillips
 */
public class Points3D extends Group {

    private final PerspectiveCamera camera;
    private final double sceneWidth = 600;
    private final double sceneHeight = 600;
    private double scenex, sceney = 0;
    private double fixedXAngle, fixedYAngle = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    Group sceneRoot;
    private final double cameraModifier = 50.0;
    private final double cameraQuantity = 10.0;
    private final double mouseXold = 0;
    private final double mouseYold = 0;
    private final double cameraYlimit = 1500;
    private final double rotateModifier = 10;
    double minLat;
    double maxLat;
    double minLon;
    double maxLon;
    double minElevation;
    double maxElevation;
    double centerX;
    double centerY;
    double centerZ;

    public Points3D(Stage primaryStage, List<Point3D> points,
            double minLat, double maxLat,
            double minLon, double maxLon,
            double minElevation, double maxElevation) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.minElevation = minElevation;
        this.maxElevation = maxElevation;
        sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight);
        scene.setFill(Color.BLACK);
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.0001);//0.1
        camera.setFarClip(10000.0);//10000.0
        camera.setTranslateZ(-1000);//-1000
        scene.setCamera(camera);
        
        AmbientLight light = new AmbientLight();
        light.setColor(Color.WHITE);
       // Group pointsGroup = buildPoints(points);//100,100
        Group pointsGroup = buildDelaunay(delaunay(points));

        pointsGroup.setTranslateX(centerY);
        pointsGroup.setTranslateY(-4-centerX/10);//-4
        pointsGroup.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
        pointsGroup.setRotate(100.0);

        sceneRoot.getChildren().addAll(pointsGroup);
        scene.setOnMouseClicked(event -> {
            Node picked = event.getPickResult().getIntersectedNode();
            if (null != picked) {
                double scalar = 2;
                if (picked.getScaleX() > 1) {
                    scalar = 1;
                }
                picked.setScaleX(scalar);
                picked.setScaleY(scalar);
                picked.setScaleZ(scalar);
            }
        });

        scene.setOnMousePressed(event -> {
            scenex = event.getSceneX();
            sceney = event.getSceneY();
            fixedXAngle = angleX.get();
            fixedYAngle = angleY.get();
        });

        scene.setOnKeyPressed(event -> {
            double change = cameraQuantity;
            KeyCode keycode = event.getCode();
            //Step 2c: Add Zoom controls
            if (keycode == KeyCode.W) {
                camera.setTranslateZ(camera.getTranslateZ() + change);
            }
            if (keycode == KeyCode.S) {
                camera.setTranslateZ(camera.getTranslateZ() - change);
            }
            //Step 2d:  Add Strafe controls
            if (keycode == KeyCode.A) {
                pointsGroup.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
                pointsGroup.setRotate(pointsGroup.getRotate() + rotateModifier);
            }
            if (keycode == KeyCode.D) {
                pointsGroup.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
                pointsGroup.setRotate(pointsGroup.getRotate() - rotateModifier);
            }
            if (keycode == KeyCode.R) {
                pointsGroup.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
                pointsGroup.setRotate(pointsGroup.getRotate() + rotateModifier);
            }
            if (keycode == KeyCode.T) {
                pointsGroup.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
                pointsGroup.setRotate(pointsGroup.getRotate() - rotateModifier);
            }

        });

        sceneRoot.getChildren().add(light);

        primaryStage.setTitle("TriangleMeshes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Group buildPoints(
            List<Point3D> points) {
        Group group = new Group();
        points.stream().map((p) -> {
            Sphere sphere = new Sphere(0.1);
            sphere.setTranslateX((p.getY() - minLon) * 1000);
            sphere.setTranslateY((p.getX() - minLat) * 1000);
            sphere.setTranslateZ(p.getZ());
            
            sphere.setMaterial(new PhongMaterial(SHOM_BATHYMETRY_CLUT_JAVA_FX.getColor(p.getZ(), false)));

            Tooltip t = new Tooltip(Double.toString(p.getZ()));
            Tooltip.install(sphere, t);
            return sphere;
        }).forEach((sphere) -> {
            group.getChildren().add(sphere);
        });
        return group;
    }

    private Group buildDelaunay(List<List<Point3D>> triangles) {
        Group group = new Group();
        TriangleMesh mesh = new TriangleMesh();
        triangles.stream().forEach((subList) -> {
            subList.stream().forEach((p) -> {
                mesh.getPoints().addAll((float) (p.getY() - minLon) * 1000,
                        (float) (p.getX() - minLat) * 1000,
                        (float) p.getZ());
                
            });
        });

        mesh.getTexCoords().addAll(0, 0);
        int k = 0;
        int size = triangles.size();
        for (int i = 0; i < size; i++) {
            for (int j = k; j < k + 3; j++) {
                mesh.getFaces().addAll(i + j, 0);
            }
            k += 2;
        }
        MeshView meshView = new MeshView(mesh);
        meshView.setDrawMode(DrawMode.FILL);
        meshView.setCullFace(CullFace.NONE);
       
        meshView.setMaterial(new PhongMaterial(SHOM_BATHYMETRY_CLUT_JAVA_FX.getColor(centerZ, false)));
        group.getChildren().add(meshView);
        return group;

    }

    private List<List<Point3D>> delaunay(List<Point3D> points) {
        List<List<Point3D>> list = new ArrayList<>();
        List<DPoint> dpList = new ArrayList<>();
        ConstrainedMesh aMesh = new ConstrainedMesh();
        aMesh.setPrecision(1.0e-3);
        aMesh.setVerbose(true);
        try {
            for (Point3D p : points) {
                dpList.add(new DPoint(p.getX(), p.getY(), p.getZ()));
            }
            aMesh.setPoints(dpList);
            aMesh.processDelaunay();
            List<DTriangle> triangles = aMesh.getTriangleList();
            triangles.stream().filter((t) -> (t.checkTopology())).map((t) -> {
                List<Point3D> l = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    l.add(new Point3D(t.getPoint(i).getX(),
                            t.getPoint(i).getY(),
                            t.getPoint(i).getZ()));
                }
                return l;
            }).forEach((l) -> {
                list.add(l);
            });
            centerX = aMesh.getBoundingBox().getMiddle().getX();
            centerY = aMesh.getBoundingBox().getMiddle().getY();
            centerZ = aMesh.getBoundingBox().getMiddle().getZ();

            System.out.println(centerX + " " + centerY + "  " + centerZ);

        } catch (DelaunayError ex) {
            Logger.getLogger(Points3D.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        return list;
    }
}
