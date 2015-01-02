class TTransceiver
!!!157954.java!!!	TTransceiver(in id : int)
        this.id = id;
!!!158082.java!!!	TTransceiver(in id : int, in mmsi : int, in latitude : double, in longitude : double)
        super(mmsi, latitude, longitude);
        this.id = id;
!!!158210.java!!!	TTransceiver(in id : int, in mmsi : int, in latitude : double, in longitude : double, inout date : Calendar)
        super(mmsi, latitude, longitude, date);
        this.id = id;
!!!158338.java!!!	TTransceiver(in id : int, in mmsi : int, in latitude : double, in longitude : double, inout date : Calendar, in epfd : int)
        super(mmsi, latitude, longitude, date, epfd);
        this.id = id;
!!!158466.java!!!	getID() : int
        return this.id;
!!!158594.java!!!	getLocation() : Location
        return Location.factory.newLocation(this.getLatitude(), this.getLongitude());
!!!158722.java!!!	setLocation(inout location : Location) : void
        this.setLatitude(location.getLatitudeDegree());
        this.setLongitude(location.getLongitudeDegree());
!!!158850.java!!!	getShapeId() : int
        return shapeId;
!!!158978.java!!!	getClone() : Object
        return this;
!!!159106.java!!!	getOrientation() : Orientation
        return null;
!!!159362.java!!!	getgShip() : GShip
        return gShip;
!!!159490.java!!!	setgShip(inout gShip : GShip) : void
        this.gShip = gShip;
