/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view.impl.controller;

/**
 *
 * @author serge
 * @date May 31, 2019
 */
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;

/**
 *
 * @author Serge modifs Dom : variables public
 */
public class JfxViewer
        extends Widget2DController
        implements Initializable {

    // 900x600
    @FXML
    public Group viewerGroup;
    @FXML
    public Group displayGroup;
    @FXML
    public Pane viewPane;
    @FXML
    public Button quitButton;
    @FXML
    public Slider opacitySlider;
    @FXML
    public Button buttonsInfos;

    protected GuiAgentServices guiAgentServices;
    protected Stage stage;
    protected String viewgroupstyle = "common.css";
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    Scene scene;
    Shape shape;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    public JfxViewer(GuiAgentServices guiAgentServices) {

        this.guiAgentServices = guiAgentServices;
        this.stage = guiAgentServices.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JfxViewer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(JfxViewer.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        String uri = CSS_STYLE_PATH + viewgroupstyle;
        viewerGroup.getStylesheets().add(uri);
        viewPane.setOpacity(0.8);

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public final void display() {
        shape = createStar();
        display(shape);
    }

    public final void display(Shape shape) {
        this.displayGroup.getChildren().add(shape);
    }

    public final void display(List<SVGPath> shapes) {
        for (Shape shape : shapes) {
            this.displayGroup.getChildren().add(shape);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quitButton.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);

        });

        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                if (opacitySlider.getValue() > 0.1) {
                    viewPane.setOpacity(opacitySlider.getValue());
                }
            });
        });
    }

    private SVGPath createStar() {
        SVGPath star = new SVGPath();
        star.setContent("M100,10 L100,10 40,180 190,60 10,60 160,180 z");
        star.setStrokeLineJoin(StrokeLineJoin.ROUND);
        star.setStroke(Color.RED);
        // star.setFill(Color.RED);
        star.setStrokeWidth(4);
        star.setOpacity(1.0);
        return star;
    }

}
