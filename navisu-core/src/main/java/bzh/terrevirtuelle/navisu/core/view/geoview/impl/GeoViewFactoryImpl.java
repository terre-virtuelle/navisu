package bzh.terrevirtuelle.navisu.core.view.geoview.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.GeoViewFactory;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWind3DView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWind3DViewImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class GeoViewFactoryImpl implements GeoViewFactory {

    @Override
    public GeoWorldWind3DView newWorldWindGeo3DView() {
        return new GeoWorldWind3DViewImpl();
    }

    @Override
    public GeoWorldWindView newWorldwindGeoView() {
        return new GeoWorldWindViewImpl();
    }

}
