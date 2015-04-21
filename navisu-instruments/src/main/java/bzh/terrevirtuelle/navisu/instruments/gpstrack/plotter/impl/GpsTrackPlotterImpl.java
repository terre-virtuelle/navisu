/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.impl;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;

import java.util.HashMap;
import java.util.Map;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.GpsTrackPlotter;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.GpsTrackPlotterServices;

import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsTrackPlotterImpl
        implements GpsTrackPlotter, GpsTrackPlotterServices, InstrumentDriver, ComponentState {
    
    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    DpAgentServices dpAgentServices;

    @UsedService
    GuiAgentServices guiAgentServices;

    @UsedService
    LayerTreeServices layerTreeServices;
    
    ComponentManager cm;
    ComponentEventSubscribe<GGAEvent> ggaES;
    ComponentEventSubscribe<RMCEvent> rmcES;
    ComponentEventSubscribe<VTGEvent> vtgES;
    
    protected WorldWindow wwd;
    protected RenderableLayer gpsTrackLayer;
    protected static final String GROUP = "Target";
    protected Ship ship;
    protected GShip gShip;
    protected boolean gShipCreated = false;

    protected boolean on = false;
    private final String NAME = "GpsTrackPlotter";

    @Override
    public void componentInitiated() {


        ship = new Ship();
        
        wwd = GeoWorldWindViewImpl.getWW();
        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);

        this.gpsTrackLayer = new RenderableLayer();
        gpsTrackLayer.setName("Target");
        
        geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(gpsTrackLayer));
        layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(gpsTrackLayer));
        
        

        cm = ComponentManager.componentManager;
        ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
        rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
        vtgES = cm.getComponentEventSubscribe(VTGEvent.class);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void on() {

        if (on == false) {
            on = true;

            // souscription aux événements GPS
            ggaES.subscribe(new GGAEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

                    GGA data = (GGA) d;
                    if (on) {
                    	System.out.println(data);
                    	ship.setLatitude(data.getLatitude());
                    	ship.setLongitude(data.getLongitude());
                        if (gShipCreated) {
                    		updateTarget(ship);
                        	} 
                    			else {createTarget(ship);
                    					gShipCreated = true;}
                    	
                    }


                }
            });

           /* vtgES.subscribe(new VTGEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    VTG data = (VTG) d;
                    if (on) {
                    	System.out.println(data);
                    	ship.setSog(10*data.getSog());
                    	ship.setCog(10*data.getCog());
                    	createTarget(ship);
                    	if (gShipCreated) {
                    		updateTarget(ship);} 
                    			else {createTarget(ship);
                    					gShipCreated = true;}
                    	
                    	
                    }

                }
            });*/
           /* rmcES.subscribe(new RMCEvent() {

                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    RMC data = (RMC) d;
                    if (on) {
                    	System.out.println(data);
                    	ship.setLatitude(data.getLatitude());
                        ship.setLongitude(data.getLongitude());
                        ship.setSog(10*data.getSog());
                        ship.setCog(10*data.getCog());
                        if (gShipCreated) {
                    		updateTarget(ship);} 
                    			else {createTarget(ship);
                    					gShipCreated = true;}
                        
                    }

                }
            });*/
        }

        
    }

    private void createTarget(Ship target) {
        gShip = new GShip(target);
        if (target.getLatitude() != 0.0 && target.getLongitude() != 0.0) {
            Renderable[] renderables = gShip.getRenderables();
            for (Renderable r : renderables) {
                gpsTrackLayer.addRenderable(r);
                
            }
            wwd.redrawNow();
        }
    }
    
    private void updateTarget(Ship target) {
          gShip.update(target);
          wwd.redrawNow();
      }
    
    @Override
    public void off() {
        // Pb dans la lib C3 ? objet non retiré de la liste 
        if (on == true) {
            on = false;

        }
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {

        return category.equals(NAME);
    }

    @Override
    public boolean isOn() {
        return on;
    }
}
