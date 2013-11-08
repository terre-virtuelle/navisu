package bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoView;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXDisplay;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayers;
import gov.nasa.worldwind.layers.Layer;
import javafx.scene.Node;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class GeoViewImpl implements GeoView, GeoViewServices, ComponentState {

    protected GeoWorldWindView geoView;
    protected LayerManager<Layer> layerManager;

    @Override
    public void componentInitiated() {

        this.geoView = bzh.terrevirtuelle.navisu.core.view.geoview.GeoView.factory.newWorldWindGeo3DView();
        this.layerManager = this.geoView.getLayerManager();

        this.initDefaultLayers(this.layerManager);
    }

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

                WorldWindLayers.CountryBoundaries.newInstance(),
                WorldWindLayers.PlaceName.newInstance(),

                WorldWindLayers.LatLonGraticule.newInstance()
        );

        layerManager.createGroup("On-screen layers",
                WorldWindLayers.WorldMap.newInstance(),
                WorldWindLayers.ScaleBar.newInstance(),
                WorldWindLayers.Compass.newInstance()
        );
    }

    @Override
    public Display<Node> getDisplayService() {
        return this.geoView;
    }



    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}
}
