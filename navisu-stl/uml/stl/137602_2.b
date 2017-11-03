class BathySoundsComponentController
!!!161794.java!!!	BathySoundsComponentController(inout layersManagerServices : LayersManagerServices, inout instrumentDriverManagerServices : InstrumentDriverManagerServices, in fileName : String)
        this.layersManagerServices = layersManagerServices;
        this.instrumentDriverManagerServices = instrumentDriverManagerServices;
        this.fileName = fileName;
        layer = layersManagerServices.getLayer(GROUP_NAME, LAYER_NAME);
        wwd = GeoWorldWindViewImpl.getWW();

        soundMap = new HashMap<>();
        imageMap = new HashMap<>();

!!!161922.java!!!	on() : void
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            lines.stream().filter((l) -> (!l.equals(""))).map((l) -> l.split(",")).map((values) -> {
                double lat = Double.valueOf(values[0]);
                double lon = Double.valueOf(values[1]);
                String image = IMAGE_DATA_PATH + "/data/sounds/bathy/images/" + values[2];
                String text = values[3];
                String sound = "/data/sounds/bathy/sounds/" + values[4];
                soundMap.put(new Point3D(lat, lon), SOUND_DATA_PATH + sound.trim());

                PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
                PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
                attrs.setImageAddress(image);
                placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
                placemark.setLabelText(text);
                placemark.setAttributes(attrs);
                return placemark;
            }).forEachOrdered((placemark) -> {
                layer.addRenderable(placemark);
            });

            wwd.addSelectListener(
                    new SelectEventListener(SelectEvent.LEFT_CLICK,
                            PointPlacemark.class,
                            new SoundAction(instrumentDriverManagerServices, soundMap))
            );
        } catch (IOException ex) {
            Logger.getLogger(BathySoundsComponentController.class.getName()).log(Level.SEVERE, null, ex);
        }
