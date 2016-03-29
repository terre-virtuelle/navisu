/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.graph.neo4J;

import org.capcaval.c3.component.ComponentService;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface GraphDatabaseComponentServices
        extends ComponentService {

    GraphDatabaseService newEmbeddedDatabase(String dbName);

    void close();
}
