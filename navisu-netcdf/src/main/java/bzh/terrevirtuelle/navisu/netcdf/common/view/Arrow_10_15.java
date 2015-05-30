/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

/**
 * NaVisu
 *
 * @date 29 mai 2015
 * @author Serge Morvan
 */
public class Arrow_10_15
        extends Arrow {

    private final  double Y = -0.02;
    private final  double X = 0.05;
    private final  double decX = X / 12.0;
    
    public Arrow_10_15(double lat, double lon) {
        super(lat,lon);
    }


    @Override
    protected double[] initShape(double latitude, double longitude) {

        double[] shipShape = new double[8];
        shipShape[0] = longitude + X;
        shipShape[1] = latitude;

        shipShape[2] = longitude - X;
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX;
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X;
        shipShape[7] = latitude;

        return shipShape;
    }
}
