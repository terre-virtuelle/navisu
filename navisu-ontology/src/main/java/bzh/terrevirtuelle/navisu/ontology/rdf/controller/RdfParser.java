/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ontology.rdf.controller;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirectionsOld;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.rdf.Binding;
import bzh.terrevirtuelle.navisu.domain.rdf.Result;
import bzh.terrevirtuelle.navisu.domain.rdf.Sparql;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
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
    private final String SAILING_DIRECTIONS = "Area";
    private NavigationalWarnings avurnav = null;
    private SailingDirectionsOld sailingDirections = null;
    private Path path;
    private String[] tmp;
    private String avurnavName = "";
    private String sailingDirectionsName = "";
    private String repFileName;
    protected WKTReader wktReader;
    protected Geometry geometry = null;

    public RdfParser() {
        navigationDataList = new ArrayList<>();
        wktReader = new WKTReader();
    }

    public RdfParser(File file) {
        navigationDataList = new ArrayList<>();
        path = file.toPath();
        repFileName = path.toString().split("\\.")[0];
        wktReader = new WKTReader();
    }

    public NavigationDataSet parse(String srcDir, String filename) {
        repFileName = srcDir + filename;
        path = Paths.get(repFileName + ".rdf");
        return parse();
    }

    public NavigationDataSet parse() {

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
            path = Paths.get(repFileName + "_.rdf");
            try {
                Files.write(path, lines, Charset.defaultCharset());
            } catch (IOException ex) {
                Logger.getLogger(RdfParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            Sparql sparql = new Sparql();
            try {
                sparql = ImportExportXML.imports(sparql, new File(repFileName + "_.rdf"));
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
                            avurnav = new NavigationalWarnings();
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
                                        case "hasGeometry":
                                            String geo = bindings.get(2).getLiteral();
                                            avurnav.setGeometry(geo);
                                            break;
                                        case "hasRestriction":
                                            avurnav.setRestriction(bindings.get(2).getUri().split("#")[1]);
                                            break;
                                        case "type":
                                            avurnav.setType(bindings.get(2).getUri().split("#")[1]);
                                            break;
                                        default:
                                    }
                                }
                            }
                        }
                    } else if (tmp[1].trim().contains(SAILING_DIRECTIONS)) {
                        if (!sailingDirectionsName.equals(tmp[1].trim())) {
                            sailingDirections = new SailingDirectionsOld();
                            navigationDataList.add(sailingDirections);
                            sailingDirectionsName = tmp[1].trim();
                            String number = sailingDirectionsName.replace(SAILING_DIRECTIONS, "");
                            sailingDirections.setId(Integer.parseInt(number));
                        }
                        if (bindings.get(1).getName().equals("p")) {
                            if (bindings.get(1).getUri() != null) {
                                tmp = bindings.get(1).getUri().split("#");
                                if (sailingDirections != null) {
                                    switch (tmp[1].trim()) {
                                        case "hasGeometry":
                                            String geo = bindings.get(2).getLiteral();
                                            sailingDirections.setWkt(geo);
                                            break;
                                        case "hasChapter":
                                            sailingDirections.setBook(bindings.get(2).getUri().split("#")[1]);
                                            break;
                                        case "zoneName":
                                            sailingDirections.setZoneName(bindings.get(2).getLiteral());
                                            break;
                                        case "zoneData":
                                            sailingDirections.setDescription(bindings.get(2).getLiteral());
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
        NavigationDataSet navigationDataSet = new NavigationDataSet();
        navigationDataSet.addAll(navigationDataList);
        return navigationDataSet;
    }

    @Override
    public String toString() {
        return "RdfParser{" + "navigationData=" + navigationDataList + '}';
    }
}
