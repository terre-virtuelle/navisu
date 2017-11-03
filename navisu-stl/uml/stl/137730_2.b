class SelectEventListener
!!!162178.java!!!	SelectEventListener(in selectEventId : String, inout pickedObjClass : Class, inout action : LocatableAction)
        this.selectEventId = selectEventId;
        this.pickedObjClass = pickedObjClass;
        this.action = action;
!!!162306.java!!!	selected(inout event : SelectEvent) : void
        if (event.getEventAction().equals(selectEventId)) {
            if (event.getTopObject().getClass().equals(pickedObjClass)) {
                Position targetPos = ((Locatable) event.getTopObject()).getPosition();
                action.doIt(new Point3D(targetPos.getLatitude().getDegrees(),
                        targetPos.getLongitude().getDegrees()));
            }
        }
