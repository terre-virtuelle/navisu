class MWV
!!!186754.java!!!	MWV(in device : String, in sentence : String, in windAngle : float, in reference : String, in windSpeed : float, in windSpeedUnit : String, in status : String)
        super(device, sentence);
        this.windAngle = windAngle;
        this.reference = reference;
        this.windSpeed = windSpeed;
        this.windSpeedUnit = windSpeedUnit;
        this.status = status;
!!!187010.java!!!	getWindAngle() : float
        return this.windAngle;
!!!187138.java!!!	setWindAngle(in value : float) : void
        this.windAngle = value;
!!!187266.java!!!	getReference() : String
        return this.reference;
!!!187394.java!!!	setReference(in value : String) : void
        this.reference = value;
!!!187522.java!!!	getWindSpeed() : float
        return this.windSpeed;
!!!187650.java!!!	setWindSpeed(in value : float) : void
        this.windSpeed = value;
!!!187778.java!!!	getWindSpeedUnit() : String
        return this.windSpeedUnit;
!!!187906.java!!!	setWindSpeedUnit(in value : String) : void
        this.windSpeedUnit = value;
!!!188034.java!!!	getStatus() : String
        return this.status;
!!!188162.java!!!	setStatus(in value : String) : void
        this.status = value;
!!!188290.java!!!	toString() : String
        return "MWV{" + "windAngle=" + windAngle + ", reference=" + reference + ", windSpeed=" + windSpeed + ", windSpeedUnit=" + windSpeedUnit + ", status=" + status + '}';
