/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.media.bathysounds.impl.controller;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import gov.nasa.worldwind.Locatable;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;

public class SelectEventListener implements SelectListener {

    protected Class pickedObjClass;    
    protected String selectEventId;
    protected LocatableAction action;

    public SelectEventListener(
            String selectEventId,
            Class pickedObjClass,
            LocatableAction action) {
        this.selectEventId = selectEventId;
        this.pickedObjClass = pickedObjClass;
        this.action = action;
    }

    /**
     * Select Listener implementation.
     *
     * @param event the SelectEvent
     */
    @Override
    public void selected(SelectEvent event) {
        if (event.getEventAction().equals(selectEventId)) {
            if (event.getTopObject().getClass().equals(pickedObjClass)) {
                Position targetPos = ((Locatable) event.getTopObject()).getPosition();
                action.doIt(new Point3DGeo(targetPos.getLatitude().getDegrees(),
                        targetPos.getLongitude().getDegrees()));
            }
        }
    }
}
