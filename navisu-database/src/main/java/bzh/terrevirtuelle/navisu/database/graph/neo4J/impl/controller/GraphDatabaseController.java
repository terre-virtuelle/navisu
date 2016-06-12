/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.graph.neo4J.impl.controller;

import java.io.File;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 *
 * @author serge
 * @date Mar 27, 2016
 *
 */
public class GraphDatabaseController {

    private static GraphDatabaseController INSTANCE;
    private final GraphDatabaseService graphDb;

    public GraphDatabaseController(String dbName) {
        graphDb = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder(new File(dbName))
                // .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
                // .setConfig(GraphDatabaseSettings.string_block_size, "60")
                // .setConfig(GraphDatabaseSettings.array_block_size, "300")
                .newGraphDatabase();
        registerShutdownHook(graphDb);
    }

    public static GraphDatabaseController getInstance(String dbName) {
        if (INSTANCE == null) {
            INSTANCE = new GraphDatabaseController(dbName);
        }
        return INSTANCE;
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    public GraphDatabaseService getGraphDb() {
        return graphDb;
    }
    
}
