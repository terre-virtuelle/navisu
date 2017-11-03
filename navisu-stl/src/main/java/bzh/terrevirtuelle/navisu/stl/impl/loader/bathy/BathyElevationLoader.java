/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.loader.bathy;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.controller.StlController;
import bzh.terrevirtuelle.navisu.stl.impl.loader.texture.TextureElevationLoader;
import gov.nasa.worldwind.geom.Position;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class BathyElevationLoader
        extends StlController {

    protected GeodesyServices geodesyServices;
    TextureElevationLoader textureLoader;
/*
    geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset
    */
    public BathyElevationLoader(Path outPathname, String title,
            int tilesCount, int index,
            GeodesyServices geodesyServices,
            List<? extends Position> positions,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification,
            double offset) {
        
        this.geodesyServices = geodesyServices;
    }

    public double[][] getElevations(List<? extends Position> positions) {

        return null;
    }
    public void compute(){
        
    }

}
