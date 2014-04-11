class DBS
!!!166402.java!!!	DBS(in device : String, in sentence : String, in depthInFeet : float, in depthInMeters : float, in depthInFathoms : float)
        super(device, sentence);
        this.depthInFeet = depthInFeet;
        this.depthInMeters = depthInMeters;
        this.depthInFathoms = depthInFathoms;
!!!166658.java!!!	getDepthInFathoms() : float
        return depthInFathoms;
!!!166786.java!!!	setDepthInFathoms(in depthInFathoms : float) : void
        this.depthInFathoms = depthInFathoms;
!!!166914.java!!!	getDepthInMeters() : float
        return depthInMeters;
!!!167042.java!!!	setDepthInMeters(in depthInMeters : float) : void
        this.depthInMeters = depthInMeters;
!!!167170.java!!!	getDepthInFeet() : float
        return depthInFeet;
!!!167298.java!!!	setDepthInFeet(in depthInFeet : float) : void
        this.depthInFeet = depthInFeet;
!!!167426.java!!!	toString() : String
        return "DBS{" + "depthInFeet=" + depthInFeet + ", depthInMeters=" + depthInMeters + ", depthInFathoms=" + depthInFathoms + '}';
