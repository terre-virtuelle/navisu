/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.svg.SVGPath3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.shapefile.DepareShapefileExport;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.TopologyException;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polygon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author serge
 * @date Jun 5, 2019
 */
public class DepareExportToSVG
        extends DepareShapefileExport {

    protected static final String SEP = File.separator;

    protected GeodesyServices geodesyServices;
    protected TopologyServices topologyServices;

    protected final String filenameRoot;
    protected final String filename;
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
    protected int id = 0;

    //Laser IMT 450x300;
    public DepareExportToSVG(GeodesyServices geodesyServices,
            TopologyServices topologyServices,
            Shapefile shapefile,
            String filenameRoot, String filename,
            double latMin, double lonMin,
            double sideX, double sideY,
            double scaleLat, double scaleLon,
            RenderableLayer layer) {
        super(shapefile, layer);
        this.geodesyServices = geodesyServices;
        this.topologyServices = topologyServices;
        this.filenameRoot = filenameRoot;
        this.filename = filename;
        this.latMin = latMin;
        this.lonMin = lonMin;
        this.sideX = sideX;
        this.sideY = sideY;
        this.scaleLat = scaleLat;
        this.scaleLon = scaleLon;
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
            svgPath3D.setId(Integer.toString(id));
            id++;
            /* Insert self format JTS for test contain */
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

    public void write(Map<Double, List<SVGPath3D>> svgMap) {
        List<SVGPath3D> shapeList = new ArrayList<>();
        Collection<List<SVGPath3D>> collShapesList = svgMap.values();
        collShapesList.forEach((l) -> {
            shapeList.addAll(l);
        });

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        String dateStr = formater.format(new Date());

        for (SVGPath3D svg : shapeList) {
            String svgContent = svg.getContent();
            svgContent = svgContent.replace("z", "");
            svgContent = svgContent.trim();
            String height = Integer.toString((int) svg.getHeight());
            String path = filenameRoot + SEP + filename + "_" + height + "_" + svg.getId();
            String ext = "";
            if (svg.getSvgOnTopList().isEmpty()) {
                ext = ".svg";
            } else {
                ext = "_.svg";
            }
            if (!svg.getBuoyageList().isEmpty()) {
                ext = "-.svg";
            }
            if (!svg.getSvgOnTopList().isEmpty() && !svg.getBuoyageList().isEmpty()) {
                ext = "+_.svg";
            }
            path += ext;
            String comment = "<!-- \n"
                    + filename + "_" + (int) svg.getHeight() + "_" + svg.getId() + ".svg  \n"
                    + "Created with NaVisu, BBT project (http://www.navisu.org/) \n"
                    + dateStr + "\n";
            String others = "";
            String buoyages = "";
            String content = "--> \n"
                    + "<svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                    + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"
                    + "\n"
                    + "<path d=\"" + svgContent + "\"\n"
                    + "style=\"stroke:#000000; fill:none; stroke-width: 1px;\"/> \n";

            for (SVGPath3D svg1 : svg.getSvgOnTopList()) {
                String svgContent1 = svg1.getContent();
                svgContent1 = svgContent1.replace("z", "");
                svgContent1 = svgContent1.trim();
                content += "<path d=\"" + svgContent1 + "\"\n"
                        + "style=\"stroke:#0000FF; stroke-width: 1px; stroke-dasharray:1, 5\""
                        //  + " opacity:0.5 \"/> "
                        + "/> \n";
                height = Integer.toString((int) svg1.getHeight());
                others += filename + "_" + height + "_" + svg1.getId() + ".svg \n";
            }
            for (Buoyage b : svg.getBuoyageList()) {
                Pair<Double, Double> latLon = scaling(b.getLatitude(), b.getLongitude(),
                        latMin, lonMin, sideY, scaleLat, scaleLon);
                String svgContent2 = "<circle cx=\"" + latLon.getX() + "\" cy=\"" + latLon.getY() + "\" r=\"4\" \n"
                        + " style=\"stroke:#FF0000;  stroke-width: 1px;\""
                        + "/> \n";
                content += svgContent2;

                String label = b.getLabel();
                label = label.replace("\n", ", ");
                buoyages += label + "\n";
            }

            content += "</svg>";
            comment += others;
            comment += buoyages;
            content = comment + content;
            try {
                Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        writeAll(svgMap);
        Map<Double, List<SVGPath3D>> svgMapEstran = new HashMap<>();
        List<SVGPath3D> listEstran = new ArrayList<>();
        svgMapEstran.put(-9.0, listEstran);
        for (SVGPath3D svg : shapeList) {
            if (svg.getHeight() == -9.0) {
                listEstran.add(svg);
            }
        }
        writeDepares(svgMapEstran);
    }

    public void writeAll(Map<Double, List<SVGPath3D>> svgMap) {
        List<SVGPath3D> shapeList = new ArrayList<>();
        Collection<List<SVGPath3D>> collShapesList = svgMap.values();
        collShapesList.forEach((l) -> {
            shapeList.addAll(l);
        });

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        String dateStr = formater.format(new Date());
        String path = filenameRoot + SEP + filename + "_" + "all.svg";
        String content = "<!-- \n"
                + filename + "_" + "all.svg  \n"
                + "Created with NaVisu, BBT project (http://www.navisu.org/) \n"
                + dateStr + "\n"
                + "--> \n"
                + "<svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"
                + "\n";
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        for (SVGPath3D svg : shapeList) {
            String svgContent = svg.getContent();
            svgContent = svgContent.replace("z", "");
            svgContent = svgContent.trim();
            content = "<path d=\"" + svgContent + "\"\n"
                    + "style=\"stroke:#000000; fill:none; stroke-width: 1px;\"/> \n";
            for (SVGPath3D svg1 : svg.getSvgOnTopList()) {
                String svgContent1 = svg1.getContent();
                svgContent1 = svgContent1.replace("z", "");
                svgContent1 = svgContent1.trim();
                content += "<path d=\"" + svgContent1 + "\"\n"
                        + "style=\"stroke:#0000FF; fill:none; stroke-width: 1px; stroke-dasharray:1, 3\""
                        //  + " opacity:0.5 \"/> "
                        + "/> \n";
            }
            for (Buoyage b : svg.getBuoyageList()) {
                Pair<Double, Double> latLon = scaling(b.getLatitude(), b.getLongitude(),
                        latMin, lonMin, sideY, scaleLat, scaleLon);
                String svgContent2 = "<circle cx=\"" + latLon.getX() + "\" cy=\"" + latLon.getY() + "\" r=\"4\" \n"
                        + " style=\"stroke:#FF0000; fill:none; stroke-width: 1px;\""
                        + "/> \n";
                content += svgContent2;
            }
            try {
                Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        content = "\n </svg>";
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void writeDepares(Map<Double, List<SVGPath3D>> svgMap) {
        List<SVGPath3D> shapeList = new ArrayList<>();
        Collection<List<SVGPath3D>> collShapesList = svgMap.values();
        collShapesList.forEach((l) -> {
            shapeList.addAll(l);
        });

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        String dateStr = formater.format(new Date());
        String path = filenameRoot + SEP + filename + "_" + "dep.svg";
        String content = "<!-- \n"
                + filename + "_" + "dep.svg  \n"
                + "Created with NaVisu, BBT project (http://www.navisu.org/) \n"
                + dateStr + "\n"
                + "--> \n"
                + "<svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"
                + "\n";
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        for (SVGPath3D svg : shapeList) {
            String svgContent = svg.getContent();
            svgContent = svgContent.replace("z", "");
            svgContent = svgContent.trim();
            content = "<path d=\"" + svgContent + "\"\n"
                    + "style=\"stroke:#000000; fill:none; stroke-width: 1px;\"/> \n";
            try {
                Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        content = "\n </svg>";
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    protected SVGPath3D createJtsGeometry(Polygon p, SVGPath3D svgPath3D) {
        Geometry geom = topologyServices.wwjPolygonToJtsGeometry(p);
        svgPath3D.setGeometry(geom);
        return svgPath3D;
    }

    protected List<Position> scaling(List<Position> positions,
            double latMin, double lonMin,
            double sideY, double latScale, double lonScale) {
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

    @SuppressWarnings("unchecked")
    protected Pair<Double, Double> scaling(double lat, double lon, double latMin, double lonMin, double sideY, double latScale, double lonScale) {
        double latM = geodesyServices.getDistanceM(latMin, lonMin, lat, lonMin);
        double lonM = geodesyServices.getDistanceM(latMin, lonMin, latMin, lon);
        latM *= latScale;
        lonM *= lonScale;
        return new Pair(lonM, sideY - latM);
    }

    protected String createContent(List<Position> scaledPosition) {
        String result;

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

        for (int i = 0; i < keyList.size(); i++) {
            for (SVGPath3D svg : svgMap.get(keyList.get(i))) {
                for (int j = 0; j < keyList.size(); j++) {
                    if (j != i) {
                        for (SVGPath3D svg1 : svgMap.get(keyList.get(j))) {
                            try {
                                if (svg.getGeometry().contains(svg1.getGeometry())) {
                                    svg.getSvgOnTopList().add(svg1);
                                }
                            } catch (TopologyException e) {

                            }
                        }
                    }
                }
            }
        }
        return svgMap;
    }

    public List<SVGPath3D> addBuoyage(List<SVGPath3D> shapeSVGList, List<Buoyage> buoyages) {
        shapeSVGList.forEach((svg) -> {
            buoyages.forEach((b) -> {
                GeometryFactory geometryFactory = new GeometryFactory();
                Coordinate coordinates = new Coordinate(b.getLongitude(), b.getLatitude());
                Point point = geometryFactory.createPoint(coordinates);
                if (svg.getGeometry().contains(point)) {
                    svg.addBuoyage(b);
                }
            });
        });

        return shapeSVGList;
    }
}
