class OSMBuildingsStlRenderable
!!!146178.java!!!	OSMBuildingsStlRenderable(inout doc : GeoJSONDoc, in defaultHeight : double)
        super(doc,defaultHeight);
        this.doc = doc;
        this.defaultHeight = defaultHeight;

        // --- Prepare the renderable
        if (doc.getRootObject() instanceof GeoJSONObject) {
            GeoJSONObject obj = (GeoJSONObject) doc.getRootObject();
            prepare(obj);
        } else if (doc.getRootObject() instanceof Object[]) {
            for (Object o : (Object[]) doc.getRootObject()) {
                if (o instanceof GeoJSONObject) {
                    prepare((GeoJSONObject) o);
                }
            }
        } else {
            // ---
        }
!!!146306.java!!!	toString() : String
        return "Contains " + renderables.size() + " elements to render";
!!!146434.java!!!	clear() : void
        renderables.clear();
!!!146562.java!!!	render(inout dc : DrawContext) : void
        renderables.forEach((r) -> {
            r.render(dc);
        });
!!!146690.java!!!	preRender(inout dc : DrawContext) : void
        renderables.stream().filter((r) -> (r instanceof PreRenderable)).forEachOrdered((r) -> {
            ((PreRenderable) r).preRender(dc);
        });
!!!146818.java!!!	dispose() : void
        for (Renderable r : renderables) {
            if (r instanceof Disposable)
                ((Disposable) r).dispose();
        }
        renderables.clear();
!!!146946.java!!!	prepare(inout object : GeoJSONObject) : void
        if (object.isGeometry()) {
            fill(object.asGeometry(), null);
        } else if (object.isFeature()) {
            GeoJSONFeature f = object.asFeature();
            fill(f.getGeometry(), f.getProperties());
        } else if (object.isFeatureCollection()) {
            GeoJSONFeatureCollection c = object.asFeatureCollection();
            for (GeoJSONFeature f : c.getFeatures()) {
                fill(f.getGeometry(), f.getProperties());
            }
        }
!!!147074.java!!!	fill(inout geom : GeoJSONGeometry, inout properties : AVList) : void
        if (geom.isPoint()) {
            GeoJSONPoint pt = geom.asPoint();
            PointPlacemarkAttributes pa = new PointPlacemarkAttributes();
            fillRenderablePoint(pt, pt.getPosition(), pa, properties);
        } else if (geom.isMultiPoint()) {
            GeoJSONMultiPoint mp = geom.asMultiPoint();
            PointPlacemarkAttributes pa = new PointPlacemarkAttributes();
            for (int i = 0; i < mp.getPointCount(); i++) {
                fillRenderablePoint(mp.asPoint(), mp.getPosition(i), pa, properties);
            }
        } else if (geom.isLineString()) {
            String msg = Logging.getMessage("Geometry rendering of line not supported");
            Logging.logger().warning(msg);
            // this.addRenderableForLineString(geom.asLineString(), layer, properties);

        } else if (geom.isMultiLineString()) {
            GeoJSONMultiLineString ms = geom.asMultiLineString();
            BasicShapeAttributes sa = new BasicShapeAttributes();
            fillShapeAttribute(sa, properties);
            for (GeoJSONPositionArray coords : ms.getCoordinates()) {
                fillRenderablePolyline(geom, coords, sa, properties);
            }
        } else if (geom.isPolygon()) {
            GeoJSONPolygon poly = geom.asPolygon();
            BasicShapeAttributes sa = new BasicShapeAttributes();
            fillShapeAttribute(sa, properties);
            // dumpAVList(properties);
            fillRenderablePolygon(poly, poly.getExteriorRing(), poly.getInteriorRings(), sa, properties);
        } else if (geom.isMultiPolygon()) {
            GeoJSONMultiPolygon mpoly = geom.asMultiPolygon();
            BasicShapeAttributes sa = new BasicShapeAttributes();
            fillShapeAttribute(sa, properties);
            for (int i = 0; i < mpoly.getPolygonCount(); i++) {
                fillRenderablePolygon(mpoly.asPolygon(), mpoly.getExteriorRing(i), mpoly.getInteriorRings(i), sa, properties);
            }
        } else if (geom.isGeometryCollection()) {
            GeoJSONGeometryCollection c = geom.asGeometryCollection();
            GeoJSONGeometry geos[] = c.getGeometries();
            for (int i = 0; i < geos.length; i++) {
                fill(geos[i], properties);
            }
        } else {
            String msg = Logging.getMessage("Geometry not supported");
            Logging.logger().warning(msg);
        }
!!!147202.java!!!	fillRenderablePoint(inout owner : GeoJSONPoint, inout pos : Position, inout attrs : PointPlacemarkAttributes, inout properties : AVList) : void
        PointPlacemark p = new PointPlacemark(pos);
        p.setAttributes(attrs);
        if (pos.getAltitude() != 0) {
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setLineEnabled(true);
        } else {
            p.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        }

        if (properties != null)
            p.setValue(AVKey.PROPERTIES, properties);

        renderables.add(p);
!!!147330.java!!!	fillRenderablePolygon(inout owner : GeoJSONPolygon, inout outerBoundary : Iterable<? extends Position>, inout innerBoundaries : Iterable<? extends Position>, inout attrs : ShapeAttributes, inout properties : AVList) : void
        if (hasNonzeroAltitude(outerBoundary)) {
            // --- It a ploygin with height (not a flat foot print)
            Polygon poly = new Polygon(outerBoundary);
            poly.setAttributes(attrs);
            if (innerBoundaries != null) {
                for (Iterable<? extends Position> iter : innerBoundaries) {
                    poly.addInnerBoundary(iter);
                }
            }

            if (properties != null)
                poly.setValue(AVKey.PROPERTIES, properties);

            renderables.add(poly);
        } else if (defaultHeight > 0) {
            // --- The polygon should be a volume
            double height = 0;
            double minHeight = 0;
            double levels = 0;
            String roofColor = "gray";
            String roofShape = "flat";
            double roofHeight = 0;
            String roofMaterial = "concrete";
            String roofOrientation = "along";
            String roofDirection = "";
            if (properties != null) {
                if (properties.getValue("height") != null)
                    height = (Double) properties.getValue("height");
                if (properties.getValue("levels") != null)
                    levels = (Double) properties.getValue("levels");
                if (properties.getValue("minHeight") != null)
                    minHeight = (Double) properties.getValue("minHeight");
                // --- Roof
                if (properties.getValue("roofColor") != null)
                    roofColor = (String) properties.getValue("roofColor");
                if (properties.getValue("roofShape") != null)
                    roofShape = (String) properties.getValue("roofShape");
                if (properties.getValue("roofHeight") != null)
                    roofHeight = (Double) properties.getValue("roofHeight");
                if (properties.getValue("roofMaterial") != null)
                    roofMaterial = (String) properties.getValue("roofMaterial");
                if (properties.getValue("roofOrientation") != null)
                    roofOrientation = (String) properties.getValue("roofOrientation");
                if (properties.getValue("roofDirection") != null)
                    roofDirection = (String) properties.getValue("roofDirection");

            }
            if (roofColor == null)
                roofColor = "gray";

            // --- Check if height is correct
            if (height <= 0) {
                // --- Sometimes level are set, but no height
                // --- Consider 1 level to be4 meters
                height = (levels == 0 ? defaultHeight : levels * 4);
                minHeight = 0;
            } else if (minHeight >= height) {
                height = minHeight + 1;
            }
            // --- If levels, try some texture on it
            /*
             * if (levels > 0){ BufferedImage tex = new BufferedImage(100, (int) height, BufferedImage.TYPE_INT_ARGB); Graphics2D g2 = (Graphics2D) tex.getGraphics(); int floor = (int)
             * (height/levels); g2.setColor(new Color(0,0,255,128)); for (int i=0;i<levels;i++) { g2.fillRect(10, (floor*i)+1, 80, floor-2); } System.out.println("LEVELS:"+levels);
             * attrs.setImageSource(tex); }
             */
            // ShapeAttributes at = new BasicShapeAttributes();
            // attrs.setInteriorMaterial(Material.WHITE);
            // at.setOutlineOpacity(0.5);
            // attrs.setInteriorOpacity(1);
            // at.setOutlineMaterial(Material.GREEN);
            // at.setOutlineWidth(2);
            // attrs.setDrawOutline(false);
            // attrs.setDrawInterior(true);
            // attrs.setEnableLighting(true);

            // --- Roof cap
            ShapeAttributes ra = new BasicShapeAttributes();
            ra.setInteriorMaterial(new Material(stringToColor(roofColor)));
            ra.setOutlineMaterial(new Material(stringToColor(roofColor)));
            ra.setInteriorOpacity(roofMaterial.equals("glass") ? 0.7 : 1);
            ra.setDrawInterior(true);
            ra.setEnableLighting(true);
            ra.setDrawOutline(false);
            ra.setEnableAntialiasing(true);

            // --- Walls with default cap (flat roof)
            ExtrudedPolygon box = new ExtrudedPolygon(height);
            box.setAltitudeMode(WorldWind.CONSTANT);
            box.setAttributes(attrs);
            box.setSideAttributes(attrs);
            box.setCapAttributes(ra);
            box.setVisible(true);
            box.setOuterBoundary(outerBoundary);
            box.setBaseDepth(-minHeight); // --- negative value will push the base up instead of below
            if (innerBoundaries != null) {
                for (Iterable<? extends Position> iter : innerBoundaries) {
                    box.addInnerBoundary(iter);
                }
            }
            renderables.add(box);

            if (roofShape.equals("pyramid") || roofShape.equals("pyramidal")) {
                // --- Flat
                /*
                 * Polygon roof = new Polygon(outerBoundary); Position ref = outerBoundary.iterator().next(); Position nref = Position.fromDegrees(ref.getLatitude().degrees,
                 * ref.getLongitude().degrees, height); roof.setReferencePosition(nref); roof.setAttributes(ra); renderables.add(roof);
                 */

            } else {

            }

            if (properties != null)
                box.setValue(AVKey.PROPERTIES, properties);

        } else {
            SurfacePolygon poly = new SurfacePolygon(attrs, outerBoundary);
            if (innerBoundaries != null) {
                for (Iterable<? extends Position> iter : innerBoundaries) {
                    poly.addInnerBoundary(iter);
                }
            }

            if (properties != null)
                poly.setValue(AVKey.PROPERTIES, properties);

            renderables.add(poly);
        }
!!!147458.java!!!	fillRenderablePolyline(inout owner : GeoJSONGeometry, inout positions : Iterable<? extends Position>, inout attrs : ShapeAttributes, inout properties : AVList) : void
        if (hasNonzeroAltitude(positions)) {
            Path p = new Path();
            p.setPositions(positions);
            p.setAltitudeMode(WorldWind.ABSOLUTE);
            p.setAttributes(attrs);

            if (properties != null)
                p.setValue(AVKey.PROPERTIES, properties);

            renderables.add(p);
        } else {
            SurfacePolyline sp = new SurfacePolyline(attrs, positions);

            if (properties != null)
                sp.setValue(AVKey.PROPERTIES, properties);

            renderables.add(sp);
        }
!!!147586.java!!!	hasNonzeroAltitude(inout positions : Iterable<? extends Position>) : boolean
        for (Position pos : positions) {
            if (pos.getAltitude() != 0)
                return true;
        }
        return false;
!!!147714.java!!!	dumpAVList(inout av : AVList) : void
        if (av == null)
            return;
        Set<Map.Entry<String, Object>> set = av.getEntries();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> e = it.next();
            System.out.println("" + e.getKey() + "=" + e.getValue().toString() + " " + e.getValue().getClass().getName());
        }
!!!147842.java!!!	fillShapeAttribute(inout sa : ShapeAttributes, inout properties : AVList) : void
        if (properties == null)
            return;
        String v = properties.getStringValue("color");
        if (v == null)
            v = "gray";

        sa.setInteriorMaterial(new Material(stringToColor(v)));
        sa.setOutlineMaterial(Material.GRAY);
        sa.setDrawInterior(true);
        sa.setDrawOutline(false);
        String mat = properties.getStringValue("material");
        if (mat == null)
            mat = "concrete";
        if (mat.equals("glass")) {
            sa.setDrawOutline(true);
        }
        sa.setEnableLighting(true);
        sa.setEnableAntialiasing(true);

        // sa.setOutlineMaterial(new Material(stringToColor(v)));
!!!147970.java!!!	stringToColor(in value : String) : Color
        if (value == null)
            return Color.GRAY;

        // Clean name for FX
        value = value.replace(" ", "").replace("-", "").replace("_", "").toLowerCase();

        // In COLORS not handled by FX ? (yet)
        String hex = COLORS.get(value);
        if (hex != null)
            return Color.decode(hex);

        // Get the color using FX (the most complete set)
        try {
            javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.valueOf(value);
            if (fxColor == null) {
                System.out.println("Color not found:" + value);
                return Color.GRAY;
            }

            return new Color((float) fxColor.getRed(), (float) fxColor.getGreen(), (float) fxColor.getBlue(), (float) fxColor.getOpacity());

        } catch (Exception e) {
            System.out.println("Color not found:" + value);
            return Color.GRAY;
        }
