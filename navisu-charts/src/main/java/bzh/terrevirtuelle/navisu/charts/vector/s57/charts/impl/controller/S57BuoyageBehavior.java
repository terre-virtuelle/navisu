/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57Behavior;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 * NaVisu
 *
 * @date 13 oct. 2015
 * @author Serge Morvan
 */
public class S57BuoyageBehavior
        extends S57Behavior {

    protected WorldWindow wwd;
    private ShapeAttributes farAttributes;
    private ShapeAttributes middleAttributes;
    private ShapeAttributes nearAttributes;
    private ShapeAttributes highlightAttributes;
    Buoyage buoyage;

    public S57BuoyageBehavior(double distance) {
        this.range = distance;
        wwd = GeoWorldWindViewImpl.getWW();
    }

    public void init(S57BuoyageController buoyageController) {
        s57Controller = buoyageController;
        buoyage = (Buoyage) s57Controller.getNavigationData();

        highlightAttributes = new BasicShapeAttributes();
        highlightAttributes.setInteriorMaterial(Material.GRAY);
        highlightAttributes.setDrawInterior(true);
        highlightAttributes.setDrawOutline(false);
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
        nearAttributes.setDrawOutline(false);
        nearAttributes.setInteriorOpacity(0.5);
    }

    @Override
    public void doIt(double distance, double azimuth, PointPlacemark pointPlacemark) {

        distance *= 1000;
        shape.setHighlightAttributes(highlightAttributes);
        if (distance > this.range) {
            shape.getAttributes().setDrawInterior(false);
            pointPlacemark.getAttributes().setScale(0.65);
            wwd.redrawNow();
        } else {
            if (distance <= this.range && distance > this.range / 2.0) {
                shape.setAttributes(farAttributes);
                shape.getAttributes().setDrawInterior(true);
                pointPlacemark.getAttributes().setScale(0.7);
                wwd.redrawNow();
            } else {
                if (distance <= this.range / 2.0 && distance > this.range / 4.0) {
                    shape.setAttributes(middleAttributes);
                    shape.getAttributes().setDrawInterior(true);
                    pointPlacemark.getAttributes().setScale(0.9);
                    wwd.redrawNow();
                } else {
                    if (distance <= this.range / 4.0) {
                        shape.setAttributes(nearAttributes);
                        shape.getAttributes().setDrawInterior(true);
                        pointPlacemark.getAttributes().setScale(1.5);
                        wwd.redrawNow();
                    }
                }
            }
        }
    }
}
