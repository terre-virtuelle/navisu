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
        ranges.add(Range.closedOpen(-14.0, -9.0));
        ranges.add(Range.closedOpen(0.0, 1.0));
        ranges.add(Range.closedOpen(1.0, 3.0));
        ranges.add(Range.closedOpen(3.0, 5.0));
        ranges.add(Range.closedOpen(5.0, 12.0));
        ranges.add(Range.closedOpen(12.0, 20.0));
        ranges.add(Range.closedOpen(20.0, 30.0));
        ranges.add(Range.closedOpen(30.0, 50.0));
        ranges.add(Range.closedOpen(50.0, 100.0));
        ranges.add(Range.closedOpen(100.0, 200.0));
        ranges.add(Range.closedOpen(200.0, 10000.0));
        ranges.add(Range.closed(MAX, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, new Color(151, 199, 0));
            put(1, new Color(210, 217, 231));
            put(2, new Color(193, 210, 246));
            put(3, new Color(129, 195, 226));
            put(4, new Color(159, 215, 247));
            put(5, new Color(129, 195, 226));
            put(6, new Color(91, 175, 247));
            put(7, new Color(40, 69, 120));
            put(8, new Color(30, 53, 100));
            put(9, new Color(17, 30, 56));
            put(10, new Color(129, 195, 226));
            put(11, new Color(200, 0, 0, 0));
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
