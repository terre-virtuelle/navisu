/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.shapefiles.impl.contoller;

import bzh.terrevirtuelle.navisu.shapefiles.impl.contoller.loader.SingleAREA_ShapefileLoader;
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
public class ShapefileController {

    private static final ShapefileController INSTANCE;
    protected String path;

    private List<Layer> layers;

    static {
        INSTANCE = new ShapefileController();
    }

    private ShapefileController() {
        this.layers = new ArrayList<>();
    }

    public static ShapefileController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;

        RenderableLayer layer = new RenderableLayer();
        layer.setName("SHP");
        SingleAREA_ShapefileLoader shapefileLoader = new SingleAREA_ShapefileLoader();
        layers = shapefileLoader.createLayersFromSource(new File(path));
        return layers;
    }
}
