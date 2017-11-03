class BathymetryAnalyticController
!!!153090.java!!!	BathymetryAnalyticController()
        this.layers = new ArrayList<>();
        this.depths = new ArrayList<>();
        this.depthmap = new HashMap<>(250000);
        this.latDepths = new ArrayList<>();
        this.lonDepths = new ArrayList<>();
        this.gridPoints = new double[10200][9900];
!!!153218.java!!!	getInstance() : BathymetryAnalyticController
        return INSTANCE;
!!!153346.java!!!	init(in fileName : String) : Layer
        latDepths.clear();
        lonDepths.clear();
        layer = new RenderableLayer();
        try {
            Path path = Paths.get(fileName);
            this.fileName = path.getFileName().toString();
            Stream<String> lines = Files.lines(path);
            lines.forEach(s -> build(s));
        } catch (IOException ex) {
            Logger.getLogger(BathymetryAnalyticController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getBoundaries();
        analytic();
        return layer;
!!!153474.java!!!	filter(in s : String) : void
        if (counter == 1) {
            build(s);
            counter = 0;
        }
        counter++;
!!!153602.java!!!	build(in s : String) : void
        String[] tmp = s.split(" ");
        double lat = new Double(tmp[1]);
        double lon = new Double(tmp[0]);
        double depth = new Double(tmp[2]);
        if (cache != lat) {
            cache = lat;
            counter++;
        }
         depths.add(new Depth(lat, lon, depth));
        latDepths.add(lat);
        lonDepths.add(lon);
        // depthmap.put(new Pair(lat, lon), depth);
!!!153730.java!!!	getBoundaries() : void
        gridFactory = new GridFactory(latDepths, lonDepths);
        minLat = gridFactory.getMinLat();
        maxLat = gridFactory.getMaxLat();
        minLon = gridFactory.getMinLon();
        maxLon = gridFactory.getMaxLon();
    //    System.out.println("minLat " + minLat);
    //    System.out.println("maxLat " + maxLat);
    //    System.out.println("minLon " + minLon);
    //    System.out.println("maxLon " + maxLon);
    //    System.out.println("latRange " + gridFactory.getLatRange());
    //    System.out.println("lonRange " + gridFactory.getLonRange());
    //    System.out.println("latDepths " + latDepths.size() + "  lonDepths " + lonDepths.size());
    //    System.out.println("counter " + counter);
        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(Material.YELLOW);
        //  normalAttributes.setOutlineOpacity(0.5);
        //  normalAttributes.setInteriorOpacity(0.8);
        normalAttributes.setOutlineMaterial(Material.GREEN);
        //   normalAttributes.setOutlineWidth(2);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);
        normalAttributes.setEnableLighting(true);

        ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
        highlightAttributes.setOutlineMaterial(Material.WHITE);
        highlightAttributes.setOutlineOpacity(1);
        ArrayList<Position> pathPositions = new ArrayList<>();

        pathPositions.add(Position.fromDegrees(minLat, minLon, 2e4));
        pathPositions.add(Position.fromDegrees(maxLat, minLon, 2e4));
        pathPositions.add(Position.fromDegrees(maxLat, maxLon, 2e4));
        pathPositions.add(Position.fromDegrees(minLat, maxLon, 2e4));

        Polygon polygon = new Polygon(pathPositions);
        polygon.setAttributes(normalAttributes);
        polygon.setHighlightAttributes(highlightAttributes);
        // polygon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
        polygon.setValue(AVKey.DISPLAY_NAME, this.fileName);
       //  layer.addRenderable(polygon);
!!!153858.java!!!	analytic() : void
        AnalyticSurface surface = new AnalyticSurface();
        surface.setSector(new Sector(Sector.fromDegrees(gridFactory.getSector()[0],
                gridFactory.getSector()[1],
                gridFactory.getSector()[2],
                gridFactory.getSector()[3])));
        double lonRange=gridFactory.getLatRange();
        double latRange= gridFactory.getLonRange();
        surface.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        surface.setDimensions(WIDTH, HEIGHT);
        surface.setClientLayer(layer);
        layer.addRenderable(surface);
        layer.setEnabled(true);
        int tmpI;
        double tmpD;
        int line = 0;
        int col = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                gridPoints[i][j] = SHOM_BATHYMETRY_CLUT.MAX;
            }
        }
       
        for (Depth c : depths) {
            tmpD = Math.abs((Math.abs(c.getLongitude()) - minLon) / lonRange);
            tmpI = (int) (tmpD);
            if (tmpD - tmpI > 0) {
                if (tmpI < WIDTH - 1) {//-2
                    col = tmpI;//1
                } else {
                    col = tmpI;
                }
            }
            tmpD = Math.abs((Math.abs(c.getLatitude()) - minLat) / latRange);
            tmpI = (int) (tmpD);

            if (tmpD - tmpI > 0) {
                if (tmpI < HEIGHT - 1) {//-2
                    line = HEIGHT - tmpI;//+1
                } else {
                    line = HEIGHT - tmpI;
                }
            }
            if (line < WIDTH && col < HEIGHT) {
                gridPoints[line][col] = c.getDepth();
            }
        }
         ArrayList<AnalyticSurface.GridPointAttributes> attributesList = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tmpD = gridPoints[i][j];
              //  if (tmpD != 0.0) {
                    attributesList.add(AnalyticSurface.createGridPointAttributes(tmpD, SHOM_BATHYMETRY_CLUT.getColor(tmpD)));
             //   }
            }
        }
        surface.setValues(attributesList);
        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setDrawShadow(false);
        attr.setInteriorOpacity(1);
        attr.setDrawOutline(false);
        attr.setOutlineWidth(0);
        surface.setSurfaceAttributes(attr);

