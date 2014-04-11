class BWR
!!!163458.java!!!	BWR(in device : String, in sentence : String, inout date : Calendar, in latitude : float, in longitude : float, in bearingDegreesTrue : float, in bearingDegreesMagnetic : float, in distanceToWayPoint : float, in unitsOfDistanceToWayPoint : String, in waypointID : String)
        super(device, sentence, date, latitude, longitude, bearingDegreesTrue, bearingDegreesMagnetic, distanceToWayPoint, unitsOfDistanceToWayPoint, waypointID);
!!!163714.java!!!	toString() : String
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "BWR{" +  "UTC=" + formater.format(getUtc().getTime()) 
                + ", latitude=" + getLatitude() 
                + ", longitude=" + getLongitude() 
                + ", bearingDegreesTrue=" + getBearingDegreesTrue() 
                + ", bearingDegreesMagnetic=" + getBearingDegreesMagnetic() 
                + ", distanceToWayPoint=" + getDistanceToWayPoint() 
                + ", unitsOfDistanceToWayPoint=" + getUnitsOfDistanceToWayPoint() 
                + ", waypointID=" + getWaypointID() + '}';
