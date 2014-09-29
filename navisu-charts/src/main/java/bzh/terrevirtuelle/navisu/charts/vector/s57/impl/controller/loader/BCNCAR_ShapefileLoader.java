/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.attributes.CATCAM;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.BeaconCardinal;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class BCNCAR_ShapefileLoader
        extends ShapefileLoader {

    private final List<BeaconCardinal> objects;
    PointPlacemarkAttributes attrs;
    private Set<Entry<String, Object>> entries;
    private BeaconCardinal beacon;

    public BCNCAR_ShapefileLoader() {
        objects = new ArrayList<>();
    }

    @Override
    protected void addRenderablesForPoints(Shapefile shp, RenderableLayer layer) {

        while (shp.hasNext()) {
            ShapefileRecord record = shp.nextRecord();

            if (!Shapefile.isPointType(record.getShapeType())) {
                continue;
            }
            attrs = this.createPointAttributes(record);
            double[] point = ((ShapefileRecordPoint) record).getPoint();
            layer.addRenderable(this.createPoint(record, point[1], point[0], attrs));
        }
    }

    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
        return normalAttributes;
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        //   attrs = new PointPlacemarkAttributes();
        objects.add(beacon);
        beacon = new BeaconCardinal();
        entries = record.getAttributes().getEntries();
       // System.out.println("entries BeaconCardinal : " + entries);
        beacon.setLat(latDegrees);
        beacon.setLon(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("RCID")) {
                beacon.setId((Long) e.getValue());
            } else {
                if (e.getKey().equals("BCNSHP")) {
                    beacon.setBeaconShape(((Long) e.getValue()).toString());
                } else {
                    if (e.getKey().equals("CATCAM")) {
                        beacon.setCategoryOfCardinalMark(((Long) e.getValue()).toString());
                    } else {
                        if (e.getKey().equals("OBJNAM")) {
                            beacon.setObjectName((String) e.getValue());
                        } else {
                            if (e.getKey().equals("COLOUR")) {
                                beacon.setColour((String) e.getValue());
                            } else {
                                if (e.getKey().equals("COLPAT")) {
                                    beacon.setColourPattern((String) e.getValue());
                                } else {
                                    if (e.getKey().equals("STATUS")) {
                                        beacon.setStatus((String) e.getValue());
                                    } else {
                                        if (e.getKey().equals("PICREP")) {
                                            beacon.setPictorialRepresentation((String) e.getValue());
                                        } else {
                                            if (e.getKey().equals("DATEND")) {
                                                beacon.setDateEnd((String) e.getValue());
                                            } else {
                                                if (e.getKey().equals("DATSTA")) {
                                                    beacon.setDateStart((String) e.getValue());
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        });

        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setLabelText(beacon.getObjectName());
        String label = "Beacon, cardinal " + CATCAM.ATT.get(beacon.getCategoryOfCardinalMark()) + "\n"
                + "Lat : " + new Float(beacon.getLat()).toString() + "\n "
                + "Lon : " + new Float(beacon.getLon()).toString();
        placemark.setValue(AVKey.DISPLAY_NAME, label);
        if (beacon.getCategoryOfCardinalMark().equals("1")) {
            attrs.setImageAddress("img/beacon/cardinalPillarNorth.png");
        } else {
            if (beacon.getCategoryOfCardinalMark().equals("2")) {
                attrs.setImageAddress("img/beacon/cardinalPillarEast.png");
            } else {
                if (beacon.getCategoryOfCardinalMark().equals("3")) {
                    attrs.setImageAddress("img/beacon/cardinalSparSouth.png");
                } else {
                    if (beacon.getCategoryOfCardinalMark().equals("4")) {
                        attrs.setImageAddress("img/beacon/cardinalPillarWest.png");
                    }
                }
            }
        }

       // attrs.setScale(1.0);
attrs.setScale(0.8);
        placemark.setAttributes(attrs);
        return placemark;
    }

    public List<BeaconCardinal> getBeacons() {
        return objects;
    }

}
