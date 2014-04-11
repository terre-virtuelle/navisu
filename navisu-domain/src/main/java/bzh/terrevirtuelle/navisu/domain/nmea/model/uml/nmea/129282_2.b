class AISType14
!!!145794.java!!!	decodeFrame() : void

        if (messageAisBinary.length() == 1008) {

            MMSI = binaryToInt(messageAisBinary,8, 38);
            SecurityMessage = binaryToString(messageAisBinary,40, 1008);
        }
!!!145922.java!!!	toString() : String
        return new String("AISType14{ISMM=" + MMSI + ", MESS=" + SecurityMessage + "}");
!!!146050.java!!!	getSecurityMessage() : String
        return SecurityMessage;
!!!146178.java!!!	setSecurityMessage(in SecurityMessage : String) : void
        this.SecurityMessage = SecurityMessage;
