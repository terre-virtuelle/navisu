/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;

/**
 *
 * @author serge
 */
public class S57ObjectsExport {

    protected PolyGeomExport polyGeomExport;
    protected TopologyServices topologyServices;
    protected JTSServices jtsServices;
    protected StlComponentServices stlComponentServices;
    protected double latMin;
    protected double lonMin;
    protected double latScale;
    protected double lonScale;
    protected double verticalOffset;

    public S57ObjectsExport(TopologyServices topologyServices, StlComponentServices stlComponentServices, JTSServices jtsServices,
            double latMin, double lonMin, double latScale, double lonScale, double verticalOffset) {
        this.topologyServices = topologyServices;
        this.stlComponentServices = stlComponentServices;
        this.jtsServices = jtsServices;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latScale = latScale;
        this.lonScale = lonScale;
        this.verticalOffset = verticalOffset;
    }

    public void export(Geo object) {
        String geometry = object.getGeom();
        // System.out.println("geometry : " + geometry);
        /*
        if ((geometry.contains("LINESTRING") && !geometry.contains("MULTILINESTRING")) && !geometry.contains("EMPTY")) {
            polyView = new LineView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }
         */
        if (geometry.contains("MULTILINESTRING") && !geometry.contains("EMPTY")) {
            // polyView = new PolylineView(acronym, topologyServices, layer);
            // polyView.export(geometry);
            polyGeomExport = new PolylineExport(topologyServices, stlComponentServices, jtsServices,
                    latMin, lonMin, latScale, lonScale, verticalOffset);
            polyGeomExport.export(geometry, object.getLabels());
        }
        /*
        if (geometry.contains("POLYGON") && !geometry.contains("MULTIPOLYGON") && !geometry.contains("EMPTY")) {
            polyView = new PolygonView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }
        if (geometry.contains("MULTIPOLYGON") && !geometry.contains("EMPTY")) {
            polyView = new MultiPolygonView(acronym, topologyServices, layer);
            polyView.display(geometry, normAttributes, highlightAttributes, object.getLabels());
        }
         */
    }
}
