package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QUASOU {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "depth known");
            put("2", "depth unknown");
            put("3", "doubtful sounding");
            put("4", "unreliable sounding");
            put("5", "no bottom found at value shown");
            put("6", "least depth known ");
            put("7", "least depth unknown, safe clearance at value shown");
            put("8", "value reported (not surveyed)");
            put("9", "value reported (not confirmed) ");
            put("10", "maintained depth");
            put("11", "not regularly maintained");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = QUASOU.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
