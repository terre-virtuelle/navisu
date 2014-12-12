/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.sedimentology.impl.controller;

import bzh.terrevirtuelle.navisu.sedimentology.impl.controller.loader.NFD_ShapefileLoader;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 10 nov. 2014 NaVisu project
 */
public class SedimentologyShapefileController {

    private static final SedimentologyShapefileController INSTANCE;
    protected String path;

    private List<Layer> layers;

    static {
        INSTANCE = new SedimentologyShapefileController();
    }

    private SedimentologyShapefileController() {
        this.layers = new ArrayList<>();
    }

    public static SedimentologyShapefileController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;

        RenderableLayer layer = new RenderableLayer();
        layer.setName("NFD");
        NFD_ShapefileLoader shapefileLoader = new NFD_ShapefileLoader();
        layers = shapefileLoader.createLayersFromSource(new File(path));
        return layers;
    }
}
