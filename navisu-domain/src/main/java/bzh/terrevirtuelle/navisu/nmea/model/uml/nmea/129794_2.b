class AISType4
!!!150146.java!!!	decodeFrame() : void
        // System.out.println("messageAisBinary : " + messageAisBinary);
        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            year = binaryToInt(messageAisBinary, 38, 52);
            month = binaryToInt(messageAisBinary, 52, 56);
            day = binaryToInt(messageAisBinary, 56, 61);
            hour = binaryToInt(messageAisBinary, 61, 67);
            minute = binaryToInt(messageAisBinary, 67, 73);
            second = binaryToInt(messageAisBinary, 73, 79);
            date = new GregorianCalendar(year, month, day, hour, minute, second);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 80, 107))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 107, 134))) / 60;
        }
!!!150274.java!!!	toString() : String
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        return "AISType4{ISMM=" + MMSI
                + ", LAT=" + latitude + ", LONG=" + longitude
                + ", DATE=" + date != null ? dateFormat.format(date.getTime()) : ""
                +"}";
!!!150402.java!!!	getDate() : Calendar
        return date;
!!!150530.java!!!	setDate(inout date : Calendar) : void
        this.date = date;
!!!150658.java!!!	getDay() : int
        return day;
!!!150786.java!!!	setDay(in day : int) : void
        this.day = day;
!!!150914.java!!!	getHour() : int
        return hour;
!!!151042.java!!!	setHour(in hour : int) : void
        this.hour = hour;
!!!151170.java!!!	getLatitude() : float
        return latitude;
!!!151298.java!!!	setLatitude(in latitude : float) : void
        this.latitude = latitude;
!!!151426.java!!!	getLongitude() : float
        return longitude;
!!!151554.java!!!	setLongitude(in longitude : float) : void
        this.longitude = longitude;
!!!151682.java!!!	getMonth() : int
        return month;
!!!151810.java!!!	setMonth(in month : int) : void
        this.month = month;
!!!151938.java!!!	getSecond() : int
        return second;
!!!152066.java!!!	setSecond(in second : int) : void
        this.second = second;
!!!152194.java!!!	getYear() : int
        return year;
!!!152322.java!!!	setYear(in year : int) : void
        this.year = year;
