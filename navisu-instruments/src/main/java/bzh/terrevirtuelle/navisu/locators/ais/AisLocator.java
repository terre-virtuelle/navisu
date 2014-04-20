/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import bzh.terrevirtuelle.navisu.core.util.IDGenerator;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.ais.controller.AisStationLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.ais.view.AisLayer;
import bzh.terrevirtuelle.navisu.locators.controller.StationProcessor;
import bzh.terrevirtuelle.navisu.locators.model.TShip;
import bzh.terrevirtuelle.navisu.locators.controller.ShipProcessor;
import bzh.terrevirtuelle.navisu.locators.model.TTransceiver;
import bzh.terrevirtuelle.navisu.locators.view.Shape;

import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.domain.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.ship.ShipType;
import bzh.terrevirtuelle.navisu.locators.model.TStation;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AbstractBrowserBalloon;
import gov.nasa.worldwind.render.BalloonAttributes;
import gov.nasa.worldwind.render.BasicBalloonAttributes;
import gov.nasa.worldwind.render.GlobeBrowserBalloon;
import gov.nasa.worldwind.render.Size;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class AisLocator {

    protected GeoLayer<Layer> aisLayer;
    protected GeoLayer<Layer> aisStationLayer;
    protected RenderableLayer baloonLayer;

    protected AisLocatorControllerWithDPAgent aisLocatorController;
    protected AisStationLocatorControllerWithDPAgent aisStationLocatorControllerWithDPAgent;
    protected Map<Integer, ShipProcessor> tShipProcessors;
    protected Map<Integer, Calendar> timestamps;
    protected Map<Integer, StationProcessor> tStationsProcessors;

    protected GeoViewServices geoViewServices;
    protected DpAgentServices dpAgentServices;
    protected GuiAgentServices guiAgentServices;

    protected GeoWorldWindView geoView;
    String str = BROWSER_BALLOON_CONTENT_PATH;
    private final static PowerOfTwoPaddedImage IMAGE_EARTH
            = PowerOfTwoPaddedImage.fromPath("logoTV200.png");
    NumberFormat nf = new DecimalFormat("0.###");
    SimpleDateFormat dt = new SimpleDateFormat("hh:mm dd-MM");
    TShip ship;
    TStation station;
    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);
    boolean first = true;
    boolean firstBt = true;
    // StackPane pane;
    Position balloonPosition;
    AbstractBrowserBalloon balloon;
    BalloonAttributes attrs;

    WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    public AisLocator(GeoViewServices geoViewServices,
            DpAgentServices dpAgentServices,
            GuiAgentServices guiAgentServices) {

        tShipProcessors = new HashMap<>();
        tStationsProcessors = new HashMap<>();
        timestamps = new HashMap<>();

        this.geoViewServices = geoViewServices;
        this.dpAgentServices = dpAgentServices;
        this.guiAgentServices = guiAgentServices;

        //      pane = guiAgentServices.getRoot();
        this.aisLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.aisLayer);

        this.aisStationLayer = GeoLayer.factory.newWorldWindGeoLayer(new AisLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.aisStationLayer);

        this.baloonLayer = new RenderableLayer();
        wwd.getModel().getLayers().add(baloonLayer);
        attrs = new BasicBalloonAttributes();
        attrs.setSize(Size.fromPixels(650, 300));

        wwd.addSelectListener((SelectEvent event) -> {
            if (event.isLeftClick()
                    && event.getTopObject().getClass().getInterfaces()[0].equals(Shape.class)) {
                makeBrowserBalloon(((Shape) event.getTopObject()).getShip());
            }
        });
        subscribe();
    }

    private void subscribe() {
        ais1ES.subscribe(new AIS1Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                try {
                    AIS1 ais = (AIS1) data;
                    int mmsi = ais.getMMSI();
                    if (!tShipProcessors.containsKey(mmsi)) {
                        ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                        geoViewServices.registerProcessor(shipProcessor);

                        ship = new TShip(IDGenerator.newIntID(),
                                ais.getMMSI(), ais.getImo(), ais.getShipname(),
                                ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                                ais.getLatitude(), ais.getLongitude(),
                                ais.getWidth(), ais.getLength(), ais.getDraught(),
                                ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                                ais.getETA(), ais.getDestination(), "");
                        dpAgentServices.create(ship);

                        aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);

                        tShipProcessors.put(mmsi, shipProcessor);
                    }
                    timestamps.put(mmsi, Calendar.getInstance());
                } catch (Exception e) {
                    System.out.println("ais1ES.subscribe " + e);
                }
            }
        });

        ais2ES.subscribe(new AIS2Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS2 ais = (AIS2) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);
                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais3ES.subscribe(new AIS3Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS3 ais = (AIS3) data;
                int mmsi = ais.getMMSI();
                if (!tShipProcessors.containsKey(mmsi)) {
                    ShipProcessor shipProcessor = new ShipProcessor(AisLocator.this.aisLayer);
                    geoViewServices.registerProcessor(shipProcessor);

                    ship = new TShip(IDGenerator.newIntID(),
                            ais.getMMSI(), ais.getImo(), ais.getShipname(),
                            ais.getHeading(), ais.getCog(), ais.getSog(), ais.getRot(),
                            ais.getLatitude(), ais.getLongitude(),
                            ais.getWidth(), ais.getLength(), ais.getDraught(),
                            ais.getShipType(), ais.getNavigationalStatus(), ais.getElectronicPositionDevice(), ais.getCallsign(),
                            ais.getETA(), ais.getDestination(), "");
                    dpAgentServices.create(ship);

                    aisLocatorController = new AisLocatorControllerWithDPAgent(dpAgentServices, ship);
                    tShipProcessors.put(mmsi, shipProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });

        ais4ES.subscribe(new AIS4Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {
                AIS4 ais = (AIS4) data;
                int mmsi = ais.getMMSI();

                if (!tStationsProcessors.containsKey(mmsi)) {
                    StationProcessor stationProcessor = new StationProcessor(AisLocator.this.aisStationLayer);
                    geoViewServices.registerProcessor(stationProcessor);

                    station = new TStation(IDGenerator.newIntID(),
                            ais.getMMSI(),
                            ais.getLatitude(), ais.getLongitude(), ais.getDate());

                    dpAgentServices.create(station);

                    aisStationLocatorControllerWithDPAgent = new AisStationLocatorControllerWithDPAgent(dpAgentServices, station);

                    tStationsProcessors.put(mmsi, stationProcessor);
                }
                timestamps.put(mmsi, Calendar.getInstance());
            }
        });
    }

    protected final void makeBrowserBalloon(TShip ship) {
        String htmlString = updateHtmlString(ship);
        this.baloonLayer.removeAllRenderables();

        balloonPosition = Position.fromDegrees(ship.getLatitude(), ship.getLongitude());
        balloon = new GlobeBrowserBalloon(htmlString, balloonPosition);

        balloon.setAttributes(attrs);
        balloon.getAttributes().setImageSource(IMAGE_EARTH.getPowerOfTwoImage());
        balloon.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
     //   balloon.getAttributes().setImageScale(7);
      //  balloon.getAttributes().setImageOffset(new Point(7, 7));

        this.baloonLayer.addRenderable(balloon);
    }

    protected String updateHtmlString(TShip ship) {
        String tmp;
        if (ship.getName() != null) {
            tmp = str.replace("__shipname__", ship.getName());
        } else {
            tmp = str.replace("__shipname__", "");
        }
        if (ship.getType() != 0) {
            tmp = tmp.replace("__shiptype__", ShipType.TYPE.get(ship.getType()));
        } else {
            tmp = tmp.replace("__shiptype__", "");
        }
        if (ship.getCallSign() != null) {
            tmp = tmp.replace("__callsign__", ship.getCallSign());
        } else {
            tmp = tmp.replace("__callsign__", "");
        }
        if (ship.getMmsi() != 0) {
            tmp = tmp.replace("__mmsi__", Integer.toString(ship.getMmsi()));
            long seconds = Calendar.getInstance().getTimeInMillis()
                    - timestamps.get(ship.getMmsi()).getTimeInMillis();
            tmp = tmp.replace("__seconds__", Long.toString(seconds / 1000) + " s");
        } else {
            tmp = tmp.replace("__mmsi__", "");
        }
        if (ship.getImo() != 0) {
            tmp = tmp.replace("__imo__", Integer.toString(ship.getImo()));
        } else {
            tmp = tmp.replace("__imo__", "");
        }
        if (ship.getLength() != 0) {
            tmp = tmp.replace("__length__", Float.toString(ship.getLength()) + " m");
        } else {
            tmp = tmp.replace("__length__", "");
        }
        if (ship.getWidth() != 0) {
            tmp = tmp.replace("__width__", Float.toString(ship.getWidth()) + " m");
        } else {
            tmp = tmp.replace("__width__", "");
        }
        if (ship.getDraught() != 0) {
            tmp = tmp.replace("__draught__", Float.toString(ship.getDraught()) + " m");
        } else {
            tmp = tmp.replace("__draught__", "");
        }
        if (ship.getNavigationalStatus() != 0) {
            tmp = tmp.replace("__status__", Integer.toString(ship.getNavigationalStatus()));
        } else {
            tmp = tmp.replace("__status__", "");
        }
        if (ship.getSog() != 0) {
            tmp = tmp.replace("__sog__", nf.format(ship.getSog()) + " Kn");
        } else {
            tmp = tmp.replace("__sog__", "");
        }
        if (ship.getCog() != 0 && ship.getCog() != 511) {
            tmp = tmp.replace("__cog__", (int) ship.getCog() + " °");
        } else {
            tmp = tmp.replace("__cog__", "");
        }
        if (ship.getDestination() != null) {
            tmp = tmp.replace("__destination__", ship.getDestination());
        } else {
            tmp = tmp.replace("__destination__", "");
        }
        if (ship.getETA() != null) {
            tmp = tmp.replace("__eta__", dt.format(ship.getETA().getTime()));
        } else {
            tmp = tmp.replace("__eta__", "");
        }
        if (ship.getLatitude() != 0) {
            tmp = tmp.replace("__latitude__", nf.format(ship.getLatitude()));
        } else {
            tmp = tmp.replace("__latitude__", "");
        }
        if (ship.getLongitude() != 0) {
            tmp = tmp.replace("__longitude__", nf.format(ship.getLongitude()));
        } else {
            tmp = tmp.replace("__longitude__", "");
        }
        if (ship.getMmsi() != 0) {
            tmp = tmp.replace("photo_keywords:", "photo_keywords:" + ship.getMmsi());
        }
        return tmp;
    }
    protected static final String BROWSER_BALLOON_CONTENT_PATH = "<!--\n"
            + "  ~ Copyright (C) 2014 Terre Virtuelle NaVisu Project.\n"
            + "  ~ All Rights Reserved.\n"
            + "  -->\n"
            + "\n"
            + "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n"
            + "        \"http:&nbsp;//www.w3.org/TR/html4/loose.dtd\">\n"
            + "\n"
            + "<!-- $Id: AISTemplate.html 1171 2014-02-12 21:45:02Z Serge Morvan$ -->\n"
            + "\n"
            + "<html>\n"
            + "<head></head>\n"
            + "<body>\n"
            + "	<center>\n"
            + "<table border  width=\"600\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "    <tr>\n"
            + "    <td colspan=\"3\" align=\"left\" valign=\"top\">\n"
            + "                 <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "                    <tr> \n"
            + "                 <!--   <td align=\"left\" valign=\"middle\"><img src=\"logoTV200.png\" alt=\"logo\"/></td>-->\n"
            + "		    <td align=\"right\"><font color=\"#999999\"><strong>  Name :&nbsp; </strong></font></td>\n"
            + "                    <td><font color=\"#000000\" size=\"+2\"strong>__shipname__</strong></font></td>\n"
            + "	            </tr>\n"
            + "                    <tr>\n"
            + "		    <td align=\"right\"><font color=\"#999999\"><strong>  Type :&nbsp; </strong></font></td>\n"
            + "		    <td><font color=\"#000000\"><strong> __shiptype__<strong> </font> </td>\n"
            + "                    <td align=\"right\"><font color=\"#999999\"><strong>  Age report :&nbsp; </strong></font></td>\n"
            + "		    <td><font color=\"#000000\"><strong> __seconds__ <strong> </font> </td>\n"
            + "\n"
            + "	            </tr>\n"
            + "		 </table>\n"
            + "</br>\n"
            + "		 <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "	            <tr>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  CallSign :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __callsign__<strong> </font> </td>\n"
            + "                    <td align=\"left\"><font color=\"#999999\"><strong>  MMSI :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __mmsi__<strong> </font> </td>\n"
            + "                    <td><font color=\"#999999\"><strong> IMO :&nbsp; </strong></font></td>\n"
            + "		    <td><font color=\"#000000\"><strong>  __imo__<strong> </font> </td>\n"
            + "                    </tr>\n"
            + "\n"
            + "                    <tr>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  Length :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\"><font color=\"#000000\"><strong>  __length__<strong> </font> </td>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  Width :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\"><font color=\"#000000\"><strong>  __width__<strong> </font> </td>\n"
            + "		    <td><font color=\"#999999\"><strong> Draught :&nbsp; </strong></font></td>\n"
            + "		    <td><font color=\"#000000\"><strong>  __draught__<strong> </font> </td>\n"
            + "                    </tr>\n"
            + "\n"
            + "                    <tr>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  Status :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\"><font color=\"#000000\"><strong>  __status__<strong> </font> </td>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  SOG :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\"><font color=\"#000000\"><strong>  __sog__<strong> </font> </td>\n"
            + "		    <td><font color=\"#999999\"><strong> COG :&nbsp; </strong></font></td>\n"
            + "		    <td><font color=\"#000000\"><strong>  __cog__<strong> </font> </td>\n"
            + "                    </tr>\n"
            + "                 </table>  \n"
            + "</br> \n"
            + "                 <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "	            <tr>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  Destination :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __destination__<strong> </font> </td>\n"
            + "                    <td align=\"left\"><font color=\"#999999\"><strong>  ETA :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __eta__<strong> </font> </td>                \n"
            + "                    </tr>\n"
            + "                    <tr>\n"
            + "		    <td align=\"left\"><font color=\"#999999\"><strong>  Latitude :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __latitude__<strong> </font> </td>\n"
            + "                    <td align=\"left\"><font color=\"#999999\"><strong>  Longitude :&nbsp; </strong></font></td>\n"
            + "		    <td align=\"left\">	<font color=\"#000000\"><strong>  __longitude__<strong> </font> </td>             \n"
            + "                    </tr>\n"
            + "	    </table> \n"
            + "</br></br>\n"
            + "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "	   <tr>\n"
            + "<!--	   <td >\n"
            + "	   <a href=\"http://www.marinetraffic.com/fr/photos/of/ships/photo_keywords:\"><strong>\n"
            + "				   <font color=\"#CC3333\">Marine Traffic informations</font></strong></a>\n"
            + "	   </td>\n"
            + "-->"
            + " </tr>\n"
            + " </table>"
            + "	</td> \n"
            + "</center>\n"
            + "</body>\n"
            + "</html>\n";
}
