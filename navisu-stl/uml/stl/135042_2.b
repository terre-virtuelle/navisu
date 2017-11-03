class BathymetryLocalCatalogLoader
!!!150402.java!!!	BathymetryLocalCatalogLoader()
        this.lines = new ArrayList<>();
        this.sectors = new ArrayList<>();
        this.layers = new ArrayList<>();
        layer = new RenderableLayer();
!!!150530.java!!!	createLayersFromSource(in source : String) : List<Layer>
        readfile(source);
        createAttributes();
        // createPolygons();
        //createPoints();
        return createShomBoundary();
!!!150658.java!!!	createShomBoundary() : List<Layer>
        ArrayList<Position> pathPositions = new ArrayList<>();
        pathPositions.add(Position.fromDegrees(points3d.get(8).getLat(), points3d.get(8).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(6).getLat(), points3d.get(6).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(4).getLat(), points3d.get(4).getLon(), 250));
        pathPositions.add(Position.fromDegrees(points3d.get(0).getLat(), points3d.get(0).getLon(), 250));
        for (int i = 1; i <= 157; i += 2) {
            pathPositions.add(Position.fromDegrees(points3d.get(i).getLat(), points3d.get(i).getLon(), 500));
        }
        Path pgon = new Path(pathPositions);
        pgon.setAltitudeMode(WorldWind.GREAT_CIRCLE);
        pgon.setAttributes(normalAttributes);
        pgon.setHighlightAttributes(highlightAttributes);
        layer.addRenderable(pgon);
        layer.setName("Shom");
        layers.add(layer);
        return layers;
!!!150786.java!!!	createPolygons() : List<Layer>
        ArrayList<Position> pathPositions;
        for (String s : lines) {
            String[] tab = s.split(" ");
            pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(new Double(tab[2]), new Double(tab[0]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[3]), new Double(tab[0]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[3]), new Double(tab[1]), 2500));
            pathPositions.add(Position.fromDegrees(new Double(tab[2]), new Double(tab[1]), 2500));
            Polygon pgon = new Polygon(pathPositions);
            pgon.setValue(AVKey.DISPLAY_NAME, tab[4]);
            pgon.setAltitudeMode(WorldWind.GREAT_CIRCLE);
            pgon.setAttributes(normalAttributes);
            pgon.setHighlightAttributes(highlightAttributes);
            layer.addRenderable(pgon);
        }
        layers.add(layer);
        lines.clear();
        return layers;
!!!150914.java!!!	createPoints() : List<Layer>
        PointPlacemarkAttributes attributes = new PointPlacemarkAttributes();

        //  attributes.setUsePointAsDefaultImage(true);
        PointPlacemark placemark;
        for (Point3D p : points3d) {
            placemark = new PointPlacemark(Position.fromDegrees(p.getLat(), p.getLon(), 100));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            placemark.setEnableBatchPicking(true);
            placemark.setAttributes(attributes);

            String label = "Id : " + p.getId()
                    + "Lat : " + p.getLat() + " °\n"
                    + "Lon : " + p.getLon() + " °\n";
            placemark.setValue(AVKey.DISPLAY_NAME, label);
            layer.addRenderable(placemark);
        }
        layers.add(layer);
        return layers;
!!!151042.java!!!	readfile(in source : String) : void
        try {
            points3d = Files.lines(new File(source).toPath())
                    .map(line -> line.trim())
                    .map(line -> line.split(" "))
                    .map(tab -> new Point3D(Integer.parseInt(tab[0]),
                                    Double.parseDouble(tab[1]),
                                    Double.parseDouble(tab[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
!!!151170.java!!!	createAttributes() : void
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.YELLOW);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        normalAttributes.setEnableLighting(true);

        highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(Material.WHITE);
        highlightAttributes.setDrawInterior(false);
        highlightAttributes.setDrawOutline(true);
        highlightAttributes.setOutlineOpacity(1);
