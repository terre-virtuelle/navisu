class BWC
!!!158978.java!!!	BWC(in device : String, in sentence : String, inout utc : Calendar, in latitude : float, in longitude : float, in bearingDegreesTrue : float, in bearingDegreesMagnetic : float, in distanceToWayPoint : float, in unitsOfDistanceToWayPoint : String, in waypointID : String)
        super(device, sentence);
        this.utc = utc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bearingDegreesTrue = bearingDegreesTrue;
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
        this.distanceToWayPoint = distanceToWayPoint;
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
        this.waypointID = waypointID;
!!!159234.java!!!	BWC(inout utc : Calendar, in unitsOfDistanceToWayPoint : String)
        this.utc = utc;
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
!!!159362.java!!!	getBearingDegreesMagnetic() : float
        return bearingDegreesMagnetic;
!!!159490.java!!!	setBearingDegreesMagnetic(in bearingDegreesMagnetic : float) : void
        this.bearingDegreesMagnetic = bearingDegreesMagnetic;
!!!159618.java!!!	getBearingDegreesTrue() : float
        return bearingDegreesTrue;
!!!159746.java!!!	setBearingDegreesTrue(in bearingDegreesTrue : float) : void
        this.bearingDegreesTrue = bearingDegreesTrue;
!!!159874.java!!!	getLatitude() : float
        return latitude;
!!!160002.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!160130.java!!!	getLongitude() : float
        return longitude;
!!!160258.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!160386.java!!!	getWaypointID() : String
        return waypointID;
!!!160514.java!!!	setWaypointID(in waypointID : String) : void
        this.waypointID = waypointID;
!!!160642.java!!!	getDistanceToWayPoint() : float
        return distanceToWayPoint;
!!!160770.java!!!	setDistanceToWayPoint(in distanceToWayPoint : float) : void
        this.distanceToWayPoint = distanceToWayPoint;
!!!160898.java!!!	getUnitsOfDistanceToWayPoint() : String
        return unitsOfDistanceToWayPoint;
!!!161026.java!!!	setUnitsOfDistanceToWayPoint(in unitsOfDistanceToWayPoint : String) : void
        this.unitsOfDistanceToWayPoint = unitsOfDistanceToWayPoint;
!!!161154.java!!!	getTime() : String
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss"); 
        return formater.format(utc.getTime());
!!!161282.java!!!	getUtc() : Calendar
        return utc;
!!!161410.java!!!	setDate(inout date : Calendar) : void
        this.utc = date;
!!!161538.java!!!	toString() : String
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "BWC{" +  "UTC=" + formater.format(utc.getTime()) + ", latitude=" + latitude + ", longitude=" + longitude + ", bearingDegreesTrue=" + bearingDegreesTrue + ", bearingDegreesMagnetic=" + bearingDegreesMagnetic + ", distanceToWayPoint=" + distanceToWayPoint + ", unitsOfDistanceToWayPoint=" + unitsOfDistanceToWayPoint + ", waypointID=" + waypointID + '}';
