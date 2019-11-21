package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NATSUR {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "mud");
            put("2", "clay");
            put("3", "silt");
            put("4", "sand");
            put("5", "stone");
            put("6", "gravel");
            put("7", "pebbles");
            put("8", "cobbles");
            put("9", "rock");
            put("10", "");
            put("11", "lava");
            put("12", "");
            put("13", "");
            put("14", "coral");
            put("15", "");
            put("16", "");
            put("17", "shells");
            put("18", "boulder");

        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = NATSUR.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
