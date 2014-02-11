/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.ais.locator.view;

import bzh.terrevirtuelle.navisu.world.ais.locator.view.impl.ShipDefaultViewImpl;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.impl.ShipViewImpl;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class AisLayer
        extends RenderableLayer {

    protected final static Logger LOGGUER = Logger.getLogger(AisLayer.class.getName());

    public AisLayer() {
        setName("AIS Layer");
    }

    public void add(ShipViewImpl shipView) {
        addRenderable(shipView);
    }

    public void add(ShipDefaultViewImpl shipView) {
        addRenderable(shipView);
    }

    public void remove(ShipView shipView) {
        remove(shipView);
    }

}
