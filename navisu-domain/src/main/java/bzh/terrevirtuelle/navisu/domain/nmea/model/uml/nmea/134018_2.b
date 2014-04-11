class VPW
!!!212738.java!!!	VPW(in device : String, in sentence : String, in speed : float)
        super(device, sentence);
        this.speed = speed;
!!!212994.java!!!	getSpeed() : float
        return speed;
!!!213122.java!!!	setSpeed(in speed : float) : void
        this.speed = speed;
!!!213250.java!!!	toString() : String
        return "VPW{" + "speed=" + speed + '}';
!!!213378.java!!!	getLOG() : Logger
        return LOG;
