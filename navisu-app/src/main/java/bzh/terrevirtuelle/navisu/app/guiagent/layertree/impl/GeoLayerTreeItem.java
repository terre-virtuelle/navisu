package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import javafx.scene.control.CheckBoxTreeItem;

/**
 * NaVisu
 *
 * @author tibus
 * @date 09/11/2013 00:25
 */
public class GeoLayerTreeItem extends CheckBoxTreeItem<String> {

   protected final GeoLayer<?> geoLayer;

    public GeoLayerTreeItem(GeoLayer<?> geoLayer) {
        super(geoLayer.getName());
        this.geoLayer = geoLayer;

        this.setSelected(this.geoLayer.isVisible());

        //TODO set on select event to show or hide
    }
}
