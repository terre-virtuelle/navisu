package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app;

/*
 * Cree le 21 sept. 2005
 * Cyril Avonde, Loic HERVE, Serge Morvan
 * CERV 2006
 */
import antlr.RecognitionException;
import antlr.TokenStreamException;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Edge;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Node;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Point2D;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.VectorUsage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer.DataSet;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer.S57Lexer;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer.S57Parser;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Coastline;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application NaVisu<br>
 * Recuperation des donnees de cartes marines S57<br>
 * Affichage texte des cartes marines
 *
 * @author Serge MORVAN
 * @version 0.1
 */
public class Main {

    final String CHART_NAME = "data/FR571220.000";
    //final String CHART_NAME = "data/US1AK90M.000";

    public Main() {
        /* Parser.parse() */
        try {
            new S57Parser(new S57Lexer(new FileInputStream(new File(CHART_NAME)))).parse();
        } catch (FileNotFoundException | RecognitionException | TokenStreamException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Results */
        System.out.println("File " + CHART_NAME + " loaded");
        System.out.println("Nombre d'objets spatiaux:" + DataSet.getSpatialObjects().size());
        System.out.println("Nombre d'objets de donn�es:" + DataSet.getFeatureObjects().size());
        setBounds();
        /* 
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {
         System.out.println(obj + "\n");
         });
         */
        /*
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {
         if (obj.getClass().getSimpleName().equals("DepthArea")) {
         System.out.println("DepthArea Id : " + obj.getId());
         DepthArea depthArea = (DepthArea) obj;
         System.out.println("DepthRangeValue1 " + depthArea.getDepthRangeValue1());
         System.out.println("DepthRangeValue2 " + depthArea.getDepthRangeValue2());
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();
         spatialRecord.keySet().stream().forEach((s) -> {
         Edge n = (Edge) s;
         List<Point2D> lp = n.getPoints();
         System.out.println("  Size : " + n.getPoints().size());
         lp.stream().forEach((p) -> {
         System.out.println(p.getY() + "  " + p.getX());
         });
         });
         System.out.println();
         }
         });
         */

        DataSet.getFeatureObjects().values().stream().forEach((obj) -> {
            if (obj.getClass().getSimpleName().equals("Coastline")) {
                System.out.println("Coastline Id : " + obj.getId());
                Coastline c = (Coastline) obj;
                System.out.print("  categoryOfCoastline : " + c.getCategoryOfCoastline());
                System.out.print("  colour : " + c.getColour());
                HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();
                spatialRecord.keySet().stream().forEach((s) -> {
                    Edge n = (Edge) s;
                    List<Point2D> lp = n.getPoints();
                    System.out.println("  Size : " + n.getPoints().size());
                    lp.stream().forEach((p) -> {
                        System.out.println(p.getY() + "  " + p.getX());
                    });
                });
                System.out.println();
            }
        });

        /*
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {

         if (obj.getClass().getSimpleName().equals("BeaconCardinal")) {
         //  System.out.println(obj.getSpatialRecord());
         // System.out.println("obj.getClass().getName() " + obj.getClass().getSimpleName());
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();

         spatialRecord.keySet().stream().forEach((s) -> {
         Node n = (Node) s;
         System.out.print("BeaconCardinal " + n.getPoint().getY() + " " + n.getPoint().getX());
         BeaconCardinal b = (BeaconCardinal) obj;
         System.out.print("  shape : " + b.getBeaconShape());
         System.out.print("  categoryOfCardinalMark : " + b.getCategoryOfCardinalMark());
         System.out.print("  colour : " + b.getColour());
         System.out.print("  colourPattern : " + b.getColourPattern());
         System.out.println("  name : " + b.getObjectName());
         });
         //  System.out.println(obj + "\n");
         }
         });
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {

         if (obj.getClass().getSimpleName().equals("BeaconLateral")) {
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();

         spatialRecord.keySet().stream().forEach((s) -> {
         Node n = (Node) s;
         System.out.print("BeaconLateral " + n.getPoint().getY() + " " + n.getPoint().getX());
         BeaconLateral b = (BeaconLateral) obj;
         System.out.print("  shape : " + b.getBeaconShape());
         System.out.print("  categoryOfLateralMark : " + b.getCategoryOfLateralMark());
         System.out.print("  colour : " + b.getColour());
         System.out.print("  colourPattern : " + b.getColourPattern());
         System.out.println("  name : " + b.getObjectName());
         });
         }
         });
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {

         if (obj.getClass().getSimpleName().equals("BeaconIsolatedDanger")) {
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();

         spatialRecord.keySet().stream().forEach((s) -> {
         Node n = (Node) s;
         System.out.print("BeaconIsolatedDanger " + n.getPoint().getY() + " " + n.getPoint().getX());
         BeaconIsolatedDanger b = (BeaconIsolatedDanger) obj;
         System.out.print("  shape : " + b.getBeaconShape());
         System.out.print("  colour : " + b.getColour());
         System.out.print("  colourPattern : " + b.getColourPattern());
         System.out.println("  name : " + b.getObjectName());
         });
         }
         });
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {

         if (obj.getClass().getSimpleName().equals("BuoyCardinal")) {
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();

         spatialRecord.keySet().stream().forEach((s) -> {
         Node n = (Node) s;
         System.out.print("BuoyCardinal " + n.getPoint().getY() + " " + n.getPoint().getX());
         BuoyCardinal b = (BuoyCardinal) obj;
         System.out.print("  shape : " + b.getBuoyShape());
         System.out.print("  colour : " + b.getColour());
         System.out.print("  colourPattern : " + b.getColourPattern());
         System.out.print("  categoryOfCardinalMark : " + b.getCategoryOfCardinalMark());
         System.out.println("  name : " + b.getObjectName());
         });
         }
         });
         DataSet.getFeatureObjects().values().stream().forEach((obj) -> {

         if (obj.getClass().getSimpleName().equals("BuoyLateral")) {
         HashMap<Spatial, VectorUsage> spatialRecord = obj.getSpatialRecord();

         spatialRecord.keySet().stream().forEach((s) -> {
         Node n = (Node) s;
         System.out.print("BuoyLateral " + n.getPoint().getY() + " " + n.getPoint().getX());
         BuoyLateral b = (BuoyLateral) obj;
         System.out.print("  shape : " + b.getBuoyShape());
         System.out.print("  colour : " + b.getColour());
         System.out.print("  colourPattern : " + b.getColourPattern());
         System.out.print("  categoryOfLateralMark : " + b.getCategoryOfLateralMark());
         System.out.println("  name : " + b.getObjectName());
         });
         }
         });
         */
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

    /**
     * Determine les limites de la carte. Les enregistre sous la forme des
     * limites de cadre SVG
     */
    public final void setBounds() {
        double minX = 180, minY = 180, maxX = -180, maxY = -180;
        HashSet<Spatial> sp = new HashSet<>(DataSet.getSpatialObjects().values());
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
