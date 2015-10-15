package bzh.terrevirtuelle.navisu.ontology.data.impl;

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

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import bzh.terrevirtuelle.navisu.ontology.data.DataAccess;
import bzh.terrevirtuelle.navisu.ontology.data.DataAccessServices;

public class DataAccessImpl implements DataAccess, DataAccessServices, ComponentState {

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
	            Logger.getLogger(DataAccessImplOld.class.getName()).log(Level.SEVERE, null, ex);
	        }		
	}

	@Override
	public void componentStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentStopped() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image queryImage(String targetName) {
        URL url = null;
        Image image = null;
        //Defining SPARQL Query. This query retrieve, from fr.dppdedia.org, the URL associated with
        //the picture of the Phare du Petit Minou.
        
        String queryString 
        		= PREFIX
        		+ " SELECT ?y "
                + " WHERE { "
                + "?s prop-fr:feux ?o ."
                + " ?s rdfs:label ?name ."
                + " ?s dbpedia-owl:thumbnail ?y ."
                + " FILTER ( REGEX ( ?name, \"Phare\", \"i\"))"
                + " FILTER ( REGEX ( ?name, \"" + targetName + "\",  \"i\"))"
                + "}";
        
        
        url = getResourceURL(queryString); 
        try {
            image = ImageIO.read(getConnection2ImageSource(url).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("image " + image);
        return image;
	}
	
	
	private URL getResourceURL(String sparqlQueryString) {
		ResultSet results = null;
		URL url = null;
		
		System.out.println("sparqlQueryString " + sparqlQueryString);
        Query query = QueryFactory.create(sparqlQueryString);
        //Choose the parser to used to parse the result set
        ARQ.getContext().setTrue(ARQ.useSAX);
        //Executing SPARQL Query and pointing to the DBpedia SPARQL Endpoint
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://fr.dbpedia.org/sparql", query);
        //Retrieving the SPARQL Query results
        results = qexec.execSelect();
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
		return url;
	}
	
	private URLConnection getConnection2ImageSource(URL url) {	
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
	    return urlConnection;
	}
}
