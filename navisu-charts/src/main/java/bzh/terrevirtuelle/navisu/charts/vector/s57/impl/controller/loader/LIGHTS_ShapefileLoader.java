/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.LightView;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.attributes.COLOUR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Light;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class LIGHTS_ShapefileLoader
        extends ShapefileLoader {

    private final List<Light> dataList;

    private Set<Map.Entry<String, Object>> entries;
    private Light data;
    private AirspaceLayer airspaceLayer;

    public LIGHTS_ShapefileLoader() {
        dataList = new ArrayList<>();
        airspaceLayer = new AirspaceLayer();
    }

    @Override
    protected PointPlacemarkAttributes createPointAttributes(ShapefileRecord record) {
        PointPlacemarkAttributes normalAttributes = new PointPlacemarkAttributes();
        return normalAttributes;
    }

    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {

        entries = record.getAttributes().getEntries();
        data = new Light();
        //System.out.println("entries " + entries);
        dataList.add(data);
        data.setLat(latDegrees);
        data.setLon(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("RCID")) {
                data.setId((Long) e.getValue());
            } else {
                if (e.getKey().equals("COLOUR")) {
                    data.setColour((String) e.getValue());
                } else {
                    if (e.getKey().equals("SIGPER")) {
                        if (e.getValue() != null) {
                            data.setSignalPeriod(e.getValue().toString());
                        }
                    } else {
                        if (e.getKey().equals("VALNMR")) {
                            if (e.getValue() != null) {
                                data.setValueOfNominalRange(e.getValue().toString());
                            }
                        } else {
                            if (e.getKey().equals("HEIGHT")) {
                                if (e.getValue() != null) {
                                    data.setHeight(e.getValue().toString());
                                }
                            } else {
                                if (e.getKey().equals("SECTR1")) {
                                    if (e.getValue() != null) {
                                        data.setSectorLimitOne(e.getValue().toString());
                                    }
                                } else {
                                    if (e.getKey().equals("SECTR2")) {
                                        if (e.getValue() != null) {
                                            data.setSectorLimitTwo(e.getValue().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        LightView partCyl = new LightView(data);

        float range = 1;
        if (data.getValueOfNominalRange() != null) {
            range = new Float(data.getValueOfNominalRange());
            range *= 0.1;
        }
        if (data.getSectorLimitOne() != null && data.getSectorLimitTwo() != null) {
            partCyl.setCenter(new LatLon(Angle.fromDegrees(latDegrees), Angle.fromDegrees(lonDegrees)));

            partCyl.setRadii(140.0 * range, 150.0 * range);
            if (data.getHeight() != null) {
                partCyl.setAltitude(new Double(data.getHeight()));
            } else {
                partCyl.setAltitude(25.0);
            }

            partCyl.setAzimuths(Angle.fromDegrees(new Float(data.getSectorLimitOne()) + 180),
                    Angle.fromDegrees(new Float(data.getSectorLimitTwo()) + 180));
            String label = "Lat : " + Double.toString(latDegrees)
                    + " Lon : " + Double.toString(lonDegrees) + "\n"
                    + "Color : " + COLOUR.ATT.get(data.getColour()) + "\n"
                    + "Period : " + data.getSignalPeriod() + "\n"
                    + "Height : " + data.getHeight() + "\n"
                    + "Nominal range : " + data.getValueOfNominalRange() + "\n"
                    + "Sect1 : " + data.getSectorLimitOne() + "\n"
                    + "Sect2 : " + data.getSectorLimitTwo() + "\n";
            partCyl.setValue(AVKey.DISPLAY_NAME, label);
            partCyl.getAttributes().setDrawOutline(true);
            // Si la couleur est blanche, la vue est jaune
            if (data.getColour().contains("1")) {
                partCyl.getAttributes().setMaterial(new Material(COLOUR.ATT.get("6")));
                partCyl.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get("6")));
            } else {
                partCyl.getAttributes().setMaterial(new Material(COLOUR.ATT.get(data.getColour())));
                partCyl.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get(data.getColour())));
            }
            airspaceLayer.addAirspace(partCyl);

        } else {
            partCyl.setCenter(new LatLon(Angle.fromDegrees(latDegrees), Angle.fromDegrees(lonDegrees)));
            if (!data.getColour().contains("1")) {
                partCyl.setRadii(100.0 * range, 110.0 * range);
            } else {
                partCyl.setRadii(140.0 * range, 150.0 * range);
            }
            if (data.getHeight() != null) {
                partCyl.setAltitude(new Double(data.getHeight()));
            } else {
                partCyl.setAltitude(25.0);
            }

            partCyl.setAzimuths(Angle.fromDegrees(0),
                    Angle.fromDegrees(360));
            String label = "Lat : " + Double.toString(latDegrees)
                    + " Lon : " + Double.toString(lonDegrees) + "\n"
                    + "Color : " + COLOUR.ATT.get(data.getColour()) + "\n"
                    + "Period : " + data.getSignalPeriod() + "\n"
                    + "Height : " + data.getHeight() + "\n"
                    + "Nominal range : " + data.getValueOfNominalRange() + "\n";

            partCyl.setValue(AVKey.DISPLAY_NAME, label);
            partCyl.getAttributes().setDrawOutline(true);
            // Si la couleur est blanche, la vue est jaune
            if (data.getColour().contains("1")) {
                partCyl.getAttributes().setMaterial(new Material(COLOUR.ATT.get("6")));
                partCyl.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get("6")));
            } else {
                partCyl.getAttributes().setMaterial(new Material(COLOUR.ATT.get(data.getColour())));
                partCyl.getAttributes().setOutlineMaterial(new Material(COLOUR.ATT.get(data.getColour())));

            }
            airspaceLayer.addAirspace(partCyl);
        }
        
        return partCyl;
    }

    public List<Light> getLights() {
        return dataList;
    }

    public AirspaceLayer getAirspaceLayer() {
        return airspaceLayer;
    }

}
