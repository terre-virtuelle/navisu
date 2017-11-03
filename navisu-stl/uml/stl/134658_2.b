class BathymetryLocalCatalogImpl
!!!148354.java!!!	openFile(in file : String) : void
        this.open(null, file);
!!!148482.java!!!	canOpen(in category : String, in file : String) : boolean
        boolean canOpen = false;

        if (category.contains(NAME) && file.toLowerCase().endsWith(EXTENSION_0)) {
            canOpen = true;
        }
        return canOpen;
!!!148610.java!!!	open(inout progressHandle : ProgressHandle, inout  : String...files) : void
        for (String file : files) {
            this.handleOpenFile(progressHandle, file);
        }
!!!148738.java!!!	handleOpenFile(inout pHandle : ProgressHandle, in fileName : String) : void
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        BathymetryLocalCatalogController bathymetryLocalCatalogController = BathymetryLocalCatalogController.getInstance();
        layers = bathymetryLocalCatalogController.init(fileName);
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
!!!148866.java!!!	getName() : String
        return NAME;
!!!148994.java!!!	getExtensions() : String
        return new String[]{"*" + EXTENSION_0};
!!!149122.java!!!	componentInitiated() : void
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();

!!!149250.java!!!	clip() : void
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
!!!149378.java!!!	unClip() : void
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(true);
            });
        }
!!!149506.java!!!	getDriver() : Driver
        return this;
!!!149634.java!!!	componentStarted() : void
        /* Nothing to do here */
!!!149762.java!!!	componentStopped() : void
        /* Nothing to do here */
