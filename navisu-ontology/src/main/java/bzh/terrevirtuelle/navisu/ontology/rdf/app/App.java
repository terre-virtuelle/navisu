/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ontology.rdf.app;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.SailingDirections;
import bzh.terrevirtuelle.navisu.ontology.rdf.controller.RdfParser;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * NaVisu
 *
 * @date 26 oct. 2015
 * @author Serge Morvan
 */
public class App {

    String SHIP_NAME = "Lithops";
   // String NAME_RDF = "requeteAvurnav";
    String NAME_RDF = "requetesInstructionsNautiques";
    String RESULT = "result";
    String DIR_SRC = "data/";
    String DIR_TARGET = "data/";

    public App() {
        NavigationDataSet navigationDataSet = new NavigationDataSet(); // Ne pas commenter

        // Les avurnav du shom sont des requetes Sparql, 
        // Instanciation d'un AvurnavSet
        // sauvegarde dans un  NavigationDataSet
        RdfParser rdfParser = new RdfParser();
        navigationDataSet = rdfParser.parse(DIR_SRC, NAME_RDF);

        // Ne pas commenter
        // sauvegarde du NavigationDataSet dans un fic
        try {
            ImportExportXML.exports(navigationDataSet, DIR_SRC + RESULT + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Lecture du fic et instanciation des entitees
        NavigationDataSet nds = null;
        try {
            nds = ImportExportXML.imports(navigationDataSet, DIR_SRC + RESULT + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (nds != null) {
            nds.display();
        }
        String wkt = null;
        Geometry geometry;
        WKTReader wktReader = new WKTReader();
        List<Avurnav> avurnavList = null;
        if (nds != null) {
            avurnavList = nds.get(Avurnav.class);
        }
        if (avurnavList != null) {
            for (Avurnav a : avurnavList) {
                wkt = a.getGeometry();
                try {
                    geometry = wktReader.read(wkt);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        List<SailingDirections> sailingDirectionsList = null;
        if (nds != null) {
            sailingDirectionsList = nds.get(SailingDirections.class);
        }
        if (sailingDirectionsList != null) {
            for (SailingDirections a : sailingDirectionsList) {
                wkt = a.getGeometry();
                try {
                    geometry = wktReader.read(wkt);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
