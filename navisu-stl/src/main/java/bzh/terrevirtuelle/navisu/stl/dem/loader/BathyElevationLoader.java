/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.stl.dem.loader;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.geom.Position;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class BathyElevationLoader extends ElevationLoader{

    public BathyElevationLoader(GeodesyServices geodesyServices,
            List<? extends Position> positions, int index, 
            double tileSideX, double tileSideY, 
            double earthSpaceX, double earthSpaceY, 
            double bottom, double magnification,
            double offset) {
        super(geodesyServices, positions, index, tileSideX, tileSideY, earthSpaceX, earthSpaceY, bottom, magnification, offset);
    }

    @Override
    public double[][] getElevations(List<? extends Position> positions, int ptsCountX, int ptsCountY, double latInc, double lonInc, double offset) {
    return null;
    }

}
