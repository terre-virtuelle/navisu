/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller;

import bzh.terrevirtuelle.navisu.currents.impl.controller.loader.CurrentsShapefileLoader;
import bzh.terrevirtuelle.navisu.domain.currents.model.Current;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.util.BufferFactory;
import gov.nasa.worldwind.util.BufferWrapper;
import gov.nasa.worldwind.util.WWMath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 10 nov. 2014 NaVisu project
 */
public class CurrentsShapefileController {

    private static final CurrentsShapefileController INSTANCE;
    protected String path;
    private final int WIDTH = 100;//132
    private final int HEIGHT = 100;
    int SIZE = HEIGHT * WIDTH;
    protected final double SMOOTHNESS = 0.5d;
    protected final double HUE_BLUE = 240d / 360d;
    protected final double HUE_RED = 0d / 360d;
    double MIN_VALUE = -200e3;
    double MAX_VALUE = 200e3;
    private final String LAYER_NAME = "Currents";
    private final List<Layer> layers;
    private final RenderableLayer layer;
    private CurrentsShapefileLoader shapefileLoader;
    private List<Current> currents;
    private double latRange;
    private double lonRange;
    //  private double minLat;
    private double minLon;
    private double maxLat;
    // private double maxLon;
    // private final double[][] gridPoints;
    private double[] values;

    static {
        INSTANCE = new CurrentsShapefileController();
    }

    private CurrentsShapefileController() {
        this.layers = new ArrayList<>();
        layer = new RenderableLayer();
        layer.setName(LAYER_NAME);
    }

    public static CurrentsShapefileController getInstance() {
        return INSTANCE;
    }

    public final List<Layer> create(String path) {
        this.path = path;

        shapefileLoader = new CurrentsShapefileLoader();
        shapefileLoader.createLayersFromSource(new File(path));//layer = pas d'affectation si AnalyticSurface
        currents = shapefileLoader.getCurrents();

        latRange = shapefileLoader.getLatRange() / HEIGHT;
        lonRange = shapefileLoader.getLonRange() / WIDTH;
        //   minLat = shapefileLoader.getMinLat();
        minLon = shapefileLoader.getMinLon();
        maxLat = shapefileLoader.getMaxLat();
        // maxLon = shapefileLoader.getMaxLon();

        buildGrid();//Chargement de values[]
        createSurface();

        return layers;
    }

    protected void buildGrid() {
        //Creation d'une grille reguliere en coordonnees JTS
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[][] coordinates = new Coordinate[HEIGHT][WIDTH];
        Coordinate[] coordPolys;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                coordinates[i][j] = new Coordinate(minLon + i * lonRange, maxLat - j * latRange);//LonLat   
            }
        }
        // Creation de polygones a partir de la grille, en JTS
        Polygon[][] polygons = new Polygon[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT - 1; i++) {
            for (int j = 0; j < WIDTH - 1; j++) {
                coordPolys = new Coordinate[5];
                coordPolys[0] = coordinates[i][j];
                coordPolys[1] = coordinates[i + 1][j];
                coordPolys[2] = coordinates[i + 1][j + 1];
                coordPolys[3] = coordinates[i][j + 1];
                coordPolys[4] = coordinates[i][j];

                LinearRing linearRing = geometryFactory.createLinearRing(coordPolys);
                polygons[i][j] = geometryFactory.createPolygon(linearRing, null);
            }
        }
        //Creatio d'une liste de coordonnées JTS, a partir des points de données
        List<Coordinate> coordJTSs = new ArrayList<>();
        currents.stream().forEach((current) -> {
            coordJTSs.add(new Coordinate(current.getLon(), current.getLat()));
        });
        //Recherche et placement des points de données sur la grille
        values = new double[HEIGHT * WIDTH];
        int k = 0;
        int kk = 0;
        for (int i = 0; i < HEIGHT - 1; i++) {
            for (int j = 0; j < WIDTH - 1; j++) {
                for (Coordinate c : coordJTSs) {
                    if (polygons[i][j].contains(new GeometryFactory().createPoint(c))) {
                        values[kk] = currents.get(k).getSpeed();
                    }
                    k++;
                }
                k = 0;
                kk++;
            }
        }

    }

    private void createSurface() {
        AnalyticSurface surface = new AnalyticSurface();
        surface.setSector(shapefileLoader.getSector());
        surface.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        surface.setDimensions(WIDTH, HEIGHT);
        surface.setClientLayer(layer);

        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setShadowOpacity(1.0);
        attr.setDrawOutline(true);
        attr.setOutlineMaterial(Material.BLACK);
        surface.setSurfaceAttributes(attr);
  
        layer.addRenderable(surface);
        layer.setEnabled(true);
        layers.add(layer);
        
        BufferWrapper firstBuffer = createBuffer();
        BufferWrapper secondBuffer = createBuffer();
        mixValuesOverTime(firstBuffer, secondBuffer, MIN_VALUE, MAX_VALUE, HUE_BLUE, HUE_RED, surface);
    }

    protected BufferWrapper createBuffer() {
        smoothValues(WIDTH, HEIGHT, values, SMOOTHNESS);
        scaleValues(values, SIZE, MIN_VALUE, MAX_VALUE);
        BufferFactory factory = new BufferFactory.DoubleBufferFactory();
        BufferWrapper buffer = factory.newBuffer(SIZE);
        buffer.putDouble(0, values, 0, SIZE);
        return buffer;
    }

    protected void scaleValues(double[] values, int count, double minValue, double maxValue) {
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (min > values[i]) {
                min = values[i];
            }
            if (max < values[i]) {
                max = values[i];
            }
        }

        for (int i = 0; i < count; i++) {
            values[i] = (values[i] - min) / (max - min);
            values[i] = minValue + values[i] * (maxValue - minValue);
        }
    }

    protected void mixValuesOverTime(
            final BufferWrapper firstBuffer, final BufferWrapper secondBuffer,
            final double minValue, final double maxValue, 
            final double minHue, final double maxHue,
            final AnalyticSurface surface) {
        double a = 10.;

        surface.setValues(createMixedColorGradientGridValues(
                a, firstBuffer, secondBuffer, minValue, maxValue, minHue, maxHue));
    }

    public Iterable<? extends AnalyticSurface.GridPointAttributes> createMixedColorGradientGridValues(double a,
            BufferWrapper firstBuffer, BufferWrapper secondBuffer, double minValue, double maxValue,
            double minHue, double maxHue) {
        ArrayList<AnalyticSurface.GridPointAttributes> attributesList = new ArrayList<>();

        long length = Math.min(firstBuffer.length(), secondBuffer.length());
        for (int i = 0; i < length; i++) {
            double value = WWMath.mixSmooth(a, firstBuffer.getDouble(i), secondBuffer.getDouble(i));
            attributesList.add(
                    AnalyticSurface.createColorGradientAttributes(value, minValue, maxValue, minHue, maxHue));
        }
        return attributesList;
    }

    protected static void smoothValues(int width, int height, double[] values, double smoothness) {
        // top to bottom
        for (int x = 0; x < width; x++) {
            smoothBand(values, x, width, height, smoothness);
        }

        // bottom to top
        int lastRowOffset = (height - 1) * width;
        for (int x = 0; x < width; x++) {
            smoothBand(values, x + lastRowOffset, -width, height, smoothness);
        }

        // left to right
        for (int y = 0; y < height; y++) {
            smoothBand(values, y * width, 1, width, smoothness);
        }

        // right to left
        int lastColOffset = width - 1;
        for (int y = 0; y < height; y++) {
            smoothBand(values, lastColOffset + y * width, -1, width, smoothness);
        }
    }

    protected static void smoothBand(double[] values, int start, int stride, int count, double smoothness) {
        double prevValue = values[start];
        int j = start + stride;

        for (int i = 0; i < count - 1; i++) {
            values[j] = smoothness * prevValue + (1 - smoothness) * values[j];
            prevValue = values[j];
            j += stride;
        }
    }

}
