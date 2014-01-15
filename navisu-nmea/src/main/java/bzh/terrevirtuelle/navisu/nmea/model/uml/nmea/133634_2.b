class VTG
!!!213506.java!!!	VTG(in device : String, in sentence : String, in trueTrackMadeGoodDegrees : float, in magneticTrackMadeGood : float, in sog : float)
        super(device, sentence);
        this.trueTrackMadeGoodDegrees = trueTrackMadeGoodDegrees;
        this.magneticTrackMadeGood = magneticTrackMadeGood;
        this.sog = sog;
!!!213762.java!!!	getSog() : float
        return sog;
!!!213890.java!!!	setSog(in sog : float) : void
        this.sog = sog;
!!!214018.java!!!	getMagneticTrackMadeGood() : float
        return magneticTrackMadeGood;
!!!214146.java!!!	setMagneticTrackMadeGood(in magneticTrackMadeGood : float) : void
        this.magneticTrackMadeGood = magneticTrackMadeGood;
!!!214274.java!!!	getTrueTrackMadeGoodDegrees() : float
        return trueTrackMadeGoodDegrees;
!!!214402.java!!!	setTrueTrackMadeGoodDegrees(in trueTrackMadeGoodDegrees : float) : void
        this.trueTrackMadeGoodDegrees = trueTrackMadeGoodDegrees;
!!!214530.java!!!	toString() : String
        return "VTG{" + "trueTrackMadeGoodDegrees=" + trueTrackMadeGoodDegrees 
                + ", magneticTrackMadeGood=" + magneticTrackMadeGood 
                + ", groundSpeed=" + sog  + '}';
