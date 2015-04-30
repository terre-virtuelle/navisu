/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.impl;

import gov.nasa.worldwind.AbstractSceneController;
import gov.nasa.worldwind.SceneController;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.TerrainProfileLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceShape;
import gov.nasa.worldwind.render.SurfaceText;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.MeasureToolPanel;
import gov.nasa.worldwindx.examples.util.SectorSelector;
import gov.nasa.worldwind.util.WWMath;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayers;
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
import bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.GpsTrackPolygon;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.GpsTrackPolygonServices;

import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import com.vividsolutions.jts.operation.valid.IsValidOp;

/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsTrackPolygonImpl implements GpsTrackPolygon,
		GpsTrackPolygonServices, InstrumentDriver, ComponentState {

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

	protected static final String GROUP = "Watch polygons";
	
	protected Ship watchedShip;

	protected boolean on = false;
	private final String NAME = "GpsTrackPolygon";

	protected boolean alarmOn = false;
	
	protected RenderableLayer polygonLayer;
	
	protected MeasureTool measureTool;
	protected MeasureToolController controller;
	protected LinkedList<MeasureTool> savedMeasureTool;
	
	protected boolean polygonActivated = false;
	protected boolean drawerActivated = false;
	protected int nbPolygon = 1;
	protected boolean freeHandActivated = false;
	protected boolean ellipseShapeActivated = false;
	protected boolean circleShapeActivated = false;
	protected boolean quadShapeActivated = false;
	protected boolean polyShapeActivated = false;

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
		
		layerTreeServices.getCheckBoxTreeItems().get(28).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(29).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(30).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(31).setSelected(true);
		layerTreeServices.getCheckBoxTreeItems().get(32).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(33).setSelected(false);
		layerTreeServices.getCheckBoxTreeItems().get(34).setSelected(false);
		
        measureTool = new MeasureTool(GeoWorldWindViewImpl.getWW());
        savedMeasureTool = new LinkedList<MeasureTool>();
		
        controller = new MeasureToolController();
        measureTool.setController(controller);
		
		measureTool.setFollowTerrain(true);
		
		controller.setUseRubberBand(true);
		
		polygonLayer = measureTool.getLayer();
		polygonLayer.setName("Polygon#1");
		
		geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
		layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));

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
						
						watchTarget(watchedShip, savedMeasureTool);
						
						if (layerTreeServices.getCheckBoxTreeItems().get(28).isSelected()) {
							measureTool.setArmed(true);
							controller.setArmed(true);
							if (!drawerActivated) {
								drawerActivated = true;
								System.out.println("Drawer ready.\n");
								}
							}
						
						else {
							measureTool.setArmed(false);
							controller.setArmed(false);
							if (drawerActivated) {
								drawerActivated = false;
								System.out.println("Drawer deactivated.\n");
								}
							}
						
						if (layerTreeServices.getCheckBoxTreeItems().get(29).isSelected()) {
							watchTarget(watchedShip);
							if (!polygonActivated) {
								System.out.println("Watch polygon activated.\n");
								polygonActivated = true;
								}
							}
						
						else {
							if (polygonActivated) {
								System.out.println("Watch polygon deactivated.\n");
								polygonActivated = false;
								}
							}
						
						if (layerTreeServices.getCheckBoxTreeItems().get(30).isSelected()) {
							measureTool.setArmed(false);
							controller.setArmed(false);
							savedMeasureTool.add(measureTool);
							nbPolygon++;
							controller = new MeasureToolController();
							measureTool = new MeasureTool(wwd);
							measureTool.setController(controller);
							
							if (layerTreeServices.getCheckBoxTreeItems().get(31).isSelected()) {measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);}
							if (layerTreeServices.getCheckBoxTreeItems().get(32).isSelected()) {measureTool.setMeasureShapeType(MeasureTool.SHAPE_ELLIPSE);}
							if (layerTreeServices.getCheckBoxTreeItems().get(33).isSelected()) {measureTool.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE);}
							if (layerTreeServices.getCheckBoxTreeItems().get(34).isSelected()) {controller.setFreeHand(true);}
	
							measureTool.setFollowTerrain(true);
							controller.setUseRubberBand(true);
							polygonLayer = measureTool.getLayer();
							polygonLayer.setName("Polygon#" + nbPolygon);
							geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
							layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
							layerTreeServices.getCheckBoxTreeItems().get(30).setSelected(false);
							layerTreeServices.getCheckBoxTreeItems().get(28).setSelected(false);
							System.out.println("New polygon (polygon#" + nbPolygon +") ready to be drawn.\n");
							}
						
						if (layerTreeServices.getCheckBoxTreeItems().get(34).isSelected()) {
							// Altitude en mètres
							double altitude = wwd.getView().getEyePosition().getAltitude();
							double spacing = (double) ((300*altitude)/(15000));
							controller.setFreeHand(true);
							controller.setFreeHandMinSpacing(spacing);
							System.out.println("Eye altitude : " + wwd.getView().getEyePosition().getAltitude());
							System.out.println("Points spacing : " + spacing + "\n");
							measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
							if (!freeHandActivated) {
								freeHandActivated = true;
								polyShapeActivated = true;
								circleShapeActivated = false;
								ellipseShapeActivated = false;
								layerTreeServices.getCheckBoxTreeItems().get(32).setSelected(false);
								layerTreeServices.getCheckBoxTreeItems().get(33).setSelected(false);
								layerTreeServices.getCheckBoxTreeItems().get(31).setSelected(true);
								System.out.println("Free hand mode activated.\n");
								}
						}
						else {
							controller.setFreeHand(false);
							if (freeHandActivated) {
								freeHandActivated = false;
								polyShapeActivated = true;
								circleShapeActivated = false;
								ellipseShapeActivated = false;
								System.out.println("Free hand mode deactivated.\n");
								}
						}
						
						
						// poly 31
						
						if (layerTreeServices.getCheckBoxTreeItems().get(31).isSelected()) {
							measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
							if (!polyShapeActivated) {
								polyShapeActivated = true;
								circleShapeActivated = false;
								ellipseShapeActivated = false;
								layerTreeServices.getCheckBoxTreeItems().get(32).setSelected(false);
								layerTreeServices.getCheckBoxTreeItems().get(33).setSelected(false);
								System.out.println("Polygon shape activated.\n");
								}
						}
						
						
						// ellipse 32
						
						if (layerTreeServices.getCheckBoxTreeItems().get(32).isSelected()) {
							measureTool.setMeasureShapeType(MeasureTool.SHAPE_ELLIPSE);
							if (!ellipseShapeActivated) {
								polyShapeActivated = false;
								circleShapeActivated = false;
								ellipseShapeActivated = true;
								layerTreeServices.getCheckBoxTreeItems().get(31).setSelected(false);
								layerTreeServices.getCheckBoxTreeItems().get(33).setSelected(false);
								System.out.println("Ellipse shape activated.\n");
								}
						}
						
						
						// circle 33
						
						if (layerTreeServices.getCheckBoxTreeItems().get(33).isSelected()) {
							measureTool.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE);
							if (!circleShapeActivated) {
								polyShapeActivated = false;
								circleShapeActivated = true;
								ellipseShapeActivated = false;
								layerTreeServices.getCheckBoxTreeItems().get(32).setSelected(false);
								layerTreeServices.getCheckBoxTreeItems().get(31).setSelected(false);
								System.out.println("Circle shape activated.\n");
								}
						}
							
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

	private void watchTarget(Ship target) {

		LatLon pos;
		pos = LatLon.fromDegrees(target.getLatitude(), target.getLongitude());
		if (WWMath.isLocationInside(pos, measureTool.getPositions()) && measureTool != null) {
			System.err.println("============ W A R N I N G ============ Ship with MMSI #" + target.getMMSI() + " is inside Polygon#" + nbPolygon + "\n");
			
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
			
			if (!layerTreeServices.getCheckBoxTreeItems().get(27).isSelected()) {
				layerTreeServices.getCheckBoxTreeItems().get(27).setSelected(true);
				}
			
			}
	}
	
	private void watchTarget(Ship target, LinkedList<MeasureTool> list) {

		LatLon pos;
		pos = LatLon.fromDegrees(target.getLatitude(), target.getLongitude());
		
		for (int i=0; i<list.size(); i++) {
		
		if (WWMath.isLocationInside(pos, list.get(i).getPositions()) && list.get(i) != null) {
			System.err.println("============ W A R N I N G ============ Ship with MMSI #" + target.getMMSI() + " is inside Polygon#" + (i+1) + "\n");
			
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
		}
	}

}