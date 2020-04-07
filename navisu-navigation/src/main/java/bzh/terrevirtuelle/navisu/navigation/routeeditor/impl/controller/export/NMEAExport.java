/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.export;

import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteEditorController;
import gov.nasa.worldwind.geom.Position;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/*
$GPGGA,170426.00,4826.26672,N,00429.85937,W,1,04,2.27,94.9,M,50.7,M,,*7D
$GPGLL,4826.26672,N,00429.85937,W,170426.00,A,A*7D
$GPGSA,A,3,26,31,21,29,,,,,,,,,19.21,2.27,19.08*00
$GPGSV,3,1,09,05,09,052,,09,03,339,,14,02,219,21,16,27,292,20*7D
$GPRMC,170426.00,A,4826.26672,N,00429.85937,W,0.193,,060320,,,A*68
$GPVTG,,T,,M,0.193,N,0.358,K,A*26
 */
/**
 *
 * @author serge
 * @date Mar 6, 2020
 */
public class NMEAExport {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmmssSS");
    private GeodeticCurve geoCurve;
    private GeodeticCalculator geoCalc;
    private Ellipsoid reference;
    private final String DATA_DIR = "privateData/nmea/";

    public NMEAExport(GeodeticCurve geoCurve, GeodeticCalculator geoCalc, Ellipsoid reference) {
        this.geoCurve = geoCurve;
        this.geoCalc = geoCalc;
        this.reference = reference;
    }

    public void export(List<Position> positions, float speed, String routeName) {

        List<GlobalCoordinates> globalCoordinates = new ArrayList<>();
        List<String> nmeaSentences = new ArrayList<>();
        GlobalCoordinates start;
        GlobalCoordinates end;
        String we;
        String ns;
        String sentence;
        double latitude;
        double longitude;
        String strLatitude;
        String strLongitude;
        double minLatitude;
        double minLongitude;
        int degLatitude;
        int degLongitude;
        int si = positions.size() - 1;
        int minute = 0;
        int second = 0;
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now(Clock.systemUTC());
        for (int k = 0; k < si; k++) {

            globalCoordinates.clear();
            Position startPos = positions.get(k);
            start = new GlobalCoordinates(startPos.getLatitude().getDegrees(), startPos.getLongitude().getDegrees());
            Position endPos = positions.get(k + 1);
            end = new GlobalCoordinates(endPos.getLatitude().getDegrees(), endPos.getLongitude().getDegrees());

            geoCurve = geoCalc.calculateGeodeticCurve(reference, start, end);
            double ellipseMeters = geoCurve.getEllipsoidalDistance();
            double i = 2 * ellipseMeters / speed;

            i = 1 / i;
            i *= 8000;//5000
            for (double j = 0; j < ellipseMeters; j += i) {
                globalCoordinates.add(geoCalc.calculateEndingGlobalCoordinates(reference, start, geoCurve.getAzimuth(), j));
            }

            for (GlobalCoordinates gc : globalCoordinates) {

                latitude = gc.getLatitude();
                longitude = gc.getLongitude();
                we = longitude > 0 ? "E" : "W";
                ns = latitude > 0 ? "N" : "S";
                if (we.equals("W")) {
                    longitude = -longitude;
                }
                if (ns.equals("S")) {
                    latitude = -latitude;
                }
                degLatitude = (int) latitude;
                degLongitude = (int) longitude;
                minLatitude = latitude - degLatitude;
                minLongitude = longitude - degLongitude;
                minLatitude *= 60;
                minLongitude *= 60;
                strLatitude = Integer.toString(degLatitude) + String.format(Locale.US, "%.4f", minLatitude);
                strLongitude = Integer.toString(degLongitude) + String.format(Locale.US, "%.4f", minLongitude);
                String t = localDateTime.plusMinutes(minute).plusSeconds(second).format(timeFormatter) + ",";
                // System.out.println("t : " + t);
                //  speed *= Math.random();
                sentence = "$GPRMC,"
                        + t
                        + "A,"
                        + strLatitude + ","
                        + ns + ","
                        + strLongitude + ","
                        + we + ","
                        + String.format(Locale.US, "%.2f", speed) + ","
                        + String.format(Locale.US, "%.2f", geoCurve.getAzimuth()) + ","
                        + localDate.format(dateFormatter) + ",,"
                        + "*";
                nmeaSentences.add(sentence + getChecksum(sentence));
                minute += 1;
                second += 1;
            }

        }
        Path path = Paths.get(DATA_DIR + routeName + ".nmea");
        try {
            Files.write(path, nmeaSentences, Charset.defaultCharset());
        } catch (IOException ex) {
            Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

    }

    public void simpleExport(List<Position> positions, float speed, String routeName) {
        List<GlobalCoordinates> globalCoordinates = new ArrayList<>();
        List<String> nmeaSentences = new ArrayList<>();
        GlobalCoordinates start;
        GlobalCoordinates end;
        String we;
        String ns;
        String sentence;
        double latitude;
        double longitude;
        String strLatitude;
        String strLongitude;
        String strLatitudeMin;
        String strLongitudeMin;
        double minLatitude;
        double minLongitude;
        int degLatitude;
        int degLongitude;
        int si = positions.size() - 1;
        int minute = 0;
        int second = 0;
        double azimuth = 0;
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now(Clock.systemUTC());
        for (int k = 0; k < positions.size(); k++) {
            latitude = positions.get(k).getLatitude().getDegrees();
            longitude = positions.get(k).getLongitude().getDegrees();
            if (k < positions.size() - 2) {
                globalCoordinates.clear();
                Position startPos = positions.get(k);
                start = new GlobalCoordinates(startPos.getLatitude().getDegrees(), startPos.getLongitude().getDegrees());
                Position endPos = positions.get(k + 1);
                end = new GlobalCoordinates(endPos.getLatitude().getDegrees(), endPos.getLongitude().getDegrees());

                geoCurve = geoCalc.calculateGeodeticCurve(reference, start, end);
                azimuth = geoCurve.getAzimuth();
            }
            we = longitude > 0 ? "E" : "W";
            ns = latitude > 0 ? "N" : "S";
            if (we.equals("W")) {
                longitude = -longitude;
            }
            if (ns.equals("S")) {
                latitude = -latitude;
            }
          
            degLatitude = (int) latitude;
            degLongitude = (int) longitude;
            minLatitude = latitude - degLatitude;
            minLongitude = longitude - degLongitude;
            minLatitude *= 60;
            minLongitude *= 60;

            DecimalFormat formatter = new java.text.DecimalFormat("00.##");
            strLatitudeMin = formatter.format(minLatitude);
            strLongitudeMin = formatter.format(minLongitude);

            strLatitude = Integer.toString(degLatitude) + strLatitudeMin;
            strLongitude = Integer.toString(degLongitude) + strLongitudeMin;;
            String t = localDateTime.plusMinutes(minute).plusSeconds(second).format(timeFormatter) + ",";
            sentence = "$GPRMC,"
                    + t
                    + "A,"
                    + strLatitude + ","
                    + ns + ","
                    + strLongitude + ","
                    + we + ","
                    + String.format(Locale.US, "%.2f", speed) + ","
                    + String.format(Locale.US, "%.2f", azimuth) + ","
                    + localDate.format(dateFormatter) + ",,"
                    + "*";
            nmeaSentences.add(sentence + getChecksum(sentence));
            minute += 1;
            second += 1;
        }
        Path path = Paths.get(DATA_DIR + routeName + ".nmea");
        try {
            Files.write(path, nmeaSentences, Charset.defaultCharset());
        } catch (IOException ex) {
            Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private String getChecksum(String in) {
        int checksum = 0;
        if (in.startsWith("$")) {
            in = in.substring(1, in.length());
        }

        int end = in.indexOf('*');
        if (end == -1) {
            end = in.length();
        }
        for (int i = 0; i < end; i++) {
            checksum = checksum ^ in.charAt(i);
        }
        String hex = Integer.toHexString(checksum);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        return hex.toUpperCase();
    }

    /**
     * Get the value of reference
     *
     * @return the value of reference
     */
    public Ellipsoid getReference() {
        return reference;
    }

    /**
     * Set the value of reference
     *
     * @param reference new value of reference
     */
    public void setReference(Ellipsoid reference) {
        this.reference = reference;
    }

    /**
     * Get the value of geoCalc
     *
     * @return the value of geoCalc
     */
    public GeodeticCalculator getGeoCalc() {
        return geoCalc;
    }

    /**
     * Set the value of geoCalc
     *
     * @param geoCalc new value of geoCalc
     */
    public void setGeoCalc(GeodeticCalculator geoCalc) {
        this.geoCalc = geoCalc;
    }

    /**
     * Get the value of geoCurve
     *
     * @return the value of geoCurve
     */
    public GeodeticCurve getGeoCurve() {
        return geoCurve;
    }

    /**
     * Set the value of geoCurve
     *
     * @param geoCurve new value of geoCurve
     */
    public void setGeoCurve(GeodeticCurve geoCurve) {
        this.geoCurve = geoCurve;
    }

}
