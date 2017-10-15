/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.bathymetry.sounds.impl.controller;

import gov.nasa.worldwind.*;
import gov.nasa.worldwind.event.*;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.util.Logging;

public class ClickAndSoundSelectListener implements SelectListener {

    private final WorldWindow wwd;
    private final Class pickedObjClass;    // Which picked object class do we handle
    private final double elevationOffset;  // Meters above the target position

    public ClickAndSoundSelectListener(WorldWindow wwd, Class pickedObjClass) {
        if (wwd == null) {
            String msg = Logging.getMessage("nullValue.WorldWindow");
            Logging.logger().severe(msg);
            throw new IllegalArgumentException(msg);
        }

        this.wwd = wwd;
        this.pickedObjClass = pickedObjClass;
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
            // This is a left click
            
            
                if (event.getTopObject().getClass().equals(pickedObjClass)) {
                    // This object class we handle and we have an orbit view
                    Position targetPos = ((PointPlacemark)event.getTopObject()).getPosition();
                    System.out.println(targetPos.getLatitude());
                    //  instrumentDriverManagerServices.open("path", "true", "1");
                }
            
        }
    }

}
