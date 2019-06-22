/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg;

import bzh.terrevirtuelle.navisu.domain.svg.SVGPath3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.shapefile.DepareShapefileExport;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 *
 * @author serge
 * @date Jun 5, 2019
 */
public class DepareExportToSVG
        extends DepareShapefileExport {
    
    protected GeodesyServices geodesyServices;
    protected TopologyServices topologyServices;
    
    protected final String filenameRoot;
    protected double latMin;
    protected double lonMin;
    protected final double scaleLat;
    protected final double scaleLon;
    protected final double sideX;
    protected final double sideY;
    protected final String resultG = null;
    protected List<Position> positions;
    protected List<Path> pathList = new ArrayList<>();
    protected double maxMax = Double.MIN_VALUE;
    protected boolean first = true;

    //Laser IMT 450x300;
    public DepareExportToSVG(GeodesyServices geodesyServices,
            DisplayServices displayServices,
            TopologyServices topologyServices,
            Shapefile shapefile, String filenameRoot,
            double latMin, double lonMin,
            double sideX, double sideY,
            double scaleLat, double scaleLon,
            RenderableLayer layer) {
        super(displayServices, shapefile, layer);
        this.geodesyServices = geodesyServices;
        this.topologyServices = topologyServices;
        this.filenameRoot = filenameRoot;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.sideX = sideX;
        this.sideY = sideY;
        this.scaleLat = scaleLat;
        this.scaleLon = scaleLon;
        
        makeAttributes();
    }
    
    public List<SVGPath3D> createSVGPath(List<Polygon> pathList) {
        List<SVGPath3D> result = new ArrayList<>();
        for (Polygon p : pathList) {
            SVGPath3D svgPath3D = new SVGPath3D();
            svgPath3D.getValues().put("drval1", (Double) p.getValue("drval1"));
            svgPath3D.getValues().put("drval2", (Double) p.getValue("drval2"));
            svgPath3D.setHeight((Double) (p.getValue("drval1")));
            svgPath3D.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
            svgPath3D.setStrokeWidth(1);
            svgPath3D.setFill(null);
            svgPath3D = createJtsGeometry(p, svgPath3D);
            result.add(svgPath3D);
            Iterable<? extends LatLon> iPostions = p.getOuterBoundary();
            List<Position> posList = new ArrayList<>();
            for (LatLon pp : iPostions) {
                Position p1 = new Position(pp.getLatitude(), pp.getLongitude(), 100);
                posList.add(p1);
            }
            List<Position> scaledPosition = scaling(posList, latMin, lonMin, sideY, scaleLat, scaleLon);
            String content = createContent(scaledPosition);
            svgPath3D.setContent(content);
        }
        
        return result;
    }
    
    protected SVGPath3D createJtsGeometry(Polygon p, SVGPath3D svgPath3D) {
        Geometry geom = topologyServices.wwjPolygonToJtsGeometry(p);
        svgPath3D.setGeometry(geom);
        return svgPath3D;
    }
    
    protected List<Position> scaling(List<Position> positions, double latMin, double lonMin, double sideY, double latScale, double lonScale) {
        List<Position> resultPos = new ArrayList<>();
        positions.forEach((position) -> {
            double lon = position.getLongitude().getDegrees();
            double lat = position.getLatitude().getDegrees();
            double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
            double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
            latM *= latScale;
            lonM *= lonScale;
            //Zero SVG is on the top
            resultPos.add(new Position(Angle.fromDegrees(lonM), Angle.fromDegrees(sideY - latM), position.getElevation()));
        });
        return resultPos;
    }
    
    protected String createContent(List<Position> scaledPosition) {
        String result = "";
        
        result = "M"
                + +(int) scaledPosition.get(0).getLatitude().getDegrees() + ","
                + (int) scaledPosition.get(0).getLongitude().getDegrees() + " ";
        result += "L";
        for (int i = 1; i < scaledPosition.size(); i++) {
            result
                    += +(int) scaledPosition.get(i).getLatitude().getDegrees() + ","
                    + (int) scaledPosition.get(i).getLongitude().getDegrees() + " ";
        }
        result += "z";
        return result;
    }
    
    public Map<Double, List<SVGPath3D>> processOnTop(Map<Double, List<SVGPath3D>> svgMap) {
        List<Double> keyList = new ArrayList<>();
        keyList.addAll(svgMap.keySet());
        keyList.sort(Comparator.reverseOrder());
        
        for (int i = 0; i < keyList.size() - 1; i++) {
            
        }
        return svgMap;
    }
}
