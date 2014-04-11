class RMB
!!!198786.java!!!	RMB(in device : String, in sentence : String, in status : String, in crossTrackError : float, in directionToSteer : String, in fromWaypointId : String, in toWaypointId : String, in destinationWaypointLatitude : float, in destinationWaypointLongitude : float, in rangeToDestination : float, in bearingToDestination : float, in destinationClosingVelocity : float, in arrivalStatus : String)
        super(device, sentence);
        this.status = status;
        this.crossTrackError = crossTrackError;
        this.directionToSteer = directionToSteer;
        this.toWaypointId = toWaypointId;
        this.fromWaypointId = fromWaypointId;
        this.destinationWaypointLatitude = destinationWaypointLatitude;
        this.destinationWaypointLongitude = destinationWaypointLongitude;
        this.rangeToDestination = rangeToDestination;
        this.bearingToDestination = bearingToDestination;
        this.destinationClosingVelocity = destinationClosingVelocity;
        this.arrivalStatus = arrivalStatus;
!!!199042.java!!!	getStatus() : String
        return this.status;
!!!199170.java!!!	setStatus(in value : String) : void
        this.status = value;
!!!199298.java!!!	getCrossTrackError() : float
        return this.crossTrackError;
!!!199426.java!!!	setCrossTrackError(in value : float) : void
        this.crossTrackError = value;
!!!199554.java!!!	getDirectionToSteer() : String
        return this.directionToSteer;
!!!199682.java!!!	setDirectionToSteer(in value : String) : void
        this.directionToSteer = value;
!!!199810.java!!!	getToWaypointId() : String
        return this.toWaypointId;
!!!199938.java!!!	setToWaypointId(in value : String) : void
        this.toWaypointId = value;
!!!200066.java!!!	getFromWaypointId() : String
        return this.fromWaypointId;
!!!200194.java!!!	setFromWaypointId(in value : String) : void
        this.fromWaypointId = value;
!!!200322.java!!!	getDestinationWaypointLatitude() : float
        return this.destinationWaypointLatitude;
!!!200450.java!!!	setDestinationWaypointLatitude(in value : float) : void
        this.destinationWaypointLatitude = value;
!!!200578.java!!!	getDestinationWaypointLongitude() : float
        return this.destinationWaypointLongitude;
!!!200706.java!!!	setDestinationWaypointLongitude(in value : float) : void
        this.destinationWaypointLongitude = value;
!!!200834.java!!!	getRangeToDestination() : float
        return this.rangeToDestination;
!!!200962.java!!!	setRangeToDestination(in value : float) : void
        this.rangeToDestination = value;
!!!201090.java!!!	getBearingToDestination() : float
        return this.bearingToDestination;
!!!201218.java!!!	setBearingToDestination(in value : float) : void
        this.bearingToDestination = value;
!!!201346.java!!!	getDestinationClosingVelocity() : float
        return this.destinationClosingVelocity;
!!!201474.java!!!	setDestinationClosingVelocity(in value : float) : void
        this.destinationClosingVelocity = value;
!!!201602.java!!!	getArrivalStatus() : String
        return this.arrivalStatus;
!!!201730.java!!!	setArrivalStatus(in value : String) : void
        this.arrivalStatus = value;
!!!201858.java!!!	getFaaModeIndicator() : String
        return this.faaModeIndicator;
!!!201986.java!!!	setFaaModeIndicator(in value : String) : void
        this.faaModeIndicator = value;
!!!202114.java!!!	toString() : String
        return "RMB{" + "status=" + status + ", crossTrackError=" + crossTrackError + ", directionToSteer=" + directionToSteer + ", toWaypointId=" + toWaypointId + ", fromWaypointId=" + fromWaypointId + ", destinationWaypointLatitude=" + destinationWaypointLatitude + ", destinationWaypointLongitude=" + destinationWaypointLongitude + ", rangeToDestination=" + rangeToDestination + ", bearingToDestination=" + bearingToDestination + ", destinationClosingVelocity=" + destinationClosingVelocity + ", arrivalStatus=" + arrivalStatus + ", faaModeIndicator=" + faaModeIndicator + '}';
