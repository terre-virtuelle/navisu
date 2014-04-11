class APA
!!!154626.java!!!	APA(in device : String, in sentence : String, in status0 : String, in status1 : String, in crossTrackErrorMagnitude : float, in directionToSteer : String, in crossTrackUnits : String, in status2 : String, in status3 : String, in bearingOriginToDestination : int, in bearingOriginToDestinationType : String, in destinationWaypointID : String)
        super(device, sentence);
        this.status0 = status0;
        this.status1 = status1;
        this.crossTrackErrorMagnitude = crossTrackErrorMagnitude;
        this.directionToSteer = directionToSteer;
        this.crossTrackUnits = crossTrackUnits;
        this.status2 = status2;
        this.status3 = status3;
        this.bearingOriginToDestination = bearingOriginToDestination;
        this.bearingOriginToDestinationType = bearingOriginToDestinationType;
        this.destinationWaypointID = destinationWaypointID;
!!!154882.java!!!	getDestinationWaypointID() : String
        return destinationWaypointID;
!!!155010.java!!!	setDestinationWaypointID(in destinationWaypointID : String) : void
        this.destinationWaypointID = destinationWaypointID;
!!!155138.java!!!	getBearingOriginToDestinationType() : String
        return bearingOriginToDestinationType;
!!!155266.java!!!	setBearingOriginToDestinationType(in bearingOriginToDestinationType : String) : void
        this.bearingOriginToDestinationType = bearingOriginToDestinationType;
!!!155394.java!!!	getBearingOriginToDestination() : int
        return bearingOriginToDestination;
!!!155522.java!!!	setBearingOriginToDestination(in bearingOriginToDestination : int) : void
        this.bearingOriginToDestination = bearingOriginToDestination;
!!!155650.java!!!	getStatus3() : String
        return status3;
!!!155778.java!!!	setStatus3(in status3 : String) : void
        this.status3 = status3;
!!!155906.java!!!	getStatus2() : String
        return status2;
!!!156034.java!!!	setStatus2(in status2 : String) : void
        this.status2 = status2;
!!!156162.java!!!	getCrossTrackUnits() : String
        return crossTrackUnits;
!!!156290.java!!!	setCrossTrackUnits(in crossTrackUnits : String) : void
        this.crossTrackUnits = crossTrackUnits;
!!!156418.java!!!	getDirectionToSteer() : String
        return directionToSteer;
!!!156546.java!!!	setDirectionToSteer(in directionToSteer : String) : void
        this.directionToSteer = directionToSteer;
!!!156674.java!!!	getCrossTrackErrorMagnitude() : float
        return crossTrackErrorMagnitude;
!!!156802.java!!!	setCrossTrackErrorMagnitude(in crossTrackErrorMagnitude : float) : void
        this.crossTrackErrorMagnitude = crossTrackErrorMagnitude;
!!!156930.java!!!	getStatus1() : String
        return status1;
!!!157058.java!!!	setStatus1(in status1 : String) : void
        this.status1 = status1;
!!!157186.java!!!	getStatus0() : String
        return status0;
!!!157314.java!!!	setStatus0(in status0 : String) : void
        this.status0 = status0;
!!!157442.java!!!	toString() : String
        return "APA{" + "status0=" + status0 + ", status1=" + status1 + ", crossTrackErrorMagnitude=" + crossTrackErrorMagnitude + ", directionToSteer=" + directionToSteer + ", crossTrackUnits=" + crossTrackUnits + ", status2=" + status2 + ", status3=" + status3 + ", bearingOriginToDestination=" + bearingOriginToDestination + ", bearingOriginToDestinationType=" + bearingOriginToDestinationType + ", destinationWaypointID=" + destinationWaypointID + '}';
