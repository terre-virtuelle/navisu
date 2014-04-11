class GPSDataShip
!!!217986.java!!!	GPSDataShip(in hours : int, in minutes : int, in seconds : int, in sog : float)
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.sog = sog;
!!!218114.java!!!	GPSDataShip(in hours : int, in minutes : int, in seconds : int, in latitude : float, in longitude : float)
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
!!!218242.java!!!	GPSDataShip(in hours : int, in minutes : int, in seconds : int, in latitude : float, in longitude : float, in cog : float)
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
!!!218370.java!!!	GPSDataShip(in hours : int, in minutes : int, in seconds : int, in latitude : float, in longitude : float, in cog : float, in sog : float)
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cog = cog;
        this.sog = sog;
!!!218498.java!!!	toString() : String
        return "GPSDataShip{" + "hours=" + hours + "minutes=" + minutes + "seconds=" + seconds + "latitude=" + latitude + "longitude=" + longitude + "cog=" + cog + "sog=" + sog + '}';
!!!218626.java!!!	getCog() : float
        return cog;
!!!218754.java!!!	setCog(in cog : float) : void
        this.cog = cog;
!!!218882.java!!!	getHours() : int
        return hours;
!!!219010.java!!!	setHours(in hours : int) : void
        this.hours = hours;
!!!219138.java!!!	getLatitude() : float
        return latitude;
!!!219266.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!219394.java!!!	getLongitude() : float
        return longitude;
!!!219522.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!219650.java!!!	getMinutes() : int
        return minutes;
!!!219778.java!!!	setMinutes(in minutes : int) : void
        this.minutes = minutes;
!!!219906.java!!!	getSeconds() : int
        return seconds;
!!!220034.java!!!	setSeconds(in seconds : int) : void
        this.seconds = seconds;
!!!220162.java!!!	getSog() : float
        return sog;
!!!220290.java!!!	setSog(in sog : float) : void
        this.sog = sog;
