/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.model.ChartS57Model;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Node;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app.Main;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer.S57Lexer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer.S57Parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {

    protected String path;

    private ChartS57Model model;

    private ChartS57Layer chartLayer;

    public ChartS57Controller(String path) {
        this.path = path;
        /* Parser.parse() */
        try {
            new S57Parser(new S57Lexer(new FileInputStream(new File(path)))).parse();
        } catch (FileNotFoundException | RecognitionException | TokenStreamException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("File " + path + " loaded");
        System.out.println("Nombre d'objets spatiaux:" + S57Model.getSpatialObjects().size());
        System.out.println("Nombre d'objets de donnees:" + S57Model.getFeatureObjects().size());
        setBounds();
      chartLayer = new ChartS57Layer();
    }

    /**
     * Get the value of chartLayer
     *
     * @return the value of chartLayer
     */
    public ChartS57Layer getChartS57Layer() {
        return chartLayer;
    }

    /**
     * Set the value of chartLayer
     *
     * @param chartLayer new value of chartLayer
     */
    public void setChartLayer(ChartS57Layer chartLayer) {
        this.chartLayer = chartLayer;
    }

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public ChartS57Model getModel() {
        return model;
    }

    /**
     * Set the value of model
     *
     * @param model new value of model
     */
    public void setModel(ChartS57Model model) {
        this.model = model;
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Determine les limites de la carte. Les enregistre sous la forme des
     * limites de cadre SVG
     */
    public final void setBounds() {
        double minX = 180, minY = 180, maxX = -180, maxY = -180;
        HashSet<Spatial> sp = new HashSet<>(S57Model.getSpatialObjects().values());
        Iterator<Spatial> itSp = sp.iterator();
        while (itSp.hasNext()) {
            Spatial spObj = itSp.next();
            if (spObj.getClass().getSimpleName().equals("ConnectedNode")
                    || spObj.getClass().getSimpleName().equals("IsolatedNode")) {
                Node n = (Node) spObj;
                if (minX > n.getPoint().getX()) {
                    minX = n.getPoint().getX();
                } else if (maxX < n.getPoint().getX()) {
                    maxX = n.getPoint().getX();
                }
                if (minY > n.getPoint().getY()) {
                    minY = n.getPoint().getY();
                } else if (maxY < n.getPoint().getY()) {
                    maxY = n.getPoint().getY();
                }
            }
        }
        System.out.println(minY + " " + minX + " " + maxY + " " + maxX);
    }

}
