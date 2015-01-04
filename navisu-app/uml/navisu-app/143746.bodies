class Shape_0
!!!164226.java!!!	Shape_0(inout tShip : TShip, inout sa : ShapeAttributes, inout itrbl : Iterable<? extends Position>)
        super(itrbl);
        this.tShip = tShip;
        setAttributes(sa);
!!!164354.java!!!	setLocation(inout location : Location) : void
        moveTo(new Position(Angle.fromDegrees(location.getLatitudeDegree()),
                Angle.fromDegrees(location.getLongitudeDegree()), 15));
!!!164482.java!!!	getRenderables() : Renderable
        return new Renderable[]{this};
!!!164610.java!!!	setRotation(in cog : double) : void
        super.setRotation(-cog);
!!!164738.java!!!	toString() : String
        return "Shape_0{" + super.toString() + '}';
!!!164866.java!!!	getShip() : TShip
        return tShip;
