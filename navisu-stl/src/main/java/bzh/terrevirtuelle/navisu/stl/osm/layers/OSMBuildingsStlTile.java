/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.osm.layers;

import bzh.terrevirtuelle.navisu.stl.osm.render.OSMBuildingsStlRenderable;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.cache.FileStore;
import gov.nasa.worldwind.formats.geojson.GeoJSONDoc;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Earth.OSMBuildingsTile;
import gov.nasa.worldwind.layers.Earth.OSMBuildingsTileListener;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.OSMBuildingsRenderable;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.retrieve.HTTPRetriever;
import gov.nasa.worldwind.retrieve.RetrievalPostProcessor;
import gov.nasa.worldwind.retrieve.Retriever;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Thread to download the gson data
 *
 * @author sbodmer
 */
public class OSMBuildingsStlTile
        extends OSMBuildingsTile
        implements RetrievalPostProcessor, Runnable {

    /**
     * Use the OSMBuildings key specified for WW
     */
    static final String OSMBUILDINGS_URL = "http://[abcd].data.osmbuildings.org/0.2/sx3pxpz6/tile";
    // 15/16942/11632.json"
    /**
     * The counter for the different servers
     */
    static int current = 0;

    int x = 0;
    int y = 0;
    int level = 15;
    double defaultHeight = 10;
    Position center = null;
    FileStore store = null;
    boolean retrieveRemoteData = true;
    long expireDate = 0;
    String cachePath = "";

    /**
     * The loading timestamp
     */
    long ts = 0;

    OSMBuildingsTileListener listener = null;

    /**
     * The loaded buildings
     */
    OSMBuildingsRenderable renderable = null;

    /**
     * The tile bounding box
     */
    Extent bb = null;

    ExtrudedPolygon tile = null;

    public OSMBuildingsStlTile(int level, int x, int y, OSMBuildingsTileListener listener, Position center,
            FileStore store, boolean retrieveRemoteData, long expireDate, double defaultHeight) {
        super(level, x, y, listener, center,store, retrieveRemoteData, expireDate, defaultHeight);
        
    }


    //**************************************************************************
    //*** Runnable
    //**************************************************************************
    /**
     * Load local cached file
     */
    @Override
    public void run() {
        try {
            URL data = store.findFile(cachePath, false);
            //--- Load the data
            GeoJSONDoc doc = new GeoJSONDoc(data);
            doc.parse();
            renderable = new OSMBuildingsRenderable(doc, defaultHeight);
            if (listener != null) {
                listener.osmBuildingsLoaded(this);
            }
        } catch (NullPointerException | IOException ex) {
            //--- File is no more in local storage ?
            if (listener != null) {
                listener.osmBuildingsLoadingFailed(this, ".json file could not be found");
            }
        }
        //--- Failed
        
    }
}
