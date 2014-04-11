class GSV
!!!177922.java!!!	GSV(in device : String, in sentence : String, in numberOfSentences : int, in sentenceNumber : int, in numberOfSatellitesInView : int)
        super(device, sentence);
        this.numberOfSentences = numberOfSentences;
        this.sentenceNumber = sentenceNumber;
        this.numberOfSatellitesInView = numberOfSatellitesInView;
        satellites = new ArrayList<>();
!!!178178.java!!!	getSatellites() : GPSSatellite
        return satellites;
!!!178306.java!!!	setSatellites(inout satellites : List<GPSSatellite>) : void
        this.satellites = satellites;
!!!178434.java!!!	addSatellite(inout s : GPSSatellite) : void
        satellites.add(s);
!!!178562.java!!!	getSatellite(in pos : int) : GPSSatellite
        return satellites.get(pos);
!!!178690.java!!!	getNumberOfSatellitesInView() : int
        return numberOfSatellitesInView;
!!!178818.java!!!	setNumberOfSatellitesInView(in numberOfSatellitesInView : int) : void
        this.numberOfSatellitesInView = numberOfSatellitesInView;
!!!178946.java!!!	getNumberOfSentences() : int
        return numberOfSentences;
!!!179074.java!!!	setNumberOfSentences(in numberOfSentences : int) : void
        this.numberOfSentences = numberOfSentences;
!!!179202.java!!!	getSentenceNumber() : int
        return sentenceNumber;
!!!179330.java!!!	setSentenceNumber(in sentenceNumber : int) : void
        this.sentenceNumber = sentenceNumber;
!!!179458.java!!!	toString() : String
        return "GSV{" + "numberOfSentences=" + numberOfSentences + ", sentenceNumber=" + sentenceNumber + ", numberOfSatellitesInView=" + numberOfSatellitesInView + ", satellites=" + satellites + '}';
