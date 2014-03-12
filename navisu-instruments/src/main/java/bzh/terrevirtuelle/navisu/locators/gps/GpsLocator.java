/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.gps;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.locators.gps.controller.GpsLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.gps.view.GpsLayer;
import bzh.terrevirtuelle.navisu.locators.view.PShipProcessor;
import bzh.terrevirtuelle.navisu.locators.view.ShipProcessor;
import gov.nasa.worldwind.layers.Layer;

/**
 *
 * @author Serge
 */
public class GpsLocator {

    protected final GeoLayer<Layer> gpsLayer;
    protected final ShipProcessor shipProcessor;
    protected final GpsLocatorControllerWithDPAgent gpsLocatorController;

    public GpsLocator(GeoViewServices geoViewServices, DpAgentServices dpAgentServices) {

        // creation de la layer
        this.gpsLayer = GeoLayer.factory.newWorldWindGeoLayer(new GpsLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.gpsLayer);
        
        // creation du processor
        this.shipProcessor = new PShipProcessor(this.gpsLayer);
        geoViewServices.registerProcessor(this.shipProcessor);

        // creation du controller
        this.gpsLocatorController = new GpsLocatorControllerWithDPAgent(dpAgentServices);
    }
}
