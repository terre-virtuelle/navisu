/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

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
    static final private List<Range> ranges;
    static final public int MAX = 200;

    static {
        ranges = new ArrayList<>();
        ranges.add(Range.closedOpen(0, 5));
        ranges.add(Range.closedOpen(5, 10));
        ranges.add(Range.closedOpen(10, 15));
        ranges.add(Range.closedOpen(15, 20));
        ranges.add(Range.closedOpen(20, 25));
        ranges.add(Range.closedOpen(25, 30));
        ranges.add(Range.closedOpen(30, 35));
        ranges.add(Range.closedOpen(35, 40));
        ranges.add(Range.closedOpen(40, 45));
        ranges.add(Range.closedOpen(45, 60));
        ranges.add(Range.closedOpen(60, 75));
        ranges.add(Range.closed(75, MAX));
    }

    public Arrow(double lat, double lon, double v) {

        int velocity = getSymbol(v);
        System.out.println("v " + velocity);
        switch (velocity) {
            case 0:
            case 1:
                initShape_5_10(lat, lon);
                break;
            case 2:
                initShape_10_15(lat, lon);
                break;
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
        }
    }

    private int getSymbol(double data) {
        int d = (int) data;
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(d)) {
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
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 1500);
        }
        return Arrays.asList(array);
    }

    private void initShape_0_5(double latitude, double longitude) {

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

        double[] shipShape = new double[8];
        shipShape[0] = longitude + X;
        shipShape[1] = latitude;

        shipShape[2] = longitude - X;
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX;
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X;
        shipShape[7] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLUE, false));
    }

    private void initShape_15_20(double latitude, double longitude) {
        double[] shipShape = new double[14];
        shipShape[0] = longitude + X;
        shipShape[1] = latitude;

        shipShape[2] = longitude - X;
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX;
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X;
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;
        shipShape[9] = latitude;

        shipShape[10] = longitude - X + decX / 2;
        shipShape[11] = latitude - YY;

        shipShape[12] = longitude - X + decX;
        shipShape[13] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLUE, false));

    }

    private void initShape_20_25(double latitude, double longitude) {
        double[] shipShape = new double[14];
        shipShape[0] = longitude + X;//0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X;//1
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX;//2
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X;//3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;//4
        shipShape[9] = latitude;

        shipShape[10] = longitude - X;//5
        shipShape[11] = latitude - Y;

        shipShape[12] = longitude - X + decX;//6
        shipShape[13] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_25_30(double latitude, double longitude) {
        double[] shipShape = new double[20];
        shipShape[0] = longitude + X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X; //1
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX; //2
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;//4
        shipShape[9] = latitude;

        shipShape[10] = longitude - X;//5
        shipShape[11] = latitude - Y;

        shipShape[12] = longitude - X + decX;//6
        shipShape[13] = latitude;

        shipShape[14] = longitude - X + 2 * decX; //7
        shipShape[15] = latitude;

        shipShape[16] = longitude - X + 1.5 * decX;  //8
        shipShape[17] = latitude - YY;

        shipShape[18] = longitude - X + 2 * decX;  //9
        shipShape[19] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_30_35(double latitude, double longitude) {
        double[] shipShape = new double[20];
        shipShape[0] = longitude + X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X; //1
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX; //2
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;//4
        shipShape[9] = latitude;

        shipShape[10] = longitude - X;//5
        shipShape[11] = latitude - Y;

        shipShape[12] = longitude - X + decX;//6
        shipShape[13] = latitude;

        shipShape[14] = longitude - X + 2 * decX; //7
        shipShape[15] = latitude;

        shipShape[16] = longitude - X + decX;  //8
        shipShape[17] = latitude - Y;

        shipShape[18] = longitude - X + 2 * decX;  //9
        shipShape[19] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_35_40(double latitude, double longitude) {
        double[] shipShape = new double[26];
        shipShape[0] = longitude + X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X; //1
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX; //2
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;//4
        shipShape[9] = latitude;

        shipShape[10] = longitude - X;//5
        shipShape[11] = latitude - Y;

        shipShape[12] = longitude - X + decX;//6
        shipShape[13] = latitude;

        shipShape[14] = longitude - X + 2 * decX; //7
        shipShape[15] = latitude;

        shipShape[16] = longitude - X + decX;  //8
        shipShape[17] = latitude - Y;

        shipShape[18] = longitude - X + 2 * decX;  //9
        shipShape[19] = latitude;

        shipShape[20] = longitude - X + 3 * decX;  //10
        shipShape[21] = latitude;

        shipShape[22] = longitude - X + 2.5 * decX;  //11
        shipShape[23] = latitude - YY;

        shipShape[24] = longitude - X + 3 * decX;  //12
        shipShape[25] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_40_45(double latitude, double longitude) {
        double[] shipShape = new double[26];
        shipShape[0] = longitude + X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X; //1
        shipShape[3] = latitude;

        shipShape[4] = longitude - X - decX; //2
        shipShape[5] = latitude - Y;

        shipShape[6] = longitude - X; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + decX;//4
        shipShape[9] = latitude;

        shipShape[10] = longitude - X;//5
        shipShape[11] = latitude - Y;

        shipShape[12] = longitude - X + decX;//6
        shipShape[13] = latitude;

        shipShape[14] = longitude - X + 2 * decX; //7
        shipShape[15] = latitude;

        shipShape[16] = longitude - X + decX;  //8
        shipShape[17] = latitude - Y;

        shipShape[18] = longitude - X + 2 * decX;  //9
        shipShape[19] = latitude;

        shipShape[20] = longitude - X + 3 * decX;  //10
        shipShape[21] = latitude;

        shipShape[22] = longitude - X + 2 * decX;  //11
        shipShape[23] = latitude - Y;

        shipShape[24] = longitude - X + 3 * decX;  //12
        shipShape[25] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.BLACK, false));
    }

    private void initShape_45_60(double latitude, double longitude) {
        double[] shipShape = new double[8];

        shipShape[0] = longitude - X; //1
        shipShape[1] = latitude;

        shipShape[2] = longitude - X + decX; //2
        shipShape[3] = latitude - Y;

        shipShape[4] = longitude - X + 4 * decX; //3
        shipShape[5] = latitude;

        shipShape[6] = longitude + X; //0
        shipShape[7] = latitude;

        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.RED, true));
    }

    private void initShape_60_75(double latitude, double longitude) {
        double[] shipShape = new double[14];

        shipShape[0] = longitude - X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X + decX; //1
        shipShape[3] = latitude - Y;

        shipShape[4] = longitude - X + 4 * decX; //2
        shipShape[5] = latitude;

        shipShape[6] = longitude - X + 5 * decX;; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + 2 * decX; //4
        shipShape[9] = latitude - Y;

        shipShape[10] = longitude - X + 5 * decX; //5
        shipShape[11] = latitude;

        shipShape[12] = longitude + X; //6
        shipShape[13] = latitude;
        
        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.RED, true));
    }

    private void initShape_75(double latitude, double longitude) {
        double[] shipShape = new double[20];

        shipShape[0] = longitude - X; //0
        shipShape[1] = latitude;

        shipShape[2] = longitude - X + decX; //1
        shipShape[3] = latitude - Y;

        shipShape[4] = longitude - X + 4 * decX; //2
        shipShape[5] = latitude;

        shipShape[6] = longitude - X + 5 * decX;; //3
        shipShape[7] = latitude;

        shipShape[8] = longitude - X + 2 * decX; //4
        shipShape[9] = latitude - Y;

        shipShape[10] = longitude - X + 5 * decX; //5
        shipShape[11] = latitude;

        shipShape[12] = longitude - X + 6 * decX; //5
        shipShape[13] = latitude;

        shipShape[14] = longitude - X + 3 * decX; //4
        shipShape[15] = latitude - Y;

        shipShape[16] = longitude - X + 6 * decX; //5
        shipShape[17] = latitude;

        shipShape[18] = longitude + X; //6
        shipShape[19] = latitude;
        
        setOuterBoundary(makePositionList(shipShape));
        setAttributes(makeAttributes(Material.RED, true));
    }
}
