package bzh.terrevirtuelle.navisu.core.view.geoview.worldwind;

import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayerManager;
import gov.nasa.worldwind.WorldWindow;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface GeoWorldWindView extends GeoView {

    WorldWindow getWorldWindow();

    @Override
    WorldWindLayerManager getLayerManager();
}
