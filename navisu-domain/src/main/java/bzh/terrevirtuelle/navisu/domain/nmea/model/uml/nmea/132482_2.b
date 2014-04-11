class HDT
!!!181378.java!!!	HDT(in device : String, in sentence : String, in heading : float)
        super(device, sentence);
        this.heading = heading;
!!!181634.java!!!	getHeading() : float
        return this.heading;
!!!181762.java!!!	setHeading(in value : float) : void
        this.heading = value;
!!!181890.java!!!	toString() : String
        return "HDT{" + "heading=" + heading + '}';
