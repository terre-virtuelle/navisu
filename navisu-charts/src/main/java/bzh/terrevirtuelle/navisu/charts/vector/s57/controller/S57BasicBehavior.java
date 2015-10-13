/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.controller;

import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 * NaVisu
 *
 * @date 13 oct. 2015
 * @author Serge Morvan
 */
public class S57BasicBehavior
        extends S57Behavior {

    private final ShapeAttributes farAttributes;
    private final ShapeAttributes middleAttributes;
    private final ShapeAttributes nearAttributes;
    private final ShapeAttributes highlightAttributes;

    public S57BasicBehavior(double distance) {
        this.range = distance;
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
    public void doIt(double distance, double azimuth) {
        distance *= 1000;
        shape.setHighlightAttributes(highlightAttributes);
        if (distance > this.range) {
            shape.getAttributes().setDrawInterior(false);
        } else {
            if (distance <= this.range && distance > this.range / 2.0) {
                shape.setAttributes(farAttributes);
                shape.getAttributes().setDrawInterior(true);
            } else {
                if (distance <= this.range / 2.0 && distance > this.range / 4.0) {
                    shape.setAttributes(middleAttributes);
                    shape.getAttributes().setDrawInterior(true);
                } else {
                    if (distance <= this.range / 4.0) {
                        shape.setAttributes(nearAttributes);
                        shape.getAttributes().setDrawInterior(true);
                    }
                }
            }
        }
    }
}
