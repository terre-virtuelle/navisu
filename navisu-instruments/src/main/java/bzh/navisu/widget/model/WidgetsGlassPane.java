/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.widget.model;

import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.JWindow;

/**
 *
 * @author Serge Morvan
 */
public class WidgetsGlassPane {

    private JWindow frame;
    private JFXPanel panel;
    private Group root;
    private Scene scene;
    private HBox hbWidgets;
   private int height;
   private int width;
    public WidgetsGlassPane(int height, int width) {
        this.height = height;
        this.width = width;
        frame = new JWindow();
        frame.setAlwaysOnTop(true);
        AWTUtilities.setWindowOpaque(frame, false);
        frame.setSize(height, width);
        frame.setLayout(new BorderLayout());
        panel = new JFXPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(height, width));
        createScene();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private void createScene() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root = new Group();
                scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                hbWidgets = new HBox();
                hbWidgets.setPrefHeight(310); 
                //root.getChildren().add(hbWidgets);
                BorderPane border = new BorderPane();
		border.setPrefSize(height, width);
		border.setBottom(hbWidgets);
                root.getChildren().add(border);
                panel.setScene(scene);
            }
        });
    }

    public HBox getHbWidgets() {
        return hbWidgets;
    }

    protected Group getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    protected JWindow getFrame() {
        return frame;
    }

    public JFXPanel getPanel() {
        return panel;
    }

    public void add(final Widget widget) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().addAll(widget);
            }
        });
    }

    public void remove(final Widget widget) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().remove(widget);
            }
        });
    }
}
