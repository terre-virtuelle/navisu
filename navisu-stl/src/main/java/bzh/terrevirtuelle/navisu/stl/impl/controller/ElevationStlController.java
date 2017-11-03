/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.controller;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.impl.loader.bathy.BathyElevationLoader;
import bzh.terrevirtuelle.navisu.stl.impl.writer.sea.SeaWriter;
import bzh.terrevirtuelle.navisu.stl.impl.loader.texture.TextureElevationLoader;
import gov.nasa.worldwind.geom.Position;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author serge
 * @date Sep 19, 2017
 */
public class ElevationStlController
        extends StlController {

    protected GeodesyServices geodesyServices;
    TextureElevationLoader textureLoader;
    SeaWriter seaWriter;

    public ElevationStlController(Path outPathname, String title,
            int tilesCount, int index,
            GeodesyServices geodesyServices,
            List<? extends Position> positions,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification,
            double offset
    ) {
        super(outPathname,
                tilesCount, index,
                positions,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
        this.geodesyServices = geodesyServices;
    }

    public void compute() {
        writeElevationTexture();
        writeElevation();
        // writeSea();
    }

    public void computeBathy() {
        //writeTexture();
        writeBathyElevation();

    }

    private void writeElevationTexture() {
        textureLoader = new TextureElevationLoader(positions, index);
        textureLoader.doSaveImage();
    }

    private void writeElevation() {

        /*
        DemElevationLoader elevationLoader = new DemElevationLoader(geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);

        write(elevationLoader.computeDEM());
         */
    }

    private void writeBathyElevation() {
       /* 
        BathyElevationLoader elevationLoader = new BathyElevationLoader(
                geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
*/
      //  write(elevationLoader.computeDEM());
         
    }

    private void writeSea() {
        seaWriter = new SeaWriter();
        write(seaWriter.compute());
    }

}
