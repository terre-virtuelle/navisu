package bzh.terrevirtuelle.navisu.domain.bathymetry.view;

import com.google.common.collect.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.paint.Color;

public class SHOM_BATHYMETRY_CLUT_JAVA_FX {

    static final private List<Range> largeRanges;
    static final private List<Range> smallRanges;
    static final public double MAX = 10000.0;

    static {
        largeRanges = new ArrayList<>();
        largeRanges.add(Range.closedOpen(-10.0, 0.0));
        largeRanges.add(Range.closedOpen(0.0, 19.94));
        largeRanges.add(Range.closedOpen(19.94, 39.87));
        largeRanges.add(Range.closedOpen(39.87, 59.81));
        largeRanges.add(Range.closedOpen(59.81, 79.74));
        largeRanges.add(Range.closedOpen(79.74, 99.88));
        largeRanges.add(Range.closedOpen(99.88, 119.62));
        largeRanges.add(Range.closedOpen(119.62, 139.55));
        largeRanges.add(Range.closedOpen(139.55, 159.49));
        largeRanges.add(Range.closedOpen(159.49, 179.42));
        largeRanges.add(Range.closedOpen(179.42, 10000.0));
        largeRanges.add(Range.closed(MAX, MAX));
        smallRanges = new ArrayList<>();
        smallRanges.add(Range.closedOpen(-10.0, 0.0));
        smallRanges.add(Range.closedOpen(0.0, 2.0));
        smallRanges.add(Range.closedOpen(2.0, 4.0));
        smallRanges.add(Range.closedOpen(4.0, 8.0));
        smallRanges.add(Range.closedOpen(8.0, 16.0));
        smallRanges.add(Range.closedOpen(16.0, 32.0));
        smallRanges.add(Range.closedOpen(32.0, 64.0));
        smallRanges.add(Range.closedOpen(64.0, 128.0));
        smallRanges.add(Range.closedOpen(128.0, 256.0));
        smallRanges.add(Range.closedOpen(256.0, 512.0));
        smallRanges.add(Range.closedOpen(512.0, 10000.0));
        smallRanges.add(Range.closed(MAX, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, Color.rgb(255, 22, 00));
            put(1, Color.rgb(255, 84, 0));
            put(2, Color.rgb(255, 133, 0));
            put(3, Color.rgb(255, 233, 0));
            put(4, Color.rgb(209, 255, 0));
            //  put(5, Color.rgb(92, 255, 0));
            put(5, Color.rgb(0, 255, 32));
            //  put(7, Color.rgb(0, 255, 223));
            put(6, Color.rgb(0, 11, 255));
            put(7, Color.rgb(0, 255, 255));
            put(8, Color.rgb(0, 0, 102));
            put(9, Color.rgb(0, 0, 200));
            put(10, Color.rgb(0, 0, 255));
            put(11, Color.rgb(0, 0, 0));
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data, boolean large) {
        if (large == true) {
            for (int i = 0; i < largeRanges.size(); i++) {
                if (largeRanges.get(i).contains(data)) {
                    return SHOM_BATHYMETRY_CLUT_JAVA_FX.ATT.get(i);
                }
            }
        } else {
            for (int i = 0; i < largeRanges.size(); i++) {
                if (smallRanges.get(i).contains(data)) {
                    return SHOM_BATHYMETRY_CLUT_JAVA_FX.ATT.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_BATHYMETRY_CLUT_JAVA_FX.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
