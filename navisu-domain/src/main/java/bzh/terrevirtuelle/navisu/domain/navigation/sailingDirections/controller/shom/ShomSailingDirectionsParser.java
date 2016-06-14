/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.shom;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.controller.SailingDirectionsParser;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Chapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.TextPart;
import bzh.terrevirtuelle.navisu.domain.util.Degrees;
import bzh.terrevirtuelle.navisu.domain.util.Pair;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Mar 21, 2016
 *
 */
public class ShomSailingDirectionsParser
        extends SailingDirectionsParser {

    public ShomSailingDirectionsParser(String filename) {
        super(filename);
    }

    @Override
    protected void readData(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), Charset.forName("UTF-16"));
        } catch (IOException ex) {
            Logger.getLogger(ShomSailingDirectionsParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (content != null) {
            content = content.replaceAll("md:", "");
            try {
                document = ImportExportXML.imports(document, new StringReader(content));
            } catch (JAXBException ex) {
                Logger.getLogger(ShomSailingDirectionsParser.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }

    @Override
    protected Set<Text> parseText() {
        Book book = document.getBook();
        List<Chapter> chapters;
        if (book != null) {
            chapters = book.getChapters();
            chapters.stream().map((c) -> c.getChapitres()).forEach((sc) -> {
                sc.stream().map((ssc) -> ssc.getPara()).forEach((p) -> {
                    p.stream().map((pa) -> pa.getParas()).forEach((sparaList) -> {
                        sparaList.stream().map((spara) -> spara.getAlinea()).forEach((alienaList) -> {
                            alienaList.stream().map((alinea) -> alinea.getTexte()).forEach((Text texte) -> {
                                List<TextPart> textPartList;
                                if (texte != null) {
                                    textPartList = texte.getTextParts();
                                    textPartList.stream().filter((textpart)
                                            -> (textpart.getClass().getSimpleName().equals("Principal"))).filter((_item)
                                            -> (texte.contains("°"))).forEach((_item)
                                            -> {
                                        textSet.add(texte);
                                    });
                                }
                            });
                        });
                    });
                });
            });
        }
        return textSet;
    }

    @Override
    protected Map<Pair<Double, Double>, String> parsePoi() {
        textSet.stream().map((t) -> t.shorten()).map((data) -> {
            if (data.contains("(")) {
                String[] tab = data.split("\\(");
                for (String s : tab) {
                    String[] sTab = s.split("\\)");
                    for (String ss : sTab) {
                        if (ss.contains("°")) {
                            if (!data.contains("[")) {
                                try {
                                    poiMap.put(Degrees.degTodecimal(ss.trim()), data);
                                } catch (Exception ex) {
                                    //nombre mal forme possible
                                    //  Logger.getLogger(ShomSailingDirectionsParser.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                                }
                            }

                        }
                    }
                }
            }
            return data;
        }).filter((data) -> (data.contains("["))).forEach((data) -> {
            String[] tab1 = data.split("\\[");
            for (String s : tab1) {
                String[] sTab = s.split("\\]");
                for (String ss : sTab) {
                    if (ss.contains("°")) {
                        if (!data.contains("(")) {
                            try {
                                poiMap.put(Degrees.degTodecimal(ss.trim()), data);
                            } catch (Exception ex) {
                                //nombre mal forme possible
                                // Logger.getLogger(ShomSailingDirectionsParser.class.getName()).log(Level.SEVERE, ex.toString(), ex);
                            }
                        }
                    }
                }
            }
        });
        return poiMap;
    }
}
