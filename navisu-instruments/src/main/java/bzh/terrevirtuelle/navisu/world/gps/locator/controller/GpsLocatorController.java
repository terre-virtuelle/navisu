 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.world.gps.locator.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GSVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VTGEvent;
import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.nmea.model.VTG;
import bzh.terrevirtuelle.navisu.ship.Ship;
import bzh.terrevirtuelle.navisu.world.gps.locator.view.GpsLayer;
import bzh.terrevirtuelle.navisu.world.gps.locator.view.ShipView;
import bzh.terrevirtuelle.navisu.world.gps.locator.view.ShipViewFactory;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;

import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class GpsLocatorController {

    protected static final Logger LOGGER = Logger.getLogger(GpsLocatorController.class.getName());

    protected LayerManager<Layer> layerManager;

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<GGAEvent> ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
    ComponentEventSubscribe<GSVEvent> gsvES = cm.getComponentEventSubscribe(GSVEvent.class);
    ComponentEventSubscribe<RMCEvent> rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
    ComponentEventSubscribe<VTGEvent> vtgES = cm.getComponentEventSubscribe(VTGEvent.class);
    
    protected GpsLayer aisLayer;
    protected Ship ship;
    private double latitude;
    private double longitude;
    private double cog;
    private final WorldWindow wwd;

    public GpsLocatorController(GeoViewServices geoViewServices) {
        aisLayer = new GpsLayer();
        ship = new Ship();
        wwd = GeoWorldWindViewImpl.getWW();
        
        ggaES.subscribe(new GGAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                GGA gga = (GGA) data;
                latitude = gga.getLatitude();
                longitude = gga.getLongitude();
                ship.setLatitude(latitude);
                ship.setLongitude(longitude);
            }
        });
        vtgES.subscribe(new VTGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                VTG vtg = (VTG) data;
                cog = vtg.getCog();
                ship.setCog((float) cog);
            }
        });

        ShipView shipView = new ShipViewFactory(ship).build();
        aisLayer.add(shipView);
        
        this.layerManager = (LayerManager<Layer>) ((GeoView) geoViewServices.getDisplayService()).getLayerManager();
        this.layerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(aisLayer));

        ship.latitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            latitude = t1.doubleValue();
            shipView.moveTo(Position.fromDegrees(latitude, longitude, 100));
            wwd.redrawNow();
        });
        ship.longitudeProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            longitude = t1.doubleValue();
            shipView.moveTo(Position.fromDegrees(latitude, longitude, 100));
            wwd.redrawNow();
        });
        ship.cogProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            cog = t1.doubleValue();
            shipView.setRotation(cog - 90);
            wwd.redrawNow();
        });
    }

    public GpsLayer getLayer() {
        return aisLayer;
    }

    public void setLayer(GpsLayer aisLayer) {
        this.aisLayer = aisLayer;
    }

}
