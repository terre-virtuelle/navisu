/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.sector.impl;

import gov.nasa.worldwind.AbstractSceneController;
import gov.nasa.worldwind.SceneController;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceText;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.util.SectorSelector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import bzh.terrevirtuelle.navisu.instruments.gpstrack.sector.GpsTrackSector;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.sector.GpsTrackSectorServices;
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
public class GpsTrackSectorImpl implements GpsTrackSector,
		GpsTrackSectorServices, InstrumentDriver, ComponentState {

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

	protected static final String GROUP = "Watch sector";
	protected Ship watchedShip;

	protected boolean on = false;
	private final String NAME = "GpsTrackSector";

	protected SectorSelector selector;
	protected RenderableLayer sectorLayer;
	protected boolean alarmOn = false;
	protected boolean isTextOn = false;
	protected SurfaceText text = null;

	@Override
	public void componentInitiated() {

		watchedShip = new Ship();
		watchedShip.setMMSI(999999999);
		
		wwd = GeoWorldWindViewImpl.getWW();
		layerTreeServices.createGroup(GROUP);
		geoViewServices.getLayerManager().createGroup(GROUP);
		
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
	public void on(String ... files) {

		if (on == false) {
			on = true;
			
			selector = new SectorSelector(GeoWorldWindViewImpl.getWW());
			selector.enable();
			// couleur du selector : bleu
			selector.setBorderColor(WWUtil.decodeColorRGBA("0000FFFF"));

			/*
			 * selector.addPropertyChangeListener(SectorSelector.SECTOR_PROPERTY,
			 * new PropertyChangeListener() { public void
			 * propertyChange(PropertyChangeEvent evt) { Sector sector = (Sector)
			 * evt.getNewValue(); System.out.println(sector != null ? sector :
			 * "no sector"); } });
			 */

			sectorLayer = (RenderableLayer) selector.getLayer();
			
			sectorLayer.setName("Watch sector");
			geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayer));
			layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayer));
			
			// souscription aux événements GPS
			ggaES.subscribe(new GGAEvent() {

				@Override
				public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

					GGA data = (GGA) d;
					if (on) {

						watchedShip.setLatitude(data.getLatitude());
						watchedShip.setLongitude(data.getLongitude());

						watchTarget(selector.getSector(), watchedShip);

					}

				}
			});

			/*
			 * vtgES.subscribe(new VTGEvent() {
			 * 
			 * @Override public <T extends NMEA> void notifyNmeaMessageChanged(T
			 * d) { VTG data = (VTG) d; if (on) { System.out.println(data);
			 * ship.setSog(10*data.getSog()); ship.setCog(10*data.getCog());
			 * createTarget(ship); if (gShipCreated) { updateTarget(ship);} else
			 * {createTarget(ship); gShipCreated = true;}
			 * 
			 * 
			 * }
			 * 
			 * } });
			 */
			/*
			 * rmcES.subscribe(new RMCEvent() {
			 * 
			 * @Override public <T extends NMEA> void notifyNmeaMessageChanged(T
			 * d) { RMC data = (RMC) d; if (on) { System.out.println(data);
			 * ship.setLatitude(data.getLatitude());
			 * ship.setLongitude(data.getLongitude());
			 * ship.setSog(10*data.getSog()); ship.setCog(10*data.getCog()); if
			 * (gShipCreated) { updateTarget(ship);} else {createTarget(ship);
			 * gShipCreated = true;}
			 * 
			 * }
			 * 
			 * } });
			 */
		}

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

	private void watchTarget(Sector sector, Ship target) {

		if (sector != null
				&& target != null
				&& sector.containsDegrees(target.getLatitude(),
						target.getLongitude())) {
			System.err
					.println("============ W A R N I N G ============ Ship with MMSI #"
							+ target.getMMSI() + " is inside Sector#1");
			if (!isTextOn) {
				textOn(sector);
			}

			if (!alarmOn) {
				MediaPlayer mediaPlayer;
				javafx.scene.media.Media media;
				String userDir = System.getProperty("user.dir");
				userDir = userDir.replace("\\", "/");
				String url = userDir + "/data/sounds/alarm10.wav";
				media = new Media("file:///" + url);
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true);
				//mediaPlayer.setCycleCount(1);
				alarmOn = true;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						alarmOn = false;
					}
				}, 7500);
			}
		}
		if (sector != null
				&& target != null
				&& !sector.containsDegrees(target.getLatitude(),
						target.getLongitude())) {
			textOff(sector);
		}
	}

	private void textOn(Sector sector) {
		text = new SurfaceText("!", new Position(sector.getCentroid(), 0));
		// couleur du texte : jaune
		text.setColor(WWUtil.decodeColorRGBA("FFFF00FF"));
		if (sectorLayer.isEnabled()) {
			sectorLayer.addRenderable(text);
		} else {
			sectorLayer.setEnabled(true);
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(true);
			if (!layerTreeServices.getCheckBoxTreeItems().get(20).isSelected()) {layerTreeServices.getCheckBoxTreeItems().get(20).setSelected(true);}
			sectorLayer.addRenderable(text);
		}

		isTextOn = true;
	}

	private void textOff(Sector sector) {
		if (text != null && sectorLayer.isEnabled()) {
			sectorLayer.removeRenderable(text);
		}
		isTextOn = false;
	}

}