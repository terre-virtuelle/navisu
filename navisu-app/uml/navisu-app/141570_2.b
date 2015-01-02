class GpsLocatorControllerWithDPAgent
!!!153346.java!!!	GpsLocatorControllerWithDPAgent(in dpAgentServices : DpAgentServices)
        this.dpAgentServices = dpAgentServices;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/domain.properties"));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        // creation du TObject (l'objet metier)
        ship = new TShip(IDGenerator.newIntID(),
                new Integer(properties.getProperty("mmsi")),
                new Integer(properties.getProperty("imo")),
                properties.getProperty("name"),
                new Float(properties.getProperty("heading")),
                new Float(properties.getProperty("cog")),
                new Float(properties.getProperty("sog")),
                new Float(properties.getProperty("rot")),
                new Float(properties.getProperty("latitude")),
                new Float(properties.getProperty("longitude")),
                new Float(properties.getProperty("width")),
                new Float(properties.getProperty("length")),
                new Float(properties.getProperty("draught")),
                new Integer(properties.getProperty("shipType")),
                new Integer(properties.getProperty("navigationalStatus")),
                new Integer(properties.getProperty("electronicPositionDevice")),
                properties.getProperty("callSign"), 
                null, 
                "",
                properties.getProperty("country"));
        ship.setShapeId(36);
        // insertion dans le DPAgent
        dpAgentServices.create(ship);

        subscribe();
!!!153474.java!!!	subscribe() : void
        // souscription aux événements
        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                GGA data = (GGA) d;
                ship.setLatitude(data.getLatitude());
                ship.setLongitude(data.getLongitude());

                // mise à jour via le DPAgent
                dpAgentServices.update(ship);
            }
        });
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                VTG data = (VTG) d;
                ship.setCog(data.getCog());
                ship.setSog(data.getSog());
                if (ship.getSog() > 0.1) {
                    ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                dpAgentServices.update(ship);
            }
        });
        rmcES.subscribe(new RMCEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                RMC data = (RMC) d;
                ship.setLatitude(data.getLatitude());
                ship.setLongitude(data.getLongitude());
                ship.setCog(data.getCog());
                ship.setSog(data.getSog());
                if (ship.getSog() > 0.1) {
                    ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                dpAgentServices.update(ship);
            }
        });
