class AISType18
!!!146434.java!!!	decodeFrame() : void

        // TODO : v�rifier le compte de bits

        if (messageAisBinary.length() >= 167) {

            MMSI = binaryToInt(messageAisBinary,8, 38);
            sog = (float) (0.1 * binaryToInt(messageAisBinary,46, 56));
            cog = (float) (0.1 * binaryToInt(messageAisBinary,112, 124));
            trueHeading = (float)binaryToInt(messageAisBinary,124, 133);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary,57, 85))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary,85, 112))) / 60;
        }
!!!146562.java!!!	toString() : String
        return new String("AISType18{ISMM=" + MMSI + ", HEAD=" + trueHeading + ", COG=" + cog + ", LAT=" + latitude + ", LONG=" + longitude + "}");
!!!146690.java!!!	getCog() : float
        return cog;
!!!146818.java!!!	setCog(in cog : float) : void
        this.cog = cog;
!!!146946.java!!!	getLatitude() : float
        return latitude;
!!!147074.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!147202.java!!!	getLongitude() : float
        return longitude;
!!!147330.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!147458.java!!!	getSog() : float
        return sog;
!!!147586.java!!!	setSog(in sog : float) : void
        this.sog = sog;
!!!147714.java!!!	getTrueHeading() : float
        return trueHeading;
!!!147842.java!!!	setTrueHeading(in trueHeading : float) : void
        this.trueHeading = trueHeading;
