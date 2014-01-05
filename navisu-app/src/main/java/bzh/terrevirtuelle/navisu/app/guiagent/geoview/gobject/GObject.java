package bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject;

import bzh.terrevirtuelle.navisu.core.model.geom.location.Location;
import bzh.terrevirtuelle.navisu.core.util.ICloneable;
import gov.nasa.worldwind.render.Renderable;

/**
 * NaVisu
 *
 * @author tibus
 * @date 05/01/2014 19:13
 */
public interface GObject extends ICloneable {

    int getID();

    void setLocation(Location location);

    Renderable[] getRenderables();
}
