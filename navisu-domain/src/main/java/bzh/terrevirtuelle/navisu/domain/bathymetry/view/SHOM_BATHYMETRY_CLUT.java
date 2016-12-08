package bzh.terrevirtuelle.navisu.domain.bathymetry.view;

import com.google.common.collect.Range;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SHOM_BATHYMETRY_CLUT {

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
            put(0, new Color(204, 192, 2));
            put(1, new Color(255, 0, 0));
            put(2, new Color(255, 45, 0));
            put(3, new Color(255, 91, 0));
            put(4, new Color(255, 136, 0));
            put(5, new Color(255, 181, 0));
            put(6, new Color(255, 227, 0));
            put(7, new Color(238, 255, 0));
            put(8, new Color(193, 255, 0));
            put(9, new Color(147, 255, 0));
            put(10, new Color(102, 255, 0));
            put(11, new Color(0, 0, 0, 0));
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data) {
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(data)) {
                return SHOM_BATHYMETRY_CLUT.ATT.get(i);
            }
        }
        return null;
    }
@SuppressWarnings("unchecked")
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_BATHYMETRY_CLUT.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
