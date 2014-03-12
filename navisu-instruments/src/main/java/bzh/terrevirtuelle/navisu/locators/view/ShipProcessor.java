package bzh.terrevirtuelle.navisu.locators.view;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.core.model.tobject.TOrientedObject;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;


/**
 * NaVisu
 *
 * @author tibus
 * @date 19/02/2014 19:13
 */
public abstract class ShipProcessor
        implements GObjectCUDProcessor {

    protected final GeoLayer<Layer> layer;
    protected TShip tShip;

    public ShipProcessor(GeoLayer<Layer> layer) {
        this.layer = layer;
    }

    public ShipProcessor(GeoLayer<Layer> layer, TShip tShip) {
        this.layer = layer;
        this.tShip = tShip;
    }

    @Override
    public GObject processUpdated(int id, TObject input, GObject output) {

        GShip gShip = (GShip) output;

        gShip.setLocation(input.getLocation());
        gShip.setCog(((TOrientedObject) input).getOrientation().getOrientationDegree());
        return output;
    }

    @Override
    public GObject processDeleted(int id, TObject input, GObject output) {
        // Nothing to do here
        return output;
    }

    protected ShapeAttributes makeAttributes() {
        final ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        return pathAttrs;
    }

    @Override
    public GeoLayer<Layer> getLayer() {
        return this.layer;
    }

    @Override
    public Class<? extends TObject> getType() {
        return TShip.class;
    }
}
