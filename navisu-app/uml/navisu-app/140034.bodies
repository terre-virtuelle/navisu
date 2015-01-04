class AisLocator
!!!149762.java!!!	AisLocator(inout geoViewServices : GeoViewServices, inout dpAgentServices : DpAgentServices, inout guiAgentServices : GuiAgentServices)

        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;

        tShipProcessors = new HashMap<>();
        tStationsProcessors = new HashMap<>();
        timestamps = new HashMap<>();

        //      pane = guiAgentServices.getRoot();
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());

        aisLayer.setName("AIS Layer");
        geoViewServices.getLayerManager().insertGeoLayer(this.aisLayer);

        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 30000) {
                clip();
            } else {
                unClip();
            }
        });

        this.aisStationLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        aisStationLayer.setName("AIS_Station_Layer");
        geoViewServices.getLayerManager().insertGeoLayer(this.aisStationLayer);
        this.baloonLayer = new RenderableLayer();
        wwd.getModel().getLayers().add(baloonLayer);
        attrs = new BasicBalloonAttributes();
        attrs.setSize(Size.fromPixels(600, 320));
        midMap = new HashMap<>();
        String[] midEntries;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader("data/ais/mmsi.txt"))) {
                String s;
                while ((s = br.readLine()) != null) {
                    if (!s.isEmpty()) {
                        midEntries = s.split(",");
                        midMap.put(new Integer(midEntries[0].trim()), midEntries[1].trim());
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AisLocator.class.getName()).log(Level.SEVERE, null, ex);
        }

        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                Class[] i = o.getClass().getInterfaces();
                if (i.length != 0) {
                    if (i[0].equals(Shape.class)) {
                        makeBrowserBalloon(((Shape) o).getShip());
                    }
                }
            }
        });
        subscribe();
!!!149890.java!!!	subscribe() : void
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS1 ais = (AIS1) data;
                    int mmsi = ais.getMMSI();
                    if (!tShipProcessors.containsKey(mmsi)) {
                        ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                        geoViewServices.registerProcessor(shipProcessor);

                        ship = new TShip(IDGenerator.newIntID(),
                                ais.getMMSI(), ais.getImo(), ais.getShipname(),
                                ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                                ais.getLatitude(), ais.getLongitude(),
                                ais.getWidth(), ais.getLength(), ais.getDraught(),
                                ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                                ais.getETA(), ais.getDestination(), "");
                        dpAgentServices.create(ship);

                        aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);

                        tShipProcessors.put(mmsi, shipProcessor);
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    System.out.println("ais1ES.subscribe " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);
                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);

                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                int mmsi = ais.getMMSI();

                if (!tStationsProcessors.containsKey(mmsi)) {
                    StationProcessor stationProcessor = new StationProcessor(AisLocator.this.aisStationLayer);
                    geoViewServices.registerProcessor(stationProcessor);

                    station = new TStation(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());

                    dpAgentServices.create(station);

                    aisStationLocatorControllerWithDPAgent = new AisStationLocatorControllerWithDPAgent(dpAgentServices, station);
                    /*
                     wwd.addPositionListener((PositionEvent event) -> {
                     System.out.println((int) wwd.getView().getCurrentEyePosition().getElevation());
                     });
                     */
                    tStationsProcessors.put(mmsi, stationProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
!!!150018.java!!!	makeBrowserBalloon(inout ship : TShip) : void
        String htmlString = updateHtmlString(ship);
        this.baloonLayer.removeAllRenderables();

        balloonPosition = Position.fromDegrees(ship.getLatitude(), ship.getLongitude());
       balloon = new GlobeBrowserBalloon(htmlString, balloonPosition);//Non supporté par Linux
       // balloon = new GlobeAnnotationBalloon(htmlString, balloonPosition);
        balloon.setAttributes(attrs);
        balloon.getAttributes().setImageSource(IMAGE_EARTH.getPowerOfTwoImage());
        balloon.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
        //   balloon.getAttributes().setImageScale(7);
        //  balloon.getAttributes().setImageOffset(new Point(7, 7));

        this.baloonLayer.addRenderable(balloon);
!!!150146.java!!!	updateHtmlString(inout ship : TShip) : String
        String tmp;
        if (ship.getName() != null) {
            tmp = str.replace("__shipname__", ship.getName());
        } else {
            tmp = str.replace("__shipname__", "");
        }
        if (ship.getType() != 0) {
            tmp = tmp.replace("__shiptype__", ShipType.TYPE.get(ship.getType()));
        } else {
            tmp = tmp.replace("__shiptype__", "");
        }
        if (ship.getCallSign() != null) {
            tmp = tmp.replace("__callsign__", ship.getCallSign());
        } else {
            tmp = tmp.replace("__callsign__", "");
        }
        if (ship.getMmsi() != 0) {
            tmp = tmp.replace("__mmsi__", Integer.toString(ship.getMmsi()));
            long seconds = Calendar.getInstance().getTimeInMillis()
                    - timestamps.get(ship.getMmsi()).getTimeInMillis();
            tmp = tmp.replace("__seconds__", Long.toString(seconds / 1000) + " s");
        } else {
            tmp = tmp.replace("__mmsi__", "");
        }
        if (ship.getImo() != 0) {
            tmp = tmp.replace("__imo__", Integer.toString(ship.getImo()));
        } else {
            tmp = tmp.replace("__imo__", "");
        }
        if (ship.getLength() != 0) {
            tmp = tmp.replace("__length__", Float.toString(ship.getLength()) + " m");
        } else {
            tmp = tmp.replace("__length__", "");
        }
        if (ship.getWidth() != 0) {
            tmp = tmp.replace("__width__", Float.toString(ship.getWidth()) + " m");
        } else {
            tmp = tmp.replace("__width__", "");
        }
        if (ship.getDraught() != 0) {
            tmp = tmp.replace("__draught__", Float.toString(ship.getDraught()) + " m");
        } else {
            tmp = tmp.replace("__draught__", "");
        }
        if (ship.getNavigationalStatus() != 0) {
            tmp = tmp.replace("__status__", Integer.toString(ship.getNavigationalStatus()));
        } else {
            tmp = tmp.replace("__status__", "");
        }
        if (ship.getSog() != 0) {
            tmp = tmp.replace("__sog__", nf.format(ship.getSog()) + " Kn");
        } else {
            tmp = tmp.replace("__sog__", "");
        }
        if (ship.getCog() != 0 && ship.getCog() != 511) {
            tmp = tmp.replace("__cog__", (int) ship.getCog() + " °");
        } else {
            tmp = tmp.replace("__cog__", "");
        }
        if (ship.getDestination() != null) {
            tmp = tmp.replace("__destination__", ship.getDestination());
        } else {
            tmp = tmp.replace("__destination__", "");
        }
        if (ship.getETA() != null) {
            tmp = tmp.replace("__eta__", dt.format(ship.getETA().getTime()));
        } else {
            tmp = tmp.replace("__eta__", "");
        }
        if (ship.getLatitude() != 0) {
            tmp = tmp.replace("__latitude__", nf.format(ship.getLatitude()));
        } else {
            tmp = tmp.replace("__latitude__", "");
        }
        if (ship.getLongitude() != 0) {
            tmp = tmp.replace("__longitude__", nf.format(ship.getLongitude()));
        } else {
            tmp = tmp.replace("__longitude__", "");
        }
        if (ship.getMmsi() != 0) {
            String mmsiStr = Integer.toString(ship.getMmsi());
            String mid = mmsiStr.substring(0, 3);
            tmp = tmp.replace("__country__", midMap.get(new Integer(mid)));
        } else {
            tmp = tmp.replace("__country__", "");
        }
        if (ship.getMmsi() != 0) {
            tmp = tmp.replace("photo_keywords:", "photo_keywords:" + ship.getMmsi());
        }
        return tmp;
!!!150274.java!!!	clip() : void
        aisStationLayer.getDisplayLayer().setEnabled(false);
!!!150402.java!!!	unClip() : void
        aisStationLayer.getDisplayLayer().setEnabled(true);
