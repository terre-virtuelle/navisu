class AISType135
!!!136194.java!!!	AISType135(in rot : float, in cog : float, in sog : float, in navigationalStatus : int, in heading : float, in latitude : float, in longitude : float, in IMO : int, in name : String, in shipType : int, in width : float, in length : float, in draught : float, in electronicPositionDevice : int, in CallSign : String, inout ETA : Calendar, in destination : String, in year : int, in month : int, in day : int, in hour : int, in minute : int)
        this.rot = rot;
        this.cog = cog;
        this.sog = sog;
        this.navigationalStatus = navigationalStatus;
        this.heading = heading;
        this.latitude = latitude;
        this.longitude = longitude;
        this.IMO = IMO;
        this.name = name;
        this.shipType = shipType;
        this.width = width;
        this.length = length;
        this.draught = draught;
        this.electronicPositionDevice = electronicPositionDevice;
        this.CallSign = CallSign;
        this.ETA = ETA;
        this.destination = destination;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
!!!136322.java!!!	toString() : String
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM");
        StringBuffer sb = new StringBuffer("AISType135{MMSI=" + MMSI
                + ", IMO = " + IMO
                + ", NAME = " + name
                + ", STATUS = " + navigationalStatus
                + ", TYPE = " + shipType
                + ", LENGTH = " + length
                + ", WIDTH = " + width
                + ", DRAUGHT = " + draught
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", HEADING = " + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", ROT = " + rot
                + ", "
                + ""
                + ", ETA = ");
        if (ETA != null) {
            Date df = ETA.getTime();
            sb.append(dateFormat.format(df));
        } else {
            sb.append("");
        }
        sb.append(", DEST = ").append(destination + "}");
        return new String(sb);
!!!136450.java!!!	toHTML() : String

        String text = "";
        if (getName() != null) {
            text += getName() + "<br/>";
        } else {
            text += "" + "<br/>";
        }
        text += "MMSI : " + getMMSI() + "<br/>";
        text += "Type : " + getShipType() + "<br/>";
        text += "Sog : " + getSog() + "<br/>";
        if (getHeading() != 511) {
            text += "Heading : " + getHeading() + "<br/>";
        }
        return text;
!!!136578.java!!!	display() : void
        System.out.println(this.toString());
!!!136706.java!!!	getElectronicPositionDevice() : int
        return electronicPositionDevice;
!!!136834.java!!!	setElectronicPositionDevice(in electronicPositionDevice : int) : void
        this.electronicPositionDevice = electronicPositionDevice;
!!!136962.java!!!	getCallSign() : String
        return CallSign;
!!!137090.java!!!	setCallSign(in CallSign : String) : void
        this.CallSign = CallSign;
!!!137218.java!!!	getETA() : Calendar
        return ETA;
!!!137346.java!!!	setETA(inout ETA : Calendar) : void
        this.ETA = ETA;
!!!137474.java!!!	getIMO() : int
        return IMO;
!!!137602.java!!!	setIMO(in IMO : int) : void
        this.IMO = IMO;
!!!137730.java!!!	getCog() : float
        return cog;
!!!137858.java!!!	setCog(in cog : float) : void
        this.cog = cog;
!!!137986.java!!!	getDay() : int
        return day;
!!!138114.java!!!	setDay(in day : int) : void
        this.day = day;
!!!138242.java!!!	getDestination() : String
        return destination;
!!!138370.java!!!	setDestination(in destination : String) : void
        this.destination = destination;
!!!138498.java!!!	getDraught() : float
        return draught;
!!!138626.java!!!	setDraught(in draught : float) : void
        this.draught = draught;
!!!138754.java!!!	getHeading() : float
        return heading;
!!!138882.java!!!	setHeading(in heading : float) : void
        this.heading = heading;
!!!139010.java!!!	getHour() : int
        return hour;
!!!139138.java!!!	setHour(in hour : int) : void
        this.hour = hour;
!!!139266.java!!!	getLatitude() : float
        return latitude;
!!!139394.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!139522.java!!!	getLength() : float
        return length;
!!!139650.java!!!	setLength(in length : float) : void
        this.length = length;
!!!139778.java!!!	getLongitude() : float
        return longitude;
!!!139906.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!140034.java!!!	getMinute() : int
        return minute;
!!!140162.java!!!	setMinute(in minute : int) : void
        this.minute = minute;
!!!140290.java!!!	getMonth() : int
        return month;
!!!140418.java!!!	setMonth(in month : int) : void
        this.month = month;
!!!140546.java!!!	getName() : String
        return name;
!!!140674.java!!!	setName(in name : String) : void
        this.name = name;
!!!140802.java!!!	getNavigationTool() : int
        return electronicPositionDevice;
!!!140930.java!!!	setNavigationTool(in navigationTool : int) : void
        this.electronicPositionDevice = navigationTool;
!!!141058.java!!!	getRot() : float
        return rot;
!!!141186.java!!!	setRot(in rot : float) : void
        this.rot = rot;
!!!141314.java!!!	getShipType() : int
        return shipType;
!!!141442.java!!!	setShipType(in shipType : int) : void
        this.shipType = shipType;
!!!141570.java!!!	getSog() : float
        return sog;
!!!141698.java!!!	setSog(in sog : float) : void
        this.sog = sog;
!!!141826.java!!!	getNavigationalStatus() : int
        return navigationalStatus;
!!!141954.java!!!	setNavigationalStatus(in navigationalStatus : int) : void
        this.navigationalStatus = navigationalStatus;
!!!142082.java!!!	getWidth() : float
        return width;
!!!142210.java!!!	setWidth(in width : float) : void
        this.width = width;
!!!142338.java!!!	getYear() : int
        return year;
!!!142466.java!!!	setYear(in year : int) : void
        this.year = year;
