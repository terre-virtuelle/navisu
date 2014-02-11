  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.ais.locator.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.AIS135;
import bzh.terrevirtuelle.navisu.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.nmea.model.AIS5;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.ship.Ship;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.AisLayer;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.ShipView;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.ShipViewFactory;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.impl.ShipDefaultViewImpl;
import bzh.terrevirtuelle.navisu.world.ais.locator.view.impl.ShipViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocatorController {

    protected static final Logger LOGGER = Logger.getLogger(AisLocatorController.class.getName());

    protected LayerManager<Layer> layerManager;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);

    protected AisLayer aisLayer;
    private double latitude;
    private double longitude;
    private double cog;
    private double heading;
    private int type;
    private final WorldWindow wwd;
    private Map<Integer, Ship> ships;
    private Ship ship;
    private ShipView shipView;

    public AisLocatorController(GeoViewServices geoViewServices) {
        ships = new HashMap<>();

        aisLayer = new AisLayer();
        wwd = GeoWorldWindViewImpl.getWW();
        this.layerManager = (LayerManager<Layer>) ((GeoView) geoViewServices.getDisplayService()).getLayerManager();
        this.layerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(aisLayer));

        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS1 ais = (AIS1) data;
                int mmsi = ais.getMMSI();
                if (ships.containsKey(mmsi)) {
                    ship = ships.get(mmsi);
                    shipUpdate(ais);
                } else {
                    ship = new Ship(ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    if (ship.getType() == 1 || ship.getType() == 6 || ship.getSog() < 0.2) {
                        shipBuildDefault(ais, 1);
                    } else {
                        shipBuild(ais, 1);
                    }
                    ships.put(mmsi, ship);
                }
            }
        });
        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                System.out.println("type : 2");
                int mmsi = ais.getMMSI();
                if (ships.containsKey(mmsi)) {
                    ship = ships.get(mmsi);
                    shipUpdate(ais);
                } else {
                    shipBuild(ais, 2);
                    ships.put(mmsi, ship);
                }
            }
        });
        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                System.out.println("type : 3");
                int mmsi = ais.getMMSI();
                if (ships.containsKey(mmsi)) {
                    ship = ships.get(mmsi);
                    shipUpdate(ais);
                } else {
                    shipBuild(ais, 3);
                    ships.put(mmsi, ship);
                }
            }
        });
        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                System.out.println("type : 4");
                int mmsi = ais.getMMSI();
                //   if (ships.containsKey(mmsi)) {
                //       ship = ships.get(mmsi);
                //shipUpdate(ais);
                //   } else {
                // shipBuild4(ais); // faire un buildDefault pour les stations fixes
                ships.put(mmsi, ship);
                //  }
            }
        });
        ais5ES.subscribe(new AIS5Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS5 ais = (AIS5) data;
                int mmsi = ais.getMMSI();
                if (ships.containsKey(mmsi)) {
                    ship = ships.get(mmsi);
                    //   shipUpdate5(ais);
                }
            }
        });

    }

    private void shipUpdate(AIS135 ais) {

        ship.setLatitude(ais.getLatitude());
        ship.setLongitude(ais.getLongitude());
        ship.setCog(ais.getCog());
        ship.setHeading(ais.getHeading());
    }

    private void shipUpdate5(AIS135 ais) {

        ship.setImo(ais.getImo());
        ship.setCallSign(ais.getCallsign());
        ship.setName(ais.getShipname());
        ship.setType(ais.getShipType());
        ship.setWidth(ais.getWidth());
        ship.setLength(ais.getLength());
        ship.setElectronicPositionDevice(ais.getElectronicPositionDevice());
        ship.setETA(ais.getETA());
        ship.setDraught(ais.getDraught());
        ship.setDestination(ais.getDestination());
    }

    private void shipBuild4(AIS4 ais) {

        ship = new Ship(ais.getMMSI(), ais.getLatitude(), ais.getLongitude());
        shipView = new ShipViewFactory(ship, 4).buildDefault();
        //   aisLayer.add(shipView);
    }

    private void shipBuildDefault(AIS135 ais, int aisType) {

        shipView = (ShipDefaultViewImpl) new ShipViewFactory(ship, aisType).buildDefault();
        aisLayer.add((ShipDefaultViewImpl) shipView);
        subscribe();

    }

    private void shipBuild(AIS135 ais, int aisType) {

        shipView = (ShipViewImpl) new ShipViewFactory(ship, aisType).build();
        shipView.setHeading(ship.getHeading());
        subscribe();
        aisLayer.add((ShipViewImpl) shipView);

    }

    private void subscribe() {
        ship.latitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            shipView.setLatitude(t1.doubleValue());
            wwd.redrawNow();
        });
        ship.longitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            shipView.setLongitude(t1.doubleValue());
            wwd.redrawNow();
        });

        ship.cogProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            cog = t1.doubleValue();
            shipView.setHeading(cog);
            wwd.redrawNow();
        });
        ship.headingProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            heading = t1.doubleValue();
            shipView.setHeading(heading);
            wwd.redrawNow();
        });
        ship.typeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            type = t1.intValue();
            shipView.setType(type);
            wwd.redrawNow();
        });
    }
}
