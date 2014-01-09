/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core.view.geoview;

import bzh.terrevirtuelle.navisu.core.model.geom.point.Point;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXDisplay;
import bzh.terrevirtuelle.navisu.core.view.geoview.impl.GeoViewFactoryImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.geodesy.Location;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface GeoView extends JFXDisplay {

    public static final GeoViewFactory factory = new GeoViewFactoryImpl();

    LayerManager<?> getLayerManager();

    Location transformPixelToLatLon(Point pixel);
    Point    transformLatLonToPixel(Location latLon);

    void       setProjection(Projection projection);
    Projection getProjection();
}
