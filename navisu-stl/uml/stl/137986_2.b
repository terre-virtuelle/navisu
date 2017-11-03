class SoundAction
!!!162434.java!!!	SoundAction(inout instrumentDriverManagerServices : InstrumentDriverManagerServices, inout soundMap : Map<Point3D, String>)
        this.soundMap = soundMap;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
!!!162562.java!!!	doIt(inout point : Point3D) : void
        String sound = soundMap.get(point);
        instrumentDriverManagerServices.open(sound, "true", "1");
