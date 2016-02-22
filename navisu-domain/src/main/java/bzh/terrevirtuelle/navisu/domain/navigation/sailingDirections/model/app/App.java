/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.app;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Alinea;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Chapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Para;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.SubChapter;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.SubParagrah;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.TextPart;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.util.Degrees;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private Set<Text> textSet;
    private Path path;
    private final String filename = "data/in/document.xml";
    private String newFilename;

    public App() throws Exception {
        // fileFilter(filename);

        textSet = new HashSet<>();
        Document document = new Document();
        try {
            document = ImportExportXML.imports(document, filename);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            ImportExportXML.exports(document, "data/in/d22.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        Book ouvrage = document.getBook();
        if (ouvrage != null) {
            List<Chapter> chapitres = ouvrage.getChapitre();
            for (Chapter c : chapitres) {
                List<SubChapter> sc = c.getsChapitre();
                for (SubChapter ssc : sc) {
                    List<Para> p = ssc.getPara();
                    for (Para pa : p) {
                        List<SubParagrah> sparaList = pa.getSpara();
                        for (SubParagrah spara : sparaList) {
                            List<Alinea> alienaList = spara.getAlinea();
                            for (Alinea alinea : alienaList) {
                                Text texte = alinea.getTexte();
                                List<TextPart> textPartList = null;
                                if (texte != null) {
                                    textPartList = texte.getTextParts();
                                    for (TextPart textpart : textPartList) {
                                        if (textpart.getClass().getSimpleName().equals("Principal")) {
                                            if (texte.contains("°")) {
                                                textSet.add(texte);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (Text t : textSet) {
                String data = t.shorten();
                System.out.println(data);
                if (data.contains("(")) {
                    String[] tab = data.split("\\(");
                    for (String s : tab) {
                        String[] sTab = s.split("\\)");
                        for (String ss : sTab) {
                            if (ss.contains("°")) {
                                if (!data.contains("[")) {
                                    System.out.println(Degrees.degTodecimal(ss.trim()));
                                }

                            }
                        }
                    }
                }
                if (data.contains("[")) {
                    String[] tab1 = data.split("\\[");
                    for (String s : tab1) {
                        String[] sTab = s.split("\\]");
                        for (String ss : sTab) {
                            if (ss.contains("°")) {
                                if (!data.contains("(")) {
                                    System.out.println(Degrees.degTodecimal(ss.trim()));
                                }
                            }
                        }
                    }
                }
                System.out.println("");
            }
        }

    }

    private String fileFilter(String filename) {
        String repFileName = null;
        path = Paths.get(filename);
        repFileName = path.toString().split("\\.")[0];
        List<String> tmpList = null;

        try {
            //  Files.copy(path,Paths.get(path.toString().split("\\.")[0]+ "_.xml"));
            tmpList = Files.readAllLines(Paths.get(path.toString().split("\\.")[0] + "_.xml"));
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        path = Paths.get(filename + "_.xml");
        if (tmpList != null) {
            List<String> lines = new ArrayList<>();
            for (String s : tmpList) {
                if (s.contains("xmlns")) {
                    s = s.replace("xmlns:md", "nameSpace");
                }
                lines.add(s);
                if (s.contains("md")) {
                    s = s.replace("md:", "");
                }
            }

            System.out.println(filename + "_.xml");
            try {
                Files.write(path, lines, Charset.defaultCharset());
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*
                Files.delete(path);
             */
        }
        return repFileName;
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (Exception e) {
            System.out.println("App e : " + e);
        }
    }
}
