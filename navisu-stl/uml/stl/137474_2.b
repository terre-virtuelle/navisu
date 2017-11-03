class BathySoundsImpl
!!!160898.java!!!	componentInitiated() : void

!!!161026.java!!!	canOpen(in category : String) : boolean
        return category.equals(KEY_NAME);
!!!161154.java!!!	getDriver() : InstrumentDriver
        return this;
!!!161282.java!!!	on(inout  : String...files) : void
        controller = new BathySoundsComponentController(layersManagerServices, 
                instrumentDriverManagerServices,
                "data/sounds/bathy/soundsAM.csv");
        controller.on();
!!!161410.java!!!	off() : void

