class MTA
!!!183810.java!!!	MTA(in device : String, in sentence : String, in degrees : float, in unit : String)
        super(device, sentence);
        this.degrees = degrees;
        this.unit = unit;
!!!184066.java!!!	getUnit() : String
        return unit;
!!!184194.java!!!	setUnit(in unit : String) : void
        this.unit = unit;
!!!184322.java!!!	getDegrees() : float
        return degrees;
!!!184450.java!!!	setDegrees(in degrees : float) : void
        this.degrees = degrees;
!!!184578.java!!!	toString() : String
        return "MTA{" + "degrees=" + degrees + ", unit=" + unit + '}';
