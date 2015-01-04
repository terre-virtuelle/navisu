class GStation
!!!161538.java!!!	GStation(in id : int, inout tStation : TStation)
        this.id = id;
        this.tStation = tStation;
        tStation.setGStation(this);
        shape = new Shape_4(Position.fromDegrees(tStation.getLatitude(), tStation.getLongitude()));
        shape.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
        shape.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        attrs.setImageOffset(new Offset(24d, 0d, AVKey.PIXELS, AVKey.PIXELS));
        shape.setClipToHorizon(true);
        attrs.setImageAddress("img/emetteur_1.png");
        attrs.setScale(.6);
        shape.setAttributes(attrs);
!!!161666.java!!!	getID() : int
        return this.id;
!!!161794.java!!!	setLocation(inout location : Location) : void
        shape.setLocation(location);
!!!161922.java!!!	getRenderables() : Renderable
        return shape.getRenderables();
!!!162050.java!!!	getClone() : Object
        return this;
!!!162178.java!!!	toString() : String
        return "GStation{" + "id=" + id + ", tStation=" + tStation + ", shape=" + shape + '}';
!!!162306.java!!!	getShape() : Shape_4
        return shape;
!!!162434.java!!!	getAttributes() : PointPlacemarkAttributes
        return shape.getAttributes();
