/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl;

import bzh.terrevirtuelle.navisu.cartography.projection.lambert.Lambert;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import static bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl.LambertZone.Lambert93;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.StrictMath.log;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge from https://github.com/yageek/lambert-java
 *
 * @date 5 Jun, 2018
 */
public class LambertImpl
        implements Lambert, LambertServices, ComponentState {

    /*
https://github.com/yageek/lambert-java
https://bintray.com/yageek/maven/lambert-java/view/files/net/yageek/lambert/lambert-java/1.1

Online samples :
http://geofree.fr/gf/coordinateConv.asp#listSys

--------------------------------------------------------------------------------------
Install cs2cs on Ubuntu :
http://www.sarasafavi.com/installing-gdalogr-on-ubuntu.html

--------------------------------------------------------------------------------------
http://cs2cs.mygeodata.eu/
Conversion From Lambert Zone II to WGS 84 :
$>cs2cs +proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=0 +k_0=0.99987742 +x_0=600000 +y_0=2200000 +a=6378249.2 +b=6356515 +towgs84=-168,-60,320,0,0,0,0 +pm=paris +units=m +no_defs +to +proj=longlat +datum=WGS84 +no_defs -f "%.11f" <<EOF
> 618115 2430676
> EOF

2.58331732871	48.87414278182 43.05512374267

--------------------------------------------------------------------------------------
Conversion From WGS 84 To Lambert Zone II:
$>cs2cs +proj=longlat +datum=WGS84 +no_defs +to +proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=0 +k_0=0.99987742 +x_0=600000 +y_0=2200000 +a=6378249.2 +b=6356515 +towgs84=-168,-60,320,0,0,0,0 +pm=paris +units=m +no_defs  -f "%.11f" <<EOF
2.58331732871 48.8741427818
EOF
618115.00035284588	2430676.00004872493 -43.05512374081
     */
 /*
Documentations :
http://geodesie.ign.fr/contenu/fichiers/documentation/algorithmes/notice/NTG_71.pdf
http://geodesie.ign.fr/contenu/fichiers/documentation/algorithmes/notice/NTG_80.pdf
http:/
     */
    public final double M_PI_2 = Math.PI / 2.0;
    public final double DEFAULT_EPS = 1e-10;
    public final double E_CLARK_IGN = 0.08248325676;
    public final double E_WGS84 = 0.08181919106;

    public final double A_CLARK_IGN = 6378249.2;
    public final double A_WGS84 = 6378137.0;
    public final double LON_MERID_PARIS = 0;
    public final double LON_MERID_GREENWICH = 0.04079234433;
    public final double LON_MERID_IERS = 3.0 * Math.PI / 180.0;

    protected int ncols = 0;
    protected int nrows = 0;
    protected double xllcorners = 0.0;
    protected double yllcorners = 0.0;
    protected double cellsize = 0.0;
    protected double nodataValue = 0;
    protected List<Double> parameters = new ArrayList<>();
    protected List<Pt3D> pts = new ArrayList<>();

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    /*
     * ALGO0001
     */
    @Override
    public double latitudeISOFromLat(double lat, double e) {
        double elt11 = Math.PI / 4d;
        double elt12 = lat / 2d;
        double elt1 = tan(elt11 + elt12);

        double elt21 = e * sin(lat);
        double elt2 = pow((1 - elt21) / (1 + elt21), e / 2d);

        return log(elt1 * elt2);
    }

    /*
    *   ALGO0002
     */
    private double latitudeFromLatitudeISO(double latISo, double e, double eps) {

        double phi0 = 2 * atan(exp(latISo)) - M_PI_2;
        double phiI = 2 * atan(pow((1 + e * sin(phi0)) / (1 - e * sin(phi0)), e / 2d) * exp(latISo)) - M_PI_2;
        double delta = abs(phiI - phi0);

        while (delta > eps) {
            phi0 = phiI;
            phiI = 2 * atan(pow((1 + e * sin(phi0)) / (1 - e * sin(phi0)), e / 2d) * exp(latISo)) - M_PI_2;
            delta = abs(phiI - phi0);
        }

        return phiI;
    }

    @Override
    public Pt3D geographicToLambertAlg003(double latitude, double longitude, LambertZone zone, double lonMeridian, double e) {
        double n = zone.n();
        double C = zone.c();
        double xs = zone.xs();
        double ys = zone.ys();

        double latIso = latitudeISOFromLat(latitude, e);

        double eLatIso = exp(-n * latIso);

        double nLon = n * (longitude - lonMeridian);

        double x = xs + C * eLatIso * sin(nLon);
        double y = ys - C * eLatIso * cos(nLon);

        return new Pt3D(x, y, 0);
    }

    /*
     *  http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/TransformationsCoordonneesGeodesiques.pdf
     *  3.4 Coordonnées géographiques Lambert
     */
    @Override
    public Pt3D geographicToLambert(double latitude, double longitude, LambertZone zone, double lonMeridian, double e) {
        double n = zone.n();
        double C = zone.c();
        double xs = zone.xs();
        double ys = zone.ys();

        double sinLat = sin(latitude);
        double eSinLat = (e * sinLat);
        double elt1 = (1 + sinLat) / (1 - sinLat);
        double elt2 = (1 + eSinLat) / (1 - eSinLat);

        double latIso = (1 / 2d) * log(elt1) - (e / 2d) * log(elt2);

        double R = C * exp(-(n * latIso));

        double LAMBDA = n * (longitude - lonMeridian);

        double x = xs + (R * sin(LAMBDA));
        double y = ys - (R * cos(LAMBDA));

        return new Pt3D(x, y, 0);
    }

    /*
     *	ALGO0004 - Lambert vers geographiques
     */
    @Override
    public Pt3D lambertToGeographic(Pt3D org, LambertZone zone, double lonMeridian, double e, double eps) {
        double n = zone.n();
        double C = zone.c();
        double xs = zone.xs();
        double ys = zone.ys();

        double x = org.getX();
        double y = org.getY();

        double lon, gamma, R, latIso;

        R = sqrt((x - xs) * (x - xs) + (y - ys) * (y - ys));

        gamma = atan((x - xs) / (ys - y));

        lon = lonMeridian + gamma / n;

        latIso = -1 / n * log(abs(R / C));

        double lat = latitudeFromLatitudeISO(latIso, e, eps);

        return new Pt3D(lon, lat, 0);
    }

    /*
     * ALGO0021 - Calcul de la grande Normale
     *
     */
    private double lambertNormal(double lat, double a, double e) {
        return a / sqrt(1 - e * e * sin(lat) * sin(lat));
    }

    /*
     * ALGO0009 - Transformations geographiques -> cartésiennes
     *
     */
    private Pt3D geographicToCartesian(double lon, double lat, double he, double a, double e) {
        double N = lambertNormal(lat, a, e);

        Pt3D pt = new Pt3D(0, 0, 0);

        pt.setX((N + he) * cos(lat) * cos(lon));
        pt.setY((N + he) * cos(lat) * sin(lon));
        pt.setZ((N * (1 - e * e) + he) * sin(lat));

        return pt;
    }

    /*
     * ALGO0012 - Passage des coordonnées cartésiennes aux coordonnées géographiques
     */
    private Pt3D cartesianToGeographic(Pt3D org, double meridien, double a, double e, double eps) {
        double x = org.getX(), y = org.getY(), z = org.getZ();

        double lon = meridien + atan(y / x);

        double module = sqrt(x * x + y * y);

        double phi0 = atan(z / (module * (1 - (a * e * e) / sqrt(x * x + y * y + z * z))));
        double phiI = atan(z / module / (1 - a * e * e * cos(phi0) / (module * sqrt(1 - e * e * sin(phi0) * sin(phi0)))));
        double delta = abs(phiI - phi0);
        while (delta > eps) {
            phi0 = phiI;
            phiI = atan(z / module / (1 - a * e * e * cos(phi0) / (module * sqrt(1 - e * e * sin(phi0) * sin(phi0)))));
            delta = abs(phiI - phi0);

        }

        double he = module / cos(phiI) - a / sqrt(1 - e * e * sin(phiI) * sin(phiI));

        return new Pt3D(lon, phiI, he);
    }

    /*
     * Convert Lambert -> WGS84
     * http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/transfo.pdf
     *
     */
    @Override
    public Pt3D convertToWGS84(Pt3D org, LambertZone zone) {
        if (zone == Lambert93) {
            return lambertToGeographic(org, Lambert93, LON_MERID_IERS, E_WGS84, DEFAULT_EPS);
        } else {
            Pt3D pt1 = lambertToGeographic(org, zone, LON_MERID_PARIS, E_CLARK_IGN, DEFAULT_EPS);

            Pt3D pt2 = geographicToCartesian(pt1.getX(), pt1.getY(), pt1.getZ(), A_CLARK_IGN, E_CLARK_IGN);
            pt2.translate(-168, -60, 320);

            //WGS84 refers to greenwich
            return cartesianToGeographic(pt2, LON_MERID_GREENWICH, A_WGS84, E_WGS84, DEFAULT_EPS);
        }
    }

    /*
     * Convert WGS84 -> Lambert
     * http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/transfo.pdf
     *
     */
    @Override
    public Pt3D convertToLambert(double latitude, double longitude, LambertZone zone) throws UnsupportedOperationException {
        if (zone == Lambert93) {
            throw new UnsupportedOperationException();
        } else {
            Pt3D pt1 = geographicToCartesian(longitude - LON_MERID_GREENWICH, latitude, 0, A_WGS84, E_WGS84);
            pt1.translate(168, 60, -320);
            Pt3D pt2 = cartesianToGeographic(pt1, LON_MERID_PARIS, A_WGS84, E_WGS84, DEFAULT_EPS);

            return geographicToLambert(pt2.getY(), pt2.getX(), zone, LON_MERID_PARIS, E_WGS84);
        }
    }

    /*
     *  Method not really usefull, just to have two ways of doing the same conversion.
     */
    @Override
    public Pt3D convertToLambertByAlg003(double latitude, double longitude, LambertZone zone) throws UnsupportedOperationException {
        if (zone == Lambert93) {
            throw new UnsupportedOperationException();
        } else {
            Pt3D pt1 = geographicToCartesian(longitude - LON_MERID_GREENWICH, latitude, 0, A_WGS84, E_WGS84);

            pt1.translate(168, 60, -320);

            Pt3D pt2 = cartesianToGeographic(pt1, LON_MERID_PARIS, A_WGS84, E_WGS84, DEFAULT_EPS);

            return geographicToLambertAlg003(pt2.getY(), pt2.getX(), zone, LON_MERID_PARIS, E_WGS84);
        }
    }

    @Override
    public Pt3D convertToWGS84(double x, double y, LambertZone zone) {
        Pt3D pt = new Pt3D(x, y, 0);
        return convertToWGS84(pt, zone);
    }

    @Override
    public Pt3D convertToWGS84Deg(double x, double y, LambertZone zone) {
        Pt3D pt = new Pt3D(x, y, 0);
        return convertToWGS84(pt, zone).toDegree();
    }

    public static void main(String[] args) {
        LambertImpl lambert = new LambertImpl();
        Pt3D pt = lambert.convertToWGS84Deg(1029705.083, 272723.849, Lambert93);
    }

    @Override
    public void readLambertDirWriteWGS84File(String orgDir, String target) {
        work(orgDir, target);
    }

    public void work(String dir, String filename) {
        File directory = new File(dir);
        String[] fileInThisDir = directory.list();
        for (String name : fileInThisDir) {
            if (name.endsWith(".asc")) {
                readAndCompute(dir + "/" + name);
                writeFile(filename);
            }
        }
    }

    public final void readAndCompute(String filename) {
        System.out.println("Compute filename : " + filename);
        try {
            parameters.clear();
            Files.lines(Paths.get(filename)).limit(6).forEach(this::init);
            init1();
            pts.clear();
            Files.lines(Paths.get(filename)).skip(6).forEach(this::compute);
        } catch (IOException ex) {
            Logger.getLogger(LambertImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void init(String s) {

        String[] tab = s.trim().split("\\s+");
        parameters.add(Double.valueOf(tab[1]));
        //  System.out.println("parameters : " + parameters);
    }

    public void init1() {
        double tmp = parameters.get(0);
        ncols = (int) tmp;
        tmp = parameters.get(1);
        nrows = (int) tmp;
        xllcorners = parameters.get(2);
        yllcorners = parameters.get(3) + 75000;
        cellsize = parameters.get(4);
        nodataValue = parameters.get(5);
    }

    public void compute(String s) {
        String[] tab = s.trim().split("\\s+");

        for (int i = 0; i < ncols; i++) {
            Pt3D pt = convertToWGS84Deg(xllcorners + i * cellsize, yllcorners, Lambert93);
            pt.setZ(Double.valueOf(tab[i]));
            pts.add(pt);
        }
        yllcorners -= cellsize;
    }

    public void writeFile(String filename) {
        FileWriter writer;
        try {
            writer = new FileWriter(filename, true);
            for (Pt3D p : pts) {
                writer.write(p.toString());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LambertImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
