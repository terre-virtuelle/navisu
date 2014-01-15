class VWR
!!!214658.java!!!	VWR(in device : String, in sentence : String, in windDirectionMagnitude : int, in windDirectionOfBow : String, in speed : float)
        super(device, sentence);
        this.windDirectionMagnitude = windDirectionMagnitude;
        this.windDirectionOfBow = windDirectionOfBow;
        this.speed = speed;
!!!214914.java!!!	getSpeed() : float
        return speed;
!!!215042.java!!!	setSpeed(in speed : float) : void
        this.speed = speed;
!!!215170.java!!!	getWindDirectionOfBow() : String
        return windDirectionOfBow;
!!!215298.java!!!	setWindDirectionOfBow(in windDirectionOfBow : String) : void
        this.windDirectionOfBow = windDirectionOfBow;
!!!215426.java!!!	getWindDirectionMagnitude() : int
        return windDirectionMagnitude;
!!!215554.java!!!	setWindDirectionMagnitude(in windDirectionMagnitude : int) : void
        this.windDirectionMagnitude = windDirectionMagnitude;
!!!215682.java!!!	toString() : String
        return "VWR{" + "windDirectionMagnitude=" + windDirectionMagnitude + ", windDirectionOfBow=" + windDirectionOfBow + ", speed=" + speed + '}';
