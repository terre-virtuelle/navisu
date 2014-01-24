class AISType9
!!!153090.java!!!	decodeFrame() : void
        if (messageAisBinary.length() == 167) {

            MMSI = binaryToInt(messageAisBinary, 8, 38);
            altitude = binaryToInt(messageAisBinary, 38, 50);
            speed = (float) (0.1 * binaryToInt(messageAisBinary, 50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary, 116, 128));
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 89, 116))) / 60;
        }
!!!153218.java!!!	toString() : String
        return ("AISType9{ISMM=" + MMSI + ", ALT=" + altitude + ", COG=" + cog + ", SPEED=" + speed + ", LAT=" + latitude + ", LONG=" + longitude + "}");
!!!153346.java!!!	getAltitude() : int
        return altitude;
!!!153474.java!!!	setAltitude(in altitude : int) : void
        this.altitude = altitude;
!!!153602.java!!!	getCog() : float
        return cog;
!!!153730.java!!!	setCog(in cog : float) : void
        this.cog = cog;
!!!153858.java!!!	getLatitude() : float
        return latitude;
!!!153986.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!154114.java!!!	getLongitude() : float
        return longitude;
!!!154242.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!154370.java!!!	getSpeed() : float
        return speed;
!!!154498.java!!!	setSpeed(in speed : float) : void
        this.speed = speed;
