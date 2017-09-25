/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller;

import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.ElevationLoader;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader.TextureLoader;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.ElevationModel;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author serge
 * @date Sep 19, 2017
 */
public class ElevationStlController
        extends StlController {

    protected ElevationModel model;

    public ElevationStlController(Path outPathname, String title,
            int tilesCount, int index,
            List<? extends Position> positions,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification,
            ElevationModel model) {
        super(outPathname,
                tilesCount, index,
                positions,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification);
        this.model = model;
    }

    public void compute() {
        writeTexture();
        writeElevation();
    }

    private void writeElevation() {
        ElevationLoader elevationLoader = new ElevationLoader(model,
                positions,
                index,
                tileSideX, tileSideY,
                earthSpaceX, earthSpaceY,
                bottom,
                magnification);
        write(elevationLoader.computeDEM());
    }

    /*
    private void writeElevation(String outFilename, int index,
            Polygon polygon, Point3D[][] pts) {

        ElevationLoader elevationLoader
                = new ElevationLoader();

        write(outFilename,
                elevationLoader.computeDEM(pts,
                        polygon,
                        index,
                        tileSideX, tileSideY,
                        spaceX, spaceY,
                        46.0,
                        bottom,
                        magnification, scaleLatFactor, scaleLonFactor)
        );

    }
     */
    private void writeTexture() {
        TextureLoader exportImageOrElevations = new TextureLoader(positions);
        exportImageOrElevations.doSaveImage();
    }

}
