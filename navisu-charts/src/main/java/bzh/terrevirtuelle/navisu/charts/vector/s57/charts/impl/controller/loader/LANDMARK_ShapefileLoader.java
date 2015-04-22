 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.CATLMK;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecordPoint;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class LANDMARK_ShapefileLoader
        extends LayerShapefileLoader {

    private Landmark object;
    private final List<Landmark> objects;
    private PointPlacemarkAttributes attrs;
    private Set<Entry<String, Object>> entries;
    private Class claz;
    private final String acronym;
    private final String marsys;private boolean dev=false;
    

    public LANDMARK_ShapefileLoader(String marsys, String acronym) {
        this.marsys = marsys;
        this.acronym = acronym;

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
        object = new Landmark();
        objects.add(object);
        entries = record.getAttributes().getEntries();
        object.setLat(latDegrees);
        object.setLon(lonDegrees);
        entries.stream().forEach((e) -> {
            if (e.getKey().equals("RCID")) {
                object.setId((Long) e.getValue());
            }

            if (e.getKey().equals("CATLMK")) {
                if (e.getValue() != null) {
                    object.setCategoryOfMark((String) e.getValue());
                } else {
                    object.setCategoryOfMark("0");
                }
            }
            if (e.getKey().equals("OBJNAM")) {
                if (e.getValue() != null) {
                    object.setObjectName((String) e.getValue());
                } else {
                    object.setObjectName(" ");
                }
            }
            if (e.getKey().equals("COLOUR")) {
                if (e.getValue() != null) {
                    object.setColour((String) e.getValue());
                } else {
                    object.setColour("0");
                }
            }
            if (e.getKey().equals("COLPAT")) {
                Object obj = e.getValue();
                String colpat = "0";
                if (obj != null) {
                    colpat = (String) e.getValue();
                }
                object.setColourPattern(colpat);
            }
            if (e.getKey().equals("STATUS")) {
                object.setStatus((String) e.getValue());
            }
            if (e.getKey().equals("PICREP")) {
                object.setPictorialRepresentation((String) e.getValue());
            }
            if (e.getKey().equals("DATEND")) {
                object.setDateEnd((String) e.getValue());
            }
            if (e.getKey().equals("DATSTA")) {
                object.setDateStart((String) e.getValue());
            }
            if (e.getKey().equals("CONVIS")) {
                if (e.getValue() != null) {
                    object.setConspicuousVisually(((Long) e.getValue()).toString());
                } else {
                    object.setConspicuousVisually("0");
                }
            }
            if (e.getKey().equals("FUNCTN")) {
                if (e.getValue() != null) {
                    object.setFunction((String) e.getValue());
                } else {
                    object.setFunction("0");
                }
            }
        });

        PointPlacemark placemark = new PointPlacemark(Position.fromDegrees(latDegrees, lonDegrees, 0));
        placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        placemark.setLabelText(object.getObjectName());

        String catMark = "";
        if (acronym.contains("CATLMK")) {
            catMark = CATLMK.ATT.get(object.getCategoryOfMark());
        }
        /*
         String label = claz.getSimpleName() + " "
         + catMark + "\n"
         + (object.getObjectName() != null ? object.getObjectName() : "") + "\n"
         + "Lat : " + new Float(object.getLat()).toString() + "\n "
         + "Lon : " + new Float(object.getLon()).toString();

         placemark.setValue(AVKey.DISPLAY_NAME, label);
         */

        /*
         System.out.println("img/buoyage/" + acronym + "_"
         + object.getShape() + "_"
         + object.getCategoryOfMark() + "_"
         + object.getColour() + "_"
         + object.getColourPattern() + "_"
         + tm
         + "_" + marsys
         + ".png  "   + object.getObjectName() );
         */
        /*attrs.setImageAddress("img/buoyage/" + acronym + "_"
                + object.getShape() + "_"
                + object.getCategoryOfMark() + "_"
                + object.getColour() + "_"
                + object.getColourPattern() + "_"
                + "_" + marsys
                + ".png");
        attrs.setImageOffset(Offset.BOTTOM_CENTER);
        attrs.setScale(0.8);
        placemark.setAttributes(attrs);*/
        attrs.setImageAddress("img/landmarks_" + marsys + "/" + acronym + "_"
                + object.getCategoryOfMark() + "_"
                + object.getConspicuousVisually() + "_"
                + object.getFunction() + "_"
                + object.getColour() + "_"
                + object.getColourPattern() + "_"
                + marsys
                + ".png");
        attrs.setImageOffset(Offset.BOTTOM_CENTER);
        attrs.setScale(0.6);//0.8
        placemark.setAttributes(attrs);
        String label = acronym + "_"
                + object.getCategoryOfMark() + "_"
                + object.getConspicuousVisually() + "_"
                + object.getFunction() + "_"
                + object.getColour() + "_"
                + object.getColourPattern() + "_"
                + marsys
                + ".png";
        //System.out.println("label : " + label);
        placemark.setValue(AVKey.DISPLAY_NAME, label);

        return placemark;
    }

    public List<Landmark> getLandmarks() {
        return objects;
    }

}
