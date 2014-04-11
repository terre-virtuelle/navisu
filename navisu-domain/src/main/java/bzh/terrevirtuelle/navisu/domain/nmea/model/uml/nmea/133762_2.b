class XTE
!!!215810.java!!!	XTE(in device : String, in sentence : String, in generalWarning : String, in status : String, in crossTrackError : float, in directionToSteer : String)
        super(device, sentence);
        this.generalWarning = generalWarning;
        this.status = status;
        this.crossTrackError = crossTrackError;
        this.directionToSteer = directionToSteer;
!!!216066.java!!!	getDirectionToSteer() : String
        return directionToSteer;
!!!216194.java!!!	setDirectionToSteer(in directionToSteer : String) : void
        this.directionToSteer = directionToSteer;
!!!216322.java!!!	getCrossTrackError() : float
        return crossTrackError;
!!!216450.java!!!	setCrossTrackError(in crossTrackError : float) : void
        this.crossTrackError = crossTrackError;
!!!216578.java!!!	getStatus() : String
        return status;
!!!216706.java!!!	setStatus(in status : String) : void
        this.status = status;
!!!216834.java!!!	getGeneralWarning() : String
        return generalWarning;
!!!216962.java!!!	setGeneralWarning(in generalWarning : String) : void
        this.generalWarning = generalWarning;
!!!217090.java!!!	toString() : String
        return "XTE{" + "generalWarning=" + generalWarning + ", status=" + status + ", crossTrackError=" + crossTrackError + ", directionToSteer=" + directionToSteer + '}';
