/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.app;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import java.sql.Connection;
import org.capcaval.c3.component.ComponentService;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * NaVisu
 *
 * @date 21 mai 2015
 * @author Serge Morvan
 */
public interface TestDBServices
        extends ComponentService {

    Connection connect(String dbName, String user, String passwd);

    GraphDatabaseService newEmbeddedDatabase(String dbName);

    void close();

    DatabaseDriver getDriver();

    void runJDBC();

    void runJPA();

    void runNeo4J(String dbName);
}
