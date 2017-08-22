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
        ranges.add(Range.closedOpen(0.0,16.0));
        ranges.add(Range.closedOpen(16.0,19.0));
        ranges.add(Range.closedOpen(19.0,23.0));
        ranges.add(Range.closedOpen(23.0,27.0));
        ranges.add(Range.closedOpen(27.0,29.0));
        ranges.add(Range.closedOpen(29.0,33.0));
        ranges.add(Range.closedOpen(33.0,43.0));
        ranges.add(Range.closed(43.0, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, new Color(25,255,20));
            put(1, new Color(130,255,16));
            put(2, new Color(239,24,0));
            put(3, new Color(20,170,255));
            put(4, new Color(16,235,61));
            put(5, new Color(53,255,194));
            put(6, new Color(0,24,255));
            put(7, new Color(0,24,255));
          
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
