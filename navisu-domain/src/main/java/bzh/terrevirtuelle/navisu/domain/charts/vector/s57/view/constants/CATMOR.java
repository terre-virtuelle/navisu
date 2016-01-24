package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CATMOR {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "dolphin");
            put("2", "deviation dolphin");
            put("3", "bollard");
            put("4", "tie-up wall");
            put("5", "post or pile");
            put("6", "chain/wire/cable");
            put("7", "mooring buoy");
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = CATMOR.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
