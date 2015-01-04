class GShip
!!!159618.java!!!	GShip(in id : int, inout ship : TShip)
        this.id = id;
        this.tShip = ship;
        if (tShip.getShapeId() == 0) {
            shape = new Shape_0(tShip, makeAttributes(),
                    makePositionList(initShape(tShip.getLatitude(), tShip.getLongitude())));
        }
        if (tShip.getShapeId() == 1) {
            shape = new Shape_1(tShip, makeAttributes(),
                    new LatLon(Angle.fromDegrees(tShip.getLatitude()), Angle.fromDegrees(tShip.getLongitude())),
                    40.0);
        }
        if (tShip.getShapeId() == 36) {
            shape = new Shape3D_0(tShip, "data/collada/sail01.dae",
                    new LatLon(Angle.fromDegrees(tShip.getLatitude()), Angle.fromDegrees(tShip.getLongitude())),
                    40.0);
        }
!!!159746.java!!!	getID() : int
        return this.id;
!!!159874.java!!!	getShape() : Shape
        return shape;
!!!160002.java!!!	setShape(inout shape : Shape) : void
        this.shape = shape;
!!!160130.java!!!	getShip() : TShip
        return tShip;
!!!160258.java!!!	setShip(inout ship : TShip) : void
        this.tShip = ship;
!!!160386.java!!!	setLocation(inout location : Location) : void
        shape.setLocation(location);
!!!160514.java!!!	getAttributes() : ShapeAttributes
        return shape.getAttributes();
!!!160642.java!!!	getRenderables() : Renderable
        return shape.getRenderables();
!!!160770.java!!!	setCog(in cog : double) : void
        shape.setRotation(cog);
!!!160898.java!!!	makeAttributes() : ShapeAttributes
        final ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        pathAttrs.setOutlineOpacity(0.8);
        pathAttrs.setOutlineWidth(1);
        pathAttrs.setInteriorMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        pathAttrs.setDrawInterior(true);
        pathAttrs.setInteriorOpacity(1.0);
        return pathAttrs;
!!!161026.java!!!	initShape(in latitude : double, in longitude : double) : double
       
        double[] shipShape = new double[10];
        shipShape[0] = longitude;
        shipShape[1] = latitude + 0.00150;
        shipShape[2] = longitude;
        shipShape[3] = latitude + 0.00075;
        shipShape[4] = longitude + 0.0005;
        shipShape[5] = latitude - 0.00075; 
        shipShape[6] = longitude - 0.0005;
        shipShape[7] = latitude - 0.00075;
        shipShape[8] = longitude;
        shipShape[9] = latitude + 0.00075;
        return shipShape;
!!!161154.java!!!	makePositionList(inout src : double) : List<Position>
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 15);
        }
        return Arrays.asList(array);
!!!161282.java!!!	getClone() : Object
        return this;
!!!161410.java!!!	toString() : String
        return "GShip{" + "id=" + id + ", tShip=" + tShip + ", shape=" + shape + '}';
