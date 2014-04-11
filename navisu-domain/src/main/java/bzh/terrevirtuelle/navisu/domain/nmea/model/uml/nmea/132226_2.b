class HDG
!!!179586.java!!!	HDG(in device : String, in sentence : String, in heading : float, in magneticDeviation : float, in magneticVariation : float)
        super(device, sentence);
        this.heading = heading;
        this.magneticDeviation = magneticDeviation;
        this.magneticVariation = magneticVariation;
!!!179842.java!!!	getHeading() : float
        return this.heading;
!!!179970.java!!!	setHeading(in value : float) : void
        this.heading = value;
!!!180098.java!!!	getMagneticDeviation() : float
        return this.magneticDeviation;
!!!180226.java!!!	setMagneticDeviation(in value : float) : void
        this.magneticDeviation = value;
!!!180354.java!!!	getMagneticVariation() : float
        return this.magneticVariation;
!!!180482.java!!!	setMagneticVariation(in value : float) : void
        this.magneticVariation = value;
!!!180610.java!!!	toString() : String
        return "HDG{" + "heading=" + heading + ", magneticDeviation=" + magneticDeviation + ", magneticVariation=" + magneticVariation + '}';
