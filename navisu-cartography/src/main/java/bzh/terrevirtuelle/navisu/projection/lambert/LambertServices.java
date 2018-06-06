/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.projection.lambert;

import bzh.terrevirtuelle.navisu.projection.lambert.impl.LambertZone;
import bzh.terrevirtuelle.navisu.projection.lambert.impl.Pt3D;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge from https://github.com/yageek/lambert-java
 * 
 * @date 5 Jun, 2018
 */
public interface LambertServices
        extends ComponentService {

    /*
    *   ALGO0001
     */
    public double latitudeISOFromLat(double lat, double e);

    /*
   *   ALGO0003
     */
    public Pt3D geographicToLambertAlg003(double latitude, double longitude, LambertZone zone, double lonMeridian, double e);

    /*
   *  http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/TransformationsCoordonneesGeodesiques.pdf
   *  3.4 Coordonnées géographiques Lambert
     */
    public Pt3D geographicToLambert(double latitude, double longitude, LambertZone zone, double lonMeridian, double e);

    /*
*	ALGO0004 - Lambert vers geographiques
     */
    public Pt3D lambertToGeographic(Pt3D org, LambertZone zone, double lonMeridian, double e, double eps);

    /*
 * Convert Lambert -> WGS84
 * http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/transfo.pdf
 *
     */
    public Pt3D convertToWGS84(Pt3D org, LambertZone zone);

    /*
 * Convert WGS84 -> Lambert
 * http://geodesie.ign.fr/contenu/fichiers/documentation/pedagogiques/transfo.pdf
 *
     */
    public Pt3D convertToLambert(double latitude, double longitude, LambertZone zone) throws UnsupportedOperationException;

    /*
        Method not really usefull, just to have two ways of doing the same conversion.
     */
    public Pt3D convertToLambertByAlg003(double latitude, double longitude, LambertZone zone) throws UnsupportedOperationException;

    public Pt3D convertToWGS84(double x, double y, LambertZone zone);

    public Pt3D convertToWGS84Deg(double x, double y, LambertZone zone);
}
