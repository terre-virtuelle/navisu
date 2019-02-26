package bzh.terrevirtuelle.navisu.domain.bathymetry.view;

import com.google.common.collect.Range;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SHOM_IGN_LITTO3D_CLUT {

    static final private List<Range> RANGES;
    static final public double MAX = 10000.0;

    static {
        RANGES = new ArrayList<>();
        RANGES.add(Range.closedOpen(-100.0, -50.0));
        RANGES.add(Range.closedOpen(-50.0, -25.0));
        RANGES.add(Range.closedOpen(-25.0, -18.0));
        RANGES.add(Range.closedOpen(-18.0, -10.0));
        RANGES.add(Range.closedOpen(-10.0, -6.0));
        RANGES.add(Range.closedOpen(-6.0, -4.0));
        RANGES.add(Range.closedOpen(-4.0, -2.0));
        RANGES.add(Range.closedOpen(-2.0, 0.0));
        RANGES.add(Range.closedOpen(0.0, 1.0));
        RANGES.add(Range.closedOpen(1.0, 2.0));
        RANGES.add(Range.closedOpen(2.0, 3.0));
        RANGES.add(Range.closedOpen(3.0, 4.0));
        RANGES.add(Range.closedOpen(4.0, 10.0));
        RANGES.add(Range.closedOpen(10.0, 20.0));
        RANGES.add(Range.closedOpen(20.0, 30.0));
        RANGES.add(Range.closedOpen(30.0, 40.0));
        RANGES.add(Range.closedOpen(40.0, 50.0));
        RANGES.add(Range.closedOpen(50.0, 60.0));
        RANGES.add(Range.closed(60.0, MAX));
    }
    private static final Map<Integer, Color> ATT = Collections.unmodifiableMap(new HashMap<Integer, Color>() {
        {
            /*
            m   Hex

-50	000238
-25	000045
-18	040482
-10	0c13ad
-6	1118f6
-4	167cff
-2	04ffff
0	3f3f3f		
1	bfbfbf
2	ffff06
3	ae9a1b
4	16ff01
10	20d514
20	11be0f
30	Oc7f19
40	035b04
50	004200
60	004600
            */
            put(0, new Color(Integer.decode("#000238")));
            put(1, new Color(Integer.decode("#000045")));
            put(2, new Color(Integer.decode("#e15d22")));
            put(3, new Color(Integer.decode("#e37226")));
            put(4, new Color(Integer.decode("#e6882a")));
            put(5, new Color(Integer.decode("#eeb534")));
            put(6, new Color(Integer.decode("#f3cd3a")));
            put(7, new Color(Integer.decode("#e7fd4f")));
            put(8, new Color(Integer.decode("#d8fc4b")));
            put(9, new Color(Integer.decode("#bafc46")));
            put(10, new Color(Integer.decode("#98f741")));
            put(11, new Color(Integer.decode("#7cdf42")));
            put(12, new Color(Integer.decode("#8befca")));
            put(13, new Color(Integer.decode("#7cd9fc")));
            put(14, new Color(Integer.decode("#3561f9")));
            put(15, new Color(Integer.decode("#1930ff")));
            put(16, new Color(Integer.decode("#2132cc")));
            put(17, new Color(Integer.decode("#1d2cb2")));
            put(18, new Color(Integer.decode("#1a2799")));
        }
    });

    @SuppressWarnings("unchecked")
    public static Color getColor(double data) {
        for (int i = 0; i < RANGES.size(); i++) {
            if (RANGES.get(i).contains(data)) {
                return SHOM_IGN_LITTO3D_CLUT.ATT.get(i);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<Integer, Color>> entries = SHOM_IGN_LITTO3D_CLUT.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
