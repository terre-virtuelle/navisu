class GGA
!!!169602.java!!!	GGA(in device : String, in sentence : String, inout utc : Calendar, in latitude : float, in longitude : float, in gpsQualityIndicator : int, in numberOfSatellitesInView : int, in horizontalDilutionOfPrecision : float, in antennaAltitude : float, in unitsOfAntennaAltitude : String, in geoidAltitude : float, in unitsOfGeoidAltitude : String)
        super(device, sentence);
        this.utc = utc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gpsQualityIndicator = gpsQualityIndicator;
        this.numberOfSatellitesInView = numberOfSatellitesInView;
        this.horizontalDilutionOfPrecision = horizontalDilutionOfPrecision;
        this.antennaAltitude = antennaAltitude;
        this.unitsOfAntennaAltitude = unitsOfAntennaAltitude;
        this.geoidAltitude = geoidAltitude;
        this.unitsOfGeoidAltitude = unitsOfGeoidAltitude;
!!!169858.java!!!	getTime() : String
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss"); 
        return formater.format(utc.getTime());
!!!169986.java!!!	setDate(inout date : Calendar) : void
        this.utc = date;
!!!170114.java!!!	getUnitsOfGeoidAltitude() : String
        return unitsOfGeoidAltitude;
!!!170242.java!!!	setUnitsOfGeoidAltitude(in unitsOfGeoidAltitude : String) : void
        this.unitsOfGeoidAltitude = unitsOfGeoidAltitude;
!!!170370.java!!!	getLatitude() : float
        return this.latitude;
!!!170498.java!!!	getLongitude() : float
        return this.longitude;
!!!170626.java!!!	setLongitude(in value : float) : void
        this.longitude = value;
!!!170754.java!!!	getGpsQualityIndicator() : int
        return this.gpsQualityIndicator;
!!!170882.java!!!	setGpsQualityIndicator(in value : int) : void
        this.gpsQualityIndicator = value;
!!!171010.java!!!	getNumberOfSatellitesInView() : int
        return this.numberOfSatellitesInView;
!!!171138.java!!!	setNumberOfSatellitesInView(in value : int) : void
        this.numberOfSatellitesInView = value;
!!!171266.java!!!	getHorizontalDilutionOfPrecision() : float
        return this.horizontalDilutionOfPrecision;
!!!171394.java!!!	setHorizontalDilutionOfPrecision(in value : float) : void
        this.horizontalDilutionOfPrecision = value;
!!!171522.java!!!	getAntennaAltitude() : float
        return this.antennaAltitude;
!!!171650.java!!!	setAntennaAltitude(in value : float) : void
        this.antennaAltitude = value;
!!!171778.java!!!	getUnitsOfAntennaAltitude() : String
        return this.unitsOfAntennaAltitude;
!!!171906.java!!!	setUnitsOfAntennaAltitude(in value : String) : void
        this.unitsOfAntennaAltitude = value;
!!!172034.java!!!	setGeoidAltitude(in geoidAltitude : float) : void
        this.geoidAltitude = geoidAltitude;
!!!172162.java!!!	getGeoidAltitude() : float
        return geoidAltitude;
!!!172290.java!!!	getUtc() : Calendar
        return utc;
!!!172418.java!!!	setUtc(inout utc : Calendar) : void
        this.utc = utc;
!!!172546.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!172674.java!!!	toString() : String
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "GGA{" + "UTC=" + formater.format(utc.getTime()) 
                + ", latitude=" + latitude 
                + ", longitude=" + longitude 
                + ", gpsQualityIndicator=" + gpsQualityIndicator 
                + ", numberOfSatellitesInView=" + numberOfSatellitesInView 
                + ", horizontalDilutionOfPrecision=" + horizontalDilutionOfPrecision 
                + ", antennaAltitude=" + antennaAltitude 
                + ", unitsOfAntennaAltitude=" + unitsOfAntennaAltitude  
                + ", geoidAltitude=" + geoidAltitude 
                + ", unitsOfGeoidAltitude=" + unitsOfGeoidAltitude
                + '}';
