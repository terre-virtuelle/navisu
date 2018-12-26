package bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.basic.BingImageryLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.basic.EarthAtNightLayer;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.basic.FogLayer;
import gov.nasa.worldwind.layers.*;
import gov.nasa.worldwind.layers.Earth.*;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public enum WorldWindLayers {

    Stars(StarsLayer.class),
    SkyGradient(SkyGradientLayer.class),
    BlueMarbleImage(BMNGOneImage.class, true, 3e6),
    BlueMarbleWMS(BMNGWMSLayer.class),
    Fog(FogLayer.class),
    LandsatICubed(LandsatI3WMSLayer.class),
    BingImagery(BingImageryLayer.class),
    EarthAtNight(EarthAtNightLayer.class, false),
    OpenStreetMap(OSMMapnikLayer.class, false),
    OSMBuildings(OSMBuildingsLayer.class, false),
    CountryBoundaries(CountryBoundariesLayer.class, false),
    PlaceName(NASAWFSPlaceNameLayer.class),
    LatLonGraticule(LatLonGraticuleLayer.class, false),
    WorldMap(WorldMapLayer.class),
    ScaleBar(ScalebarLayer.class),
    Compass(CompassLayer.class);

    protected final Class<? extends Layer> type;
    protected final boolean enabled;
    protected final double minActiveAltitude;

    WorldWindLayers(Class<? extends Layer> type) {
        this(type, true);
    }

    WorldWindLayers(Class<? extends Layer> type, boolean enabled) {
        this(type, enabled, 0d);
    }

    WorldWindLayers(Class<? extends Layer> layer, boolean enabled, double minActiveAltitude) {
        this.type = layer;
        this.enabled = enabled;
        this.minActiveAltitude = minActiveAltitude;
    }

    public GeoLayer<Layer> newInstance() {

        Layer layer = null;

        try {
            layer = type.newInstance();
            layer.setEnabled(enabled);
            layer.setMinActiveAltitude(minActiveAltitude);
        } catch (IllegalAccessException | InstantiationException e) {
        }

        return GeoLayer.factory.newWorldWindGeoLayer(layer);
    }
    public GeoLayer<Layer> newInstance(String name) {

        Layer layer = null;

        try {
            layer = type.newInstance();
            layer.setName(name);
            layer.setEnabled(enabled);
            layer.setMinActiveAltitude(minActiveAltitude);
        } catch (IllegalAccessException | InstantiationException e) {
        }

        return GeoLayer.factory.newWorldWindGeoLayer(layer);
    }
}
