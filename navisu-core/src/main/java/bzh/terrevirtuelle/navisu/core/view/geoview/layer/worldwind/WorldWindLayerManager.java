package bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.layers.Layer;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface WorldWindLayerManager extends LayerManager<Layer> {

    Model getModel();

    @SuppressWarnings("unchecked")
    void insertGeoLayerBeforeCompass(GeoLayer<Layer>... layers);

    @SuppressWarnings("unchecked")
    void insertGeoLayerBeforeCompass(String groupName, GeoLayer<Layer>... layers);
}
