/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.view.geoview.layer;

import gov.nasa.worldwind.layers.Layer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface LayerManager<T> {

    public static final String DEFAULT_GROUP = LayerManager.class.getName() + ".DEFAULT_GROUP";

    @SuppressWarnings("unchecked")
    void createGroup(String groupName, GeoLayer<T>... layers);

    List<GeoLayer<T>> getGroup(String key);

    List<GeoLayer<T>> getDefaultGroup();

    Map<String, List<GeoLayer<T>>> getGroups();

    @SuppressWarnings("unchecked")
    void insertGeoLayer(GeoLayer<T>... layers);

    @SuppressWarnings("unchecked")
    void insertGeoLayer(String groupName, GeoLayer<T>... layers);

    void insertGeoLayerBeforeLayerName(GeoLayer<T> layer, String layerName);

    void insertGeoLayerBeforeLayerName(String groupName, GeoLayer<T> layer, String layerName);

    void removelayer(GeoLayer<Layer> layer);
}
