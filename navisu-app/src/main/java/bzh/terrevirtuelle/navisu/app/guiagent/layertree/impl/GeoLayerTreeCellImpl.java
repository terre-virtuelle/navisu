package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/11/2013 17:05
 */
public class GeoLayerTreeCellImpl extends TreeCell<String> {

    protected GeoLayer geoLayer;

    protected HBox container;
    protected CheckBox check;
    protected Text label;

    public GeoLayerTreeCellImpl() {

        this.check = new CheckBox();

        this.label = new Text(getString());

        this.container = new HBox(this.check, this.label);
    }

    public GeoLayerTreeCellImpl(final GeoLayer geoLayer) {

        this.geoLayer = geoLayer;

        this.check = new CheckBox();
        this.check.setSelected(this.geoLayer.isVisible());

        this.label = new Text();
        this.label.setText(this.geoLayer.getName());

        this.container = new HBox(this.check, this.label);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setGraphic(null);
        }

        else {
            setGraphic(this.container);
        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
