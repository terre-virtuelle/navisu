/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.layers.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManager;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class LayersManagerImpl
        implements LayersManager, LayersManagerServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    LayerTreeServices layerTreeServices;

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public RenderableLayer initLayer(String groupName, String layerName) {
        List<String> groups = layerTreeServices.getGroupNames();
        if (!groups.contains(groupName)) {
            layerTreeServices.createGroup(groupName);
            geoViewServices.getLayerManager().createGroup(groupName);
        }
        boolean layerExist = false;
        RenderableLayer layer = null;
        List<GeoLayer<Layer>> layers = geoViewServices.getLayerManager().getGroup(groupName);
        for (GeoLayer<Layer> g : layers) {
            if (g.getName().contains(layerName)) {
                layer = (RenderableLayer) g.getDisplayLayer();
                layerExist = true;
            }
        }
        if (!layerExist) {
            layer = new RenderableLayer();
            layer.setName(layerName);
            geoViewServices.getLayerManager().insertGeoLayer(groupName, GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices.addGeoLayer(groupName, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }
        return layer;
    }

}
