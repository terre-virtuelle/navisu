/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.view.symbols;

import com.google.common.collect.Range;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NaVisu
 *
 * @date 29 mai 2015
 * @author Serge Morvan
 */
public class Arrow
        extends Polygon {

    private final double Y = -0.02;
    private final double X = 0.05;
    private final double decX = X / 12.0;
    private final double YY = -0.01;

    List<Position> positions;
    static final private List<Range> RANGES;
    static final public int MAX = 200;

    static {
        RANGES = new ArrayList<>();
        RANGES.add(Range.closedOpen(0, 5));
        RANGES.add(Range.closedOpen(5, 10));
        RANGES.add(Range.closedOpen(10, 15));
        RANGES.add(Range.closedOpen(15, 20));
        RANGES.add(Range.closedOpen(20, 25));
        RANGES.add(Range.closedOpen(25, 30));
        RANGES.add(Range.closedOpen(30, 35));
        RANGES.add(Range.closedOpen(35, 40));
        RANGES.add(Range.closedOpen(40, 45));
        RANGES.add(Range.closedOpen(45, 60));
        RANGES.add(Range.closedOpen(60, 75));
        RANGES.add(Range.closed(75, MAX));
    }

    public Arrow(double lat, double lon, double v) {
        int velocity = getSymbol(2 * v);
        //initShape_5_10(lat, lon);
       
        switch (velocity) {
            case 0:
                initShape_0_5(lat, lon);
            case 1:
                initShape_5_10(lat, lon);
                break;
                /*
            case 2:
                initShape_10_15(lat, lon);
                break;
             */
            case 3:
                initShape_15_20(lat, lon);
                break;
               
            case 4:
                initShape_20_25(lat, lon);
                break;
               
            case 5:
                initShape_25_30(lat, lon);
                break;
            case 6:
                initShape_30_35(lat, lon);
                break;
            case 7:
                initShape_10_15(lat, lon);
                break;
                
            case 8:
                initShape_35_40(lat, lon);
                break;
            case 9:
                initShape_40_45(lat, lon);
            case 10:
                initShape_45_60(lat, lon);
            case 11:
                initShape_60_75(lat, lon);
                break;
            case 12:
                initShape_75(lat, lon);
            default:
                initShape_0_5(lat, lon);
        }

        setRotation(90.0);
    }

    private int getSymbol(double data) {
        int d = (int) data;
        for (int i = 0; i < RANGES.size(); i++) {
            if (RANGES.get(i).contains(d)) {
                return i;
            }
        }
        return MAX;
    }

    private ShapeAttributes makeAttributes(Material material, boolean isDrawInterior) {
        ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(material);
        pathAttrs.setOutlineWidth(2);
        pathAttrs.setDrawInterior(isDrawInterior);
        pathAttrs.setInteriorMaterial(material);

        return pathAttrs;
    }

    private List<Position> makePositionList(double[] src) {
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 1000);
        }
        return Arrays.asList(array);
    }

    private void initShape_0_5(double latitude, double longitude) {
        double[] dataShape = new double[8];
        dataShape[0] = longitude + X;
        dataShape[1] = latitude + X;
        
        dataShape[2] = longitude - X;
        dataShape[3] = latitude + X;
        
        dataShape[4] = longitude - X;
        dataShape[5] = latitude - X;
        
        dataShape[6] = longitude + X;
        dataShape[7] = latitude - X;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLUE, false));
    }

    private void initShape_5_10(double latitude, double longitude) {
        double[] shipShape = new double[8];
        shipShape[0] = longitude + X;
        shipShape[1] = latitude;

        shipShape[2] = longitude - X;
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX;
        shipShape[5] = latitude - YY;

        shipShape[6] = longitude - X;
        shipShape[7] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLUE, false));
    }

    private void initShape_10_15(double latitude, double longitude) {

        double[] dataShape = new double[8];

        dataShape[1] = longitude + X;
        dataShape[0] = latitude;

        dataShape[3] = longitude - X;
        dataShape[2] = latitude;

        dataShape[5] = longitude - X - decX;
        dataShape[4] = latitude - Y;

        dataShape[6] = longitude - X;
        dataShape[7] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLUE, false));
    }

    private void initShape_15_20(double latitude, double longitude) {
        double[] dataShape = new double[14];
        dataShape[0] = longitude + X;
        dataShape[1] = latitude;

        dataShape[2] = longitude - X;
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX;
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X;
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;
        dataShape[9] = latitude;

        dataShape[10] = longitude - X + decX / 2;
        dataShape[11] = latitude - YY;

        dataShape[12] = longitude - X + decX;
        dataShape[13] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLUE, false));

    }

    private void initShape_20_25(double latitude, double longitude) {
        double[] dataShape = new double[14];
        dataShape[0] = longitude + X;//0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X;//1
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX;//2
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X;//3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;//4
        dataShape[9] = latitude;

        dataShape[10] = longitude - X;//5
        dataShape[11] = latitude - Y;

        dataShape[12] = longitude - X + decX;//6
        dataShape[13] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_25_30(double latitude, double longitude) {
        double[] dataShape = new double[20];
        dataShape[0] = longitude + X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X; //1
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX; //2
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;//4
        dataShape[9] = latitude;

        dataShape[10] = longitude - X;//5
        dataShape[11] = latitude - Y;

        dataShape[12] = longitude - X + decX;//6
        dataShape[13] = latitude;

        dataShape[14] = longitude - X + 2 * decX; //7
        dataShape[15] = latitude;

        dataShape[16] = longitude - X + 1.5 * decX;  //8
        dataShape[17] = latitude - YY;

        dataShape[18] = longitude - X + 2 * decX;  //9
        dataShape[19] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_30_35(double latitude, double longitude) {
        double[] dataShape = new double[20];
        dataShape[0] = longitude + X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X; //1
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX; //2
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;//4
        dataShape[9] = latitude;

        dataShape[10] = longitude - X;//5
        dataShape[11] = latitude - Y;

        dataShape[12] = longitude - X + decX;//6
        dataShape[13] = latitude;

        dataShape[14] = longitude - X + 2 * decX; //7
        dataShape[15] = latitude;

        dataShape[16] = longitude - X + decX;  //8
        dataShape[17] = latitude - Y;

        dataShape[18] = longitude - X + 2 * decX;  //9
        dataShape[19] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_35_40(double latitude, double longitude) {
        double[] dataShape = new double[26];
        dataShape[0] = longitude + X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X; //1
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX; //2
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;//4
        dataShape[9] = latitude;

        dataShape[10] = longitude - X;//5
        dataShape[11] = latitude - Y;

        dataShape[12] = longitude - X + decX;//6
        dataShape[13] = latitude;

        dataShape[14] = longitude - X + 2 * decX; //7
        dataShape[15] = latitude;

        dataShape[16] = longitude - X + decX;  //8
        dataShape[17] = latitude - Y;

        dataShape[18] = longitude - X + 2 * decX;  //9
        dataShape[19] = latitude;

        dataShape[20] = longitude - X + 3 * decX;  //10
        dataShape[21] = latitude;

        dataShape[22] = longitude - X + 2.5 * decX;  //11
        dataShape[23] = latitude - YY;

        dataShape[24] = longitude - X + 3 * decX;  //12
        dataShape[25] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_40_45(double latitude, double longitude) {
        double[] dataShape = new double[26];
        dataShape[0] = longitude + X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X; //1
        dataShape[3] = latitude;

        dataShape[4] = longitude - X - decX; //2
        dataShape[5] = latitude - Y;

        dataShape[6] = longitude - X; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + decX;//4
        dataShape[9] = latitude;

        dataShape[10] = longitude - X;//5
        dataShape[11] = latitude - Y;

        dataShape[12] = longitude - X + decX;//6
        dataShape[13] = latitude;

        dataShape[14] = longitude - X + 2 * decX; //7
        dataShape[15] = latitude;

        dataShape[16] = longitude - X + decX;  //8
        dataShape[17] = latitude - Y;

        dataShape[18] = longitude - X + 2 * decX;  //9
        dataShape[19] = latitude;

        dataShape[20] = longitude - X + 3 * decX;  //10
        dataShape[21] = latitude;

        dataShape[22] = longitude - X + 2 * decX;  //11
        dataShape[23] = latitude - Y;

        dataShape[24] = longitude - X + 3 * decX;  //12
        dataShape[25] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_45_60(double latitude, double longitude) {
        double[] dataShape = new double[8];

        dataShape[0] = longitude - X; //1
        dataShape[1] = latitude;

        dataShape[2] = longitude - X + decX; //2
        dataShape[3] = latitude - Y;

        dataShape[4] = longitude - X + 4 * decX; //3
        dataShape[5] = latitude;

        dataShape[6] = longitude + X; //0
        dataShape[7] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.RED, true));
    }

    private void initShape_60_75(double latitude, double longitude) {
        double[] dataShape = new double[14];

        dataShape[0] = longitude - X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X + decX; //1
        dataShape[3] = latitude - Y;

        dataShape[4] = longitude - X + 4 * decX; //2
        dataShape[5] = latitude;

        dataShape[6] = longitude - X + 5 * decX;; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + 2 * decX; //4
        dataShape[9] = latitude - Y;

        dataShape[10] = longitude - X + 5 * decX; //5
        dataShape[11] = latitude;

        dataShape[12] = longitude + X; //6
        dataShape[13] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.RED, true));
    }

    private void initShape_75(double latitude, double longitude) {
        double[] dataShape = new double[20];

        dataShape[0] = longitude - X; //0
        dataShape[1] = latitude;

        dataShape[2] = longitude - X + decX; //1
        dataShape[3] = latitude - Y;

        dataShape[4] = longitude - X + 4 * decX; //2
        dataShape[5] = latitude;

        dataShape[6] = longitude - X + 5 * decX;; //3
        dataShape[7] = latitude;

        dataShape[8] = longitude - X + 2 * decX; //4
        dataShape[9] = latitude - Y;

        dataShape[10] = longitude - X + 5 * decX; //5
        dataShape[11] = latitude;

        dataShape[12] = longitude - X + 6 * decX; //5
        dataShape[13] = latitude;

        dataShape[14] = longitude - X + 3 * decX; //4
        dataShape[15] = latitude - Y;

        dataShape[16] = longitude - X + 6 * decX; //5
        dataShape[17] = latitude;

        dataShape[18] = longitude + X; //6
        dataShape[19] = latitude;

        setOuterBoundary(makePositionList(dataShape));
        setAttributes(makeAttributes(Material.RED, true));
    }
}
