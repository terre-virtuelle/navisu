class Shape_1
!!!164994.java!!!	Shape_1(inout tShip : TShip, inout sa : ShapeAttributes, inout latlon : LatLon, in d : double)
        super(sa, latlon, d);
        this.tShip = tShip;
        //  wwd = GeoWorldWindViewImpl.getWW();
        // pick();
!!!165122.java!!!	setLocation(inout location : Location) : void
        moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 100));
!!!165250.java!!!	setRotation(in cog : double) : void
        // Nothing todo
!!!165378.java!!!	getRenderables() : Renderable

        return new Renderable[]{this};
!!!165506.java!!!	toString() : String
        return "Shape_1{" + super.toString() + '}';
!!!165634.java!!!	getShip() : TShip
        return tShip;
