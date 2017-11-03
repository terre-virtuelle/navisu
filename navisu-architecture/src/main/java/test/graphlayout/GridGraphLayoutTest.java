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
package test.graphlayout;

import java.awt.Point;
import org.netbeans.api.visual.graph.layout.GridGraphLayout;
import org.netbeans.api.visual.graph.layout.GraphLayoutListener;
import org.netbeans.api.visual.graph.layout.UniversalGraph;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.layout.SceneLayout;
import test.SceneSupport;
import test.general.StringGraphScene;


/**
 * @author David Kaspar
 */
public class GridGraphLayoutTest extends StringGraphScene {

    private int edgeID = 1;

    public GridGraphLayoutTest () {
        initializeGraph ();

        GridGraphLayout<String, String> graphLayout = new GridGraphLayout<String, String> ();
        graphLayout.addGraphLayoutListener (new MyListener ());

        SceneLayout sceneGraphLayout = LayoutFactory.createSceneGraphLayout (this, graphLayout);
        sceneGraphLayout.invokeLayout ();
    }

    private void addEdge (String sourceNode, String targetNode) {
        String id = "edge" + (edgeID ++);
        addEdge (id);
        setEdgeSource (id, sourceNode);
        setEdgeTarget (id, targetNode);
    }

    private void initializeGraph () {
        addNode ("1");
        addNode ("2");
        addNode ("3");
        addNode ("4");
        addNode ("5");
        addNode ("6");
        addNode ("7");

        addEdge ("1", "2");
        addEdge ("1", "3");
        addEdge ("1", "4");
        addEdge ("2", "4");
        addEdge ("3", "5");
        addEdge ("2", "5");
        addEdge ("3", "6");
        addEdge ("3", "4");
    }

    public static void main (String[] args) {
        SceneSupport.show (new GridGraphLayoutTest ());
    }

    private static class MyListener implements GraphLayoutListener<String, String> {

        @Override
        public void graphLayoutStarted (UniversalGraph<String, String> graph) {
            System.out.println ("Layout started");
        }

        @Override
        public void graphLayoutFinished (UniversalGraph<String, String> graph) {
            System.out.println ("Layout finished");
        }

        @Override
        public void nodeLocationChanged (UniversalGraph<String, String> graph, String node, Point previousPreferredLocation, Point newPreferredLocation) {
            System.out.println ("Node location changed: " + node + " -> " + newPreferredLocation);
        }

    }

}
