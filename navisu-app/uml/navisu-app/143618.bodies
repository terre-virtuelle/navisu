class Shape3D_0
!!!163330.java!!!	Shape3D_0(inout tShip : TShip, in fileName : String, inout latlon : LatLon, in d : double)
        this.tShip = tShip;
        this.fileName = fileName;
        try {
            colladaRoot = ColladaRoot.createAndParse(new File(fileName));
            colladaRoot.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            colladaRoot.setHeading(Angle.fromDegrees(30.0));
           // colladaRoot.setPosition(Position.fromDegrees(48.3649, -4.490, 0));
           // latlon.
            colladaRoot.setPosition(new Position(latlon.getLatitude(), latlon.getLongitude(),0));
            colladaRoot.setPitch(Angle.fromDegrees(0.0));
            colladaRoot.setRoll(Angle.fromDegrees(0.0));
            colladaRoot.setModelScale(new Vec4(20.0));
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(GpsLocator.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create a KMLController to adapt the KMLRoot to the World Wind renderable interface.
        colladaController = new ColladaController(colladaRoot);
!!!163458.java!!!	setLocation(inout location : Location) : void

!!!163586.java!!!	getRenderables() : Renderable
        return new Renderable[]{colladaController};
!!!163714.java!!!	setRotation(in cog : double) : void
        // super.setRotation(-cog);
!!!163842.java!!!	toString() : String
        return "Shape_0{" + super.toString() + '}';
!!!163970.java!!!	getShip() : TShip
        return tShip;
!!!164098.java!!!	getAttributes() : ShapeAttributes
        return null;
