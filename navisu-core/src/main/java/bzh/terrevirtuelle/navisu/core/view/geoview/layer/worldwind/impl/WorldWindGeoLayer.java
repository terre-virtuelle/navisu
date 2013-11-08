package bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class WorldWindGeoLayer implements GeoLayer<Layer> {

    protected final Layer layer;

    public WorldWindGeoLayer(Layer layer) {
        this.layer = layer;
    }

    @Override
    public String getName() {
        return this.layer.getName();
    }

    @Override
    public void setName(String name) {
        this.layer.setName(name);
    }

    @Override
    public void setVisible(boolean visible) {
        this.layer.setEnabled(visible);
    }

    @Override
    public boolean isVisible() {
        return this.layer.isEnabled();
    }

    @Override
    public void setOpacity(double opacity) {
        this.layer.setOpacity(opacity);
    }

    @Override
    public double getOpacity() {
        return this.layer.getOpacity();
    }

    @Override
    public Layer getDisplayLayer() {
        return this.layer;
    }
}
