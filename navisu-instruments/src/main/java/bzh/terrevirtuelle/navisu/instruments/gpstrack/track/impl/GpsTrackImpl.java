/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.track.impl;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
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
import bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.GpsTrackPlotter;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.track.GpsTrackServices;
import gov.nasa.worldwind.geom.Angle;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsTrackImpl implements GpsTrackPlotter,
        GpsTrackServices, InstrumentDriver, ComponentState {
    
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
    protected ArrayList<Position> pathPositions;
    protected ShapeAttributes attrs;
    
    protected static final String GROUP = "GpsTrack";
    protected Path path;
    protected boolean pathCreated = false;
    protected boolean on = false;
    private final String NAME = "GpsTrack";
    protected double latitude;
    protected double longitude;
    
    @Override
    public void componentInitiated() {
        
        wwd = GeoWorldWindViewImpl.getWW();
        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);
        
        this.gpsTrackLayer = new RenderableLayer();
        gpsTrackLayer.setName("NMEA track");
        gpsTrackLayer.setEnabled(false);
        pathPositions = new ArrayList<>();
        
        geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(gpsTrackLayer));
        layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(gpsTrackLayer));
        
        layerTreeServices.search("GpsTrack").setSelected(true);
        
        attrs = new BasicShapeAttributes();
        attrs.setOutlineMaterial(Material.GREEN);
        attrs.setOutlineWidth(4);
        
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
    public void on(String... files) {
        
        if (on == false) {
            on = true;

            // souscription aux événements GPS
            ggaES.subscribe(new GGAEvent() {
                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    GGA data = (GGA) d;
                    if (on) {
                        latitude = data.getLatitude();
                        longitude = data.getLongitude();
                        run();
                    }
                }
            });
            rmcES.subscribe(new RMCEvent() {
                @Override
                public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                    RMC data = (RMC) d;
                    if (on) {
                        latitude = data.getLatitude();
                        longitude = data.getLongitude();
                        run();
                    }
                }
            });
        }
    }
    
    private void run() {
        if (pathCreated) {
            updatePath();
        } else {
            createPath();
        }
    }
    
    private void createPath() {
        path = new Path(pathPositions);
        path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        path.setVisible(true);
        path.setPathType(AVKey.GREAT_CIRCLE);
        path.setAttributes(attrs);
        gpsTrackLayer.addRenderable(path);
        wwd.redrawNow();
        pathCreated = true;
    }
    
    private void updatePath() {
        pathPositions.add(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), 10));
        path.setPositions(pathPositions);
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
