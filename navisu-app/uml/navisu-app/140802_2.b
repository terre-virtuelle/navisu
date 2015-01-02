class TStation
!!!156290.java!!!	TStation(in id : int)
        this.id = id;
!!!156418.java!!!	TStation(in id : int, in mmsi : int, in latitude : double, in longitude : double)
        super(mmsi, latitude, longitude);
        this.id = id;
!!!156546.java!!!	TStation(in id : int, in mmsi : int, in latitude : double, in longitude : double, inout date : Calendar)
        super(mmsi, latitude, longitude, date);
        this.id = id;
!!!156674.java!!!	TStation(in id : int, in mmsi : int, in latitude : double, in longitude : double, inout date : Calendar, in epfd : int)
        super(mmsi, latitude, longitude, date, epfd);
        this.id = id;
!!!156802.java!!!	getID() : int
        return this.id;
!!!156930.java!!!	getGStation() : GStation
        return gStation;
!!!157058.java!!!	setGStation(inout gStation : GStation) : void
        this.gStation = gStation;
!!!157186.java!!!	getLocation() : Location
        return Location.factory.newLocation(this.getLatitude(), this.getLongitude());
!!!157314.java!!!	setLocation(inout location : Location) : void
        this.setLatitude(location.getLatitudeDegree());
        this.setLongitude(location.getLongitudeDegree());
!!!157442.java!!!	getShapeId() : int
        return shapeId;
!!!157570.java!!!	getClone() : Object
        return this;
!!!157698.java!!!	getOrientation() : Orientation
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
!!!157826.java!!!	setOrientation(inout orientation : Orientation) : void
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
