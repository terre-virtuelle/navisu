class VBW
!!!208002.java!!!	VBW(in device : String, in sentence : String, in longitudinalWaterSpeed : float, in transverseWaterSpeed : float, in wStatus : String, in longitudinalGroundSpeed : float, in transverseGroundSpeed : float, in gStatus : String)
        super(device, sentence);
        this.longitudinalWaterSpeed = longitudinalWaterSpeed;
        this.transverseWaterSpeed = transverseWaterSpeed;
        this.wStatus = wStatus;
        this.longitudinalGroundSpeed = longitudinalGroundSpeed;
        this.transverseGroundSpeed = transverseGroundSpeed;
        this.gStatus = gStatus;
!!!208258.java!!!	getGStatus() : String
        return gStatus;
!!!208386.java!!!	setGStatus(in gStatus : String) : void
        this.gStatus = gStatus;
!!!208514.java!!!	getTransverseGroundSpeed() : float
        return transverseGroundSpeed;
!!!208642.java!!!	setTransverseGroundSpeed(in transverseGroundSpeed : float) : void
        this.transverseGroundSpeed = transverseGroundSpeed;
!!!208770.java!!!	getLongitudinalGroundSpeed() : float
        return longitudinalGroundSpeed;
!!!208898.java!!!	setLongitudinalGroundSpeed(in longitudinalGroundSpeed : float) : void
        this.longitudinalGroundSpeed = longitudinalGroundSpeed;
!!!209026.java!!!	getWStatus() : String
        return wStatus;
!!!209154.java!!!	setWStatus(in wStatus : String) : void
        this.wStatus = wStatus;
!!!209282.java!!!	getTransverseWaterSpeed() : float
        return transverseWaterSpeed;
!!!209410.java!!!	setTransverseWaterSpeed(in transverseWaterSpeed : float) : void
        this.transverseWaterSpeed = transverseWaterSpeed;
!!!209538.java!!!	getLongitudinalWaterSpeed() : float
        return longitudinalWaterSpeed;
!!!209666.java!!!	setLongitudinalWaterSpeed(in longitudinalWaterSpeed : float) : void
        this.longitudinalWaterSpeed = longitudinalWaterSpeed;
!!!209794.java!!!	toString() : String
        return "VBW{" + "longitudinalWaterSpeed=" + longitudinalWaterSpeed + ", transverseWaterSpeed=" + transverseWaterSpeed + ", wStatus=" + wStatus + ", longitudinalGroundSpeed=" + longitudinalGroundSpeed + ", transverseGroundSpeed=" + transverseGroundSpeed + ", gStatus=" + gStatus + '}';
