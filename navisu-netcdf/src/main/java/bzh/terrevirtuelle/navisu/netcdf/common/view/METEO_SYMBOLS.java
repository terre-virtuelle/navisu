package bzh.terrevirtuelle.navisu.netcdf.common.view;

import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class METEO_SYMBOLS {

    static final private List<Range> ranges;
    static final public int MAX = 200;

    static {
        ranges = new ArrayList<>();
        ranges.add(Range.closedOpen(0, 5));
        ranges.add(Range.closedOpen(5, 10));
        ranges.add(Range.closedOpen(10, 15));
        ranges.add(Range.closedOpen(15, 20));
        ranges.add(Range.closedOpen(20, 25));
        ranges.add(Range.closedOpen(25, 30));
        ranges.add(Range.closedOpen(30, 35));
        ranges.add(Range.closedOpen(35, 40));
        ranges.add(Range.closedOpen(40, 45));
        ranges.add(Range.closedOpen(45, 60));
        ranges.add(Range.closedOpen(60, 75));
        ranges.add(Range.closed(75, MAX));
    }
    private static final Map<Integer,  Class<? extends Arrow>> SYMB
            = Collections.unmodifiableMap(new HashMap<Integer, Class<? extends Arrow>>() {
                {
                    put(0, Arrow_5_10.class);
                    put(1, Arrow_10_15.class);
                    put(2, Arrow_20_25.class);
                    put(3, Arrow_25_30.class);
                    put(4, Arrow_30_35.class);
                    put(5, Arrow_35_40.class);
                    put(6, Arrow_40_45.class);
                    put(7, Arrow_45_60.class);
                    put(8, Arrow_60_75.class);
                    put(9, Arrow_75.class);
                }
            }
            );

    public static Class<? extends Arrow> getSymbol(double data) {
        int d = (int)data;
        System.out.println("d : "+d);
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i).contains(d)) {
                return METEO_SYMBOLS.SYMB.get(i);
            }
        }
        return null;
    }

}
