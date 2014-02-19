/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.gps.locator;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.world.gps.locator.controller.GpsLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.world.gps.locator.view.GpsLayer;
import bzh.terrevirtuelle.navisu.world.gps.locator.view.ShipProcessor;
import gov.nasa.worldwind.layers.Layer;

/**
 *
 * @author Serge
 */
public class GpsLocator {

    //protected GpsLocatorController aisLocatorController;

    protected final GeoLayer<Layer> gpsLayer;
    protected final ShipProcessor shipProcessor;
    protected final GpsLocatorControllerWithDPAgent gpsLocatorController;


    public GpsLocator(GeoViewServices geoViewServices, DpAgentServices dpAgentServices) {
        //aisLocatorController = new GpsLocatorController(geoViewServices);

        // creation de la layer
        this.gpsLayer = GeoLayer.factory.newWorldWindGeoLayer(new GpsLayer());

        // creation du processor
        this.shipProcessor = new ShipProcessor(this.gpsLayer);
        geoViewServices.registerProcessor(this.shipProcessor);

        geoViewServices.getLayerManager().insertGeoLayer(this.shipProcessor.getLayer());

        // creation du controller
        this.gpsLocatorController = new GpsLocatorControllerWithDPAgent(dpAgentServices);
    }
}
