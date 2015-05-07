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
import gov.nasa.worldwind.geom.LatLon;
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
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwindx.examples.util.SectorSelector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisDeleteTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateStationEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.sector.GpsTrackSector;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.sector.GpsTrackSectorServices;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.GpsTrackPlotter;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.plotter.GpsTrackPlotterServices;

import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import com.vividsolutions.jts.operation.valid.IsValidOp;

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

	@UsedService
	AisServices aisServices;

	ComponentManager cm;
	ComponentEventSubscribe<GGAEvent> ggaES;
	ComponentEventSubscribe<RMCEvent> rmcES;
	ComponentEventSubscribe<VTGEvent> vtgES;

	ComponentEventSubscribe<AisCreateTargetEvent> aisCTEvent;
	ComponentEventSubscribe<AisUpdateTargetEvent> aisUTEvent;

	protected WorldWindow wwd;

	protected static final String GROUP = "Watch sectors";

	protected Ship watchedShip;

	protected boolean on = false;
	private final String NAME = "GpsTrackSector";

	protected LinkedList<SectorSelector> selectors;
	protected LinkedList<RenderableLayer> sectorLayers;
	protected boolean alarmOn = false;
	protected LinkedList<Boolean> isTextOn;
	protected LinkedList<Boolean> isTextOnAis;
	protected LinkedList<SurfaceText> text;
	protected LinkedList<SurfaceText> textAis;
	protected int nbSelector = 0;
	protected LinkedList<Ship> aisShips;

	@Override
	public void componentInitiated() {

		watchedShip = new Ship();
		watchedShip.setMMSI(999999999);
		aisShips = new LinkedList<Ship>();

		wwd = GeoWorldWindViewImpl.getWW();
		layerTreeServices.createGroup(GROUP);
		geoViewServices.getLayerManager().createGroup(GROUP);

		layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(22).setSelected(false);

		cm = ComponentManager.componentManager;
		ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
		rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
		vtgES = cm.getComponentEventSubscribe(VTGEvent.class);

		aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
		aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);

		isTextOn = new LinkedList<Boolean>();
		for (int k=0; k<100; k++) {isTextOn.add(false);}

		isTextOnAis = new LinkedList<Boolean>();
		for (int k=0; k<100; k++) {isTextOnAis.add(false);}

		text = new LinkedList<SurfaceText>();
		for (int k=0; k<100; k++) {text.add(null);}

		textAis = new LinkedList<SurfaceText>();
		for (int k=0; k<100; k++) {textAis.add(null);}

		selectors = new LinkedList<SectorSelector>();
		sectorLayers = new LinkedList<RenderableLayer> ();

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

			// souscription aux événements GPS
			ggaES.subscribe(new GGAEvent() {

				@Override
				public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

					GGA data = (GGA) d;

					if (on) {

						watchedShip.setLatitude(data.getLatitude());
						watchedShip.setLongitude(data.getLongitude());

						//code déplacé dans les évènements AIS
						/*if (layerTreeServices.getCheckBoxTreeItems().get(22).isSelected()) {
							selectors.add(new SectorSelector(GeoWorldWindViewImpl.getWW()));
							selectors.getLast().enable();
							nbSelector++;
							// couleur du selector : bleu
							selectors.getLast().setBorderColor(WWUtil.decodeColorRGBA("0000FFFF"));
							RenderableLayer TempLayer = (RenderableLayer) ((selectors.getLast()).getLayer());
							TempLayer.setName("Watch sector#"+nbSelector);
							sectorLayers.add(TempLayer);
							geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayers.getLast()));
							layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayers.getLast()));
							System.out.println(sectorLayers.getLast().getName()+" created successfully."+"\n");
							System.out.println("NbSelector = " + nbSelector + "\n");
							layerTreeServices.getCheckBoxTreeItems().get(22).setSelected(false);
						}*/

						for (int j=0; j<selectors.size(); j++) {watchTarget(j, watchedShip);}

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

		if (!aisServices.isOn()) {aisServices.on();}
			
		aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedData) -> {
			createTarget(updatedData);
			for (int j=0; j<selectors.size(); j++) {
				watchTargetAis(j, aisShips);
				}
			});
		aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedData) -> {
			updateTarget(updatedData);
			for (int j=0; j<selectors.size(); j++) {
				watchTargetAis(j, aisShips);
				}
			
			if (layerTreeServices.getCheckBoxTreeItems().get(22).isSelected()) {
				selectors.add(new SectorSelector(GeoWorldWindViewImpl.getWW()));
				selectors.getLast().enable();
				nbSelector++;
				// couleur du selector : bleu
				selectors.getLast().setBorderColor(WWUtil.decodeColorRGBA("0000FFFF"));
				RenderableLayer TempLayer = (RenderableLayer) ((selectors.getLast()).getLayer());
				TempLayer.setName("Watch sector#"+nbSelector);
				sectorLayers.add(TempLayer);
				geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayers.getLast()));
				layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(sectorLayers.getLast()));
				System.out.println(sectorLayers.getLast().getName()+" created successfully."+"\n");
				System.out.println("NbSelector = " + nbSelector + "\n");
				layerTreeServices.getCheckBoxTreeItems().get(22).setSelected(false);
			}
			
			});
		
	}

	private void createTarget(Ship target) {
		Ship aisShip = new Ship();
		aisShip.setMMSI(target.getMMSI());
		aisShip.setLatitude(target.getLatitude());
		aisShip.setLongitude(target.getLongitude());
		aisShips.add(aisShip);
		// Enlever les commentaires pour voir les messages AIS
		//System.out.println("Ship with MMSI " + aisShip.getMMSI() + " created - position lat " + aisShip.getLatitude() + " and lon " + aisShip.getLongitude());
	}

	private void updateTarget(Ship target) {
		for (int i=0; i<aisShips.size(); i++) {
			if (aisShips.get(i).getMMSI() == target.getMMSI()) {
				Ship resu = new Ship();
				resu.setLatitude(target.getLatitude());
				resu.setLongitude(target.getLongitude());
				resu.setMMSI(target.getMMSI());
				aisShips.set(i, resu);
				// Enlever les commentaires pour voir les messages AIS
				//System.out.println("Ship with MMSI " + resu.getMMSI() + " updated - position lat " + resu.getLatitude() + " and lon " + resu.getLongitude());
			}
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

	private void watchTarget(int i, Ship target) {

		Sector sector = selectors.get(i).getSector();
		if (sector != null
				&& target != null
				&& sector.containsDegrees(target.getLatitude(),
						target.getLongitude())) {
			System.err
			.println("============ W A R N I N G ============ Ship with MMSI #"
					+ target.getMMSI() + " is inside Sector#"+ (i+1));

			if (!layerTreeServices.getCheckBoxTreeItems().get(19).isSelected()) {layerTreeServices.getCheckBoxTreeItems().get(19).setSelected(true);}
			if (!layerTreeServices.getCheckBoxTreeItems().get(20).isSelected()) {layerTreeServices.getCheckBoxTreeItems().get(20).setSelected(true);}
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(false);
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(true);

			if (!(isTextOn.get(i))) {
				wwd.getView().setEyePosition(new Position(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), 20000));
				textOn(sector, i);
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

			if (!layerTreeServices.getCheckBoxTreeItems().get(21).isSelected()) {
				layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(true);
			}
		}
		if (sector != null
				&& target != null
				&& !sector.containsDegrees(target.getLatitude(),
						target.getLongitude())) {
			textOff(sector, i);
		}
	}

	private void watchTargetAis(int i, LinkedList<Ship> targets) {

		Sector sector = selectors.get(i).getSector();
		boolean putTextOn = false;
		int index = 0;
		for (Ship target : targets) {
			if (sector != null && target != null && sector.containsDegrees(target.getLatitude(), target.getLongitude())) {
				System.err.println("============ W A R N I N G ============ Ship with MMSI #" + target.getMMSI() + " is inside Sector#"+ (i+1));
				putTextOn = true;
				index = targets.indexOf(target);
			}
		}

		if (!(isTextOnAis.get(i)) && putTextOn) {
			wwd.getView().setEyePosition(new Position(LatLon.fromDegrees(targets.get(index).getLatitude(), targets.get(index).getLongitude()), 20000));
			textOnAis(sector, i);
		}

		if (!alarmOn && putTextOn) {
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

		if (isTextOnAis.get(i) && !putTextOn) {
			textOffAis(sector, i);
		}
	}

	private void textOn(Sector sector, int i) {
		text.set(i, new SurfaceText("S" + (i+1), new Position(sector.getCentroid(), 0)));
		// couleur du texte : jaune
		text.get(i).setColor(WWUtil.decodeColorRGBA("FFFF00FF"));
		if (sectorLayers.get(i).isEnabled()) {
			sectorLayers.get(i).addRenderable(text.get(i));
		} else {
			sectorLayers.get(i).setEnabled(true);
			if (!layerTreeServices.getCheckBoxTreeItems().get(20).isSelected()) {layerTreeServices.getCheckBoxTreeItems().get(20).setSelected(true);}
			if (!layerTreeServices.getCheckBoxTreeItems().get(19).isSelected()) {layerTreeServices.getCheckBoxTreeItems().get(19).setSelected(true);}
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(false);
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(true);
			sectorLayers.get(i).addRenderable(text.get(i));
		}

		isTextOn.set(i, true);
	}

	private void textOff(Sector sector, int i) {
		if (text.get(i) != null && sectorLayers.get(i).isEnabled()) {
			sectorLayers.get(i).removeRenderable(text.get(i));
		}
		isTextOn.set(i, false);
	}


	private void textOnAis(Sector sector, int i) {
		textAis.set(i, new SurfaceText("S" + (i+1), new Position(sector.getCentroid(), 0)));
		// couleur du texte : jaune
		textAis.get(i).setColor(WWUtil.decodeColorRGBA("FFFF00FF"));
		if (sectorLayers.get(i).isEnabled()) {
			sectorLayers.get(i).addRenderable(textAis.get(i));
		} else {
			sectorLayers.get(i).setEnabled(true);
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(false);
			layerTreeServices.getCheckBoxTreeItems().get(21).setSelected(true);
			sectorLayers.get(i).addRenderable(textAis.get(i));
		}

		isTextOnAis.set(i, true);
	}

	private void textOffAis(Sector sector, int i) {
		if (textAis.get(i) != null && sectorLayers.get(i).isEnabled()) {
			sectorLayers.get(i).removeRenderable(textAis.get(i));
		}
		isTextOnAis.set(i, false);
	}
	
}