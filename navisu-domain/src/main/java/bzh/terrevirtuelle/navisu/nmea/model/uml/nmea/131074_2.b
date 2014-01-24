class DBK
!!!165250.java!!!	DBK(in device : String, in sentence : String, in depthInFeet : float, in depthInMeters : float, in depthInFathoms : float)
        super(device, sentence);
        this.depthInFeet = depthInFeet;
        this.depthInMeters = depthInMeters;
        this.depthInFathoms = depthInFathoms;
!!!165506.java!!!	getDepthInFathoms() : float
        return depthInFathoms;
!!!165634.java!!!	setDepthInFathoms(in depthInFathoms : float) : void
        this.depthInFathoms = depthInFathoms;
!!!165762.java!!!	getDepthInMeters() : float
        return depthInMeters;
!!!165890.java!!!	setDepthInMeters(in depthInMeters : float) : void
        this.depthInMeters = depthInMeters;
!!!166018.java!!!	getDepthInFeet() : float
        return depthInFeet;
!!!166146.java!!!	setDepthInFeet(in depthInFeet : float) : void
        this.depthInFeet = depthInFeet;
!!!166274.java!!!	toString() : String
        return "DBK{" + "depthInFeet=" + depthInFeet + ", depthInMeters=" + depthInMeters + ", depthInFathoms=" + depthInFathoms + '}';
