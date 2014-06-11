package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.attributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryOfCardinalMark {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "north");
            put("2", "east");
            put("3", "south");
            put("4", "west");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = CategoryOfCardinalMark.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
