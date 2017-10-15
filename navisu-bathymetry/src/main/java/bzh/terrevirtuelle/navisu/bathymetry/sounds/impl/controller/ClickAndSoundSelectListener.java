/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.bathymetry.sounds.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.*;
import gov.nasa.worldwind.event.*;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.util.Logging;
import java.util.Map;
import org.gavaghan.geodesy.Angle;

public class ClickAndSoundSelectListener implements SelectListener {

    private final WorldWindow wwd;
    private final Class pickedObjClass;    // Which picked object class do we handle
    private final double elevationOffset;  // Meters above the target position
    Map<Point3D, String> soundMap;

    InstrumentDriverManagerServices instrumentDriverManagerServices;

   

    public ClickAndSoundSelectListener(
            InstrumentDriverManagerServices instrumentDriverManagerServices,
            WorldWindow wwd, Class pickedObjClass) {
        if (wwd == null) {
            String msg = Logging.getMessage("nullValue.WorldWindow");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        this.wwd = wwd;
        this.pickedObjClass = pickedObjClass;
        this.instrumentDriverManagerServices=instrumentDriverManagerServices;
        this.elevationOffset = 0d;
    }

    /**
     * Select Listener implementation.
     *
     * @param event the SelectEvent
     */
    @Override
    public void selected(SelectEvent event) {
        if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
            if (event.getTopObject().getClass().equals(pickedObjClass)) {
                Position targetPos = ((PointPlacemark) event.getTopObject()).getPosition();
                String sound = soundMap.get(new Point3D(
                        targetPos.getLatitude().getDegrees(),
                        targetPos.getLongitude().getDegrees()));
               System.out.println(sound);
               instrumentDriverManagerServices.open(sound, "true", "1");
            }
        }
    }

    public Map<Point3D, String> getSoundMap() {
        return soundMap;
    }

    public void setSoundMap(Map<Point3D, String> soundMap) {
        this.soundMap = soundMap;
    }

}
