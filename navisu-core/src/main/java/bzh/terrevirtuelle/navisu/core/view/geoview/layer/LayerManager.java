/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.view.geoview.layer;

import java.util.List;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface LayerManager<T> {


    public static final String DEFAULT_GROUP = LayerManager.class.getName() + ".DEFAULT_GROUP";

    void createGroup(String groupName, GeoLayer<T>... layers);

    List<GeoLayer<T>> getGroup(String key);
    List<GeoLayer<T>> getDefaultGroup();

    void insertGeoLayer(                  GeoLayer<T>... layers);
    void insertGeoLayer(String groupName, GeoLayer<T>... layers);

    void insertGeoLayerBeforeLayerName(                  GeoLayer<T> layer, String layerName);
    void insertGeoLayerBeforeLayerName(String groupName, GeoLayer<T> layer, String layerName);
}
