/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.track.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;

/**
 *
 * @author serge
 * @date 23 déc. 2015
 */
public class GpsTrackController extends GpsPlotterController {

    protected ArrayList<Position> pathPositions;
    protected ShapeAttributes attrs;
    protected Path path;

    public GpsTrackController(LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            String name) {
        super(layersManagerServices, guiAgentServices, name);
    }

    @Override
    public void init(boolean subscribe) {
        pathPositions = new ArrayList<>();
        createAttributes();
        super.init(subscribe);
    }

    @Override
    protected void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass() == Path.class) {
                    Object object = ((Path) o).getValue("TYPE");
                    if (object != null) {
                        updateShipPanel(ownerShip);
                    }
                }
            }
        });
    }

    protected void createAttributes() {
        attrs = new BasicShapeAttributes();
        attrs.setOutlineMaterial(Material.GREEN);
        attrs.setOutlineWidth(4);
    }

    @Override
    protected void createTarget() {
        path = new Path(pathPositions);
        path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        path.setVisible(true);
        path.setValue("TYPE", "ShipTrack");
        path.setPathType(AVKey.GREAT_CIRCLE);
        path.setAttributes(attrs);
        gpsLayer.addRenderable(path);
        wwd.redrawNow();
    }

    private void updateTarget(double latitude, double longitude) {
     //   ownerShip.setLatitude(latitude);
      //  ownerShip.setLongitude(longitude);
        pathPositions.add(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), 10));
        path.setPositions(pathPositions);
        wwd.redrawNow();
    }
/*
    @Override
    protected void notifyNmeaMessage(GGA data) {
        updateTarget(data.getLatitude(), data.getLongitude());
    }

    @Override
    protected void notifyNmeaMessage(VTG data) {
        ownerShip.setCog(data.getCog());
        ownerShip.setSog(data.getSog());
    }

    @Override
    protected void notifyNmeaMessage(RMC data) {
        //filtre sur les doublons
        if (!sentenceQueue.contains(data)) {
            sentenceQueue.add(data);
            RMC d = sentenceQueue.element();
            if (d != null) {
             //   ownerShip.setCog(d.getCog());
            //    ownerShip.setSog(d.getSog());
             //   updateTarget(data.getLatitude(), data.getLongitude());
            }
        }
    }
*/
}
