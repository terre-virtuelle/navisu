package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Node;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyInstallation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
 /*
        NavigationDataSet navigationDataSet = new NavigationDataSet();

        Buoyage beaconCardinal = new BeaconCardinal(2, "POINT(5.0 2.4)");
        beaconCardinal.setColour(COLOR_NAME.getColor("3"));
        beaconCardinal.setImageAddress("img/buoyage_1/BCNCAR_3_3_6.2_1_14_1.png");
        navigationDataSet.add(beaconCardinal);

        Landmark landmark = new Landmark();
        landmark.setCategoryOfLandMark("test");
        landmark.setGeometry("POINT(8.0 6.4)");

        navigationDataSet.add(landmark);

        Ship ship = new Ship();
        navigationDataSet.add(ship);

        Gpx gpx = new Gpx();
        Highway highway = new Highway();
        highway.setDescription("Range : 1 sec");
        highway.setLatitude(2.0);
        highway.setLongitude(-5.0);
        highway.setGeometry("POLYGON((-4.488563780922933 48.34339883168431,-4.48858282060117 48.34334764433006,-4.488610824537018 48.34330075686735,-4.488646864663059 48.34325972317513,-4.488689746587659 48.343225903134844,-4.507352133611914 48.33108804961321,-4.5074069585995 48.33105993897154,-4.507466370012336 48.33104362114499,-4.507527862017346 48.33103978438094,-4.5075888410269895 48.33104859050503,-4.507646735090659 48.33106966809574,-4.507699102373484 48.331102128150086,-4.507743734147155 48.33114460157964,-4.507778747948869 48.33119529695544,-4.507802666979166 48.331252076066285,-4.513068926170632 48.348531033107854,-4.541709053820411 48.3508800980555,-4.558533602312241 48.34822013423315,-4.597115585253378 48.33607708200947,-4.621413185915269 48.32368019545076,-4.626677834193586 48.308385735495264,-4.626702333937507 48.308332271375754,-4.62673679325801 48.308284614217236,-4.6267798879027335 48.308244595457595,-4.626829961768649 48.30821375299531,-4.626885090545169 48.308193272088914,-4.626943155664332 48.30818393980816,-4.6270019257161685 48.308186114787354,-4.6270591422005465 48.30819971344327,-4.627112606320059 48.308224213187195,-4.627160263478576 48.3082586725077,-4.627200282238221 48.30830176715242,-4.627231124700506 48.308351841018336,-4.6272516056069 48.30840696979486,-4.627260937887656 48.30846503491402,-4.627258762908459 48.30852380496586,-4.627245164252541 48.30858102145024,-4.621941091018168 48.32399001582091,-4.6219172648819065 48.32404231866555,-4.621883901872033 48.3240891180448,-4.621842226511958 48.32412869627984,-4.621793768413501 48.324159600729686,-4.597365922887377 48.33662293956198,-4.5973196451193745 48.336641873094486,-4.558692500211961 48.348799139313236,-4.558649283716739 48.34880929740067,-4.541767224668697 48.3514783536512,-4.54169585284018 48.35148103011447,-4.512816830169306 48.3491123709832,-4.512758247958866 48.34910163429484,-4.512702941183618 48.34907953650154,-4.512653089638345 48.349046948539176,-4.512610658111951 48.34900515479055,-4.512577318949633 48.348955802464225,-4.512554386141075 48.348900836673515,-4.507344985970079 48.33180843797182,-4.489112860632482 48.343666453050396,-4.484241601531106 48.36161948255613,-4.484220712057134 48.36167445781788,-4.484189498827029 48.361724301411186,-4.48414916134772 48.36176709787471,-4.484101249765914 48.361801202564365,-4.484047605296832 48.36182530485605,-4.483990289467335 48.361838478512205,-4.483931504892609 48.361840217276615,-4.483873510630893 48.36183045432955,-4.483818535369142 48.36180956485558,-4.483768691775834 48.361778351625475,-4.483725895312308 48.36173801414616,-4.483691790622656 48.36169010256435,-4.483667688330975 48.361636458095276,-4.483654514674818 48.361579142265775,-4.483652775910403 48.36152035769105,-4.483662538857472 48.36146236342933,-4.488563780922933 48.34339883168431))");
        gpx.setHighway(highway);
        navigationDataSet.add(gpx);

        SailingDirections sailingDirections = new ShomSailingDirections("data/in/d21.xml");
        navigationDataSet.add(sailingDirections);
        
        try {
            ImportExportXML.exports(navigationDataSet, "data/test.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            System.out.println("ex " + ex);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        navigationDataSet.clear();
        try {
           // navigationDataSet = ImportExportXML.imports(navigationDataSet, "data/Route.nds");
             navigationDataSet = ImportExportXML.imports(navigationDataSet, "data/test.xml");
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<BeaconLateral> navigationList = navigationDataSet.get(BeaconLateral.class);
        navigationList.stream().forEach((b) -> {
            System.out.println(b.getLatitude() + "  " + b.getLongitude() + "  " + b.getImageAddress());
        });
        List<Gpx> navigationList1 = navigationDataSet.get(Gpx.class);
        navigationList1.stream().forEach((b) -> {
            System.out.println(b.getHighway());
        });
        List<SailingDirections> navigationList2 = navigationDataSet.get(SailingDirections.class);
        navigationList2.stream().forEach((b) -> {
            System.out.println(b);
        });
         */
 /*
        Buoyage beaconCardinal = new BeaconCardinal(2, "POINT(5.0 2.4)");
        List<Class> classes = getSuperClasses(beaconCardinal);
        classes.forEach((c) -> {
            System.out.println("c : " + c.getSimpleName());
        });
        System.out.println("");
        Light light = new Light();
        classes = getSuperClasses(light);
        classes.forEach((c) -> {
            System.out.println("c : " + c.getSimpleName());
        });
        Area area = new Area();
        classes = getSuperClasses(area);
        classes.forEach((c) -> {
            System.out.println("c : " + c.getSimpleName());
        });
         */
        Buoyage buoyCardinal = new BuoyCardinal();
        System.out.println("req : " + buoyCardinal.spatialRequest(-3, 48, -4, 49, "4326"));
        
        Buoyage buoyLateral = new BuoyLateral();
        System.out.println("req : " + buoyLateral.spatialRequest(-3, 48, -4, 49, "4326"));
        
        Buoyage buoyinb = new BuoyInstallation();
        System.out.println("req : " + buoyinb.spatialRequest(-3, 48, 1, 49));
        
        Buoyage buoyspp = new BuoySpecialPurpose();
        System.out.println("req : " + buoyspp.spatialRequest(-3, 48, 1, 49));
        
        Buoyage buoyisd = new BuoyIsolatedDanger();
        System.out.println("req : " + buoyisd.spatialRequest(-3, 48, 1, 49));
        
        Buoyage buoysaw= new BuoySafeWater();
        System.out.println("req : " + buoysaw.spatialRequest(-3, 48, 1, 49));
        
        Buoyage beaconCardinal = new BeaconCardinal();
        System.out.println("req : " + beaconCardinal.spatialRequest(-3, 48, -4, 49));
        
        Buoyage bcnisd = new BeaconIsolatedDanger();
        System.out.println("req : " + bcnisd.spatialRequest(-3, 48, 1, 49));
        
        Buoyage bcnLateral = new BeaconLateral();
        System.out.println("req : " + bcnLateral.spatialRequest(-3, 48, -4, 49, "4326"));
        
        Buoyage bcnsaw= new BeaconSafeWater();
        System.out.println("req : " + bcnsaw.spatialRequest(-3, 48, 1, 49));
        
        Buoyage bcnspp = new BeaconSpecialPurpose();
        System.out.println("req : " + bcnspp.spatialRequest(-3, 48, 1, 49));
    }

    public final List<Class> getSuperClasses(Object o) {
        List<Class> classList = new ArrayList<>();
        Class classe = o.getClass();
        Class superclass = classe.getSuperclass();
        classList.add(superclass);
        while (superclass != null) {
            classe = superclass;
            superclass = classe.getSuperclass();
            if (superclass != null) {
                classList.add(superclass);
            }
        }

        return classList;
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
