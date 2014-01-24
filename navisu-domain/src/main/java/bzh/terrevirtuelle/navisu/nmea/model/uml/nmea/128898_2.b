class AISType1
!!!142722.java!!!	decodeFrame() : void
        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            navigationalStatus = binaryToInt(messageAisBinary, 38, 42);
            sog = (float) (0.1 * binaryToInt(messageAisBinary, 50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary, 116, 128));
            heading = (float) binaryToInt(messageAisBinary, 128, 137);
            rot = (float) complementToInt(messageAisBinary, 42, 50);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 89, 116))) / 60;
        }
!!!142850.java!!!	toString() : String
        return "AISType1{MMSI=" + MMSI
                + ", STATUS = " + navigationalStatus
                + ", HEAD =" + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot + '}';
