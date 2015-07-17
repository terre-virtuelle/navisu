
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.base.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.instruments.ais.base.Ais;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.GpsTrackPolygonServices;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.ProducedEvent;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author serge
 */
public class AisImpl
        implements Ais, AisServices, ComponentState {

    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    SpeakerServices speakerServices;

    @ProducedEvent
    protected AisCreateTargetEvent aisCreateTargetEvent;
    @ProducedEvent
    protected AisUpdateTargetEvent aisUpdateTargetEvent;
    @ProducedEvent
    protected AisDeleteTargetEvent aisDeleteTargetEvent;
    @ProducedEvent
    protected AisCreateStationEvent aisCreateStationEvent;
    @ProducedEvent
    protected AisUpdateStationEvent aisUpdateStationEvent;
    @ProducedEvent
    protected AisDeleteStationEvent aisDeleteStationEvent;

    protected boolean on = false;
    protected Ship ship;
    protected BaseStation station;
    protected HashMap<Integer, Ship> ships;
    protected HashMap<Integer, BaseStation> stations;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, String> midMap;
    protected final String MID_MAP = "data/ais/mmsi.txt";
    protected NumberFormat nf = new DecimalFormat("0.###");
    protected SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");
    protected TimerTask task;
    protected static final long TIME_OUT = 300000;
    protected static final long DELAY = 300000;
    protected static final String DATA_PATH = System.getProperty("user.dir").replace("\\", "/");
    protected static final String DELETE_TARGET_SOUND = "/data/sounds/mechanic.wav";

    ComponentManager cm;
    ComponentEventSubscribe<AIS01Event> ais1ES;
    ComponentEventSubscribe<AIS02Event> ais2ES;
    ComponentEventSubscribe<AIS03Event> ais3ES;
    ComponentEventSubscribe<AIS04Event> ais4ES;
    ComponentEventSubscribe<AIS05Event> ais5ES;
    int i = 0;

    public AisImpl() {
    }

    @Override
    public void componentInitiated() {
        cm = ComponentManager.componentManager;
        ais1ES = cm.getComponentEventSubscribe(AIS01Event.class);
        ais2ES = cm.getComponentEventSubscribe(AIS02Event.class);
        ais3ES = cm.getComponentEventSubscribe(AIS03Event.class);
        ais4ES = cm.getComponentEventSubscribe(AIS04Event.class);
        ais5ES = cm.getComponentEventSubscribe(AIS05Event.class);

        ships = new HashMap<>();
        stations = new HashMap<>();
        midMap = new HashMap<>();
        timestamps = new HashMap<>();
        loadMaritimeIdentificationDigits();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {
        on = true;
        ais1ES.subscribe(new AIS01Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS01 ais = (AIS01) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .heading(ais.getHeading()).cog(ais.getCog())
                            .sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    aisCreateTargetEvent.notifyAisMessageChanged(ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setHeading(ais.getHeading());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    ship.setRot(ais.getRot());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setNavigationalStatus(ais.getNavigationalStatus());
                    aisUpdateTargetEvent.notifyAisMessageChanged(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais2ES.subscribe(new AIS02Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS02 ais = (AIS02) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .heading(ais.getHeading()).cog(ais.getCog())
                            .sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    aisCreateTargetEvent.notifyAisMessageChanged(ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setHeading(ais.getHeading());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    ship.setRot(ais.getRot());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setNavigationalStatus(ais.getNavigationalStatus());
                    aisUpdateTargetEvent.notifyAisMessageChanged(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS03Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS03 ais = (AIS03) data;
                //System.out.println("reception ais " + ais);
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .heading(ais.getHeading()).cog(ais.getCog())
                            .sog(ais.getSog()).rot(ais.getRot())
                            .latitude(ais.getLatitude()).longitude(ais.getLongitude())
                            .navigationalStatus(ais.getNavigationalStatus())
                            .build();
                    ships.put(mmsi, ship);
                    aisCreateTargetEvent.notifyAisMessageChanged(ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setHeading(ais.getHeading());
                    ship.setCog(ais.getCog());
                    ship.setSog(ais.getSog());
                    ship.setRot(ais.getRot());
                    ship.setLatitude(ais.getLatitude());
                    ship.setLongitude(ais.getLongitude());
                    ship.setNavigationalStatus(ais.getNavigationalStatus());
                    aisUpdateTargetEvent.notifyAisMessageChanged(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        ais4ES.subscribe(new AIS04Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                AIS04 ais = (AIS04) data;
                //System.out.println("reception ais " + ais);
                int mmsi = ais.getMMSI();

                if (!stations.containsKey(mmsi)) {
                    station = new BaseStation(
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getDate(),
                            ais.getTypeOfElectronicPositionFixingDevice());
                    stations.put(mmsi, station);
                    aisCreateStationEvent.notifyAisMessageChanged(station);
                } else {
                    station = stations.get(mmsi);
                    station.setDate(ais.getDate());
                    station.setEpfd(ais.getTypeOfElectronicPositionFixingDevice());
                    aisUpdateStationEvent.notifyAisMessageChanged(station);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais5ES.subscribe(new AIS05Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS05 ais = (AIS05) data;
                int mmsi = ais.getMMSI();
                if (!ships.containsKey(mmsi)) {
                    ship = ShipBuilder.create()
                            .mmsi(ais.getMMSI())
                            .destination(ais.getDestination())
                            .shipType(ais.getShipType())
                            .name(ais.getName())
                            .build();
                    ships.put(mmsi, ship);
                } else {
                    ship = ships.get(mmsi);
                    ship.setShipType(ais.getShipType());
                    ship.setName(ais.getShipName());
                    ship.setETA(ais.getETA());
                    ship.setDestination(ais.getDestination());
                    aisUpdateTargetEvent.notifyAisMessageChanged(ship);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
        Timer timer = new Timer();
        scheduleLostTarget();
        timer.schedule(task, DELAY, TIME_OUT);
    }

    @Override
    public void off() {
        on = false;
        ais1ES.unsubscribe(new AIS01Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
            }
        });
        ais2ES.subscribe(new AIS02Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
            }
        });
        ais3ES.subscribe(new AIS03Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
            }
        });
        ais4ES.subscribe(new AIS04Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
            }
        });
        ais5ES.subscribe(new AIS05Event() {
            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
            }
        });
    }

    private void loadMaritimeIdentificationDigits() {
        String[] midEntries;
        try (BufferedReader br = new BufferedReader(new FileReader(MID_MAP))) {
            String s;
            while ((s = br.readLine()) != null) {
                if (!s.isEmpty()) {
                    midEntries = s.split(",");
                    midMap.put(new Integer(midEntries[0].trim()), midEntries[1].trim());

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AisImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public HashMap<Integer, Ship> getShips() {
        return (HashMap) ships.clone();
    }

    @Override
    public Map<Integer, BaseStation> getStations() {
        return (HashMap) stations.clone();
    }

    @Override
    public Map<Integer, Calendar> getTimestamps() {
        return timestamps;
    }

    @Override
    public Map<Integer, String> getMidMap() {
        return midMap;
    }

    private void scheduleLostTarget() {
        task = new TimerTask() {
            @Override
            public void run() {
                Set<Integer> targets = timestamps.keySet();
                targets.stream().filter((i) -> ((Calendar.getInstance().getTimeInMillis() - timestamps.get(i).getTimeInMillis()) > TIME_OUT)).map((_item) -> {
                    //   instrumentDriverManagerServices.open(DATA_PATH + DELETE_TARGET_SOUND, "true", "1");
                    Ship ship = ships.get(_item);
                    String name;
                    if (ship != null) {
                        name = ship.getName();
                        if (name == null) {
                            name = " ";
                        } else {
                            name = " navire " + name;
                        }
                        if (i < 1000) {
                            speakerServices.read("Perte d'une cible A I ES ! " + name);
                            i++;
                        }  
                    }
                    return _item;
                }).map((_item) -> {
                    ships.keySet().stream().forEach((s) -> {
                        Ship ship = ships.get(s);
                        if (s == ship.getMMSI()) {
                            aisDeleteTargetEvent.notifyAisMessageChanged(ship);
                        }
                    });
                    return _item;
                }).forEach((_item) -> {
                    stations.keySet().stream().forEach((s) -> {
                        BaseStation station = stations.get(s);
                        if (s == station.getMMSI()) {
                            aisDeleteStationEvent.notifyAisMessageChanged(station);
                        }
                    });
                });
            }
        };
    }
}