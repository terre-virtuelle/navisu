/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import com.vividsolutions.jts.operation.buffer.BufferParameters;
import gov.nasa.worldwind.render.Path;
import java.util.List;
import java.util.Map;

/**
 *
 * @author serge
 */
public class PolylineExport
        implements PolyGeomExport {

    protected TopologyServices topologyServices;
    protected StlComponentServices stlComponentServices;
    protected JTSServices jtsServices;
    protected String acronym;
    protected Path path;
    protected double latMin;
    protected double lonMin;
    protected double latScale;
    protected double lonScale;
    protected double verticalOffset;

    public PolylineExport(TopologyServices topologyServices, StlComponentServices stlComponentServices, JTSServices jtsServices,
            double latMin, double lonMin, double latScale, double lonScale,
            double verticalOffset) {
        this.topologyServices = topologyServices;
        this.stlComponentServices = stlComponentServices;
        this.jtsServices = jtsServices;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.latScale = latScale;
        this.lonScale = lonScale;
        this.verticalOffset = verticalOffset;
    }

    @Override
    public void export(String geometry, Map<String, String> labels) {
        path = topologyServices.wktMultiLineToWwjPath(geometry, verticalOffset);
        //  System.out.println("PolylineExport geometry : " + geometry);
        System.out.println("path : " + path.getPositions());
   //     List<Point3D> pts = stlComponentServices.getBuffer(geometry, 10.0, BufferParameters.CAP_FLAT);

        //BufferParameters.CAP_FLAT
        //  String stl = stlComponentServices.toFacet(path, latMin, lonMin, latScale, lonScale, verticalOffset);
        //  System.out.println("stl : " + stl);
    }

}
