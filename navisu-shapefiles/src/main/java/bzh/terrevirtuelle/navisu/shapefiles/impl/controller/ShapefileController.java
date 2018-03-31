/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.shapefiles.impl.controller;

import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.loader.SingleAREA_ShapefileLoader;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Serge Morvan
 * @date 10 nov. 2014 NaVisu project
 */
public class ShapefileController {

    private static final ShapefileController INSTANCE;
    protected String path;
    private List<Layer> layers;
    protected SingleAREA_ShapefileLoader shapefileLoader;

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

    public final List<Layer> init(String path, Map<String, Integer> keys, List<List<String>> dbList) {

        this.path = path;
        RenderableLayer layer = new RenderableLayer();
        layer.setName("SHP");
        shapefileLoader = new SingleAREA_ShapefileLoader(dbList, keys);
        layers = shapefileLoader.createLayersFromSource(new File(path));
        return layers;
    }

    public Shapefile getShapefile() {
        return shapefileLoader.getShapefile();
    }
}
