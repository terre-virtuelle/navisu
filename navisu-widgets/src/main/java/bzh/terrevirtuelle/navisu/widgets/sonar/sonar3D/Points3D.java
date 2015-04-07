package bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D;

import bzh.terrevirtuelle.navisu.domain.bathymetry.view.SHOM_BATHYMETRY_CLUT_JAVA_FX;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

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
        camera.setNearClip(0.001);//0.1
        camera.setFarClip(10000.0);//10000.0
        camera.setTranslateZ(-70);//-1000
        scene.setCamera(camera);

        Group pointsGroup = buildPoints(points);//100,100
        pointsGroup.setTranslateX(-6);//-400
        pointsGroup.setTranslateY(-4);
        pointsGroup.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
        pointsGroup.setRotate(120.0);
        //   pointsGroup.setTranslateZ(-z);
        //   System.out.println("x "+x+" "+y+" "+z);
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
        //Start Tracking mouse movements only when a button is pressed
        scene.setOnMousePressed(event -> {
            scenex = event.getSceneX();
            sceney = event.getSceneY();
            fixedXAngle = angleX.get();
            fixedYAngle = angleY.get();
        });

        scene.setOnKeyPressed(event -> {
            double change = cameraQuantity;
            //Add shift modifier to simulate "Running Speed"
            if (event.isShiftDown()) {
                change = cameraModifier;
            }
            //What key did the user press?
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
                camera.setTranslateX(camera.getTranslateX() - change);
            }
            if (keycode == KeyCode.D) {
                camera.setTranslateX(camera.getTranslateX() + change);
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
        //Step 4b:  Add a Point light to show specular highlights
        PointLight light = new PointLight(Color.WHITE);
        sceneRoot.getChildren().add(light);
        light.setTranslateZ(-sceneWidth / 2);
        light.setTranslateY(-sceneHeight / 2);

        primaryStage.setTitle("TriangleMeshes");
        primaryStage.setScene(scene);
        primaryStage.show();
        //End Step 1
    }

    private Group buildPoints(
            List<Point3D> points) {
        Group group = new Group();
        points.stream().map((p) -> {
            Sphere sphere = new Sphere(0.1);
            sphere.setTranslateX((p.getY() - minLon) * 1000);
            sphere.setTranslateY((p.getX() - minLat) * 1000);
            sphere.setTranslateZ(-p.getZ());
            sphere.setMaterial(new PhongMaterial(SHOM_BATHYMETRY_CLUT_JAVA_FX.getColor(p.getZ())));

            Tooltip t = new Tooltip(Double.toString(p.getZ()));
            Tooltip.install(sphere, t);
            System.out.println(sphere.getTranslateX() + "," + sphere.getTranslateY() + "," + sphere.getTranslateZ());
            return sphere;
        }).forEach((sphere) -> {
            group.getChildren().add(sphere);
        });
         System.out.println("\n\n\n");
        return group;
    }
}
