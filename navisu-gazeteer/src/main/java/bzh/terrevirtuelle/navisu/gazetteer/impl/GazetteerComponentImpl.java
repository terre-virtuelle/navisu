package bzh.terrevirtuelle.navisu.gazetteer.impl;

import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.GeoNameResolver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponent;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.domain.Location;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

/**
 * User: serge Date: 23/11/2013
 */
public class GazetteerComponentImpl
        implements GazetteerComponent, GazetteerComponentServices,
        ComponentState {

    private GeoNameResolver resolver;
    private final int DEFAULT_COUNT = 10;
 //   private final boolean DEFAULT_RESERVE_GEOCODING_ENABLED = true;
    private String indexerPath;
    private String gazetteerPath;
    private Properties properties;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    private final String INDEXER_PATH = "luceneAllCountriesIndexPath";
    private final String GAZETEER_PATH = "allCountriesPath";

    @Override
    public void componentInitiated() {
        /*
        resolver = new GeoNameResolver();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
            indexerPath = properties.getProperty(INDEXER_PATH).trim();
            gazetteerPath = properties.getProperty(GAZETEER_PATH).trim();
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        if (indexerPath == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Gazetteer");
            alert.setHeaderText("Attention");
            Text s = new Text("  Le chemin de l'index géographique est  incorrect."
                    + "\n  Vous devez compléter le fichier config.properties"
                    + "\n Menu : TOOLS/Config/App/Options");
            s.setWrappingWidth(350);
            alert.getDialogPane().setContent(s);
            alert.show();
            AudioClip plonkSound = new AudioClip(this.getClass().getResource("sounds/warning.mp3").toExternalForm());
            plonkSound.play();

        }
        if (gazetteerPath == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Gazetteer");
            alert.setHeaderText("Attention");
            Text s = new Text("  Le chemin des données de l'index géographique \n est incorrect."
                    + "\n  Vous devez compléter le fichier config.properties"
                    + "\n Menu : TOOLS/Config/App/Options");
            s.setWrappingWidth(350);
            alert.getDialogPane().setContent(s);
            alert.show();
        }
*/
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void buildIndex(String gazetteerPath, String indexerPath, boolean reverseGeocodingEnabled) {
         resolver = new GeoNameResolver();
        this.indexerPath = indexerPath;
        try {
            resolver.buildIndex(gazetteerPath, indexerPath, reverseGeocodingEnabled);
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public void buildIndex() {
        try {
            resolver.buildIndex(gazetteerPath, indexerPath, true);
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    @Override
    public List<Location> searchNearby(Double latitude, Double longitude, Double distanceInMiles, String indexerPath, int count) {
        try {
            return resolver.searchNearby(latitude, longitude, distanceInMiles, indexerPath, count);
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return null;
    }

    @Override
    public HashMap<String, List<Location>> searchGeoName(String indexerPath,
            List<String> locationNameEntities, int count) {
        try {
            return resolver.searchGeoName(indexerPath, locationNameEntities, count);
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return null;
    }

    @Override
    public HashMap<String, List<Location>> searchGeoName(List<String> locationNameEntities, int count) {
        try {
            return resolver.searchGeoName(this.indexerPath, locationNameEntities, count);
        } catch (IOException ex) {
            Logger.getLogger(GazetteerComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return null;
    }

    @Override
    public Location searchGeoName(String town, String countryCode) {
        resolver = new GeoNameResolver();
        try {
            HashMap<String, List<Location>> locationMap;
            List<String> locationNameEntities = new ArrayList<>();
            locationNameEntities.add(town);
            if (resolver != null) {
                locationMap = searchGeoName(resolver.getIndexerPath(), locationNameEntities, DEFAULT_COUNT);
                for (Map.Entry<String, List<Location>> s : locationMap.entrySet()) {
                    List<Location> locations = s.getValue();
                    for (Location l : locations) {
                        if (l.getCountryCode().equals(countryCode)) {
                            return l;
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }
        return null;
    }

}
