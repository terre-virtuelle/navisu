package bzh.terrevirtuelle.navisu.domain.currents.view;

import com.google.common.collect.Range;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SHOM_CURRENTS_CLUT {

    static final private List<Range> ranges;
    static final public double MAX = 10.0;

    static {
        ranges = new ArrayList<>();
        ranges.add(Range.closedOpen(0.0, 0.25));
        ranges.add(Range.closedOpen(0.25, 0.5));
        ranges.add(Range.closedOpen(0.5, 0.75));
        ranges.add(Range.closedOpen(0.75, 1.0));
        ranges.add(Range.closedOpen(1.0, 1.25));
        ranges.add(Range.closedOpen(1.25, 1.5));
        ranges.add(Range.closedOpen(1.5, 1.75));
        ranges.add(Range.closedOpen(1.75, 2.0));
        ranges.add(Range.closedOpen(2.0, 2.25));
        ranges.add(Range.closedOpen(2.25, 2.5));
        ranges.add(Range.closedOpen(2.5, 2.75));
        ranges.add(Range.closedOpen(2.75, 3.0));
        ranges.add(Range.closedOpen(3.0, 3.25));
        ranges.add(Range.closedOpen(3.25, 3.5));
        ranges.add(Range.closedOpen(3.5, 3.75));
        ranges.add(Range.closedOpen(3.75, 4.0));
        ranges.add(Range.closedOpen(4.0, 4.25));
        ranges.add(Range.closedOpen(4.25, 4.5));
        ranges.add(Range.closedOpen(4.5, 4.75));
        ranges.add(Range.closed(4.75, 5.0));
        ranges.add(Range.closed(MAX, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, new Color(10, 0, 121));
            put(1, new Color(0, 0, 204));
            put(2, new Color(0, 0, 255));
            put(3, new Color(0, 51, 255));
            put(4, new Color(0, 102, 255));
            put(5, new Color(0, 153, 255));
            put(6, new Color(0, 204, 255));
            put(7, new Color(0, 255, 255));
            put(8, new Color(51, 255, 229));
            put(9, new Color(102, 255, 204));
            put(10, new Color(153, 255, 178));
            put(11, new Color(204, 255, 153));
            put(12, new Color(255, 255, 127));
            put(13, new Color(255, 204, 102));
            put(14, new Color(255, 153, 76));
            put(15, new Color(255, 102, 51));
            put(16, new Color(255, 51, 25));
            put(17, new Color(255, 0, 0));
            put(18, new Color(204, 0, 0));
            put(19, new Color(153, 0, 0));
            put(20, new Color(0, 0, 0, 0));
        }
    });
@SuppressWarnings("unchecked")
    public static Color getColor(double speed) {
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(speed)) {
                return SHOM_CURRENTS_CLUT.ATT.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_CURRENTS_CLUT.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
