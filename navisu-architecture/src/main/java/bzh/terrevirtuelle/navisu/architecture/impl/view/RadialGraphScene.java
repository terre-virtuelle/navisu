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
package bzh.terrevirtuelle.navisu.architecture.impl.view;

import org.netbeans.api.visual.action.*;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.border.Border;

import java.awt.*;

/**
 * @author David Kaspar
 */
public class RadialGraphScene extends GraphScene<String, String> {

    private LayerWidget nodesLayer = new LayerWidget (this);
    private LayerWidget edgesLayer = new LayerWidget (this);

    private Border lineBorder = BorderFactory.createLineBorder ();
    private WidgetAction hoverAction = ActionFactory.createHoverAction (new MyHoverProvider ());
    private WidgetAction moveAction = ActionFactory.createMoveAction ();
    private WidgetAction zoomAction = ActionFactory.createZoomAction ();
    private WidgetAction panAction = ActionFactory.createPanAction ();

    public RadialGraphScene () {
        addChild (nodesLayer);
        addChild (edgesLayer);
        getActions ().addAction (hoverAction);
        getActions ().addAction (moveAction);
        getActions ().addAction (zoomAction);
        getActions ().addAction (panAction);
    }

    protected Widget attachNodeWidget (String node) {
        LabelWidget label = new LabelWidget (this);
        label.setBorder (lineBorder);
        label.setCheckClipping (true);
        label.setLabel (node);
        label.setOpaque (true);
        label.getActions ().addAction (hoverAction);
        label.getActions ().addAction (moveAction);
        nodesLayer.addChild (label);
        return label;
    }

    protected Widget attachEdgeWidget (String edge) {
        ConnectionWidget connection = new ConnectionWidget (this);
        connection.setCheckClipping (true);
        edgesLayer.addChild (connection);
        return connection;
    }

    protected void attachEdgeSourceAnchor (String edge, String oldSourceNode, String sourceNode) {
        ConnectionWidget widget = (ConnectionWidget) findWidget (edge);
        widget.setSourceAnchor (AnchorFactory.createRectangularAnchor (findWidget (sourceNode)));
    }

    protected void attachEdgeTargetAnchor (String edge, String oldTargetNode, String targetNode) {
        ConnectionWidget widget = (ConnectionWidget) findWidget (edge);
        widget.setTargetAnchor (AnchorFactory.createRectangularAnchor (findWidget (targetNode)));
    }

    private static class MyHoverProvider implements TwoStateHoverProvider {

        public void unsetHovering (Widget widget) {
            widget.setBackground (Color.WHITE);
        }

        public void setHovering (Widget widget) {
            widget.setBackground (Color.GREEN);
        }

    }

}
