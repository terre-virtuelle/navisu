/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.view.geoview;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWind3DView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface GeoViewFactory {
 
    GeoWorldWind3DView newWorldWindGeo3DView();

    GeoWorldWindView newWorldwindGeoView();
}
