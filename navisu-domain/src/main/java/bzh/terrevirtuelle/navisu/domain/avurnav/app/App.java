/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.avurnav.app;

import bzh.terrevirtuelle.navisu.domain.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.avurnav.AvurnavSet;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Binding;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Result;
import bzh.terrevirtuelle.navisu.domain.avurnav.rdf.Sparql;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
public class App {

    String NAME = "requeteRdf";
    String DIR_SRC = "data/";
    String DIR_TARGET = "data/";

    public App() {
        AvurnavSet avurnavSet = new AvurnavSet(DIR_SRC, NAME, "rdf");
        avurnavSet.print();
        try {
            ImportExportXML.exports(avurnavSet, DIR_TARGET + NAME + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        avurnavSet = new AvurnavSet();
        try {
            avurnavSet = ImportExportXML.imports(avurnavSet, DIR_TARGET + NAME + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        avurnavSet.print();
    }

    public static void main(String[] args) {
        new App();
    }
}
