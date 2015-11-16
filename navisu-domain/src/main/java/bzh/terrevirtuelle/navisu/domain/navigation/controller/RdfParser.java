/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.controller;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.area.Area;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.controller.rdf.Binding;
import bzh.terrevirtuelle.navisu.domain.navigation.controller.rdf.Result;
import bzh.terrevirtuelle.navisu.domain.navigation.controller.rdf.Sparql;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class RdfParser {

    private final List<NavigationData> navigationDataList;
    private final String AVURNAV = "Avurnav";
    private final String AREA = "Area";
    private Avurnav avurnav = null;
    private Area area = null;

    public RdfParser() {
        navigationDataList = new ArrayList<>();
    }

    public List<NavigationData> parse(String srcDir, String filename, String extension) {

        String[] tmp;
        String avurnavName = "";
        String areaName = "";
        
        Path path = Paths.get(srcDir + filename + ".rdf");
        List<String> tmpList = null;
        try {
            tmpList = Files.readAllLines(path);
        } catch (IOException ex) {
            Logger.getLogger(RdfParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex " + ex);
        }
        if (tmpList != null) {
            List<String> lines = new ArrayList<>();
            for (String s : tmpList) {
                if (s.contains("xmlns")) {
                    s = s.replace("xmlns", "name");
                }
                lines.add(s);
            }
            path = Paths.get(srcDir + filename + "_.rdf");
            try {
                Files.write(path, lines, Charset.defaultCharset());
            } catch (IOException ex) {
                Logger.getLogger(RdfParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (extension.contains("rdf")) {
                Sparql sparql = new Sparql();
                try {
                    sparql = ImportExportXML.imports(sparql, new File(srcDir + filename + "_.rdf"));
                } catch (JAXBException | FileNotFoundException ex) {
                    Logger.getLogger(RdfParser.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Files.delete(path);
                } catch (IOException ex) {
                    Logger.getLogger(RdfParser.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Result> results = sparql.getResults().getResultList();
                for (Result r : results) {
                    List<Binding> bindings = r.getBindings();
                    if (bindings.get(0).getName().equals("s")) {
                        tmp = bindings.get(0).getUri().split("#");
                        if (tmp[1].trim().contains(AVURNAV)) {

                            if (!avurnavName.equals(tmp[1].trim())) {
                                avurnav = new Avurnav();
                                navigationDataList.add(avurnav);
                                avurnavName = tmp[1].trim();
                            }
                            if (bindings.get(1).getName().equals("p")) {
                                if (bindings.get(1).getUri() != null) {
                                    tmp = bindings.get(1).getUri().split("#");
                                    if (avurnav != null) {
                                        switch (tmp[1].trim()) {
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
                        } else if (tmp[1].trim().contains(AREA)) {
                            if (!areaName.equals(tmp[1].trim())) {
                                area = new Area();
                                navigationDataList.add(area);
                                areaName = tmp[1].trim();
                                String number=areaName.replace(AREA, "");
                                area.setId(Integer.parseInt(number));
                            }
                            if (bindings.get(1).getName().equals("p")) {
                                if (bindings.get(1).getUri() != null) {
                                    tmp = bindings.get(1).getUri().split("#");
                                    if (area != null) {
                                        switch (tmp[1].trim()) {
                                            case "hasGeometry":
                                                area.setWkt(bindings.get(2).getLiteral());
                                                break;
                                            case "hasChapter":
                                                area.setBook(bindings.get(2).getUri().split("#")[1]);
                                                break;
                                            case "zoneName":
                                                area.setZoneName(bindings.get(2).getLiteral());
                                                break;
                                            case "zoneData":
                                                area.setDescription(bindings.get(2).getLiteral());
                                                break;
                                            default:
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return navigationDataList;
    }

    @Override
    public String toString() {
        return "RdfParser{" + "navigationData=" + navigationDataList + '}';
    }

}
