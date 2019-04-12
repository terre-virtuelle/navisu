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

-50	000238 0 2 56
-25	000045 0 0 69
-18	040482 4 4 130
-10	0c13ad 12 19 173
-6	1118f6 17 24 246
-4	167cff 22 124 255
-2	04ffff 4 255 255
0	3f3f3f 63 63 63	
1	bfbfbf 191 191 191
2	ffff06 255 255 6
3	ae9a1b 174 154 27
4	16ff01 22 255 1
10	20d514 32 213 20
20	11be0f 17 190 15
30	Oc7f19 0 127 25
40	035b04 3 91 4
50	004200 0 66 0
80	004600 0 70 0
            */
            put(0, new Color(Integer.decode("#000238")));
            put(1, new Color(Integer.decode("#000045")));
            put(2, new Color(Integer.decode("#040482")));
            put(3, new Color(Integer.decode("#0c13ad")));
            put(4, new Color(Integer.decode("#1118f6")));
            put(5, new Color(Integer.decode("#167cff")));
            put(6, new Color(Integer.decode("#04ffff")));
            put(7, new Color(Integer.decode("#3f3f3f")));
            put(8, new Color(Integer.decode("#bfbfbf")));
            put(9, new Color(Integer.decode("#ffff06")));
            put(10, new Color(Integer.decode("#ae9a1b")));
            put(11, new Color(Integer.decode("#16ff01")));
            put(12, new Color(Integer.decode("#20d514")));
            put(13, new Color(Integer.decode("#11be0f")));
            put(14, new Color(Integer.decode("#Oc7f19")));
            put(15, new Color(Integer.decode("#035b04")));
            put(16, new Color(Integer.decode("#004200")));
            put(17, new Color(Integer.decode("#004600")));
            put(18, new Color(Integer.decode("#004600")));
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
