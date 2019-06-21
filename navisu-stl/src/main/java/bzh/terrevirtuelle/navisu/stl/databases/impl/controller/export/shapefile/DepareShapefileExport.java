/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.shapefile;

import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl.DepareExportToSTL;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPolygon;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Jun 21, 2019
 */
public class DepareShapefileExport {

    protected DisplayServices displayServices;
    protected Shapefile shapefile;
    protected Set<Map.Entry<String, Object>> entries;
    protected double val1 = 0.0;
    protected double val2 = 0.0;
    protected ShapeAttributes normalAttributes;
    protected ShapeAttributes highlightAttributes;
    protected RenderableLayer layer;

    public DepareShapefileExport(DisplayServices displayServices, Shapefile shapefile, RenderableLayer layer) {
        this.displayServices = displayServices;
        this.shapefile = shapefile;
        this.layer = layer;
    }

    public List<Polygon> export() {
        List<Polygon> polyWWJ = new ArrayList<>();

        while (shapefile.hasNext()) {
            try {
                ShapefileRecord record = shapefile.nextRecord();
                List<Position> depare = createDepare(record);
                List<List<Position>> posfiltered = filter(depare);
                posfiltered.stream().map((pf) -> {
                    List<Position> positions1 = new ArrayList<>();
                    pf.stream().map((pos) -> new Position(pos.getLatitude(), pos.getLongitude(), 100.0)).forEachOrdered((pos2) -> {
                        positions1.add(pos2);
                    });
                    return positions1;
                }).map((positions1) -> new Polygon(positions1)).map((poly) -> {
                    if (record.getAttributes() != null) {
                        entries = record.getAttributes().getEntries();
                        entries.stream().filter((e) -> (e != null)).forEachOrdered((e) -> {
                            if (e.getKey().equalsIgnoreCase("drval1")) {
                                val1 = (Double) e.getValue();
                            }
                            if (e.getKey().equalsIgnoreCase("drval2")) {
                                val2 = (Double) e.getValue();
                            }
                        });
                    }
                    poly.setValue("drval1", val1);
                    return poly;
                }).map((poly) -> {
                    poly.setValue("drval2", val2);
                    return poly;
                }).map((poly) -> {
                    String label = val1 + " " + val2;
                    poly.setValue(AVKey.DISPLAY_NAME, label);
                    return poly;
                }).forEachOrdered((poly) -> {
                    polyWWJ.add(poly);
                });
            } catch (Exception ex) {
                Logger.getLogger(DepareExportToSTL.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        Color color = new Color((int) (Math.random() * 255),
                (int) (Math.random() * 255), (int) (Math.random() * 255));
        displayServices.displayPolygons(polyWWJ, layer, Material.GREEN, 1);
        return polyWWJ;
    }

    protected List<Position> createDepare(ShapefileRecord record) {

        List<Position> result = new ArrayList<>();
        SurfacePolygons shape = new SurfacePolygons(
                Sector.fromDegrees(((ShapefileRecordPolygon) record).getBoundingRectangle()),
                record.getCompoundPointBuffer());
        Iterable<? extends LatLon> pos = shape.getLocations();

        for (LatLon l : pos) {
            result.add(new Position(l, 100.0));
        }
        return result;
    }

    public List<List<Position>> filter(List<Position> positions) {
        List<List<Position>> result = new ArrayList<>();
        int k = 0;
        int j = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            result.add(new ArrayList<>());
            if ((positions.get(j).getLatitude().getDegrees() != positions.get(i + 1).getLatitude().getDegrees())
                    && (positions.get(j).getLongitude().getDegrees() != positions.get(i + 1).getLongitude().getDegrees())) {
                result.get(k).add(positions.get(i));
            } else {
                result.get(k).add(positions.get(i + 1));
                k++;
                j = i + 2;
                i += 3;
            }
        }

        List<List<Position>> resultFiltered = new ArrayList<>();
        result.stream().filter((l) -> (l.size() > 3)).map((l) -> {
            List<Position> lp = new ArrayList<>();
            lp.addAll(l);
            return lp;
        }).forEachOrdered((lp) -> {
            resultFiltered.add(lp);
        });

        return resultFiltered;
    }

    protected final void makeAttributes() {
        normalAttributes = new BasicShapeAttributes();
        normalAttributes.setOutlineMaterial(Material.RED);
        normalAttributes.setOutlineOpacity(1.0);
        normalAttributes.setOutlineWidth(1);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setDrawInterior(false);

    }
}
