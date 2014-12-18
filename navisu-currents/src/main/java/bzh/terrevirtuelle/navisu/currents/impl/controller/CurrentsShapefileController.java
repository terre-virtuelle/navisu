/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller;


import bzh.terrevirtuelle.navisu.currents.impl.controller.loader.CurrentsShapefileLoader;
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
public class CurrentsShapefileController {

    private static final CurrentsShapefileController INSTANCE;
    protected String path;

    private List<Layer> layers;

    static {
        INSTANCE = new CurrentsShapefileController();
    }

    private CurrentsShapefileController() {
        this.layers = new ArrayList<>();
    }

    public static CurrentsShapefileController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;
        RenderableLayer layer = new RenderableLayer();
        layer.setName("Currents");
        CurrentsShapefileLoader shapefileLoader= new CurrentsShapefileLoader();
        layers = shapefileLoader.createLayersFromSource(new File(path));
        return layers;
    }
}
