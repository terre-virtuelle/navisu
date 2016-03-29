/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.database.graph.neo4J.impl;

import bzh.terrevirtuelle.navisu.database.graph.neo4J.GraphDatabaseComponent;
import bzh.terrevirtuelle.navisu.database.graph.neo4J.GraphDatabaseComponentServices;
import bzh.terrevirtuelle.navisu.database.graph.neo4J.impl.controller.GraphDatabaseController;
import org.capcaval.c3.component.ComponentState;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 *
 * @author serge
 * @date Mar 27, 2016
 *
 */
public class GraphDatabaseComponentImpl implements
        GraphDatabaseComponent, GraphDatabaseComponentServices, ComponentState {

    GraphDatabaseController graphDatabaseController;

    @Override
    public GraphDatabaseService newEmbeddedDatabase(String dbName) {
        graphDatabaseController = GraphDatabaseController.getInstance(dbName);
        return graphDatabaseController.getGraphDb();
    }

    @Override
    public void close() {
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
