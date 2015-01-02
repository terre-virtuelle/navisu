class GpsLocator
!!!153218.java!!!	GpsLocator(inout geoViewServices : GeoViewServices, inout dpAgentServices : DpAgentServices)

        // creation de la layer
        this.gpsLayer = GeoLayer.factory.newWorldWindGeoLayer(new GpsLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.gpsLayer);

        // creation du processor
        this.shipProcessor = new ShipProcessor(this.gpsLayer);
        geoViewServices.registerProcessor(this.shipProcessor);

        // creation du controller
        this.gpsLocatorController = new GpsLocatorControllerWithDPAgent(dpAgentServices);

