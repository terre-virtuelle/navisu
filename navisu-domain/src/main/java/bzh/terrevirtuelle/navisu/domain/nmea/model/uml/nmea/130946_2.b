class BWW
!!!163842.java!!!	BWW(in device : String, in sentence : String, in bearingDegreesTrue : float, in bearingDegreesMagnetic : float, in toWaypoint : String, in fromWaypoint : String)
        super(device, sentence);
        this.bearingDegreesTrue = bearingDegreesTrue;
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
        this.toWaypoint = toWaypoint;
        this.fromWaypoint = fromWaypoint;
!!!164098.java!!!	getFromWaypoint() : String
        return fromWaypoint;
!!!164226.java!!!	setFromWaypoint(in fromWaypoint : String) : void
        this.fromWaypoint = fromWaypoint;
!!!164354.java!!!	getToWaypoint() : String
        return toWaypoint;
!!!164482.java!!!	setToWaypoint(in toWaypoint : String) : void
        this.toWaypoint = toWaypoint;
!!!164610.java!!!	getBearingDegreesMagnetic() : float
        return bearingDegreesMagnetic;
!!!164738.java!!!	setBearingDegreesMagnetic(in bearingDegreesMagnetic : float) : void
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
!!!164866.java!!!	getBearingDegreesTrue() : float
        return bearingDegreesTrue;
!!!164994.java!!!	setBearingDegreesTrue(in bearingDegreesTrue : float) : void
        this.bearingDegreesTrue = bearingDegreesTrue;
!!!165122.java!!!	toString() : String
        return "BWW{" + "bearingDegreesTrue=" + bearingDegreesTrue + ", bearingDegreesMagnetic=" + bearingDegreesMagnetic + ", toWaypoint=" + toWaypoint + ", fromWaypoint=" + fromWaypoint + '}';
