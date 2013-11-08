/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.widget.view.buoy;

import com.javafx.magnifier.control.MagnifierPane;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import org.navisu.widget.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class BuoyWidget
        extends Widget {

    private ImageView background;
    private Button button;
    private Robot robot;
BufferedImage screenShot;
Image image;
ImageView sample1;
    public BuoyWidget() {
        createScene();
    }

    @Override
    protected final void createScene() {
        background = ImageViewBuilder.create()
                .image(new Image(getClass().getResourceAsStream("resources/images/cardinalWest.png")))
                .build();
        getChildren().add(background);
        button = ButtonBuilder.create()
                .text("Widget Test")
                .layoutX(55)
                .layoutY(80)
                .prefWidth(100)
                .prefHeight(15)
                .build();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Button");
            }
        });
        getChildren().add(button);
    }

    @Override
    protected void initEvt() {
        super.initEvt();
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me != null && dragAnchor != null) {
                    setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                    setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
                    try {
                        BuoyWidget.this.robot = new Robot();
                        BuoyWidget.this.screenShot =
                                robot.createScreenCapture(new Rectangle((int) me.getSceneX(), (int) me.getSceneY(),
                                (int) me.getSceneX() + 10, (int) me.getSceneX() + 10));
                        // ImageIO.write(screenhot, "png", new File("screenShot" + System.currentTimeMillis()+".png"));

                        BuoyWidget.this.image = SwingFXUtils.toFXImage(screenShot, null);
                        BuoyWidget.this.sample1 = new ImageView(image);
                        configureSample(sample1);
                        // Thread.sleep(100);
                    } catch (AWTException e) {
                        System.out.println("e  " + e);
                    }
                }

            }
        });


    }

    private void configureSample(Node sample) {
        MagnifierPane p = new MagnifierPane();
        
        // p.radiusProperty().bind(40);
        // p.frameWidthProperty().bind(frameWidth);
        // p.scaleFactorProperty().bind(scaleFactor);
        // p.scopeLineWidthProperty().bind(scopeLineWidth);
        //  p.scopeLinesVisibleProperty().bind(scopeLinesVisible);
        p.getChildren().add(sample);
      
      //  StackPane c = StackPaneBuilder.create().padding(new Insets(20)).build();
      //  c.getChildren().add(p);
        getChildren().add(p);
    }
}
