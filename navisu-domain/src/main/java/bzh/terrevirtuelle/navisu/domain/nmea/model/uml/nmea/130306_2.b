class APB
!!!157570.java!!!	APB(in device : String, in sentence : String, in status0 : String, in status1 : String, in crossTrackErrorMagnitude : float, in directionToSteer : String, in crossTrackUnits : String, in status2 : String, in status3 : String, in bearingOriginToDestination : int, in bearingOriginToDestinationType : String, in destinationWaypointID : String, in bearingPresentPositionToDestination : int, in bearingPresentPositionToDestinationType : String, in headingToSteerToDestination : int, in headingToSteerToDestinationType : String)
        super(device, sentence, status0, status1, crossTrackErrorMagnitude, directionToSteer, crossTrackUnits, status2, status3, bearingOriginToDestination, bearingOriginToDestinationType, destinationWaypointID);
        this.bearingPresentPositionToDestination = bearingPresentPositionToDestination;
        this.bearingPresentPositionToDestinationType = bearingPresentPositionToDestinationType;
        this.headingToSteerToDestination = headingToSteerToDestination;
        this.headingToSteerToDestinationType = headingToSteerToDestinationType;
!!!157826.java!!!	getHeadingToSteerToDestinationType() : String
        return headingToSteerToDestinationType;
!!!157954.java!!!	setHeadingToSteerToDestinationType(in headingToSteerToDestinationType : String) : void
        this.headingToSteerToDestinationType = headingToSteerToDestinationType;
!!!158082.java!!!	getHeadingToSteerToDestination() : int
        return headingToSteerToDestination;
!!!158210.java!!!	setHeadingToSteerToDestination(in headingToSteerToDestination : int) : void
        this.headingToSteerToDestination = headingToSteerToDestination;
!!!158338.java!!!	getBearingPresentPositionToDestinationType() : String
        return bearingPresentPositionToDestinationType;
!!!158466.java!!!	setBearingPresentPositionToDestinationType(in bearingPresentPositionToDestinationType : String) : void
        this.bearingPresentPositionToDestinationType = bearingPresentPositionToDestinationType;
!!!158594.java!!!	getBearingPresentPositionToDestination() : int
        return bearingPresentPositionToDestination;
!!!158722.java!!!	setBearingPresentPositionToDestination(in bearingPresentPositionToDestination : int) : void
        this.bearingPresentPositionToDestination = bearingPresentPositionToDestination;
!!!158850.java!!!	toString() : String
        
        return "APB{" 
                + super.toString()
                + "bearingPresentPositionToDestination=" + bearingPresentPositionToDestination 
                + ", bearingPresentPositionToDestinationType=" + bearingPresentPositionToDestinationType 
                + ", headingToSteerToDestination=" + headingToSteerToDestination 
                + ", headingToSteerToDestinationType=" + headingToSteerToDestinationType + '}';
