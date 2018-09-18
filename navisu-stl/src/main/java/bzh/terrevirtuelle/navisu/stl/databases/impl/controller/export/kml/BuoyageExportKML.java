/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.kml;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE_INV;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date 6/7/2018
 *
 */
public class BuoyageExportKML {

    String filename;
    String model;
    String sep = File.separator;
    protected RenderableLayer layer;
    protected List<Buoyage> buoyages;
    static int id = 0;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;
    protected double elv;

    public BuoyageExportKML(String filename) {
        this.filename = filename;
    }

    public void export(List<Buoyage> buoyages, double elevation) {
        java.nio.file.Path path = Paths.get(filename);
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportKML.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        body = body.replace("</Document>", "");
        body = body.replace("</kml>", "");
        String buoys = "";
        for (Buoyage buoyage : buoyages) {
            acronym = BUOYAGE_INV.ATT.get(buoyage.getClass().getSimpleName());
            lat = buoyage.getLatitude();
            lon = buoyage.getLongitude();

            String imageAddress = "";
            if (acronym.equals("LNDMRK")) {
                elv = Double.valueOf(buoyage.getElevation());
                buoys = buoys.concat(insertedFile(lat, lon, elv, "Babord-blender.dae"));
                imageAddress = "img/landmarks_" + buoyage.getMarsys() + "/"
                        + acronym + "_"
                        + buoyage.getCategoryOfMark() + "_"
                        + buoyage.getConspicuousVisually() + "_"
                        + ((Landmark) buoyage).getFunction() + "_"
                        + buoyage.getColour() + "_"
                        + buoyage.getColourPattern() + "_"
                        + buoyage.getMarsys()
                        + ".png";
            } else {
                if (acronym.equals("DAYMAR")) {
                    imageAddress = "img/daymarks_"
                            + buoyage.getMarsys() + "/"
                            + acronym + "_"
                            + buoyage.getShape() + "_"
                            + buoyage.getCategoryOfMark() + "_"
                            + buoyage.getColour() + "_"
                            + buoyage.getColourPattern() + "_"
                            + buoyage.getNatureOfConstruction() + "_"
                            + buoyage.getMarsys()
                            + ".png";
                } else {
                    if (acronym.equals("BCNLAT") || acronym.equals("BOYLAT")) {
                        if (buoyage.getMarsys().equals("1")) {
                            if (buoyage.getCategoryOfMark().equals("1")) {
                                model = "latRed.dae";
                            }
                            if (buoyage.getCategoryOfMark().equals("2")) {
                                model = "latGreen.dae";
                            }
                        }
                        if (buoyage.getMarsys().equals("2")) {
                            if (buoyage.getCategoryOfMark().equals("1")) {
                                model = "latGreen.dae";
                            }
                            if (buoyage.getCategoryOfMark().equals("2")) {
                                model = "latRed.dae";
                            }
                        }
                        buoys = buoys.concat(insertedFile(lat, lon, elevation, model));
                    }
                    if (acronym.equals("BCNCAR") || acronym.equals("BOYCAR")) {
                        if (buoyage.getCategoryOfMark().equals("1")) {
                            model = "north.dae";
                        }
                        if (buoyage.getCategoryOfMark().equals("2")) {
                            model = "east.dae";
                        }
                        if (buoyage.getCategoryOfMark().equals("3")) {
                            model = "south.dae";
                        }
                        if (buoyage.getCategoryOfMark().equals("4")) {
                            model = "west.dae";
                        }
                        buoys = buoys.concat(insertedFile(lat, lon, elevation, model));
                    }
                }
            }
        }

        body = body.concat(buoys);
        body = body.concat("</Document>\n </kml>\n");
        try {
            Files.write(path, body.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(BuoyageExportKML.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String insertedFile(double latitude, double longitude, double altitude, String model) {
        String dataDir = System.getProperty("user.dir");
        String result = "<Placemark>\n"
                + "<name>Buoyage</name>\n"
                + "<description>NaVisu</description>\n"
                + "<Model id=\"model_" + id + "\">\n"
                + "<altitudeMode>relativeToGround</altitudeMode>\n"
                + "<Location>\n"
                + "<longitude>" + longitude + "</longitude>\n"
                + "<latitude>" + latitude + "</latitude>\n"
                + "<altitude>" + altitude + "</altitude>\n"
                + "</Location>\n"
                + "<Orientation>\n"
                + "<heading>0</heading>\n"
                + "<tilt>0</tilt>\n"
                + "<roll>0</roll>\n"
                + "</Orientation>\n"
                + "<Scale>\n"
                + "<x>25</x>\n"
                + "<y>25</y>\n"
                + "<z>25</z>\n"
                + "</Scale>\n"
                + "<Link>\n"
              //  + "<href>" + dataDir + sep + "data" + sep + "collada" + sep + "buoys" + sep + model + "</href>"
                + "<href>" +"buoys" + sep + model + "</href>"
                + "</Link>\n"
                + "</Model>\n"
                + "</Placemark>\n";
        id++;
        return result;
    }
}
