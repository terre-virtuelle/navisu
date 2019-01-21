class TargetCmd
!!!132354.java!!!	getInstance(inout s57ChartComponentServices : S57ChartComponentServices, inout geodesyServices : GeodesyServices, inout layersManagerServices : LayersManagerServices) : TargetCmd
        if (INSTANCE == null) {
            INSTANCE = new TargetCmd(s57ChartComponentServices, geodesyServices, layersManagerServices);
        }
        return INSTANCE;
!!!132482.java!!!	TargetCmd(inout s57ChartComponentServices : S57ChartComponentServices, inout geodesyServices : GeodesyServices, inout layersManagerServices : LayersManagerServices)
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.geodesyServices = geodesyServices;
        layer = layersManagerServices.getLayer(GROUP, NAME);
!!!132610.java!!!	doIt(inout arg : NavigationData) : NavigationDataSet
        // Set of beacon, buoys, ... on the chart
        s57Controllers = s57ChartComponentServices.getS57Controllers();
        navigationDataSet = new NavigationDataSet();

        Target target = (Target) arg;
        double lat = target.getLatitude();
        double lon = target.getLongitude();
        double distance = target.getDistance();
        double azimuth = target.getAzimuth();

        //First filter the type of NavigatiobData
        Class type = target.getNavigationData().getClass();
        Set<S57Controller> validS57 = new HashSet<>();
        List<Class> types;
        for (S57Controller s : s57Controllers) {
            types = getSuperClasses(s.getNavigationData());
            if (types.contains(type)) {
                validS57.add(s);
            }
        }

        if (!validS57.isEmpty()) {
            //Calculate distance and azimuth from locate point
            double dist;
            double azi;
            Target tgt;
            int id = 0;
            List<Target> targets = new ArrayList<>();
            List<Target> targetsSended = new ArrayList<>();
            for (S57Controller s : validS57) {
                dist = geodesyServices.getDistanceM(s.getLat(), s.getLon(), lat, lon);
                azi = geodesyServices.getAzimuth(lat, lon, s.getLat(), s.getLon());
                tgt = new Target(s.getNavigationData(), s.getNavigationData().getLatitude(),
                        s.getNavigationData().getLongitude(), id, dist, azi);
                targets.add(tgt);
                id++;
            }
            //Filters from TargetCmd
            targets.sort(Comparator.comparingDouble(Target::getDistance));
            if (distance == -1 && azimuth == 511) {
                navigationDataSet.add(targets.get(0));
                targetsSended.add(targets.get(0));
            }
            if (distance != -1 && azimuth == 511) {
                targets.stream().filter((t) -> (t.getDistance() <= distance)).forEachOrdered((t) -> {
                    navigationDataSet.add(t);
                    targetsSended.add(t);
                });
            }
            if (distance != -1 && azimuth != 511) {
                targets.stream().filter((t) -> (t.getDistance() <= distance
                        && t.getAzimuth() >= azimuth - 10 && t.getAzimuth() <= azimuth + 10)).map((t) -> {
                    navigationDataSet.add(t);
                    return t;
                }).forEachOrdered((t) -> {
                    targetsSended.add(t);
                });
            }
            if (distance == -1 && azimuth != 511) {
                targets.stream().filter((t) -> (t.getAzimuth() >= azimuth - 10 && t.getAzimuth() <= azimuth + 10)).map((t) -> {
                    navigationDataSet.add(t);
                    return t;
                }).forEachOrdered((t) -> {
                    targetsSended.add(t);
                });
            }

            viewTargets(targetsSended, lat, lon);
        }
        return navigationDataSet;
!!!132738.java!!!	getSuperClasses(inout o : Object) : List<Class>
        List<Class> classList = new ArrayList<>();
        Class classe = o.getClass();
        classList.add(classe);
        Class superclass = classe.getSuperclass();
        classList.add(superclass);
        while (superclass != null) {
            classe = superclass;
            superclass = classe.getSuperclass();
            if (superclass != null) {
                classList.add(superclass);
            }
        }
        return classList;
!!!132866.java!!!	viewTargets(inout targets : List<Target>, in lat : double, in lon : double) : void
        PointPlacemark pp = new PointPlacemark(Position.fromDegrees(lat, lon, 10));
        pp.setValue(AVKey.DISPLAY_NAME, "Lat : " + Double.toString(lat) + "\n "
                + "Lon : " + Double.toString(lon));
        layer.addRenderable(pp);

        ShapeAttributes attrs = new BasicShapeAttributes();
        attrs.setOutlineMaterial(Material.RED);
        attrs.setOutlineWidth(2d);

        targets.stream().map((t) -> {
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(lat, lon, 10));
            pathPositions.add(Position.fromDegrees(t.getLatitude(),
                    t.getLongitude(), 10));
            Path path = new DirectedPath(pathPositions);
            path.setAttributes(attrs);
            path.setVisible(true);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            path.setPathType(AVKey.GREAT_CIRCLE);
            path.setValue(AVKey.DISPLAY_NAME,
                    "distance = "
                    + String.format("%.0f", t.getDistance()) + " m \n "
                    + "azimuth : "
                    + String.format("%.0f", t.getAzimuth()) + "°");
            return path;
        }).forEachOrdered((path) -> {
            layer.addRenderable(path);
        });
