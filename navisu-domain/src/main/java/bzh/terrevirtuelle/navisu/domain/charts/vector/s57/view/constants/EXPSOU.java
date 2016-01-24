package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EXPSOU {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "within the range of depth of the surrounding depth area");
            put("2", "shoaler than the range of depth of the surrounding depth area");
            put("3", "deeper than the range of depth of the surrounding depth area");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = EXPSOU.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
