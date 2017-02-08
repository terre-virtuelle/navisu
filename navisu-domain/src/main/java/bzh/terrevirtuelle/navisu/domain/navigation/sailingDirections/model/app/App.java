/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.app;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.shom.ShomSailingDirectionsParser;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Publication;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.SdShomCatalog;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.ShomSailingDirections;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
public class App {

    private final String filename = "data/in/d21.xml";

    public App() {
        SailingDirections sailingDirections = new ShomSailingDirections(filename);

        Document document = sailingDirections.getDocument();

        Publication publication = document.getMetadata().getPublication();

        System.out.println(publication.getStitle());
        System.out.println(publication.getCountry());
        System.out.println(publication.getRegion());
        System.out.println(publication.getNumbering().getSerie());
        System.out.println(publication.getNumbering().getVolume());
        System.out.println(publication.getUpdate().getGan());
        System.out.println(document.getBook().getChapters());

        System.out.println(sailingDirections.getPoiMap());
        
        
        SdShomCatalog sdShomCatalog = new SdShomCatalog();
        
        try {
            sdShomCatalog = ImportExportXML.imports(sdShomCatalog, "data/in/instructionsNautiquesShom.xml");
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sdShomCatalog);
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (Exception e) {
            System.out.println("App e : " + e);
        }
    }
}
