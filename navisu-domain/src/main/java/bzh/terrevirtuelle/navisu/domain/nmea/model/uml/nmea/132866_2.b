class MTW
!!!184706.java!!!	MTW(in device : String, in sentence : String, in degrees : float, in unit : String)
        super(device, sentence);
        this.degrees = degrees;
        this.unit = unit;
!!!184962.java!!!	getDegrees() : float
        return this.degrees;
!!!185090.java!!!	setDegrees(in value : float) : void
        this.degrees = value;
!!!185218.java!!!	getUnit() : String
        return this.unit;
!!!185346.java!!!	setUnit(in value : String) : void
        this.unit = value;
!!!185474.java!!!	toString() : String
        return "MTW{" + "degrees=" + degrees + ", unit=" + unit + '}';
