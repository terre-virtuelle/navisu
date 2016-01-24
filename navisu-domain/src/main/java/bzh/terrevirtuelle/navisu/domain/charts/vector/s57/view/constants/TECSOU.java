package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TECSOU {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "found by echo-sounder");
            put("2", "found by side scan sonar");
            put("3", "found by multi-beam");
            put("4", "found by diver");
            put("5", "found by lead-line");
            put("6", "swept by wire-drag");
            put("7", "found by laser");
            put("8", "swept by vertical acoustic system");
            put("9", "found by electromagnetic sensor");
            put("10", "photogrammetry");
            put("11", "satellite imagery");
            put("12", "found by levelling");
            put("13", "swept by side-scan sonar");
            put("14", "computer generated");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = TECSOU.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
