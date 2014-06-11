package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryOfLateralMark {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "Port hand");
            put("2", "Starboard hand");
            put("3", "Port hand preferred channel");
            put("4", "Starboard hand preferred channel");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = CategoryOfLateralMark.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
