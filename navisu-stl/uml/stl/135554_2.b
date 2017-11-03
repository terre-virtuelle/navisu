class BathymetryImpl
!!!151810.java!!!	getDriver() : Driver
        return this;
!!!151938.java!!!	openFile(in file : String) : void
        this.open(null, file);
!!!152066.java!!!	canOpen(in category : String, in file : String) : boolean
        boolean canOpen = false;

        if (category.contains(NAME) && file.toLowerCase().endsWith(EXTENSION_0)) {
            canOpen = true;
        }
        System.out.println("OK ");
        return canOpen;
!!!152194.java!!!	open(inout progressHandle : ProgressHandle, inout  : String...files) : void
        for (String file : files) {
            this.handleOpenFile(progressHandle, file);
        }
!!!152322.java!!!	handleOpenFile(inout pHandle : ProgressHandle, in fileName : String) : void
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        BathymetryAnalyticController bathymetryController = BathymetryAnalyticController.getInstance();
        layers.add(bathymetryController.init(fileName));
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            String name = l.getName();
            //  if (name.contains(NAME)) {
            l.setPickEnabled(true);
            // } else {
            //     l.setPickEnabled(false);
            //}
            //  System.out.println("l : "+l);
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });

!!!152450.java!!!	getName() : String
        return NAME;
!!!152578.java!!!	getExtensions() : String
        return new String[]{"*" + EXTENSION_0};
!!!152706.java!!!	componentInitiated() : void
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        layers = new ArrayList<>();
