/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.controller;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.CATNAV;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.NavigationLine;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Serge Morvan
 * @date 4 sept. 2014 NaVisu project
 */
public class NavigationLineController {

    protected WorldWindow wwd;
    private NavigationLine navigationLine;
    private final List<NavigationLine> navigationLines;
    private final GlobeAnnotation tooltipAnnotation;
    private Path path;

    public NavigationLineController() {
        wwd = GeoWorldWindViewImpl.getWW();
        navigationLines = new ArrayList<>();
        this.tooltipAnnotation = new GlobeAnnotation("", Position.fromDegrees(0, 0, 0));
        // this.tooltipAnnotation.getAttributes().setFont(Font.decode("Arial-Plain-12"));
        this.tooltipAnnotation.getAttributes().setSize(new Dimension(320, 180));
        this.tooltipAnnotation.getAttributes().setDistanceMinScale(1);
        this.tooltipAnnotation.getAttributes().setDistanceMaxScale(1);
        this.tooltipAnnotation.getAttributes().setVisible(false);
        this.tooltipAnnotation.setAlwaysOnTop(true);
        wwd.addSelectListener((SelectEvent event) -> {
            if (event.getEventAction().equals(SelectEvent.ROLLOVER)) {
                Object o = event.getTopObject();
                if (o != null) {
                    if (o.getClass() == Path.class) {
                        highlight((Path) o);
                    } else {
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                tooltipAnnotation.getAttributes().setVisible(false);
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 1000);
                    }
                }
            }
        });
    }

    /**
     * Get the value of navigationLine
     *
     * @return the value of navigationLine
     */
    public NavigationLine getNavigationLine() {
        return navigationLine;
    }

    /**
     * Set the value of navigationLine
     *
     * @param navigationLine new value of navigationLine
     */
    public void setNavigationLine(NavigationLine navigationLine) {
        this.navigationLine = navigationLine;
    }

    public void createNavLines(List<ShapefileRecord> records, List< List<LatLon>> coords, RenderableLayer layer) {

        layer.addRenderable(tooltipAnnotation);
        ShapeAttributes shapeAttributes0 = new BasicShapeAttributes();
        shapeAttributes0.setOutlineMaterial(Material.GRAY);
        shapeAttributes0.setOutlineWidth(2d);
        ShapeAttributes shapeAttributes1 = new BasicShapeAttributes();
        shapeAttributes1.setOutlineStipplePattern((short) 0xAAAA);
        shapeAttributes1.setOutlineStippleFactor(5);
        shapeAttributes1.setOutlineMaterial(Material.GRAY);
        shapeAttributes1.setOutlineWidth(2d);

        int i = 0;
        ArrayList<Position> pathPositions;
        ShapefileRecord record;

        for (List<LatLon> l : coords) {
            navigationLine = new NavigationLine();
            navigationLines.add(navigationLine);
            record = records.get(i);
            record.getAttributes().getEntries().stream().forEach((e) -> {
                if (e.getKey().equals("NINFOM")) {
                    if (e.getValue() != null) {
                        navigationLine.setInformationInNationalLanguage(e.getValue().toString());
                    }
                }
                if (e.getKey().equals("INFORM")) {
                    if (e.getValue() != null) {
                        String tmp = e.getValue().toString();
                        if (tmp.contains("at")) {
                            navigationLine.setInformation(tmp.substring(0, tmp.length() - 1) + "°");
                        } else {
                            navigationLine.setInformation(tmp);
                        }
                    }
                }
                if (e.getKey().equals("CATNAV")) {
                    if (e.getValue() != null) {
                        navigationLine.setCategoryOfNavigationLine(CATNAV.ATT.get(e.getValue().toString()));
                    }
                }
                if (e.getKey().equals("ORIENT")) {
                    if (e.getValue() != null) {
                        navigationLine.setOrientation(e.getValue().toString() + "°");
                    }
                }
            });

            pathPositions = new ArrayList<>();
            pathPositions.add(new Position(l.get(l.size() - 1), 2.0));
            pathPositions.add(new Position(l.get(l.size() - 2), 2.0));
            path = new Path(pathPositions);
            path.setVisible(true);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            path.setPathType(AVKey.GREAT_CIRCLE);
            path.setAttributes(shapeAttributes0);
            path.setValue("NAVLNE", navigationLine);
            layer.addRenderable(path);
            i++;
        }
        i = 0;
        for (List<LatLon> l : coords) {
            pathPositions = new ArrayList<>();
            pathPositions.add(new Position(l.get(0), 2.0));
            pathPositions.add(new Position(l.get(1), 2.0));
            path = new Path(pathPositions);
            path.setVisible(true);
            path.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            path.setPathType(AVKey.GREAT_CIRCLE);
            path.setAttributes(shapeAttributes1);
            path.setValue("NAVLNE", navigationLines.get(i));
            layer.addRenderable(path);
            i++;
        }
    }

    private void highlight(Path path) {
        navigationLine = (NavigationLine) path.getValue("NAVLNE");
        // ogr ne semble pas bien gérer les accents, seulle la version anglaise est affichée
        String str = "NAVIGATION LINE (NAVLNE)\n\n"
                + "CATNAV : " + navigationLine.getCategoryOfNavigationLine() + "\n"
                + "ORIENT : " + navigationLine.getOrientation() + "\n"
                + "INFORM : " + navigationLine.getInformation();// + "\n"
        // + "NINFOM : " + navigationLine.getInformationInNationalLanguage();
        this.tooltipAnnotation.setText(str);
        List<Position> pos = (List<Position>) path.getPositions();
        tooltipAnnotation.setPosition(Position.interpolate(0.5, pos.get(0), pos.get(1)));
        this.tooltipAnnotation.getAttributes().setVisible(true);
    }
}
