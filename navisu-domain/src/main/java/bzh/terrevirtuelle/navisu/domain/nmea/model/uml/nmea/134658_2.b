class RMC
!!!202242.java!!!	RMC(in device : String, in sentence : String, inout date : Calendar, in status : String, in latitude : float, in longitude : float, in sog : float, in track : float, in variation : float, in eastOrWestVariation : String)
        super(device, sentence);
        this.date = date;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.track = track;
        this.date = date;
        this.variation = variation;
        this.eastOrWestVariation = eastOrWestVariation;
!!!202498.java!!!	getEastOrWestVariation() : String
        return eastOrWestVariation;
!!!202626.java!!!	setEastOrWestVariation(in eastOrWestVariation : String) : void
        this.eastOrWestVariation = eastOrWestVariation;
!!!202754.java!!!	getTime() : String
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss");
        return formater.format(date.getTime());
!!!202882.java!!!	getDate() : Calendar
        return date;
!!!203010.java!!!	setDate(inout date : Calendar) : void
        this.date = date;
!!!203138.java!!!	getStatus() : String
        return status;
!!!203266.java!!!	setStatus(in status : String) : void
        this.status = status;
!!!203394.java!!!	getLatitude() : float
        return latitude;
!!!203522.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!203650.java!!!	getLongitude() : float
        return longitude;
!!!203778.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!203906.java!!!	getVariation() : float
        return variation;
!!!204034.java!!!	setVariation(in variation : float) : void
        this.variation = variation;
!!!204162.java!!!	getTrack() : float
        return track;
!!!204290.java!!!	setTrack(in track : float) : void
        this.track = track;
!!!204418.java!!!	getSog() : float
        return sog;
!!!204546.java!!!	setSog(in sog : float) : void
        this.sog = sog;
!!!204674.java!!!	toString() : String
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/YY", Locale.FRENCH);
        simpleDateFormat.setLenient(false);
        return "RMC{" + "eastOrWestVariation=" + eastOrWestVariation
                + ", date=" + simpleDateFormat.format(date.getTime())
                + ", status=" + status + ", latitude=" + latitude + ", longitude=" + longitude + ", variation=" + variation + ", track=" + track + ", sog=" + sog + '}';
