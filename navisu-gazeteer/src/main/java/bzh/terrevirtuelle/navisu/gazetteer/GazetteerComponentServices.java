/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gazetteer;

import edu.usc.ir.geo.gazetteer.domain.Location;
import java.util.HashMap;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 */
public interface GazetteerComponentServices
        extends ComponentService {

    void buildIndex(String gazetteerPath, String indexerPath, boolean reverseGeocodingEnabled);

    void buildIndex();

    List<Location> searchNearby(Double latitude, Double longitude, Double distanceInMiles, String indexerPath, int count);

    HashMap<String, List<Location>> searchGeoName(String indexerPath, List<String> locationNameEntities, int count);

    HashMap<String, List<Location>> searchGeoName(List<String> locationNameEntities, int count);

    Location searchGeoName(String town, String country);

}
