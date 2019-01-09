package bzh.terrevirtuelle.navisu.domain.bathymetry.view;

import com.google.common.collect.Range;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SHOM_HOMONIM_BATHYMETRY_CLUT {

    static final private List<Range> RANGES;
    static final public double MAX = 10000.0;

    static {
        RANGES = new ArrayList<>();
        /*
        RANGES.add(Range.closedOpen(-15.0, 5.0));
        RANGES.add(Range.closedOpen(5.0, 10.0));
        RANGES.add(Range.closedOpen(10.0, 15.0));
        RANGES.add(Range.closedOpen(15.0, 20.0));
        RANGES.add(Range.closedOpen(7.0, 10.0));
        RANGES.add(Range.closedOpen(20.0, 25.0));
        RANGES.add(Range.closedOpen(25.0, 35.0));
        RANGES.add(Range.closedOpen(35.0, MAX));
        */

        RANGES.add(Range.closedOpen(-15.0, 0.0));
        RANGES.add(Range.closedOpen(0.0, 1.0));
        RANGES.add(Range.closedOpen(1.0, 3.0));
        RANGES.add(Range.closedOpen(3.0, 7.0));
        RANGES.add(Range.closedOpen(7.0, 10.0));
        RANGES.add(Range.closedOpen(10.0, 14.0));
        RANGES.add(Range.closedOpen(14.0, 19.0));
        RANGES.add(Range.closedOpen(19.0, 25.0));
        RANGES.add(Range.closed(25.0, 31.0));
        RANGES.add(Range.closedOpen(31.0, 39.0));
        RANGES.add(Range.closedOpen(39.0, 45.0));
        RANGES.add(Range.closedOpen(45.0, 53.0));
        RANGES.add(Range.closedOpen(53.0, 83.0));
        RANGES.add(Range.closed(83.0, 94.0));
        RANGES.add(Range.closedOpen(94.0, 107.0));
        RANGES.add(Range.closedOpen(107.0, 120.0));
        RANGES.add(Range.closedOpen(120.0, 149.0));
        RANGES.add(Range.closed(149.0, MAX));
        
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            
            put(0, new Color(Integer.decode("#00FFB")));
            put(1, new Color(Integer.decode("#00FF60")));
            put(2, new Color(Integer.decode("#00FF25")));
            put(3, new Color(Integer.decode("#15FF00")));
            put(4, new Color(Integer.decode("#50FF00")));
            put(5, new Color(Integer.decode("#8BFF00")));
            put(6, new Color(Integer.decode("#C6FF00")));
            put(7, new Color(Integer.decode("#FFFF00")));
            put(8, new Color(Integer.decode("#FFC400")));
            put(9, new Color(Integer.decode("#FF8900")));
            put(10, new Color(Integer.decode("#FF4E00")));
            put(11, new Color(Integer.decode("#FF1300")));
            put(12, new Color(Integer.decode("#FF033E")));
            put(13, new Color(Integer.decode("#FF00FF")));
            put(14, new Color(Integer.decode("#FF4EFF")));
            put(15, new Color(Integer.decode("#FF89FF")));
            put(16, new Color(Integer.decode("#FF89FF")));
            put(17, new Color(Integer.decode("#FFC4E7")));
             
            /*
            put(7, new Color(Integer.decode("#192EF8")));
            put(6, new Color(Integer.decode("#8FF5FE")));
            put(5, new Color(Integer.decode("#7EDF3A")));
            put(4, new Color(Integer.decode("#BAFC46")));
            put(3, new Color(Integer.decode("#FCFD49")));
            put(2, new Color(Integer.decode("#F9FD53")));
            put(1, new Color(Integer.decode("#E15D22")));
            put(0, new Color(Integer.decode("#D0321B")));
*/
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data) {
        for (int i = 0; i < RANGES.size(); i++) {
            if (RANGES.get(i).contains(data)) {
                return SHOM_HOMONIM_BATHYMETRY_CLUT.ATT.get(i);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_HOMONIM_BATHYMETRY_CLUT.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
