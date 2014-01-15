class NMEA
!!!128130.java!!!	NMEA(in device : String)
        this.device = device;
!!!128258.java!!!	NMEA(in device : String, in sentence : String)
        this.device = device;
        this.sentence = sentence;
!!!128386.java!!!	getSentence() : String
        return sentence;
!!!128514.java!!!	setSentence(in sentence : String) : void
        this.sentence = sentence;
!!!128642.java!!!	getDevice() : String
        return this.device;
!!!128770.java!!!	setDevice(in value : String) : void
        this.device = value;
!!!128898.java!!!	getChecksum() : String
        StringTokenizer st = new StringTokenizer(sentence, "*");
        String tmp = st.nextToken();
        tmp = st.nextToken();
        return tmp;
!!!129026.java!!!	getChecksumValidation() : boolean
        String tmp = sentence;
        StringTokenizer st = new StringTokenizer(tmp, "$");
        tmp = st.nextToken();
        st = new StringTokenizer(tmp, "*");
        tmp = st.nextToken();

        int c = 0;
        for (int i = 0; i < tmp.length(); i++) {
            c ^= tmp.charAt(i);
        }
        String result = Integer.toHexString(c).toUpperCase();

        String check = st.nextToken();
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result.equals(check);
