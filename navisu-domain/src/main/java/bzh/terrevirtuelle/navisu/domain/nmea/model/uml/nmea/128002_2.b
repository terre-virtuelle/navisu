class AAM
!!!130306.java!!!	AAM(in device : String, in sentence : String, in arrivalCircleEntered : String, in perpendicularPassed : String, in circleRadius : float, in waypointName : String)
        super(device, sentence);
        this.arrivalCircleEntered = arrivalCircleEntered;
        this.perpendicularPassed = perpendicularPassed;
        this.circleRadius = circleRadius;
        this.waypointName = waypointName;
!!!130562.java!!!	getArrivalCircleEntered() : String
        return arrivalCircleEntered;
!!!130690.java!!!	setArrivalCircleEntered(in arrivalCircleEntered : String) : void
        this.arrivalCircleEntered = arrivalCircleEntered;
!!!130818.java!!!	getCircleRadius() : float
        return circleRadius;
!!!130946.java!!!	setCircleRadius(in circleRadius : float) : void
        this.circleRadius = circleRadius;
!!!131074.java!!!	getPerpendicularPassed() : String
        return perpendicularPassed;
!!!131202.java!!!	setPerpendicularPassed(in perpendicularPassed : String) : void
        this.perpendicularPassed = perpendicularPassed;
!!!131330.java!!!	getWaypointName() : String
        return waypointName;
!!!131458.java!!!	setWaypointName(in waypointName : String) : void
        this.waypointName = waypointName;
!!!131586.java!!!	toString() : String
        return "AAM{" + "arrivalCircleEntered=" + arrivalCircleEntered + ", perpendicularPassed=" + perpendicularPassed + ", circleRadius=" + circleRadius + ", waypointName=" + waypointName + '}';
