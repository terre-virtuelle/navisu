class RadarController
!!!169986.java!!!	RadarController(inout keyCode : KeyCode, inout keyCombination : KeyCombination.Modifier)
        super(keyCode, keyCombination);
        ships = new HashMap<>();
        transceivers = new HashMap<>();
        timestamps = new HashMap<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Radar-fullscreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        createOwnerShip();
        subscribe();
        setTarget(CENTER_X, CENTER_Y, RADIUS, "#ff0000");
!!!170114.java!!!	initialize(inout url : URL, inout rb : ResourceBundle) : void
        // TODO
!!!170242.java!!!	start() : void
        schedule();
!!!170370.java!!!	stop() : void
        fiveSecondsWonder.stop();
!!!170498.java!!!	createOwnerShip() : void
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/domain.properties"));
        } catch (IOException ex) {
            Logger.getLogger(RadarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // creation de l'objet metier
        ownerShip = ShipBuilder.create()
                .mmsi(new Integer(properties.getProperty("mmsi")))
                .name(properties.getProperty("name"))
                .latitude(new Float(properties.getProperty("latitude")))
                .longitude(new Float(properties.getProperty("longitude")))
                .cog(new Float(properties.getProperty("cog")))
                .sog(new Float(properties.getProperty("sog")))
                .heading(new Float(properties.getProperty("heading")))
                .country(properties.getProperty("country"))
                .width(new Float(properties.getProperty("width")))
                .length(new Float(properties.getProperty("length")))
                .draught(new Float(properties.getProperty("draught")))
                .shipType(new Integer(properties.getProperty("shipType")))
                .navigationalStatus(new Integer(properties.getProperty("navigationalStatus")))
                .electronicPositionDevice(new Integer(properties.getProperty("electronicPositionDevice")))
                .callSign(properties.getProperty("callSign")).build();
        latOwner = new Float(properties.getProperty("latitude"));
        lonOwner = new Float(properties.getProperty("longitude"));
        System.out.println("ownerShip " + ownerShip);
!!!170626.java!!!	subscribe() : void
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS1 ais = (AIS1) data;
                    int mmsi = ais.getMMSI();
                    if (!ships.containsKey(mmsi)) {
                        ship = ShipBuilder.create()
                                .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                                .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                                .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                                .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                                .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                                .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                                .eta(ais.getETA()).destination(ais.getDestination())
                                .build();
                        ships.put(mmsi, ship);
                        setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y  +(latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    // System.out.println("ais1ES.subscribe " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y +(latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                int mmsi = ais.getMMSI();

                if (!transceivers.containsKey(mmsi)) {
                    transceiver = new Transceiver(ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());
                    transceivers.put(mmsi, transceiver);
                }
                timestamps.put(mmsi, Calendar.getInstance());
                 setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#0000ff");
            }
            
        });
        ais5ES.subscribe(new AIS5Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS5 ais = (AIS5) data;
                int mmsi = ais.getMMSI();

                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI()).imo(ais.getImo()).name(ais.getShipname())
                            .heading(ais.getHeading()).cog(ais.getCog()).sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .width(ais.getWidth()).length(ais.getLength()).draught(ais.getDraught())
                            .shipType(ais.getShipType()).navigationalStatus(ais.getNavigationalStatus())
                            .electronicPositionDevice(ais.getElectronicPositionDevice()).callSign(ais.getCallsign())
                            .eta(ais.getETA()).destination(ais.getDestination()).build();
                    ships.put(mmsi, ship);
                     setTarget((int) (CENTER_X - (lonOwner - ais.getLongitude()) * RANGE),
                                (int) (CENTER_Y + (latOwner - ais.getLatitude()) * RANGE),
                                4.0, "#00ff00");
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        // souscription aux événements
        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                GGA data = (GGA) d;
                ownerShip.setLatitude(data.getLatitude());
                ownerShip.setLongitude(data.getLongitude());

                // mise à jour via le DPAgent
                // dpAgentServices.update(ship);
            }
        });
        
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                VTG data = (VTG) d;
                ownerShip.setCog(data.getCog());
                ownerShip.setSog(data.getSog());
                if (ownerShip.getSog() > 0.1) {
                    //   ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                // dpAgentServices.update(ship);
            }
        });
        rmcES.subscribe(new RMCEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                RMC data = (RMC) d;
                ownerShip.setLatitude(data.getLatitude());
                ownerShip.setLongitude(data.getLongitude());
                ownerShip.setCog(data.getCog());
                ownerShip.setSog(data.getSog());
                if (ownerShip.getSog() > 0.1) {
                    //  ship.setShapeId(0);
                }
                // mise à jour via la DPAgent
                // dpAgentServices.update(ship);
            }
        });
!!!170754.java!!!	schedule() : void
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(DURATION), (ActionEvent event) -> {
            faisceau.setRotate(route);
            route++;
            route %= 360;
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
!!!170882.java!!!	setTarget(in centerX : int, in centerY : int, in radius : double, in color : String) : void
        if (centerX <= 600 && centerX >= 100 && centerY <= 600 && centerY >= 100) {
            Circle circle = new Circle();
            circle.setCenterX(centerX);
            circle.setCenterY(centerY);
            circle.setRadius(radius);
            circle.setFill(Paint.valueOf(color));

            Platform.runLater(() -> {
                radar.getChildren().add(circle);
            });

            circle.setOnMouseClicked((MouseEvent me) -> {

                if (first) {
                    circle.setRadius(radius * 1.5);
                    first = false;
                } else {
                    circle.setRadius(radius);
                    first = true;
                }
            });
        }
