/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.app;

import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Document;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Publication;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.ShomSailingDirections;

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
        //  System.out.println(document);
        Publication publication = document.getMetadata().getPublication();
        System.out.println(publication.getStitle());
        System.out.println(publication.getCountry());
        System.out.println(publication.getRegion());
        System.out.println(publication.getNumbering().getSerie());
        System.out.println(publication.getNumbering().getVolume());
        System.out.println(publication.getUpdate().getGan());
        System.out.println(document.getBook().getChapters());
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (Exception e) {
            System.out.println("App e : " + e);
        }
    }
}
