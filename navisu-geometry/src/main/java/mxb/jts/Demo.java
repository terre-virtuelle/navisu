/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mxb.jts;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;

import java.awt.Color;

import javax.swing.JOptionPane;


import mxb.jts.triangulate.EarClipper;
import mxb.jts.triangulate.TestPolygonProvider;


/**
 * Demonstrates brute force approach to the ear clipping algorithm
 * to triangulate a polygon.
 *
 * This version attempts a general approach to holes.
 *
 * @author Michael Bedward
 */
public class Demo {

    /**
     * Demonstrate the ear-clipping algorithm
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String polyName = (String) JOptionPane.showInputDialog(null,
                "Select a polygon to triangulate",
                "Triangulation demo",
                JOptionPane.QUESTION_MESSAGE, null,
                TestPolygonProvider.getNames().toArray(), "house with diamond hole");

        if (polyName != null) {
            Polygon poly = TestPolygonProvider.getPolygon(polyName);
            EarClipper clipper = new EarClipper(poly);
            Geometry ears = clipper.getResult();

            ResultFrame frame = new ResultFrame("Triangulation");
            for (int i = 0; i < ears.getNumGeometries(); i++) {
                Polygon ear = (Polygon) ears.getGeometryN(i);
                frame.addPolygon(ear, Color.RED);
            }
            frame.addPolygon(poly, Color.BLUE);
            frame.setSize(500, 500);
            frame.setVisible(true);
        }
    }

}
