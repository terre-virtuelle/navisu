class BathymetryLocalCatalogController
!!!149890.java!!!	BathymetryLocalCatalogController()
        wwd = GeoWorldWindViewImpl.getWW();
        this.wwd.addSelectListener(this);
!!!150018.java!!!	getInstance() : BathymetryLocalCatalogController
        return INSTANCE;
!!!150146.java!!!	init(in path : String) : List<Layer>
        this.path = path;

        BathymetryLocalCatalogLoader loader = new BathymetryLocalCatalogLoader();

        return loader.createLayersFromSource(path);//pas d'affectation si AnalyticSurface
!!!150274.java!!!	selected(inout event : SelectEvent) : void
        if (event.isRightClick()) {
            Object topObject = event.getTopObject();
            if (topObject != null) {
                if (topObject.getClass() == Polygon.class) {

                }
            }
        }
