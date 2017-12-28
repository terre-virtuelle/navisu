/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.kml;

import de.micromata.opengis.kml.v_2_2_0.Container;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * https://labs.micromata.de/projects/jak/quickstart.html
 */
public class App {

    private final String NAVISU_HOME = System.getProperty("user.home") + "/navisu";
    private final String DATA_S57_CATALOG_1 = "data/charts/vector/s57/catalog/ENC_NP1.kmz";
    private final String DATA_S57_CATALOG_2 = "data/charts/vector/s57/catalog/ENC_NP2.kmz";
    private final String DATA_S57_CATALOG_3 = "data/charts/vector/s57/catalog/ENC_NP3.kmz";
    private final String DATA_S57_CATALOG_4 = "data/charts/vector/s57/catalog/ENC_NP4.kmz";
    private final String DATA_S57_CATALOG_5 = "ENC_NP5.kml";
    private final String DATA_S57_CATALOG_6 = "ENC_NP6.kml";

    public App() {
        test();
    }

    public void test() {
        Kml kml = Kml.unmarshal(new File(DATA_S57_CATALOG_5));
        List<Placemark> placemarks = getPlacemarks(kml.getFeature());
        for(Placemark pm : placemarks){
            String fr=pm.getName();
            fr=fr.substring(0, 2);
            if(fr.equals("FR")){
                System.out.println(pm.getName());
            }
        }
    }

    public ArrayList<Placemark> getPlacemarks(Feature root) {
        ArrayList<Placemark> Placemarks = new ArrayList<Placemark>();

        if (root instanceof Container) {
            if (root instanceof Document) {
                for (Feature feature : ((Document) root).getFeature()) {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                }
            } else if (root instanceof Folder) {
                for (Feature feature : ((Folder) root).getFeature()) {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                }
            }
        } else {
            if (root instanceof Placemark) {
                Placemarks.add((Placemark) root);
            }
        }
        return Placemarks;
    }
    public static void main(String[] args) {
        new App();
    }
}
