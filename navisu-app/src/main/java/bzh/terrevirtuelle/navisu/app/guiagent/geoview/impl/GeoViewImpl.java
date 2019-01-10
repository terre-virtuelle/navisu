package bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentEvents;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoView;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObject;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.gobject.GObjectCUDProcessor;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.model.generic.Model;
import bzh.terrevirtuelle.navisu.core.model.tobject.TObject;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayers;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import javafx.scene.Node;
import org.capcaval.c3.component.ComponentStateAdaptor;
import org.capcaval.c3.component.annotation.ConsumedEvent;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class GeoViewImpl
        extends ComponentStateAdaptor
        implements GeoView, GeoViewServices {

    private static final Logger LOGGER = Logger.getLogger(GeoViewImpl.class.getName());

    static {
        LOGGER.setLevel(Level.ALL);
    }

    @UsedService
    LayerTreeServices layerTreeServices;

    protected GeoWorldWindView geoView;
    protected LayerManager<Layer> layerManager;
    protected Model<GObject> model;
    protected List<GObjectCUDProcessor> processors;

    @Override
    public void componentInitiated() {

        this.geoView = bzh.terrevirtuelle.navisu.core.view.geoview.GeoView.factory.newWorldWindGeo3DView();
        this.layerManager = this.geoView.getLayerManager();

        this.initDefaultLayers(this.layerManager);

        this.layerManager.getGroups().forEach((groupName, geoLayerList) -> {

            if (geoLayerList.size() > 0) {
                layerTreeServices.createGroup(groupName, geoLayerList.toArray(new GeoLayer[geoLayerList.size()]));
            }
        });

        this.model = Model.factory.newModel(GObject.class);
        this.processors = new ArrayList<>();
    }

    protected void createGObject(GObjectCUDProcessor processor, TObject tObject) {
        // Create the GObject
        GObject newGObject = processor.processCreated(tObject.getID(), tObject);
        //TODO /!\ C'est pas top de faire
        final RenderableLayer layer = (RenderableLayer) processor.getLayer().getDisplayLayer();

        for (Renderable renderable : newGObject.getRenderables()) {
            layer.addRenderable(renderable);
        }
        // Add the new GObject in the modelÒ
        this.model.getWriteDataServices().create(newGObject.getID(), newGObject);

        // Redraw WorldWind
        this.geoView.getWorldWindow().redraw();

    }

    protected void updateGObject(GObjectCUDProcessor processor, TObject tObject) {

        // Retrieve the GObject in the model
        final GObject gObject = this.model.getReadDataServices().read(tObject.getID());

        // Update the GObject
        final GObject updatedGObject = processor.processUpdated(gObject.getID(), tObject, gObject);

        // Update the GObject in the model
        this.model.getWriteDataServices().update(gObject.getID(), updatedGObject);

        // Redraw WorldWind
        this.geoView.getWorldWindow().redraw();
    }

    protected void deleteGObject(GObjectCUDProcessor processor, TObject tObject) {

        // Retrieve the GObject in the model
        final GObject gObject = this.model.getReadDataServices().read(tObject.getID());

        // Delete the GObject
        final GObject deletedGObject = processor.processDeleted(gObject.getID(), tObject, gObject);

        //TODO /!\ C'est pas top de faire
        final RenderableLayer layer = (RenderableLayer) processor.getLayer().getDisplayLayer();

        for (Renderable renderable : deletedGObject.getRenderables()) {
            layer.removeRenderable(renderable);
        }

        // Delete in the model
        this.model.getWriteDataServices().delete(deletedGObject.getID());

        // Redraw WorldWind
        this.geoView.getWorldWindow().redraw();
    }

    @ConsumedEvent
    protected DpAgentEvents createDpAgentEvents() {
        return new DpAgentEvents() {

            @Override
            public void notifyCreated(TObject tObject) {
                //  LOGGER.info("notifyCreated(" + tObject.getID() + ")");
                GObjectCUDProcessor processor;

                processor = findProcessor(tObject);

                if (processor == null) {
                    LOGGER.log(Level.WARNING, "Cannot find processor for TObject : {0}", tObject.getClass());
                    return;
                }
                createGObject(processor, tObject);
            }

            @Override
            public void notifyUpdated(TObject tObject) {
                // LOGGER.info("notifyUpdated(" + tObject.getID() + ")");

                GObjectCUDProcessor processor = findProcessor(tObject);
                if (processor == null) {
                    LOGGER.log(Level.WARNING, "Cannot find processor for TObject : {0}", tObject.getClass());
                    return;
                }

                updateGObject(processor, tObject);
            }

            @Override
            public void notifyDeleted(TObject tObject) {
                //    LOGGER.info("notifyDeleted(" + tObject.getID() + ")");

                GObjectCUDProcessor processor = findProcessor(tObject);

                if (processor == null) {
                    LOGGER.warning("Cannot find processor for TObject : " + tObject.getClass());
                    return;
                }

                deleteGObject(processor, tObject);
            }
        };
    }

    @Override
    public void registerProcessor(GObjectCUDProcessor processor) {

        Checker.notNull(processor, "Processor is null.");

        this.processors.add(processor);
    }

    protected GObjectCUDProcessor findProcessor(final TObject tObject) {

        Checker.notNull(tObject, "tObject is null, cannot find processor.");

        GObjectCUDProcessor processor = null;

        for (GObjectCUDProcessor proc : this.processors) {
            if (proc.getType().isAssignableFrom(tObject.getClass())) {
                processor = proc;
                break;
            }
        }

        return processor;
    }

    @SuppressWarnings("unchecked")
    protected void initDefaultLayers(final LayerManager<Layer> layerManager) {
     
        layerManager.createGroup("On-earth layers",
                WorldWindLayers.Stars.newInstance(),
                WorldWindLayers.SkyGradient.newInstance(),
                WorldWindLayers.BlueMarbleImage.newInstance(),
                WorldWindLayers.Fog.newInstance(),
                WorldWindLayers.BlueMarbleWMS.newInstance(),
                WorldWindLayers.LandsatICubed.newInstance(),
                WorldWindLayers.BingImagery.newInstance(),
                WorldWindLayers.EarthAtNight.newInstance(),
                WorldWindLayers.OpenStreetMap.newInstance(),
                WorldWindLayers.OSMBuildings.newInstance("OSMBuildings"),
                WorldWindLayers.CountryBoundaries.newInstance(),
                WorldWindLayers.PlaceName.newInstance(),
                WorldWindLayers.LatLonGraticule.newInstance()
                
        );

        layerManager.createGroup("On-screen layers",
                //  WorldWindLayers.WorldMap.newInstance(),
                WorldWindLayers.ScaleBar.newInstance(),
                WorldWindLayers.Compass.newInstance()
        );

    }

    @Override
    public Display<Node> getDisplayService() {
        return this.geoView;
    }

    @Override
    public LayerManager<Layer> getLayerManager() {
        return this.layerManager;
    }

}
