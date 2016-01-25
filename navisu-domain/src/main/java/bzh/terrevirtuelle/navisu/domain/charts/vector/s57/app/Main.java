package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Node;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.BuoyageView;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Highway;
import bzh.terrevirtuelle.navisu.domain.gpx.view.GpxView;
import bzh.terrevirtuelle.navisu.domain.gpx.view.HighwayView;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationViewSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.model.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.view.AvurnavView;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.view.SailingDirectionsView;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.view.ShipView;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

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
 /*
        try {
            new S57Parser(new S57Lexer(new FileInputStream(new File(CHART_NAME)))).parse();
        } catch (FileNotFoundException | RecognitionException | TokenStreamException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
 /* Results */
 /*
        System.out.println("File " + CHART_NAME + " loaded");
        System.out.println("Nombre d'objets spatiaux:" + S57Model.getSpatialObjects().size());
        System.out.println("Nombre d'objets de donnees:" + S57Model.getFeatureObjects().size());
        setBounds();
         */
 /* 
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
         System.out.println(obj + "\n");
         });
         */
 /*
        S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
            if (obj.getClass().getSimpleName().equals("DepthArea")) {
                DepthArea da = (DepthArea) obj;
                System.out.println("DepthArea Id : " + da.getId()
                        + " prim=" + da.getPrim()
                        + " inner=" + da.getInnerBoundaryIndex()
                        + " epthRangeValue1=" + da.getDepthRangeValue1()
                        + " epthRangeValue2=" + da.getDepthRangeValue2()
                        + " qualityOfSoundingMeasurement" + da.getQualityOfSoundingMeasurement()
                        + " erticaldatum=" + da.getVerticaldatum()
                        + " scaleMinimum=" + da.getScaleMinimum()
                        + " scaleMaximum=" + da.getScaleMaximum());
                HashMap<Spatial, VectorUsage> daSpatialRecord = da.getSpatialRecord();
                daSpatialRecord.keySet().stream().forEach((s) -> {
                    Edge e = (Edge) s;
                    System.out.println("Edge Id : " + e.getId());
                    HashMap<Spatial, VectorUsage> eSpatialRecord = e.getSpatialRecord();
                    eSpatialRecord.keySet().stream().forEach((ss) -> {
                        // System.out.println((ConnectedNode)ss);
                        System.out.println(ss.getClass().getSimpleName()
                                + " id=" + ss.getId()
                                + "  " + ((ConnectedNode) ss).getPoint()
                                + "  " + eSpatialRecord.get(ss));
                    });

                    List<Point2D> lp = e.getPoints();
                    System.out.println("Points size : " + lp.size());
                    lp.stream().forEach((p) -> {
                        System.out.print(p.getY() + "  " + p.getX() +"; ");
                    });
                    System.out.println("");
                });
                System.out.println();
            }
        });
         */

 /*
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
         if (obj.getClass().getSimpleName().equals("Coastline")) {
         System.out.println("Coastline Id : " + obj.getId());
         Coastline c = (Coastline) obj;
         System.out.print("  categoryOfCoastline : " + c.getCategoryOfCoastline());
         System.out.print("  colour : " + c.getColour());
         c.getEdges().stream().forEach((e) -> {
         List<Point2D> lp = e.getPoints();
         System.out.println("  Size : " + e.getPoints().size());
         lp.stream().forEach((p) -> {
         System.out.println(p.getY() + "  " + p.getX());
         });
         });
         System.out.println();
         }
         });
         */
 /*
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
         if (obj.getClass().getSimpleName().equals("SurveyReliability")) {
         System.out.println("SurveyReliability : " + obj.getClass().getName());
         SurveyReliability o = (SurveyReliability) obj;
         System.out.println("  surveyAuthority : " + o.getSurveyAuthority());
         System.out.println("  surveyDateStart : " + o.getSurveyDateStart());
         System.out.println("  surveyDateEnd : " + o.getSurveyDateEnd());
         System.out.println("  surveyType : " + o.getSurveyType());
         System.out.println("  scaleMinimum : " + o.getScaleMinimum());
         System.out.println("  scaleValueOne : " + o.getScaleValueOne());
         System.out.println("  scaleValueTwo : " + o.getScaleValueTwo());
         System.out.println();
         }
         });
         */
 /*
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {

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
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {

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
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {

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
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {

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
         S57Model.getFeatureObjects().values().stream().forEach((obj) -> {

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
        BeaconCardinal b = new BeaconCardinal(1, 2.4, 5.0);
        b = new BeaconCardinal(2, "POINT(5.0 2.4)");
        b.setColour(CHART_NAME);

        BuoyageView buoyageView = new BuoyageView(b, 450, 200);
        NavigationViewSet navigationViewSet = new NavigationViewSet();
        navigationViewSet.add(buoyageView);

        Landmark landmark = new Landmark();
        landmark.setCategoryOfLandMark("test");
        landmark.setGeometry("POINT(8.0 6.4)");
        buoyageView = new BuoyageView(landmark, 450, 200);
        navigationViewSet.add(buoyageView);

        Ship ship = new Ship();
        ShipView shipView = new ShipView(ship, 632, 124);
        navigationViewSet.add(shipView);

        Gpx gpx = new Gpx();
        GpxView gpxView = new GpxView(gpx, 56, 518);
        navigationViewSet.add(gpxView);

        Highway highway = new Highway();
        HighwayView highwayView = new HighwayView(highway, 1204, 235);
        navigationViewSet.add(highwayView);
        
        SailingDirections sailingDirections = new SailingDirections();
        SailingDirectionsView sailingDirectionsView = new SailingDirectionsView(sailingDirections, 245, 489);
        navigationViewSet.add(sailingDirectionsView);
        
        Avurnav avurnav =new Avurnav();
        AvurnavView avurnavView=new AvurnavView(avurnav,568,759);
        navigationViewSet.add(avurnavView);
        
        try {
            ImportExportXML.exports(navigationViewSet, "data/test.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            System.out.println("ex " + ex);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        NavigationViewSet navigationViewSet1 = new NavigationViewSet();
        try {
            navigationViewSet1 = ImportExportXML.imports(navigationViewSet1, "data/test.xml");
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        List<BuoyageView> navigationViewList = navigationViewSet1.get(BuoyageView.class);
        System.out.println(navigationViewList);
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
