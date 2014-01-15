class DBT
!!!167554.java!!!	DBT(in device : String, in sentence : String, in depthInFeet : float, in depthInMeters : float, in depthInFathoms : float)
        super(device, sentence);
        this.depthInFeet = depthInFeet;
        this.depthInMeters = depthInMeters;
        this.depthInFathoms = depthInFathoms;
!!!167810.java!!!	getDepthInFathoms() : float
        return depthInFathoms;
!!!167938.java!!!	setDepthInFathoms(in depthInFathoms : float) : void
        this.depthInFathoms = depthInFathoms;
!!!168066.java!!!	getDepthInMeters() : float
        return depthInMeters;
!!!168194.java!!!	setDepthInMeters(in depthInMeters : float) : void
        this.depthInMeters = depthInMeters;
!!!168322.java!!!	getDepthInFeet() : float
        return depthInFeet;
!!!168450.java!!!	setDepthInFeet(in depthInFeet : float) : void
        this.depthInFeet = depthInFeet;
!!!168578.java!!!	toString() : String
        return "DBT{" + "depthInFeet=" + depthInFeet + ", depthInMeters=" + depthInMeters + ", depthInFathoms=" + depthInFathoms + '}';
