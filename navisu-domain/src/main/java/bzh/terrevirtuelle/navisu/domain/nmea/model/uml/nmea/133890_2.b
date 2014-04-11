class ZDA
!!!217218.java!!!	ZDA(in device : String, in sentence : String, inout date : Calendar)
        super(device, sentence);
        this.date = date;
!!!217474.java!!!	getDate() : Calendar
        return date;
!!!217602.java!!!	setDate(inout date : Calendar) : void
        this.date = date;
!!!217730.java!!!	toString() : String
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/YY", Locale.FRENCH);
        simpleDateFormat.setLenient(false);
        return "ZDA{" + "date=" + simpleDateFormat.format(date.getTime()) + '}';
