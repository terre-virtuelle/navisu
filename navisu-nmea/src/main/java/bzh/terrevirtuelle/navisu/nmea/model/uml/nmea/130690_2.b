class BOD
!!!162050.java!!!	BOD(in device : String, in sentence : String, in bearingTrue : float, in bearingMagnetic : float, in destinationWaypointID : String, in originWaypointID : String)
        super(device, sentence);
        this.bearingTrue = bearingTrue;
        this.bearingMagnetic = bearingMagnetic;
        this.destinationWaypointID = destinationWaypointID;
        this.originWaypointID = originWaypointID;
!!!162306.java!!!	getOriginWaypointID() : String
        return originWaypointID;
!!!162434.java!!!	setOriginWaypointID(in originWaypointID : String) : void
        this.originWaypointID = originWaypointID;
!!!162562.java!!!	getDestinationWaypointID() : String
        return destinationWaypointID;
!!!162690.java!!!	setDestinationWaypointID(in destinationWaypointID : String) : void
        this.destinationWaypointID = destinationWaypointID;
!!!162818.java!!!	getBearingMagnetic() : float
        return bearingMagnetic;
!!!162946.java!!!	setBearingMagnetic(in bearingMagnetic : float) : void
        this.bearingMagnetic = bearingMagnetic;
!!!163074.java!!!	getBearingTrue() : float
        return bearingTrue;
!!!163202.java!!!	setBearingTrue(in bearingTrue : float) : void
        this.bearingTrue = bearingTrue;
!!!163330.java!!!	toString() : String
        return "BOD{" + "bearingTrue=" + bearingTrue + ", bearingMagnetic=" + bearingMagnetic + ", destinationWaypointID=" + destinationWaypointID + ", originWaypointID=" + originWaypointID + '}';
