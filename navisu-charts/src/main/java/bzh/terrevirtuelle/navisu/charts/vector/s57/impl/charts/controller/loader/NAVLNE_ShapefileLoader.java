/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.controller.NavigationLineController;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class NAVLNE_ShapefileLoader
        extends ShapefileLoader
        implements S57ShapeFileLoader {


    private Shapefile shapefile;
    private ShapefileRecord record;
    private final NavigationLineController navigationLineController;
    private RenderableLayer layer;
    private final List< List<LatLon>> coords;
    private final List<LatLon> latLonList;
    private final List<ShapefileRecord> records;

    public NAVLNE_ShapefileLoader() {
        records = new ArrayList<>();
        latLonList = new ArrayList<>();
        coords = new ArrayList<>();
        navigationLineController = new NavigationLineController();
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
    }

    @Override
    public List<Layer> createLayersFromShapefile(Shapefile shp) {
        this.shapefile = shp;
        return super.createLayersFromShapefile(shp);
    }

    @Override
    protected void addRenderablesForPolylines(Shapefile shp, RenderableLayer layer) {
        ShapeAttributes attrs = this.createPolylineAttributes(null);
        this.layer = layer;
        createPolyline(shp, attrs);
    }

    @Override
    protected Renderable createPolyline(Shapefile shp, ShapeAttributes attrs) {
        List<Integer> numberOfPoints = new ArrayList<>();
        while (shp.hasNext()) {
            record = shp.nextRecord();
            records.add(record);
            numberOfPoints.add(record.getNumberOfPoints());
        }

        for (LatLon l : shp.getPointBuffer().getLocations()) {
            latLonList.add(l);
        }
        List<LatLon> tmpList;
        int kk = 0;
        for (int i : numberOfPoints) {
            tmpList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                tmpList.add(latLonList.get(kk));
                kk++;
            }
            coords.add(tmpList);
        }
        navigationLineController.createNavLines(records, coords, layer);
        return null;
    }
}
