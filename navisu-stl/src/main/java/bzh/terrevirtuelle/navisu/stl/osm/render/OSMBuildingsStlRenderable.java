/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.osm.render;

import gov.nasa.worldwind.Disposable;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.formats.geojson.GeoJSONDoc;
import gov.nasa.worldwind.formats.geojson.GeoJSONFeature;
import gov.nasa.worldwind.formats.geojson.GeoJSONFeatureCollection;
import gov.nasa.worldwind.formats.geojson.GeoJSONGeometry;
import gov.nasa.worldwind.formats.geojson.GeoJSONGeometryCollection;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiLineString;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiPoint;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiPolygon;
import gov.nasa.worldwind.formats.geojson.GeoJSONObject;
import gov.nasa.worldwind.formats.geojson.GeoJSONPoint;
import gov.nasa.worldwind.formats.geojson.GeoJSONPolygon;
import gov.nasa.worldwind.formats.geojson.GeoJSONPositionArray;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.OSMBuildingsRenderable;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.PreRenderable;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;
import gov.nasa.worldwind.render.SurfacePolyline;
import gov.nasa.worldwind.util.Logging;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The renderable of the GeoJSONDoc for OSM Buildings
 *
 * @author sbodmer
 */
public class OSMBuildingsStlRenderable extends OSMBuildingsRenderable
        implements Renderable, PreRenderable, Disposable {
    private static final Map<String, String> COLORS = new HashMap<String, String>();

    private GeoJSONDoc doc = null;
    /**
     * Default height of buildings
     */
    private double defaultHeight = 10;

    /**
     * The list of renderable for this GeoJSON object (PointPlacemarks, ExtrudePolygon, ...)
     */
    private List<Renderable> renderables = new ArrayList<Renderable>();

    static {
        COLORS.put("lightbrown", "#ac6b25");
        COLORS.put("yellowbrown", "#bb9613");
        COLORS.put("darkbrown", "#654321");
        COLORS.put("redbrown", "#a52a2a");
        COLORS.put("cream", "#ffffcc");
        COLORS.put("brickred", "#cb4154");
        COLORS.put("bluegreen", "#0d98ba");
    }

    /**
     * If the height is 0, then no building are extruded
     *
     * @param doc
     * @param defaultHeight
     */
    public OSMBuildingsStlRenderable(GeoJSONDoc doc, double defaultHeight) {
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
    }

    @Override
    public String toString() {
        return "Contains " + renderables.size() + " elements to render";
    }

    // **************************************************************************
    // *** API
    // **************************************************************************
    @Override
    public void clear() {
        renderables.clear();
    }

    // **************************************************************************
    // *** Renderable
    // **************************************************************************
    @Override
    public void render(DrawContext dc) {
        renderables.forEach((r) -> {
            r.render(dc);
        });
    }

    @Override
    public void preRender(DrawContext dc) {
        renderables.stream().filter((r) -> (r instanceof PreRenderable)).forEachOrdered((r) -> {
            ((PreRenderable) r).preRender(dc);
        });
    }

    // **************************************************************************
    // *** Disposable
    // **************************************************************************
    @Override
    public void dispose() {
        for (Renderable r : renderables) {
            if (r instanceof Disposable)
                ((Disposable) r).dispose();
        }
        renderables.clear();
    }

    // **************************************************************************
    // *** Private
    // **************************************************************************

    /**
     * Create the basic shape for the rendering
     *
     * @param object
     */
    public void prepare(GeoJSONObject object) {
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
    }

    protected void fill(GeoJSONGeometry geom, AVList properties) {
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
    }

    /**
     * Create a PointPlacemark
     *
     * @param properties
     */
    protected void fillRenderablePoint(GeoJSONPoint owner, Position pos, PointPlacemarkAttributes attrs, AVList properties) {
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
    }

    /**
     * Prepare the polygon with color if present, the points are always on the same plane
     * <p>
     * If the polygon has an altitude, do not extrude it
     * <p>
     * <p>
     * If the polygon properties has a height use it for extrude
     * <p>
     * <p>
     * If the the default height > 0 , then extrude the polygon if no other height is found
     * <p>
     *
     * @param owner
     * @param outerBoundary
     * @param innerBoundaries
     * @param attrs
     * @param properties
     */
    protected void fillRenderablePolygon(GeoJSONPolygon owner, 
            Iterable<? extends Position> outerBoundary, 
            Iterable<? extends Position>[] innerBoundaries, 
            ShapeAttributes attrs, 
            AVList properties) {
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
    }

    protected void fillRenderablePolyline(GeoJSONGeometry owner, Iterable<? extends Position> positions, ShapeAttributes attrs, AVList properties) {
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
    }

    /**
     * Check if a position has an altitude (!= 0)
     * <p>
     *
     * @param positions
     *
     * @return
     */
    protected static boolean hasNonzeroAltitude(Iterable<? extends Position> positions) {
        for (Position pos : positions) {
            if (pos.getAltitude() != 0)
                return true;
        }
        return false;
    }

    protected void dumpAVList(AVList av) {
        if (av == null)
            return;
        Set<Map.Entry<String, Object>> set = av.getEntries();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> e = it.next();
            System.out.println("" + e.getKey() + "=" + e.getValue().toString() + " " + e.getValue().getClass().getName());
        }
    }

    /**
     * Will fill the passed shape attribute with the properties
     *
     * <PRE>
     * "color" will be processed (if none found, WHITE is used)
     * </PRE>
     *
     * @param sa
     * @param properties
     */
    private void fillShapeAttribute(ShapeAttributes sa, AVList properties) {
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
    }

    private Color stringToColor(String value) {
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
    }
}
