/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.gps;

import bzh.terrevirtuelle.navisu.app.dpagent.DpAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.locators.gps.controller.GpsLocatorControllerWithDPAgent;
import bzh.terrevirtuelle.navisu.locators.gps.view.GpsLayer;
import bzh.terrevirtuelle.navisu.locators.controller.ShipProcessor;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.ogc.collada.impl.ColladaController;
import gov.nasa.worldwind.render.Renderable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Serge
 */
public class GpsLocator {

    protected final GeoLayer<Layer> gpsLayer;
    protected final ShipProcessor shipProcessor;
    protected final GpsLocatorControllerWithDPAgent gpsLocatorController;

    public GpsLocator(GeoViewServices geoViewServices, DpAgentServices dpAgentServices) {

        // creation de la layer
        this.gpsLayer = GeoLayer.factory.newWorldWindGeoLayer(new GpsLayer());
        geoViewServices.getLayerManager().insertGeoLayer(this.gpsLayer);

        // creation du processor
        this.shipProcessor = new ShipProcessor(this.gpsLayer);
        geoViewServices.registerProcessor(this.shipProcessor);

        // creation du controller
        this.gpsLocatorController = new GpsLocatorControllerWithDPAgent(dpAgentServices);
/*
        ColladaRoot shipCollada = null;
        try {
            shipCollada = ColladaRoot.createAndParse(new File("data/collada/lithops.dae"));
            shipCollada.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            shipCollada.setHeading(Angle.fromDegrees(-124.0));
            shipCollada.setPosition(Position.fromDegrees(48.35, -4.497602, -43));
            shipCollada.setPitch(Angle.fromDegrees(4.0));
            shipCollada.setRoll(Angle.fromDegrees(-20.0));
            shipCollada.setModelScale(new Vec4(10.0));
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(GpsLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Create a KMLController to adapt the KMLRoot to the World Wind renderable interface.
        final ColladaController colladaController = new ColladaController(shipCollada);

        // Adds a new layer containing the KMLRoot to the end of the WorldWindow's layer list. This
        // retrieves the layer name from the KMLRoot's DISPLAY_NAME field.
        final RenderableLayer layer = new RenderableLayer();
        layer.setName("Lithops");
        layer.addRenderable(colladaController);
        GeoWorldWindViewImpl.getWW().getModel().getLayers().add(layer);
        GeoWorldWindViewImpl.getWW().redrawNow();
        */
    }
}
