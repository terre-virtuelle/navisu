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

    static final private List<Range> ranges;
    static final public double MAX = 10000.0;

    static {
        ranges = new ArrayList<>();
        ranges.add(Range.closedOpen(-10.0, 0.0));
        ranges.add(Range.closedOpen(0.0, 19.94));
        ranges.add(Range.closedOpen(19.94, 39.87));
        ranges.add(Range.closedOpen(39.87, 59.81));
        ranges.add(Range.closedOpen(59.81, 79.74));
        ranges.add(Range.closedOpen(79.74, 99.88));
        ranges.add(Range.closedOpen(99.88, 119.62));
        ranges.add(Range.closedOpen(119.62, 139.55));
        ranges.add(Range.closedOpen(139.55, 159.49));
        ranges.add(Range.closedOpen(159.49, 179.42));
        ranges.add(Range.closedOpen(179.42, 10000.0));  
        ranges.add(Range.closed(MAX, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0,  Color.rgb(204, 192, 2));
            put(1,  Color.rgb(255, 0, 0));
            put(2,  Color.rgb(255, 45, 0));
            put(3,  Color.rgb(255, 91, 0));
            put(4,  Color.rgb(255, 136, 0));
            put(5,  Color.rgb(255, 181, 0));
            put(6,  Color.rgb(255, 227, 0));
            put(7,  Color.rgb(238, 255, 0));
            put(8,  Color.rgb(193, 255, 0));
            put(9,  Color.rgb(147, 255, 0));
            put(10,  Color.rgb(102, 255, 0));
            put(11,  Color.rgb(0, 0, 0, 0));
        }
    });

    public static Color getColor(double data) {
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(data)) {
                return SHOM_BATHYMETRY_CLUT_JAVA_FX.ATT.get(i);
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
