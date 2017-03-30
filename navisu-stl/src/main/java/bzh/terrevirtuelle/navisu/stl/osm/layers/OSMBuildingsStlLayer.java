/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.osm.layers;

import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.Message;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Earth.OSMBuildingsLayer;
import gov.nasa.worldwind.layers.Earth.OSMBuildingsTileListener;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ScreenCredit;
import gov.nasa.worldwind.render.ScreenCreditImage;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
import gov.nasa.worldwind.util.Logging;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

/**
 * Always assume a zoom level of 15
 *
 * @author sbodmer
 */
public class OSMBuildingsStlLayer
        extends OSMBuildingsLayer
        implements OSMBuildingsTileListener,
        ActionListener,
        SelectListener {

    public static final String CACHE_FOLDER = "Earth" + File.separatorChar + "OSMBuildings";

    public static final int ZOOM = 15;

    public static final double maxX = Math.pow(2, ZOOM);
    public static final double maxY = Math.pow(2, ZOOM);

    // LatLon center = null;
    // SurfacePolygon carpet = null;
    ExtrudedPolygon box = null;

    /**
     * The last viewport center postion in world coordinates
     */
    Position center = null;

    /**
     * The worldwindow for this layer
     */
    WorldWindow ww = null;

    SurfaceCircle cursor = null;

    /**
     * The key is "{level};{x};{y}"
     */
    HashMap<String, OSMBuildingsStlTile> buildings = new HashMap<String, OSMBuildingsStlTile>();

    File cacheFolder = null;

    int maxTiles = 10;
    double defaultHeight = 10;

    // Cylinder c = null;
    public OSMBuildingsStlLayer() {
        super();

        //--- Prepare the screen credits
        ScreenCredit sc = new ScreenCreditImage("OSM Buildings",
                getClass().getResource("/images/OSMBuildings_32x32.png"));
        sc.setLink("http://www.osmbuildings.org");
        sc.setOpacity(1);
        setScreenCredit(sc);

        setExpiryTime(365L * 24L * 60L * 60L * 1000L);

        // System.out.println("ROOT:"+cacheRoot);
        /*
        List<FileStoreDataSet> dataSets = FileStoreDataSet.getDataSets(cacheRoot);
        for (FileStoreDataSet fileStoreDataSet : dataSets) {
            String cacheName = fileStoreDataSet.getName();
            if (cacheName.contains(sourceName)) {
                fileStoreDataSet.delete(false);
                break;
            }
        }
         */
 /*
        ShapeAttributes a4 = new BasicShapeAttributes();
        a4.setInteriorOpacity(1);
        a4.setEnableLighting(true);
        a4.setOutlineMaterial(Material.RED);
        // a4.setOutlineWidth(2d);
        a4.setDrawInterior(true);
        a4.setDrawOutline(false);
         */
 /*
        c = new Cylinder(new Position(LatLon.fromDegrees(0,0),0), 100, 10);
        c.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        c.setAttributes(a4);
        c.setVisible(true);
        addRenderable(c);
         */
 /*
        ArrayList<Position> poss = new ArrayList<>();
        poss.add(Position.fromDegrees(0, 0));
        poss.add(Position.fromDegrees(oneTileX, 0));
        poss.add(Position.fromDegrees(oneTileX, oneTileY));
        poss.add(Position.fromDegrees(0, oneTileY));
        
        carpet = new SurfacePolygon(a4, poss);
        addRenderable(carpet);
         */
 /*
        ShapeAttributes at = new BasicShapeAttributes();
        at.setInteriorMaterial(Material.WHITE);
        // at.setOutlineOpacity(0.5);
        at.setInteriorOpacity(1);
        // at.setOutlineMaterial(Material.GREEN);
        // at.setOutlineWidth(2);
        // at.setDrawOutline(true);
        at.setDrawInterior(true);
        at.setEnableLighting(true);

        ShapeAttributes cap = new BasicShapeAttributes();
        cap.setInteriorMaterial(Material.GRAY);
        cap.setInteriorOpacity(1);
        cap.setDrawInterior(true);
        cap.setEnableLighting(true);

        ArrayList<Position> poss = new ArrayList<>();
        poss.add(Position.fromDegrees(0, 0));
        poss.add(Position.fromDegrees(0, oneTileY));
        poss.add(Position.fromDegrees(oneTileX, oneTileY));
        poss.add(Position.fromDegrees(oneTileX, 0));

        box = new ExtrudedPolygon(10d);
        box.setAltitudeMode(WorldWind.CONSTANT);
        box.setAttributes(at);
        box.setSideAttributes(at);
        box.setCapAttributes(cap);
        box.setVisible(true);
        box.setOuterBoundary(poss);

        addRenderable(box);
         */
        //--- Prepare the cursor
        ShapeAttributes a1 = new BasicShapeAttributes();
        a1.setInteriorMaterial(Material.GREEN);
        a1.setInteriorOpacity(0.5);
        a1.setEnableLighting(false);
        a1.setOutlineMaterial(Material.BLACK);
        a1.setOutlineWidth(2d);
        a1.setDrawInterior(true);
        a1.setDrawOutline(true);
        cursor = new SurfaceCircle(a1, LatLon.ZERO, 20d);
        cursor.setVisible(true);
        addRenderable(cursor);
    }

    //**************************************************************************
    //*** API
    //*************************************************************************
    public void setDefaultBuildingHeight(double defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public void setMaxTiles(int maxTiles) {
        this.maxTiles = maxTiles;
    }

    public static int lon2x(double lon, int z) {
        return (int) (Math.floor((lon + 180.0) / 360.0 * Math.pow(2.0, z)));
    }

    public static int lat2y(double lat, int z) {
        return (int) (Math.floor((1.0 - Math.log(Math.tan(lat * Math.PI / 180.0) + 1.0 / Math.cos(lat * Math.PI / 180.0)) / Math.PI) / 2.0 * Math.pow(2.0, z)));
    }

    public static double x2lon(int x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180;
    }

    public static double y2lat(int y, int z) {
        double n = Math.PI - 2.0 * Math.PI * y / Math.pow(2.0, z);
        return 180.0 / Math.PI * Math.atan(0.5 * (Math.exp(n) - Math.exp(-n)));
    }

    //**************************************************************************
    //*** AbstractLayer
    //**************************************************************************
    @Override
    public void dispose() {

        super.dispose();
    }

    @Override
    public boolean isLayerInView(DrawContext dc) {
        dc.addScreenCredit(getScreenCredit());

        return true;
    }

    /**
     * Fetch the buldings data for the center of the viewport
     *
     * @param dc
     */
    @Override
    public void doRender(DrawContext dc) {
        center = dc.getViewportCenterPosition();
        //--- Move cursor to center of viewport
        if (center != null) {
            cursor.moveTo(new Position(center, 0));
        }
        super.doRender(dc);
    }

    @Override
    public void doPreRender(DrawContext dc) {
        // System.out.println("doPreRender:" + dc);
        // SectorGeometryList gl = dc.getSurfaceGeometry();
        // Sector s = dc.getVisibleSector();
        // System.out.println("Sector:" + s);
        // LatLon poss[] = s.getCorners();
        // Position center = dc.getViewportCenterPosition();
        // fr.getRight().

        // ArrayList<Position> poss = new ArrayList<>();
        /*
        poss.add(Position.fromDegrees(, 0));
        poss.add(Position.fromDegrees(0, oneTileY));
        poss.add(Position.fromDegrees(oneTileX, oneTileY));
        poss.add(Position.fromDegrees(oneTileX, 0));
         */
        // box.setOuterBoundary(Arrays.asList(s.getCorners()));
        // box.moveTo(new Position(center, 0));
        // Vec4 c = dc.getView().getCenterPoint();
        // System.out.println("CENTER:" + center.getLatitude().degrees + "," + center.getLongitude().degrees);
        // System.out.println("Lat:"+c.getY()+" Lon:"+c.getX()+" Alt:"+c.z);
        /*
        Cylinder c = new Cylinder(Position.fromRadians(center.getLatitude(), oneTileX, oneTileX), maxX, maxX)
        BasicShapeAttributes sa = new BasicShapeAttributes();
        SurfacePolyline sp = new SurfacePolyline(sa, s.asList());
        addRenderable(sp);
         */
        super.doPreRender(dc);
    }

    //**************************************************************************
    //*** MessageListener
    //**************************************************************************
    /**
     * If view stopped,find the osmbuidling tile to fetch at the center of the
     * viewport
     *
     * @param msg
     */
    @Override
    public void onMessage(Message msg) {
        System.out.println("onMessage:" + msg.getName() + " when:" + msg.getWhen() + " source:" + msg.getSource());

        if (View.VIEW_STOPPED.equals(msg.getName()) && (center != null)) {
            if (ww == null) {
                ww = (WorldWindow) msg.getSource();
                // ww.addSelectListener(this);
            }
            /*
            double rx = center.getLongitude().radians;
            double dx = 128 / Math.PI * maxX * (rx + Math.PI);
            int x = (int) dx / 256;

            //--- Rows
            double ry = center.getLatitude().radians;
            double tl = Math.tan(Math.PI / 4d + ry / 2d);
            double dy = 128 / Math.PI * maxY * (Math.PI - Math.log(tl));
            int y = (int) dy / 256;
             */
            int x = lon2x(center.getLongitude().degrees, ZOOM);
            int y = lat2y(center.getLatitude().degrees, ZOOM);
            // System.out.println("X=" + x + ", Y=" + y);

            //--- Take the total of 9 tile
            x--;
            y--;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //--- Check if max tiles are reached, if so, remove the oldest one
                    if (buildings.size() > maxTiles) {
                        Iterator<OSMBuildingsStlTile> it = buildings.values().iterator();
                        OSMBuildingsStlTile oldest = null;
                        while (it.hasNext()) {
                            OSMBuildingsStlTile t = it.next();
                            if (oldest == null) {
                                oldest = t;
                            }
                            if (t.getLastUsed() < oldest.getLastUsed()) {
                                oldest = t;
                            }
                        }
                        Renderable rend = oldest.getRenderable();
                        if (rend != null) {
                            removeRenderable(rend);
                        }
                        removeRenderable(oldest.getTileSurfaceRenderable());
                        buildings.remove(oldest.toString());
                    }

                    String key = (x + i) + "x" + (y + j) + "@" + ZOOM;
                    OSMBuildingsStlTile t = buildings.get(key);
                    if (t == null) {
                        t = new OSMBuildingsStlTile(ZOOM, (x + i), (y + j), this, center, getDataFileStore(),
                                isNetworkRetrievalEnabled(),
                                getExpiryTime(), defaultHeight);
                        buildings.put(key, t);
                        t.fetch();
                    }
                    t.tick();
                }
            }
        }
    }

    
    //**************************************************************************
    //*** ActionListener
    //**************************************************************************
    @Override
    public void actionPerformed(ActionEvent e) {
        //--- Nothing at the moment
    }

    //**************************************************************************
    //*** SelectListener
    //**************************************************************************
    @Override
    public void selected(SelectEvent event) {
        // System.out.println("EVENT:"+event);
        if (event.isLeftDoubleClick()) {
            System.out.println("Double click on " + event.getTopPickedObject());
        }
    }

    //**************************************************************************
    //*** Private
    //**************************************************************************
    public static void main(String args[]) {
        // double lat = 52.20276987984823d;
        double lat = 46.1935;
        double lon = 6.129;
        // double lat = 0;

        int level = 15;
        int maxY = 1 << level;
        int maxX = 1 << level;
        double oneY = 180d / maxY;

        //--- https://en.wikipedia.org/wiki/Web_Mercator
        /*
        System.out.println("MAX:" + maxY);
        System.out.println("ONE:" + oneY);
        int y = (int) ((maxY * (lat + 90d)) / 180d);

        System.out.println("Y  :" + y);
        System.out.println("D  :" + (maxY - y));

        double plat = Math.log(Math.tan((Math.PI/4)+(lat/2)));
        System.out.println("PLAT:"+plat);
         */
        //--- Cols (x)
        double rx = Math.toRadians(lon);
        double x = 128 / Math.PI * maxX * (rx + Math.PI);
        System.out.println("COLS:" + (x / 256));

        //--- Rows
        double ry = Math.toRadians(lat);
        double tl = Math.tan(Math.PI / 4d + ry / 2d);
        double y = 128 / Math.PI * maxY * (Math.PI - Math.log(tl));
        System.out.println("ROWS:" + (y / 256));
    }

}
