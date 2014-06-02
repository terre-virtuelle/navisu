package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Debug {

    private Debug() {
    }

    public final static void DebugTokenMap(Map<String, List<String>> map) {

        Iterator<String> it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            List<String> values = map.get(key);

            System.out.println(key);
            for (String s : values) {
                System.out.println("\t " + s);
            }
        }
    }
}
