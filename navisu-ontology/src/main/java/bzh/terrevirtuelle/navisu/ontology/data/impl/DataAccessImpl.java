/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ontology.data.impl;

import bzh.terrevirtuelle.navisu.ontology.data.DataAccess;
import bzh.terrevirtuelle.navisu.ontology.data.DataAccessServices;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @date 23 juil. 2015
 * @author Serge Morvan
 */
public class DataAccessImpl
        implements DataAccess, DataAccessServices, ComponentState {

    final static String PREFIX = "prefix dbpedia-owl: <http://dbpedia.org/ontology/> \n"
            + "prefix prop-fr: <http://fr.dbpedia.org/property/> \n"
            + "prefix prop:  <http://dbpedia.org/property/> \n"
            + "prefix dcterms: <http://purl.org/dc/terms/> \n"
            + "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "prefix ns1:   <http://www.w3.org/ns/prov#> \n"
            + "prefix foaf:  <http://xmlns.com/foaf/0.1/> \n"
            + "prefix dc:    <http://purl.org/dc/terms/> ";

    @Override
    public void componentInitiated() {
        try {
            Class.forName("net.rootdev.javardfa.jena.RDFaReader");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void test() {
        Model model = ModelFactory.createDefaultModel();
        model.read("http://fr.dbpedia.org/page/Phare_de_Kermorvan", "HTML");
        model.write(System.out, "TURTLE");

        String queryString
                = PREFIX
                + "SELECT ?y WHERE {  "
                // + "     ?x rdfs:label \"Phare du Petit Minou\"@fr ."
                //  + "     ?x foaf:depiction ?y "
                + " ?x dbpedia-owl:thumbnail ?y "
                + "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            String[] url0 = soln.toString().split("=");
            if (url0.length > 0) {
                String[] url1 = url0[1].split("\\?");
                url1[0] = url1[0].replace("<", "");
                try {
                    String out2 = new String(url1[0].trim().getBytes("iso-8859-1"), "utf-8");
                    System.out.println(out2);  
                 //  BufferedImage image = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/1/1b/Phare_de_Kermorvan_vu_de_la_presqu%27%C3%AEle.jpg"));
                 //   System.out.println("image " + image);
                } catch (IOException ex) {
                    Logger.getLogger(DataAccessImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
