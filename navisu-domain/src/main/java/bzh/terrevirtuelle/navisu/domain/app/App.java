/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.app;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;

/**
 *
 * @author serge
 * @date Apr 2, 2016
 *
 */
public class App {
    
    private static final Dimension DEFAULT_SIZE = new Dimension(550, 350);
    private final JFrame frame;
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

    public App() {

        frame = new JFrame();
        frame.setTitle("JGraphT Adapter to JGraph Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        frame.pack();
        frame.setVisible(true);
    }

    public void init() {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g
                = new ListenableDirectedMultigraph<>(
                        DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<>(g);

        JGraph jgraph = new JGraph(jgAdapter);

        //   adjustDisplaySettings(jgraph);
        frame.getContentPane().add(jgraph);
        frame.setSize(DEFAULT_SIZE);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        
        // add some sample data (graph manipulated via JGraphT)
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v1);
        g.addEdge(v4, v3);

        // position vertices nicely within JGraph component
        positionVertexAt(v1, 130, 40);
        positionVertexAt(v2, 60, 200);
        positionVertexAt(v3, 310, 230);
        positionVertexAt(v4, 380, 70);

    }

    @SuppressWarnings("unchecked")
    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds
                = new Rectangle2D.Double(
                        x,
                        y,
                        bounds.getWidth(),
                        bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    
    private static class ListenableDirectedMultigraph<V, E>
            extends DefaultListenableGraph<V, E>
            implements DirectedGraph<V, E> {

        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass) {
            super(new DirectedMultigraph<>(edgeClass));
        }
        
    }

    public static void main(String[] args) {
        new App();
    }
    
}
