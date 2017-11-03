class DisplayBathymetryImpl
!!!163202.java!!!	getDriver() : InstrumentDriver
        return this;
!!!163330.java!!!	componentInitiated() : void
        layer = layersManagerServices.getLayer(GROUP, LAYER_NAME);
        controller = DisplayBathymetryController.getInstance(this,
                bathymetryDBServices, guiAgentServices,
                displayServices, delaunayServices, jtsServices,
                layer);
!!!163714.java!!!	canOpen(in category : String) : boolean
        boolean canOpen = false;
        if (!category.equals(NAME)) {
        } else {
            canOpen = true;
        }
        return canOpen;
!!!163842.java!!!	on(inout  : String...files) : void
        String[] tab = files;

!!!163970.java!!!	displaySounding(in lat : double, in lon : double, in depth : double, inout l : RenderableLayer) : void
        controller.displaySounding(lat, lon, depth, l);
!!!164098.java!!!	displaySounding(inout points : List<Point3D>, inout l : RenderableLayer) : void
        controller.displaySounding(points, l);
!!!164226.java!!!	displayDelaunaySounding(inout points : List<Point3D>, inout layer : RenderableLayer, in maxElevation : double) : void
         controller.displayDelaunaySounding(points, layer, maxElevation);
