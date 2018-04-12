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

    static final private List<Range> RANGES;
    static final public double MAX = 10000.0;

    static {
        RANGES = new ArrayList<>();
        RANGES.add(Range.closedOpen(-15.0, 0.0));
        RANGES.add(Range.closedOpen(0.0, 2.5));
        RANGES.add(Range.closedOpen(2.5, 5.0));
        RANGES.add(Range.closedOpen(5.0, 7.5));
        RANGES.add(Range.closedOpen(7.5, 10.0));
        RANGES.add(Range.closedOpen(10.0, 20.0));
        RANGES.add(Range.closedOpen(20.0, 30.0));
        RANGES.add(Range.closedOpen(30.0, 40.0));
        RANGES.add(Range.closed(40.0, 50.0));
        RANGES.add(Range.closed(50.0, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            put(0, new Color(Integer.decode("#97C700")));
            put(1, new Color(Integer.decode("#00AAFF")));
            put(2, new Color(Integer.decode("#1CB3FF")));
            put(3, new Color(Integer.decode("#39BDFF")));
            put(4, new Color(Integer.decode("#55C6FF")));
            put(5, new Color(Integer.decode("#71D0FF")));
            put(6, new Color(Integer.decode("#8ED9FF")));
            put(7, new Color(Integer.decode("#AAE3FF")));
            put(8, new Color(Integer.decode("#C6ECFF")));
            put(9, new Color(Integer.decode("#E3F6FF")));
            put(10, new Color(Integer.decode("#FFFFFF")));
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data) {
        for (int i = 0; i < RANGES.size(); i++) {
            if (RANGES.get(i).contains(data)) {
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
