/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.controller;

import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.dem.loader.BathyElevationLoader;
import bzh.terrevirtuelle.navisu.stl.dem.loader.DemElevationLoader;
import bzh.terrevirtuelle.navisu.stl.dem.loader.ElevationLoader;
import bzh.terrevirtuelle.navisu.stl.impl.controller.loader.SeaLoader;
import bzh.terrevirtuelle.navisu.stl.dem.loader.TextureLoader;
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
        writeTexture();
        writeDemElevation();
        // writeSea();
    }

    private void writeTexture() {
        TextureLoader exportImageOrElevations = new TextureLoader(positions, index);
        exportImageOrElevations.doSaveImage();
    }

    private void writeDemElevation() {

        ElevationLoader elevationLoader = new DemElevationLoader(geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
        write(elevationLoader.computeDEM());

    }

    private void writeBathyElevation() {

        ElevationLoader elevationLoader = new BathyElevationLoader(geodesyServices,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification,
                offset);
        write(elevationLoader.computeDEM());

    }

    private void writeSea() {
        SeaLoader seaLoader = new SeaLoader();
        write(seaLoader.compute());
    }

}
