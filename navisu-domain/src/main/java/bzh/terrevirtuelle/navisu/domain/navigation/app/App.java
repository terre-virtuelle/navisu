/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.app;

import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.rss.AvurnavRSS;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.rss.Rss;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.AvurnavSet;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.File;
import java.io.FileNotFoundException;
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
    String NAME_RDF = "requeteRdf";
    String NAME_RSS = "rssatlantique-fr";
    String TEST = "test";
    String DIR_SRC = "data/";
    String DIR_TARGET = "data/";

    public App() {
        NavigationDataSet navigationDataSet = new NavigationDataSet(); // Ne pas commenter

        // Instanciation d'un Ship, sauvegarde dans un  NavigationDataSet
        Ship ship = ShipBuilder
                .create()
                .mmsi(124578)
                .name(SHIP_NAME)
                .build();
        navigationDataSet.add(ship);

        // Les avurnav du shom sont des flux Rss,
        // Instanciation d'un AvurnavRSS
        // sauvegarde dans un  NavigationDataSet
        Rss rss = new Rss();
        try {
            rss = ImportExportXML.imports(rss, new File(DIR_SRC + NAME_RSS + ".xml"));
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        AvurnavRSS avurnav = new AvurnavRSS(rss);
        navigationDataSet.add(avurnav);

        // Les avurnav du shom sont des requetes Sparql, 
        // Instanciation d'un AvurnavSet
        // sauvegarde dans un  NavigationDataSet
        AvurnavSet avurnavSet = new AvurnavSet(DIR_SRC, NAME_RDF, "rdf");
        navigationDataSet.add(avurnavSet);

        // Ne pas commenter
        // sauvegarde du NavigationDataSet dans un fic
        try {
            ImportExportXML.exports(navigationDataSet, DIR_SRC + TEST + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Lecture du fic et instanciation des entitees
        NavigationDataSet nds = null;
        try {
            nds = ImportExportXML.imports(navigationDataSet, DIR_SRC + TEST + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new App();

    }
}
