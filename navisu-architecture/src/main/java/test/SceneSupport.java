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
package test;

import org.netbeans.api.visual.widget.Scene;
import org.openide.util.RequestProcessor;

import javax.swing.*;
import java.awt.*;

/**
 * @author David Kaspar
 */
public class SceneSupport {

    public static void show (final Scene scene) {
        if (SwingUtilities.isEventDispatchThread ())
            showEDT (scene);
        else
            SwingUtilities.invokeLater (new Runnable() {
                public void run () {
                    showEDT (scene);
                }
            });
    }

    private static void showEDT (Scene scene) {
        JComponent sceneView = scene.getView ();
        if (sceneView == null)
            sceneView = scene.createView ();
        show (sceneView);
    }

    public static void show (final JComponent sceneView) {
        if (SwingUtilities.isEventDispatchThread ())
            showEDT (sceneView);
        else
            SwingUtilities.invokeLater (new Runnable() {
                public void run () {
                    showEDT (sceneView);
                }
            });
    }

    private static void showEDT (JComponent sceneView) {
        JScrollPane panel = new JScrollPane (sceneView);
        panel.getHorizontalScrollBar ().setUnitIncrement (32);
        panel.getHorizontalScrollBar ().setBlockIncrement (256);
        panel.getVerticalScrollBar ().setUnitIncrement (32);
        panel.getVerticalScrollBar ().setBlockIncrement (256);
        showCoreEDT (panel);
    }

    public static void showCore (final JComponent view) {
        if (SwingUtilities.isEventDispatchThread ())
            showCoreEDT (view);
        else
            SwingUtilities.invokeLater (new Runnable() {
                public void run () {
                    showCoreEDT (view);
                }
            });
    }

    private static void showCoreEDT (JComponent view) {
        int width=800,height=600;
        JFrame frame = new JFrame ();//new JDialog (), true);
        frame.add (view, BorderLayout.CENTER);
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
        frame.setVisible (true);
    }

    public static int randInt (int max) {
        return (int) (Math.random () * max);
    }

    public static void invokeLater (final Runnable runnable, int delay) {
        RequestProcessor.getDefault ().post (new Runnable() {
            public void run () {
                SwingUtilities.invokeLater (runnable);
            }
        }, delay);
    }

    public static void sleep (int delay) {
        try {
            Thread.sleep (delay);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

}
