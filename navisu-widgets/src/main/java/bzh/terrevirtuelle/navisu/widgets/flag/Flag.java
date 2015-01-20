/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.flag;

import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import bzh.org.fxyz.cameras.CameraTransformer;
import bzh.org.fxyz.shapes.complex.cloth.ClothMesh;
import javafx.scene.Group;

/**
 *
 * @author Serge
 */
public class Flag extends Group {

    private PerspectiveCamera camera;
    private final CameraTransformer cameraTransform = new CameraTransformer();
    ClothMesh cloth;

    public Flag() {
        camera = new PerspectiveCamera(true);
        cameraTransform.setTranslate(0, 0, 0);
        cameraTransform.getChildren().addAll(camera);
        camera.setNearClip(0.1);
        camera.setFarClip(1000000.0);
        camera.setFieldOfView(42);
        camera.setVerticalFieldOfView(true);
        camera.setTranslateZ(-1500);

        cloth = new ClothMesh(25,10);
        cloth.setPerPointMass(10);
        cloth.setBendStrength(0.5);//.5
        cloth.setStretchStrength(0.1);//1.0
        cloth.setShearStrength(0.1);
        cloth.setDrawMode(DrawMode.FILL);
        cloth.setCullFace(CullFace.NONE);
        cloth.setDiffuseMap(new Image(getClass().getResourceAsStream("flag.png")));
        cloth.setSpecularPower(5);

        PointLight light2 = new PointLight(Color.GAINSBORO);
        light2.setTranslateZ(-1500);
        PointLight light3 = new PointLight(Color.AZURE);
        light3.setTranslateZ(2500);
        getChildren().addAll(cameraTransform, cloth, light2, light3);
        // initEvt();
    }

    public void startSimulation() {
        cloth.startSimulation();
    }

    public void stopSimulation() {
        cloth.stopSimulation();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

}
