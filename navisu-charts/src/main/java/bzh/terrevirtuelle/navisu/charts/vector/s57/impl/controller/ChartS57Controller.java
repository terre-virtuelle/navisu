/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Edge;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Node;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Point2D;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.VectorUsage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app.Main;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer.S57Lexer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controller.analyzer.S57Parser;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Coastline;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.DepthArea;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {
    
    protected String path;
    protected Set<Polyline> coastlinePolylineSet;
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
        chartLayer.setName("ChartS57Layer");
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
    
    public void addCoastlines(Set<Coastline> coastlines) {
        coastlinePolylineSet = new HashSet<>();
        coastlines.stream().forEach((c) -> {
            c.getEdges().stream().forEach((e) -> {
                List<Point2D> lp = e.getPoints();
                ArrayList<Position> positions = new ArrayList<>();
                lp.stream().forEach((p) -> {
                    positions.add(Position.fromDegrees(p.getY(), p.getX()));
                });
                Polyline pl = new Polyline(positions);
                pl.setFollowTerrain(true);
                pl.setColor(Color.BLACK);
                pl.setLineWidth(2);
                coastlinePolylineSet.add(pl);
                chartLayer.addRenderable(pl);
            });
        });
    }
    
    public void removeCoastlines() {
        coastlinePolylineSet.stream().forEach((c) -> {
            chartLayer.removeRenderable(c);
        });
    }
    
    public void addDepthAreas(Set<DepthArea> depthAreas) {
        //  depthAreaPolylineSet = new HashSet<>();
        List<Polyline> polylines = new ArrayList<>();
        List<Path> paths = new ArrayList<>();
       // ArrayList<Position> positions = new ArrayList<>();
        depthAreas.stream().forEach((d) -> {
            float v1 = new Float(d.getDepthRangeValue1());
            float v2 = new Float(d.getDepthRangeValue2());
            //  ArrayList<Position> positions = new ArrayList<>();
            HashMap<Spatial, VectorUsage> spatialRecord = d.getSpatialRecord();
            
            spatialRecord.keySet().stream().forEach((s) -> {
                Edge n = (Edge) s;
                List<Point2D> lp = n.getPoints();
                ArrayList<Position> positions = new ArrayList<>();
                
                lp.stream().forEach((p) -> {
                    positions.add(Position.fromDegrees(p.getY(), p.getX(),1));
                });
                /*
                 Polyline pl = new Polyline(positions);
                 pl.setFollowTerrain(true);
                 pl.setColor(Color.YELLOW);
                 // chartLayer.addRenderable(pl);
                 polylines.add(pl);
                 positions.clear();
                 */
                
                Path pl = new Path(positions);
              //  pl.setFollowTerrain(true);
                ShapeAttributes attrs = new BasicShapeAttributes();
                attrs.setOutlineMaterial(new Material(Color.yellow));
                pl.setAttributes(attrs);
                
                chartLayer.addRenderable(pl);        
            });
        });  
    }
}
