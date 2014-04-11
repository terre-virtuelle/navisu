class GLL
!!!172802.java!!!	GLL(in device : String, in sentence : String, in latitude : float, in longitude : float, inout utc : Calendar, in status : String)
        super(device, sentence);
        this.latitude = latitude;
        this.longitude = longitude;
        this.utc = utc;
        this.status = status;
!!!173058.java!!!	getUtc() : Calendar
        return utc;
!!!173186.java!!!	getTime() : String
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss");
        return formater.format(utc.getTime());
!!!173314.java!!!	setDate(inout date : Calendar) : void
        this.utc = date;
!!!173442.java!!!	getLatitude() : float
        return this.latitude;
!!!173570.java!!!	setLatitude(in value : float) : void
        this.latitude = value;
!!!173698.java!!!	getLongitude() : float
        return this.longitude;
!!!173826.java!!!	setLongitude(in value : float) : void
        this.longitude = value;
!!!173954.java!!!	getStatus() : String
        return this.status;
!!!174082.java!!!	setStatus(in value : String) : void
        this.status = value;
!!!174210.java!!!	toString() : String
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "GLL{" + "latitude=" + latitude + ", longitude=" + longitude 
                + ", status=" + status 
                + ", UTC=" + formater.format(utc.getTime()) + '}';
