package bzh.terrevirtuelle.navisu.domain.bathymetry.view;

import com.google.common.collect.Range;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SHOM_LOW_BATHYMETRY_CLUT {

    static final private List<Range> ranges;
    static final public double MAX = 10000.0;

    static {
        ranges = new ArrayList<>();
        ranges.add(Range.closedOpen(0.0, 2.5));
        ranges.add(Range.closedOpen(2.5, 5.0));
        ranges.add(Range.closedOpen(5.0, 7.5));
        ranges.add(Range.closedOpen(7.5, 10.0));
        ranges.add(Range.closedOpen(10.0, 20.0));
        ranges.add(Range.closedOpen(20.0, 30.0));
        ranges.add(Range.closedOpen(30.0, 40.0));
        ranges.add(Range.closed(40.0, 50.0));
        ranges.add(Range.closed(50.0, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, new Color(238, 105, 112));
            put(1, new Color(242, 84, 104));
            put(2, new Color(238, 52, 83));
            put(3, new Color(236, 21, 58));
            put(4, new Color(211, 51, 67));
            put(5, new Color(244, 42, 55));
            put(6, new Color(206, 138, 72));
            put(7, new Color(209, 184, 69));
            put(8, new Color(248, 255, 25));
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data) {
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(data)) {
                return SHOM_LOW_BATHYMETRY_CLUT.ATT.get(i);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_LOW_BATHYMETRY_CLUT.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
