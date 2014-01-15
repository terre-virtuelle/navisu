class MWD
!!!185602.java!!!	MWD(in device : String, in sentence : String, in windDirectionTrue : float, in windDirectionMagnetic : float, in windSpeed : float)
        super(device, sentence);
        this.windDirectionTrue = windDirectionTrue;
        this.windDirectionMagnetic = windDirectionMagnetic;
        this.windSpeed = windSpeed;
!!!185858.java!!!	getWindSpeed() : float
        return windSpeed;
!!!185986.java!!!	setWindSpeed(in windSpeed : float) : void
        this.windSpeed = windSpeed;
!!!186114.java!!!	getWindDirectionMagnetic() : float
        return windDirectionMagnetic;
!!!186242.java!!!	setWindDirectionMagnetic(in windDirectionMagnetic : float) : void
        this.windDirectionMagnetic = windDirectionMagnetic;
!!!186370.java!!!	getWindDirectionTrue() : float
        return windDirectionTrue;
!!!186498.java!!!	setWindDirectionTrue(in windDirectionTrue : float) : void
        this.windDirectionTrue = windDirectionTrue;
!!!186626.java!!!	toString() : String
        return "MWD{" + "windDirectionTrue=" + windDirectionTrue + ", windDirectionMagnetic=" + windDirectionMagnetic + ", windSpeed=" + windSpeed + '}';
