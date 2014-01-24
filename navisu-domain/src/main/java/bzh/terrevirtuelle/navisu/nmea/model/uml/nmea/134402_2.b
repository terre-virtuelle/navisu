class VHW
!!!209922.java!!!	VHW(in device : String, in sentence : String, in degreesTrue : float, in degreesMagnetic : float, in speedInKnots : float, in speedInKilometers : float)
        super(device, sentence);
        this.degreesTrue = degreesTrue;
        this.degreesMagnetic = degreesMagnetic;
        this.speedInKnots = speedInKnots;
        this.speedInKilometers = speedInKilometers;
!!!210178.java!!!	getDegreesTrue() : float
        return degreesTrue;
!!!210306.java!!!	setDegreesTrue(in degreesTrue : float) : void
        this.degreesTrue = degreesTrue;
!!!210434.java!!!	getDegreesMagnetic() : float
        return degreesMagnetic;
!!!210562.java!!!	setDegreesMagnetic(in degreesMagnetic : float) : void
        this.degreesMagnetic = degreesMagnetic;
!!!210690.java!!!	getSpeedInKilometers() : float
        return speedInKilometers;
!!!210818.java!!!	setSpeedInKilometers(in speedInKilometers : float) : void
        this.speedInKilometers = speedInKilometers;
!!!210946.java!!!	getSpeedInKnots() : float
        return speedInKnots;
!!!211074.java!!!	setSpeedInKnots(in speedInKnots : float) : void
        this.speedInKnots = speedInKnots;
!!!211202.java!!!	toString() : String
        return "VHW{" + "degreesTrue=" + degreesTrue + ", degreesMagnetic=" + degreesMagnetic + ", speedInKnots=" + speedInKnots + ", speedInKilometers=" + speedInKilometers + '}';
