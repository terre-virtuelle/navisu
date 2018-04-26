package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RESTRN {

    public static final Map<String, String> ATT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("1", "anchoring prohibited");
            put("2", "anchoring restricted");
            put("3", "fishing prohibited");
            put("4", "fishing restricted");
            put("5", "trawling prohibited");
            put("6", "trawling restricted");
            put("7", "entry prohibited");
            put("8", "entry restricted");
            put("9", "dredging prohibited");
            put("10", "dredging restricted");
            put("11", "diving prohibited");
            put("12", "diving restricted");
            put("13", "no wake");
            put("14", "area to be avoided");
            put("15", "construction prohibited");
            put("16", "discharging prohibited");
            put("17", "discharging restricted");
            put("18", "industrial or mineral exploration/development prohibited");
            put("19", "industrial or mineral exploration/development restricted");
            put("20", "drilling prohibited");
            put("21", "drilling restricted");
            put("22", "removal of historical artifacts prohibited");
            put("23", "cargo transhipment (lightering) prohibited");
            put("24", "dragging prohibited");
            put("25", "stopping prohibited");
            put("26", "landing prohibited");
            put("27", "	speed restricted");
      
        }
    });

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        Set<Map.Entry<String, String>> entries = RESTRN.ATT.entrySet();
        buffer.append("[");
        entries.stream().forEach((e) -> {
            buffer.append("[").append(e.getKey()).append(", ").append(e.getValue()).append("]");
        });
        buffer.append("]");
        return buffer.toString();
    }

}
