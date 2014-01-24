class AISMessage
!!!132482.java!!!	AISMessage(in MMSI : int, in padding : int, in checksum : String, in nbreTrames : int, in positionTrame : int, in messageAis : String, in messageAisBinary : String)
        this.MMSI = MMSI;
        this.padding = padding;
        this.checksum = checksum;
        this.nbreTrames = nbreTrames;
        this.positionTrame = positionTrame;
        this.messageAis = messageAis;
        this.messageAisBinary = messageAisBinary;
!!!132610.java!!!	getSentence() : String
        return getMessageAis();
!!!132994.java!!!	displayFrame() : void
        System.out.println(this.toString());
!!!133122.java!!!	checksumPadding(in checkPad : String) : void
        // transforme le 0*7F en checksum et padding
        checkPad = checkPad.replace("*", " ");
        // l'�toile n'est pas accept�e comme Pattern
        String patternStr = " ";
        // donc on remplace par un espace ...
        String[] champs = checkPad.split(patternStr);
        padding = Integer.parseInt(champs[0]);
        checksum = champs[1];
!!!133250.java!!!	fill(in line : String) : void

        //System.err.println(line);
        try {

            String patternStr = ",";
            String[] champs = line.split(patternStr);

            if (champs[0].equals("!AIVDM")) {
                nbreTrames = Integer.parseInt(champs[1]);
                positionTrame = Integer.parseInt(champs[2]);
                messageAis = champs[5];
                checksumPadding(champs[6]);
                String messageBinaire = "";
                for (int i = 0; i < this.messageAis.length(); i++) {
                    char c = messageAis.charAt(i);
                    int cInt = (int) c + 40;
                    if (cInt > 128) {
                        cInt = cInt + 32;
                    } else {
                        cInt = cInt + 40;
                    }
                    String charBinaire = Integer.toBinaryString(cInt);
                    charBinaire = charBinaire.substring(charBinaire.length() - 6, charBinaire.length());
                    messageBinaire = messageBinaire.concat(charBinaire);
                }
                messageBinaire = messageBinaire.substring(0, messageBinaire.length() - this.padding - 1);
                messageAisBinary = messageBinaire;
            }

        } catch (Exception e) {
            System.err.println("(AISMessage) TRAME INVALIDE : " + line);
            messageAis = "";
        }
!!!133378.java!!!	whatType() : int

        // System.out.println(messageAisBinary.binaryToInt(0,6));
        if (!messageAis.equals("")) {
            return binaryToInt(messageAisBinary, 0, 6);
        } else {
            return -1;
        }
!!!133506.java!!!	binaryToInt(in str : String, in begin : int, in end : int) : int

        return PosBinStringToInt( str.substring(begin, end));
!!!133634.java!!!	complementToInt(in str : String, in begin : int, in end : int) : int

        String word = str.substring(begin, end);
        if (word.charAt(0) == '1') {
            return (-NegBinStringToInt(word));
        } else {
            return PosBinStringToInt(word);
        }
!!!133762.java!!!	PosBinStringToInt(in word : String) : int

        int result = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            int puiss = 1;
            for (int j = 1; j < word.length() - i; j++) {
                puiss *= 2;
            }
            if ((int) word.charAt(i) == 49) {
                result += puiss;
            }
        }
        return result;
!!!133890.java!!!	NegBinStringToInt(in word : String) : int

        int result;

        result = 0;
        for (int i = word.length() - 1; i >= 1; i--) {
            int puiss = 1;
            for (int j = 1; j < word.length() - i; j++) {
                puiss *= 2;
            }
            if ((int) word.charAt(i) == 48) {
                result += puiss;
            }
        }
        result++;
        return result;
!!!134018.java!!!	binaryToString(in str : String, in begin : int, in end : int) : String

        int spacecount;
        int ASCII;
        String line;
        String result;

        spacecount = 0;
        line = str.substring(begin, end);
        result = new String();

        for (int i = 0; i < line.length(); i = i + 6) {
            String word = line.substring(i, i + 6);
            ASCII = PosBinStringToInt(word);
            if (ASCII < 31) {
                ASCII = ASCII + 64;
            }
            if (ASCII == 64) {
                ASCII = 32;
                spacecount++;
            } else {
                spacecount = 0;
            }
            if (spacecount <= 1) {
                result = result.concat("" + ((char) ASCII));
            }
        }
        return result;
!!!134146.java!!!	getMMSI() : int
        return MMSI;
!!!134274.java!!!	setMMSI(in MMSI : int) : void
        this.MMSI = MMSI;
!!!134402.java!!!	getChecksum() : String
        return checksum;
!!!134530.java!!!	setChecksum(in checksum : String) : void
        this.checksum = checksum;
!!!134658.java!!!	getMessageAis() : String
        return messageAis;
!!!134786.java!!!	setMessageAis(in messageAis : String) : void
        this.messageAis = messageAis;
!!!134914.java!!!	getMessageAisBinary() : String
        return messageAisBinary;
!!!135042.java!!!	setMessageAisBinary(in messageAisBinary : String) : void
        this.messageAisBinary = messageAisBinary;
!!!135170.java!!!	getNbreTrames() : int
        return nbreTrames;
!!!135298.java!!!	setNbreTrames(in nbreTrames : int) : void
        this.nbreTrames = nbreTrames;
!!!135426.java!!!	getPadding() : int
        return padding;
!!!135554.java!!!	setPadding(in padding : int) : void
        this.padding = padding;
!!!135682.java!!!	getPositionTrame() : int
        return positionTrame;
!!!135810.java!!!	setPositionTrame(in positionTrame : int) : void
        this.positionTrame = positionTrame;
!!!135938.java!!!	compareTo(inout o : AISMessage) : int
        return this.MMSI - o.MMSI;
