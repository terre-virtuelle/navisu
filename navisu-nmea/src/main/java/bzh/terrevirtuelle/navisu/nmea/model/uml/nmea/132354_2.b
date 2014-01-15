class HDM
!!!180738.java!!!	HDM(in device : String, in sentence : String, in heading : float)
        super(device, sentence);
        this.heading = heading;
!!!180994.java!!!	getHeading() : float
        return this.heading;
!!!181122.java!!!	setHeading(in value : float) : void
        this.heading = value;
!!!181250.java!!!	toString() : String
        return "HDM{" + "heading=" + heading + '}';
