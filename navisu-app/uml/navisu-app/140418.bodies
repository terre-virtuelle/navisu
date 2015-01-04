class ShipProcessor
!!!151426.java!!!	ShipProcessor(inout layer : GeoLayer<Layer>)
        this.layer = layer;
!!!151554.java!!!	ShipProcessor(inout layer : GeoLayer<Layer>, inout tShip : TShip)
        this.layer = layer;
        this.tShip = tShip;
!!!151682.java!!!	processCreated(in id : int, inout input : TObject) : GObject

        tShip = (TShip) input;
        gShip = new GShip(id, tShip);
        return gShip;
!!!151810.java!!!	processUpdated(in id : int, inout input : TObject, inout output : GObject) : GObject
        tShip = (TShip) input;
        gShip = (GShip) output;

        gShip.setLocation(tShip.getLocation());
        gShip.setCog(tShip.getOrientation().getOrientationDegree());
        gShip.getAttributes().setInteriorMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        gShip.getAttributes().setOutlineMaterial(ShipTypeColor.VIEW.get(tShip.getType()));
        return output;
!!!151938.java!!!	processDeleted(in id : int, inout input : TObject, inout output : GObject) : GObject
        // Nothing to do here
        return output;
!!!152066.java!!!	getLayer() : GeoLayer<Layer>
        return this.layer;
!!!152194.java!!!	getType() : Class<? extends TObject>
        return TShip.class;
