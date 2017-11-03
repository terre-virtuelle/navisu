/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package test.general;

import java.awt.Color;
import java.awt.Point;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import test.SceneSupport;

import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 * @author David Kaspar
 */
public class GraphSceneTest {

    public static void main(String[] args) {
        //  testGraphScene ();
        // testConnectionWidget();
        testUMLClassWidget();
        // testGraphPinScene();
    }

    public static void testLabelWidget() {
        Scene scene = new Scene();
        scene.setBackground(Color.BLUE);

        LabelWidget label = new LabelWidget(scene);
        label.setOpaque(true);
        label.setBackground(Color.GREEN);
        label.setLabel("ABCD");
        scene.addChild(label);

        SceneSupport.show(scene);
    }

    public static void testUMLClassWidget() {
        StringGraphScene scene = new StringGraphScene();

        for (int a = 1; a <= 3; a++) {
            scene.addNode(String.valueOf(a)).setPreferredLocation(new Point(a * 20, a * 20));  
        }

        SceneSupport.show(scene);
    }

    private static void createConnection(StringGraphScene scene, String edgeID, String nodeID1, String nodeID2) {
        scene.addEdge(edgeID);
        scene.setEdgeSource(edgeID, nodeID1);
        scene.setEdgeTarget(edgeID, nodeID2);
    }

    public static void testConnectionWidget() {
        StringGraphScene scene = new StringGraphScene();

        scene.addNode("1").setPreferredLocation(new Point(100, 100));
        scene.addNode("2").setPreferredLocation(new Point(400, 400));
        scene.addNode("3").setPreferredLocation(new Point(500, 100));

        createConnection(scene, "A", "1", "2");
        createConnection(scene, "B", "2", "3");
        createConnection(scene, "C", "3", "1");

        SceneSupport.show(scene);
    }

    public static void testConnectionAnimation() {
        StringGraphScene scene = new StringGraphScene();

        for (int a = 0; a < 3; a++) {
            String n1 = "A" + a;
            scene.addNode(n1).setPreferredLocation(new Point(100, 100));
            String n2 = "B" + a;
            scene.addNode(n2).setPreferredLocation(new Point(400, 400));

            createConnection(scene, "C" + a, n1, n2);
        }

        startAnimation(scene, scene.getMainLayer(), 100);
        SceneSupport.show(scene);
    }

    public static void testAnimation() {
        StringGraphScene scene = new StringGraphScene();

        for (int a = 1; a <= 100; a++) {
            scene.addNode(String.valueOf(a)).setPreferredLocation(new Point(a * 10, a * 10));
        }

        startAnimation(scene, scene.getMainLayer(), 0);
        SceneSupport.show(scene);
    }

    public static void testAddRemove() {
        StringGraphScene scene = new StringGraphScene();

        startAddRemove(scene, 500);
        SceneSupport.show(scene);
    }

    public static void testGraphScene() {
        final StringGraphScene scene = new StringGraphScene();

        String previousNodeID = null;
        for (int a = 0; a < 10; a++) {
            String nodeID = "node" + String.valueOf(a);
            scene.addNode(nodeID).setPreferredLocation(new Point(SceneSupport.randInt(1000), SceneSupport.randInt(1000)));
            if (previousNodeID != null) {
                String edgeID = "edge" + String.valueOf(a);
                scene.addEdge(edgeID);
                scene.setEdgeSource(edgeID, previousNodeID);
                scene.setEdgeTarget(edgeID, nodeID);
            }
            previousNodeID = nodeID;
        }

        SceneSupport.show(scene);
    }

    public static void testGraphPinScene() {
        final StringGraphPinScene scene = new StringGraphPinScene();

        String rootNode = "Root";
        scene.addNode(rootNode).setPreferredLocation(new Point(30, 500));

        for (int a = 0; a < 10; a++) {
            String rootPin = "+Pin" + a;
            scene.addPin(rootNode, rootPin);

            String childNode = "Child" + a;
            scene.addNode(childNode).setPreferredLocation(new Point(500, a * 100));

            String childPin = "-Pin" + a;
            scene.addPin(childNode, childPin);

            String edge = "edge" + String.valueOf(a);
            scene.addEdge(edge);
            scene.setEdgeSource(edge, rootPin);
            scene.setEdgeTarget(edge, childPin);
        }

        SceneSupport.show(scene);
    }

    public static void startAnimation(Scene scene, Widget widget, int delay) {
        SwingUtilities.invokeLater(new Animation(scene, widget, delay));
    }

    static class Animation implements Runnable {

        private Scene scene;
        private Widget widget;
        private int delay;

        public Animation(Scene scene, Widget widget, int delay) {
            this.scene = scene;
            this.widget = widget;
            this.delay = delay;
        }

        public void run() {
            Collection<Widget> children = widget.getChildren();
//            Widget child = children.iterator ().next ();
            for (Widget child : children) {
                child.setPreferredLocation(new Point(SceneSupport.randInt(1000), SceneSupport.randInt(1000)));
            }
            scene.validate();

            SceneSupport.invokeLater(this, delay);
        }

    }

    private static void startAddRemove(StringGraphScene scene, int delay) {
        SwingUtilities.invokeLater(new AddRemove(scene, delay));
    }

    private static class AddRemove implements Runnable {

        private StringGraphScene scene;
        private int delay;
        private String node;

        public AddRemove(StringGraphScene scene, int delay) {
            this.scene = scene;
            this.delay = delay;
            this.node = null;
        }

        public void run() {
            if (node == null) {
                node = "Node";
                scene.addNode(node).setPreferredLocation(new Point(SceneSupport.randInt(1000), SceneSupport.randInt(1000)));
            } else {
                scene.removeNode(node);
                node = null;
            }
            scene.validate();
            SceneSupport.invokeLater(this, delay);
        }

    }

}
