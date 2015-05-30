/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.view;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.Arrays;
import java.util.List;

/**
 * NaVisu
 *
 * @date 29 mai 2015
 * @author Serge Morvan
 */
public abstract class Arrow
        extends Polygon {

    List<Position> positions;

    public Arrow() {
    }

    public Arrow(double lat, double lon) {
        positions = makePositionList(initShape(lat, lon));
        setAttributes(makeAttributes());
        setOuterBoundary(positions);
    }

    public void init(double lat, double lon) {
        positions = makePositionList(initShape(lat, lon));
        setAttributes(makeAttributes());
        setOuterBoundary(positions);

    }

    protected ShapeAttributes makeAttributes() {
        ShapeAttributes pathAttrs = new BasicShapeAttributes();
        pathAttrs.setOutlineMaterial(Material.BLACK);
        pathAttrs.setOutlineWidth(2);
        pathAttrs.setDrawInterior(false);
        return pathAttrs;
    }

    protected abstract double[] initShape(double latitude, double longitude);

    protected final List<Position> makePositionList(double[] src) {
        int numCoords = src.length / 2;
        Position[] array = new Position[numCoords];

        for (int i = 0; i < numCoords; i++) {
            double lonDegrees = src[2 * i];
            double latDegrees = src[2 * i + 1];
            array[i] = Position.fromDegrees(latDegrees, lonDegrees, 1500);
        }
        return Arrays.asList(array);
    }

}
