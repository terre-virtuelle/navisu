/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ontology.data.impl;

import bzh.terrevirtuelle.navisu.ontology.data.DataAccess;
import bzh.terrevirtuelle.navisu.ontology.data.DataAccessServices;
import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.ReadableByteChannel;
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

    final String target = "\"Phare du Petit Minou\"@fr .";
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
        queryImage("Le Petit Minou");

    }

    public void test_0() {
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
    /*
     @Override
     public Image queryImage(String target) {
     URL url = null;
     String sparqlQueryStringMinouImage = " SELECT ?y "
     + " WHERE {{ "
     + " ?s "
     + " <http://www.w3.org/2000/01/rdf-schema#label> "
     + " \"" + target + "\"@fr ."
     + " ?s <http://dbpedia.org/ontology/thumbnail> ?y"
     + "}}";
     System.out.println(sparqlQueryStringMinouImage);
     Query query = QueryFactory.create(sparqlQueryStringMinouImage);
     //Choose the parser to used to parse the result set
     ARQ.getContext().setTrue(ARQ.useSAX);
     //Executing SPARQL Query and pointing to the DBpedia SPARQL Endpoint
     QueryExecution qexec = QueryExecutionFactory.sparqlService("http://fr.dbpedia.org/sparql", query);
     //Retrieving the SPARQL Query results
     ResultSet results = qexec.execSelect();
     //Iterating over the SPARQL Query results
     while (results.hasNext()) {
     QuerySolution soln = results.nextSolution();
     try {
     url = new URL(soln.get("?y").toString());
     } catch (MalformedURLException e) {
     System.out.println("e " + e);
     }
     }
     qexec.close();

     //==========================================
     //Get the image and save it to file
     //==========================================
     URLConnection urlConnection = null;
     ReadableByteChannel rbc;
     FileOutputStream fos;
     String redirect;
     Image image = null;
     try {
     if (url != null) {
     urlConnection = url.openConnection();
     if (urlConnection != null) {
     redirect = urlConnection.getHeaderField("Location");
     //Let's check if there's a redirection (http 301) and get the new URL
     if (redirect != null) {
     urlConnection = new URL(redirect).openConnection();
     }
     if (urlConnection != null) {
     image = ImageIO.read(urlConnection.getInputStream());
     }
     }
     }
     } catch (IOException e) {
     System.out.println("e " + e);
     }
     System.out.println("image " + image);
     return image;
     }
     */

    @Override
    public Image queryImage(String target) {
        //==========================================
        //Query part
        //==========================================
        URL url = null;

        //Defining SPARQL Query. This query retrieve, from fr.dppdedia.org, the URL associated with
        //the picture of the Phare du Petit Minou.
        String sparqlQueryStringImage = " SELECT ?y "
                + " WHERE {{ "
                + " ?s "
                + " <http://www.w3.org/2000/01/rdf-schema#label> "
                + "\"" + target + "\"@fr ."
                + " ?s <http://dbpedia.org/ontology/thumbnail> ?y"
                + "}}";
        System.out.println("sparqlQueryStringImage " + sparqlQueryStringImage);
        Query query = QueryFactory.create(sparqlQueryStringImage);
        //Choose the parser to used to parse the result set
        ARQ.getContext().setTrue(ARQ.useSAX);
        //Executing SPARQL Query and pointing to the DBpedia SPARQL Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://fr.dbpedia.org/sparql", query);
        //Retrieving the SPARQL Query results
        ResultSet results = qexec.execSelect();
        //Iterating over the SPARQL Query results
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            try {
                url = new URL(soln.get("?y").toString());
                System.out.println(url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        qexec.close();

        //==========================================
        //Get the image and save it to file
        //==========================================
        URLConnection urlConnection = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        String redirect = null;

        try {
            //Setting the http connection
            if (url != null) {
                urlConnection = url.openConnection();
                redirect = urlConnection.getHeaderField("Location");
                //Let's check if there's a redirection (http 301) and get the new URL
                if (redirect != null) {
                    urlConnection = new URL(redirect).openConnection();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

//==========================================
        //Display URL content (image) in a window
        //==========================================
        Image image = null;
        try {
            image = ImageIO.read(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("image " + image);
        return image;
    }
}
