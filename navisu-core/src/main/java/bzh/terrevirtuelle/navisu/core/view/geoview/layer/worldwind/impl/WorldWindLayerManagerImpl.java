package bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayerManager;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.placename.PlaceNameLayer;

import java.util.*;


/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class WorldWindLayerManagerImpl implements WorldWindLayerManager {

    protected final Model model;
    protected final Map<String, List<GeoLayer<Layer>>> groupMap;

    public WorldWindLayerManagerImpl(Model model) {

        Checker.notNull(model, "model is null.");

        this.model = model;
        this.groupMap = new HashMap<>();

        this.groupMap.put(DEFAULT_GROUP, new LinkedList<GeoLayer<Layer>>());
    }

    @Override
    public void createGroup(String groupName, GeoLayer<Layer>... layers) {

        Checker.notNull(groupName, "Group name is null.");
        Checker.keyNotExistsInMap(this.groupMap, groupName, "Group " + groupName + " already exists.");

        // Create the group
        List<GeoLayer<Layer>> newGroup = new LinkedList<>();
        // Add it in the group map
        this.groupMap.put(groupName, newGroup);

        // Insert layers
        Arrays.asList(layers).forEach(layer -> {

            newGroup.add(layer);

            this.model.getLayers().add(layer.getDisplayLayer());
        });
    }

    @Override
    public List<GeoLayer<Layer>> getGroup(String groupName) {

        Checker.notNull(groupName, "Group name is null.");
        Checker.keyExistsInMap(this.groupMap, groupName, "Group " + groupName + " does not exists.");

        return this.groupMap.get(groupName);
    }

    @Override
    public List<GeoLayer<Layer>> getDefaultGroup() {
        return this.groupMap.get(DEFAULT_GROUP);
    }

    @Override
    public Map<String, List<GeoLayer<Layer>>> getGroups() {
        return this.groupMap;
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Insertion
    //
    @Override
    public void insertGeoLayer(GeoLayer<Layer>... layers) {

        final List<GeoLayer<Layer>> defaultGroup = this.groupMap.get(DEFAULT_GROUP);

        Arrays.asList(layers).forEach(layer -> {
            // Keep the layer
            defaultGroup.add(layer);

            // Insert it in the WorldWind model
            this.model.getLayers().add(layer.getDisplayLayer());
        });
    }

    @Override
    public void insertGeoLayer(String groupName, GeoLayer<Layer>... layers) {


        Checker.notNull(groupName, "Group name is null.");
        Checker.keyExistsInMap(this.groupMap, groupName, "Group " + groupName + " does not exists.");

        final List<GeoLayer<Layer>> group = this.groupMap.get(groupName);

        Arrays.asList(layers).forEach(layer -> {
            // Keep the layer
            group.add(layer);

            // Insert it in the WorldWind model
            this.model.getLayers().add(layer.getDisplayLayer());
        });
    }

    @Override
    public void insertGeoLayerBeforeLayerName(GeoLayer<Layer> layer, String layerName) {

        Checker.notNull(layer, "Layer is null.");
        Checker.notNull(layerName, "Layer name is null");

        final List<GeoLayer<Layer>> defaultGroup = this.groupMap.get(DEFAULT_GROUP);

        defaultGroup.add(layer);
        this.insertBeforeLayerName(layer.getDisplayLayer(), layerName);
    }

    @Override
    public void insertGeoLayerBeforeLayerName(String groupName, GeoLayer<Layer> layer, String layerName) {

        Checker.notNull(groupName, "Group name is null.");
        Checker.notNull(layer, "Layer is null.");
        Checker.notNull(layerName, "Layer name is null.");

        final List<GeoLayer<Layer>> group = this.groupMap.get(groupName);

        group.add(layer);
        this.insertBeforeLayerName(layer.getDisplayLayer(), layerName);
    }

    @Override
    public void insertGeoLayerBeforeCompass(GeoLayer<Layer>... layers) {

        final List<GeoLayer<Layer>> defaultGroup = this.groupMap.get(DEFAULT_GROUP);

        Arrays.asList(layers).forEach(layer -> {

            defaultGroup.add(layer);
            this.insertBeforeCompass(layer.getDisplayLayer());
        });
    }

    @Override
    public void insertGeoLayerBeforeCompass(String groupName, GeoLayer<Layer>... layers) {

        Checker.notNull(groupName, "Group name is null.");
        Checker.keyExistsInMap(this.groupMap, groupName, "Group " + groupName + " does not exists.");

        final List<GeoLayer<Layer>> group = this.groupMap.get(groupName);

        Arrays.asList(layers).forEach(layer -> {

            group.add(layer);
            this.insertBeforeCompass(layer.getDisplayLayer());
        });
    }

    protected void insertBeforeCompass(Layer layer) {
        // Insert the layer into the layer list just before the compass.
        int compassPosition = 0;
        LayerList layers = model.getLayers();
        for (Layer l : layers) {
            if (l instanceof CompassLayer)
                compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition, layer);
    }

    protected void insertBeforePlacenames(Layer layer) {
        // Insert the layer into the layer list just before the placenames.
        int compassPosition = 0;
        LayerList layers = model.getLayers();
        for (Layer l : layers) {
            if (l instanceof PlaceNameLayer)
                compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition, layer);
    }

    protected void insertAfterPlacenames(Layer layer) {
        // Insert the layer into the layer list just after the placenames.
        int compassPosition = 0;
        LayerList layers = model.getLayers();
        for (Layer l : layers) {
            if (l instanceof PlaceNameLayer)
                compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition + 1, layer);
    }

    protected void insertBeforeLayerName(Layer layer, String targetName) {
        // Insert the layer into the layer list just before the target layer.
        int targetPosition = 0;
        LayerList layers = model.getLayers();
        for (Layer l : layers) {
            if (l.getName().indexOf(targetName) != -1) {
                targetPosition = layers.indexOf(l);
                break;
            }
        }
        layers.add(targetPosition, layer);
    }
    //
    //----------------------------------------------------------------------------------------------------------------//

    @Override
    public Model getModel() {
        return this.model;
    }
}
