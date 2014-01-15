class AISType11
!!!143106.java!!!	getETADate() : Calendar
        return ETADate;
!!!143234.java!!!	setETADate(inout ETADate : Calendar) : void
        this.ETADate = ETADate;
!!!143362.java!!!	getYear() : int
        return year;
!!!143490.java!!!	setYear(in year : int) : void
        this.year = year;
!!!143618.java!!!	getMonth() : int
        return month;
!!!143746.java!!!	setMonth(in month : int) : void
        this.month = month;
!!!143874.java!!!	getDay() : int
        return day;
!!!144002.java!!!	setDay(in day : int) : void
        this.day = day;
!!!144130.java!!!	getHour() : int
        return hour;
!!!144258.java!!!	setHour(in hour : int) : void
        this.hour = hour;
!!!144386.java!!!	getMinute() : int
        return minute;
!!!144514.java!!!	setMinute(in minute : int) : void
        this.minute = minute;
!!!144642.java!!!	getSecond() : int
        return second;
!!!144770.java!!!	setSecond(in second : int) : void
        this.second = second;
!!!144898.java!!!	getLatitude() : float
        return latitude;
!!!145026.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!145154.java!!!	getLongitude() : float
        return longitude;
!!!145282.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!145410.java!!!	decodeFrame() : void

        if (messageAisBinary.length() == 167) {

            MMSI = binaryToInt(messageAisBinary, 8, 38);
            year = binaryToInt(messageAisBinary, 38, 52);
            month = binaryToInt(messageAisBinary, 52, 56);
            day = binaryToInt(messageAisBinary, 56, 61);
            hour = binaryToInt(messageAisBinary, 61, 67);
            minute = binaryToInt(messageAisBinary, 67, 73);
            second = binaryToInt(messageAisBinary, 73, 79);
            ETADate = new GregorianCalendar(year, month, day, hour, minute, second);
            longitude = -((float) (0.0001 * complementToInt(messageAisBinary, 80, 107))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 107, 134))) / 60;
            try {
                //	position = new WGS84Location (latitude, longitude);
            } catch (NumberFormatException e) {
                throw (new IllegalArgumentException());
            }
        }
!!!145538.java!!!	toString() : String
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        return "AISType11{ISMM=" + MMSI + ", LAT=" + latitude + ", LONG=" + longitude + ", ETA=" + dateFormat.format(ETADate.getTime() + "}");
