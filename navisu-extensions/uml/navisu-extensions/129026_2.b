class BathymetryCmd
!!!130306.java!!!	getInstance(inout bathymetryDBServices : BathymetryDBServices) : BathymetryCmd
        if (INSTANCE == null) {
            INSTANCE = new BathymetryCmd(bathymetryDBServices);
        }
        return INSTANCE;
!!!130434.java!!!	BathymetryCmd(inout bathymetryDBServices : BathymetryDBServices)
        this.bathymetryDBServices = bathymetryDBServices;
!!!130562.java!!!	doIt(inout arg : NavigationData) : NavigationDataSet
        Depth depth = (Depth) arg;
        double lat = depth.getLatitude();
        double lon = depth.getLongitude();
       
        navigationDataSet = new NavigationDataSet();
        List<Point3D> points = bathymetryDBServices.retrieveIn("bathy", lat, lon, lat + 0.0015, lon + 0.0015);
        points.forEach((p) -> {
            navigationDataSet.add(p);
        });
        return navigationDataSet;
