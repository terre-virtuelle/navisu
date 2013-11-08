package bzh.terrevirtuelle.navisu.core.view.geoview.layer;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.impl.WorldWindGeoLayer;
import gov.nasa.worldwind.layers.Layer;


public interface GeoLayerFactory {

    GeoLayer<Layer> newWorldWindGeoLayer(Layer layer);

    public static final GeoLayerFactory instance = new GeoLayerFactoryImpl();

    static class GeoLayerFactoryImpl implements GeoLayerFactory {

        @Override
        public GeoLayer<Layer> newWorldWindGeoLayer(Layer layer) {
            return new WorldWindGeoLayer(layer);
        }
    }
}
