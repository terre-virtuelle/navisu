/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;

/**
 * NaVisu
 *
 * @date 29 mai 2015
 * @author Serge Morvan
 */
public class Arrow_45_60
        extends Arrow {

    private final double Y = -0.02;
    private final double YY = -0.01;
    private final double X = 0.05;
    private final double decX = X / 12.0;

    public Arrow_45_60(double lat, double lon) {
        super(lat, lon);
    }

    @Override
    protected double[] initShape(double latitude, double longitude) {

        double[] shipShape = new double[8];

        shipShape[0] = longitude - X; //1
        shipShape[1] = latitude;

        shipShape[2] = longitude - X + decX; //2
        shipShape[3] = latitude - Y;

        shipShape[4] = longitude - X + 4 * decX; //3
        shipShape[5] = latitude;

        shipShape[6] = longitude + X; //0
        shipShape[7] = latitude;

        return shipShape;
    }

    @Override
    protected ShapeAttributes makeAttributes() {
        ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineWidth(2);
        pathAttrs.setDrawInterior(false);
        pathAttrs.setInteriorMaterial(Material.BLACK);
        pathAttrs.setDrawInterior(true);

        return pathAttrs;
    }
}
