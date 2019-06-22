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
import bzh.terrevirtuelle.navisu.domain.svg.SVGPath3D;
import bzh.terrevirtuelle.navisu.util.collections.CircularQueue;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    public StackPane stackPane;
    @FXML
    public Button quitButton;
    @FXML
    public Slider opacitySlider;
    @FXML
    public Button buttonsInfos;
    @FXML
    public Button layerButton;
    @FXML
    public Label drval1Label;
    @FXML
    public Label drval2Label;

    protected GuiAgentServices guiAgentServices;
    protected Stage stage;
    protected String viewgroupstyle = "common.css";
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    protected Scene scene;
    protected Shape shape;
    protected Group originGroup = new Group();
    protected double orgSceneX, orgSceneY;
    protected double orgTranslateX, orgTranslateY;
    protected List<SVGPath3D> shapes;
    protected List<SVGPath3D> shapeList = new ArrayList<>();
    protected CircularQueue<List<SVGPath3D>> circularQueue;
    protected int circularQueueSize = 0;
    protected Map<Double, List<SVGPath3D>> svgMap;
int i = 1;
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
        this.stackPane.getChildren().add(originGroup);

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
        layerButton.setOnMouseClicked((MouseEvent event) -> {
            changeTop();
        });
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
        this.stackPane.getChildren().add(shape);
    }

    public final void display(List<SVGPath3D> shapes) {

        this.shapes = shapes;
        for (Shape s : shapes) {
            originGroup.getChildren().add(s);
        }
    }

    public final void display(Map<Double, List<SVGPath3D>> svgMap) {
        this.svgMap = svgMap;
        Collection<List<SVGPath3D>> collShapesList = svgMap.values();
        collShapesList.forEach((l) -> {
            shapeList.addAll(l);
        });
        sort(svgMap);
        originGroup.getChildren().addAll(circularQueue.dequeue());
        drval1Label.setText("All depths");
    }

    private CircularQueue<List<SVGPath3D>> sort(Map<Double, List<SVGPath3D>> svgMap) {
        List<Double> keyList = new ArrayList<>();
        keyList.addAll(svgMap.keySet());
        keyList.sort(Comparator.naturalOrder());
        circularQueueSize = keyList.size() + 1;
        circularQueue = new CircularQueue(circularQueueSize);
        circularQueue.enqueue(shapeList);
        for (double key : keyList) {
            circularQueue.enqueue(svgMap.get(key));
        }
        return circularQueue;
    }

    private void changeTop() {
        
        circularQueue.enqueue(shapeList);
        originGroup.getChildren().removeAll(shapeList);
        shapeList = circularQueue.dequeue();
        if ((i / circularQueueSize) == 0) {
            Double drval1 = shapeList.get(0).getValues().get("drval1");
            Double drval2 = shapeList.get(0).getValues().get("drval2");
            drval1Label.setText(Double.toString(drval1));
            drval2Label.setText(Double.toString(drval2));
            i++;
        } else {
            drval1Label.setText("All depths");
            drval2Label.setText("");
            i = 1;
        }
        originGroup.getChildren().addAll(shapeList);
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
