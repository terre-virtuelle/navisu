/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation;

import bzh.terrevirtuelle.navisu.core.util.BalloonController;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceShape;
import javax.swing.SwingUtilities;

/**
 * NaVisu Simple behavior for object whith a single point and for representation
 * a Placemark
 *
 * @date 13 oct. 2015
 * @author Serge Morvan
 */
public class S57BasicBehavior
        extends S57Behavior {

    private ShapeAttributes farAttributes;
    private ShapeAttributes middleAttributes;
    private ShapeAttributes nearAttributes;
    private ShapeAttributes highlightAttributes;

    public S57BasicBehavior() {
        init();
    }

    public S57BasicBehavior(S57Controller s57Controller) {
        super(s57Controller);
        init();
    }

    public final void init() {
        highlightAttributes = new BasicShapeAttributes();
        highlightAttributes.setInteriorMaterial(Material.GRAY);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setDrawOutline(true);
        highlightAttributes.setInteriorOpacity(0.4);

        farAttributes = new BasicShapeAttributes();
        farAttributes.setInteriorMaterial(Material.GREEN);
        farAttributes.setDrawOutline(false);
        farAttributes.setInteriorOpacity(0.1);

        middleAttributes = new BasicShapeAttributes();
        middleAttributes.setInteriorMaterial(Material.ORANGE);
        middleAttributes.setDrawOutline(false);
        middleAttributes.setInteriorOpacity(0.2);

        nearAttributes = new BasicShapeAttributes();
        nearAttributes.setInteriorMaterial(Material.RED);
       // nearAttributes.setDrawInterior(true);
        nearAttributes.setDrawOutline(false);
        nearAttributes.setInteriorOpacity(0.5);
    }

    @Override
    public void doIt(double distance, double azimuth) {
        SurfaceShape surveyZone = s57Controller.getSurveyZone();
       // PointPlacemark pointPlacemark = s57Controller.getPointPlacemark();
        double range = s57Controller.getRange();
        distance *= 1000;
       // surveyZone.setAttributes(nearAttributes);
        surveyZone.setHighlightAttributes(highlightAttributes);
        if (distance > range) {
            surveyZone.getAttributes().setDrawInterior(false);
            //   pointPlacemark.getAttributes().setScale(0.65);
            wwd.redrawNow();
        } else if (distance <= range && distance > range / 2.0) {
            surveyZone.setAttributes(farAttributes);
            surveyZone.getAttributes().setDrawInterior(true);
            //  pointPlacemark.getAttributes().setScale(0.7);
            wwd.redrawNow();

        } else if (distance <= range / 2.0 && distance > range / 4.0) {
            surveyZone.setAttributes(middleAttributes);
            surveyZone.getAttributes().setDrawInterior(true);
            //   pointPlacemark.getAttributes().setScale(0.9);
            wwd.redrawNow();
        } else if (distance <= range / 4.0) {
            surveyZone.setAttributes(nearAttributes);
            surveyZone.getAttributes().setDrawInterior(true);
            // pointPlacemark.getAttributes().setScale(1.5);
            wwd.redrawNow();
        }
    }
}
