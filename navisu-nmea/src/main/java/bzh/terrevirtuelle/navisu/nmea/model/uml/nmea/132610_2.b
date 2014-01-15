class MSK
!!!182018.java!!!	MSK(in device : String, in sentence : String, in frequencyToUse : float, in frequencyMode : String, in beaconBitRate : int, in bitRateMode : String, in frequencyForMSS : int)
        super(device, sentence);
        this.frequencyToUse = frequencyToUse;
        this.frequencyMode = frequencyMode;
        this.beaconBitRate = beaconBitRate;
        this.bitRateMode = bitRateMode;
        this.frequencyForMSS = frequencyForMSS;
!!!182146.java!!!	MSK(in frequencyToUse : float, in frequencyMode : String, in beaconBitRate : int, in bitRateMode : String)
        this.frequencyToUse = frequencyToUse;
        this.frequencyMode = frequencyMode;
        this.beaconBitRate = beaconBitRate;
        this.bitRateMode = bitRateMode;
!!!182402.java!!!	toString() : String
        return "MSK{" + "frequencyToUse=" + frequencyToUse + ", frequencyMode=" + frequencyMode + ", beaconBitRate=" + beaconBitRate + ", bitRateMode=" + bitRateMode + ", frequencyForMSS=" + frequencyForMSS + '}';
!!!182530.java!!!	getBeaconBitRate() : int
        return beaconBitRate;
!!!182658.java!!!	setBeaconBitRate(in beaconBitRate : int) : void
        this.beaconBitRate = beaconBitRate;
!!!182786.java!!!	getBitRateMode() : String
        return bitRateMode;
!!!182914.java!!!	setBitRateMode(in bitRateMode : String) : void
        this.bitRateMode = bitRateMode;
!!!183042.java!!!	getFrequencyForMSS() : int
        return frequencyForMSS;
!!!183170.java!!!	setFrequencyForMSS(in frequencyForMSS : int) : void
        this.frequencyForMSS = frequencyForMSS;
!!!183298.java!!!	getFrequencyMode() : String
        return frequencyMode;
!!!183426.java!!!	setFrequencyMode(in frequencyMode : String) : void
        this.frequencyMode = frequencyMode;
!!!183554.java!!!	getFrequencyToUse() : float
        return frequencyToUse;
!!!183682.java!!!	setFrequencyToUse(in frequencyToUse : float) : void
        this.frequencyToUse = frequencyToUse;
