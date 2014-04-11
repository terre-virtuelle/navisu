class AISType19
!!!148098.java!!!	getTrueHeading() : float
        return trueHeading;
!!!148226.java!!!	setTrueHeading(in trueHeading : float) : void
        this.trueHeading = trueHeading;
!!!148354.java!!!	decodeFrame() : void
        if (messageAisBinary.length() >= 167) {
            MMSI = binaryToInt(messageAisBinary,8, 38);
            sog = (float) (0.1 * binaryToInt(messageAisBinary,46, 56));
            cog = (float) (0.1 * binaryToInt(messageAisBinary,112, 124));
            trueHeading = (float) binaryToInt(messageAisBinary,124, 133);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary,57, 85))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary,85, 112))) / 60;
        }
!!!148482.java!!!	toString() : String
        return new String("AISType19{ISMM=" + MMSI + ", HEAD=" + trueHeading + ", COG=" + cog + ", LAT=" + latitude + ", LONG=" + longitude + "}");
!!!148610.java!!!	getCog() : float
        return cog;
!!!148738.java!!!	setCog(in cog : float) : void
        this.cog = cog;
!!!148866.java!!!	getLatitude() : float
        return latitude;
!!!148994.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!149122.java!!!	getLongitude() : float
        return longitude;
!!!149250.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!149378.java!!!	getSog() : float
        return sog;
!!!149506.java!!!	setSog(in sog : float) : void
        this.sog = sog;
