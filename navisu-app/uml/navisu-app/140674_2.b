class TShip
!!!154626.java!!!	TShip(in id : int)
        this.id = id;
!!!154754.java!!!	TShip(in id : int, in mmsi : int, in latitude : float, in longitude : float)
        super(mmsi, latitude, longitude);
        this.id = id;
!!!154882.java!!!	TShip(in id : int, in mmsi : int, in name : String, in country : String, in width : float, in length : float, in draught : float, in shipType : int, in navigationalStatus : int, in electronicPositionDevice : int, in callSign : String)
        super(mmsi, name, country, width, length, draught, shipType, navigationalStatus, electronicPositionDevice, callSign);
        this.id = id;
!!!155010.java!!!	TShip(in id : int, in mmsi : int, in imo : int, in name : String, in heading : float, in cog : float, in sog : float, in rot : float, in latitude : float, in longitude : float, in width : float, in length : float, in draught : float, in type : int, in navigationalStatus : int, in electronicPositionDevice : int, in callSign : String, inout ETA : Calendar, in destination : String, in country : String)
        super(mmsi, imo, name, heading, cog, sog, rot, latitude, longitude, width, length, draught, type, navigationalStatus, electronicPositionDevice, callSign, ETA, destination, country);
        if (sog > 0.1) {
            shapeId = 0;
        }
        this.id = id;
!!!155138.java!!!	getID() : int
        return this.id;
!!!155266.java!!!	getLocation() : Location
        return Location.factory.newLocation(this.getLatitude(), this.getLongitude());
!!!155394.java!!!	setLocation(inout location : Location) : void
        this.setLatitude(location.getLatitudeDegree());
        this.setLongitude(location.getLongitudeDegree());
!!!155522.java!!!	getOrientation() : Orientation
        return Orientation.factory.newOrientation(this.getCog());
!!!155650.java!!!	setOrientation(inout orientation : Orientation) : void
        this.setCog((float) orientation.getOrientationDegree());
!!!155778.java!!!	getShapeId() : int
        return shapeId;
!!!155906.java!!!	setShapeId(in shapeId : int) : void
        this.shapeId = shapeId;
!!!156034.java!!!	getClone() : Object
        TShip clone = new TShip(this.id);
        clone.setLocation(this.getLocation());
        clone.setOrientation(this.getOrientation());
        // TODO à completer 
        // return clone avec constructeur complet
        return this;
!!!156162.java!!!	toString() : String
        return "TShip{" + "id=" + id + ", shapeId=" + shapeId + " "+  super.toString() + '}';
