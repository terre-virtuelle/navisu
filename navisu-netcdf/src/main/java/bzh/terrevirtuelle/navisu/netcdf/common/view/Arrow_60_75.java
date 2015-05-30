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
public class Arrow_60_75
        extends Arrow {

    private final double Y = -0.02;
    private final double YY = -0.01;
    private final double X = 0.05;
    private final double decX = X / 12.0;

    public Arrow_60_75(double lat, double lon) {
        super(lat, lon);
    }

    @Override
    protected double[] initShape(double latitude, double longitude) {
        double[] shipShape = new double[14];

        shipShape[0] = longitude - X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X + decX; //1
        shipShape[3] = latitude - Y;

        shipShape[4] = longitude - X + 4 * decX; //2
        shipShape[5] = latitude;

        shipShape[6] = longitude - X + 5 * decX; ; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + 2*decX; //4
        shipShape[9] = latitude - Y;

        shipShape[10] = longitude - X + 5 * decX; //5
        shipShape[11] = latitude;
        
        shipShape[12] = longitude + X; //6
        shipShape[13] = latitude;

        return shipShape;
    }

    @Override
    protected ShapeAttributes makeAttributes() {
        ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.RED);
        pathAttrs.setOutlineWidth(2);
        pathAttrs.setDrawInterior(false);
        pathAttrs.setInteriorMaterial(Material.RED);
        pathAttrs.setDrawInterior(true);

        return pathAttrs;
    }
}
