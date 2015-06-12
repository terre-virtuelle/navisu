/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.impl;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.SurfaceText;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import gov.nasa.worldwind.util.WWMath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.ZoneDriver;
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
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisCreateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.controller.events.AisUpdateTargetEvent;
import bzh.terrevirtuelle.navisu.instruments.common.view.panel.TrackPanel;
import bzh.terrevirtuelle.navisu.instruments.common.view.targets.GShip;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.GpsTrackPolygon;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.polygon.GpsTrackPolygonServices;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;


/**
 * @date 3 mars 2015
 * @author Serge Morvan
 */
public class GpsTrackPolygonImpl implements GpsTrackPolygon,
		GpsTrackPolygonServices, ZoneDriver, ComponentState {

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

	protected static final String GROUP = "Watch polygons";
	
	protected Ship watchedShip;

	protected boolean on = false;
	private final String NAME = "GpsTrackPolygon";

	protected boolean alarmOn = false;
	
	protected RenderableLayer polygonLayer;
	
	protected MeasureTool measureTool;
	protected MeasureToolController controller;
	protected LinkedList<MeasureTool> savedMeasureTool;
	
	protected boolean drawerActivated = false;
	protected int nbPolygon = 1;
	protected boolean freeHandActivated = false;
	protected LinkedList<Boolean> centered;
	protected LinkedList<Boolean> isTextOn;
	protected LinkedList<Boolean> isTextOnAis;
	protected LinkedList<SurfaceText> text;
	protected LinkedList<SurfaceText> textAis;
	protected LinkedList<RenderableLayer> polygonLayers;
	protected LinkedList<Position> centers; 
	protected LinkedList<Ship> aisShips;
	protected MeasureTool dmp;
	protected RenderableLayer dmpLayer;
	protected MeasureToolController dmpController;
	protected boolean dmpActivated = false;
	protected double diameter;
	protected double savedAltitude = 0;
	protected boolean firstDetection = false;
	protected String[][] shipMatrix = new String[6][5000];
	protected long count = 1;
	protected int inSight = 0;
	protected LinkedList<ArrayList<Position>> savedPolygons;
	protected MeasureTool pmt;
	protected MeasureToolController pmtc;
	protected RenderableLayer pLayer = new RenderableLayer();
	protected RenderableLayer tLayer = new RenderableLayer();
	protected ArrayList<Position> path = new ArrayList<Position>();
	protected static final String GROUP1 = "Target";
	protected static final String GROUP2 = "Path";
	protected Ship pShip;
	protected GShip gShip;
	protected boolean pShipCreated = false;
	protected int etape = 0;
	protected Date t0;
	protected boolean verrou = false;
	protected boolean pathActivated = false;
	protected static final String ANSI_RESET = "\u001B[0m";
	protected static final String ANSI_BLACK = "\u001B[30m";
	protected static final String ANSI_RED = "\u001B[31m";
	protected static final String ANSI_GREEN = "\u001B[32m";
	protected static final String ANSI_YELLOW = "\u001B[33m";
	protected static final String ANSI_BLUE = "\u001B[34m";
	protected static final String ANSI_PURPLE = "\u001B[35m";
	protected static final String ANSI_CYAN = "\u001B[36m";
	protected static final String ANSI_WHITE = "\u001B[37m";
	protected TrackPanel aisTrackPanel;
	protected DateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
	protected DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
	protected Boolean[][] detected = new Boolean[1000][5000];
	protected Boolean[] shipDetected = new Boolean[1000];
	protected Boolean[] aisShipDetected = new Boolean[5000];
	protected int nbSave = 0;
	protected int nbMmsiReceived = 0;
	protected int nbNamesReceived = 0;
	protected Date startTime;
	protected int nbNamesDB = 0;
	protected int distanceInterval = 70;
	protected int timeInterval = 7000;

	@Override
	public void componentInitiated() {

		watchedShip = new Ship();
		watchedShip.setMMSI(999999999);
		watchedShip.setName("PLASTRON");
		aisShips = new LinkedList<Ship>();
		
		wwd = GeoWorldWindViewImpl.getWW();
		layerTreeServices.createGroup(GROUP);
		geoViewServices.getLayerManager().createGroup(GROUP);
		
		cm = ComponentManager.componentManager;
		ggaES = cm.getComponentEventSubscribe(GGAEvent.class);
		rmcES = cm.getComponentEventSubscribe(RMCEvent.class);
		vtgES = cm.getComponentEventSubscribe(VTGEvent.class);
		
        aisCTEvent = cm.getComponentEventSubscribe(AisCreateTargetEvent.class);
        aisUTEvent = cm.getComponentEventSubscribe(AisUpdateTargetEvent.class);
		
        measureTool = new MeasureTool(GeoWorldWindViewImpl.getWW());
        savedMeasureTool = new LinkedList<MeasureTool>();
        savedPolygons = new LinkedList<ArrayList<Position>>();
        centered = new LinkedList<Boolean>();
        for (int k=0; k<100; k++) {centered.add(false);}
		
        controller = new MeasureToolController();
        measureTool.setController(controller);
		
		measureTool.setFollowTerrain(true);
		
		controller.setUseRubberBand(true);
		
		polygonLayer = measureTool.getLayer();
		polygonLayer.setName("Polygon#1");
		
		geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
		layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
		
		isTextOn = new LinkedList<Boolean>();
		for (int k=0; k<100; k++) {isTextOn.add(false);}
		
		isTextOnAis = new LinkedList<Boolean>();
		for (int k=0; k<100; k++) {isTextOnAis.add(false);}
		
		text = new LinkedList<SurfaceText>();
		for (int k=0; k<100; k++) {text.add(null);}
		
		textAis = new LinkedList<SurfaceText>();
		for (int k=0; k<100; k++) {textAis.add(null);}
		
		for (int i=0; i<1000; i++) {for (int j=0; j<5000; j++) {detected[i][j] = false;}}
		
		for (int i=0; i<1000; i++) {shipDetected[i] = false;}
		
		for (int i=0; i<5000; i++) {aisShipDetected[i] = false;}
		
		polygonLayers = new LinkedList<RenderableLayer> ();
		centers = new LinkedList<Position> ();
		
		dmp = new MeasureTool(GeoWorldWindViewImpl.getWW());
		dmpLayer = new RenderableLayer();
		dmpController = new MeasureToolController();
		dmp.setController(dmpController);
		dmp.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE);
		dmp.setFollowTerrain(true);
		dmpController.setUseRubberBand(true);
		
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
		
		pmt = new MeasureTool(wwd);
		pmtc = new MeasureToolController();
		pmt.setController(pmtc);
		pmt.setFollowTerrain(true);
		pmt.setMeasureShapeType(MeasureTool.SHAPE_PATH);
		// couleur de la trajectoire perso : vert
		pmt.setLineColor(WWUtil.decodeColorRGBA("00FF00FF"));
		pmtc.setFreeHand(true);
		pmtc.setFreeHandMinSpacing(distanceInterval);
		tLayer.setName("Custom target");
		geoViewServices.getLayerManager().insertGeoLayer(GROUP1, GeoLayer.factory.newWorldWindGeoLayer(tLayer));
		layerTreeServices.addGeoLayer(GROUP1, GeoLayer.factory.newWorldWindGeoLayer(tLayer));
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
			readShips();
			addPanelController();
			startTime = new Date();
			 
			// souscription aux événements GPS
			ggaES.subscribe(new GGAEvent() {

				@Override
				public <T extends NMEA> void notifyNmeaMessageChanged(T d) {

					GGA data = (GGA) d;
					if (on) {

						watchedShip.setLatitude(data.getLatitude());
						watchedShip.setLongitude(data.getLongitude());
						
						if (dmpActivated) {translatePolygon(watchedShip.getLatitude(), watchedShip.getLongitude());}
						
						watchTarget(watchedShip, savedMeasureTool);
						}

					}
			});
		}
		
		if (!aisServices.isOn()) {aisServices.on();}
		
		aisCTEvent.subscribe((AisCreateTargetEvent) (Ship updatedData) -> {
			createTarget(updatedData);
			for (int j=0; j<savedMeasureTool.size(); j++) {
				watchTargetAis(aisShips, savedMeasureTool.get(j));
				}
			if (dmpActivated) {
				watchTargetDmp(aisShips);
				}
            });
        aisUTEvent.subscribe((AisUpdateTargetEvent) (Ship updatedData) -> {
        	updateTarget(updatedData);
        	
        	Date t = new Date();
        	if (pShipCreated && !verrou && (int)(t.getTime()-t0.getTime())%5==0 && etape<path.size()-1) {
        		verrou = true;
        		Position p1 = new Position(LatLon.fromDegrees(path.get(etape).getLatitude().getDegrees(), path.get(etape).getLongitude().getDegrees()), 0);
        		Position p2 = new Position(LatLon.fromDegrees(path.get(etape+1).getLatitude().getDegrees(), path.get(etape+1).getLongitude().getDegrees()), 0);
        		double course = computeCourse(p1, p2);
        		pShip.setCog(course);
        		etape++;
        		pShip.setLatitude(path.get(etape).getLatitude().getDegrees());
        		pShip.setLongitude(path.get(etape).getLongitude().getDegrees());
        		updatePathTarget(pShip);
        		watchTarget(pShip, savedMeasureTool);
        		if (dmpActivated) {translatePolygon(pShip.getLatitude(), pShip.getLongitude());}
        		
        		Timer timer = new Timer();
    			timer.schedule(new TimerTask() {
    				public void run() {
    					verrou = false;
    				}
    			}, timeInterval);
        	}
        	
            for (int j=0; j<savedMeasureTool.size(); j++) {
				watchTargetAis(aisShips, savedMeasureTool.get(j));
				}
            
            if (dmpActivated){
            	watchTargetDmp(aisShips);
            	}
            });
		

	}
	
	private void createTarget(Ship target) {
		boolean shipExists = false;
		inSight++;
		for (int i=0; i<aisShips.size(); i++) {
    		if (aisShips.get(i).getMMSI() == target.getMMSI()) {
    			shipExists = true;
    			}
    		}
		
		if (shipExists) {updateTarget(target);} else {

		nbMmsiReceived++;
		Date date = new Date();
		Ship aisShip = new Ship();
		aisShip.setMMSI(target.getMMSI());
		aisShip.setLatitude(target.getLatitude());
		aisShip.setLongitude(target.getLongitude());
		if (target.getName() != null && !target.getName().equals("") && !target.getName().equals(" ") && !target.getName().equals("  ")) {aisShip.setName(target.getName());}
		aisShips.add(aisShip);
		shipMatrix[0][aisShips.size()-1] = Integer.toString(aisShip.getMMSI());
		shipMatrix[1][aisShips.size()-1] = aisShip.getName();
		shipMatrix[2][aisShips.size()-1] = Double.toString(aisShip.getLatitude());
		shipMatrix[3][aisShips.size()-1] = Double.toString(aisShip.getLongitude());
		shipMatrix[4][aisShips.size()-1] = dateFormatDate.format(date);
		shipMatrix[5][aisShips.size()-1] = dateFormatTime.format(date);
		// Enlever les commentaires pour voir les messages AIS
		//System.err.println("Ship#" + aisShips.size() + " with MMSI " + aisShip.getMMSI() + " created - name " + aisShip.getName() + " - position lat " + aisShip.getLatitude() + " and lon " + aisShip.getLongitude() + " at " + dateFormatTime.format(date));
		aisTrackPanel.updateAisPanelMmsi(dateFormatTime.format(date), inSight, aisShip.getMMSI());
		count++;
		MediaPlayer mediaPlayer;
		javafx.scene.media.Media media;
		String userDir = System.getProperty("user.dir");
		userDir = userDir.replace("\\", "/");
		String url = userDir + "/data/sounds/pling.wav";
		media = new Media("file:///" + url);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		}
	}

    private void updateTarget(Ship target) {
    	
		Date date = new Date();
		
		if (count%49==0) {
			//System.out.println(ANSI_BLUE + inSight + " ships in sight at " + dateFormatTime.format(date) + ANSI_RESET);
			aisTrackPanel.updateAisPanelShips(dateFormatTime.format(date), inSight);
			}
		
		if (count%290==0) {
			saveShips();
			nbSave++;
			Date now = new Date();
			long diff  = now.getTime() - startTime.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			//System.out.println(ANSI_GREEN + "List of AIS ships saved (" + aisShips.size() + " ships in database)" + ANSI_RESET);
			aisTrackPanel.updateAisPanelStatus("Database saved (save #" + nbSave + ")");
			aisTrackPanel.updateAisPanelStatus(nbMmsiReceived + " new ships / " + nbNamesReceived + " new names in database");
			aisTrackPanel.updateAisPanelStatus("Running for " + diffHours + " hours " + diffMinutes + " minutes " + diffSeconds + " seconds");
			aisTrackPanel.updateAisPanelCount(dateFormatTime.format(date), inSight, aisShips.size(), nbNamesDB+nbNamesReceived);
			}
		
		if (count%5001==0) {
			System.gc();
			}
    	
    	for (int i=0; i<aisShips.size(); i++) {
    		if (aisShips.get(i).getMMSI() == target.getMMSI()) {
    			Ship resu = new Ship();
    			resu.setLatitude(target.getLatitude());
    			resu.setLongitude(target.getLongitude());
    			resu.setMMSI(target.getMMSI());
    			if (target.getName() != null && !target.getName().equals(aisShips.get(i).getName())) {
    				resu.setName(target.getName());
    				if (!((target.getName()).equals(aisShips.get(i).getName()))) {
    					nbNamesReceived++;
    					//System.out.println(ANSI_PURPLE + "New name received : " + target.getName() + " for ship#" + (i+1) + " with MMSI " + target.getMMSI() + ANSI_RESET);
    					aisTrackPanel.updateAisPanelName(dateFormatTime.format(date), inSight, target.getName());
    					MediaPlayer mediaPlayer;
    					javafx.scene.media.Media media;
    					String userDir = System.getProperty("user.dir");
    					userDir = userDir.replace("\\", "/");
    					String url = userDir + "/data/sounds/beep-07.wav";
    					media = new Media("file:///" + url);
    					mediaPlayer = new MediaPlayer(media);
    					mediaPlayer.setAutoPlay(true);
    					}
    			}
    			else {resu.setName(aisShips.get(i).getName());}
    			aisShips.set(i, resu);
    			shipMatrix[0][i] = Integer.toString(resu.getMMSI());
    			shipMatrix[1][i] = resu.getName();
    			shipMatrix[2][i] = Double.toString(resu.getLatitude());
    			shipMatrix[3][i] = Double.toString(resu.getLongitude());
    			shipMatrix[4][i] = dateFormatDate.format(date);
    			shipMatrix[5][i] = dateFormatTime.format(date);
    			// Enlever les commentaires pour voir les messages AIS
    			//System.out.println(ANSI_CYAN + "Ship#" + (i+1) + " with MMSI " + target.getMMSI() + " updated - name " + resu.getName() + " - position lat " + target.getLatitude() + " and lon " + target.getLongitude() + " at " + dateFormatTime.format(date) + ANSI_RESET);
    			count++;
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
	
	private void watchTarget(Ship target, LinkedList<MeasureTool> list) {

		LatLon pos;
		pos = LatLon.fromDegrees(target.getLatitude(), target.getLongitude());
		
		for (int i=0; i<list.size(); i++) {

		if (WWMath.isLocationInside(pos, list.get(i).getPositions()) && list.get(i) != null) {
			
			if (!shipDetected[i]) {
				aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " inside P" + (i+1));
				shipDetected[i] = true;
				}
					
			if (!(centered.get(i))) {
				wwd.getView().setEyePosition(new Position(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), 20000));
				centered.set(i, true);
			}
			
			if (!(isTextOn.get(i))) {
				layerTreeServices.search("Target").setSelected(false);
				layerTreeServices.search("Target").setSelected(true);
				layerTreeServices.search("Path").setSelected(false);
				layerTreeServices.search("Path").setSelected(true);
				layerTreeServices.search("Watch polygons").setSelected(false);
				layerTreeServices.search("Watch polygons").setSelected(true);
				textOn(i);
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
		
		if (!(WWMath.isLocationInside(pos, list.get(i).getPositions())) && list.get(i) != null) {
			textOff(i);
		}
		
		if (!(WWMath.isLocationInside(pos, list.get(i).getPositions())) && list.get(i) != null) {
			if (shipDetected[i]) {
				aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " outside P" + (i+1));
				shipDetected[i] = false;
			}
		}
		
		}
	}
	
	
	private void watchTargetAis(LinkedList<Ship> targets, MeasureTool tool) {
		
		int index = 0;
		boolean putTextOn = false;
		for (Ship target : targets) {
		
			if (WWMath.isLocationInside(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), tool.getPositions()) && tool != null) {
				if (!detected[savedMeasureTool.indexOf(tool)][targets.indexOf(target)]) {
					aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " inside P" + (savedMeasureTool.indexOf(tool)+1));
					detected[savedMeasureTool.indexOf(tool)][targets.indexOf(target)] = true;
					}
				putTextOn = true;
				index = targets.indexOf(target);
			}
			
			if (!(WWMath.isLocationInside(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), tool.getPositions())) && tool != null) {
				if (detected[savedMeasureTool.indexOf(tool)][targets.indexOf(target)]) {
					aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " outside P" + (savedMeasureTool.indexOf(tool)+1));
					detected[savedMeasureTool.indexOf(tool)][targets.indexOf(target)] = false;
				}
			}
			
		}
			
		if (!(isTextOnAis.get(savedMeasureTool.indexOf(tool))) && putTextOn) {
			wwd.getView().setEyePosition(new Position(LatLon.fromDegrees(targets.get(index).getLatitude(), targets.get(index).getLongitude()), 20000));
			layerTreeServices.search("Watch polygons").setSelected(false);
			layerTreeServices.search("Watch polygons").setSelected(true);
			textOnAis(savedMeasureTool.indexOf(tool));
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

	if (isTextOnAis.get(savedMeasureTool.indexOf(tool)) && !putTextOn) {
		textOffAis(savedMeasureTool.indexOf(tool));
	}	
	}
	
	
	private void textOn(int i) {
		text.set(i, new SurfaceText("P" + (i+1), centers.get(i)));
		// couleur du texte : violet
		text.get(i).setColor(WWUtil.decodeColorRGBA("FF00FFFF"));
		if (polygonLayers.get(i).isEnabled()) {
			polygonLayers.get(i).addRenderable(text.get(i));
		} else {
			polygonLayers.get(i).setEnabled(true);
			polygonLayers.get(i).addRenderable(text.get(i));
		}

		isTextOn.set(i, true);
	}
	
	private void textOff(int i) {
		if (text.get(i) != null && polygonLayers.get(i).isEnabled()) {
			polygonLayers.get(i).removeRenderable(text.get(i));
		}
		isTextOn.set(i, false);
		}
	
	private void textOnAis(int i) {
		textAis.set(i, new SurfaceText("P" + (i+1), centers.get(i)));
		// couleur du texte : violet
		textAis.get(i).setColor(WWUtil.decodeColorRGBA("FF00FFFF"));
		if (polygonLayers.get(i).isEnabled()) {
			polygonLayers.get(i).addRenderable(textAis.get(i));
		} else {
			polygonLayers.get(i).setEnabled(true);
			polygonLayers.get(i).addRenderable(textAis.get(i));
		}

		isTextOnAis.set(i, true);
	}
	
	private void textOffAis(int i) {
		if (textAis.get(i) != null && polygonLayers.get(i).isEnabled()) {
			polygonLayers.get(i).removeRenderable(textAis.get(i));
		}
		isTextOnAis.set(i, false);
		}
	
	
	private Position barycenter(ArrayList<? extends Position> list) {
		double latmax = list.get(0).getLatitude().getDegrees();
		double longmax = list.get(0).getLongitude().getDegrees();
		double latmin = list.get(0).getLatitude().getDegrees();
		double longmin = list.get(0).getLongitude().getDegrees();
		double altmax = list.get(0).getAltitude();
		double latmoy = 0;
		double longmoy = 0;
		Position resu;
		
		for (Position pos : list) {
			if (Math.abs(latmax)<Math.abs(pos.getLatitude().getDegrees())) {latmax = pos.getLatitude().getDegrees();}
			if (Math.abs(latmin)>Math.abs(pos.getLatitude().getDegrees())) {latmin = pos.getLatitude().getDegrees();}
			if (Math.abs(longmax)<Math.abs(pos.getLongitude().getDegrees())) {longmax = pos.getLongitude().getDegrees();}
			if (Math.abs(longmin)>Math.abs(pos.getLongitude().getDegrees())) {longmin = pos.getLongitude().getDegrees();}
			if (altmax<pos.getAltitude()) {altmax = pos.getAltitude();}
		}
		//System.out.println(latmin +" "+ latmax+ " " + latmoy);
		latmoy = (latmin+latmax)/2;
		longmoy = (longmin+longmax)/2;
		if (altmax<=0) {altmax=0;}
		
		resu = new Position(LatLon.fromDegrees(latmoy, longmoy), altmax);
		
		return resu;
	}
	
	private void translatePolygon(double lat, double lon) {
		dmp.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE, new Position(LatLon.fromDegrees(lat, lon), 0), diameter/2);
		// couleur : blanc transparent a 032/256
		dmp.setFillColor(WWUtil.decodeColorRGBA("FFFFFF20"));
		dmp.setShowControlPoints(false);
	}
	
	private void watchTargetDmp(LinkedList<Ship> targets) {
		int index = 0;
		boolean detection = false;
		for (Ship target : targets) {
		
			if (WWMath.isLocationInside(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), dmp.getPositions()) && dmp != null && !firstDetection) {
				if (!aisShipDetected[targets.indexOf(target)]) {
					aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " inside CPA zone");
					aisShipDetected[targets.indexOf(target)] = true;
					}
				detection = true;
				//couleur de la DMP passe en rouge
				dmp.setLineColor(WWUtil.decodeColorRGBA("FF0000FF"));
				index = targets.indexOf(target);
				layerTreeServices.search("Watch polygons").setSelected(false);
				layerTreeServices.search("Watch polygons").setSelected(true);
				wwd.getView().setEyePosition(new Position(LatLon.fromDegrees(targets.get(index).getLatitude(), targets.get(index).getLongitude()), 20000));
				firstDetection = true;
			}
			
			if (WWMath.isLocationInside(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), dmp.getPositions()) && dmp != null && firstDetection) {
				if (!aisShipDetected[targets.indexOf(target)]) {
					aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " inside CPA zone");
					aisShipDetected[targets.indexOf(target)] = true;
					}
				detection = true;
				//couleur de la DMP passe en rouge
				dmp.setLineColor(WWUtil.decodeColorRGBA("FF0000FF"));
				index = targets.indexOf(target);
				layerTreeServices.search("Watch polygons").setSelected(false);
				layerTreeServices.search("Watch polygons").setSelected(true);
			}
			
			if (!(WWMath.isLocationInside(LatLon.fromDegrees(target.getLatitude(), target.getLongitude()), dmp.getPositions())) && dmp != null && firstDetection && aisShipDetected[targets.indexOf(target)]) {
				aisShipDetected[targets.indexOf(target)] = false;
				aisTrackPanel.updateAisPanelStatus("MMSI " + target.getMMSI() + " - " + target.getName() + " outside CPA zone");
			}
			
		}

		if (!alarmOn && detection) {
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
		
		if (!detection) {
			//couleur de la DMP repasse en vert
			dmp.setLineColor(WWUtil.decodeColorRGBA("00FF00FF"));
			firstDetection = false;
			}
		
	}
	
	@Override
	public void drawerOn() {
			if (!drawerActivated) {
				measureTool.setArmed(true);
				controller.setArmed(true);
				aisTrackPanel.updateAisPanelStatus("Drawer ready");
				drawerActivated = true;
				}
			else {
				measureTool.setArmed(false);
				controller.setArmed(false);
				aisTrackPanel.updateAisPanelStatus("Drawer deactivated");
				drawerActivated = false;
				}
	}
	
	@Override
	public void createPath() {
		pmt.setArmed(true);
		pmtc.setArmed(true);
		aisTrackPanel.updateAisPanelStatus("Custom path ready to be drawn");
	}
	
	@Override
	public void activatePath() {
		// masquage des points de contrôle de la trajectoire
		pmt.setShowControlPoints(false);
		path = (ArrayList<Position>) pmt.getPositions();
		pLayer = pmt.getLayer();
		pLayer.setName("Custom path");
		geoViewServices.getLayerManager().insertGeoLayer(GROUP2, GeoLayer.factory.newWorldWindGeoLayer(pLayer));
		layerTreeServices.addGeoLayer(GROUP2, GeoLayer.factory.newWorldWindGeoLayer(pLayer));
		pmt.setArmed(false);
		pmtc.setArmed(false);
		pShip = new Ship();
		pShip.setMMSI(999999998);
		pShip.setName("PLASTRON2");
		pShip.setLatitude(path.get(etape).getLatitude().getDegrees());
		pShip.setLongitude(path.get(etape).getLongitude().getDegrees());
		createPathTarget(pShip);
		pathActivated = true;
		layerTreeServices.search("Target").setSelected(false);
		layerTreeServices.search("Target").setSelected(true);
		layerTreeServices.search("Path").setSelected(false);
		//layerTreeServices.search("Path").setSelected(true);
		aisTrackPanel.updateAisPanelStatus("Custom path activated");
	}
	
	private void createPathTarget(Ship target) {
		gShip = new GShip(target);
		//gShip.update(0);
		target.setShipType(80);
		double sog = (distanceInterval*1000*3600)/(1852*timeInterval);
		target.setSog(sog);
		if (target.getLatitude() != 0.0 && target.getLongitude() != 0.0) {
			Renderable[] renderables = gShip.getRenderables();
			for (Renderable r : renderables) {
				tLayer.addRenderable(r);
			}
			wwd.redrawNow();
		}
		pShipCreated = true;
		t0 = new Date();
		layerTreeServices.search("Path").setSelected(true);
		layerTreeServices.search("Path").setSelected(false);
		gShip.setShip(target);
	}

	private void updatePathTarget(Ship target) {
		gShip.update();
		wwd.redrawNow();
	}
	
	@Override
	public void savePolygon() {
		centers.add(barycenter(measureTool.getPositions()));
		//System.out.println("Polygon center position is lat : " + centers.getLast().getLatitude().getDegrees() + " lon : " + centers.getLast().getLongitude().getDegrees() + " alt : " + centers.getLast().getAltitude() +"\n");							
		
		ArrayList<Position> list = new ArrayList<Position>();
		list = (ArrayList<Position>) measureTool.getPositions();
		savedPolygons.add(list);
		
		measureTool.setArmed(false);
		controller.setArmed(false);
		drawerActivated = false;
		aisTrackPanel.updateAisPanelStatus("Drawer deactivated");
		savedMeasureTool.add(measureTool);	
		nbPolygon++;
		controller = new MeasureToolController();
		measureTool = new MeasureTool(wwd);
		measureTool.setController(controller);
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
		measureTool.setFollowTerrain(true);
		controller.setUseRubberBand(true);
		polygonLayer = measureTool.getLayer();
		polygonLayers.add(polygonLayer);
		polygonLayer.setName("Polygon#" + nbPolygon);
		geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
		layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(polygonLayer));
		aisTrackPanel.updateAisPanelStatus("New polygon (polygon#" + nbPolygon +") ready to be drawn");
	}
	
	@Override
	public void saveAllPolygons() {
		// sauvegarde des polygons
		//declare output stream
				BufferedWriter writer = null;
				
				try {
				    // open file for writing
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/saved/savedPolygons.csv"), "utf-8"));
					//Put data - if needed put the loop around more than orw of records
					for (int j=0;j<savedPolygons.size();j++){
						int size = savedPolygons.get(j).size();
						for (int i=0; i<size-1; i++) {
							writer.write(savedPolygons.get(j).get(i).getLatitude().getDegrees()+";"+savedPolygons.get(j).get(i).getLongitude().getDegrees()+";");
						}
						writer.write(savedPolygons.get(j).get(size-1).getLatitude().getDegrees()+";"+savedPolygons.get(j).get(size-1).getLongitude().getDegrees()+"\r\n");
					}
					
				} catch (IOException ex) {
				  // report
				} finally {
				//close file
				   try {writer.close();} catch (Exception ex) {}
				   aisTrackPanel.updateAisPanelStatus(savedPolygons.size()+" polygons saved successfully");
				}

	}
	
	@Override
	public void loadPolygons() {
		// chargement des polygons sauvegardés
		int i = 0;
		String csvFile = "data/saved/savedPolygons.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				ArrayList<Position> list = new ArrayList<Position>();
				// use separator
				String[] positions = line.split(cvsSplitBy);
				for (int j=0; j<positions.length-1; j=j+2) {
					Position pos = new Position(LatLon.fromDegrees(Double.parseDouble(positions[j]), Double.parseDouble(positions[j+1])), 0);
					list.add(pos);
				}
				nbPolygon++;
				MeasureTool mt = new MeasureTool(wwd);
				MeasureToolController mtc = new MeasureToolController();
				mt.setController(mtc);
				mt.setArmed(true);
				mtc.setArmed(true);
				mtc.setUseRubberBand(true);
				mt.setPositions(list);
				mt.setFollowTerrain(true);
				RenderableLayer layer = mt.getLayer();
				polygonLayers.add(layer);
				layer.setName("Polygon#" + nbPolygon);
				geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
				layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
				savedMeasureTool.add(mt);
				centers.add(barycenter(mt.getPositions()));
				//System.out.println("Polygon center position is lat : " + centers.getLast().getLatitude().getDegrees() + " lon : " + centers.getLast().getLongitude().getDegrees() + " alt : " + centers.getLast().getAltitude() +"\n");
				ArrayList<Position> list1 = new ArrayList<Position>();
				list1 = (ArrayList<Position>) mt.getPositions();
				savedPolygons.add(list1);
				mt.setArmed(false);
				mtc.setArmed(false);
				aisTrackPanel.updateAisPanelStatus("New polygon (polygon#" + nbPolygon +") loaded");
				i++;
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		aisTrackPanel.updateAisPanelStatus(i + " polygons loaded successfully");	
	}
	
	@Override
	public void savePath() {
		if (pathActivated) {
			// sauvegarde de la trajectoire path tracée
			//declare output stream
				BufferedWriter writer = null;
				
				try {
				    // open file for writing
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/saved/savedPath.csv"), "utf-8"));
					//Put data - if needed put the loop around more than orw of records
					for (int k=0; k<path.size()-1; k++) {
						writer.write(path.get(k).getLatitude().getDegrees()+";"+path.get(k).getLongitude().getDegrees()+";");
					}
					writer.write(path.get(path.size()-1).getLatitude().getDegrees()+";"+path.get(path.size()-1).getLongitude().getDegrees()+"\r\n");					
				} catch (IOException ex) {
				  // report
				} finally {
				//close file
				   try {writer.close();} catch (Exception ex) {}
				   aisTrackPanel.updateAisPanelStatus("Custom path saved successfully");
				}
				
		} 
		
		else {
			aisTrackPanel.updateAisPanelStatus("Please activate path before saving it");
		}

	}
	
	@Override
	public void loadPath() {
		// chargement de la trajectoire path sauvegardée
		String csvFile = "data/saved/savedPath.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use separator
				String[] positions = line.split(cvsSplitBy);
				for (int j=0; j<positions.length-1; j=j+2) {
					Position pos = new Position(LatLon.fromDegrees(Double.parseDouble(positions[j]), Double.parseDouble(positions[j+1])), 0);
					path.add(pos);
				}
				pmt.setArmed(true);
				pmtc.setArmed(true);
				pmt.setPositions(path);
				pmt.setFollowTerrain(true);
				pLayer = pmt.getLayer();
				pmt.setArmed(false);
				pmtc.setArmed(false);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		aisTrackPanel.updateAisPanelStatus("Path loaded successfully");	
	}
	
	
	@Override
	public void polyShapeOn() {
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
		aisTrackPanel.updateAisPanelStatus("Polygon shape activated");
	}
	
	@Override
	public void ellipseShapeOn() {
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_ELLIPSE);
		aisTrackPanel.updateAisPanelStatus("Ellipse shape activated");
	}
	
	@Override
	public void circleShapeOn() {
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_CIRCLE);
		aisTrackPanel.updateAisPanelStatus("Circle shape activated");
	}
	
	@Override
	public void quadShapeOn() {
		measureTool.setMeasureShapeType(MeasureTool.SHAPE_QUAD);
		aisTrackPanel.updateAisPanelStatus("Quad shape activated");
	}
	
	@Override
	public void freeHandOn() {
		if (!freeHandActivated) {
			// Altitude en mètres
			double altitude = wwd.getView().getEyePosition().getAltitude();
			double spacing = (double) ((200*altitude)/(15000));
			controller.setFreeHand(true);
			controller.setFreeHandMinSpacing(spacing);
			measureTool.setMeasureShapeType(MeasureTool.SHAPE_POLYGON);
			freeHandActivated = true;
			aisTrackPanel.updateAisPanelStatus("Free hand mode activated");
			if (altitude != savedAltitude) {
				savedAltitude = altitude;
				aisTrackPanel.updateAisPanelStatus("Eye altitude : " + altitude);
				aisTrackPanel.updateAisPanelStatus("Points spacing : " + spacing);
			}
		}
		
		else {
			controller.setFreeHand(false);
			freeHandActivated = false;
			aisTrackPanel.updateAisPanelStatus("Free hand mode deactivated");
		}	
	}
	
	@Override
	public void createCpaZone() {
		dmp.setArmed(true);
		dmpController.setArmed(true);
		aisTrackPanel.updateAisPanelStatus("CPA zone ready to be drawn");
	}
	
	@Override
	public void createCpaZone(double yards) {
		if (!dmpActivated) {
			dmpActivated = true;
			//couleur de la DMP : vert
			dmp.setLineColor(WWUtil.decodeColorRGBA("00FF00FF"));
			//rayon statique de 500 yards
			diameter = 2*yards*0.9144;
			dmpLayer = dmp.getLayer();
			dmpLayer.setEnabled(true);
			dmpLayer.setName("CPA zone " + yards + " yards");
			geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(dmpLayer));
			layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(dmpLayer));
			aisTrackPanel.updateAisPanelStatus("CPA zone " + yards + " yards activated");
		}
	}
	
	@Override
	public void activateCpaZone() {
		if (!dmpActivated) {
			dmpActivated = true;
			//couleur de la DMP : vert
			dmp.setLineColor(WWUtil.decodeColorRGBA("00FF00FF"));
			//diamètre dynamique (commenter/décommenter la ligne suivante)
			diameter = dmp.getWidth();
			//ou : rayon statique de x yards (commenter/décommenter les 2 lignes suivantes)
			//double x = 1000;
			//diameter = 2*x*0.9144;
			dmpLayer = dmp.getLayer();
			dmpLayer.setEnabled(true);
			dmpLayer.setName("CPA zone");
			geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(dmpLayer));
			layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(dmpLayer));
			dmp.setArmed(false);
			dmpController.setArmed(false);
			aisTrackPanel.updateAisPanelStatus("CPA zone activated");
		}
	}
	
	private void saveShips() {
		//declare output stream
		BufferedWriter writer = null;
		//define headers
		String[] CSVHeaders = {"MMSI","Name","Last known latitude","Last known longitude","Date","Time"};
		
		try {
		    // open file for writing
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data/saved/savedAisShips.csv"), "utf-8"));
		    // put headers in CSV file
			for (int i=0;i<CSVHeaders.length-1;i++){
			writer.write(CSVHeaders[i]+";");
			}
			writer.write(CSVHeaders[CSVHeaders.length-1]+"\n");
			
			//Put data - if needed put the loop around more than orw of records
			for (int j=0;j<aisShips.size();j++){
				for (int i=0;i<6;i++) {
					writer.write(shipMatrix[i][j]+";");
					}
				writer.write("\r\n");
			}
			
		} catch (IOException ex) {
		  // report
		} finally {
		//close file
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	
	private void readShips() {
		boolean firstLine = true;
		int i = 0;
		String csvFile = "data/saved/savedAisShips.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
				if (firstLine) {
					firstLine = false;
					continue;
					}
				// use separator
				String[] ship = line.split(cvsSplitBy);
	 
				shipMatrix[0][i] = ship[0];
    			shipMatrix[1][i] = ship[1];
    			shipMatrix[2][i] = ship[2];
    			shipMatrix[3][i] = ship[3];
    			shipMatrix[4][i] = ship[4];
    			shipMatrix[5][i] = ship[5];
    			
    			//System.out.println("Ship [MMSI= " + ship[0] + " , name=" + ship[1] + " , lat=" + ship[2] + " , lon=" + ship[3] + " , date=" + ship[4] + " , time=" + ship[5] + " , i=" + i + "]");
    			
    			Ship s = new Ship();
    			s.setMMSI(Integer.parseInt(ship[0]));
    			s.setName(ship[1]);
    			if (!ship[1].equals("")) {
    				nbNamesDB++;
    				}
    			//on ne charge pas les dernières positions connues des bateaux car ceux-ci ne sont peut-être plus actifs, les positions seront mises à jour à la réception de nouveaux signaux AIS (pour les bateaux actifs)
    			//s.setLatitude(Double.parseDouble(ship[2]));
    			//s.setLongitude(Double.parseDouble(ship[3]));
    			aisShips.add(s);
    			i++;
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//for (Ship s : aisShips) {System.out.println(s.toString());}
	}
	
	private void addPanelController() {
        Platform.runLater(() -> {
        	Date date = new Date();
        	aisTrackPanel = new TrackPanel(KeyCode.T, KeyCombination.CONTROL_DOWN);
            aisTrackPanel.setTranslateX(150);
            guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, aisTrackPanel);
            guiAgentServices.getRoot().getChildren().add(aisTrackPanel);
            aisTrackPanel.setScale(1.0);
            aisTrackPanel.setVisible(true);
            
            aisTrackPanel.updateAisPanelCount(dateFormatTime.format(date), 0, aisShips.size(), nbNamesDB);
            aisTrackPanel.updateAisPanelStatus("Reading file done (" + aisShips.size() + " ships / " + nbNamesDB + " names in database)");
        });
        
    }
	
	private double computeCourse(Position start, Position end) {
		double lat1 = start.getLatitude().getRadians();
		double lat2 = end.getLatitude().getRadians();
		double lon1 = start.getLongitude().getRadians();
		double lon2 = end.getLongitude().getRadians();
		double resu = Math.atan2((Math.sin(lon2-lon1))*(Math.cos(lat2)), (Math.cos(lat1))*(Math.sin(lat2))-(Math.sin(lat1))*(Math.cos(lat2))*(Math.cos(lon2-lon1)));
		resu = Math.toDegrees(resu);
		resu = Math.round(resu);
		if (resu<0) {
			resu = resu + 360;
		}
		return resu;
	}
	
}
