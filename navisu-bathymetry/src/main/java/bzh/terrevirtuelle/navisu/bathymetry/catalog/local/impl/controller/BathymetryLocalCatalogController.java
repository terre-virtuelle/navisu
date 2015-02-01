/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.controller;

import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.controller.loader.BathymetryLocalCatalogLoader;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.Polygon;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 1 fev. 2015 NaVisu project
 */
public class BathymetryLocalCatalogController
        implements SelectListener {

    protected WorldWindow wwd;
    private static final BathymetryLocalCatalogController INSTANCE;
    protected String path;

    static {
        INSTANCE = new BathymetryLocalCatalogController();
    }

    private BathymetryLocalCatalogController() {
        wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);
    }

    public static BathymetryLocalCatalogController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> init(String path) {
        this.path = path;

        BathymetryLocalCatalogLoader loader = new BathymetryLocalCatalogLoader();

        return loader.createLayersFromSource(path);//pas d'affectation si AnalyticSurface
    }

    @Override
    public void selected(SelectEvent event) {
        if (event.isRightClick()) {
            Object topObject = event.getTopObject();
            if (topObject != null) {
                if (topObject.getClass() == Polygon.class) {

                }
            }
        }
    }
}
