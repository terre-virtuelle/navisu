class BathymetryPointController
!!!153986.java!!!	BathymetryPointController()
        this.layers = new ArrayList<>();
        this.depths = new ArrayList<>();
        this.depthmap = new HashMap<>();
!!!154114.java!!!	getInstance() : BathymetryPointController
        return INSTANCE;
!!!154242.java!!!	init(in fileName : String) : Layer
        layer = new RenderableLayer();
        try {
            Path path = Paths.get(fileName);
            Stream<String> lines = Files.lines(path);
            lines.forEach(s -> build(s));
        } catch (IOException ex) {
            Logger.getLogger(BathymetryPointController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //   System.out.println(counter);
        return layer;
!!!154370.java!!!	filter(in s : String) : void
        if (counter == 10) {
            build(s);
            counter = 0;
        }
        counter++;
!!!154498.java!!!	build(in s : String) : void
        String[] tmp = s.split(" ");
        double lat = new Double(tmp[1]);
        double lon = new Double(tmp[0]);
        double depth = new Double(tmp[2]);
        // depths.add(new Depth(tmp[3], lat, lon, depth));
        //new Pair(lat, lon);
        depthmap.put(new Pair(lat, lon), depth);

        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();
        attributes.setUsePointAsDefaultImage(true);
        color = SHOM_BATHYMETRY_CLUT.getColor(depth);
        if (color != null) {
            attributes.setLineMaterial(new Material(color));
        }
        attributes.setScale(.5d);
        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setAttributes(attributes);
        layer.addRenderable(placemark);
        placemark.setValue(AVKey.DISPLAY_NAME, Double.toString(depth) + " m");
