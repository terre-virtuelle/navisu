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
        readRdf(NAME);
    }

    public final void readRdf(String name) {
        String[] tmp;
        String avurnavName = "";
        Avurnav avurnav = null;
        List<Avurnav> avurnavs = new ArrayList<>();
        Sparql sparql = new Sparql();
        try {
            sparql = ImportExportXML.imports(sparql, new File(DIR_SRC + name + ".rdf"));
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex " + ex);
        }
        List<Result> results = sparql.getResults().getResultList();
        for (Result r : results) {
            List<Binding> bindings = r.getBindings();
            for (int i = 0; i < 3; i++) {
                if (bindings.get(0).getName().equals("s")) {
                    tmp = bindings.get(0).getUri().split("#");
                    if (!avurnavName.equals(tmp[1])) {
                        avurnav = new Avurnav();
                        avurnavs.add(avurnav);
                        avurnavName = tmp[1];
                    }
                    if (bindings.get(1).getName().equals("p")) {
                        if (bindings.get(1).getUri() != null) {
                            tmp = bindings.get(1).getUri().split("#");
                        }
                        if (avurnav != null) {
                            switch (tmp[1]) {
                                case "globalZone":
                                    avurnav.setGlobalZone(bindings.get(2).getLiteral());
                                    break;
                                case "textDescription":
                                    avurnav.setDescription(bindings.get(2).getLiteral());
                                    break;
                                case "expirationDate":
                                    avurnav.setExpirationDate(bindings.get(2).getLiteral());
                                    break;
                                case "avNumber":
                                    avurnav.setId(Integer.parseInt(bindings.get(2).getLiteral()));
                                    break;
                                case "broadcastTime":
                                    avurnav.setBroadcastTime(bindings.get(2).getLiteral());
                                    break;
                                default:
                            }
                        }
                    }
                }
            }
        }
        /*
         avurnavs.stream().forEach((a) -> {
         System.out.println(a);
         });
         AvurnavSet avurnavSet = new AvurnavSet(avurnavs);
         try {
         ImportExportXML.exports(avurnavSet, DIR_TARGET + name + ".xml");
         } catch (JAXBException | FileNotFoundException ex) {
         Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        AvurnavSet avurnavSet1 = new AvurnavSet(DIR_SRC, NAME, "rdf");
        avurnavSet1.print();

        try {
            ImportExportXML.exports(avurnavSet1, DIR_TARGET + NAME + ".xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
